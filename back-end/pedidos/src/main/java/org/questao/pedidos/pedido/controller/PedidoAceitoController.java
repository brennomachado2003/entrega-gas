package org.questao.pedidos.pedido.controller;

import org.questao.pedidos.publicador.SseDomainEventsPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/pedidoAceito")
public class PedidoAceitoController {

    private final SseDomainEventsPublisher ssePublisher;

    public PedidoAceitoController(SseDomainEventsPublisher ssePublisher) {
        this.ssePublisher = ssePublisher;
    }

    @GetMapping(value = "/{idPedido}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter conectar(@PathVariable Long idPedido) {
        return ssePublisher.conectar(idPedido);
    }
}