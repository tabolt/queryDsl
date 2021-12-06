package com.example.demo.code;

import com.example.demo.code.common.CommonCode;
import com.example.demo.code.common.CommonCodeConverter;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Converter;

@Getter
@ToString
public enum StatusCode implements CommonCode {

    ACTIVE("001", "활동회원"),
    DORMANT("002", "휴면회원"),
    WITHDRAW("003", "탈퇴회원");

    public static final String GROUP_CODE = "M01";
    public static final String GROUP_NAME = "회원상태";

    private final String code;
    private final String name;

    StatusCode(String subCode, String name) {
        this.code = GROUP_CODE + subCode;
        this.name = name;
    }

    @Override
    public String getGroupCode() {
        return GROUP_CODE;
    }

    @Override
    public String getGroupName() {
        return GROUP_NAME;
    }

    @Converter(autoApply = true)
    public static class CustomConverter extends CommonCodeConverter<StatusCode> {
        CustomConverter() {
            super(StatusCode.class);
        }
    }
}
