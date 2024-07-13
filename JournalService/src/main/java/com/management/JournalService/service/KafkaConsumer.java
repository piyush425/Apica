package com.management.JournalService.service;

import com.management.JournalService.entity.Journal;
import com.management.JournalService.model.UserEvent;
import com.management.JournalService.repository.JournalRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class KafkaConsumer {

    private final JournalRepository journalRepository;

    public KafkaConsumer(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @KafkaListener(topics = "user-events", groupId = "journal-service")
    public void listen(UserEvent userEvent) {
        Journal journal = new Journal();
        journal.setUserId(userEvent.getUserId());
        journal.setAction(userEvent.getAction());
        journal.setRole(userEvent.getRole());
        journal.setTimestamp(LocalDateTime.now());
        journalRepository.save(journal);
    }
}