package com.learning.debezium.demo;

import com.learning.debezium.demo.domain.DebeziumEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DebeziumEventConsumer {

    @KafkaListener(topics = "pg-changes.public.person")
    public void listener(DebeziumEventDto eventDto) {
        try {
            final var payload = eventDto.getPayload();
            final var source = payload.getSource();
            log.info("{} event on {} table", payload.getOperationType(), source.getTable());
        } catch (Exception e) {
            log.error("Failed to processing consumed message {}", eventDto, e);
        }
    }
}
