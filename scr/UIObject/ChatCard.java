package UIObject;

import entity.TaiKhoan;
import event.EventChat;
import event.EventOnChatCard;
import event.PublicEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChatCard extends javax.swing.JPanel {

    private TaiKhoan user;
    public ChatCard(TaiKhoan user) {
        initComponents();
        this.user = user;
        lblUserName.setText(user.getUsername());
        
    }
    
    public TaiKhoan getUser(){
        return this.user;
    }
    
    public void setStatus(){
         if(user.getTrangThai() == 1){
            lblStatus.setText("Active now");
            lblStatus.setForeground(new java.awt.Color(40, 147, 59));
        }else{
             lblStatus.setText("Offline");
             lblStatus.setForeground(new Color(160, 160, 160));
        }
    }
    
    public void init(){
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUserName = new javax.swing.JLabel();
        deleteChatButton = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        lblUserName.setText("jLabel1");

        deleteChatButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-delete-chat-32.png"))); // NOI18N
        deleteChatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteChatButtonActionPerformed(evt);
            }
        });

        lblStatus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(0, 255, 0));
        lblStatus.setText("active");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
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
                .addComponent(lblUserName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

         PublicEvent.getInstance().getEventChat().setUser(this.user); 
    }//GEN-LAST:event_formMouseClicked

    private void deleteChatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteChatButtonActionPerformed
          removeAll();
//        PublicEvent.getInstance().getEventChat().deleteChat();
    }//GEN-LAST:event_deleteChatButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteChatButton;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUserName;
    // End of variables declaration//GEN-END:variables
}
