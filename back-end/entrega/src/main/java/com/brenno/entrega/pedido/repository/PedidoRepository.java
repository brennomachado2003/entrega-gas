package com.brenno.entrega.pedido.repository;

import com.brenno.entrega.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    /*
    save(pedido)
    findById(id)
    findAll()
    deleteById(id)
    delete(pedido)
    existsById(id)
    count()
    findAllById(ids)
    */

    @Modifying
    @Query("""
    UPDATE Pedido p
    SET p.status.idStatus = 3,
        p.entregador.idEntregador = :entregadorId
    WHERE p.idPedido = :pedidoId
    AND p.status.idStatus = 2
    """)
    int aceitarPedido(Integer pedidoId, Integer entregadorId);
    List<Pedido> findByUsuarioIdUsuarioOrderByDataPedidoDesc(Integer usuarioId);
    List<Pedido> findByEntregadorIdEntregadorOrderByDataPedidoDesc(Integer entregadorId);

}
