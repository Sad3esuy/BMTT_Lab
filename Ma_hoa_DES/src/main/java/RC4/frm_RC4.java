/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package RC4;
import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author nguye
 */
public class frm_RC4 extends javax.swing.JFrame {
    private RC4Cipher rc4;  // Khai báo đối tượng RC4Cipher
    /**
     * Creates new form frm_DES
     */
    public frm_RC4() {
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
        btnMaHoa = new javax.swing.JButton();
        txtFKey = new javax.swing.JTextField();
        btnLuuFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtABanRo = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtABanMa = new javax.swing.JTextArea();
        btnGiaiMa = new javax.swing.JButton();
        btnMoFile = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MA HOA RC4 DEMO");

        btnMaHoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMaHoa.setText("Mã hoá");
        btnMaHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaHoaActionPerformed(evt);
            }
        });

        btnLuuFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuuFile.setText("Write");
        btnLuuFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuFileActionPerformed(evt);
            }
        });

        txtABanRo.setColumns(20);
        txtABanRo.setRows(5);
        jScrollPane1.setViewportView(txtABanRo);

        txtABanMa.setColumns(20);
        txtABanMa.setRows(5);
        jScrollPane2.setViewportView(txtABanMa);

        btnGiaiMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGiaiMa.setText("Giải mã");
        btnGiaiMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaiMaActionPerformed(evt);
            }
        });

        btnMoFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMoFile.setText("Hide");
        btnMoFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoFileActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Key:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Bản rõ:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Bản mã:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(txtFKey)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMaHoa)
                                .addGap(102, 102, 102)
                                .addComponent(btnGiaiMa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                .addComponent(btnMoFile)
                                .addGap(93, 93, 93)
                                .addComponent(btnLuuFile))
                            .addComponent(jScrollPane1))))
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFKey, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMaHoa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMoFile)
                        .addComponent(btnLuuFile)
                        .addComponent(btnGiaiMa)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuFileActionPerformed
        String ciphertext = txtABanMa.getText();  // Lấy văn bản mã hóa từ giao diện người dùng.
        JFileChooser fileChooser = new JFileChooser();  // Mở hộp thoại chọn file.
        fileChooser.setDialogTitle("Save Ciphertext to File");
        int userSelection = fileChooser.showSaveDialog(this);  // Hiển thị hộp thoại và nhận lựa chọn của người dùng.

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();  // Lấy file người dùng chọn.
            try (FileWriter writer = new FileWriter(fileToSave.getAbsolutePath() + ".txt")) {  // Ghi nội dung vào file với phần mở rộng .txt
                writer.write(ciphertext);  // Ghi nội dung ciphertext vào file.
                JOptionPane.showMessageDialog(this, "Ciphertext saved to file successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);  // Hiển thị lỗi nếu có.
            }
        }
    }//GEN-LAST:event_btnLuuFileActionPerformed

    private void btnMaHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaHoaActionPerformed
        String plaintext = txtABanRo.getText();  // Lấy văn bản cần mã hóa từ TextField
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);  // Chuyển văn bản thành mảng byte

        String key = txtFKey.getText();  // Lấy khóa từ TextField
        rc4 = new RC4Cipher(key.getBytes());  // Khởi tạo đối tượng RC4Cipher với khóa

        // Mã hóa văn bản
        byte[] ciphertextBytes = rc4.encrypt(plaintextBytes);
        String ciphertext = bytesToHexString(ciphertextBytes);  // Chuyển mảng byte thành chuỗi hex
        txtABanMa.setText(ciphertext);  // Hiển thị kết quả mã hóa
    }//GEN-LAST:event_btnMaHoaActionPerformed

    private void btnGiaiMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaiMaActionPerformed
        String ciphertext = txtABanMa.getText();  // Lấy chuỗi mã hóa từ TextField
        byte[] ciphertextBytes = hexStringToByteArray(ciphertext);  // Chuyển chuỗi hex thành mảng byte

        String key = txtFKey.getText();  // Lấy khóa từ TextField
        rc4 = new RC4Cipher(key.getBytes());  // Khởi tạo đối tượng RC4Cipher với khóa

        // Giải mã văn bản
        byte[] decryptedBytes = rc4.decrypt(ciphertextBytes);
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);  // Chuyển mảng byte thành văn bản
        txtABanRo.setText(decryptedText);  // Hiển thị kết quả giải mã
    }//GEN-LAST:event_btnGiaiMaActionPerformed

    // Phương thức chuyển đổi mảng byte thành chuỗi hex
    private String bytesToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));  // Chuyển mỗi byte thành chuỗi hex
        }
        return hexString.toString();
    }

    // Phương thức chuyển đổi chuỗi hex thành mảng byte
    private byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
    private void btnMoFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();  // Mở hộp thoại chọn file.
        fileChooser.setDialogTitle("Open File containing Ciphertext");
        int userSelection = fileChooser.showOpenDialog(this);  // Hiển thị hộp thoại và nhận lựa chọn của người dùng.

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();  // Lấy file người dùng chọn.
            try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {  // Đọc nội dung từ file.
                StringBuilder ciphertextBuilder = new StringBuilder();  // Dùng StringBuilder để nối các dòng.
                String line;
                while ((line = reader.readLine()) != null) {
                    ciphertextBuilder.append(line);  // Nối các dòng lại với nhau.
                }
                String ciphertext = ciphertextBuilder.toString().trim();  // Lấy ciphertext từ StringBuilder.
                txtABanMa.setText(ciphertext);  // Hiển thị ciphertext vào giao diện người dùng.
                txtABanRo.setText("");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);  // Hiển thị lỗi nếu có.
            }
        }
    }//GEN-LAST:event_btnMoFileActionPerformed

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
            java.util.logging.Logger.getLogger(frm_RC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_RC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_RC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_RC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_RC4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiaiMa;
    private javax.swing.JButton btnLuuFile;
    private javax.swing.JButton btnMaHoa;
    private javax.swing.JButton btnMoFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtABanMa;
    private javax.swing.JTextArea txtABanRo;
    private javax.swing.JTextField txtFKey;
    // End of variables declaration//GEN-END:variables
}
