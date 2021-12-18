package com.example.demo.code.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonCodeDto {

    private String code;
    private String name;

    public static CommonCodeDto from(CommonCode code) {
        return new CommonCodeDto(code.getCode(), code.getName());
    }
}
