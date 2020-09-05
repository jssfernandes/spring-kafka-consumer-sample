package com.spring.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationProperties {
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.topic.name}")
    private String kafkaTopic;

    @Value(value = "${kafka.group-id}")
    private String groupId;

    @Value("${kafka.security.password}")
    private String senhaKafka;

//    @Value("${kafka.security.protocol}")
    private String protocol;

//    @Value("${kafka.security.mechanism}")
    private String mechanism;

    @Value("${kafka.security.user}")
    private String securityUser;

    @Value("${kafka.value-deserializer}")
    private String deserializer;

    @Value("${env.variables.pass}")
    private String passwordDecrypt;

    @Value("${env.variables.algorithm}")
    private String algorithmDecrypt;

    public String getBootstrapAddress() {
        return bootstrapAddress;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getSenhaKafka() {
        return senhaKafka;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getMechanism() {
        return mechanism;
    }

    public String getSecurityUser() {
        return securityUser;
    }

    public String getDeserializer() {
        return deserializer;
    }

    public String getPasswordDecrypt() {
        return passwordDecrypt;
    }

    public String getAlgorithmDecrypt() {
        return algorithmDecrypt;
    }
}
