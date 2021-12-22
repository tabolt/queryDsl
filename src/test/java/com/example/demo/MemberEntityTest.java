package com.example.demo;

import com.example.demo.code.GenderCode;
import com.example.demo.code.StatusCode;
import com.example.demo.dto.MemberCondition;
import com.example.demo.entity.AddressEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MemberEntityTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void select() {

        this.insertTestMembers();

        MemberCondition param = new MemberCondition();
        param.setGender(GenderCode.MALE);

        Pageable pageable = PageRequest.of(1, 20, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("age"),
                Sort.Order.asc("gender"),
                Sort.Order.desc("address_address"),
                Sort.Order.asc("address_name"),
                Sort.Order.desc("address_zipCode")
        ));

        this.memberRepository.selectMember(param, pageable).forEach(System.out::println);
    }

    @Test
    public void insert() {

        MemberEntity member = this.insertTest();

        this.memberRepository.findById(member.getNo()).ifPresent(System.out::println);
    }

    @Transactional
    MemberEntity insertTest() {

        MemberEntity member = MemberEntity.builder()
                .name("이름")
                .gender(GenderCode.MALE)
                .status(StatusCode.ACTIVE)
                .age(30)
                .build();

        AddressEntity address1 = AddressEntity.builder().name("주소1").zipCode("111111").address("주소1 상세").build();
        AddressEntity address2 = AddressEntity.builder().name("주소2").zipCode("222222").address("주소2 상세").build();
        AddressEntity address3 = AddressEntity.builder().name("주소3").zipCode("333333").address("주소3 상세").build();

        member.addAddress(address1);
        member.addAddress(address2);
        member.addAddress(address3);

        member = this.memberRepository.save(member);

        member.getAddress().clear();

        member = this.memberRepository.save(member);

        AddressEntity address4 = AddressEntity.builder().name("주소4").zipCode("444444").address("주소4 상세").build();
        AddressEntity address5 = AddressEntity.builder().name("주소5").zipCode("555555").address("주소5 상세").build();
        AddressEntity address6 = AddressEntity.builder().name("주소6").zipCode("666666").address("주소6 상세").build();

        member.addAddress(address4);
        member.addAddress(address5);
        member.addAddress(address6);

        member = this.memberRepository.save(member);

        System.out.println(member);

        return member;
    }

    private void insertTestMembers() {

        List<MemberEntity> list = new ArrayList<>();

        list.add(MemberEntity.builder().name("테스트0").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(1).build());
        list.add(MemberEntity.builder().name("테스트1").gender(GenderCode.FEMALE).status(StatusCode.DORMANT).age(2).build());
        list.add(MemberEntity.builder().name("테스트2").gender(GenderCode.ETC).status(StatusCode.ACTIVE).age(3).build());
        list.add(MemberEntity.builder().name("테스트3").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(4).build());
        list.add(MemberEntity.builder().name("테스트4").gender(GenderCode.FEMALE).status(StatusCode.ACTIVE).age(5).build());
        list.add(MemberEntity.builder().name("테스트5").gender(GenderCode.ETC).status(StatusCode.ACTIVE).age(6).build());
        list.add(MemberEntity.builder().name("테스트6").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(7).build());
        list.add(MemberEntity.builder().name("테스트7").gender(GenderCode.FEMALE).status(StatusCode.ACTIVE).age(8).build());
        list.add(MemberEntity.builder().name("테스트8").gender(GenderCode.ETC).status(StatusCode.DORMANT).age(9).build());
        list.add(MemberEntity.builder().name("테스트9").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(10).build());
        list.add(MemberEntity.builder().name("테스트10").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(11).build());
        list.add(MemberEntity.builder().name("테스트11").gender(GenderCode.FEMALE).status(StatusCode.WITHDRAW).age(12).build());
        list.add(MemberEntity.builder().name("테스트12").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(13).build());
        list.add(MemberEntity.builder().name("테스트13").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(14).build());
        list.add(MemberEntity.builder().name("테스트14").gender(GenderCode.FEMALE).status(StatusCode.ACTIVE).age(15).build());
        list.add(MemberEntity.builder().name("테스트15").gender(GenderCode.ETC).status(StatusCode.ACTIVE).age(16).build());
        list.add(MemberEntity.builder().name("테스트16").gender(GenderCode.MALE).status(StatusCode.DORMANT).age(17).build());
        list.add(MemberEntity.builder().name("테스트17").gender(GenderCode.FEMALE).status(StatusCode.ACTIVE).age(18).build());
        list.add(MemberEntity.builder().name("테스트18").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(19).build());
        list.add(MemberEntity.builder().name("테스트19").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(20).build());
        list.add(MemberEntity.builder().name("테스트20").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(21).build());
        list.add(MemberEntity.builder().name("테스트21").gender(GenderCode.ETC).status(StatusCode.ACTIVE).age(22).build());
        list.add(MemberEntity.builder().name("테스트22").gender(GenderCode.FEMALE).status(StatusCode.ACTIVE).age(23).build());
        list.add(MemberEntity.builder().name("테스트23").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(24).build());
        list.add(MemberEntity.builder().name("테스트24").gender(GenderCode.MALE).status(StatusCode.DORMANT).age(25).build());
        list.add(MemberEntity.builder().name("테스트25").gender(GenderCode.FEMALE).status(StatusCode.ACTIVE).age(26).build());
        list.add(MemberEntity.builder().name("테스트26").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(27).build());
        list.add(MemberEntity.builder().name("테스트27").gender(GenderCode.ETC).status(StatusCode.DORMANT).age(28).build());
        list.add(MemberEntity.builder().name("테스트28").gender(GenderCode.FEMALE).status(StatusCode.ACTIVE).age(29).build());
        list.add(MemberEntity.builder().name("테스트29").gender(GenderCode.MALE).status(StatusCode.WITHDRAW).age(30).build());
        list.add(MemberEntity.builder().name("테스트30").gender(GenderCode.ETC).status(StatusCode.DORMANT).age(31).build());
        list.add(MemberEntity.builder().name("테스트31").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(32).build());
        list.add(MemberEntity.builder().name("테스트32").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(33).build());
        list.add(MemberEntity.builder().name("테스트33").gender(GenderCode.FEMALE).status(StatusCode.WITHDRAW).age(34).build());
        list.add(MemberEntity.builder().name("테스트34").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(35).build());
        list.add(MemberEntity.builder().name("테스트35").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(36).build());
        list.add(MemberEntity.builder().name("테스트36").gender(GenderCode.FEMALE).status(StatusCode.ACTIVE).age(37).build());
        list.add(MemberEntity.builder().name("테스트37").gender(GenderCode.ETC).status(StatusCode.ACTIVE).age(38).build());
        list.add(MemberEntity.builder().name("테스트38").gender(GenderCode.MALE).status(StatusCode.DORMANT).age(39).build());
        list.add(MemberEntity.builder().name("테스트39").gender(GenderCode.FEMALE).status(StatusCode.ACTIVE).age(40).build());
        list.add(MemberEntity.builder().name("테스트40").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(41).build());
        list.add(MemberEntity.builder().name("테스트41").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(42).build());
        list.add(MemberEntity.builder().name("테스트42").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(43).build());
        list.add(MemberEntity.builder().name("테스트43").gender(GenderCode.ETC).status(StatusCode.ACTIVE).age(44).build());
        list.add(MemberEntity.builder().name("테스트44").gender(GenderCode.FEMALE).status(StatusCode.ACTIVE).age(45).build());
        list.add(MemberEntity.builder().name("테스트45").gender(GenderCode.MALE).status(StatusCode.ACTIVE).age(46).build());
        list.add(MemberEntity.builder().name("테스트46").gender(GenderCode.MALE).status(StatusCode.DORMANT).age(47).build());

        this.memberRepository.saveAll(list);
    }
}
