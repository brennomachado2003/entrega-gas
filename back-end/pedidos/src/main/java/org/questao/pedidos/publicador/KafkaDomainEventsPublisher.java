package org.questao.pedidos.publicador;

import org.questao.pedidos.pedido.intraestrutura.events.PedidoCriado;
import org.questao.pedidos.pedido.intraestrutura.kafka.PedidoCriadoKafka;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("kafkaDomainEventsPublisher")
public class KafkaDomainEventsPublisher implements DomainEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topics.pedido-criado}")
    private String pedidoCriadoTopic;

    public KafkaDomainEventsPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicar(Collection<DomainEvent> events) {
        events.forEach(this::enviar);
    }

    private void enviar(DomainEvent evento) {
        if (evento instanceof PedidoCriado pedidoCriado) {
            var mensagem = new PedidoCriadoKafka(pedidoCriado.idPedido(), pedidoCriado.idEndereco());
            kafkaTemplate.send(pedidoCriadoTopic, pedidoCriado.idPedido().toString(), mensagem);
        }
    }
}