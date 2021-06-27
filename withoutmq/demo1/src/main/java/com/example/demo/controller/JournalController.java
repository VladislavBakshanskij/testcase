package com.example.demo.controller;

import com.example.demo.app.PagedData;
import com.example.demo.service.journal.JournalDto;
import com.example.demo.service.journal.JournalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journals")
@Api("${journals}")
@RequiredArgsConstructor
public class JournalController {
    private final JournalService journalService;

    @ApiOperation("${journals.all}")
    @ApiResponse(code = 200, message = "Успешно", response = PagedData.class)
    @GetMapping
    public PagedData<JournalDto> all(@RequestParam(name = "limit", defaultValue = "100") int limit,
                                     @RequestParam(name = "offset", defaultValue = "0") int offset) {
        return journalService.all(limit, offset);
    }
}
