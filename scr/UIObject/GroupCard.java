/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UIObject;

import entity.NhomChat;
import entity.TaiKhoan;
import entity.ThanhVienNhomChat;
import event.PublicEvent;
import java.awt.Color;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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

        this.group = group;
        this.role = role;
        showMember(false);
        // deleteChatButton.setVisible(false);
        txtGroupName2.setVisible(false);
        sp1.setVisible(false);

        sp.setViewportView(listGroupMemberTable);
    }

    public NhomChat getGroup() {
        return this.group;
    }

    public void showMember(boolean flag) {
        adminPanel.setVisible(flag);
        if (role) {
            adminSubPanel.setVisible(true);
        } else {
            adminSubPanel.setVisible(false);
        }
    }

    private void addRow(JTable table, int size) {
        var model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        int adds = abs(size - model.getRowCount());
        for (int count = 1; count <= adds; count++) {
            model.addRow(new Object[]{"", "", "", "", ""});
            // System.out.println(table.getRowCount());
        }
        table.setModel(model);

        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                table.setValueAt("", i, j);
            }
        }

    }

    public void setMemberList(ArrayList<ThanhVienNhomChat> members) {
        addRow(listGroupMemberTable, members.size());
        String chucNang;
        for (int i = 0; i < members.size(); i++) {

            if (members.get(i).getChucNang()) {
                chucNang = "Admin";
            } else {
                chucNang = "Thành viên";
            }
            listGroupMemberTable.setValueAt(members.get(i).getUsername(), i, 0);
            listGroupMemberTable.setValueAt(chucNang, i, 1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtGroupName = new javax.swing.JTextField();
        deleteChatButton = new javax.swing.JButton();
        viewMemberButton = new javax.swing.JButton();
        adminPanel = new javax.swing.JPanel();
        sp = new javax.swing.JScrollPane();
        listGroupMemberTable = new javax.swing.JTable();
        adminSubPanel = new javax.swing.JPanel();
        updateAdminButton = new javax.swing.JButton();
        removeMemberButton = new javax.swing.JButton();
        addMemberButton = new javax.swing.JButton();
        renameGroupButton = new javax.swing.JButton();
        txtGroupName2 = new javax.swing.JTextField();
        sp1 = new javax.swing.JScrollPane();
        listFriend = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtGroupName.setEditable(false);
        txtGroupName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGroupName.setText("Tên nhóm");
        txtGroupName.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        deleteChatButton.setText("Xóa");
        deleteChatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteChatButtonActionPerformed(evt);
            }
        });

        viewMemberButton.setText("Xem thành viên");
        viewMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMemberButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtGroupName, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteChatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteChatButton)
                    .addComponent(viewMemberButton))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        adminPanel.setBackground(new java.awt.Color(255, 255, 255));

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
        if (listGroupMemberTable.getColumnModel().getColumnCount() > 0) {
            listGroupMemberTable.getColumnModel().getColumn(1).setMaxWidth(60);
        }

        adminSubPanel.setBackground(new java.awt.Color(255, 255, 255));

        updateAdminButton.setText("Update Admin");
        updateAdminButton.setToolTipText("Admin thành member và ngược lại");
        updateAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAdminButtonActionPerformed(evt);
            }
        });

        removeMemberButton.setText("Xóa thành viên");
        removeMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMemberButtonActionPerformed(evt);
            }
        });

        addMemberButton.setText("Thêm thành viên");
        addMemberButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addMemberButtonMouseEntered(evt);
            }
        });
        addMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMemberButtonActionPerformed(evt);
            }
        });

        renameGroupButton.setText("Đổi tên");
        renameGroupButton.setToolTipText("Nhập tên mới vào khung bên cạnh, sau đó bấm nút để đổi tên");
        renameGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                renameGroupButtonMouseEntered(evt);
            }
        });
        renameGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameGroupButtonActionPerformed(evt);
            }
        });

        txtGroupName2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGroupName2.setText("Tên nhóm");
        txtGroupName2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        listFriend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên", "Admin"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
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
        listFriend.setToolTipText("Chọn thành viên, đánh dấu vào ô nếu là admin");
        sp1.setViewportView(listFriend);
        if (listFriend.getColumnModel().getColumnCount() > 0) {
            listFriend.getColumnModel().getColumn(1).setMaxWidth(60);
        }

        javax.swing.GroupLayout adminSubPanelLayout = new javax.swing.GroupLayout(adminSubPanel);
        adminSubPanel.setLayout(adminSubPanelLayout);
        adminSubPanelLayout.setHorizontalGroup(
            adminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminSubPanelLayout.createSequentialGroup()
                        .addComponent(removeMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(renameGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGroupName2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(adminSubPanelLayout.createSequentialGroup()
                        .addGroup(adminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(sp1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(adminSubPanelLayout.createSequentialGroup()
                                .addComponent(addMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateAdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        adminSubPanelLayout.setVerticalGroup(
            adminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminSubPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(adminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeMemberButton)
                    .addComponent(renameGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGroupName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adminSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(adminSubPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(addMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(updateAdminButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminSubPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(adminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteChatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteChatButtonActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(getRootPane(), "Bạn có chắc chắn không?", ""
                + "Xóa lịch sử nhóm chat", dialogButton);
        if (result == 1) {
            PublicEvent.getInstance().getEventGroupChat().deleteCurrentGroupData(group);
        }
    }//GEN-LAST:event_deleteChatButtonActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        PublicEvent.getInstance().getEventGroupChat().requestGroupData(group);
        PublicEvent.getInstance().getEventGroupChat().setGroup(group);
    }//GEN-LAST:event_formMouseClicked

    private void removeMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMemberButtonActionPerformed
        ArrayList<ThanhVienNhomChat> users = new ArrayList<>();
        var selects = listGroupMemberTable.getSelectedRows();
    }//GEN-LAST:event_removeMemberButtonActionPerformed

    private void updateAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAdminButtonActionPerformed
        ArrayList<ThanhVienNhomChat> users = new ArrayList<>();
        var selects = listGroupMemberTable.getSelectedRows();
        for (int i = 0; i < selects.length; i++) {
            var chucNang = (String) (listGroupMemberTable.getValueAt(selects[i], 1));
            users.add(new ThanhVienNhomChat("", (String) (listGroupMemberTable.getValueAt(selects[i], 0)), !chucNang.equals("Admin"), new Date()));
        }
        PublicEvent.getInstance().getEventGroupChat().setAdmin(group, users);
    }//GEN-LAST:event_updateAdminButtonActionPerformed

    private void viewMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMemberButtonActionPerformed
        if (isClick == true) {
            //sp.setViewportView(null);
            showMember(false);
            isClick = false;
        } else {
            System.out.println("not clicked yet");
            //sp.setViewportView(listGroupMemberTable);
            isClick = true;
            showMember(true);
        }
        PublicEvent.getInstance().getEventGroupChat().requestMemberData(group);
    }//GEN-LAST:event_viewMemberButtonActionPerformed

    private void renameGroupButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_renameGroupButtonMouseEntered
        if (role && !txtGroupName2.isVisible()) {
            txtGroupName2.setVisible(true);
            txtGroupName2.setEditable(true);
            txtGroupName2.setText(group.getTenNhom());
            System.out.println("Hovered");
            revalidate();
            repaint();
        }
    }//GEN-LAST:event_renameGroupButtonMouseEntered

    private void renameGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameGroupButtonActionPerformed
        if (role) {
            txtGroupName2.setVisible(false);
            txtGroupName2.setEditable(false);
            String newName = txtGroupName2.getText();
            PublicEvent.getInstance().getEventGroupChat().setNewGroupName(group, newName);
            revalidate();
            repaint();
        }
    }//GEN-LAST:event_renameGroupButtonActionPerformed

    private void addMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMemberButtonActionPerformed
        ArrayList<ThanhVienNhomChat> users = new ArrayList<>();
        var selects = listFriend.getSelectedRows();
        
        sp1.setVisible(false);
        revalidate();
        repaint();
    }//GEN-LAST:event_addMemberButtonActionPerformed

    private void addMemberButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMemberButtonMouseEntered
        sp1.setVisible(true);
        revalidate();
        repaint();
    }//GEN-LAST:event_addMemberButtonMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMemberButton;
    private javax.swing.JPanel adminPanel;
    private javax.swing.JPanel adminSubPanel;
    private javax.swing.JButton deleteChatButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable listFriend;
    private javax.swing.JTable listGroupMemberTable;
    private javax.swing.JButton removeMemberButton;
    private javax.swing.JButton renameGroupButton;
    private javax.swing.JScrollPane sp;
    private javax.swing.JScrollPane sp1;
    private javax.swing.JTextField txtGroupName;
    private javax.swing.JTextField txtGroupName2;
    private javax.swing.JButton updateAdminButton;
    private javax.swing.JButton viewMemberButton;
    // End of variables declaration//GEN-END:variables

}
