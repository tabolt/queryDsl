package com.example.demo.repository;

import com.example.demo.dto.MemberCondition;
import com.example.demo.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberCustomRepository {

    List<MemberDto> selectMember(MemberCondition param);

    Page<MemberDto> selectMember(MemberCondition param, Pageable pageable);
}
