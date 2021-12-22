package com.example.demo.code;

import com.example.demo.code.common.CommonCode;
import com.example.demo.code.common.CommonCodeConverter;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Converter;

@Getter
@ToString
public enum StatusCode implements CommonCode {

    ACTIVE("M01001", "활동회원"),
    DORMANT("M01002", "휴면회원"),
    WITHDRAW("M01003", "탈퇴회원");

    private final String code;
    private final String name;

    StatusCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Converter(autoApply = true)
    public static class CodeConverter extends CommonCodeConverter<StatusCode> {
        CodeConverter() {
            super(StatusCode.class);
        }
    }
}
