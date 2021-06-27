package com.example.journalconsumer.app;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchInfo {
    private int limit;
    private int offset;
}
