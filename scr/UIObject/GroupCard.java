/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UIObject;

import entity.NhomChat;
import entity.TaiKhoan;
import entity.ThanhVienNhomChat;
import event.PublicEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class GroupCard extends javax.swing.JPanel {
    
    TaiKhoan user;
    boolean role;
    NhomChat group;
    
    public GroupCard(NhomChat group, TaiKhoan name, boolean role) {
        initComponents();
        txtGroupName.setText(group.getTenNhom());
        
        if(!role){
            txtGroupName.setEditable(false);
        }  
        listGroupMemberTable.setVisible(false);
        this.group = group;
       
        addAdminButton.setVisible(false);
        deleteGroupChatMemberButton.setVisible(false);
        listGroupMemberTable.setVisible(true);
    }
    
    public void forAdmin(){
        addAdminButton.setVisible(true);
        deleteGroupChatMemberButton.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        leaveGroupButton = new javax.swing.JButton();
        deleteChatButton = new javax.swing.JButton();
        addAdminButton = new javax.swing.JButton();
        viewMemberButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listGroupMemberTable = new javax.swing.JTable();
        txtGroupName = new javax.swing.JTextField();
        deleteGroupChatMemberButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        leaveGroupButton.setText("Thêm thành viên");
        leaveGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveGroupButtonActionPerformed(evt);
            }
        });

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        deleteChatButton.setText("Xóa");
        deleteChatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteChatButtonActionPerformed(evt);
            }
        });

        addAdminButton.setText("Thêm admin");
        addAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAdminButtonActionPerformed(evt);
            }
        });

        viewMemberButton.setText("Xem thành  viên");
        viewMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMemberButtonActionPerformed(evt);
            }
        });

        listGroupMemberTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên", "Chức năng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(listGroupMemberTable);

        txtGroupName.setText("Tên nhóm");
        txtGroupName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtGroupName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGroupNameActionPerformed(evt);
            }
        });

        deleteGroupChatMemberButton.setText("Xóa thành viên");
        deleteGroupChatMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGroupChatMemberButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(deleteChatButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewMemberButton)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(deleteGroupChatMemberButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addAdminButton)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteChatButton)
                    .addComponent(viewMemberButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteGroupChatMemberButton)
                    .addComponent(addAdminButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteChatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteChatButtonActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(getRootPane(), "Bạn có chắc chắn không?", ""
                + "Xóa lịch sử nhóm chat", dialogButton);
        if(result == 1){
           PublicEvent.getInstance().getEventGroupChat().deleteCurrentGroupData(group);
        }  
    }//GEN-LAST:event_deleteChatButtonActionPerformed

    private void leaveGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveGroupButtonActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(getRootPane(), "Bạn có chắc chắn không?", ""
                + "Rời nhóm", dialogButton);
        if(result == 1){
           PublicEvent.getInstance().getEventGroupChat().leaveGroup(group);
        }   
    }//GEN-LAST:event_leaveGroupButtonActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        PublicEvent.getInstance().getEventGroupChat().requestGroupData(group);
        PublicEvent.getInstance().getEventGroupChat().setGroup(group);
    }//GEN-LAST:event_formMouseClicked

    private void addAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAdminButtonActionPerformed
        PublicEvent.getInstance().getEventGroupChat().setAdmin(group, user);
    }//GEN-LAST:event_addAdminButtonActionPerformed

    private void txtGroupNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGroupNameActionPerformed
        if(role){
            String newName = txtGroupName.getText();
            PublicEvent.getInstance().getEventGroupChat().setNewGroupName(group, newName);
        }
    }//GEN-LAST:event_txtGroupNameActionPerformed

    private void deleteGroupChatMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGroupChatMemberButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteGroupChatMemberButtonActionPerformed

    private void viewMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMemberButtonActionPerformed
        if(role){
            forAdmin();
        }
        listGroupMemberTable.setVisible(true);
    }//GEN-LAST:event_viewMemberButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAdminButton;
    private javax.swing.JButton deleteChatButton;
    private javax.swing.JButton deleteGroupChatMemberButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton leaveGroupButton;
    private javax.swing.JTable listGroupMemberTable;
    private javax.swing.JTextField txtGroupName;
    private javax.swing.JButton viewMemberButton;
    // End of variables declaration//GEN-END:variables
}
