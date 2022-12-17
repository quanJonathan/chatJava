/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package authentication_ui;

import entity.IDPrefix;
import event.EventForgetPass;
import event.PublicEvent;
import java.awt.CardLayout;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import service_client.Service;

/**
 *
 * @author ADMIN
 */
class MailConfig {

    public static final String HOST_NAME = "smtp.gmail.com";

    public static final int SSL_PORT = 465; // Port for SSL
    public static final int TSL_PORT = 587; // Port for TLS/STARTTLS
    public static final String APP_EMAIL = "bebaoboy@gmail.com"; // your email
    public static final String APP_PASSWORD = "pmhgxmxbnmonqwik"; // your password
}

public class ForgetPassUI extends javax.swing.JFrame {

    String currentID = "";

    /**
     * Creates new form forgot_password_1
     */
    public ForgetPassUI() {
        initComponents();
        cardLayout = (CardLayout) jPanel2.getLayout();
        cardLayout.show(jPanel2, "formCard");
        setLocationRelativeTo(null);
        init();
    }

    public void init() {
        PublicEvent.getInstance().addEventForgetPass(new EventForgetPass() {
            @Override
            public void sendPasswordResetMail(String email, String username) {
                service_client.Service.getInstance().run();
                 if (!Service.getInstance().isServerRunning()) {
                    JOptionPane.showMessageDialog(rootPane, "Server is not available!");
                    dispose();
                    return;
                }
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.host", MailConfig.HOST_NAME);
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.port", MailConfig.TSL_PORT);
                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
                    }
                });
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                    message.setSubject("Reset Password from ChatJava");
                    var code = IDPrefix.getIDMatKhau();
                    sendCode(username, code);
                    message.setText("Warning: DON'T share this passcode to anyone!\nYour username is " + username + "\nYour passcode is: " + code + ".\n"
                            + " Please enter this passcode in the application to reset your password!");
                    Transport.send(message);
                    System.out.println("Message sent successfully");
                } catch (MessagingException e) {

                } finally {
                    JOptionPane.showConfirmDialog(rootPane, "Vui lòng kiểm tra và nhập mã code đã gửi tới email\n" + email, "Thông báo", JOptionPane.CLOSED_OPTION);
                    cardLayout.show(jPanel2, "codeCard");
                }
            }

            @Override
            public void goback() {
                new LoginUI().setVisible(true);
            }

            @Override
            public void sendCode(String username, String code) {
                service_client.Service.getInstance().al.sendCommand("/sendPasswordResetCode", new JSONObject().put("user", username).put("code", code));
            }

            @Override
            public void codeChecked(String id, boolean checked) {
                if (currentID.equals(id)) {
                    if (checked) {
                        JOptionPane.showConfirmDialog(rootPane, "Bạn đã nhập đúng code!", "Thông báo", JOptionPane.CLOSED_OPTION);
                        cardLayout.show(jPanel2, "resetCard");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Bạn đã nhập SAI code! Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.CLOSED_OPTION);
                    }
                }
            }

            @Override
            public void sendEnterCode(String username, String enterCode) {
                service_client.Service.getInstance().al.sendCommand("/checkPasswordResetCode", new JSONObject()
                        .put("user", username)
                        .put("code", enterCode)
                        .put("id", !currentID.isEmpty() ? currentID : ""));
            }

            @Override
            public void setID(String id) {
                currentID = id;
            }

            @Override
            public void sendPassword(String username, String password) {
                service_client.Service.getInstance().al.sendCommand("/resetPass", new JSONObject()
                        .put("user", username)
                        .put("password", password));
            }

            @Override
            public void checkResult(boolean b) {
                if (b) {
                    JOptionPane.showConfirmDialog(rootPane, "Đặt lại mật khẩu thành công!", "Thông báo", JOptionPane.CLOSED_OPTION);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra", "Thông báo", JOptionPane.CLOSED_OPTION);

                }
                cardLayout.show(jPanel2, "formCard");
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        formPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        emailText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        usernameText = new javax.swing.JTextField();
        emailSendingButton = new javax.swing.JButton();
        goback = new javax.swing.JButton();
        codePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        continueButton = new javax.swing.JButton();
        goback2 = new javax.swing.JButton();
        resetPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNewPass = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNewPassAgain = new javax.swing.JTextField();
        doneButton = new javax.swing.JButton();
        goback1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-forgot-password-96.png"))); // NOI18N
        jLabel1.setText("Đặt lại mật khẩu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setLayout(new java.awt.CardLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel3.setText("Username");

        emailText.setText("bebaoboy2@gmail.com");
        emailText.setToolTipText("Enter email");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Email");

        usernameText.setToolTipText("Enter username");

        emailSendingButton.setText("Gửi email đặt lại mật khẩu");
        emailSendingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailSendingButtonActionPerformed(evt);
            }
        });

        goback.setText("Quay lại");
        goback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gobackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(emailText)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(usernameText)
                        .addComponent(emailSendingButton))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(goback, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(emailSendingButton)
                .addGap(18, 18, 18)
                .addComponent(goback)
                .addContainerGap())
        );

        jPanel2.add(formPanel, "formCard");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel6.setText("Nhập mã xác nhận:");

        txtCode.setToolTipText("Enter username");

        continueButton.setText("Tiếp tục");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        goback2.setText("Quay lại");
        goback2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goback2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout codePanelLayout = new javax.swing.GroupLayout(codePanel);
        codePanel.setLayout(codePanelLayout);
        codePanelLayout.setHorizontalGroup(
            codePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(codePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(codePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCode)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addGroup(codePanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(codePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(goback2, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(continueButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        codePanelLayout.setVerticalGroup(
            codePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(codePanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(continueButton)
                .addGap(18, 18, 18)
                .addComponent(goback2)
                .addContainerGap())
        );

        jPanel2.add(codePanel, "codeCard");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Vui lòng nhập lại:");

        txtNewPass.setToolTipText("Enter email");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("Nhập mật khẩu mới:");

        txtNewPassAgain.setToolTipText("Enter username");

        doneButton.setText("Hoàn tất");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        goback1.setText("Quay lại");
        goback1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goback1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout resetPanelLayout = new javax.swing.GroupLayout(resetPanel);
        resetPanel.setLayout(resetPanelLayout);
        resetPanelLayout.setHorizontalGroup(
            resetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNewPass)
                        .addComponent(txtNewPassAgain)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(resetPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(resetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(goback1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(doneButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        resetPanelLayout.setVerticalGroup(
            resetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNewPassAgain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(doneButton)
                .addGap(18, 18, 18)
                .addComponent(goback1)
                .addContainerGap())
        );

        jPanel2.add(resetPanel, "resetCard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gobackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gobackActionPerformed
        this.dispose();
        PublicEvent.getInstance().getEventForgetPass().goback();
    }//GEN-LAST:event_gobackActionPerformed

    private void emailSendingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailSendingButtonActionPerformed
        var email = emailText.getText();
        var username = usernameText.getText();
        var input = JOptionPane.showConfirmDialog(rootPane, "Xác nhận email đặt lại mật khẩu?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            PublicEvent.getInstance().getEventForgetPass().sendPasswordResetMail(email, username);
        }
    }//GEN-LAST:event_emailSendingButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        if (!txtNewPass.getText().equals(txtNewPassAgain.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Mật khẩu nhập lại không đúng.", "Thông báo", JOptionPane.CLOSED_OPTION);
            return;
        }

        var input = JOptionPane.showConfirmDialog(rootPane, "Xác nhận đặt lại mật khẩu?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
        if (input == JOptionPane.OK_OPTION) {
            PublicEvent.getInstance().getEventForgetPass().sendPassword(usernameText.getText(), txtNewPass.getText());
        }
    }//GEN-LAST:event_doneButtonActionPerformed

    private void goback1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goback1ActionPerformed
        var input = JOptionPane.showConfirmDialog(rootPane, "Hủy nhận đặt lại mật khẩu?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        if (input == JOptionPane.OK_OPTION) {
            cardLayout.show(jPanel2, "formCard");
        }
    }//GEN-LAST:event_goback1ActionPerformed

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        PublicEvent.getInstance().getEventForgetPass().sendEnterCode(usernameText.getText(), txtCode.getText());
    }//GEN-LAST:event_continueButtonActionPerformed

    private void goback2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goback2ActionPerformed
        var input = JOptionPane.showConfirmDialog(rootPane, "Hủy nhận đặt lại mật khẩu?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        if (input == JOptionPane.OK_OPTION) {
            cardLayout.show(jPanel2, "formCard");
        }
    }//GEN-LAST:event_goback2ActionPerformed

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
            java.util.logging.Logger.getLogger(ForgetPassUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgetPassUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgetPassUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgetPassUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgetPassUI().setVisible(true);
            }
        });
    }

    CardLayout cardLayout;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel codePanel;
    private javax.swing.JButton continueButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JButton emailSendingButton;
    private javax.swing.JTextField emailText;
    private javax.swing.JPanel formPanel;
    private javax.swing.JButton goback;
    private javax.swing.JButton goback1;
    private javax.swing.JButton goback2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel resetPanel;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtNewPass;
    private javax.swing.JTextField txtNewPassAgain;
    private javax.swing.JTextField usernameText;
    // End of variables declaration//GEN-END:variables
}
