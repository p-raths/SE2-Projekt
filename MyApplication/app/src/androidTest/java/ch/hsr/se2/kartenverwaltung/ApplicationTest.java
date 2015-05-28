package ch.hsr.se2.kartenverwaltung;

import android.app.Application;
import android.test.ApplicationTestCase;


import junit.framework.Assert;
import junit.framework.TestResult;

import javax.crypto.spec.SecretKeySpec;

import ch.hsr.se2.kartenverwaltung.data.Crypto;
import ch.hsr.se2.kartenverwaltung.domain.Card;
import ch.hsr.se2.kartenverwaltung.services.JsonRequestHandler;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest() {
        super(Application.class);
        hashTest();

    }

    static void hashTest(){
        Crypto c = new Crypto();
        byte[] hash = c.getHash("This is a Test");
        assertEquals(hash, c.getHash("This is a Test")); //gleicher text -> glecher hash
    }

    static void loginTest(){

    }

    static void addCardTest(){
       // Card card = new Card(0, "NewCard", "TestCard");
       // JsonRequestHandler jsonHandler = new JsonRequestHandler(this);
       // jsonHandler.jsonAddCardMethod(card);


    }

    static void deleteCardTest(){

    }

    static void dbConnectionTest(){

    }




}


