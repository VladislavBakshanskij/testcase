package com.example.demo.app;

import lombok.Value;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Value
public class PagedData<T> {
    List<T> data;
    Meta meta;

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
