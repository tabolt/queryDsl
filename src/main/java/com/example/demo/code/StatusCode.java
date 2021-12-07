package com.example.demo.code;

import com.example.demo.code.common.CommonCode;
import com.example.demo.code.common.CommonCodeConverter;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;

@Getter
@ToString
public enum StatusCode implements CommonCode {

    ACTIVE("001", "활동회원"),
    DORMANT("002", "휴면회원"),
    WITHDRAW("003", "탈퇴회원");

    private final String groupCode = "M01";
    private final String groupName = "회원상태";

    private final String code;
    private final String name;

    StatusCode(String subCode, String name) {
        this.code = groupCode + subCode;
        this.name = name;
    }

    @Override
    public Enum<? extends CommonCode>[] getValues() {
        return values();
    }

    @Component
    @Converter(autoApply = true)
    public static class CodeConverter extends CommonCodeConverter<StatusCode> {
        CodeConverter() {
            super(StatusCode.class);
        }
    }
}
