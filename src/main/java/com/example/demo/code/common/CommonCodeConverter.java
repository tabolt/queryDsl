package com.example.demo.code.common;

import lombok.Getter;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.Optional;

@Getter
public abstract class CommonCodeConverter<E extends Enum<E> & CommonCode> implements AttributeConverter<E, String> {

    private final Class<E> enumClass;

    /**
     * NULL 허용 여부
     */
    private final boolean nullable;

    public CommonCodeConverter(Class<E> enumClass) {
        this(enumClass, false);
    }

    public CommonCodeConverter(Class<E> enumClass, boolean nullable) {
        this.enumClass = enumClass;
        this.nullable = nullable;
    }

    @Override
    public String convertToDatabaseColumn(E param) {

        if (!this.nullable && param == null) {
            throw new IllegalArgumentException(String.format("%s 은/는 NULL 을 입력 할 수 없습니다.", param.getGroupName()));
        }

        return Optional.ofNullable(param)
                .map(item -> item.getCode())
                .orElse(null);
    }

    @Override
    public E convertToEntityAttribute(String param) {

        return EnumSet.allOf(enumClass)
                .stream()
                .filter(e -> e.getCode().equals(param))
                .findAny()
                .orElse(null);
    }
}
