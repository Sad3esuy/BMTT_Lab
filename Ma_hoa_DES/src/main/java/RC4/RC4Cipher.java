/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC4;

/**
 *
 * @author nguye
 */
public class RC4Cipher {
    private byte[] S = new byte[256];  // Mảng trạng thái S
    private int x = 0;  // Chỉ số X trong thuật toán RC4
    private int y = 0;  // Chỉ số Y trong thuật toán RC4

    // Hàm khởi tạo RC4 với khóa byte[] key
    public RC4Cipher(byte[] key) {
        int keyLength = key.length;
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;  // Khởi tạo mảng S với các giá trị từ 0 đến 255
        }

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + key[i % keyLength]) & 0xFF;  // Thực hiện quá trình trộn khóa vào S
            swap(S, i, j);  // Hoán đổi vị trí trong mảng S
        }
    }

    // Hàm hoán đổi hai phần tử trong mảng
    private void swap(byte[] arr, int i, int j) {
        byte temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Hàm mã hóa dữ liệu
    public byte[] encrypt(byte[] plaintext) {
        byte[] ciphertext = new byte[plaintext.length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < plaintext.length; k++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            swap(S, i, j);  // Hoán đổi trong mảng S
            int t = (S[i] + S[j]) & 0xFF;
            ciphertext[k] = (byte) (plaintext[k] ^ S[t]);  // Xử lý mã hóa XOR
        }
        return ciphertext;
    }

    // Hàm giải mã dữ liệu (RC4 là mã hóa đối xứng, vì vậy giải mã giống mã hóa)
    public byte[] decrypt(byte[] ciphertext) {
        return encrypt(ciphertext);  // Mã hóa và giải mã giống nhau trong RC4
    }
}
