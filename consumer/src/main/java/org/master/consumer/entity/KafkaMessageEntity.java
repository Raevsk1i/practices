package org.master.consumer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "messages")
@Entity
public class KafkaMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "message")
    private String message;

    @Column(name = "timeStamp")
    private Long timeStamp;
}
