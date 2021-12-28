package com.example.demo.support;

import com.querydsl.core.types.CollectionExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;

import java.util.Collection;

public class QueryDslSupport {

    public static <T> BooleanExpression eq(SimpleExpression<T> left, T right) {
        return left.eq(right);
    }

    public static <T> BooleanExpression eq(SimpleExpression<T> left, SimpleExpression<T> right) {
        return left.eq(right);
    }

    public static <T> BooleanExpression eqIf(SimpleExpression<T> left, T right) {
        if (right == null) return null;
        return left.eq(right);
    }

    public static <T> BooleanExpression eqAny(SimpleExpression<T> left, CollectionExpression<?, ? super T> right) {
        return left.eqAny(right);
    }

    public static <T> BooleanExpression eqAnyIf(SimpleExpression<T> left, CollectionExpression<?, ? super T> right) {
        if (right == null) return null;
        return left.eqAny(right);
    }

    public static <T> BooleanExpression eqAll(SimpleExpression<T> left, CollectionExpression<?, ? super T> right) {
        return left.eqAll(right);
    }

    public static <T> BooleanExpression eqAllIf(SimpleExpression<T> left, CollectionExpression<?, ? super T> right) {

        return left.eqAll(right);
    }

    public static BooleanExpression contains(StringExpression left, Expression<String> right) {
        return left.contains(right);
    }

    public static BooleanExpression containsIf(StringExpression left, Expression<String> right) {
        if (right == null) return null;
        return left.contains(right);
    }

    public static BooleanExpression contains(StringExpression left, String right) {
        return left.contains(right);
    }

    public static BooleanExpression containsIf(StringExpression left, String right) {
        if (right == null) return null;
        return left.contains(right);
    }

    public static BooleanExpression containsIgnoreCase(StringExpression left, Expression<String> right) {
        return left.containsIgnoreCase(right);
    }

    public static BooleanExpression containsIgnoreCaseIf(StringExpression left, Expression<String> right) {
        if (right == null) return null;
        return left.containsIgnoreCase(right);
    }

    public static BooleanExpression containsIgnoreCase(StringExpression left, String right) {
        return left.containsIgnoreCase(right);
    }

    public static BooleanExpression containsIgnoreCaseIf(StringExpression left, String right) {
        if (right == null) return null;
        return left.containsIgnoreCase(right);
    }

    public static <T> BooleanExpression in(SimpleExpression<T> left, Collection<T> right) {
        return left.in(right);
    }

}
