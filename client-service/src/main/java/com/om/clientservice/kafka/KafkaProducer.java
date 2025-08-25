package com.om.clientservice.kafka;

import client.events.ClientEvent;
import com.om.clientservice.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Client client) {
        ClientEvent event = ClientEvent.newBuilder()
                .setClientId(client.getId().toString())
                .setName(client.getName())
                .setEmail(client.getEmail())
                .setEventType("CLIENT_CREATED")
                .build();

        // Try to send event to client topic
        try {
            kafkaTemplate.send("client", event.toByteArray());
        } catch (Exception e) {
            log.error("Error sending ClientCreated event: {}", event);
        }
    }
}
