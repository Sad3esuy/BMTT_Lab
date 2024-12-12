/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package B5;
import java.util.Arrays;
/**
 *
 * @author Administrator
 */
public class TranspositionCipher {
public String encrypt(String text, int[] key) {
    // Số dòng trong grid
    int numRows = (int) Math.ceil((double) text.length() / key.length);
    
    // Tạo grid với số dòng là numRows và số cột là chiều dài của key
    char[][] grid = new char[numRows][key.length];
    
    // Điền giá trị vào grid, các ký tự từ text sẽ được phân phối vào grid theo chiều dọc
    for (char[] row : grid) {
        Arrays.fill(row, ' '); // Điền ' ' vào grid
    }
    
    for (int i = 0; i < text.length(); i++) {
        grid[i / key.length][i % key.length] = text.charAt(i); // Phân phối ký tự vào grid
    }
    
    // Tạo StringBuilder để xây dựng ciphertext
    StringBuilder ciphertext = new StringBuilder();
    
    // Lặp qua key để lấy các chỉ số cột trong grid
    for (int k : key) {
        for (int row = 0; row < numRows; row++) {
            if (grid[row][k - 1] != ' ') { // Kiểm tra nếu không phải là ký tự trống
                ciphertext.append(grid[row][k - 1]);
            }
        }
    }
    
    // Trả về ciphertext đã mã hóa
    return ciphertext.toString();
}
public String decrypt(String text, int[] key) {
    // Số dòng trong grid
    int numRows = (int) Math.ceil((double) text.length() / key.length);
    
    // Tạo grid với số dòng là numRows và số cột là chiều dài của key
    char[][] grid = new char[numRows][key.length];
    
    // Điền giá trị vào grid, các ký tự từ text sẽ được phân phối vào grid theo chiều dọc
    for (char[] row : grid) {
        Arrays.fill(row, ' '); // Điền ' ' vào grid
    }
    
    int textIndex = 0;
    
    // Lặp qua key để điền các ký tự vào grid
    for (int k : key) {
        for (int row = 0; row < numRows; row++) {
            if (textIndex < text.length()) {
                grid[row][k - 1] = text.charAt(textIndex++); // Điền ký tự vào grid
            }
        }
    }
    
    // Tạo StringBuilder để xây dựng plaintext
    StringBuilder plaintext = new StringBuilder();
    
    // Đọc các ký tự từ grid theo thứ tự dòng và cột
    for (int row = 0; row < numRows; row++) {
        for (int col = 0; col < key.length; col++) {
            if (grid[row][col] != ' ') { // Kiểm tra nếu không phải ký tự trống
                plaintext.append(grid[row][col]);
            }
        }
    }
    
    // Trả về plaintext đã giải mã
    return plaintext.toString();
}

   
}
