package com.example.demo;

import com.example.demo.code.GenderCode;
import com.example.demo.code.StatusCode;
import com.example.demo.code.common.CommonCodeDto;
import com.example.demo.code.common.CommonCodeSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class CommonCodeTest {

    @Autowired
    private Map<String, List<CommonCodeDto>> commonCodeMap;

    @Test
    public void commonCodeSupportTest() {
        CommonCodeSupport.getCodeList(GenderCode.class).forEach(System.out::println);
        CommonCodeSupport.getCodeList(StatusCode.class).forEach(System.out::println);
    }

    @Test
    public void jacksonTest() throws JsonProcessingException {

        Sample sample = new Sample();
        sample.setGender(GenderCode.FEMALE);
        sample.setStatus(StatusCode.DORMANT);

        String json = new ObjectMapper().writeValueAsString(sample);

        System.out.println(json);

        Sample jsonObject = new ObjectMapper().readValue(json, Sample.class);

        System.out.println(jsonObject);

        Assertions.assertEquals(sample.getGender(), jsonObject.getGender());
        Assertions.assertEquals(sample.getStatus(), jsonObject.getStatus());
    }

    @Test
    public void commonCodeServiceTest() {

        List<CommonCodeDto> codeList = this.commonCodeMap.get(GenderCode.class.getSimpleName());
        System.out.println(codeList);
    }

    public static class Sample {

        private GenderCode gender;
        private StatusCode status;

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
            return "Sample{" + "gender=" + gender + ", status=" + status + '}';
        }
    }

}
