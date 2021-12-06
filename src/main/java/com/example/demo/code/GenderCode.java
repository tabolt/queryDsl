package com.example.demo.code;

import com.example.demo.code.common.CommonCode;
import com.example.demo.code.common.CommonCodeConverter;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Converter;

@Getter
@ToString
public enum GenderCode implements CommonCode {

    MALE("001", "남성"),
    FEMALE("002", "여성"),
    ETC("003", "기타");

    private final String groupCode = "M02";
    private final String groupName = "성별";

    private final String code;
    private final String name;

    GenderCode(String subCode, String name) {
        this.code = this.groupCode + subCode;
        this.name = name;
    }

    @Converter(autoApply = true)
    public static class CustomConverter extends CommonCodeConverter<GenderCode> {
        CustomConverter() {
            super(GenderCode.class);
        }
    }
}
