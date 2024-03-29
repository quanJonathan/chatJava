/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main_ui;

import UIObject.FriendCard;
import UIObject.FriendSearchCard;
import UIObject.RequestCard;
import entity.BanBe;
import entity.TaiKhoan;
import event.PublicEvent;
import net.miginfocom.swing.MigLayout;
import swing.ModifiedScrollBar;
import java.util.ArrayList;

public class FriendPage extends javax.swing.JPanel {

    private ArrayList<BanBe> friendList;
    private ArrayList<Integer> statuses;

    public FriendPage() {
        initComponents();
        sp.setVerticalScrollBar(new ModifiedScrollBar());
        sp.setHorizontalScrollBar(new ModifiedScrollBar());
        sp1.setVerticalScrollBar(new ModifiedScrollBar());
        sp1.setHorizontalScrollBar(new ModifiedScrollBar());
        sp2.setVerticalScrollBar(new ModifiedScrollBar());
        sp2.setHorizontalScrollBar(new ModifiedScrollBar());
        init();

    }

    public void setFriendList(ArrayList<BanBe> friends, ArrayList<Integer> status) {
        this.friendList = friends;
        statuses = status;
    }

    public void showAllFriend() {
        friendListPanel.removeAll();
        try {
            for (BanBe b : friendList) {
                friendListPanel.add(new FriendCard(b), "wrap");
            }
        } catch (Throwable t) {
        }
        for (int i = 0; i < friendList.size(); i++) {
            var friendCard = (FriendCard) friendListPanel.getComponent(i);
            friendCard.setStatus(statuses.get(i));
        }
        friendListPanel.repaint();
        friendListPanel.revalidate();
    }

    public void showAllFriendRequest(ArrayList<BanBe> friendRequest) {
        friendRequestListPanel.removeAll();
        for (BanBe b : friendRequest) {
            friendRequestListPanel.add(new RequestCard(b), "wrap");
        }
        friendRequestListPanel.repaint();
        friendRequestListPanel.revalidate();
    }

    public void showAllSearch(ArrayList<BanBe> friendSearch) {
        friendAddListPanel.removeAll();
        for (BanBe b : friendSearch) {
            friendAddListPanel.add(new FriendSearchCard(b), "wrap");
        }
        friendAddListPanel.repaint();
        friendAddListPanel.revalidate();
    }

    public void init() {
        friendListPanel.setLayout(new MigLayout());
        friendAddListPanel.setLayout(new MigLayout());
        friendRequestListPanel.setLayout(new MigLayout());
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
        sp2 = new javax.swing.JScrollPane();
        friendRequestListPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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

        friendRequestListPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout friendRequestListPanelLayout = new javax.swing.GroupLayout(friendRequestListPanel);
        friendRequestListPanel.setLayout(friendRequestListPanelLayout);
        friendRequestListPanelLayout.setHorizontalGroup(
            friendRequestListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );
        friendRequestListPanelLayout.setVerticalGroup(
            friendRequestListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        sp2.setViewportView(friendRequestListPanel);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Bạn bè của bạn");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Kết quả tìm kiếm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Yêu cầu kết bạn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(friendSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(friendSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sp1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addComponent(sp2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(friendSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(friendSearchButton))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(sp2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void friendSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendSearchButtonActionPerformed
        var text = friendSearchBar.getText();
        if (text.strip().isEmpty()) {
            return;
        }
        var query = text.strip().split("\\s+");
        var s = new ArrayList<String>();
        for (var word : query) {
            s.add("username like N'%" + word + "%'");
        }
        text = "where " + String.join(" or ", s);
        PublicEvent.getInstance().getEventFriend().getFriendSearchData(text);
    }//GEN-LAST:event_friendSearchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel friendAddListPanel;
    private javax.swing.JPanel friendListPanel;
    private javax.swing.JPanel friendRequestListPanel;
    private javax.swing.JTextField friendSearchBar;
    private javax.swing.JButton friendSearchButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane sp;
    private javax.swing.JScrollPane sp1;
    private javax.swing.JScrollPane sp2;
    // End of variables declaration//GEN-END:variables
}
