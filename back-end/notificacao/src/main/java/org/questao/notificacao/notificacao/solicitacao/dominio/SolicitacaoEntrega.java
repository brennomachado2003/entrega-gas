package org.questao.notificacao.notificacao.solicitacao.dominio;

import lombok.Getter;
import lombok.Setter;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.event.SolicitacaoEntregaAceitaEvent;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.event.SolicitacaoEntregaCriadaEvent;
import org.questao.notificacao.publicador.AggregateRoot;

import java.time.LocalDateTime;

@Getter
@Setter
public class SolicitacaoEntrega extends AggregateRoot {

    private final Long idSolicitacao;
    private final Long pedido;
    private final Long entregador;
    private StatusSolicitacao status;
    private LocalDateTime dataSolicitacao;

    public SolicitacaoEntrega(Long idSolicitacao, Long pedido, Long entregador, StatusSolicitacao status, LocalDateTime dataSolicitacao) {
        this.idSolicitacao = idSolicitacao;
        this.pedido = pedido;
        this.entregador = entregador;
        this.status = status;
        this.dataSolicitacao = dataSolicitacao;
    }

    public static SolicitacaoEntrega reconstituir(Long idSolicitacao, Long pedido, Long entregador, StatusSolicitacao status, LocalDateTime dataSolicitacao) {
        return new SolicitacaoEntrega(idSolicitacao, pedido, entregador, status, dataSolicitacao);
    }


    public void atualizarStatus(StatusSolicitacao dados) {
        this.status = dados;
    }

    public static SolicitacaoEntrega iniciarSolicitacao(Long pedido, Long entregador) {
        return new SolicitacaoEntrega(null, pedido, entregador, StatusSolicitacao.PENDENTE, LocalDateTime.now());
    }

    public void aceitarSolicitacao(){
        this.status = StatusSolicitacao.ACEITA;
        register(new SolicitacaoEntregaAceitaEvent(entregador, pedido));
    }


}
