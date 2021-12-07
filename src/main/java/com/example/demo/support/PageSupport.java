package com.example.demo.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.function.Function;
import java.util.stream.Collectors;

public class PageSupport {

    /**
     * 페이지 변환
     */
    public static <T, R> Page<R> map(Page<T> target, Function<T, R> mapper) {
        return new PageImpl<>(target.getContent().stream().map(mapper).collect(Collectors.toList()), target.getPageable(), target.getTotalElements());
    }
}
