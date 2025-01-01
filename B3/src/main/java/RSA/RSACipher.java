/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RSA;
import java.math.BigInteger;
import java.util.Random;
/**
 *
 * @author nguye
 */
public class RSACipher {
    private BigInteger p, q, N, phi, E, D;
    private int bitlength;

    // Constructor to initialize bitlength
    public RSACipher(int bitLength) {
        this.bitlength = bitLength;
        generateKeys();
    }

    // Method to generate RSA keys (public and private)
    private void generateKeys() {
        // Generate two large prime numbers, p and q
        p = BigInteger.probablePrime(bitlength / 2, new Random());
        q = BigInteger.probablePrime(bitlength / 2, new Random());

        // N is the modulus used in both the public and private keys
        N = p.multiply(q);

        // Euler's totient function, phi(N) = (p-1)*(q-1)
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Generate public key E (usually a small prime number)
        E = BigInteger.probablePrime(bitlength / 2, new Random());

        // Ensure E is coprime with phi(N) and 1 < E < phi(N)
        while (E.compareTo(BigInteger.ONE) <= 0 || E.compareTo(phi) >= 0 || E.gcd(phi).compareTo(BigInteger.ONE) != 0) {
            E = new BigInteger(bitlength / 2, new Random());
        }

        // Generate private key D such that (D * E) % phi(N) = 1
        D = E.modInverse(phi);
    }

    // Method to encrypt a message
    public BigInteger[] encrypt(String message) {
        byte[] bytes = message.getBytes();
        BigInteger[] encrypted = new BigInteger[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            encrypted[i] = new BigInteger(new byte[]{bytes[i]}).modPow(E, N);
        }

        return encrypted;
    }
        // Method to decrypt a message
    public String decrypt(BigInteger[] message, BigInteger d, BigInteger n) {
        byte[] bytes = new byte[message.length];

        for (int i = 0; i < message.length; i++) {
            // Decrypt each message using modPow(d, n) and convert it to a byte
            bytes[i] = message[i].modPow(d, n).byteValue();
        }

        // Convert decrypted byte array back to a string
        return new String(bytes);
    }

    // Getters for the public and private keys
    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getN() {
        return N;
    }

    public BigInteger getE() {
        return E;
    }

    public BigInteger getD() {
        return D;
    }
}
