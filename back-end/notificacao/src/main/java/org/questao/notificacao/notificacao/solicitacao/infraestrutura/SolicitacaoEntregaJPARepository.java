package org.questao.notificacao.notificacao.solicitacao.infraestrutura;


import org.questao.notificacao.notificacao.solicitacao.dominio.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitacaoEntregaJPARepository extends JpaRepository<SolicitacaoEntregaEntity, Long> {

    List<SolicitacaoEntregaEntity> findByPedidoAndStatus(
            Long pedido,
            StatusSolicitacao status
    );

    List<SolicitacaoEntregaEntity> findByEntregadorAndStatus(
            Long entregador,
            StatusSolicitacao status
    );
    @Modifying
    @Query("""
    UPDATE SolicitacaoEntregaEntity s
    SET s.status = :status
    WHERE s.pedido = :idPedido
    AND s.status = org.questao.notificacao.notificacao.solicitacao.dominio.StatusSolicitacao.PENDENTE
""")
    int atualizarStatusPendentes(@Param("idPedido") Long idPedido, @Param("status") StatusSolicitacao status);
}