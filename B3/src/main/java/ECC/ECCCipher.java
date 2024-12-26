/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ECC;
import java.security.*;
import java.security.spec.*;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.IESParameterSpec;
/**
 *
 * @author nguye
 */
public class ECCCipher {
     // Định nghĩa các hằng số
    private static final String EC_ALGORITHM = "EC";
    private static final String ECC_CIPHER_ALGORITHM = "ECIES";

    // Thêm BouncyCastle Provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // Phương thức tạo cặp khóa
    public KeyPair generateKeyPair() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, NoSuchProviderException {
        // Tạo đối tượng KeyPairGenerator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EC_ALGORITHM, "BC");

        // Khai báo tham số EC với curve secp256r1
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");

        // Khởi tạo keyPairGenerator với tham số EC và SecureRandom
        keyPairGenerator.initialize(ecSpec, new SecureRandom());

        // Tạo và trả về cặp khóa
        return keyPairGenerator.generateKeyPair();
    }   
        // Phương thức mã hóa
    public byte[] encrypt(String plaintext, PublicKey publicKey) throws Exception {
        // Tạo đối tượng Cipher với thuật toán ECIES
        Cipher cipher = Cipher.getInstance(ECC_CIPHER_ALGORITHM, "BC");
        
        // Tạo nonce ngẫu nhiên
        byte[] nonce = new byte[16]; // sử dụng nonce ngẫu nhiên
        SecureRandom random = new SecureRandom();
        random.nextBytes(nonce);
        
        // Khởi tạo tham số IES với nonce
        IESParameterSpec params = new IESParameterSpec(null, null, 128, 128, nonce);
        
        // Khởi tạo cipher cho mã hóa
        cipher.init(Cipher.ENCRYPT_MODE, publicKey, params);
        
        // Mã hóa văn bản
        return cipher.doFinal(plaintext.getBytes());
    }

    // Phương thức giải mã
    public String decrypt(byte[] ciphertext, PrivateKey privateKey) throws Exception {
        // Tạo đối tượng Cipher với thuật toán ECIES
        Cipher cipher = Cipher.getInstance(ECC_CIPHER_ALGORITHM, "BC");
        
        // Tạo nonce ngẫu nhiên
        byte[] nonce = new byte[16]; // sử dụng nonce ngẫu nhiên
        SecureRandom random = new SecureRandom();
        random.nextBytes(nonce);
        
        // Khởi tạo tham số IES với nonce
        IESParameterSpec params = new IESParameterSpec(null, null, 128, 128, nonce);
        
        // Khởi tạo cipher cho giải mã
        cipher.init(Cipher.DECRYPT_MODE, privateKey, params);
        
        // Giải mã ciphertext
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        
        // Trả về văn bản đã giải mã
        return new String(decryptedBytes);
    }

    // Phương thức load PublicKey từ mảng byte
    public static PublicKey loadPublicKey(byte[] keyBytes) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(EC_ALGORITHM, "BC");
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(publicKeySpec);
    }

    // Phương thức load PrivateKey từ mảng byte
    public static PrivateKey loadPrivateKey(byte[] keyBytes) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(EC_ALGORITHM, "BC");
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        return keyFactory.generatePrivate(privateKeySpec);
    }
}
