package com.management.UserService.service;

import com.management.UserService.model.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventProducer {

    @Autowired
    private KafkaTemplate<String, UserEvent> kafkaTemplate;

    private static final String TOPIC = "user-events";


    public void sendUserEvent(UserEvent userEvent) {
        kafkaTemplate.send(TOPIC, userEvent);
    }

    public void sendKafkaProducer(String action, String role, String id) {
        UserEvent userEvent = new UserEvent();
        userEvent.setUserId(id);
        userEvent.setAction(action);
        userEvent.setRole(role);
        sendUserEvent(userEvent);
    }
}