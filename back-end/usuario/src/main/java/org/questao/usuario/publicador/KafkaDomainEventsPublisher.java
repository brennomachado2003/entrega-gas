package org.questao.usuario.publicador;

import org.questao.usuario.endereco.infraestrutura.events.EnderecoCadastrado;
import org.questao.usuario.endereco.infraestrutura.kafka.EnderecoCadastradoKafka;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class KafkaDomainEventsPublisher implements DomainEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topics.endereco-cadastrado}")
    private String enderecoCadastradoTopic;

    public KafkaDomainEventsPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicar(Collection<DomainEvent> events) {
        events.forEach(this::enviar);
    }

    private void enviar(DomainEvent evento) {

        if (evento instanceof EnderecoCadastrado enderecoCadastrado) {

            var mensagem = new EnderecoCadastradoKafka(
                    enderecoCadastrado.enderecoId(),
                    enderecoCadastrado.rua(),
                    enderecoCadastrado.numero(),
                    enderecoCadastrado.complemento(),
                    enderecoCadastrado.bairro(),
                    enderecoCadastrado.cidade(),
                    enderecoCadastrado.estado(),
                    enderecoCadastrado.cep()
            );

            kafkaTemplate.send(
                    enderecoCadastradoTopic,
                    enderecoCadastrado.enderecoId().toString(),
                    mensagem
            );
        }
    }
}