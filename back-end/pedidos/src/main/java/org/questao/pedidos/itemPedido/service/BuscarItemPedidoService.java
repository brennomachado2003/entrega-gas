package org.questao.pedidos.itemPedido.service;

import org.questao.pedidos.itemPedido.dominio.ItemPedido;
import org.questao.pedidos.itemPedido.infraestrutura.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public BuscarItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public ItemPedido buscar(Long id){
        return itemPedidoRepository.buscar(id);
    }
}
