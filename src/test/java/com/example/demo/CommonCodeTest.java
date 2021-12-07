package com.example.demo;

import com.example.demo.code.GenderCode;
import com.example.demo.code.StatusCode;
import com.example.demo.code.common.CommonCodeSupport;
import com.example.demo.service.CommonCodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonCodeTest {

    @Autowired
    private CommonCodeService commonCode;

    @Test
    public void commonCodeSupportTest() {
        CommonCodeSupport.getCodeList(GenderCode.class).forEach(System.out::println);
        CommonCodeSupport.getCodeList(StatusCode.class).forEach(System.out::println);
    }

    @Test
    public void jacksonTest() throws JsonProcessingException {

        String json = new ObjectMapper().writeValueAsString(new Sample());

        System.out.println(json);

        Sample obj = new ObjectMapper().readValue(json, Sample.class);

        System.out.println(obj);
    }

    public static class Sample {

        private GenderCode gender = GenderCode.ETC;
        private StatusCode status = StatusCode.DORMANT;

        public GenderCode getGender() {
            return gender;
        }

        public void setGender(GenderCode gender) {
            this.gender = gender;
        }

        public StatusCode getStatus() {
            return status;
        }

        public void setStatus(StatusCode status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Sample{" +
                    "gender=" + gender +
                    ", status=" + status +
                    '}';
        }
    }

}
