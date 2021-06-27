package com.example.testcase.app;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PagedData<T> {
    private List<T> data;
    private Meta meta;

    public PagedData(Meta meta, List<T> data) {
        this.data = data;
        this.meta = meta;
    }

    public PagedData(int limit, int offset, List<T> data) {
        this.data = data;
        this.meta = new Meta(limit, offset);
    }

    public <K> PagedData<K> map(Function<T, K> mapper) {
        return new PagedData<>(meta, data.stream()
                .map(mapper)
                .collect(Collectors.toList()));
    }
}
