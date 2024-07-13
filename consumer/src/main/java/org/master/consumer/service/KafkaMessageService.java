package org.master.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.master.consumer.entity.KafkaMessageEntity;
import org.master.consumer.model.KafkaMessage;
import org.master.consumer.repositories.MessageRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaMessageService {

    private final MessageRepository messages;
    private final ObjectMapper objectMapper;

    public void add(KafkaMessageEntity message) {
        messages.save(message);
    }

    public KafkaMessage getById(Long id) {
        return objectMapper.convertValue(messages.getReferenceById(id), KafkaMessage.class);
    }
}
