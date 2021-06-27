package com.example.testcase.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SearchInfo {
    private int limit;
    private int offset;
}
