package org.questao.notificacao.publicador;

import org.questao.notificacao.notificacao.solicitacao.infraestrutura.event.SolicitacaoEntregaAceitaEvent;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.event.SolicitacaoEntregaCriadaEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("sseDomainEventsPublisher")
public class SseDomainEventsPublisher implements DomainEventPublisher {

    private final Map<Long, SseEmitter> conexoes = new ConcurrentHashMap<>();

    public SseEmitter conectar(Long idEntregador) {

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        conexoes.put(idEntregador, emitter);
        emitter.onCompletion(() -> {
            conexoes.remove(idEntregador);
        });
        emitter.onTimeout(() -> {
            conexoes.remove(idEntregador);
        });
        emitter.onError(erro -> {
            conexoes.remove(idEntregador);
        });

        return emitter;
    }

    @Override
    public void publicar(Collection<DomainEvent> events) {
        events.forEach(this::enviar);
    }

    private void enviar(DomainEvent evento) {

        if (evento instanceof SolicitacaoEntregaCriadaEvent solicitacao) {
            Long idEntregador = solicitacao.idEntregador();
            SseEmitter emitter = conexoes.get(idEntregador);
            if (emitter == null) return;
            try {
                emitter.send(SseEmitter.event().name("solicitacao-entrega").data(solicitacao));
            } catch (IOException e) {
                conexoes.remove(idEntregador);
                emitter.completeWithError(e);
            }
        }
        if (evento instanceof SolicitacaoEntregaAceitaEvent solicitacao) {
            Long idPedido = solicitacao.idEntregador();
            SseEmitter emitter = conexoes.get(idPedido);
            if (emitter == null) return;
            try {
                emitter.send(SseEmitter.event().name("solicitacao-aceita").data(solicitacao));
            } catch (IOException e) {
                conexoes.remove(idPedido);
                emitter.completeWithError(e);
            }
        }
    }
}