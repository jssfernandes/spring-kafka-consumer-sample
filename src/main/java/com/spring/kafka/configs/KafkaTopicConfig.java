package com.spring.kafka.configs;

import java.util.HashMap;
import java.util.Map;
import com.spring.configs.ApplicationProperties;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;


@Configuration
public class KafkaTopicConfig {
    @Bean
    public KafkaAdmin kafkaAdmin(ApplicationProperties props) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapAddress());
        return new KafkaAdmin(configs);
    }
}