package org.questao.pedidos.itemTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.questao.pedidos.itemPedido.dominio.ItemPedido;
import org.questao.pedidos.itemPedido.infraestrutura.ItemPedidoRepository;
import org.questao.pedidos.itemPedido.service.*;
import org.questao.pedidos.pedido.intraestrutura.PedidoEntity;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemPedidoServiceTest {

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    @InjectMocks
    private CadastrarItemPedidoService cadastrarItemPedidoService;

    @InjectMocks
    private BuscarItemPedidoService buscarItemPedidoService;

    @InjectMocks
    private DeletarPedidoService deletarPedidoService;

    @InjectMocks
    private ListaItemPedidosService listaItemPedidosService;

    @InjectMocks
    private RegistrarListaPedidoItemService registrarListaPedidoItemService;


    @Test
    void deveCadastrarItemPedido() {

        ItemPedido itemPedido = mock(ItemPedido.class);

        when(itemPedidoRepository.salvar(itemPedido))
                .thenReturn(itemPedido);

        ItemPedido resultado =
                cadastrarItemPedidoService.cadastrar(itemPedido);

        assertSame(itemPedido, resultado);

        verify(itemPedidoRepository)
                .salvar(itemPedido);
    }


    @Test
    void deveBuscarItemPedido() {

        Long id = 1L;

        ItemPedido itemPedido = mock(ItemPedido.class);

        when(itemPedidoRepository.buscar(id))
                .thenReturn(itemPedido);

        ItemPedido resultado =
                buscarItemPedidoService.buscar(id);

        assertSame(itemPedido, resultado);

        verify(itemPedidoRepository)
                .buscar(id);
    }


    @Test
    void deveDeletarItemPedido() {

        Long id = 1L;

        deletarPedidoService.deletar(id);

        verify(itemPedidoRepository)
                .excluir(id);
    }


    @Test
    void deveListarItemPedidos() {

        List<ItemPedido> itens = List.of(
                mock(ItemPedido.class),
                mock(ItemPedido.class)
        );

        when(itemPedidoRepository.listar())
                .thenReturn(itens);

        List<ItemPedido> resultado =
                listaItemPedidosService.listarItemPedido();

        assertSame(itens, resultado);

        verify(itemPedidoRepository)
                .listar();
    }


    @Test
    void deveRegistrarListaDeItensDoPedido() {

        PedidoEntity pedido = mock(PedidoEntity.class);

        ItemPedido item1 = mock(ItemPedido.class);
        ItemPedido item2 = mock(ItemPedido.class);
        ItemPedido item3 = mock(ItemPedido.class);

        List<ItemPedido> itens = List.of(
                item1,
                item2,
                item3
        );

        registrarListaPedidoItemService
                .registrarListaPedidoItem(pedido, itens);

        verify(itemPedidoRepository).salvar(item1);
        verify(itemPedidoRepository).salvar(item2);
        verify(itemPedidoRepository).salvar(item3);

        verify(itemPedidoRepository, times(3))
                .salvar(any(ItemPedido.class));
    }
}
