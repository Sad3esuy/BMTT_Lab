/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MD5;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author nguye
 */
public class MD5Util {
    public static String md5(String input) {
        try {
            // Khởi tạo MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Cập nhật thông tin đầu vào vào MessageDigest
            md.update(input.getBytes());

            // Tính toán giá trị hash
            byte[] digest = md.digest();

            // Chuyển đổi byte array thành BigInteger và sau đó thành chuỗi hex
            BigInteger bigInt = new BigInteger(1, digest);
            String md5Hex = bigInt.toString(16);

            // Đảm bảo chuỗi hex có độ dài chính xác 32 ký tự
            while (md5Hex.length() < 32) {
                md5Hex = "0" + md5Hex;
            }

            return md5Hex;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
