package com.codingproblem.codingproblem.DemoSpringRest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;


@Component
@Slf4j
public class YBLEncDecUtils {

    private PublicKey yblPubKey;

    private PublicKey niyoPubKey;

    private PrivateKey niyoPrivKey;

    private static final String X_509 = "X.509";
    private static final String RSA = "RSA";
    private static final String AES = "AES";
    private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
    private static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
    private static final String SHA1_RSA = "SHA1withRSA";

    private String yblUpiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtG/L62XUYsuRlh5n5yaYwYf6aa4bUb/h+Xzstd00cifFT++MHYr5BQicNkNF2rYa86OsPJJDkPS/jht1syRERwN2S88cf4cWO9Tttx5FaQV+JgVTfNboaAyxnOkZebjSR9fA1Jo0t3phiDyDEJrWwuwWMaxMzEH0iGL4KhratpZKYMeZv1eoVw7/+PWryn0im3jpL2ryYeho9LiYO1bVqowuKcCfNmuvYgJDHJP+iEvNp5jYzVdQ9QnzyugU6l6iNliJjrljUVXJoYtVjeEYChupPfiYz/besrt3zNaFjD/J1zw+vuY3+kFt5St18bR2da5TGvlDiGTaWsHvAQGVRQIDAQAB";


    private String niyoUpiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0yw6NFqeR6sw3+/6sDNnG2ZrgDXF4MA5LHuNvgV/sRuHO7JNiKuCqsbBioIEI2437xEQc//JOjKJB4xXV7qlpXbftsGm52tcyTGy8YAzOuCeb8x4yRttDhNWsld0jrYMcVk/Y4qBOc1SnMxK3ayR6befGfU05s8YSGbEQdDxDGivfQX3x0QHGfgKez4r3nG4YGrps6mi7CvmW+YTTKNdOxaWtQzUT2zZAijbwy82kPft8mHO/7KINp4+ekwFHfX113kwxFiryF3bOqRfpLRF0oUcy87eHleK2RnRwKde3om/zqppEIJZ4Dj0FB0pAq5FJiY3ZVfTVldl2Bu0CqwfbwIDAQAB";


    private String niyoUpiPrivateKey= "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDTLDo0Wp5HqzDf7/qwM2cbZmuANcXgwDkse42+BX+xG4c7sk2Iq4KqxsGKggQjbjfvERBz/8k6MokHjFdXuqWldt+2wabna1zJMbLxgDM64J5vzHjJG20OE1ayV3SOtgxxWT9jioE5zVKczErdrJHpt58Z9TTmzxhIZsRB0PEMaK99BffHRAcZ+Ap7PivecbhgaumzqaLsK+Zb5hNMo107Fpa1DNRPbNkCKNvDLzaQ9+3yYc7/sog2nj56TAUd9fXXeTDEWKvIXds6pF+ktEXShRzLzt4eV4rZGdHAp17eib/OqmkQglngOPQUHSkCrkUmJjdlV9NWV2XYG7QKrB9vAgMBAAECggEADXwgh2FWBCuZZmNztbbCmcxThw9oAG8l94I4Te8Z9VxVqWk8bELPiEpC565fx0dtiPWlAFNQLUg22eWLWk3P8B4EMS5ykwqXFT34OFXAb70SQP9koyr+LszrV14gUlCrwL07QAcDM4a6BMtC6J7xfmQ8Y7L12ttUqKLvW2iVS/5xmFWkGarqFNlj7y/FxUXIaMwmSqXLi5i4XR0QRJD4XHWOlkdWoXGF2/oYrKpUbrQ4xha8UZaEJAOt35bni89gxLjc2Mz2l27EwM7pPTJktFBYoUcky+BGzXpNJIR9ulvfZWeQxoAiwpPT8LHiQVyDxStZU4Odxj7AU3F5PDRN6QKBgQD6C322tiesPeTustefYK0aLByqlOKmlQHb32z9oQockIXXCWTVjSmTvkLOLlxaq5vQsZWGtv6MGsoxcgFy6HELJv4uENk7vHUe+prHN7mPAsjotcQCknA9Z6BTZhsIP2dmzXA+7zZF2EAtnd8XOxQzkPzK+WY004OsYQ+FPSwj9QKBgQDYM7w5CNoPokztn+xkqpKJ3C944L6nmEuA+/zyF22kaN95BzvKGqUvw4D1fp5Opn9Rx6YGkRQKJUJ4nY/O6HLWAevlqmUcN95ygQV+e4981uOZ1hJQ3vgVPgYGtvBGyZx1rU2bG8wXx7TYDA+Ff/weFLIcLINfJYJku0Pt2+c7UwKBgQDSeeXCbSaMCDWy7/ws/nX3t/YRaO44hulfqXzXKj2WudjPn+qvD/pVkSwu3juDXzzzuOhC07sFOG/Gm0646Qxu9M20/R2++O4lu5GZ7EBYL2Hq8UYjXBz6s0XzrdlHYgeqM3guobGvrU6ol2F47pQcrAj+2ly8TudhrPohj9KeGQKBgDpZ/DsIgJIno8uelha1UseSfd2KCusA16AAYsyUNithgq8PnLt3ZY32nh+kBOYFWeegktbC4T27wKz9GYsmgZfw/NIHozJygb81w13Xy2pONS+X72mURDC3hLjbNw5j6653D7MFVZg1dkG5P5cwa8NSop+oA+zyGrdFM5hG+amxAoGAT0zBsfHXlz3jtuKNuadPn2yQn85iDHlqFct9I3P7X0gwhvwdeoq5TmFYmbW/IqTZn5ABqTlpftag45Z8pUWBw6ESDmkjUTBmN7Os7fYFJ2IQ0iclpV/2N4ElPfNJC4wCu3z91Q0pDVfA4KC/mUyg1g89jYywt4ucY4BkmTZhhis=";


