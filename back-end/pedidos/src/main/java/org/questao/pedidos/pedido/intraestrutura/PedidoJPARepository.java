package org.questao.pedidos.pedido.intraestrutura;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoJPARepository extends JpaRepository<PedidoEntity, Long> {

    List<PedidoEntity> findByUsuarioOrderByDataPedidoDesc(Long usuario);

    List<PedidoEntity> findByEntregadorOrderByDataPedidoDesc(Long entregador);

}



