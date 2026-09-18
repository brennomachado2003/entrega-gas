package org.questao.pedidos.pedido.intraestrutura.events;

import org.questao.pedidos.publicador.DomainEvent;

import java.time.Instant;

public record PedidoCriado (
        Long idPedido,
        Long idEndereco,
        Instant ocorridoEm
) implements DomainEvent {
    public PedidoCriado(Long idPedido, Long idEndereco) {
        this(idPedido, idEndereco,Instant.now());
    }
}
