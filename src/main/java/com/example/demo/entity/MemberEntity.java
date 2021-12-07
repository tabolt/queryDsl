package com.example.demo.entity;

import com.example.demo.code.GenderCode;
import com.example.demo.code.StatusCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@Table(name = "MEMBER")
@Entity
public class MemberEntity {

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

    @Column(nullable = false)
    private int age;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AddressEntity> address = new ArrayList<>();

    @Builder
    public MemberEntity(Long no, String name, GenderCode gender, StatusCode status, int age) {
        this.no = no;
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.age = age;
    }

    /**
     * 주소 추가
     */
    public void addAddress(AddressEntity address) {
        if (address != null) {
            address.applyMember(this);
            this.address.add(address);
        }
    }
}
