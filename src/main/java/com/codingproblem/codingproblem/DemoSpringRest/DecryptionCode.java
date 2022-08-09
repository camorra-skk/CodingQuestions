package com.codingproblem.codingproblem.DemoSpringRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DecryptionCode {

    private static final String X_509 = "X.509";
    private static final String RSA = "RSA";
    private static final String AES = "AES";
    private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
    private static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
    private static final String SHA1_RSA = "SHA1withRSA";

    private String yblUpiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtG/L62XUYsuRlh5n5yaYwYf6aa4bUb/h+Xzstd00cifFT++MHYr5BQicNkNF2rYa86OsPJJDkPS/jht1syRERwN2S88cf4cWO9Tttx5FaQV+JgVTfNboaAyxnOkZebjSR9fA1Jo0t3phiDyDEJrWwuwWMaxMzEH0iGL4KhratpZKYMeZv1eoVw7/+PWryn0im3jpL2ryYeho9LiYO1bVqowuKcCfNmuvYgJDHJP+iEvNp5jYzVdQ9QnzyugU6l6iNliJjrljUVXJoYtVjeEYChupPfiYz/besrt3zNaFjD/J1zw+vuY3+kFt5St18bR2da5TGvlDiGTaWsHvAQGVRQIDAQAB";


    private String niyoUpiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0yw6NFqeR6sw3+/6sDNnG2ZrgDXF4MA5LHuNvgV/sRuHO7JNiKuCqsbBioIEI2437xEQc//JOjKJB4xXV7qlpXbftsGm52tcyTGy8YAzOuCeb8x4yRttDhNWsld0jrYMcVk/Y4qBOc1SnMxK3ayR6befGfU05s8YSGbEQdDxDGivfQX3x0QHGfgKez4r3nG4YGrps6mi7CvmW+YTTKNdOxaWtQzUT2zZAijbwy82kPft8mHO/7KINp4+ekwFHfX113kwxFiryF3bOqRfpLRF0oUcy87eHleK2RnRwKde3om/zqppEIJZ4Dj0FB0pAq5FJiY3ZVfTVldl2Bu0CqwfbwIDAQAB";


    private String niyoUpiPrivateKey= "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDTLDo0Wp5HqzDf7/qwM2cbZmuANcXgwDkse42+BX+xG4c7sk2Iq4KqxsGKggQjbjfvERBz/8k6MokHjFdXuqWldt+2wabna1zJMbLxgDM64J5vzHjJG20OE1ayV3SOtgxxWT9jioE5zVKczErdrJHpt58Z9TTmzxhIZsRB0PEMaK99BffHRAcZ+Ap7PivecbhgaumzqaLsK+Zb5hNMo107Fpa1DNRPbNkCKNvDLzaQ9+3yYc7/sog2nj56TAUd9fXXeTDEWKvIXds6pF+ktEXShRzLzt4eV4rZGdHAp17eib/OqmkQglngOPQUHSkCrkUmJjdlV9NWV2XYG7QKrB9vAgMBAAECggEADXwgh2FWBCuZZmNztbbCmcxThw9oAG8l94I4Te8Z9VxVqWk8bELPiEpC565fx0dtiPWlAFNQLUg22eWLWk3P8B4EMS5ykwqXFT34OFXAb70SQP9koyr+LszrV14gUlCrwL07QAcDM4a6BMtC6J7xfmQ8Y7L12ttUqKLvW2iVS/5xmFWkGarqFNlj7y/FxUXIaMwmSqXLi5i4XR0QRJD4XHWOlkdWoXGF2/oYrKpUbrQ4xha8UZaEJAOt35bni89gxLjc2Mz2l27EwM7pPTJktFBYoUcky+BGzXpNJIR9ulvfZWeQxoAiwpPT8LHiQVyDxStZU4Odxj7AU3F5PDRN6QKBgQD6C322tiesPeTustefYK0aLByqlOKmlQHb32z9oQockIXXCWTVjSmTvkLOLlxaq5vQsZWGtv6MGsoxcgFy6HELJv4uENk7vHUe+prHN7mPAsjotcQCknA9Z6BTZhsIP2dmzXA+7zZF2EAtnd8XOxQzkPzK+WY004OsYQ+FPSwj9QKBgQDYM7w5CNoPokztn+xkqpKJ3C944L6nmEuA+/zyF22kaN95BzvKGqUvw4D1fp5Opn9Rx6YGkRQKJUJ4nY/O6HLWAevlqmUcN95ygQV+e4981uOZ1hJQ3vgVPgYGtvBGyZx1rU2bG8wXx7TYDA+Ff/weFLIcLINfJYJku0Pt2+c7UwKBgQDSeeXCbSaMCDWy7/ws/nX3t/YRaO44hulfqXzXKj2WudjPn+qvD/pVkSwu3juDXzzzuOhC07sFOG/Gm0646Qxu9M20/R2++O4lu5GZ7EBYL2Hq8UYjXBz6s0XzrdlHYgeqM3guobGvrU6ol2F47pQcrAj+2ly8TudhrPohj9KeGQKBgDpZ/DsIgJIno8uelha1UseSfd2KCusA16AAYsyUNithgq8PnLt3ZY32nh+kBOYFWeegktbC4T27wKz9GYsmgZfw/NIHozJygb81w13Xy2pONS+X72mURDC3hLjbNw5j6653D7MFVZg1dkG5P5cwa8NSop+oA+zyGrdFM5hG+amxAoGAT0zBsfHXlz3jtuKNuadPn2yQn85iDHlqFct9I3P7X0gwhvwdeoq5TmFYmbW/IqTZn5ABqTlpftag45Z8pUWBw6ESDmkjUTBmN7Os7fYFJ2IQ0iclpV/2N4ElPfNJC4wCu3z91Q0pDVfA4KC/mUyg1g89jYywt4ucY4BkmTZhhis=";


    @Autowired
    YBLEncDecUtils yblEncDecUtils;


//     "payload": ""
//} with key ->  and iv -> AAAAAAAAAAAAAAAAAAAAAA== and hash ->


    public static void main(String[] args) {

        byte[] str = new byte[]{34, 97, 66, 48, 71, 55, 69, 101, 53, 102, 115, 76, 113, 66, 56, 102, 69, 111, 70, 102, 101, 67, 89, 101, 110, 109, 103, 109, 109, 97, 114, 83, 90, 69, 106, 101, 47, 72, 78, 86, 70, 86, 104, 52, 121, 73, 79, 101, 110, 100, 120, 99, 69, 88, 88, 97, 111, 67, 83, 89, 83, 112, 89, 69, 50, 73, 118, 47, 118, 100, 122, 109, 54, 97, 70, 118, 97, 100, 117, 108, 111, 51, 113, 120, 67, 55, 54, 70, 77, 107, 113, 102, 99, 90, 43, 104, 56, 98, 83, 68, 74, 112, 120, 52, 111, 111, 54, 74, 78, 78, 47, 77, 53, 66, 49, 76, 115, 119, 48, 120, 70, 68, 75, 53, 115, 70, 104, 115, 54, 106, 54, 120, 70, 108, 121, 122, 116, 80, 88, 112, 66, 70, 89, 112, 89, 72, 67, 98, 108, 51, 112, 112, 47, 66, 52, 69, 51, 69, 55, 97, 54, 88, 78, 106, 66, 48, 49, 78, 53, 52, 73, 47, 84, 47, 113, 53, 79, 54, 113, 106, 83, 114, 66, 84, 47, 122, 100, 67, 83, 108, 82, 57, 81, 74, 120, 76, 53, 90, 84, 98, 75, 70, 67, 79, 121, 51, 80, 78, 97, 114, 54, 85, 83, 85, 100, 55, 110, 90, 74, 79, 81, 106, 103, 81, 55, 87, 121, 81, 110, 100, 113, 84, 112, 86, 56, 100, 102, 90, 43, 52, 103, 71, 75, 88, 80, 52, 87, 104, 83, 69, 118, 48, 89, 72, 88, 100, 77, 90, 72, 101, 72, 68, 83, 77, 101, 50, 50, 87, 50, 85, 82, 47, 87, 56, 118, 101, 117, 81, 104, 66, 75, 99, 56, 105, 100, 72, 102, 105, 100, 84, 97, 103, 70, 88, 74, 82, 73, 78, 87, 76, 73, 77, 48, 108, 117, 72, 119, 84, 106, 74, 111, 111, 65, 67, 83, 69, 90, 55, 119, 72, 118, 43, 116, 100, 81, 85, 69, 48, 66, 49, 110, 107, 70, 79, 52, 66, 108, 71, 47, 107, 54, 116, 110, 74, 82, 47, 116, 122, 103, 47, 98, 109, 50, 53, 106, 65, 109, 119, 107, 77, 77, 81, 107, 104, 68, 66, 87, 53, 78, 85, 87, 121, 82, 103, 105, 99, 90, 103, 47, 107, 77, 80, 76, 82, 107, 108, 53, 122, 101, 87, 114, 103, 89, 75, 83, 74, 43, 72, 57, 65, 89, 102, 98, 117, 118, 89, 114, 106, 57, 76, 122, 47, 50, 88, 119, 79, 79, 98, 121, 48, 82, 119, 109, 112, 77, 75, 99, 76, 114, 101, 113, 107, 68, 47, 100, 105, 102, 85, 82, 120, 115, 106, 102, 99, 72, 90, 47, 84, 68, 76, 82, 53, 79, 87, 103, 85, 57, 122, 71, 116, 121, 72, 101, 120, 81, 65, 76, 69, 117, 105, 90, 50, 88, 98, 51, 121, 114, 110, 121, 90, 87, 54, 102, 120, 102, 100, 78, 104, 66, 107, 99, 79, 122, 97, 71, 114, 73, 109, 82, 68, 112, 84, 108, 54, 67, 104, 78, 87, 71, 115, 116, 82, 111, 108, 87, 102, 120, 108, 68, 110, 105, 79, 71, 69, 49, 80, 43, 122, 70, 76, 117, 100, 67, 48, 86, 51, 106, 43, 81, 97, 115, 75, 103, 74, 111, 71, 72, 120, 68, 73, 57, 74, 107, 70, 69, 110, 108, 109, 77, 71, 55, 112, 97, 116, 109, 101, 88, 50, 108, 114, 114, 116, 69, 81, 99, 118, 72, 73, 57, 69, 97, 103, 74, 105, 85, 83, 99, 108, 75, 48, 67, 118, 77, 97, 55, 116, 76, 106, 120, 97, 117, 67, 120, 101, 79, 122, 67, 73, 115, 53, 55, 83, 97, 106, 89, 57, 71, 118, 74, 87, 102, 86, 111, 102, 48, 82, 65, 73, 109, 104, 77, 82, 105, 82, 50, 83, 87, 90, 72, 117, 90, 108, 47, 88, 116, 55, 102, 52, 110, 120, 105, 83, 71, 83, 110, 114, 89, 103, 55, 84, 55, 83, 67, 87, 116, 120, 117, 78, 47, 119, 72, 76, 72, 76, 86, 105, 120, 68, 53, 119, 79, 120, 102, 111, 75, 100, 69, 55, 107, 90, 89, 72, 117, 79, 115, 89, 116, 73, 56, 117, 52, 54, 89, 56, 118, 102, 119, 114, 98, 74, 121, 71, 115, 50, 98, 83, 79, 67, 72, 50, 108, 84, 55, 87, 118, 81, 52, 51, 52, 114, 48, 86, 84, 49, 84, 70, 57, 55, 121, 118, 88, 65, 67, 101, 113, 82, 47, 53, 71, 85, 101, 100, 50, 101, 48, 100, 103, 100, 103, 75, 70, 81, 106, 68, 65, 48, 89, 90, 78, 54, 49, 97, 89, 49, 70, 111, 99, 89, 84, 67, 108, 85, 79, 78, 66, 120, 69, 53, 81, 118, 108, 100, 89, 122, 103, 112, 81, 97, 119, 66, 49, 73, 82, 100, 106, 89, 121, 118, 121, 86, 117, 73, 61, 34, 10};

        System.out.println((new String(str)));
        String payload = "aB0G7Ee5fsLqB8fEoFfeCYenmgmmarSZEje/HNVFVh4yIOendxcEXXaoCSYSpYE2Iv/vdzm6aFvadulo3qxC76FMkqfcZ+h8bSDJpx4oo6JNN/M5B1Lsw0xFDK5sFhs6j6xFlyztPXpBFYpYHCbl3pp/B4E3E7a6XNjB01N54I/T/q5O6qjSrBT/zdCSlR9QJxL5ZTbKFCOy3PNar6USUd7nZJOQjgQ7WyQndqTpV8dfZ+4gGKXP4WhSEv0YHXdMZHeHDSMe22W2UR/W8veuQhBKc8idHfidTagFXJRINWLIM0luHwTjJooACSEZ7wHv+tdQUE0B1nkFO4BlG/k6tnJR/tzg/bm25jAmwkMMQkhDBW5NUWyRgicZg/kMPLRkl5zeWrgYKSJ+H9AYfbuvYrj9Lz/2XwOOby0RwmpMKcLreqkD/difURxsjfcHZ/TDLR5OWgU9zGtyHexQALEuiZ2Xb3yrnyZW6fxfdNhBkcOzaGrImRDpTl6ChNWGstRolWfxlDniOGE1P+zFLudC0V3j+QasKgJoGHxDI9JkFEnlmMG7patmeX2lrrtEQcvHI9EagJiUSclK0CvMa7tLjxauCxeOzCIs57SajY9GvJWfVof0RAImhMRiR2SWZHuZl/Xt7f4nxiSGSnrYg7T7SCWtxuN/wHLHLVixD5wOxfoKdE7kZYHuOsYtI8u46Y8vfwrbJyGs2bSOCH2lT7WvQ434r0VT1TF97yvXACeqR/5GUed2e0dgdgKFQjDA0YZN61aY1FocYTClUONBxE5QvldYzgpQawB1IRdjYyvyVuI=";
        String key = "j55xr++UpHQ+43ie9FOhCWG8VmQSV1/Cl8T//vaIKyFl+6AiU7b2ZA0s9ZUDj0QBrT8W+2u7VpsHl/nabkaCUHq58a2W5Ge83QLP+lJYT6ejpLiu4s55H5K7pI9KW/XtIvlKMKTdKsX7Mc2bAGraPfl1TpokhgC519MhXHYgifKnIEBXVPsTLkRedBBnvKQo7nrPgYwjALeZCo8FrfkrjiMr53KKxBFVtqYFn38cFPMdxM/vREUhMfHkkgsB5srRN6gE2dM8GvSCBACoK6VpsFfsq64WS2OOojN0TXb4iwwHDZndE9JNbVgzTlSw+r2WNZkZGZpLkaVJhQtMRldFcw==";
        String iv = "AAAAAAAAAAAAAAAAAAAAAA==";
        String hash = "TruyFQoQp8YTxG5TXtJxhxTqxFb/r/lvOhW0XPbXoYJlfYU79YGeIy49DIzsi+D0sg1vyo+arYbFKZTO+cqlpsttEAk2UF0vJeR+mm8JDpLUHeUSkkYlts+ijk+946SeT+vxhoYN0OAtdsIueoEmVXseRTgUu65yvCvMfu+8r/PlAYPTE052hRYL74kmnhx6j6EQ+8L+nCPpy8TEDUrHfcNo1wtf2W8PBRJqWpqYHZpQSaMX+4e9cFh2jmmFxf8p2cG2VkUcpSApQkpG1IAdNKIWneuwMBjs2TjbWDN7BtW7YZVN4pNlezq3EFWwdBZR9T1uq6Z3OgA69MhblcYR7A==";

        DecryptionCode decryptionCode = new DecryptionCode();
        decryptionCode.testDecryption(payload,key,iv,hash);


    }

    enum ABC {
        Test
    }

    class MyClass {
        String val;
    }


    public void testDecryption(String payload, String key, String iv, String hash) {
        System.out.println("payload is -> {} with key -> {} and iv -> {} and hash -> {} "+payload + key + iv + hash);
        Assert.notNull(payload,"Payload can't be null");
        Assert.notNull(key,"Key can't be null");
        Assert.notNull(iv,"IV can't be null");
        Assert.notNull(hash,"hash can't be null");

        System.out.println("Here1");

        byte[] decryptedSessionKey = decryptSessionKeyUsingNiyoPrivKey(key.getBytes(StandardCharsets.UTF_8));

        System.out.println("Here2 -> " + decryptedSessionKey);

        System.out.println(new String(Base64.getDecoder().decode(iv.getBytes())));

        byte[] decodedIV = Base64.getDecoder().decode(iv.getBytes(StandardCharsets.UTF_8));



        byte[] decryptedBody = aesDecrypt(decryptedSessionKey,decodedIV,payload.getBytes(StandardCharsets.UTF_8));

        System.out.println("Decrypted Response body is -> " + new String(decryptedBody));
        boolean isSignatureVerified = verifyRSASignature(decryptedBody,hash);
        System.out.println("Signature verified is -> {}"+isSignatureVerified);

    }

    public byte[] decryptSessionKeyUsingNiyoPrivKey(byte[] encryptedSessionKey) {
        try {
            System.out.println("decryptSessionKeyUsingNiyoPrivKey");
            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(niyoUpiPrivateKey));
            return cipher.doFinal(Base64.getDecoder().decode(encryptedSessionKey));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to decrypt Session Key " + e.getMessage());
        }
    }

    private PrivateKey getPrivateKey(String privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            return  keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Unable to parse Private key",e);
        }
    }

    public byte[] aesDecrypt(byte[] randomSymmetricKey, byte[] iv, byte[] rawText)  {
        try {
            System.out.println("aesDecrypt");
            SecretKey originalKey = new SecretKeySpec(randomSymmetricKey, 0, randomSymmetricKey.length, AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            System.out.println("original Key " + originalKey);
            cipher.init(Cipher.DECRYPT_MODE, originalKey, new IvParameterSpec(iv));

            System.out.println("Raw : " +rawText);
           byte[] testData=new byte[]{123, 10, 32, 32, 32, 32, 34, 112, 97, 121, 108, 111, 97, 100, 34, 58, 32, 34, 107, 98, 107, 105, 86, 108, 117, 86, 90, 68, 84, 73, 114, 69, 100, 119, 69, 98, 47, 84, 117, 114, 48, 81, 67, 80, 80, 56, 107, 83, 101, 117, 68, 107, 80, 73, 57, 101, 100, 115, 88, 100, 52, 56, 104, 116, 47, 82, 49, 71, 114, 108, 112, 50, 49, 69, 49, 118, 80, 67, 110, 57, 107, 100, 82, 106, 52, 101, 98, 52, 112, 101, 68, 89, 87, 89, 80, 103, 50, 86, 81, 114, 50, 112, 106, 89, 49, 110, 65, 80, 51, 67, 77, 70, 104, 57, 84, 57, 55, 73, 101, 89, 80, 112, 100, 103, 100, 79, 98, 48, 80, 98, 90, 106, 81, 107, 49, 102, 112, 114, 99, 90, 78, 90, 84, 70, 65, 80, 81, 78, 56, 57, 73, 110, 67, 109, 111, 109, 51, 82, 113, 90, 70, 86, 52, 113, 78, 50, 77, 105, 119, 66, 69, 82, 85, 113, 75, 47, 68, 52, 111, 84, 72, 119, 71, 97, 103, 70, 75, 74, 52, 77, 82, 114, 101, 106, 113, 81, 85, 54, 99, 73, 43, 97, 117, 121, 82, 48, 51, 73, 105, 86, 71, 119, 82, 119, 97, 104, 56, 119, 112, 105, 83, 82, 83, 89, 84, 56, 101, 73, 90, 89, 49, 54, 83, 77, 69, 122, 86, 116, 51, 50, 109, 49, 108, 107, 79, 122, 74, 106, 81, 53, 79, 110, 116, 83, 56, 70, 114, 117, 103, 56, 55, 110, 67, 85, 72, 112, 79, 114, 109, 111, 101, 67, 52, 56, 83, 100, 82, 121, 51, 71, 119, 57, 83, 119, 114, 81, 112, 81, 119, 114, 55, 65, 120, 100, 120, 80, 83, 99, 100, 86, 67, 90, 73, 122, 101, 81, 85, 120, 119, 101, 71, 84, 121, 80, 56, 116, 101, 47, 49, 89, 76, 97, 55, 97, 54, 84, 56, 77, 49, 81, 74, 100, 87, 114, 49, 56, 106, 115, 119, 78, 101, 99, 98, 87, 73, 104, 113, 81, 81, 109, 99, 68, 113, 122, 120, 77, 84, 70, 106, 79, 70, 102, 43, 66, 48, 105, 103, 80, 116, 102, 76, 119, 118, 71, 77, 104, 109, 97, 88, 122, 101, 117, 119, 86, 55, 99, 107, 114, 83, 103, 98, 83, 71, 69, 48, 119, 49, 54, 110, 82, 70, 115, 34, 10, 125};

            System.out.println("DecryptionCode.aesDecrypt1> "+new String(testData));
            System.out.println("DecryptionCode.aesDecrypt2> "+new String(rawText));
           return cipher.doFinal(Base64.getDecoder().decode(rawText));

//            return cipher.doFinal(Base64.getDecoder().decode(rawText));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to decrypt Response "+ e.getMessage());
        }
    }

    public boolean verifyRSASignature(byte[] data,String hash) {
        try {
            Signature privateSignature = Signature.getInstance(SHA1_RSA);
            privateSignature.initVerify(getPublicKeyFromX509PublicKey(yblUpiPublicKey));
            privateSignature.update(data);
            return privateSignature.verify(Base64.getDecoder().decode(hash.getBytes(StandardCharsets.UTF_8)));

        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Signature is not verified "+e.getMessage());
        }
    }

    private PublicKey getPublicKeyFromX509PublicKey(String publicKey) {
        try {

            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            return keyFactory.generatePublic(keySpec);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to parse public key",e);
        }
    }
}
