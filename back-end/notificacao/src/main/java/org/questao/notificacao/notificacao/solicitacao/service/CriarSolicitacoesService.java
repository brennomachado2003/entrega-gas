package org.questao.notificacao.notificacao.solicitacao.service;

import org.questao.notificacao.notificacao.solicitacao.dominio.SolicitacaoEntrega;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.SolicitarEntregaRepository;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.event.SolicitacaoEntregaCriadaEvent;
import org.questao.notificacao.publicador.DomainEvent;
import org.questao.notificacao.publicador.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriarSolicitacoesService {

    private final SolicitarEntregaRepository solicitacaoEntregaRepository;
    private final DomainEventPublisher domainEventPublisher;

    public CriarSolicitacoesService(SolicitarEntregaRepository solicitacaoEntregaRepository,
                                    @Qualifier("sseDomainEventsPublisher") DomainEventPublisher domainEventPublisher) {
        this.solicitacaoEntregaRepository = solicitacaoEntregaRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public void criarSolicitacoes(Long pedido, List<Long> entregadores) {

        List<SolicitacaoEntrega> solicitacoes = entregadores.stream().map(entregador -> {
                    SolicitacaoEntrega solicitacao = SolicitacaoEntrega.iniciarSolicitacao(pedido, entregador);
                    SolicitacaoEntrega salvo = solicitacaoEntregaRepository.salvar(solicitacao);
                    salvo.register(new SolicitacaoEntregaCriadaEvent(
                            salvo.getIdSolicitacao(),
                            salvo.getPedido(),
                            salvo.getEntregador()));
                    return salvo;
                }).toList();

        List<DomainEvent> eventos = solicitacoes.stream()
                .flatMap(solicitacao -> solicitacao.getEvents().stream())
                .toList();
        domainEventPublisher.publicar(eventos);
    }


}
