package org.questao.usuario.endereco.consumer.kafka.event;

public record EnderecoLocalizadoKafka(
        Long enderecoId,
        Double latitude,
        Double longitude
) {
}
