package org.questao.pedidos.pedidoTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.questao.pedidos.pedido.dominio.Pedido;
import org.questao.pedidos.pedido.intraestrutura.PedidoRepository;
import org.questao.pedidos.pedido.service.*;
import org.questao.pedidos.publicador.DomainEventPublisher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private DomainEventPublisher domainEventPublisher;

    @InjectMocks
    private AberturaPedidoService aberturaPedidoService;

    @InjectMocks
    private AceitarPedidoService aceitarPedidoService;

    @InjectMocks
    private BuscarPedidoService buscarPedidoService;

    @InjectMocks
    private CancelarPedidoService cancelarPedidoService;

    @InjectMocks
    private ListarPedidosPorEntregadorService listarPedidosPorEntregadorService;

    @InjectMocks
    private ListarPedidosPorUsuarioService listarPedidosPorUsuarioService;


    @Test
    void deveAbrirPedido() {

        Long endereco = 1L;
        Long usuario = 2L;
        Long empresa = 3L;

        Pedido pedido = mock(Pedido.class);

        when(pedidoRepository.salvar(any(Pedido.class)))
                .thenReturn(pedido);

        Pedido resultado =
                aberturaPedidoService.aberturaPedido(
                        endereco,
                        usuario,
                        empresa
                );

        assertSame(pedido, resultado);

        verify(pedidoRepository)
                .salvar(any(Pedido.class));
    }


    @Test
    void deveAceitarPedido() {

        Long idPedido = 1L;
        Long idEntregador = 2L;

        Pedido pedido = mock(Pedido.class);

        when(pedidoRepository.buscar(idPedido))
                .thenReturn(pedido);

        when(pedidoRepository.salvar(pedido))
                .thenReturn(pedido);

        Pedido resultado =
                aceitarPedidoService.aceitarPedido(
                        idPedido,
                        idEntregador
                );

        verify(pedidoRepository)
                .buscar(idPedido);

        verify(pedido)
                .aceitarPedido(idEntregador);

        verify(pedidoRepository)
                .salvar(pedido);

        assertSame(pedido, resultado);
    }


    @Test
    void deveBuscarPedido() {

        Long id = 1L;

        Pedido pedido = mock(Pedido.class);

        when(pedidoRepository.buscar(id))
                .thenReturn(pedido);

        Pedido resultado =
                buscarPedidoService.busacarPedido(id);

        assertSame(pedido, resultado);

        verify(pedidoRepository)
                .buscar(id);
    }


    @Test
    void deveCancelarPedido() {

        Long pedidoId = 1L;

        Pedido pedido = mock(Pedido.class);

        when(pedidoRepository.buscar(pedidoId))
                .thenReturn(pedido);

        when(pedidoRepository.salvar(pedido))
                .thenReturn(pedido);

        Pedido resultado =
                cancelarPedidoService.cancelarPedido(pedidoId);

        verify(pedidoRepository)
                .buscar(pedidoId);

        verify(pedido)
                .cancelarPedido();

        verify(pedidoRepository)
                .salvar(pedido);

        assertSame(pedido, resultado);
    }


    @Test
    void deveListarPedidosPorEntregador() {

        Long entregadorId = 1L;

        List<Pedido> pedidos = List.of(
                mock(Pedido.class),
                mock(Pedido.class)
        );

        when(pedidoRepository.listarPorEntregador(entregadorId))
                .thenReturn(pedidos);

        List<Pedido> resultado =
                listarPedidosPorEntregadorService
                        .listaPedidosPorEntregador(entregadorId);

        assertSame(pedidos, resultado);

        verify(pedidoRepository)
                .listarPorEntregador(entregadorId);
    }


    @Test
    void deveListarPedidosPorUsuario() {

        Long usuarioId = 1L;

        List<Pedido> pedidos = List.of(
                mock(Pedido.class),
                mock(Pedido.class)
        );

        when(pedidoRepository.listarPorUsuario(usuarioId))
                .thenReturn(pedidos);

        List<Pedido> resultado =
                listarPedidosPorUsuarioService
                        .listarPedidosPorUsuario(usuarioId);

        assertSame(pedidos, resultado);

        verify(pedidoRepository)
                .listarPorUsuario(usuarioId);
    }
}