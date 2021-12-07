package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long no;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String address;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private MemberEntity member;

    @Builder
    public AddressEntity(String name, String zipCode, String address) {
        this.name = name;
        this.zipCode = zipCode;
        this.address = address;
    }

    public void applyMember(MemberEntity member) {
        this.member = member;
    }

}
