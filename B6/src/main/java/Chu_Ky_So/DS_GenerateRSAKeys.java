/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chu_Ky_So;
import java.security.*;
/**
 *
 * @author nguye
 */
public class DS_GenerateRSAKeys {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Khởi tạo KeyPairGenerator cho RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        
        // Khởi tạo với độ dài khóa 2048 bit
        keyGen.initialize(2048);
        
        // Tạo KeyPair (cặp khóa công khai và riêng tư)
        KeyPair keyPair = keyGen.generateKeyPair();
        
        // Lấy khóa công khai và khóa riêng tư
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        // In ra khóa công khai và khóa riêng tư (dưới dạng mã hóa)
        System.out.println("Public Key: " + publicKey.getEncoded());
        System.out.println("Private Key: " + privateKey.getEncoded());
    }
}
