package forSubmitOnly;

import entity.TaiKhoan;
import forSubmitOnly.main_user_ui;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class LoginUI extends javax.swing.JFrame {

    /**
     * Creates new form login_1
     */
    private String username;

    public LoginUI() {
        initComponents();
        init();
    }

    public final void init() {

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        forgetPassButton = new javax.swing.JLabel();
        logInButton = new javax.swing.JButton();
        signUpButton = new javax.swing.JButton();
        javax.swing.JLabel errorPanel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-account-96.png"))); // NOI18N
        jLabel1.setText("Đăng nhập");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel3.setText("Mật khẩu");

        usernameTextField.setText("bebaoboy@gmail.com");

        forgetPassButton.setForeground(new java.awt.Color(0, 204, 255));
        forgetPassButton.setText("Quên mật khẩu");
        forgetPassButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetPassButtonMouseClicked(evt);
            }
        });

        logInButton.setText("Đăng nhập");
        logInButton.setName("logInButton"); // NOI18N
        logInButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logInButtonMouseClicked(evt);
            }
        });
        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });

        signUpButton.setText("Đăng ký");
        signUpButton.setName("logInButton"); // NOI18N
        signUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signUpButtonMouseClicked(evt);
            }
        });
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });

        passwordTextField.setText("1234567");
        passwordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(errorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(logInButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(forgetPassButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(passwordTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(forgetPassButton)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logInButton)
                    .addComponent(signUpButton))
                .addGap(78, 78, 78)
                .addComponent(errorPanel)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logInButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInButtonMouseClicked

    }//GEN-LAST:event_logInButtonMouseClicked

    private void signUpButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpButtonMouseClicked
        this.dispose();
    }//GEN-LAST:event_signUpButtonMouseClicked

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        this.dispose();
        new RegisterUI().setVisible(true);
    }//GEN-LAST:event_signUpButtonActionPerformed

    private void forgetPassButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetPassButtonMouseClicked
        this.dispose();
        new ForgetPassUI().setVisible(true);
    }//GEN-LAST:event_forgetPassButtonMouseClicked

    private void passwordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextFieldActionPerformed

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInButtonActionPerformed
        var email = usernameTextField.getText();
        String password = String.valueOf(passwordTextField.getPassword());

        TaiKhoan acc = new TaiKhoan("", password, email.toLowerCase());
        JOptionPane.showConfirmDialog(rootPane, "Đăng nhập thành công!", "Thông báo", JOptionPane.CLOSED_OPTION);
        this.dispose();
        new main_user_ui(acc).setVisible(true);
    }//GEN-LAST:event_logInButtonActionPerformed

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
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginUI().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel forgetPassButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logInButton;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JButton signUpButton;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
