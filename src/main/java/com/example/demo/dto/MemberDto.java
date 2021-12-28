package com.example.demo.dto;

import com.example.demo.code.GenderCode;
import com.example.demo.code.StatusCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDto {

    private Long no;
    private String name;
    private GenderCode gender;
    private StatusCode status;
    private Integer age;
    private List<AddressDto> address = new ArrayList<>();

    @QueryProjection
    public MemberDto(Long no, String name, GenderCode gender, StatusCode status, Integer age) {
        this.no = no;
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.age = age;
    }

    @QueryProjection
    public MemberDto(Long no, String name, GenderCode gender, StatusCode status, Integer age, List<AddressDto> address) {
        this.no = no;
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.age = age;
        this.address = address;
    }
}
