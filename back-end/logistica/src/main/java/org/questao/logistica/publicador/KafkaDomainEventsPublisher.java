package org.questao.logistica.publicador;

import org.questao.logistica.logistica.buscarLocalizacao.event.EnderecoLocalizado;
import org.questao.logistica.logistica.buscarLocalizacao.kafka.EnderecoLocalizadoKafka;
import org.questao.logistica.logistica.consumer.events.ListaEntregadoresProximosKafka;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class KafkaDomainEventsPublisher implements DomainEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topics.endereco-localizado}")
    private String enderecoLocalizadoTopic;

    @Value("${topics.lista-entregadores}")
    private String listaEntregadoresTopic;

    public KafkaDomainEventsPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicar(Collection<DomainEvent> events) {
        events.forEach(this::enviar);
    }

    private void enviar(DomainEvent evento) {
        if (evento instanceof EnderecoLocalizado enderecoLocalizado) {
            var mensagem = new EnderecoLocalizadoKafka(
                    enderecoLocalizado.enderecoId(),
                    enderecoLocalizado.latitude(),
                    enderecoLocalizado.longitude()
            );
            kafkaTemplate.send(enderecoLocalizadoTopic, enderecoLocalizado.enderecoId().toString(), mensagem);
        }
        if (evento instanceof ListaEntregadoresProximosKafka listaEntregadoresProximos) {
            var mensagem = new ListaEntregadoresProximosKafka(listaEntregadoresProximos.lista(), listaEntregadoresProximos.idPedido());
            kafkaTemplate.send(listaEntregadoresTopic, mensagem);
        }
    }
}