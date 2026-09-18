package org.questao.notificacao.notificacao.solicitacao.infraestrutura;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.questao.notificacao.notificacao.solicitacao.dominio.StatusSolicitacao;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "solicitacao_entrega")
public class SolicitacaoEntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Long idSolicitacao;

    @Column(name = "id_pedido", nullable = false)
    private Long pedido;

    @Column(name = "id_entregador", nullable = false)
    private Long entregador;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusSolicitacao status;

    @Column(name = "data_solicitacao")
    private LocalDateTime dataSolicitacao;
}