/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TACACS_plus;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author nguye
 */
public class TacacsClient {
    public static void main(String[] args) {
        String serverIp = "localhost";
        int serverPort = 49;

        try (Socket socket = new Socket(serverIp, serverPort);
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {

            byte[] request = createAuthRequest(1, "username", "password");
            dataOutputStream.write(request);
            dataOutputStream.flush();

            byte[] responseHeader = new byte[12];
            if (dataInputStream.read(responseHeader) < responseHeader.length) {
                throw new IOException("Incomplete response header.");
            }

            int bodyLength = ((responseHeader[5] & 0xFF) << 8) | (responseHeader[4] & 0xFF);
            byte[] responseBody = new byte[bodyLength];
            if (dataInputStream.read(responseBody) < responseBody.length) {
                throw new IOException("Incomplete response body.");
            }

            String responseMessage = new String(responseBody, StandardCharsets.UTF_8);
            System.out.println("Server response: " + responseMessage);
        } catch (IOException e) {
            System.err.println("Error in TacacsClient: " + e.getMessage());
        }
    }

    private static byte[] createAuthRequest(int seqNum, String username, String password) {
        byte[] header = {
            0x04, // Version
            0x01, // Authentication request
            0x00, 0x00, 0x00, 0x00, // Session ID
            0x00, 0x00, 0x00, (byte) seqNum, // Sequence number
            0x00, 0x00, 0x00, 0x00 // Flags
        };

        String usernamePassword = username + "\0" + password;
        byte[] usernamePasswordBytes = usernamePassword.getBytes(StandardCharsets.UTF_8);
        byte[] request = new byte[header.length + usernamePasswordBytes.length];

        System.arraycopy(header, 0, request, 0, header.length);
        System.arraycopy(usernamePasswordBytes, 0, request, header.length, usernamePasswordBytes.length);

        return request;
    }
}
