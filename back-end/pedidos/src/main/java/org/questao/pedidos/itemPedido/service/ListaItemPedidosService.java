package org.questao.pedidos.itemPedido.service;

import org.questao.pedidos.itemPedido.dominio.ItemPedido;
import org.questao.pedidos.itemPedido.infraestrutura.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaItemPedidosService {

    private final ItemPedidoRepository itemPedidoRepository;

    public ListaItemPedidosService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public List<ItemPedido> listarItemPedido(){
        return itemPedidoRepository.listar();
    }
}
