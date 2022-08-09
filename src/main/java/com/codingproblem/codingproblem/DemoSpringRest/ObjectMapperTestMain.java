package com.codingproblem.codingproblem.DemoSpringRest;

import com.codingproblem.codingproblem.DemoSpringRest.test.YblScanPayListBlockedVPAResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class ObjectMapperTestMain implements i1,i2 {


    public static void main(String[] args) throws JsonProcessingException {
        if(true){
            testBlockVPA();
            return;
        }

//        String jsonInput = "[{\"id\":\"junk\",\"stuff\":\"things\"},{\"id\":\"spam\",\"stuff\":\"eggs\"}]";
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        List<MyClass> myObjects = mapper.readValue(jsonInput, mapper.getTypeFactory().constructCollectionType(List.class, MyClass.class));
//
//        System.out.println(myObjects);

        ObjectMapperTestMain obj = new ObjectMapperTestMain();
        obj.a();


//        class MyClass {
//            String id;
//            String stuff;
//        }

        MyClass myClass = new MyClass("1","1");
        MyClass myClass1 = new MyClass("1","1");

        System.out.println(myClass.equals(myClass1));
        System.out.println(myClass==myClass1);

        String s = "Sumit";
        String s1 = "Sumit";
        String s2 = new String("Sumit");

        System.out.println(s.equals(s1));
        System.out.println(s==s1);
        System.out.println(s==s2);

    }

    private static void testBlockVPA() {
        String data="{\"status\":\"SUCCESS\",\"responseCode\":\"00\",\"requestId\":\"afuQ4eefes4r5tte006\",\"responseMessage\":\"Blocked VPAs list fetched successfully\",\"mobileNumber\":\"8767788264\",\"payload\":\"{\\\"merchantChannelId\\\":\\\"GONIYOTEST\\\",\\\"merchantId\\\":\\\"GONIYOTEST\\\",\\\"merchantCustomerId\\\":\\\"GON144\\\",\\\"customerMobileNumber\\\":\\\"918767788264\\\",\\\"blockedVpas\\\":[{\\\"payeeVpa\\\":\\\"9768981582@ypay\\\",\\\"blockedAt\\\":\\\"2022-06-23T13:12:50+05:30\\\",\\\"name\\\":\\\"Yash Hasmukh Mehta\\\"}]}\"}";
        System.out.println("ScanPayServiceImpl.main "+data);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        objectMapper.registerModule(new JavaTimeModule());

        YblScanPayListBlockedVPAResponseDto responseDto = null;
        try {
            responseDto = objectMapper.readValue(data, YblScanPayListBlockedVPAResponseDto.class);
            System.out.println("ObjectMapperTestMain.testBlockVPA AAAAAAAAAAAA"+responseDto.getPayloadStr());
//            if(SUCCESS_RESPONSE_CODE.equals(responseDto.getResponseCode())) {
                responseDto.setValidationPayload(objectMapper.readValue(responseDto.getPayloadStr(), YblScanPayListBlockedVPAResponseDto.YblScanPayListBlockedVPAValidationPayloadDto.class));
            System.out.println("ObjectMapperTestMain.testBlockVPA "+responseDto.getPayloadStr());
//                if(responseDto.getValidationPayload().getBlockedVpas()!= null) {
//                    List<YblScanPayListBlockedVPAResponseDto.YblScanPayListBlockedVPADto> list = objectMapper.readValue(responseDto.getValidationPayload().getBlockedVpas(), objectMapper.getTypeFactory().constructCollectionType(List.class, YblScanPayListBlockedVPAResponseDto.YblScanPayListBlockedVPADto.class));
//                    YblScanPayListBlockedVPAResponseDto.YblScanPayListBlockedVPAValidationPayloadDto validationPayload = responseDto.getValidationPayload();
//                    validationPayload.setBlockedVpasList(list);
//                    validationPayload.setBlockedVpas(null);
//                    responseDto.setValidationPayload(validationPayload);
//                }

                responseDto.setPayloadStr(null);
//            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("ScanPayServiceImpl.main responseDto"+responseDto);

    }

    @Override
    public void a() {
        i1.super.a();
    }
}

@AllArgsConstructor
class MyClass {
    String id;
    String stuff;
}

interface i1 {
    default void a() {
        System.out.println("i1");
    }
}

interface i2 {
    default void a() {
        System.out.println("i2");
    }
}
