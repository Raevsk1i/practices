package org.master.consumer.repositories;

import org.master.consumer.entity.KafkaMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<KafkaMessageEntity, Long> {
}
