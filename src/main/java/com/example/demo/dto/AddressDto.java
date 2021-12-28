package com.example.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddressDto {

    private Long no;
    private String name;
    private String zipCode;
    private String address;

    @QueryProjection
    public AddressDto(Long no, String name, String zipCode, String address) {
        this.no = no;
        this.name = name;
        this.zipCode = zipCode;
        this.address = address;
    }
}
