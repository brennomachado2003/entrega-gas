package org.questao.pedidos.comsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.questao.pedidos.comsumer.events.SolicitacaoEntregaAceitaKafka;
import org.questao.pedidos.pedido.service.AceitarPedidoService;
import org.questao.pedidos.publicador.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PedidoAceitoConsumer {

    private final AceitarPedidoService aceitarPedidoService;
    private final ObjectMapper objectMapper;
    private final DomainEventPublisher domainEventPublisherSSE;

    public PedidoAceitoConsumer(
            AceitarPedidoService aceitarPedidoService,
            ObjectMapper objectMapper,
            @Qualifier("sseDomainEventsPublisher")  DomainEventPublisher domainEventPublisherSSE
    ) {
        this.aceitarPedidoService = aceitarPedidoService;
        this.objectMapper = objectMapper;
        this.domainEventPublisherSSE = domainEventPublisherSSE;
    }

    @KafkaListener(topics = "${topics.pedido-aceito}", groupId = "pedido")
    public void consumir(String mensagem) {
        try {
            SolicitacaoEntregaAceitaKafka evento = objectMapper.readValue( mensagem, SolicitacaoEntregaAceitaKafka.class );
            aceitarPedidoService.aceitarPedido(evento.idPedido(), evento.idEntregador());
            domainEventPublisherSSE.publicar(List.of(evento));
        }
        catch (Exception e) { throw new RuntimeException( "Erro ao processar mensagem de endereço cadastrado", e ); }
    }
}
