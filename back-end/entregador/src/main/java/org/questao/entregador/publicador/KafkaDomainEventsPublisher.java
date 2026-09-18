package org.questao.entregador.publicador;

import org.questao.entregador.entregador.infraestrutura.events.EntregadorCadastrado;
import org.questao.entregador.entregador.infraestrutura.kafka.EntregadorCadastradoKafka;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class KafkaDomainEventsPublisher implements DomainEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topics.entregador-cadastrado}")
    private String entregadorCadastradoTopic;

    public KafkaDomainEventsPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicar(Collection<DomainEvent> events) {
        events.forEach(this::enviar);
    }

    private void enviar(DomainEvent evento) {

        if (evento instanceof EntregadorCadastrado entregadorCadastrado) {

            var mensagem = new EntregadorCadastradoKafka(
                    entregadorCadastrado.idEntregador()
            );

            kafkaTemplate.send(
                    entregadorCadastradoTopic,
                    entregadorCadastrado.idEntregador().toString(),
                    mensagem
            );
        }
    }
}