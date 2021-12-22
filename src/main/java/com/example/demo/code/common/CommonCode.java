package com.example.demo.code.common;

import com.fasterxml.jackson.annotation.JsonValue;

public interface CommonCode {

    @JsonValue
    String getCode();

    String getName();
}
