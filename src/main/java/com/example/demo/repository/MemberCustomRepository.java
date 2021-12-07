package com.example.demo.repository;

import com.example.demo.dto.MemberCondition;
import com.example.demo.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberCustomRepository {

    List<MemberEntity> selectMember(MemberCondition param);

    Page<MemberEntity> selectMember(MemberCondition param, Pageable pageable);
}
