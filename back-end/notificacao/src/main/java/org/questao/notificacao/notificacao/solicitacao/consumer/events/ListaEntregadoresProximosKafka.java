package org.questao.notificacao.notificacao.solicitacao.consumer.events;

import java.time.Instant;
import java.util.List;

public record ListaEntregadoresProximosKafka(
        List<Long> lista,
        Long idPedido,
        Instant ocorridoEm
){
    public ListaEntregadoresProximosKafka(List<Long> lista, Long idPedido) {
        this(lista, idPedido,Instant.now());
    }
}
