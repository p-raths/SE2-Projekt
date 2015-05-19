package ch.hsr.se2.kartenverwaltung;

import android.app.Application;
import android.test.ApplicationTestCase;


import junit.framework.Assert;
import junit.framework.TestResult;

import javax.crypto.spec.SecretKeySpec;

import ch.hsr.se2.kartenverwaltung.data.Crypto;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    static void hashTest(){
        Crypto c = new Crypto();
        String hash = c.getHash("This is a Test");
        assertEquals(hash, "268eeff87b29f953e7c6e1608140efb1f43c9e02");
    }

    static void cryptoTest(){
        Crypto c = new Crypto();
        SecretKeySpec aesKey = c.getKey("password");

        String text = "This is a Test";

        String encrypted = c.encrypt(text, aesKey);
        String decrypted = c.decrypt(encrypted, aesKey);

        assertEquals(decrypted, "This is a Test");
    }
}


