package org.questao.usuario.endereco.consumer.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.questao.usuario.endereco.consumer.kafka.event.EnderecoLocalizadoKafka;
import org.questao.usuario.endereco.service.AtualizarCordenadas;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnderecoLocalizadoConsumer {

    private final AtualizarCordenadas atualizarCordenadas;
    private final ObjectMapper objectMapper;

    public EnderecoLocalizadoConsumer(
            AtualizarCordenadas atualizarCordenadas,
            ObjectMapper objectMapper
    ) {
        this.atualizarCordenadas = atualizarCordenadas;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "${topics.endereco-localizado}",
            groupId = "usuario"
    )
    public void consumir(String mensagem) {

        try {
            EnderecoLocalizadoKafka evento = objectMapper.readValue(mensagem, EnderecoLocalizadoKafka.class);
            atualizarCordenadas.atualizarCordenadas(evento.enderecoId(), evento.latitude(), evento.longitude());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter mensagem do Kafka", e);
        }
    }
}