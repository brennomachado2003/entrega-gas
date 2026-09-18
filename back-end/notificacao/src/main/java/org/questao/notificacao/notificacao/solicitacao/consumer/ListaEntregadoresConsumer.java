package org.questao.notificacao.notificacao.solicitacao.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.questao.notificacao.notificacao.solicitacao.consumer.events.ListaEntregadoresProximosKafka;
import org.questao.notificacao.notificacao.solicitacao.service.CriarSolicitacoesService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class ListaEntregadoresConsumer {

    private final CriarSolicitacoesService criarSolicitacoesService;
    private final ObjectMapper objectMapper;

    public ListaEntregadoresConsumer(
            CriarSolicitacoesService criarSolicitacoesService,
            ObjectMapper objectMapper
    ) {
        this.criarSolicitacoesService = criarSolicitacoesService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${topics.lista-entregadores}", groupId = "solicitacao")
    public void consumir(String mensagem) {
        try {
            ListaEntregadoresProximosKafka evento = objectMapper.readValue( mensagem, ListaEntregadoresProximosKafka.class );
            criarSolicitacoesService.criarSolicitacoes(evento.idPedido(), evento.lista());
        }
        catch (Exception e) { throw new RuntimeException( "Erro ao processar mensagem de endereço cadastrado", e ); }
    }
}
