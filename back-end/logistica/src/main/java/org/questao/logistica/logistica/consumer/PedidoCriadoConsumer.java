package org.questao.logistica.logistica.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.questao.logistica.logistica.consumer.events.ListaEntregadoresProximosKafka;
import org.questao.logistica.logistica.consumer.events.PedidoCriadoKafka;
import org.questao.logistica.logistica.integracao.endereco.api.EnderecoAPI;
import org.questao.logistica.logistica.integracao.endereco.dto.LocalizacaoResponseDTO;
import org.questao.logistica.logistica.rastreamento.service.BuscarEntregadoresMaisProximoService;
import org.questao.logistica.publicador.DomainEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoCriadoConsumer {

    private final DomainEventPublisher domainEventPublisher;
    private final ObjectMapper objectMapper;
    private final BuscarEntregadoresMaisProximoService buscarEntregadoresMaisProximoService;
    private final EnderecoAPI enderecoAPI;
    private final GeometryFactory geometryFactory;

    public PedidoCriadoConsumer(
            DomainEventPublisher domainEventPublisher,
            ObjectMapper objectMapper,
            BuscarEntregadoresMaisProximoService buscarEntregadoresMaisProximoService,
            EnderecoAPI enderecoAPI,
            GeometryFactory geometryFactory
    ) {
        this.domainEventPublisher = domainEventPublisher;
        this.objectMapper = objectMapper;
        this.buscarEntregadoresMaisProximoService = buscarEntregadoresMaisProximoService;
        this.enderecoAPI = enderecoAPI;
        this.geometryFactory = geometryFactory;
    }

    @KafkaListener(topics = "${topics.pedido-criado}", groupId = "logistica")
    public void consumir(String mensagem) {


        try {
            PedidoCriadoKafka evento = objectMapper.readValue( mensagem, PedidoCriadoKafka.class );
            ResponseEntity<LocalizacaoResponseDTO> response = enderecoAPI.buscarEndereco(evento.idEndereco());
            LocalizacaoResponseDTO localizacao = response.getBody();
            Point coordenada = geometryFactory.createPoint(new Coordinate(localizacao.longitude(), localizacao.latitude()));
            coordenada.setSRID(4326);
            List<Long> ids = buscarEntregadoresMaisProximoService.buscarEntregadoresMaisProximos(coordenada, 5000);
            ListaEntregadoresProximosKafka listaEntregadoresProximosKafka = new ListaEntregadoresProximosKafka(ids, evento.idPedido());
            domainEventPublisher.publicar(List.of(listaEntregadoresProximosKafka));
        }
        catch (Exception e) { throw new RuntimeException( "Erro ao processar mensagem de endereço cadastrado", e ); }
    }
}
