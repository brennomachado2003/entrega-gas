package org.questao.pedidos.itemPedido.service;

import org.questao.pedidos.itemPedido.infraestrutura.ItemPedidoJPARepository;
import org.springframework.stereotype.Service;


@Service
public class ItemPedidoService {
    private final ItemPedidoJPARepository pedidoProdutoRepository;

    public ItemPedidoService(ItemPedidoJPARepository pedidoProdutoRepository) {
        this.pedidoProdutoRepository = pedidoProdutoRepository;
    }

}
