/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DIFFIE_HELLMAN;

/**
 *
 * @author nguye
 */
public class CryptoUtil {
    // Converts a byte array to a hexadecimal string
    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            // Ensure that single digit hex values are prepended with '0'
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Converts a single byte to its hexadecimal representation and appends to a StringBuffer
    public static final void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        
        int high = (b & 0xF0) >> 4;  // Get the high nibble (4 most significant bits)
        int low = (b & 0x0F);        // Get the low nibble (4 least significant bits)
        
        buf.append(hexChars[high]);   // Append the character corresponding to the high nibble
        buf.append(hexChars[low]);    // Append the character corresponding to the low nibble
    }
}
