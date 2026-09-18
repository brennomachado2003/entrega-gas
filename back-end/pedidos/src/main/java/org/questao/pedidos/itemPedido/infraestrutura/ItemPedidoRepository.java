package org.questao.pedidos.itemPedido.infraestrutura;

import org.questao.pedidos.itemPedido.dominio.ItemPedido;

import java.util.List;

public interface ItemPedidoRepository {

    List<ItemPedido> listar();

    ItemPedido buscar(Long id);

    ItemPedido salvar(ItemPedido itemPedido);

    ItemPedido atualizar(Long id, ItemPedido dados);

    void excluir(Long id);
}
