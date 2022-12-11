package UIObject;

import entity.TaiKhoan;
import event.EventChat;
import event.EventOnChatCard;
import event.PublicEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class ChatCard extends javax.swing.JPanel {

    private TaiKhoan user;
    public ChatCard(TaiKhoan user) {
        initComponents();
        this.user = user;
        lblUserName.setText(user.getUsername()); 
        setStatus();
    }
    
    public TaiKhoan getUser(){
        return this.user;
    }
    
    public final void setStatus(){
         if(user.getTrangThai() == 1){
            lblStatus.setText("Active now");
            lblStatus.setForeground(new java.awt.Color(40, 147, 59));
        }else{
             lblStatus.setText("Offline");
             lblStatus.setForeground(new Color(160, 160, 160));
        }
        
        lblStatus.repaint();
        lblStatus.revalidate();
    }
    
    public void init(){
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUserName = new javax.swing.JLabel();
        deleteChatButton = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        lblUserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserName.setText("jLabel1");

        deleteChatButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-delete-chat-32.png"))); // NOI18N
        deleteChatButton.setToolTipText("Xóa trò chuyện");
        deleteChatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteChatButtonActionPerformed(evt);
            }
        });

        lblStatus.setForeground(new java.awt.Color(0, 255, 0));
        lblStatus.setText("active");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStatus)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteChatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(deleteChatButton, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

         PublicEvent.getInstance().getEventChat().setUser(this.user); 
         PublicEvent.getInstance().getEventChat().requestData(this.user);
    }//GEN-LAST:event_formMouseClicked

    private void deleteChatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteChatButtonActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(getRootPane(), "Bạn có chắc chắn không?", ""
                + "Xóa lịch sử chat", dialogButton);
        if(result == 1){
            removeAll();
            PublicEvent.getInstance().getEventChat().deleteChat(user);   
        }   
    }//GEN-LAST:event_deleteChatButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteChatButton;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUserName;
    // End of variables declaration//GEN-END:variables
}
