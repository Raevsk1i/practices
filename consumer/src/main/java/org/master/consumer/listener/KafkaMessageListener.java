package org.master.consumer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.master.consumer.entity.KafkaMessageEntity;
import org.master.consumer.model.KafkaMessage;
import org.master.consumer.service.KafkaMessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageListener {

    private final KafkaMessageService kafkaMessageService;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "message-topic",
            groupId = "${kafka.topic.group.id}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory"
    )
    public void listen(@Payload KafkaMessage message,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        log.info("received message: {}", message);
        log.info("key: {}, partition: {}, topic: {}, timestamp: {}", key, partition, topic, timestamp);

        KafkaMessageEntity messageEntity = objectMapper.convertValue(message, KafkaMessageEntity.class);
        messageEntity.setTimeStamp(timestamp);

        kafkaMessageService.add(messageEntity);
    }
}
