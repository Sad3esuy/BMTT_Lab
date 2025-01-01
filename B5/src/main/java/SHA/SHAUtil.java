/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SHA;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author nguye
 */
public class SHAUtil {
    // Phương thức để mã hóa với SHA-1
    public static String sha1(String input) throws NoSuchAlgorithmException {
        return hashString(input, "SHA-1");
    }

    // Phương thức để mã hóa với SHA-256
    public static String sha256(String input) throws NoSuchAlgorithmException {
        return hashString(input, "SHA-256");
    }

    // Phương thức để mã hóa với SHA-512
    public static String sha512(String input) throws NoSuchAlgorithmException {
        return hashString(input, "SHA-512");
    }

    // Phương thức chung để mã hóa với bất kỳ thuật toán nào
    private static String hashString(String input, String algorithm) throws NoSuchAlgorithmException {
        // Khởi tạo MessageDigest với thuật toán đã chỉ định
        MessageDigest md = MessageDigest.getInstance(algorithm);

        // Tính toán giá trị hash của chuỗi đầu vào
        byte[] hashedBytes = md.digest(input.getBytes());

        // Dùng StringBuilder để xây dựng chuỗi kết quả
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }

        // Trả về chuỗi hash dưới dạng hex
        return sb.toString();
    } 
}
