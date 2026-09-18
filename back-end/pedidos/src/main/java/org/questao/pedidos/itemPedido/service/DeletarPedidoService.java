package org.questao.pedidos.itemPedido.service;

import org.questao.pedidos.itemPedido.infraestrutura.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public  DeletarPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public void deletar(Long itemPedido){
        itemPedidoRepository.excluir(itemPedido);
    }
}
