/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DH_Socket;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;
/**
 *
 * @author nguye
 */
public class socket_Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_IP, PORT);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            // Generate client's key pair
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DiffieHellman");
            keyPairGen.initialize(1024);
            KeyPair clientKeyPair = keyPairGen.generateKeyPair();

            // Initialize client's key agreement
            KeyAgreement clientKeyAgreement = KeyAgreement.getInstance("DiffieHellman");
            clientKeyAgreement.init(clientKeyPair.getPrivate());

            // Receive server's public key
            byte[] serverPublicKeyBytes = (byte[]) in.readObject();
            KeyFactory keyFactory = KeyFactory.getInstance("DiffieHellman");
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(serverPublicKeyBytes);
            PublicKey serverPublicKey = keyFactory.generatePublic(x509KeySpec);

            // Perform the key agreement
            clientKeyAgreement.doPhase(serverPublicKey, true);

            // Send client's public key to the server
            out.writeObject(clientKeyPair.getPublic().getEncoded());

            // Generate the shared secret
            byte[] sharedSecret = clientKeyAgreement.generateSecret();
            SecretKeySpec secretKeySpec = new SecretKeySpec(sharedSecret, 0, 16, "AES");

            // Take input from user and encrypt it
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter text: ");
            String plainText = scanner.nextLine();

            // Encrypt the message using AES
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

            // Send the encrypted message to the server
            out.writeObject(encryptedBytes);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException
                | ClassNotFoundException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    } 
}
