/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chu_Ky_So;
import java.security.*;
import java.util.Base64;
/**
 *
 * @author nguye
 */
public class DS_RSASignature {
    public static void main(String[] args) throws Exception {
        String text = "Hello, this is a message to be signed.";
        
        // Hashing the text using SHA-256
        byte[] hashedText = DS_SHA256Hashing.hash(text);
        
        // Generate RSA key pair
        KeyPair keyPair = generateRSAKeyPair();
        
        // Signing the hashed text
        byte[] signature = sign(hashedText, keyPair.getPrivate());
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
        
        // Verifying the signature
        boolean isVerified = verify(hashedText, signature, keyPair.getPublic());
        System.out.println("Signature verified: " + isVerified);
    }

    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        // Create RSA KeyPairGenerator instance
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        
        // Initialize the KeyPairGenerator with 2048-bit key size
        keyGen.initialize(2048);
        
        // Generate and return the KeyPair
        return keyGen.generateKeyPair();
    }

    public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception {
        // Initialize Signature instance with SHA-256 and RSA
        Signature signature = Signature.getInstance("SHA256withRSA");
        
        // Initialize the signature for signing with the private key
        signature.initSign(privateKey);
        
        // Update the signature with data
        signature.update(data);
        
        // Sign and return the signature
        return signature.sign();
    } 
    public static boolean verify(byte[] data, byte[] signature, PublicKey publicKey) throws Exception {
        // Khởi tạo Signature instance với thuật toán SHA-256 và RSA
        Signature sig = Signature.getInstance("SHA256withRSA");

        // Khởi tạo để xác minh chữ ký với khóa công khai
        sig.initVerify(publicKey);

        // Cập nhật dữ liệu vào đối tượng Signature
        sig.update(data);

        // Kiểm tra chữ ký và trả về kết quả (true nếu hợp lệ, false nếu không hợp lệ)
        return sig.verify(signature);
    }

}
