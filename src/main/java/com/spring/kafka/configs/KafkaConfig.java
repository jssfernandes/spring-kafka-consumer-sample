package com.spring.kafka.configs;

import com.spring.configs.ApplicationProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfig {
    @Autowired
    ApplicationProperties props;

    private static final String SECURITYPROTOCOL = "security.protocol";
    private static final String SASLMECHANISM = "sasl.mechanism";
    private static final String JASCONFIG = "sasl.jaas.config";
    private static final String SECURITYUSERPASS = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";

    public String getPassKafka() {
        //Para usar a senha com crypto descomentar a linha a 30 e excluir a linha 31
//        return new Decryptor().decrypt(props.getSenhaKafka(), System.getenv(props.getPasswordDecrypt()), System.getenv(props.getAlgorithmDecrypt()));
        return props.getSenhaKafka();
    }

    @Bean
    public Map<String, Object> consumerConfig() {
        Map<String, Object> propsi = new HashMap<>();
        propsi.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapAddress());
        propsi.put(ConsumerConfig.GROUP_ID_CONFIG, props.getGroupId());
        propsi.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsi.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        propsi.put("value.deserializer", props.getDeserializer());

        if (props.getProtocol() != null) {
            String jaasTemplate = SECURITYUSERPASS;
            String jaasCfg = String.format(jaasTemplate, props.getSecurityUser(), getPassKafka());
            propsi.put(SECURITYPROTOCOL, props.getProtocol());
            propsi.put(SASLMECHANISM, props.getMechanism());
            propsi.put(JASCONFIG, jaasCfg);
        }
        return propsi;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
