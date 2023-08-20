package com.cocobeen.Utils;

import com.cocobeen.Utils.struct.PostData;
import com.cocobeen.Utils.struct.TradeInfo;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DataEncryptionUtils {

    public static PostData EncryptData(String MerchantID, int amt, String key, String iv) {
        try {
            TradeInfo info = new TradeInfo(MerchantID, amt);

            String aes = encryptAES(info.getTradeInfoData(), key, iv);

            String SHA256 = insertAtBeginning(aes, "HashKey=" + key + "&") + "&HashIV=" + iv;

            return new PostData(MerchantID, aes, SHA256(SHA256).toUpperCase());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptAES(String data, String key, String iv) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encryptedBytes);
    }

    public static String decryptAES(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static String SHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars).toUpperCase();
    }

    private static String insertAtBeginning(String original, String prefix) {
        StringBuilder stringBuilder = new StringBuilder(prefix);
        stringBuilder.append(original);
        return stringBuilder.toString();
    }
}
