package com.example.demo.service;

import com.example.demo.dto.MemberCondition;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import com.example.demo.support.PageableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepo;

    public void selectMemberList() {

        MemberCondition condition = new MemberCondition();

        Pageable pageable = PageRequest.of(20, 1);

        Page<MemberEntity> page = this.memberRepo.selectMember(condition, pageable);

        Page<MemberEntity> map = PageableSupport.mappingTo(page, memberEntity -> memberEntity);

    }
}
