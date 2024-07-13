package org.master.consumer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.master.consumer.model.KafkaMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
@Slf4j
public class KafkaController {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @PostMapping("/send")
    public ResponseEntity<Object> sendMessage(@RequestBody KafkaMessage message) {
        try {
            kafkaTemplate.send(topicName, message);
            log.info("Message sent to topic {}", topicName);
            return ResponseEntity.ok().body("Message sent to topic " + topicName);
        } catch (Exception e) {
            log.error("error while sending message to topic {}", topicName, e);
            return ResponseEntity.badRequest().body("Error while sending message to topic " + topicName);
        }
    }
}
