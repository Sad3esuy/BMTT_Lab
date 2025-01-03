/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Radius;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 *
 * @author nguye
 */
public class RadiusClient {
    private static final int RADIUS_PORT = 1812;
    private static final String RADIUS_SERVER = "localhost"; // Địa chỉ của RADIUS server
    private static final String SHARED_SECRET = "radius_secret"; // Chia sẻ bí mật

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000); // Đặt timeout cho socket

            // Chuẩn bị gói yêu cầu RADIUS (Access-Request)
            byte[] requestPacket = createAccessRequestPacket("testuser", "testpassword");

            // Gửi yêu cầu RADIUS tới server
            InetAddress serverAddress = InetAddress.getByName(RADIUS_SERVER);
            DatagramPacket requestDatagram = new DatagramPacket(requestPacket, requestPacket.length, serverAddress, RADIUS_PORT);
            socket.send(requestDatagram);
            System.out.println("Request sent to RADIUS server at " + serverAddress + ":" + RADIUS_PORT);

            // Nhận phản hồi từ server
            byte[] responseBuffer = new byte[4096];
            DatagramPacket responseDatagram = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responseDatagram);

            // Xử lý phản hồi
            processRadiusResponse(responseDatagram.getData(), responseDatagram.getLength());

        } catch (Exception e) {
            System.err.println("Error in RADIUS client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static byte[] createAccessRequestPacket(String username, String password) {
        byte[] packet = new byte[4096];

        // Thêm mã gói RADIUS: Access-Request (1)
        packet[0] = 1;

        // Thêm thuộc tính username và password
        byte[] usernameAttribute = createRadiusAttribute(1, username); // Type 1: Username
        byte[] passwordAttribute = createRadiusAttribute(2, password); // Type 2: Password

        // Chèn username và password vào gói RADIUS
        System.arraycopy(usernameAttribute, 0, packet, 20, usernameAttribute.length);
        System.arraycopy(passwordAttribute, 0, packet, 20 + usernameAttribute.length, passwordAttribute.length);

        return packet;
    }

    private static byte[] createRadiusAttribute(int type, String value) {
        byte[] attribute = new byte[value.length() + 2];

        // Thiết lập type và length của thuộc tính
        attribute[0] = (byte) type;
        attribute[1] = (byte) (value.length() + 2); // Length của thuộc tính, bao gồm type và length byte

        // Sao chép giá trị vào thuộc tính
        System.arraycopy(value.getBytes(), 0, attribute, 2, value.length());

        return attribute;
    }

    private static void processRadiusResponse(byte[] responseData, int responseLength) {
        int responseCode = responseData[0]; // Mã phản hồi trong gói RADIUS

        // Kiểm tra mã phản hồi và in kết quả
        if (responseCode == 2) {
            System.out.println("Authentication successful.");
        } else if (responseCode == 3) {
            System.out.println("Authentication failed.");
        } else {
            System.out.println("Unknown response code: " + responseCode);
        }
    }
}
