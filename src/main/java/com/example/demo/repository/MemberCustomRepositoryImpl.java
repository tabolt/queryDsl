package com.example.demo.repository;

import com.example.demo.dto.MemberCondition;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.QAddressDto;
import com.example.demo.dto.QMemberDto;
import com.example.demo.entity.QAddressEntity;
import com.example.demo.entity.QMemberEntity;
import com.querydsl.core.ResultTransformer;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.support.PageableSupport.*;
import static com.example.demo.support.QueryDslSupport.*;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory jpaQuery;

    private final QMemberEntity qMember = QMemberEntity.memberEntity;

    private final QAddressEntity qAddress = QAddressEntity.addressEntity;

    private final ResultTransformer<List<MemberDto>> transformer = groupBy(qMember.no)
            .list(
                    new QMemberDto(
                            qMember.no,
                            qMember.name,
                            qMember.gender,
                            qMember.status,
                            qMember.age,
                            list(
                                    new QAddressDto(
                                            qAddress.no,
                                            qAddress.name,
                                            qAddress.zipCode,
                                            qAddress.address
                                    )
                            )
                    )
            );

    /**
     * 회원 조회
     */
    @Override
    public List<MemberDto> selectMember(MemberCondition condition) {
        return this.selectQuery(condition, null).transform(this.transformer);
    }

    /**
     * 회원 조회 - 페이징 처리
     */
    @Override
    public Page<MemberDto> selectMember(MemberCondition condition, Pageable pageable) {

        condition.setNoList(this.selectPagingNoList(condition, pageable));

        List<MemberDto> list = this.selectQuery(condition, pageable).transform(this.transformer);

        return new PageImpl<>(list, pageable, this.count(condition));
    }

    /**
     * 조회 쿼리
     */
    private JPAQuery<?> selectQuery(MemberCondition condition, Pageable pageable) {

        JPAQuery<?> query = this.basicQuery(condition)
                .leftJoin(qAddress).on(
                        eq(qAddress.member.no, qMember.no)
                )
                .where(
                        in(qMember.no, condition.getNoList())
                );

        applyOrderBy(query, pageable).orderBy(
                OrderBy.path("name", qMember.name),
                OrderBy.path("age", qMember.age),
                OrderBy.path("gender", qMember.gender)
        );

        return query;
    }

    /**
     * 페이징 대상 목록 조회
     */
    private List<Long> selectPagingNoList(MemberCondition condition, Pageable pageable) {

        JPAQuery<Long> query = this.basicQuery(condition)
                .select(
                        qMember.no
                );

        applyPageable(query, pageable).orderBy(
                OrderBy.path("name", qMember.name),
                OrderBy.path("age", qMember.age),
                OrderBy.path("gender", qMember.gender)
        );

        return query.fetch();
    }

    /**
     * 카운트
     */
    private long count(MemberCondition condition) {

        return this.basicQuery(condition)
                .select(
                        qMember.no.count()
                )
                .fetchOne();
    }

    /**
     * 기본 쿼리
     */
    private JPAQuery<?> basicQuery(MemberCondition condition) {

        return jpaQuery
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
                );
    }
}
