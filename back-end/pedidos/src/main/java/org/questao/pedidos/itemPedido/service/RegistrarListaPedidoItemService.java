package org.questao.pedidos.itemPedido.service;

import org.questao.pedidos.itemPedido.dominio.ItemPedido;
import org.questao.pedidos.itemPedido.infraestrutura.ItemPedidoRepository;
import org.questao.pedidos.pedido.intraestrutura.PedidoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrarListaPedidoItemService {

    private final ItemPedidoRepository itemPedidoRepository;

    public RegistrarListaPedidoItemService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public void registrarListaPedidoItem(PedidoEntity pedido, List<ItemPedido> itemPedidos) {
        itemPedidos.forEach(itemPedidoRepository::salvar);
    }
}
