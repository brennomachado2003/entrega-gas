package org.questao.notificacao.publicador;


import org.questao.notificacao.notificacao.solicitacao.infraestrutura.event.SolicitacaoEntregaAceitaEvent;
import org.questao.notificacao.notificacao.solicitacao.infraestrutura.kafka.SolicitacaoEntregaAceitaKafka;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("kafkaDomainEventsPublisher")
public class KafkaDomainEventsPublisher implements DomainEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topics.pedido-aceito}")
    private String pedidoAceitoTopic;

    public KafkaDomainEventsPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicar(Collection<DomainEvent> events) {
        events.forEach(this::enviar);
    }

    private void enviar(DomainEvent evento) {
        if (evento instanceof SolicitacaoEntregaAceitaEvent solicitacaoEntregaAceitaEvent) {
            var mensagem = new SolicitacaoEntregaAceitaKafka(
                    solicitacaoEntregaAceitaEvent.idEntregador(),
                    solicitacaoEntregaAceitaEvent.idPedido()
            );
            kafkaTemplate.send(pedidoAceitoTopic, solicitacaoEntregaAceitaEvent.idPedido().toString(), mensagem);
        }
    }
}