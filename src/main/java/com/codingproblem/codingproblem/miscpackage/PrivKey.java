package com.codingproblem.codingproblem.miscpackage;

import java.io.BufferedReader;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class PrivKey {

    public static final String X_509 = "X.509";
    public static final String RSA = "RSA";

    private final static String PRIVATE_KEY =
            "-----BEGIN PRIVATE KEY-----\n"
                    + "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM7t8Ub1DP+B91NJ\n"
                    + "nC45zqIvd1QXkQ5Ac1EJl8mUglWFzUyFbhjSuF4mEjrcecwERfRummASbLoyeMXl\n"
                    + "eiPg7jvSaz2szpuV+afoUo9c1T+ORNUzq31NvM7IW6+4KhtttwbMq4wbbPpBfVXA\n"
                    + "IAhvnLnCp/VyY/npkkjAid4c7RoVAgMBAAECgYBcCuy6kj+g20+G5YQp756g95oN\n"
                    + "dpoYC8T/c9PnXz6GCgkik2tAcWJ+xlJviihG/lObgSL7vtZMEC02YXdtxBxTBNmd\n"
                    + "upkruOkL0ElIu4S8CUwD6It8oNnHFGcIhwXUbdpSCr1cx62A0jDcMVgneQ8vv6vB\n"
                    + "/YKlj2dD2SBq3aaCYQJBAOvc5NDyfrdMYYTY+jJBaj82JLtQ/6K1vFIwdxM0siRF\n"
                    + "UYqSRA7G8A4ga+GobTewgeN6URFwWKvWY8EGb3HTwFkCQQDgmKtjjJlX3BotgnGD\n"
                    + "gdxVgvfYG39BL2GnotSwUbjjce/yZBtrbcClfqrrOWWw7lPcX1d0v8o3hJfLF5dT\n"
                    + "6NAdAkA8qAQYUCSSUwxJM9u0DOqb8vqjSYNUftQ9dsVIpSai+UitEEx8WGDn4SKd\n"
                    + "V8kupy/gJlau22uSVYI148fJSCGRAkBz+GEHFiJX657YwPI8JWHQBcBUJl6fGggi\n"
                    + "t0F7ibceOkbbsjU2U4WV7sHyk8Cei3Fh6RkPf7i60gxPIe9RtHVBAkAnPQD+BmND\n"
                    + "By8q5f0Kwtxgo2+YkxGDP5bxDV6P1vd2C7U5/XxaN53Kc0G8zu9UlcwhZcQ5BljH\n"
                    + "N24cUWZOo+60\n"
                    + "-----END PRIVATE KEY-----";



    public static void main(String[] args) throws Exception {

        StringBuilder pkcs8Lines = new StringBuilder();
        BufferedReader rdr = new BufferedReader(new StringReader(PRIVATE_KEY));
        String line;
        while ((line = rdr.readLine()) != null) {
            pkcs8Lines.append(line);
        }

        String pkcs8Pem = pkcs8Lines.toString();
        pkcs8Pem = pkcs8Pem.replace("-----BEGIN PRIVATE KEY-----", "");
        pkcs8Pem = pkcs8Pem.replace("-----END PRIVATE KEY-----", "");
        pkcs8Pem = pkcs8Pem.replaceAll("\\s+","");

        // Base64 decode the result

        byte [] pkcs8EncodedBytes = Base64.getDecoder().decode(pkcs8Pem);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privKey = kf.generatePrivate(keySpec);
        System.out.println("Test : "+privKey);


        String privateKeyString = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDTLDo0Wp5HqzDf7/qwM2cbZmuANcXgwDkse42+BX+xG4c7sk2Iq4KqxsGKggQjbjfvERBz/8k6MokHjFdXuqWldt+2wabna1zJMbLxgDM64J5vzHjJG20OE1ayV3SOtgxxWT9jioE5zVKczErdrJHpt58Z9TTmzxhIZsRB0PEMaK99BffHRAcZ+Ap7PivecbhgaumzqaLsK+Zb5hNMo107Fpa1DNRPbNkCKNvDLzaQ9+3yYc7/sog2nj56TAUd9fXXeTDEWKvIXds6pF+ktEXShRzLzt4eV4rZGdHAp17eib/OqmkQglngOPQUHSkCrkUmJjdlV9NWV2XYG7QKrB9vAgMBAAECggEADXwgh2FWBCuZZmNztbbCmcxThw9oAG8l94I4Te8Z9VxVqWk8bELPiEpC565fx0dtiPWlAFNQLUg22eWLWk3P8B4EMS5ykwqXFT34OFXAb70SQP9koyr+LszrV14gUlCrwL07QAcDM4a6BMtC6J7xfmQ8Y7L12ttUqKLvW2iVS/5xmFWkGarqFNlj7y/FxUXIaMwmSqXLi5i4XR0QRJD4XHWOlkdWoXGF2/oYrKpUbrQ4xha8UZaEJAOt35bni89gxLjc2Mz2l27EwM7pPTJktFBYoUcky+BGzXpNJIR9ulvfZWeQxoAiwpPT8LHiQVyDxStZU4Odxj7AU3F5PDRN6QKBgQD6C322tiesPeTustefYK0aLByqlOKmlQHb32z9oQockIXXCWTVjSmTvkLOLlxaq5vQsZWGtv6MGsoxcgFy6HELJv4uENk7vHUe+prHN7mPAsjotcQCknA9Z6BTZhsIP2dmzXA+7zZF2EAtnd8XOxQzkPzK+WY004OsYQ+FPSwj9QKBgQDYM7w5CNoPokztn+xkqpKJ3C944L6nmEuA+/zyF22kaN95BzvKGqUvw4D1fp5Opn9Rx6YGkRQKJUJ4nY/O6HLWAevlqmUcN95ygQV+e4981uOZ1hJQ3vgVPgYGtvBGyZx1rU2bG8wXx7TYDA+Ff/weFLIcLINfJYJku0Pt2+c7UwKBgQDSeeXCbSaMCDWy7/ws/nX3t/YRaO44hulfqXzXKj2WudjPn+qvD/pVkSwu3juDXzzzuOhC07sFOG/Gm0646Qxu9M20/R2++O4lu5GZ7EBYL2Hq8UYjXBz6s0XzrdlHYgeqM3guobGvrU6ol2F47pQcrAj+2ly8TudhrPohj9KeGQKBgDpZ/DsIgJIno8uelha1UseSfd2KCusA16AAYsyUNithgq8PnLt3ZY32nh+kBOYFWeegktbC4T27wKz9GYsmgZfw/NIHozJygb81w13Xy2pONS+X72mURDC3hLjbNw5j6653D7MFVZg1dkG5P5cwa8NSop+oA+zyGrdFM5hG+amxAoGAT0zBsfHXlz3jtuKNuadPn2yQn85iDHlqFct9I3P7X0gwhvwdeoq5TmFYmbW/IqTZn5ABqTlpftag45Z8pUWBw6ESDmkjUTBmN7Os7fYFJ2IQ0iclpV/2N4ElPfNJC4wCu3z91Q0pDVfA4KC/mUyg1g89jYywt4ucY4BkmTZhhis=";

        PrivateKey privateKey = getPrivateKey(privateKeyString);

        System.out.println(privateKey);


    }

    private static PrivateKey getPrivateKey(String privateKeyString) throws Exception {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString));
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException();
        }
    }


}
