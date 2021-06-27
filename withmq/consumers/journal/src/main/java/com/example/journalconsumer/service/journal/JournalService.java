package com.example.journalconsumer.service.journal;

import com.example.journalconsumer.app.PagedData;

public interface JournalService {
    PagedData<JournalDto> all(int limit, int offset);
}
