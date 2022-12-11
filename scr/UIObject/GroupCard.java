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
    
    boolean role;
    NhomChat group;
    boolean isClick = false;
    
    public GroupCard(NhomChat group, boolean role) {
        initComponents();
        txtGroupName.setText(group.getTenNhom());
        
        if(!role){
            txtGroupName.setEditable(false);
        }  
        this.group = group;
        
        sp.setViewportView(null);
        forUser();
    }
    
    public void forAdmin(){
        addAdminButton.setVisible(true);
        deleteGroupChatMemberButton.setVisible(true);
    }
    
    private void forUser() {
        addAdminButton.setVisible(false);
        deleteGroupChatMemberButton.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deleteChatButton = new javax.swing.JButton();
        addAdminButton = new javax.swing.JButton();
        viewMemberButton = new javax.swing.JButton();
        txtGroupName = new javax.swing.JTextField();
        deleteGroupChatMemberButton = new javax.swing.JButton();
        sp = new javax.swing.JScrollPane();
        listGroupMemberTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
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
        sp.setViewportView(listGroupMemberTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(deleteChatButton))
                            .addComponent(deleteGroupChatMemberButton))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(viewMemberButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(addAdminButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteGroupChatMemberButton)
                    .addComponent(addAdminButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        PublicEvent.getInstance().getEventGroupChat().requestGroupData(group);
        PublicEvent.getInstance().getEventGroupChat().setGroup(group);
    }//GEN-LAST:event_formMouseClicked

    private void addAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAdminButtonActionPerformed
//        PublicEvent.getInstance().getEventGroupChat().setAdmin(group, user);
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
   
        if(isClick == true){
            sp.setViewportView(null);
            isClick = false;
        }else{
            System.out.println("no");
            sp.setViewportView(listGroupMemberTable);
            isClick = true;
            if(role){
                forAdmin();
            }
        }
    }//GEN-LAST:event_viewMemberButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAdminButton;
    private javax.swing.JButton deleteChatButton;
    private javax.swing.JButton deleteGroupChatMemberButton;
    private javax.swing.JTable listGroupMemberTable;
    private javax.swing.JScrollPane sp;
    private javax.swing.JTextField txtGroupName;
    private javax.swing.JButton viewMemberButton;
    // End of variables declaration//GEN-END:variables

    
}
