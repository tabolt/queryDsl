package com.example.demo.code.common;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class CommonCodeSupport {

    /**
     * 공통코드 목록 생성
     */
    public static <E extends Enum<E> & CommonCode> List<CommonCodeDto.Code> getCodeList(Class<E> enumClass) {

        return EnumSet.allOf(enumClass)
                .stream()
                .map(CommonCodeDto.Code::from)
                .collect(Collectors.toList());
    }
}
