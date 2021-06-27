package com.example.demo.service.journal;

import com.example.demo.app.PagedData;

public interface JournalService {
    PagedData<JournalDto> all(int limit, int offset);
}
