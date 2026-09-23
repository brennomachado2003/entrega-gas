package org.questao.usuario.enderecoTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.questao.usuario.Erros.endereco.ErroAoAtualizarCordenadasEndereco;
import org.questao.usuario.Erros.endereco.ErroAoBuscarEnderecoPeloId;
import org.questao.usuario.Erros.endereco.ErroAoCadastrarEndereco;
import org.questao.usuario.Erros.endereco.ErroAoListarEnderecoPeloUsuario;
import org.questao.usuario.endereco.dominio.Endereco;
import org.questao.usuario.endereco.infraestrutura.EnderecoRepository;
import org.questao.usuario.endereco.service.AtualizarCordenadas;
import org.questao.usuario.endereco.service.BuscarEnderecoService;
import org.questao.usuario.endereco.service.CadastrarEnderecoService;
import org.questao.usuario.endereco.service.ListaEnderecoService;
import org.questao.usuario.publicador.DomainEventPublisher;
import org.questao.usuario.usuario.dominio.Usuario;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private GeometryFactory geometryFactory;

    @Mock
    private DomainEventPublisher domainEventPublisher;

    @Mock
    private Point point;

    @InjectMocks
    private AtualizarCordenadas atualizarCordenadas;

    @InjectMocks
    private BuscarEnderecoService buscarEnderecoService;

    @InjectMocks
    private CadastrarEnderecoService cadastrarEnderecoService;

    @InjectMocks
    private ListaEnderecoService listaEnderecoService;


    @Test
    void deveAtualizarCordenadas() {

        Long id = 1L;
        Double latitude = -12.9714;
        Double longitude = -38.5014;

        Endereco endereco = mock(Endereco.class);

        when(enderecoRepository.buscar(id))
                .thenReturn(endereco);

        when(geometryFactory.createPoint(any(Coordinate.class)))
                .thenReturn(point);

        when(enderecoRepository.salvar(endereco))
                .thenReturn(endereco);

        Endereco resultado =
                atualizarCordenadas.atualizarCordenadas(
                        id,
                        latitude,
                        longitude
                );

        assertNotNull(resultado);
        assertEquals(endereco, resultado);

        verify(enderecoRepository)
                .buscar(id);

        verify(geometryFactory)
                .createPoint(any(Coordinate.class));

        verify(point)
                .setSRID(4326);

        verify(endereco)
                .atualizarCordenadas(point);

        verify(enderecoRepository)
                .salvar(endereco);
    }


    @Test
    void deveLancarErroAoAtualizarCordenadas() {

        Long id = 1L;

        when(enderecoRepository.buscar(id))
                .thenThrow(new RuntimeException());

        assertThrows(
                ErroAoAtualizarCordenadasEndereco.class,
                () -> atualizarCordenadas.atualizarCordenadas(
                        id,
                        -12.9714,
                        -38.5014
                )
        );

        verify(enderecoRepository)
                .buscar(id);
    }


    @Test
    void deveBuscarEndereco() {

        Long id = 1L;

        Endereco endereco = mock(Endereco.class);

        when(enderecoRepository.buscar(id))
                .thenReturn(endereco);

        Endereco resultado =
                buscarEnderecoService.buscar(id);

        assertNotNull(resultado);
        assertEquals(endereco, resultado);

        verify(enderecoRepository)
                .buscar(id);
    }


    @Test
    void deveLancarErroAoBuscarEndereco() {

        Long id = 1L;

        when(enderecoRepository.buscar(id))
                .thenThrow(new RuntimeException());

        assertThrows(
                ErroAoBuscarEnderecoPeloId.class,
                () -> buscarEnderecoService.buscar(id)
        );

        verify(enderecoRepository)
                .buscar(id);
    }


    @Test
    void deveCadastrarEndereco() {

        Endereco endereco = mock(Endereco.class);

        when(enderecoRepository.salvar(endereco))
                .thenReturn(endereco);

        CadastrarEnderecoService service =
                new CadastrarEnderecoService(
                        enderecoRepository,
                        domainEventPublisher
                );

        Endereco resultado =
                service.cadastrar(endereco);

        assertNotNull(resultado);
        assertEquals(endereco, resultado);

        verify(enderecoRepository)
                .salvar(endereco);

        verify(endereco)
                .enderecoCadastrado(endereco);

        verify(domainEventPublisher)
                .publicar(endereco.getEvents());

        verify(endereco)
                .limparEvents();
    }


    @Test
    void deveLancarErroAoCadastrarEndereco() {

        Endereco endereco = mock(Endereco.class);

        when(enderecoRepository.salvar(endereco))
                .thenThrow(new RuntimeException());

        assertThrows(
                ErroAoCadastrarEndereco.class,
                () -> cadastrarEnderecoService.cadastrar(endereco)
        );

        verify(enderecoRepository)
                .salvar(endereco);
    }


    @Test
    void deveListarEnderecosPorUsuario() {

        Usuario usuario = mock(Usuario.class);

        List<Endereco> enderecos = List.of(
                mock(Endereco.class),
                mock(Endereco.class)
        );

        when(enderecoRepository.listaEndereco(usuario))
                .thenReturn(enderecos);

        List<Endereco> resultado =
                listaEnderecoService.listEndereco(usuario);

        assertNotNull(resultado);
        assertEquals(enderecos, resultado);

        verify(enderecoRepository)
                .listaEndereco(usuario);
    }


    @Test
    void deveLancarErroAoListarEnderecosPorUsuario() {

        Usuario usuario = mock(Usuario.class);

        when(enderecoRepository.listaEndereco(usuario))
                .thenThrow(new RuntimeException());

        assertThrows(
                ErroAoListarEnderecoPeloUsuario.class,
                () -> listaEnderecoService.listEndereco(usuario)
        );

        verify(enderecoRepository)
                .listaEndereco(usuario);
    }
}
