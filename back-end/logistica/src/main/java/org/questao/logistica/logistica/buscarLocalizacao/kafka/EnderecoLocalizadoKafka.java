package org.questao.logistica.logistica.buscarLocalizacao.kafka;

public record EnderecoLocalizadoKafka(
        Long enderecoId,
        Double latitude,
        Double longitude
) {
}
