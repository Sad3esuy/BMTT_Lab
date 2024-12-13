/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TwoFish;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author nguye
 */
public class TwofishCipher {
    // Kích thước khóa trong byte (Twofish hỗ trợ 128-bit, 192-bit, và 256-bit khóa)
    private static final int KEY_SIZE = 16; // 128-bit key size

    // Đối tượng mã hóa Twofish với chế độ CBC
    private BlockCipher cipher = new CBCBlockCipher(new TwofishEngine());

    /**
     * Mã hóa văn bản với Twofish
     * 
     * @param plaintext Văn bản cần mã hóa
     * @param key Khóa mã hóa (có thể là 16, 24, hoặc 32 byte)
     * @param iv Vector khởi tạo (IV) cho chế độ CBC
     * @return Mảng byte chứa văn bản mã hóa
     * @throws Exception Nếu có lỗi trong quá trình mã hóa
     */
    public byte[] encrypt(String plaintext, byte[] key, byte[] iv) throws Exception {
        if (key.length != KEY_SIZE) {
            throw new IllegalArgumentException("Key size must be 128 bits (16 bytes)");
        }
        
        // Khởi tạo cipher với padding và IV
        PaddedBufferedBlockCipher paddedCipher = new PaddedBufferedBlockCipher(cipher);
        KeyParameter keyParameter = new KeyParameter(key);
        ParametersWithIV parametersWithIV = new ParametersWithIV(keyParameter, iv);
        paddedCipher.init(true, parametersWithIV);  // true cho mã hóa
        
        // Chuyển plaintext thành mảng byte
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        
        // Tính toán kích thước output và mã hóa
        int outputSize = paddedCipher.getOutputSize(plaintextBytes.length);
        byte[] outBuf = new byte[outputSize];
        int length1 = paddedCipher.processBytes(plaintextBytes, 0, plaintextBytes.length, outBuf, 0);
        int length2 = paddedCipher.doFinal(outBuf, length1);

        // Kết hợp kết quả vào mảng byte ciphertext
        byte[] ciphertext = new byte[length1 + length2];
        System.arraycopy(outBuf, 0, ciphertext, 0, ciphertext.length);
        return ciphertext;
    }

    /**
     * Giải mã văn bản đã mã hóa với Twofish
     * 
     * @param ciphertext Mảng byte chứa văn bản mã hóa
     * @param key Khóa mã hóa (có thể là 16, 24, hoặc 32 byte)
     * @param iv Vector khởi tạo (IV) cho chế độ CBC
     * @return Văn bản đã giải mã
     * @throws Exception Nếu có lỗi trong quá trình giải mã
     */
    public String decrypt(byte[] ciphertext, byte[] key, byte[] iv) throws Exception {
        if (key.length != KEY_SIZE) {
            throw new IllegalArgumentException("Key size must be 128 bits (16 bytes)");
        }
        
        // Khởi tạo cipher với padding và IV
        PaddedBufferedBlockCipher paddedCipher = new PaddedBufferedBlockCipher(cipher);
        KeyParameter keyParameter = new KeyParameter(key);
        ParametersWithIV parametersWithIV = new ParametersWithIV(keyParameter, iv);
        paddedCipher.init(false, parametersWithIV);  // false cho giải mã

        // Giải mã ciphertext thành mảng byte
        int outputSize = paddedCipher.getOutputSize(ciphertext.length);
        byte[] outBuf = new byte[outputSize];
        int length1 = paddedCipher.processBytes(ciphertext, 0, ciphertext.length, outBuf, 0);
        int length2 = paddedCipher.doFinal(outBuf, length1);

        // Kết hợp kết quả vào mảng byte plaintext
        byte[] plaintextBytes = new byte[length1 + length2];
        System.arraycopy(outBuf, 0, plaintextBytes, 0, plaintextBytes.length);

        // Chuyển mảng byte thành chuỗi văn bản
        return new String(plaintextBytes, StandardCharsets.UTF_8);
    }
}
