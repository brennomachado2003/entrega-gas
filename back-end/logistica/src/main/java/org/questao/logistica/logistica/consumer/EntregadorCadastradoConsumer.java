package org.questao.logistica.logistica.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.questao.logistica.logistica.consumer.events.EntregadorCadastradoKafka;
import org.questao.logistica.logistica.rastreamento.dominio.PosicaoEntregador;
import org.questao.logistica.logistica.rastreamento.service.CriarPosicaoEntregadorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EntregadorCadastradoConsumer {


    private final ObjectMapper objectMapper;
    private final CriarPosicaoEntregadorService criarPosicaoEntregadorService;

    public EntregadorCadastradoConsumer(
            ObjectMapper objectMapper,
            CriarPosicaoEntregadorService criarPosicaoEntregadorService
    ) {
        this.objectMapper = objectMapper;
        this.criarPosicaoEntregadorService = criarPosicaoEntregadorService;
    }

    @KafkaListener(topics = "entregador-cadastrado", groupId = "logistica")
    public void consumir(String mensagem) {
        try {
            EntregadorCadastradoKafka evento = objectMapper.readValue( mensagem, EntregadorCadastradoKafka.class );
            PosicaoEntregador posicaoEntregador = PosicaoEntregador.criarPosicaoEntregador(evento.idEntregador());
            criarPosicaoEntregadorService.cadastrar(posicaoEntregador);
        }
        catch (Exception e) { throw new RuntimeException( "Erro ao processar mensagem de endereço cadastrado", e ); }
    }
}
