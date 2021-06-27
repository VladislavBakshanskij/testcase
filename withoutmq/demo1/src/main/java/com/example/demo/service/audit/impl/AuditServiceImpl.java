package com.example.demo.service.audit.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.Journal;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserActionType;
import com.example.demo.repository.JournalRepository;
import com.example.demo.service.audit.AuditService;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Transactional
public class AuditServiceImpl implements AuditService {
    private final ObjectMapper objectMapper;
    private final JournalRepository journalRepository;

    @PostConstruct
    public void inti() {
    }

    @Override
    public void save(User user, UserActionType userActionType) {
        Journal journal = new Journal()
                .setLogin(user.getName())
                .setUserActionType(userActionType)
                .setCurrentState(objectMapper.convertValue(user, new TypeReference<>() {}));
        journalRepository.save(journal);
    }
}
