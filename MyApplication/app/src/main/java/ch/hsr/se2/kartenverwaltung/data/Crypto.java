package ch.hsr.se2.kartenverwaltung.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by patrick on 24.04.15.
 */
public class Crypto {

    public byte[] getHash(String text) {
        try {
            byte[] toHash = (text).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            return sha.digest(toHash);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }



    public void getKey(String keyString, Context context) {

        byte[] key = null;
        try {
            key = (keyString).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
        } catch (Throwable e) {
            e.printStackTrace();
        }



        SharedPreferences prefs = context.getSharedPreferences("Kartenverwaltung", 0);
        String stringSecretKey = Base64.encodeToString(
                key, Base64.DEFAULT);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("SECRET_KEY", stringSecretKey);
        editor.commit();


    }

    public String encrypt(String text, SecretKeySpec key) {
        SecretKeySpec secretKeySpec = key;
        byte[] encrypted = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            encrypted = cipher.doFinal(text.getBytes());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // bytes converte to Base64-String (easyer to read)
        String secret = Base64.encodeToString(encrypted, Base64.DEFAULT);
        return secret;
    }

    public String decrypt(String secret, SecretKeySpec key) {

        String result = null;
        byte[] crypted = Base64.decode(secret, Base64.DEFAULT);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] cipherData2 = cipher.doFinal(crypted);
            result = new String(cipherData2);

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return result;
    }



}
