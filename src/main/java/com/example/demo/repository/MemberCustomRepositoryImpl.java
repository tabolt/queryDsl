package com.example.demo.repository;

import com.example.demo.code.StatusCode;
import com.example.demo.entity.Member;
import com.example.demo.entity.QMember;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory jpaQuery;

    @Override
    public JPAQuery<Member> selectAllActiveMember() {

        QMember qMember = QMember.member;

        return jpaQuery
                .select(
                        qMember
                )
                .from(
                        qMember
                )
                .where(
                        qMember.status.eq(StatusCode.ACTIVE)
                );
    }

}
