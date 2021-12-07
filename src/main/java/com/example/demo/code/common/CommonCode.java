package com.example.demo.code.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public interface CommonCode {

    String getGroupCode();

    String getGroupName();

    @JsonValue
    String getCode();

    String getName();

    Enum<? extends CommonCode>[] getValues();

    @JsonCreator
    default Enum<? extends CommonCode> from(String code) {

        return Arrays.stream(this.getValues())
                .filter(item -> ((CommonCode) item).getCode().equals(code))
                .findAny()
                .orElse(null);
    }
}
