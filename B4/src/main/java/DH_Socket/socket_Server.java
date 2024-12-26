/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DH_Socket;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
/**
 *
 * @author nguye
 */
public class socket_Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            System.out.println("Server is running...");
            System.out.println("Client connected.");

            // Generate server's key pair
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DiffieHellman");
            keyPairGen.initialize(1024);
            KeyPair serverKeyPair = keyPairGen.generateKeyPair();

            // Initialize server key agreement
            KeyAgreement serverKeyAgreement = KeyAgreement.getInstance("DiffieHellman");
            serverKeyAgreement.init(serverKeyPair.getPrivate());

            // Send server's public key to client
            out.writeObject(serverKeyPair.getPublic().getEncoded());

            // Receive client's public key
            byte[] clientPublicKeyBytes = (byte[]) in.readObject();
            KeyFactory keyFactory = KeyFactory.getInstance("DiffieHellman");
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(clientPublicKeyBytes);
            PublicKey clientPublicKey = keyFactory.generatePublic(x509KeySpec);

            // Perform the key agreement
            serverKeyAgreement.doPhase(clientPublicKey, true);
            byte[] sharedSecret = serverKeyAgreement.generateSecret();

            // Create AES secret key using the shared secret
            SecretKeySpec secretKeySpec = new SecretKeySpec(sharedSecret, 0, 16, "AES");

            // Decrypt the message sent by the client
            byte[] encryptedBytes = (byte[]) in.readObject();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            String decryptedMessage = new String(cipher.doFinal(encryptedBytes));

            System.out.println("Received message from client: " + new String(encryptedBytes));
            System.out.println("Decrypted message from client: " + decryptedMessage);

            // Close the socket
            socket.close();

        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException
                 | ClassNotFoundException | NoSuchPaddingException | IllegalBlockSizeException
                 | BadPaddingException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}
