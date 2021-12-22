package com.example.demo.dto;

import com.example.demo.code.GenderCode;
import com.example.demo.code.StatusCode;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberCondition {

    private Long no;
    private String name;
    private String containsName;
    private StatusCode status;
    private GenderCode gender;
    private Integer age;
}
