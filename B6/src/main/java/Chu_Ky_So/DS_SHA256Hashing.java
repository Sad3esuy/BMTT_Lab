/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chu_Ky_So;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author nguye
 */
public class DS_SHA256Hashing {
    public static byte[] hash(String input) throws NoSuchAlgorithmException {
        // Khởi tạo MessageDigest với thuật toán SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        // Tính toán hash của chuỗi đầu vào
        return md.digest(input.getBytes());
    }
}
