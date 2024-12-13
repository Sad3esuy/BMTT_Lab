/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TripleDES;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
/**
 *
 * @author nguye
 */
public class TripleDESCipher {
    private static final String ALGORITHM = "DESede";  // Định nghĩa thuật toán Triple DES

    // Phương thức mã hóa
    public static String encrypt(String plaintext, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, 
                   BadPaddingException, IllegalBlockSizeException {
        SecretKey key = generateKey(secretKey);  // Sinh khóa từ secretKey
        Cipher cipher = Cipher.getInstance(ALGORITHM);  // Tạo Cipher với thuật toán DESede
        cipher.init(Cipher.ENCRYPT_MODE, key);  // Khởi tạo Cipher ở chế độ mã hóa
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());  // Thực hiện mã hóa
        return Base64.getEncoder().encodeToString(encryptedBytes);  // Trả về chuỗi mã hóa dưới dạng Base64
    }

    // Phương thức giải mã
    public static String decrypt(String ciphertext, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, 
                   BadPaddingException, IllegalBlockSizeException {
        SecretKey key = generateKey(secretKey);  // Sinh khóa từ secretKey
        Cipher cipher = Cipher.getInstance(ALGORITHM);  // Tạo Cipher với thuật toán DESede
        cipher.init(Cipher.DECRYPT_MODE, key);  // Khởi tạo Cipher ở chế độ giải mã
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);  // Giải mã Base64 thành byte[]
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);  // Thực hiện giải mã
        return new String(decryptedBytes);  // Trả về chuỗi đã giải mã
    }

    // Phương thức sinh khóa
    private static SecretKey generateKey(String secretKey) throws NoSuchAlgorithmException {
        // Key must be 24 bytes (192 bits) long for Triple DES
        byte[] keyBytes = secretKey.getBytes();  // Chuyển secretKey thành mảng byte
        byte[] validKeyBytes = new byte[24];  // Khởi tạo mảng validKeyBytes với độ dài 24 byte

        // Truncate or pad keyBytes to make it 24 bytes long
        for (int i = 0; i < validKeyBytes.length; i++) {
            if (i < keyBytes.length) {
                validKeyBytes[i] = keyBytes[i];  // Sao chép byte từ keyBytes vào validKeyBytes
            } else {
                validKeyBytes[i] = 0;  // Padding bằng giá trị 0 nếu keyBytes ngắn hơn 24 byte
            }
        }

        // Tạo SecretKeySpec từ validKeyBytes
        SecretKeySpec keySpec = new SecretKeySpec(validKeyBytes, "DESede");
        return keySpec;  // Trả về đối tượng SecretKeySpec
    }
}
