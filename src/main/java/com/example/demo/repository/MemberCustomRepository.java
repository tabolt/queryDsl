package com.example.demo.repository;

import com.example.demo.entity.Member;
import com.querydsl.jpa.impl.JPAQuery;

public interface MemberCustomRepository {

    JPAQuery<Member> selectAllActiveMember();
}
