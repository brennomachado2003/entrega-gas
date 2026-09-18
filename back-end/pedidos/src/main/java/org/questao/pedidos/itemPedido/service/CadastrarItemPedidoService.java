package org.questao.pedidos.itemPedido.service;

import org.questao.pedidos.itemPedido.dominio.ItemPedido;
import org.questao.pedidos.itemPedido.infraestrutura.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastrarItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public CadastrarItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public ItemPedido cadastrar(ItemPedido itemPedido) {
        return itemPedidoRepository.salvar(itemPedido);
    }
}
