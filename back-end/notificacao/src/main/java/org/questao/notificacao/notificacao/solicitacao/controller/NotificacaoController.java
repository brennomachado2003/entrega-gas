package org.questao.notificacao.notificacao.solicitacao.controller;

import org.questao.notificacao.publicador.SseDomainEventsPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final SseDomainEventsPublisher ssePublisher;

    public NotificacaoController(SseDomainEventsPublisher ssePublisher) {
        this.ssePublisher = ssePublisher;
    }

    @GetMapping(value = "/entregadores/{idEntregador}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter conectar(@PathVariable Long idEntregador) {
        return ssePublisher.conectar(idEntregador);
    }
}