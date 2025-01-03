/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TACACS_plus;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author nguye
 */
public class TacacsServer {
    public static void main(String[] args) {
        int port = 49;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TACACS+ server is running on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client Connect: " + clientSocket.getInetAddress().getHostAddress());

                    try (DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                         DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream())) {

                        while (true) {
                            byte[] headerBytes = new byte[12];
                            if (dataInputStream.read(headerBytes) < headerBytes.length) {
                                System.out.println("Incomplete header received, closing connection.");
                                break;
                            }

                            int type = headerBytes[1];
                            int seqNum = headerBytes[9];
                            int bodyLength = ((headerBytes[5] & 0xFF) << 8) | (headerBytes[4] & 0xFF);

                            byte[] bodyBytes = new byte[bodyLength];
                            if (dataInputStream.read(bodyBytes) < bodyBytes.length) {
                                System.out.println("Incomplete body received, closing connection.");
                                break;
                            }

                            if (type == 1) { // Authentication request
                                byte[] response = createAuthResponse(seqNum);
                                dataOutputStream.write(response);
                                dataOutputStream.flush();
                            }
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    private static byte[] createAuthResponse(int seqNum) {
        byte[] header = {
            0x04, // Version
            0x02, // Authentication reply
            0x00, 0x00, 0x00, 0x00, // Session ID
            0x00, 0x00, 0x00, (byte) seqNum, // Sequence number
            0x00, 0x00, 0x00, 0x00 // Flags
        };

        String message = "Authentication successful!";
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] response = new byte[header.length + messageBytes.length];

        System.arraycopy(header, 0, response, 0, header.length);
        System.arraycopy(messageBytes, 0, response, header.length, messageBytes.length);

        return response;
    }
}
