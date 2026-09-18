package org.questao.pedidos.publicador;

import org.questao.pedidos.comsumer.events.SolicitacaoEntregaAceitaKafka;
import org.questao.pedidos.pedido.intraestrutura.events.SolicitacaoEntregaAceitaEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("sseDomainEventsPublisher")
public class SseDomainEventsPublisher implements DomainEventPublisher {

    private final Map<Long, SseEmitter> conexoes = new ConcurrentHashMap<>();

    public SseEmitter conectar(Long idPedido) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        conexoes.put(idPedido, emitter);
        emitter.onCompletion(() -> {conexoes.remove(idPedido);});
        emitter.onTimeout(() -> {conexoes.remove(idPedido);});
        emitter.onError(erro -> {conexoes.remove(idPedido);});

        return emitter;
    }

    @Override
    public void publicar(Collection<DomainEvent> events) {
        events.forEach(this::enviar);
    }

    private void enviar(DomainEvent evento) {

        if (evento instanceof SolicitacaoEntregaAceitaKafka solicitacaoEntregaAceitaKafka) {
            SolicitacaoEntregaAceitaEvent solicitacao = new SolicitacaoEntregaAceitaEvent(solicitacaoEntregaAceitaKafka.idEntregador(), solicitacaoEntregaAceitaKafka.idPedido());
            Long idPedido = solicitacao.idPedido();
            SseEmitter emitter = conexoes.get(idPedido);
            if (emitter == null) return;
            try {
                emitter.send(SseEmitter.event().name("pedido-aceito").data(solicitacao));
            } catch (IOException e) {
                conexoes.remove(idPedido);
                emitter.completeWithError(e);
            }
        }
    }
}