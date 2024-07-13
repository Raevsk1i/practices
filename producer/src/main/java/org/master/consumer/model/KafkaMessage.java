package org.master.consumer.model;

import lombok.Data;

@Data
public class KafkaMessage {
    private String sender;
    private String receiver;
    private String message;
}
