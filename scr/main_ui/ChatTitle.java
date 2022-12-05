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
        layerSearch = new javax.swing.JLayeredPane();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        layer.setLayout(new java.awt.BorderLayout());

        lbName.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(66, 66, 66));
        lbName.setText("bebaoboy");
        layer.add(lbName, java.awt.BorderLayout.PAGE_START);

        layerSearch.setLayout(new javax.swing.BoxLayout(layerSearch, javax.swing.BoxLayout.LINE_AXIS));

        jTextField1.setToolTipText("Nhập tin nhắn");
        jTextField1.setPreferredSize(new java.awt.Dimension(50, 26));
        layerSearch.add(jTextField1);

        jButton1.setText("Tìm kiếm");
        layerSearch.add(jButton1);

        lblStatus.setForeground(new java.awt.Color(40, 147, 59));
        lblStatus.setText("Active now");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(354, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                    .addComponent(layerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(82, 82, 82)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(layer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(layerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(9, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLayeredPane layerSearch;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
