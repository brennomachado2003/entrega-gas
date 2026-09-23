package org.questao.entregador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.questao.entregador.entregador.dominio.Entregador;
import org.questao.entregador.entregador.dominio.Senha;
import org.questao.entregador.entregador.dto.LoginResponse;
import org.questao.entregador.entregador.infraestrutura.EntregadorRepository;
import org.questao.entregador.entregador.service.*;
import org.questao.entregador.publicador.DomainEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.questao.entregador.entregador.dominio.Telefone;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntregadorServiceTest {

    @Mock
    private EntregadorRepository entregadorRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private DomainEventPublisher domainEventPublisher;

    @Mock
    private ValidarLoginService validarLoginServiceMock;

    @InjectMocks
    private AlterarStatusOnlineService alterarStatusOnlineService;

    @InjectMocks
    private BuscarEntregadorService buscarEntregadorService;

    @InjectMocks
    private CadastrarEntregadorService cadastrarEntregadorService;

    @InjectMocks
    private ValidarLoginService validarLoginService;

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService(validarLoginServiceMock);
    }

    @Test
    void deveAlterarStatusOnline() {

        Entregador entregador = mock(Entregador.class);

        when(entregador.getAtivo())
                .thenReturn(true);

        when(entregadorRepository.salvar(entregador))
                .thenReturn(entregador);

        Entregador resultado =
                alterarStatusOnlineService.alterarStatusOnline(entregador);

        assertNotNull(resultado);
        assertEquals(entregador, resultado);

        verify(entregador)
                .setAtivo(false);

        verify(entregadorRepository)
                .salvar(entregador);
    }


    @Test
    void deveBuscarEntregadorPorId() {

        Long id = 1L;

        Entregador entregador = mock(Entregador.class);

        when(entregadorRepository.buscar(id))
                .thenReturn(entregador);

        Entregador resultado =
                buscarEntregadorService.buscarEntregadorPorId(id);

        assertNotNull(resultado);
        assertEquals(entregador, resultado);

        verify(entregadorRepository)
                .buscar(id);
    }

    @Test
    void deveCadastrarEntregador() {

        Entregador entregador = mock(Entregador.class);
        Senha senha = mock(Senha.class);

        when(entregador.getSenha())
                .thenReturn(senha);

        when(senha.senha())
                .thenReturn("123456");

        when(passwordEncoder.encode("123456"))
                .thenReturn("senhaCriptografada");

        when(entregadorRepository.salvar(entregador))
                .thenReturn(entregador);

        Entregador resultado =
                cadastrarEntregadorService.cadastrar(entregador);

        assertNotNull(resultado);
        assertEquals(entregador, resultado);

        verify(passwordEncoder)
                .encode("123456");

        verify(entregador)
                .setSenha(any(Senha.class));

        verify(entregador)
                .setCriadoEm(any());

        verify(entregadorRepository)
                .salvar(entregador);

        verify(entregador)
                .entregadorCadastrado();

        verify(domainEventPublisher)
                .publicar(any());

        verify(entregador)
                .limparEvents();
    }


    @Test
    void deveValidarLogin() {

        String cpf = "12345678900";
        String senha = "123456";

        Entregador entregador = mock(Entregador.class);
        Senha senhaEntregador = mock(Senha.class);

        when(entregadorRepository.buscarPorCpf(cpf))
                .thenReturn(entregador);

        when(entregador.getSenha())
                .thenReturn(senhaEntregador);

        when(senhaEntregador.senha())
                .thenReturn("senhaCriptografada");

        when(passwordEncoder.matches(
                senha,
                "senhaCriptografada"
        )).thenReturn(true);

        Entregador resultado =
                validarLoginService.validarLogin(cpf, senha);

        assertNotNull(resultado);
        assertEquals(entregador, resultado);

        verify(entregadorRepository)
                .buscarPorCpf(cpf);

        verify(passwordEncoder)
                .matches(senha, "senhaCriptografada");
    }

    @Test
    void deveFazerLogin() {

        String cpf = "12345678900";
        String senha = "123456";

        Entregador entregador = mock(Entregador.class);
        Telefone telefone = mock(Telefone.class);

        when(validarLoginServiceMock.validarLogin(cpf, senha))
                .thenReturn(entregador);

        when(entregador.getIdEntregador())
                .thenReturn(1L);

        when(entregador.getNome())
                .thenReturn("Brenno");

        when(entregador.getTelefone())
                .thenReturn(telefone);

        when(telefone.telefone())
                .thenReturn("75999999999");

        when(entregador.getAtivo())
                .thenReturn(true);

        LoginResponse resultado =
                authService.login(cpf, senha);

        assertNotNull(resultado);

        assertEquals(
                1L,
                resultado.getId().longValue()
        );

        assertEquals(
                "Brenno",
                resultado.getNome()
        );

        assertEquals(
                "75999999999",
                resultado.getContato()
        );

        assertEquals(
                "Entregador",
                resultado.getTipo()
        );

        assertTrue(resultado.isAtivo());

        verify(validarLoginServiceMock)
                .validarLogin(cpf, senha);
    }
}
