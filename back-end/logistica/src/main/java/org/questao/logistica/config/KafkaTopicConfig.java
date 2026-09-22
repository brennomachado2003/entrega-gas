package org.questao.logistica.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic enderecoLocalizadoTopic() {
        return TopicBuilder
                .name("endereco-localizado")
                .partitions(2)
                .replicas(2)
                .build();
    }

    @Bean
    public NewTopic listaEntregadoresTopic() {
        return TopicBuilder
                .name("lista-entregadores")
                .partitions(1)
                .replicas(2)
                .build();
    }
}
