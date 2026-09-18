package org.questao.logistica.logistica.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.questao.logistica.logistica.buscarLocalizacao.dto.CoordenadaDTO;
import org.questao.logistica.logistica.buscarLocalizacao.dto.EnderecoDTO;
import org.questao.logistica.logistica.buscarLocalizacao.event.EnderecoLocalizado;
import org.questao.logistica.logistica.buscarLocalizacao.service.BuscarLocalizacaoService;
import org.questao.logistica.logistica.consumer.events.EnderecoCadastradoKafka;
import org.questao.logistica.publicador.DomainEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoCadastradoConsumer {

    private final BuscarLocalizacaoService buscarLocalizacaoService;
    private final DomainEventPublisher domainEventPublisher;
    private final ObjectMapper objectMapper;

    public EnderecoCadastradoConsumer(
            BuscarLocalizacaoService buscarLocalizacaoService,
            DomainEventPublisher domainEventPublisher,
            ObjectMapper objectMapper
    ) {
        this.buscarLocalizacaoService = buscarLocalizacaoService;
        this.domainEventPublisher = domainEventPublisher;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${topics.endereco-cadastrado}", groupId = "logistica")
    public void consumir(String mensagem) {


        try {
            EnderecoCadastradoKafka evento = objectMapper.readValue( mensagem, EnderecoCadastradoKafka.class );
            EnderecoDTO endereco = new EnderecoDTO(
                    evento.rua(),
                    evento.numero().toString(),
                    evento.complemento(),
                    evento.bairro(),
                    evento.cidade(),
                    evento.estado(),
                    evento.cep()
            );
            CoordenadaDTO coordenada = buscarLocalizacaoService.buscarLocalizacao(endereco);
            EnderecoLocalizado enderecoLocalizado = new EnderecoLocalizado(evento.enderecoId(), coordenada.latitude(), coordenada.longitude());
            domainEventPublisher.publicar(List.of(enderecoLocalizado));
        }
        catch (Exception e) { throw new RuntimeException( "Erro ao processar mensagem de endereço cadastrado", e ); }
    }
}
