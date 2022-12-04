/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UIObject;

import entity.NhomChat;
import entity.ThanhVienNhomChat;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class GroupCard extends javax.swing.JPanel {
    
    private NhomChat group;
    private ArrayList<ThanhVienNhomChat> groupMember;
    public GroupCard(String name) {
        initComponents();
        this.groupName.setText(name);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        groupName = new javax.swing.JLabel();
        deleteChatButton = new javax.swing.JButton();
        leaveGroupButton = new javax.swing.JButton();

        groupName.setText("Tên nhóm");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(groupName)
                .addGap(43, 43, 43)
                .addComponent(deleteChatButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(leaveGroupButton)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupName)
                    .addComponent(deleteChatButton)
                    .addComponent(leaveGroupButton))
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteChatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteChatButtonActionPerformed
        removeAll();
    }//GEN-LAST:event_deleteChatButtonActionPerformed

    private void leaveGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveGroupButtonActionPerformed
        //PublicEvent.getInstance().getEventGroupChat().leaveChat();
    }//GEN-LAST:event_leaveGroupButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteChatButton;
    private javax.swing.JLabel groupName;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JButton leaveGroupButton;
    // End of variables declaration//GEN-END:variables
}
