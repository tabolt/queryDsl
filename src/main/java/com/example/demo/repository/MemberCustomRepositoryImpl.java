package com.example.demo.repository;

import com.example.demo.dto.MemberCondition;
import com.example.demo.entity.MemberEntity;
import com.example.demo.entity.QAddressEntity;
import com.example.demo.entity.QMemberEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.support.PageableSupport.OrderBy;
import static com.example.demo.support.PageableSupport.applyPageable;
import static com.example.demo.support.QueryDslSupport.containsIf;
import static com.example.demo.support.QueryDslSupport.eqIf;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory jpaQuery;

    /**
     * 회원 조회
     */
    @Override
    public List<MemberEntity> selectMember(MemberCondition condition) {
        return this.basicQuery(condition, null).fetch();
    }

    /**
     * 회원 조회 - 페이징 처리
     */
    @Override
    public Page<MemberEntity> selectMember(MemberCondition condition, Pageable pageable) {
        return new PageImpl<>(this.basicQuery(condition, pageable).fetch(), pageable, this.basicQueryCount(condition));
    }

    private long basicQueryCount(MemberCondition condition) {

        QMemberEntity qMember = QMemberEntity.memberEntity;
        QAddressEntity qAddress = QAddressEntity.addressEntity;

        return jpaQuery
                .select(
                        qMember.no.count()
                )
                .from(
                        qMember
                )
                .where(
                        eqIf(qMember.no, condition.getNo()),
                        eqIf(qMember.name, condition.getName()),
                        containsIf(qMember.name, condition.getContainsName()),
                        eqIf(qMember.gender, condition.getGender()),
                        eqIf(qMember.age, condition.getAge()),
                        eqIf(qMember.status, condition.getStatus())
                )
                .fetchOne();
    }

    /**
     * 기본 회원 쿼리
     */
    private JPAQuery<MemberEntity> basicQuery(MemberCondition condition, @Nullable Pageable pageable) {

        QMemberEntity qMember = QMemberEntity.memberEntity;
        QAddressEntity qAddress = QAddressEntity.addressEntity;

        JPAQuery<MemberEntity> query = jpaQuery
                .select(
                        qMember
                )
                .from(qMember)
                .leftJoin(qMember.address, qAddress).fetchJoin()
                .where(
                        eqIf(qMember.no, condition.getNo()),
                        eqIf(qMember.name, condition.getName()),
                        containsIf(qMember.name, condition.getContainsName()),
                        eqIf(qMember.gender, condition.getGender()),
                        eqIf(qMember.age, condition.getAge()),
                        eqIf(qMember.status, condition.getStatus())
                );

        applyPageable(query, pageable).orderBy(
                OrderBy.path("name", qMember.name),
                OrderBy.path("age", qMember.age),
                OrderBy.path("gender", qMember.gender),
                OrderBy.path("address_name", qAddress.name),
                OrderBy.path("address_address", qAddress.address),
                OrderBy.path("address_zipCode", qAddress.zipCode)
        );

        return query;
    }
}