    @PostConstruct
    void initialise() {
        log.info("Inside bean postConstruct {}",niyoUpiPublicKey);
        this.niyoPubKey=getPublicKeyFromX509PublicKey(niyoUpiPublicKey);
        this.niyoPrivKey=getPrivateKey(niyoUpiPrivateKey);
        this.yblPubKey=getPublicKeyFromX509PublicKey(yblUpiPublicKey);
    }
    public YBLEncDecUtils() {
        log.info("Inside bean constructor {}",niyoUpiPublicKey);

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

    private PublicKey getPublicKeyFromX509PublicCertificate(String publicCertificate) {
        try {
            return CertificateFactory
                    .getInstance(X_509)
                    .generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(publicCertificate)))
                    .getPublicKey();

        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to parse public key",e);
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

    public static String generateRandom16DigitNumber() {
        try {
            Random rand = SecureRandom.getInstanceStrong();  // SecureRandom is preferred to Random
            long x = (long) (rand.nextDouble() * 10000000000000000L);
            return String.format("%016d", x);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to generate 16 digit Random Number " +e.getMessage());
        }
    }

    public static byte[] generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(128);// Size of Key
            SecretKey aesKey = keyGenerator.generateKey();
            return aesKey.getEncoded();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to generate Session Key " + e.getMessage());
        }
    }

    public String aesEncrypt(byte[] randomSymmetricKey, byte[] iv, byte[] rawText) {
        try {
            SecretKey originalKey = new SecretKeySpec(randomSymmetricKey, 0, randomSymmetricKey.length, AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, originalKey, new IvParameterSpec(iv));

            return Base64.getEncoder().encodeToString(cipher.doFinal(rawText));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to encrypt request " + e.getMessage());
        }
    }

    public String encryptUsingYBLPubKey(byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, yblPubKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(key));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to encrypt using Public Key "+ e.getMessage());
        }
    }

    public String generateSha1RsaSignature(byte[] data) {
        try {
            Signature privateSignature = Signature.getInstance(SHA1_RSA);
            privateSignature.initSign(niyoPrivKey);
            privateSignature.update(data);
            byte[] s = privateSignature.sign();
            return Base64.getEncoder().encodeToString(s);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to generate SHA1RSA Signature " + e.getMessage());
        }
    }

    public byte[] aesDecrypt(byte[] randomSymmetricKey, byte[] iv, byte[] rawText)  {
        try {
            SecretKey originalKey = new SecretKeySpec(randomSymmetricKey, 0, randomSymmetricKey.length, AES);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, originalKey, new IvParameterSpec(iv));

            return cipher.doFinal(Base64.getDecoder().decode(rawText));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to decrypt Response "+ e.getMessage());
        }
    }

    public byte[] decryptSessionKeyUsingNiyoPrivKey(byte[] encryptedSessionKey) {
        try {
            System.out.println("decryptSessionKeyUsingNiyoPrivKey");
            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, niyoPrivKey);
            return cipher.doFinal(Base64.getDecoder().decode(encryptedSessionKey));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Unable to decrypt Session Key " + e.getMessage());
        }
    }

    public boolean verifyRSASignature(byte[] data,String hash) {
        try {
            Signature privateSignature = Signature.getInstance(SHA1_RSA);
            privateSignature.initVerify(yblPubKey);
            privateSignature.update(data);
            return privateSignature.verify(Base64.getDecoder().decode(hash.getBytes(StandardCharsets.UTF_8)));

        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Signature is not verified "+e.getMessage());
        }
    }
}
