package com.codingproblem.codingproblem.customannotation;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class TestClass {

    private static final String X_509 = "X.509";
    private static final String RSA = "RSA";
    private static final String AES = "AES";
    private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
    private static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
    private static final String SHA1_RSA = "SHA1withRSA";

    private static String yblUpiPublicKey =  "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtG/L62XUYsuRlh5n5yaYwYf6aa4bUb/h+Xzstd00cifFT++MHYr5BQicNkNF2rYa86OsPJJDkPS/jht1syRERwN2S88cf4cWO9Tttx5FaQV+JgVTfNboaAyxnOkZebjSR9fA1Jo0t3phiDyDEJrWwuwWMaxMzEH0iGL4KhratpZKYMeZv1eoVw7/+PWryn0im3jpL2ryYeho9LiYO1bVqowuKcCfNmuvYgJDHJP+iEvNp5jYzVdQ9QnzyugU6l6iNliJjrljUVXJoYtVjeEYChupPfiYz/besrt3zNaFjD/J1zw+vuY3+kFt5St18bR2da5TGvlDiGTaWsHvAQGVRQIDAQAB";
    private static String niyoUpiPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDTLDo0Wp5HqzDf7/qwM2cbZmuANcXgwDkse42+BX+xG4c7sk2Iq4KqxsGKggQjbjfvERBz/8k6MokHjFdXuqWldt+2wabna1zJMbLxgDM64J5vzHjJG20OE1ayV3SOtgxxWT9jioE5zVKczErdrJHpt58Z9TTmzxhIZsRB0PEMaK99BffHRAcZ+Ap7PivecbhgaumzqaLsK+Zb5hNMo107Fpa1DNRPbNkCKNvDLzaQ9+3yYc7/sog2nj56TAUd9fXXeTDEWKvIXds6pF+ktEXShRzLzt4eV4rZGdHAp17eib/OqmkQglngOPQUHSkCrkUmJjdlV9NWV2XYG7QKrB9vAgMBAAECggEADXwgh2FWBCuZZmNztbbCmcxThw9oAG8l94I4Te8Z9VxVqWk8bELPiEpC565fx0dtiPWlAFNQLUg22eWLWk3P8B4EMS5ykwqXFT34OFXAb70SQP9koyr+LszrV14gUlCrwL07QAcDM4a6BMtC6J7xfmQ8Y7L12ttUqKLvW2iVS/5xmFWkGarqFNlj7y/FxUXIaMwmSqXLi5i4XR0QRJD4XHWOlkdWoXGF2/oYrKpUbrQ4xha8UZaEJAOt35bni89gxLjc2Mz2l27EwM7pPTJktFBYoUcky+BGzXpNJIR9ulvfZWeQxoAiwpPT8LHiQVyDxStZU4Odxj7AU3F5PDRN6QKBgQD6C322tiesPeTustefYK0aLByqlOKmlQHb32z9oQockIXXCWTVjSmTvkLOLlxaq5vQsZWGtv6MGsoxcgFy6HELJv4uENk7vHUe+prHN7mPAsjotcQCknA9Z6BTZhsIP2dmzXA+7zZF2EAtnd8XOxQzkPzK+WY004OsYQ+FPSwj9QKBgQDYM7w5CNoPokztn+xkqpKJ3C944L6nmEuA+/zyF22kaN95BzvKGqUvw4D1fp5Opn9Rx6YGkRQKJUJ4nY/O6HLWAevlqmUcN95ygQV+e4981uOZ1hJQ3vgVPgYGtvBGyZx1rU2bG8wXx7TYDA+Ff/weFLIcLINfJYJku0Pt2+c7UwKBgQDSeeXCbSaMCDWy7/ws/nX3t/YRaO44hulfqXzXKj2WudjPn+qvD/pVkSwu3juDXzzzuOhC07sFOG/Gm0646Qxu9M20/R2++O4lu5GZ7EBYL2Hq8UYjXBz6s0XzrdlHYgeqM3guobGvrU6ol2F47pQcrAj+2ly8TudhrPohj9KeGQKBgDpZ/DsIgJIno8uelha1UseSfd2KCusA16AAYsyUNithgq8PnLt3ZY32nh+kBOYFWeegktbC4T27wKz9GYsmgZfw/NIHozJygb81w13Xy2pONS+X72mURDC3hLjbNw5j6653D7MFVZg1dkG5P5cwa8NSop+oA+zyGrdFM5hG+amxAoGAT0zBsfHXlz3jtuKNuadPn2yQn85iDHlqFct9I3P7X0gwhvwdeoq5TmFYmbW/IqTZn5ABqTlpftag45Z8pUWBw6ESDmkjUTBmN7Os7fYFJ2IQ0iclpV/2N4ElPfNJC4wCu3z91Q0pDVfA4KC/mUyg1g89jYywt4ucY4BkmTZhhis=";
    private static String niyoUpiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0yw6NFqeR6sw3+/6sDNnG2ZrgDXF4MA5LHuNvgV/sRuHO7JNiKuCqsbBioIEI2437xEQc//JOjKJB4xXV7qlpXbftsGm52tcyTGy8YAzOuCeb8x4yRttDhNWsld0jrYMcVk/Y4qBOc1SnMxK3ayR6befGfU05s8YSGbEQdDxDGivfQX3x0QHGfgKez4r3nG4YGrps6mi7CvmW+YTTKNdOxaWtQzUT2zZAijbwy82kPft8mHO/7KINp4+ekwFHfX113kwxFiryF3bOqRfpLRF0oUcy87eHleK2RnRwKde3om/zqppEIJZ4Dj0FB0pAq5FJiY3ZVfTVldl2Bu0CqwfbwIDAQAB";


    public static void main(String[] args) {


        String key = "aYCXhcLl4BmZphzu9NKvF7zKWkY/RZDajJqoT+usvlnr/NXW3OIjfwJsa4+0AgR1Q0F3wpc33tbYqaoG1B3jlzsd9yapmnRji99GbzV4tUtSL6QfK0SAsD3gV4IR+vVugWVhuwtp+Jb0XGV3VhhlHWUvkI/dp3JLDVHGE2aAJSdumfAHOKEyy+rbDdB4NvIUdL1pzHJUXxZ89Bqxg4FCRG9LXlNonRrElWdxWqqraG5bUmkNuNjqFBo7EjswlEUT+9d6hlcReEiBxKTV1aPGVdFwY3wm0shzF1h6AoT/ol3kx+oZgaqtBzjmClSxv0r99rnbjp+rCJOiLVNJ3EOtaw==";

        String iv = "0410645817324195";

        String body = "8b5JnNMLlxOyqHG5Zz9ArL61m/azyXLQ6sFhO+MOqoLArDNtRaJclF+5tFNOJT04sJZIoiPaMcGPMlqKYhqeIefL7jsXSxthxcs46A2l2YJ8h/dmuMtFNJCw1xBhgxi64wYn9iwUMWgygydLicanwzfLKiYGM6yJkTs6tMveMeNVTZGdXC5Mh58ZTi8g9P5bChmvD9g5FLz52uy2gKeiILOOqI2rBmruyu0hCU5sLfJqGGrXjwrmAXF/L8TK4nnyy/2N92qVCcXXtPdTXAXOz0XNFkrc9JQ4O8sYqRXPUsUzp/thojuoLCkM5dnbM1dwEzNKp7HKSHAe1+hWoyZXX2mhqCblsySdCoD5kTFx+VVdmtaUj/lAdz1RuJUQLI0qP6iZimUPrXkTm2IlB6zVao22Ce25dHJCSWLWI97yxkXLay/A/VOmjcCoFXWBBt2OemTR51yFVTyRTKkHBrES3Zy5VeDDxAtAIrI+r9Bqy+t4SQfNbyk3V7FgehxK9biZyxHZRyg/G9xwSTERbtk/35/axVM2kI/FruKaQnVv96hpL0oiSaRRvxA6NPcAnioe";

        String hash = "XpUkJsdVqfFYPHIJr3IV48Qjl6HqxHl4JAaamGQJrrlf8c1yc1aD2altRxJJyqTGJKvYJdZzm0h+yBTDzoVy2l5ugrVeMkbYwFYnO9G+wu8DdncKEA5jWrFgGi362sbnZWnTld0Y+3lOkAJz8C12EJV9TBvf7+H3x6XGbmNpV/ETe4C9qseKMw7pdjdruiHlVW/HqOXFeG/y8H0F8NU8EuCVc4DI7W0bVMJ9sjVyWHhdp+HCaBAHzuVIoNf8cpM3QVftdr1pacO1m8/HT4CgbUOvMss6cECxbbbIQd1YNAY4Gj5eLpjTtQnMMVv8b8WkuU+mJkFvDeQV3n+6WwcWaQ==";

        byte[] a = decryptSessionKeyUsingNiyoPrivKey(key.getBytes(StandardCharsets.UTF_8));
        System.out.println(Arrays.toString(a));

        byte[] b = aesDecrypt(a,iv.getBytes(StandardCharsets.UTF_8),body.getBytes(StandardCharsets.UTF_8));

        System.out.println(verifyRSASignature(b,hash));

        System.out.println(new String(b));


    }

    public static boolean verifyRSASignature(byte[] data, String hash) {
        try {
            Signature privateSignature = Signature.getInstance(SHA1_RSA);
//            privateSignature.initSign(getPublicKeyFromX509PublicKey(yblUpiPublicKey));
            privateSignature.initVerify(getPublicKeyFromX509PublicKey(yblUpiPublicKey));
            privateSignature.update(data);
            System.out.println(Arrays.toString(Base64.getDecoder().decode(hash.getBytes(StandardCharsets.UTF_8))));
            return privateSignature.verify(Base64.getDecoder().decode(hash.getBytes(StandardCharsets.UTF_8)));

        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    public static String generateSha1RsaSignature(byte[] data) {
//        try {
//            Signature privateSignature = Signature.getInstance(SHA1_RSA);
//            privateSignature.initSign(get(yblUpiPublicKey));
//            privateSignature.update(data);
//            byte[] s = privateSignature.sign();
//            return Base64.getEncoder().encodeToString(s);
//        } catch (GeneralSecurityException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    private static PrivateKey getPrivateKey(String privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            return  keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Unable to parse Private key",e);
        }
    }

    public static byte[] aesDecrypt(byte[] randomSymmetricKey, byte[] iv, byte[] body)  {
        try {
            SecretKey originalKey = new SecretKeySpec(randomSymmetricKey, 0, randomSymmetricKey.length, AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, originalKey, new IvParameterSpec(iv));

            return cipher.doFinal(Base64.getDecoder().decode(body));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException( e.getMessage());
        }
    }


    public static byte[] decryptSessionKeyUsingNiyoPrivKey(byte[] encryptedSessionKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(niyoUpiPrivateKey));
            return cipher.doFinal(Base64.getDecoder().decode(encryptedSessionKey));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException( e.getMessage());
        }
    }

    private static PublicKey getPublicKeyFromX509PublicKey(String publicKey) {
        try {

            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            return keyFactory.generatePublic(keySpec);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to parse public key",e);
        }
    }

}
