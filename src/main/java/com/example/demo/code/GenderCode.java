package com.example.demo.code;

import com.example.demo.code.common.CommonCode;
import com.example.demo.code.common.CommonCodeConverter;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Converter;

@Getter
@ToString
public enum GenderCode implements CommonCode {

    MALE("M02001", "남성"),
    FEMALE("M02002", "여성"),
    ETC("M02003", "기타");

    private final String code;
    private final String name;

    GenderCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Converter(autoApply = true)
    public static class CodeConverter extends CommonCodeConverter<GenderCode> {
        CodeConverter() {
            super(GenderCode.class);
        }
    }
}
