package com.example.demo.code.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class CommonCodeConfiguration {

    private ApplicationContext applicationContext;
    private Environment environment;

    /**
     * 공통코드 등록
     */
    @Bean(name = "commonCode")
    public Map<String, List<CommonCodeDto>> scanAllCommonCode() throws Exception {

        Set<String> dupCodeCheck = new HashSet<>();

        HashMap<String, List<CommonCodeDto>> commonCodeMap = new HashMap<>();

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(CommonCode.class));
        scanner.addIncludeFilter(new AssignableTypeFilter(Enum.class));

        // 모든 공통코드 검색
        Set<BeanDefinition> beans = scanner.findCandidateComponents("com.example.demo");
        for (BeanDefinition bean : beans) {

            Class<?> aClass = Class.forName(bean.getBeanClassName());

            List<CommonCodeDto> list = Arrays.stream(aClass.getEnumConstants())
                    .map(e -> (CommonCode) e)
                    .map(CommonCodeDto::from)
                    .peek(code -> {
                        if (dupCodeCheck.contains(code.getCode())) {
                            throw new IllegalArgumentException("Duplicated common code registered. code : " + code.getCode());
                        } else {
                            dupCodeCheck.add(code.getCode());
                        }
                    })
                    .collect(Collectors.toList());

            commonCodeMap.put(aClass.getSimpleName(), list);

            log.info("Common code group registered. {} : {}", aClass.getSimpleName(), list);
        }

        return commonCodeMap;
    }
}