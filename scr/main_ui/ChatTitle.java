package main_ui;

import entity.TaiKhoan;
import event.PublicEvent;
import java.awt.Color;

public class ChatTitle extends javax.swing.JPanel {

    private TaiKhoan user;
    public ChatTitle() {
        initComponents();
    }

    public void setUserName(TaiKhoan user){
        lbName.setText(user.getUsername());
        this.user = user;
        setStatus(user);
    }
    
    public TaiKhoan getUser(){
        return this.user;
    }
    
    public void updateUser(TaiKhoan user){
        if(this.user == user){
            setUserName(user);
            setStatus(user);
        }
    }

    public void setStatus(TaiKhoan user) {
        if(user.getTrangThai() == 1){
            lblStatus.setText("Active now");
            lblStatus.setForeground(new java.awt.Color(40, 147, 59));
        }else{
             lblStatus.setText("Offline");
             lblStatus.setForeground(new Color(160, 160, 160));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lbName = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();

        layer.setLayout(new java.awt.GridLayout(0, 1));

        lbName.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(66, 66, 66));
        lbName.setText("Name");
        layer.add(lbName);

        lblStatus.setForeground(new java.awt.Color(40, 147, 59));
        lblStatus.setText("Active now");
        layer.add(lblStatus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(456, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(layer, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
