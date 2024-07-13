package org.master.consumer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.master.consumer.model.KafkaMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.topic.group.id}")
    private String kafkaMessageGroupId;

    @Bean
    public ConsumerFactory<String, KafkaMessage> kafkaMessageConsumerFactory(ObjectMapper objectMapper) {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaMessageGroupId);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(objectMapper));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> kafkaMessageConcurrentKafkaListenerContainerFactory(
            ConsumerFactory<String, KafkaMessage> kafkaMessageConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaMessageConsumerFactory);

        return factory;
    }
}
