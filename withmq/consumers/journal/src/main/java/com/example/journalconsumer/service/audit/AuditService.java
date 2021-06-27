package com.example.journalconsumer.service.audit;

import com.example.journalconsumer.service.journal.JournalCreateDto;

public interface AuditService {
    void save(JournalCreateDto dto);
}
