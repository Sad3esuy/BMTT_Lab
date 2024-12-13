/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AES;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
/**
 *
 * @author nguye
 */
public class AESCipher {
    private static final String ALGORITHM = "AES";  // Định nghĩa thuật toán AES
    private static final String ENCRYPTION_KEY = "encryptionkey123";  // Khóa sử dụng cho AES (16 bytes)

    // Phương thức mã hóa
    public static String encrypt(String plaintext, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        SecretKey key = generateKey(secretKey);  // Tạo khóa từ secretKey
        Cipher cipher = Cipher.getInstance(ALGORITHM);  // Tạo Cipher với thuật toán AES
        cipher.init(Cipher.ENCRYPT_MODE, key);  // Khởi tạo Cipher ở chế độ mã hóa
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());  // Mã hóa văn bản
        return Base64.getEncoder().encodeToString(encryptedBytes);  // Mã hóa kết quả thành chuỗi Base64
    }

    // Phương thức giải mã
    public static String decrypt(String ciphertext, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        SecretKey key = generateKey(secretKey);  // Tạo khóa từ secretKey
        Cipher cipher = Cipher.getInstance(ALGORITHM);  // Tạo Cipher với thuật toán AES
        cipher.init(Cipher.DECRYPT_MODE, key);  // Khởi tạo Cipher ở chế độ giải mã
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);  // Giải mã Base64 thành byte[]
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);  // Giải mã dữ liệu
        return new String(decryptedBytes);  // Trả về chuỗi đã giải mã
    }

    // Phương thức sinh khóa
    private static SecretKey generateKey(String secretKey) throws NoSuchAlgorithmException {
        byte[] keyBytes = secretKey.getBytes();  // Chuyển secretKey thành mảng byte
        // Đảm bảo khóa có độ dài 16 byte (AES yêu cầu khóa 16, 24 hoặc 32 byte)
        if (keyBytes.length < 16) {
            byte[] paddedKey = new byte[16];
            System.arraycopy(keyBytes, 0, paddedKey, 0, keyBytes.length);
            keyBytes = paddedKey;
        }
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);  // Tạo khóa AES
        return keySpec;  // Trả về SecretKeySpec
    }
    // Phương thức tạo Registration Key
    public static String generateRegistrationKey(String username, String password) {
        String registrationKey = username + ":" + password + ":ENCRYPTION_KEY";  // Tạo key từ username, password và một giá trị cố định
        return registrationKey;
    }

    // Phương thức lưu Registration Key vào file
    public static void saveRegistrationKeyToFile(String registrationKey, String filename) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);  // Mở file để ghi
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {  // Tạo ObjectOutputStream để ghi đối tượng
            oos.writeObject(registrationKey);  // Ghi Registration Key vào file
        } catch (IOException e) {
            throw new IOException("Error saving registration key to file: " + e.getMessage());
        }
    }

    // Phương thức đọc Registration Key từ file
    public static String readRegistrationKeyFromFile(String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);  // Mở file để đọc
             ObjectInputStream ois = new ObjectInputStream(fis)) {  // Tạo ObjectInputStream để đọc đối tượng
            return (String) ois.readObject();  // Đọc và trả về Registration Key
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Error reading registration key from file: " + e.getMessage());
        }
    }
}
