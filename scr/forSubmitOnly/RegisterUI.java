/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forSubmitOnly;

import database.DAO_TaiKhoan;
import database.DAO_TinNhan;
import entity.TaiKhoan;
import event.EventRegister;
import event.PublicEvent;
import java.awt.Color;
import java.awt.GridLayout;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Properties;
import javax.swing.JOptionPane;
import main_ui.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

/**
 *
 * @author ADMIN
 */
public class RegisterUI extends javax.swing.JFrame {

    /**
     * Creates new form register1
     */
    public RegisterUI() {
        this.model = new SqlDateModel();
        model.setDate(2002, 0, 1);
        model.setSelected(true);
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBackground(Color.white);
        initComponents();
        jDatePanel.setLayout(new GridLayout(1, 5));
        jDatePanel.add(datePicker);
        jDatePanel.revalidate();
        jDatePanel.repaint();

        init();

    }

    public void init() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        addressTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        femaleRadioButton = new javax.swing.JRadioButton();
        maleRadioButton = new javax.swing.JRadioButton();
        signUpButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        goback = new javax.swing.JButton();
        jDatePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-sign-up-96.png"))); // NOI18N
        jLabel1.setText("Tài khoản mới");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Email");

        emailTextField.setText("maithikimthanh@gmail.com");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel3.setText("Username");

        usernameTextField.setText("kimthanh");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Mật khẩu");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("Địa chỉ");

        addressTextField.setText("1 Lê Lợi Q5");
        addressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTextFieldActionPerformed(evt);
            }
        });

        passwordTextField.setText("kimthanh123");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel6.setText("Giới tính");

        buttonGroup1.add(femaleRadioButton);
        femaleRadioButton.setSelected(true);
        femaleRadioButton.setText("Nữ");

        buttonGroup1.add(maleRadioButton);
        maleRadioButton.setText("Nam");
        maleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleRadioButtonActionPerformed(evt);
            }
        });

        signUpButton.setText("Đăng ký");
        signUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signUpButtonMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel7.setText("Ngày sinh");

        goback.setText("Quay lại");
        goback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gobackActionPerformed(evt);
            }
        });

        jDatePanel.setBackground(new java.awt.Color(255, 255, 255));
        jDatePanel.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(goback, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(237, 237, 237))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(47, 47, 47)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(usernameTextField)
                            .addComponent(emailTextField)
                            .addComponent(addressTextField)
                            .addComponent(passwordTextField)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(maleRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                        .addGap(51, 51, 51))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(femaleRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(136, 136, 136))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(femaleRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maleRadioButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(signUpButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(goback))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void maleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maleRadioButtonActionPerformed

    private void addressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTextFieldActionPerformed

    private void signUpButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpButtonMouseClicked
        var input = JOptionPane.showConfirmDialog(rootPane, "Xác nhận tạo tài khoản?", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            Date selectedDate = (java.sql.Date) datePicker.getModel().getValue();
            var user = new TaiKhoan(
                    usernameTextField.getText(),
                    String.valueOf(passwordTextField.getPassword()),
                    emailTextField.getText())
                    .setNgaySinh(selectedDate)
                    .setGioiTinh(!maleRadioButton.isSelected())
                    .setDiaChi(new String(addressTextField.getText().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                    .setTrangThai(0);
            JOptionPane.showConfirmDialog(rootPane, "Hoàn tất đăng ký tài khoản", "Thông báo", JOptionPane.CLOSED_OPTION);
            this.dispose();
            new LoginUI().setVisible(true);
        }

    }//GEN-LAST:event_signUpButtonMouseClicked

    private void gobackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gobackActionPerformed
        this.dispose();
        new LoginUI().setVisible(true);
    }//GEN-LAST:event_gobackActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new RegisterUI().setVisible(true);
            }
        });
    }

    SqlDateModel model;
    //model.setDate(20,04,2014);
    // Need this...
    Properties p = new Properties();

    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.JButton goback;
    private javax.swing.JPanel jDatePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton maleRadioButton;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JButton signUpButton;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
