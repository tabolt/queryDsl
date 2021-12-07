package com.example.demo.support;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionSupport {

    /**
     * 목록에서 중복된 아이템 반환
     */
    public static <R, T> List<T> getDuplicatedElement(List<R> list, Function<R, T> mapper) {

        List<T> targetList = list.stream()
                .map(mapper)
                .collect(Collectors.toList());

        return targetList.stream()
                .filter(item -> Collections.frequency(targetList, item) > 1)
                .distinct()
                .collect(Collectors.toList());
    }
}
