package com.example.demo;

import com.example.demo.code.GenderCode;
import com.example.demo.code.StatusCode;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void memberTest() {

        Member member = Member.builder()
                .name("김혁")
                .gender(GenderCode.MALE)
                .status(StatusCode.ACTIVE)
                .build();

        this.memberRepository.save(member);

        this.memberRepository.selectAllActiveMember().fetch().forEach(System.out::println);
    }
}
