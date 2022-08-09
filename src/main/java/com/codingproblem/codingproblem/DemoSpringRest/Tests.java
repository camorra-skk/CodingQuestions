package com.codingproblem.codingproblem.DemoSpringRest;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Tests {

    public static void main(String[] args) throws ParseException {


        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];
        System.out.println(RandomStringUtils.randomAlphabetic(24));
        random.nextBytes(bytes);
        String s = new BigInteger(1, bytes).toString(16).substring(0,24);
        System.out.println(s);
        String amount = "0.76";
        System.out.println(Double.parseDouble(amount));

        String date = "2022-06-29T05:55:21+05:30";

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

        ZonedDateTime now = ZonedDateTime.parse(date);
        System.out.println(now.toInstant().getEpochSecond());


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date mydae = sdf.parse(date);

        ZonedDateTime zdtWithZoneOffset = ZonedDateTime
                .parse(date, formatter);

        ZonedDateTime zdtInLocalTimeline = zdtWithZoneOffset
                .withZoneSameInstant(ZoneId.systemDefault());

        System.out.println(zdtWithZoneOffset);
        System.out.println(zdtInLocalTimeline);
    }
}
