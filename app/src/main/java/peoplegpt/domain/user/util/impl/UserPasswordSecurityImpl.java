package peoplegpt.domain.user.util.impl;


import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import peoplegpt.domain.user.util.UserPasswordSecurity;

public class UserPasswordSecurityImpl implements UserPasswordSecurity {

    private final String key256 = Generate_256SecretKey();
    private final String ivKey = Generate_IVKey();
    
    @Override
    public String encryptPassword(String password) {
        String encryptedText = encrypt(key256, ivKey, password);
        return encryptedText;
    }
    @Override
    public String decryptPassword(String encryptedPassword) {
        String decryptedText = decrypt(key256, ivKey, encryptedPassword);
        return decryptedText;
    }
    
    /**
     * Google Cloud Platform (GCP) - Generate AES-256 Encryption Key Example
     */
    private String Generate_256SecretKey() {
        byte[] result = new byte[32];
        new SecureRandom().nextBytes(result);
        return Base64.getEncoder().encodeToString(result);
    }

    private String Generate_IVKey() {
        byte[] result = new byte[16];
        new SecureRandom().nextBytes(result);
        return Base64.getEncoder().encodeToString(result);
    }

    private static String encrypt(String key, String iv, String plainText) {
        // AES-256 Encryption
        try {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            byte[] decodedIV = Base64.getDecoder().decode(iv);

            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(decodedIV);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Decryption
    private static String decrypt(String key, String iv, String encryptedText) {
        // AES-256 Decryption
        try {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            byte[] decodedIV = Base64.getDecoder().decode(iv);
            byte[] decodedEncryptedText = Base64.getDecoder().decode(encryptedText);

            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(decodedIV);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] decryptedBytes = cipher.doFinal(decodedEncryptedText);

            return new String(decryptedBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }    
}
