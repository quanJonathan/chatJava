/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main_ui;

import UIObject.FriendCard;
import entity.BanBe;
import net.miginfocom.swing.MigLayout;
import swing.ModifiedScrollBar;
import java.util.ArrayList;

public class FriendPage extends javax.swing.JPanel {

    private ArrayList<BanBe> friendList;

    public FriendPage() {
        initComponents();
        sp.setVerticalScrollBar(new ModifiedScrollBar());
        sp.setHorizontalScrollBar(new ModifiedScrollBar());
        sp1.setVerticalScrollBar(new ModifiedScrollBar());
        sp1.setHorizontalScrollBar(new ModifiedScrollBar());
        init();
    }

    public void setFriendList(ArrayList<BanBe> friends) {
        this.friendList = friends;
    }

    public void showAllFriend() {
        friendListPanel.removeAll();
        for (BanBe b : friendList) {
            friendListPanel.add(new FriendCard(b), "wrap");
        }
        friendListPanel.repaint();
        friendListPanel.revalidate();
    }
    
    public void showAllUser() {
        
    }

    public void init() {
        friendListPanel.setLayout(new MigLayout());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        friendListPanel = new javax.swing.JPanel();
        friendSearchButton = new javax.swing.JButton();
        friendSearchBar = new javax.swing.JTextField();
        sp1 = new javax.swing.JScrollPane();
        friendAddListPanel = new javax.swing.JPanel();

        friendListPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout friendListPanelLayout = new javax.swing.GroupLayout(friendListPanel);
        friendListPanel.setLayout(friendListPanelLayout);
        friendListPanelLayout.setHorizontalGroup(
            friendListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );
        friendListPanelLayout.setVerticalGroup(
            friendListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        sp.setViewportView(friendListPanel);

        friendSearchButton.setText("Search");
        friendSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendSearchButtonActionPerformed(evt);
            }
        });

        friendSearchBar.setText("bebaoboy");

        friendAddListPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout friendAddListPanelLayout = new javax.swing.GroupLayout(friendAddListPanel);
        friendAddListPanel.setLayout(friendAddListPanelLayout);
        friendAddListPanelLayout.setHorizontalGroup(
            friendAddListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );
        friendAddListPanelLayout.setVerticalGroup(
            friendAddListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        sp1.setViewportView(friendAddListPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(friendSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(friendSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addComponent(sp1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(friendSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(friendSearchButton))
                        .addGap(29, 29, 29)
                        .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void friendSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendSearchButtonActionPerformed
        var text = friendSearchBar.getText();
        if (text.strip().isEmpty()) {
            showAllUser();
            return;
        }
        var query = text.strip().split("\\s+");
        var s = new ArrayList<String>();
        for (var word : query) {
            s.add("username like N'%" + word + "%'");
        }
    }//GEN-LAST:event_friendSearchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel friendAddListPanel;
    private javax.swing.JPanel friendListPanel;
    private javax.swing.JTextField friendSearchBar;
    private javax.swing.JButton friendSearchButton;
    private javax.swing.JScrollPane sp;
    private javax.swing.JScrollPane sp1;
    // End of variables declaration//GEN-END:variables
}
