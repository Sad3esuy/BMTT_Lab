/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DIFFIE_HELLMAN;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import java.security.InvalidKeyException;
/**
 *
 * @author nguye
 */
public class frm_Alice extends javax.swing.JFrame {

    /**
     * Creates new form frm_Alice
     */
    KeyAgreement aliceKeyAgree;
    PublicKey bobPubKey;
    SecretKey aliceDesKey;
    Cipher aliceCipher;

    public frm_Alice() {
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtAliceKey = new javax.swing.JTextField();
        txtBobKey = new javax.swing.JTextField();
        txtShare = new javax.swing.JTextField();
        txtEncryptShare = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnRun = new javax.swing.JButton();
        btnAliceGenerate = new javax.swing.JButton();
        btnBobDisplay = new javax.swing.JButton();
        btnMakeSec = new javax.swing.JButton();
        btnEncryptSecKey = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Alice");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Alice's key:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Bob's key:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Shared secret:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Encrypt Shared secret:");

        btnRun.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRun.setText("Run encrypt/decrypt form");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        btnAliceGenerate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAliceGenerate.setText("Alice's  key generate");
        btnAliceGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAliceGenerateActionPerformed(evt);
            }
        });

        btnBobDisplay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBobDisplay.setText("Bob's key display");
        btnBobDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBobDisplayActionPerformed(evt);
            }
        });

        btnMakeSec.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMakeSec.setText("Make secret key");
        btnMakeSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMakeSecActionPerformed(evt);
            }
        });

        btnEncryptSecKey.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEncryptSecKey.setText("Encrypt secret key");
        btnEncryptSecKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncryptSecKeyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtShare, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBobKey, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAliceKey, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEncryptShare, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAliceGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBobDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMakeSec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEncryptSecKey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(331, 331, 331)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAliceKey, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAliceGenerate))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBobKey, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBobDisplay)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel2)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtShare, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMakeSec)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel4)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEncryptShare, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEncryptSecKey))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(btnRun)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAliceGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAliceGenerateActionPerformed
        try {
            // Generate parameters for Diffie-Hellman
            AlgorithmParameterGenerator paramGen = AlgorithmParameterGenerator.getInstance("DH");
            paramGen.init(512);
            AlgorithmParameters params = paramGen.generateParameters();

            // Retrieve DHParameterSpec
            DHParameterSpec dhSkipParamSpec = (DHParameterSpec) params.getParameterSpec(DHParameterSpec.class);

            System.out.println("Generating a DH KeyPair...");

            // Initialize KeyPairGenerator for Diffie-Hellman
            KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
            aliceKpairGen.initialize(dhSkipParamSpec);

            // Generate Alice's KeyPair
            KeyPair aliceKpair = aliceKpairGen.generateKeyPair();

            if (aliceKpair == null || aliceKpair.getPrivate() == null || aliceKpair.getPublic() == null) {
                System.err.println("Error: Key pair generation failed");
                return;
            }

            System.out.println("Initializing the KeyAgreement Engine with DH private key");

            // Initialize the KeyAgreement engine
            aliceKeyAgree = KeyAgreement.getInstance("DH");  // Initialize the class-level variable
            try {
                aliceKeyAgree.init(aliceKpair.getPrivate());
            } catch (InvalidKeyException e) {
                System.err.println("Error initializing KeyAgreement: " + e.getMessage());
                return;
            }

            // Assuming Bob's public key (for demonstration purposes) 
            // You should have Bob's public key from a secure exchange process
            byte[] alicePubKeyEnc = aliceKpair.getPublic().getEncoded();

            // Generate shared secret
            try {
                // Example of performing the key agreement phase with a dummy second party (Bob's public key)
                // You should replace this with the actual shared key exchange process
                aliceKeyAgree.doPhase(aliceKpair.getPublic(), true);
                byte[] sharedSecret = aliceKeyAgree.generateSecret();
                System.out.println("Shared secret: " + java.util.Base64.getEncoder().encodeToString(sharedSecret));
            } catch (Exception e) {
                System.err.println("Error during key agreement phase: " + e.getMessage());
            }

            // Specify directory path for saving the public key
            java.nio.file.Path dirPath = Paths.get("src/week_04");

            // Create the directory if it doesn't exist
            if (!java.nio.file.Files.exists(dirPath)) {
                java.nio.file.Files.createDirectories(dirPath);
            }

            // Write Alice's public key to a file
            try (FileOutputStream fos = new FileOutputStream(dirPath.resolve("A.pub").toFile())) {
                fos.write(alicePubKeyEnc);
            }

            // Display Alice's public key in Base64 encoding
            txtAliceKey.setText(java.util.Base64.getEncoder().encodeToString(alicePubKeyEnc));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAliceGenerateActionPerformed

    private void btnBobDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBobDisplayActionPerformed
        try {
            byte[] bkeyp;
            // Read Bob's public key from file
            try (FileInputStream fis = new FileInputStream(Paths.get("src/week_04/B.pub").toFile())) {
                bkeyp = new byte[fis.available()];
                fis.read(bkeyp); // Read the public key data
            }
            // Convert the byte array to a Base64 string for display
            txtBobKey.setText(java.util.Base64.getEncoder().encodeToString(bkeyp));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading Bob's public key: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBobDisplayActionPerformed

    private void btnMakeSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMakeSecActionPerformed
        try {
            byte[] bobPubKeyEnc;
            try (FileInputStream fis = new FileInputStream(Paths.get("src/week_04/B.pub").toFile())) {
                bobPubKeyEnc = new byte[fis.available()];
                fis.read(bobPubKeyEnc);
            }

            KeyFactory aliceKeyFac = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(bobPubKeyEnc);
            bobPubKey = aliceKeyFac.generatePublic(x509KeySpec);

            System.out.println("Executing PHASE1 of key agreement...");
            aliceKeyAgree.doPhase(bobPubKey, true);

            byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();
            System.out.println("Shared secret: " + CryptoUtil.toHexString(aliceSharedSecret));

            txtShare.setText(CryptoUtil.toHexString(aliceSharedSecret));

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating shared secret: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnMakeSecActionPerformed

    private void btnEncryptSecKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncryptSecKeyActionPerformed
        try {
            // Perform the second phase of the key agreement
            aliceKeyAgree.doPhase(bobPubKey, true);

            // Generate the shared secret
            byte[] sharedSecret = aliceKeyAgree.generateSecret();
            txtEncryptShare.setText(CryptoUtil.toHexString(sharedSecret));

            // Hash the shared secret using SHA-256 and take the first 8 bytes for DES key
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] desKeyBytes = Arrays.copyOf(sha256.digest(sharedSecret), 8); // Use the first 8 bytes for DES key

            // Create the DES key
            SecretKeySpec desKeySpec = new SecretKeySpec(desKeyBytes, "DES");

            // Display the Base64 encoded DES key
            txtEncryptShare.setText(Base64.getEncoder().encodeToString(desKeySpec.getEncoded()));

            // Save the DES key to a file
            String fileName = "src/week_04/A.txt";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                bw.write(Base64.getEncoder().encodeToString(desKeySpec.getEncoded())); // Save Base64 encoded key to file
            }
            JOptionPane.showMessageDialog(this, "DES key saved to file successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error during encryption: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEncryptSecKeyActionPerformed

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        try {
            // Open the DES encryption/decryption form
            frm_DESCS des = new frm_DESCS();
            des.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error opening encryption/decryption form: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_Alice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Alice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Alice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Alice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Alice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAliceGenerate;
    private javax.swing.JButton btnBobDisplay;
    private javax.swing.JButton btnEncryptSecKey;
    private javax.swing.JButton btnMakeSec;
    private javax.swing.JButton btnRun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtAliceKey;
    private javax.swing.JTextField txtBobKey;
    private javax.swing.JTextField txtEncryptShare;
    private javax.swing.JTextField txtShare;
    // End of variables declaration//GEN-END:variables
}
