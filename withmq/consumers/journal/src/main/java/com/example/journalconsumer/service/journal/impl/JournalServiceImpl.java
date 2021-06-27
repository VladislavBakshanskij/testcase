package com.example.journalconsumer.service.journal.impl;

import com.example.journalconsumer.app.PagedData;
import com.example.journalconsumer.repository.JournalRepository;
import com.example.journalconsumer.service.journal.JournalDto;
import com.example.journalconsumer.service.journal.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;

    @Override
    public PagedData<JournalDto> all(int limit, int offset) {
        return new PagedData<>(limit, offset, journalRepository.findAll(limit, offset))
                .map(JournalDto::new);
    }
}
