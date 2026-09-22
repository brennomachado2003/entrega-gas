package org.questao.pedidos.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic entregadorCadastradoTopic() {
        return TopicBuilder
                .name("pedido-criado")
                .partitions(2)
                .replicas(2)
                .build();
    }
}
