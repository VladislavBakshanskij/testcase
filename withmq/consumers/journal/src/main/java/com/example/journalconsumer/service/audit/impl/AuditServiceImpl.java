package com.example.journalconsumer.service.audit.impl;

import com.example.journalconsumer.service.journal.JournalCreateDto;
import com.example.journalconsumer.model.Journal;
import com.example.journalconsumer.repository.JournalRepository;
import com.example.journalconsumer.service.audit.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Transactional
public class AuditServiceImpl implements AuditService {
    private final JournalRepository journalRepository;

    @PostConstruct
    public void inti() {
    }

    @Override
    public void save(JournalCreateDto dto) {
        Journal journal = new Journal()
                .setLogin(dto.getLogin())
                .setUserActionType(dto.getAction())
                .setCurrentState(dto.getCurrentState());
        journalRepository.save(journal);
    }
}
