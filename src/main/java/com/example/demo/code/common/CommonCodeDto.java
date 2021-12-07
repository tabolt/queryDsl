package com.example.demo.code.common;

import lombok.*;

public class CommonCodeDto {

    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Group {

        private String groupCode;
        private String groupName;

        public static Group from(CommonCode code) {
            return new Group(code.getGroupCode(), code.getGroupName());
        }
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Code {

        private String code;
        private String name;

        public static Code from(CommonCode code) {
            return new Code(code.getCode(), code.getName());
        }
    }
}
