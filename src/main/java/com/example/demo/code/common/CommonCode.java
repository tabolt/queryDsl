package com.example.demo.code.common;

import com.fasterxml.jackson.annotation.JsonValue;

public interface CommonCode {

    String getGroupName();

    @JsonValue
    String getCode();

    String getName();
}
