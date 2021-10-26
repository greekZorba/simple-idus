package com.homework.simpleidus.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class PageResponse<T> implements Serializable {
    private List<T> contests;
    private int totalPage;
    private long totalCount;

    public static <T, R> PageResponse<R> of(Page<T> page, Function<T, R> convertFunction) {
        return PageResponse.<R>builder()
                .contests(page.getContent().stream().map(convertFunction).collect(Collectors.toList()))
                .totalPage(page.getTotalPages())
                .totalCount(page.getTotalElements())
                .build();
    }
}