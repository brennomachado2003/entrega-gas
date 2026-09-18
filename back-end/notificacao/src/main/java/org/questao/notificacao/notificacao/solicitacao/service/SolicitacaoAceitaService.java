package org.questao.notificacao.notificacao.solicitacao.service;

import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.SolicitarEntregaRepository;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.event.SolicitacaoEntregaAceitaEvent;
import org.questao.notificacao.publicador.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SolicitacaoAceitaService {

    private final SolicitarEntregaRepository solicitacaoEntregaRepository;
    private final DomainEventPublisher domainEventPublisherSSE;
    private DomainEventPublisher domainEventPublisherKafka;

    public SolicitacaoAceitaService(SolicitarEntregaRepository solicitacaoEntregaRepository,
                                    @Qualifier("sseDomainEventsPublisher") DomainEventPublisher domainEventPublisherSSE,
                                    @Qualifier("kafkaDomainEventsPublisher") DomainEventPublisher domainEventPublisherKafka) {
        this.solicitacaoEntregaRepository = solicitacaoEntregaRepository;
        this.domainEventPublisherSSE = domainEventPublisherSSE;
        this.domainEventPublisherKafka = domainEventPublisherKafka;
    }

    public void atualizar(Long idSolicitacao) {
        SolicitacaoEntrega solicitacaoEntrega = solicitacaoEntregaRepository.buscar(idSolicitacao);
        solicitacaoEntrega.aceitarSolicitacao();
        solicitacaoEntregaRepository.salvar(solicitacaoEntrega);
        List<SolicitacaoEntrega> list = solicitacaoEntregaRepository.buscarListaSolicitacoesPedidoPendente(solicitacaoEntrega.getPedido());
        solicitacaoEntregaRepository.solicitacoesRecusadas(solicitacaoEntrega.getPedido());
        domainEventPublisherKafka.publicar(solicitacaoEntrega.getEvents());
        solicitacaoEntrega.limparEvents();
        list.forEach(solicitacaoPendente -> domainEventPublisherSSE.publicar(java.util.List.of(new SolicitacaoEntregaAceitaEvent(solicitacaoPendente.getEntregador(), solicitacaoPendente.getPedido()))));
    }
}
