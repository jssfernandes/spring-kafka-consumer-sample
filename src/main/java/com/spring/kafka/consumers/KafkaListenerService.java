package com.spring.kafka.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KafkaListenerService {

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group-id}")
    public void consumerMessage(String msg, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        System.out.println("Consumindo o Tópico");
        System.out.println(String.format("Mensagem: %s - %s - %s - %s", msg, partitions, topics, offsets));
    }
}
