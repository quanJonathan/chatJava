/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package forSubmitOnly;

import main_ui.*;
import UIObject.GroupCard;
import java.awt.Component;
import java.util.List;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import swing.ModifiedScrollBar;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class chatListAndSearch extends javax.swing.JPanel {

    ArrayList<String> groups;
    ArrayList<String> userAccounts;
    public chatListAndSearch() {
        initComponents();
        init();
        sp.setVerticalScrollBar(new ModifiedScrollBar());
        sp.setHorizontalScrollBar(new ModifiedScrollBar());
        chatListPanel.setLayout(new MigLayout("wrap"));
    }
    
    public void refreshChatListPanel(){
        chatListPanel.repaint();
        chatListPanel.revalidate();
    }
    
    public JPanel getChatListPanel(){
        return chatListPanel;
    }
    
    public void init(){
        userAccounts = new ArrayList<>();
        groups = new ArrayList<>();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        chatListPanel = new javax.swing.JPanel();
        txtUsernamSearch = new javax.swing.JTextField();
        searchButtonChat = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout chatListPanelLayout = new javax.swing.GroupLayout(chatListPanel);
        chatListPanel.setLayout(chatListPanelLayout);
        chatListPanelLayout.setHorizontalGroup(
            chatListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );
        chatListPanelLayout.setVerticalGroup(
            chatListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 738, Short.MAX_VALUE)
        );

        sp.setViewportView(chatListPanel);

        txtUsernamSearch.setToolTipText("Nhập tên");

        searchButtonChat.setText("Tìm kiếm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUsernamSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButtonChat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(sp, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsernamSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButtonChat))
                .addGap(4, 4, 4)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chatListPanel;
    private javax.swing.JButton searchButtonChat;
    private javax.swing.JScrollPane sp;
    private javax.swing.JTextField txtUsernamSearch;
    // End of variables declaration//GEN-END:variables
}
