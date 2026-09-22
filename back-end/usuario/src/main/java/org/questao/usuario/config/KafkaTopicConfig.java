package org.questao.usuario.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic enderecoCadastradoTopic() {
        return TopicBuilder
                .name("endereco-cadastrado")
                .partitions(2)
                .replicas(2)
                .build();
    }
}
