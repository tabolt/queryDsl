package com.example.demo.service;

import com.example.demo.code.common.CommonCode;
import com.example.demo.code.common.CommonCodeConverter;
import com.example.demo.code.common.CommonCodeDto;
import com.example.demo.support.CollectionSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommonCodeService {

    private final Map<String, List<CommonCodeDto.Group>> groupMap;

    private final Map<String, List<CommonCodeDto.Code>> codeMap;

    public <E extends Enum<E> & CommonCode> CommonCodeService(@Autowired List<CommonCodeConverter<E>> converterList) {

        List<? extends CommonCode> all = converterList.stream()
                .map(CommonCodeConverter::getEnumClass)
                .map(EnumSet::allOf)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<String> dupCode = CollectionSupport.getDuplicatedElement(all, CommonCode::getCode);
        if (dupCode.size() > 0) {
            throw new IllegalArgumentException("공통코드가 중복 등록되었습니다. 코드 : " + StringUtils.join(dupCode, ","));
        }

        this.groupMap = all.stream()
                .map(CommonCodeDto.Group::from)
                .distinct()
                .collect(
                        Collectors.groupingBy(
                                CommonCodeDto.Group::getGroupCode, Collectors.toList()
                        )
                );

        this.codeMap = all.stream()
                .distinct()
                .collect(
                        Collectors.groupingBy(
                                CommonCode::getGroupCode,
                                Collectors.mapping(CommonCodeDto.Code::from, Collectors.toList())
                        )
                );
    }
}
