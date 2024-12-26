/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RSA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
/**
 *
 * @author nguye
 */
public class RSADemo {
    public static void main(String[] args) throws IOException {
        int primeSize = 1024;  // Sửa 'primesize' thành 'primeSize'
        RSACipher rsa = new RSACipher(primeSize);  // Sửa 'rsa new RSACipher' thành 'rsa = new RSACipher'

        System.out.println("Key Size: [" + primeSize + "]");
        System.out.println();
        
        System.out.println("Generated prime numbers p and q");
        System.out.println("p: [" + rsa.getP().toString(16).toUpperCase() + "]");
        System.out.println("q: [" + rsa.getQ().toString(16).toUpperCase() + "]");  // Sửa 'rsa.get()' thành 'rsa.getQ()'
        System.out.println();
        
        System.out.println("The public key is the pair (N, E) which will be published.");
        System.out.println("N: [" + rsa.getN().toString(16).toUpperCase() + "]");
        System.out.println("E: [" + rsa.getE().toString(16).toUpperCase() + "]");  // Sửa 'B' thành 'E'
        System.out.println();
        
        System.out.println("The private key is the pair (N, D) which will be kept private.");
        System.out.println("N: [" + rsa.getN().toString(16).toUpperCase() + "]");
        System.out.println("D: [" + rsa.getD().toString(16).toUpperCase() + "]");
        System.out.println();
        
        System.out.print("Please enter message (plaintext): ");
        String plaintext = new BufferedReader(new InputStreamReader(System.in)).readLine();  // Sửa 'new BufferedReader...' cho đúng cú pháp
        
        // Giả sử rsa.encrypt() trả về một mảng BigInteger, cần kiểm tra phương thức này
        BigInteger[] ciphertext = rsa.encrypt(plaintext);
        
        System.out.print("Ciphertext: ");
        for (BigInteger ciphertextPart : ciphertext) {  // Sửa vòng lặp for cho đúng cú pháp
            System.out.print(ciphertextPart.toString(16).toUpperCase());
            System.out.print(" ");
        }
        System.out.println();

        // Giả sử rsa.decrypt() trả về một chuỗi văn bản đã giải mã, cần kiểm tra phương thức này
        String recoveredPlaintext = rsa.decrypt(ciphertext, rsa.getD(), rsa.getN());
        System.out.println("Recovered plaintext: " + recoveredPlaintext);
    }
}
