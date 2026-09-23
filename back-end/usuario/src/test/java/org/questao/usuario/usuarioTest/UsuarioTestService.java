package org.questao.usuario.usuarioTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.questao.usuario.Erros.usuario.ErroAoBusarUsuarioPeloId;
import org.questao.usuario.Erros.usuario.ErroAoCadastrarUsuario;
import org.questao.usuario.Erros.usuario.ErroAoFazerLogin;
import org.questao.usuario.Erros.usuario.ErroAoTentarValidarLoginUsuario;
import org.questao.usuario.usuario.dominio.Senha;
import org.questao.usuario.usuario.dominio.Usuario;
import org.questao.usuario.usuario.dto.LoginResponse;
import org.questao.usuario.usuario.infraestrutura.UsuarioRepository;
import org.questao.usuario.usuario.service.AuthService;
import org.questao.usuario.usuario.service.BuscarUsuarioService;
import org.questao.usuario.usuario.service.CadastrarUsuarioService;
import org.questao.usuario.usuario.service.ValidarLoginService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private ValidarLoginService validarLoginServiceMock;

    @InjectMocks
    private BuscarUsuarioService buscarUsuarioService;

    @InjectMocks
    private CadastrarUsuarioService cadastrarUsuarioService;

    @InjectMocks
    private ValidarLoginService validarLoginService;


    @Test
    void deveBuscarUsuarioPorId() {

        Long id = 1L;

        Usuario usuario = mock(Usuario.class);

        when(usuarioRepository.buscar(id))
                .thenReturn(usuario);

        Usuario resultado =
                buscarUsuarioService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);

        verify(usuarioRepository)
                .buscar(id);
    }


    @Test
    void deveLancarErroAoBuscarUsuarioPorId() {

        Long id = 1L;

        when(usuarioRepository.buscar(id))
                .thenThrow(new RuntimeException());

        assertThrows(
                ErroAoBusarUsuarioPeloId.class,
                () -> buscarUsuarioService.buscarPorId(id)
        );

        verify(usuarioRepository)
                .buscar(id);
    }


    @Test
    void deveCadastrarUsuario() {

        Usuario usuario = mock(Usuario.class);
        Senha senha = mock(Senha.class);

        String senhaOriginal = "123456";
        String senhaCriptografada = "senha-criptografada";

        when(usuario.getSenha())
                .thenReturn(senha);

        when(senha.senha())
                .thenReturn(senhaOriginal);

        when(passwordEncoder.encode(senhaOriginal))
                .thenReturn(senhaCriptografada);

        when(usuarioRepository.salvar(usuario))
                .thenReturn(usuario);

        Usuario resultado =
                cadastrarUsuarioService.cadastrar(usuario);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);

        verify(passwordEncoder)
                .encode(senhaOriginal);

        verify(usuario)
                .setSenha(org.mockito.ArgumentMatchers.any(Senha.class));

        verify(usuarioRepository)
                .salvar(usuario);
    }


    @Test
    void deveLancarErroAoCadastrarUsuario() {

        Usuario usuario = mock(Usuario.class);

        when(usuario.getSenha())
                .thenThrow(new RuntimeException());

        assertThrows(
                ErroAoCadastrarUsuario.class,
                () -> cadastrarUsuarioService.cadastrar(usuario)
        );

        verify(usuario)
                .getSenha();
    }


    @Test
    void deveValidarLogin() {

        String email = "usuario@email.com";
        String senha = "123456";

        Usuario usuario = mock(Usuario.class);
        Senha senhaUsuario = mock(Senha.class);

        when(usuarioRepository.buscarPorEmail(email))
                .thenReturn(usuario);

        when(usuario.getSenha())
                .thenReturn(senhaUsuario);

        when(senhaUsuario.senha())
                .thenReturn("senha-criptografada");

        when(passwordEncoder.matches(
                senha,
                "senha-criptografada"
        )).thenReturn(true);

        Usuario resultado =
                validarLoginService.validarLogin(email, senha);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);

        verify(usuarioRepository)
                .buscarPorEmail(email);

        verify(passwordEncoder)
                .matches(
                        senha,
                        "senha-criptografada"
                );
    }


    @Test
    void deveLancarErroQuandoUsuarioNaoExiste() {

        String email = "usuario@email.com";
        String senha = "123456";

        when(usuarioRepository.buscarPorEmail(email))
                .thenReturn(null);

        assertThrows(
                ErroAoTentarValidarLoginUsuario.class,
                () -> validarLoginService.validarLogin(email, senha)
        );

        verify(usuarioRepository)
                .buscarPorEmail(email);
    }


    @Test
    void deveLancarErroQuandoSenhaEstiverIncorreta() {

        String email = "usuario@email.com";
        String senha = "123456";

        Usuario usuario = mock(Usuario.class);
        Senha senhaUsuario = mock(Senha.class);

        when(usuarioRepository.buscarPorEmail(email))
                .thenReturn(usuario);

        when(usuario.getSenha())
                .thenReturn(senhaUsuario);

        when(senhaUsuario.senha())
                .thenReturn("senha-criptografada");

        when(passwordEncoder.matches(
                senha,
                "senha-criptografada"
        )).thenReturn(false);

        assertThrows(
                ErroAoTentarValidarLoginUsuario.class,
                () -> validarLoginService.validarLogin(email, senha)
        );

        verify(usuarioRepository)
                .buscarPorEmail(email);

        verify(passwordEncoder)
                .matches(
                        senha,
                        "senha-criptografada"
                );
    }
}