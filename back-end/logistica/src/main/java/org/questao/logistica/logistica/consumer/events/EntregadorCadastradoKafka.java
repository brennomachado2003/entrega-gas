package org.questao.logistica.logistica.consumer.events;


import java.time.Instant;

public record EntregadorCadastradoKafka(
        Long idEntregador,
        Instant ocorridoEm
){
}
