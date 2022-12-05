/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UIObject;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class GroupCard extends javax.swing.JPanel {
    
    
    public GroupCard(String name) {
        initComponents();
        lblGroupName.setText(name);
        addAdminButton.setVisible(false);
        deleteGroupButton.setVisible(false);
    }
    
    public void forAdmin(){
        addAdminButton.setVisible(true);
        deleteGroupButton.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        lblGroupName = new javax.swing.JLabel();
        deleteChatButton = new javax.swing.JButton();
        leaveGroupButton = new javax.swing.JButton();
        deleteGroupButton = new javax.swing.JButton();
        addAdminButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        lblGroupName.setText("Tên nhóm");

        deleteChatButton.setText("Xóa");
        deleteChatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteChatButtonActionPerformed(evt);
            }
        });

        leaveGroupButton.setText("Rời nhóm");
        leaveGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveGroupButtonActionPerformed(evt);
            }
        });

        deleteGroupButton.setText("Xóa nhóm");

        addAdminButton.setText("Thêm admin");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblGroupName)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteGroupButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addAdminButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteChatButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leaveGroupButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGroupName)
                    .addComponent(deleteChatButton)
                    .addComponent(leaveGroupButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteGroupButton)
                    .addComponent(addAdminButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteChatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteChatButtonActionPerformed
        
    }//GEN-LAST:event_deleteChatButtonActionPerformed

    private void leaveGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveGroupButtonActionPerformed
        //PublicEvent.getInstance().getEventGroupChat().leaveChat();
    }//GEN-LAST:event_leaveGroupButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAdminButton;
    private javax.swing.JButton deleteChatButton;
    private javax.swing.JButton deleteGroupButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lblGroupName;
    private javax.swing.JButton leaveGroupButton;
    // End of variables declaration//GEN-END:variables
}
