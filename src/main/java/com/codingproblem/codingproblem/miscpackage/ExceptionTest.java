package com.codingproblem.codingproblem.miscpackage;

import javax.crypto.Cipher;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class ExceptionTest {

    public static void main(String[] args) {

        try {
            System.out.println("5");
            throw new ArrayIndexOutOfBoundsException();
        } catch (ArrayIndexOutOfBoundsException e ) {
            System.out.println("Catch");
            try {
                throw new FileNotFoundException();
            } catch (Exception e1) {
                System.out.println("Ex -1");
            }

        } catch (Exception e) {
            System.out.println("Ex");
        } finally {
            System.out.println("finally");
        }

    }

}
