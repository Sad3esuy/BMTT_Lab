/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Radius;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
/**
 *
 * @author nguye
 */
public class RadiusServer {
    private static final int RADIUS_PORT = 1812;
    private static final String SHARED_SECRET = "radius_secret"; // Đảm bảo chỉ một dòng khai báo

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(RADIUS_PORT)) {
            System.out.println("RADIUS server started on port " + RADIUS_PORT);
            while (true) {
                byte[] receiveBuffer = new byte[4096];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                
                // Nhận gói dữ liệu
                socket.receive(receivePacket);
                processRadiusPacket(receivePacket);
            }
        } catch (Exception e) {
            System.err.println("Error in RADIUS server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processRadiusPacket(DatagramPacket packet) {
        byte[] receiveData = packet.getData();
        int receiveLength = packet.getLength();
        System.out.println("Received packet from " + packet.getAddress() + ":" + packet.getPort() +
                " with length " + receiveLength + " bytes");

        String username = extractUsername(receiveData, receiveLength);
        boolean isAuthenticated = true; // Logic kiểm tra xác thực ở đây
        sendRadiusResponse(packet.getAddress(), packet.getPort(), receiveData, receiveLength, isAuthenticated);
    }

    private static String extractUsername(byte[] data, int length) {
        // Đây chỉ là một ví dụ. Bạn có thể thay thế bằng cách phân tích dữ liệu thực tế.
        return "testuser";
    }

    private static void sendRadiusResponse(InetAddress clientAddress, int clientPort,
                                           byte[] requestData, int requestLength, boolean isAuthenticated) {
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] responseData = Arrays.copyOf(requestData, requestLength);
            
            if (isAuthenticated) {
                // Thay đổi mã gói phản hồi thành Access-Accept (2)
                responseData[0] = 2;
            } else {
                // Thay đổi mã gói phản hồi thành Access-Reject (3)
                responseData[0] = 3;
            }

            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
            socket.send(responsePacket);
            System.out.println("Response sent to " + clientAddress + ":" + clientPort);
        } catch (Exception e) {
            System.err.println("Error sending response: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
