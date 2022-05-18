package com.codingproblem.codingproblem.DemoSpringRest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class ObjectMapperTestMain implements i1,i2 {


    public static void main(String[] args) throws JsonProcessingException {

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
