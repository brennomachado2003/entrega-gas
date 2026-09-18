package org.questao.pedidos.itemPedido.infraestrutura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoJPARepository extends JpaRepository<ItemPedidoEntity, Long> {
    /*
    save(pedidoProduto)
    findById(id)
    findAll()
    deleteById(id)
    delete(pedidoProduto)
    existsById(id)
    count()
    findAllById(ids)
    */
}
