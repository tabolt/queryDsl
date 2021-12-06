package com.example.demo.entity;

import com.example.demo.code.GenderCode;
import com.example.demo.code.StatusCode;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long no;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private GenderCode gender;

    @Column(nullable = false)
    private StatusCode status;

}
