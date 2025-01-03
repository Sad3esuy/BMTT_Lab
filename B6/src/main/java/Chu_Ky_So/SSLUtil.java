/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chu_Ky_So;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Date;
/**
 *
 * @author nguye
 */
public class SSLUtil {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    } 
        // Method to generate a self-signed certificate
    public static X509Certificate generateSelfSignedCertificate(KeyPair keyPair, String dn) throws Exception {
        long now = System.currentTimeMillis();
        Date startDate = new Date(now);
        X500Name dnName = new X500Name(dn);
        BigInteger certSerialNumber = new BigInteger(Long.toString(now));
        Date endDate = new Date(now + 365L * 24L * 60L * 60L * 1000L); // 1 year validity

        ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256WithRSA").build(keyPair.getPrivate());
        
        X509v3CertificateBuilder certificateBuilder = new JcaX509v3CertificateBuilder(
                dnName, certSerialNumber, startDate, endDate, dnName, keyPair.getPublic());

        return new JcaX509CertificateConverter()
                .setProvider("BC")
                .getCertificate(certificateBuilder.build(contentSigner));
    }

    // Method to convert a certificate to PEM format
    public static String convertToPEM(X509Certificate certificate) throws Exception {
        StringBuilder pem = new StringBuilder();
        pem.append("-----BEGIN CERTIFICATE-----\n");
        pem.append(new String(java.util.Base64.getMimeEncoder().encode(certificate.getEncoded())));
        pem.append("\n-----END CERTIFICATE-----");
        return pem.toString();
    }
}
