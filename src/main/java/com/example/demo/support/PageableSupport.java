package com.example.demo.support;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.function.Function;
import java.util.stream.Collectors;

public class PageableSupport {

    /**
     * 페이지 생성
     */
    @SuppressWarnings("deprecation")
    public static <T> Page<T> getPage(JPAQuery<T> query, Pageable pageable) {
        QueryResults<T> results = query.fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    /**
     * 페이지 변환
     */
    public static <T, R> Page<R> mappingTo(Page<T> target, Function<T, R> mapper) {
        return new PageImpl<>(target.getContent().stream().map(mapper).collect(Collectors.toList()), target.getPageable(), target.getTotalElements());
    }

    /**
     * Pageable 페이징 적용
     */
    public static <T> PageableOrderBy<T> applyPageable(JPAQuery<T> query, Pageable pageable) {

        if (pageable != null && pageable.isPaged()) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());
        }

        return applyPageableOrderBy(query, pageable);
    }

    /**
     * Pageable OrderBy 처리 시작
     */
    public static <T> PageableOrderBy<T> applyPageableOrderBy(JPAQuery<T> query, Pageable pageable) {
        return new PageableOrderBy<>(query, pageable);
    }

    /**
     * Pageable OrderBy 처리 유틸 클래스
     */
    public static class PageableOrderBy<T> {

        private final JPAQuery<T> query;
        private final Pageable pageable;

        public PageableOrderBy(JPAQuery<T> query, Pageable pageable) {
            this.query = query;
            this.pageable = pageable;
        }

        public PageableOrderBy<T> orderBy(OrderBy... cases) {

            if (this.pageable.getSort().isSorted() && cases != null) {
                for (OrderBy item : cases) {
                    query.orderBy(createOrderByName(this.pageable, item.getName(), item.getPath()));
                }
            }
            return this;
        }

        private <R> OrderSpecifier<?>[] createOrderByName(Pageable pageable, String name, Path<R> path) {

            return pageable.getSort().stream()
                    .filter(order -> name.equals(order.getProperty()))
                    .map(order -> getOrderSpecifier(path.getMetadata().getParent(), order.isAscending(), path.getMetadata().getName()))
                    .toArray(OrderSpecifier[]::new);
        }

        /**
         * OrderSpecifier 생성
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private <R> OrderSpecifier<?> getOrderSpecifier(Path<R> path, boolean ascending, String name) {
            return new OrderSpecifier(
                    ascending ? Order.ASC : Order.DESC,
                    new PathBuilder<>(path.getType(), path.getMetadata().getName()).get(name)
            );
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderBy {
        private String name;
        private Path<?> path;

        public static OrderBy path(String name, Path<?> path) {
            return new OrderBy(name, path);
        }
    }
}
