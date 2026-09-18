package org.questao.logistica.logistica.consumer.events;

import java.time.Instant;

public record PedidoCriadoKafka(
        Long idPedido,
        Long idEndereco,
        Instant ocorridoEm
){
}
