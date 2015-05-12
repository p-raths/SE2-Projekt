package ch.hsr.se2.kartenverwaltung.data;

import android.util.Base64;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by patrick on 24.04.15.
 */
public class Crypto {

    public String getHash(String text) {
        try {
            byte[] toHash = (text).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            return new String(sha.digest(toHash));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }



    public SecretKeySpec getKey(String keyString) {

        byte[] key = null;
        try {
            key = (keyString).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return new SecretKeySpec(key, "AES");
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

    public String base64Encode(String input){

        return null;

    }

    public String base64Decode(String input){

        return null;

    }

}
