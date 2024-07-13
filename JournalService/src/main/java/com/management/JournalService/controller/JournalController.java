package com.management.JournalService.controller;

import com.management.JournalService.entity.Journal;
import com.management.JournalService.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    JournalService journalService;

    @GetMapping("/getAllJournals")
    public ResponseEntity<List<Journal>> getAllJournals() {
        List<Journal> journals = journalService.getAllJournals();
        return ResponseEntity.ok(journals);
    }

}