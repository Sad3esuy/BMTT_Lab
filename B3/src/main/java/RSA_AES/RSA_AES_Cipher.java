/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RSA_AES;
import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author nguye
 */
public class RSA_AES_Cipher {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public RSA_AES_Cipher() throws Exception {
        // Generate RSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // Key size 2048 bits
        KeyPair keyPair = keyGen.generateKeyPair();
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
    }

    // Method to encrypt data
    public byte[] encrypt(String plainText) throws Exception {
        // Generate AES key
        SecretKey secretKey = generateAESKey();
        
        // Encrypt the AES key with RSA
        byte[] encryptedSymmetricKey = rsaEncrypt(secretKey.getEncoded());

        // Encrypt the data using AES
        Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = aesCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Combine the encrypted AES key and the encrypted data
        byte[] combined = new byte[encryptedSymmetricKey.length + encryptedData.length];
        System.arraycopy(encryptedSymmetricKey, 0, combined, 0, encryptedSymmetricKey.length);
        System.arraycopy(encryptedData, 0, combined, encryptedSymmetricKey.length, encryptedData.length);

        return combined;
    }  
    // Method to decrypt data
    public String decrypt(byte[] combined) throws Exception {
        int symmetricKeyLength = 256; // RSA key size for 2048 bits
        byte[] encryptedSymmetricKey = new byte[symmetricKeyLength];
        byte[] encryptedData = new byte[combined.length - symmetricKeyLength];

        // Split the combined data into encrypted symmetric key and encrypted data
        System.arraycopy(combined, 0, encryptedSymmetricKey, 0, symmetricKeyLength);
        System.arraycopy(combined, symmetricKeyLength, encryptedData, 0, encryptedData.length);

        // RSA decrypt the symmetric key
        byte[] decryptedSymmetricKey = rsaDecrypt(encryptedSymmetricKey);

        // Convert the decrypted symmetric key into an AES key
        SecretKey secretKey = new SecretKeySpec(decryptedSymmetricKey, "AES");

        // Decrypt the data using AES
        Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedData = aesCipher.doFinal(encryptedData);

        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    // Method to generate AES key
    private SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Using AES 128 bits
        return keyGen.generateKey();
    }

    // RSA encryption method
    private byte[] rsaEncrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    // RSA decryption method
    private byte[] rsaDecrypt(byte[] encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedData);
    }

    // Getter for public key
    public PublicKey getPublicKey() {
        return publicKey;
    }

    // Getter for private key
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

}
