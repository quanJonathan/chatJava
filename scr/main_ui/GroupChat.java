/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main_ui;

import entity.NhomChat;
import entity.TaiKhoan;
import entity.TinNhan;
import net.miginfocom.swing.MigLayout;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class GroupChat extends javax.swing.JPanel {

    private ChatTitle groupChatTitle;
    private ChatBody groupChatBody;
    private ChatBottom groupChatBottom;
    public GroupChat() {
        initComponents();
        
        setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[100%, bottom]0[shrink 0]0"));
        groupChatTitle = new ChatTitle();
        groupChatBody = new ChatBody();
        groupChatBottom = new ChatBottom();
        
        add(groupChatTitle, "wrap");
        add(groupChatBody, "wrap");
        add(groupChatBottom, "h ::50%");
    }
    
    public void setGroupData(ArrayList<TinNhan> messages){
        groupChatBody.setChatData(messages);
    }
    
    public void setGroup(NhomChat group, TaiKhoan user){
        groupChatTitle.setGroupName(group);
        groupChatBody.clear();
        groupChatBottom.setUser(user);
    }
    
    public ChatBody getGroupChatBody() {
        return groupChatBody;
    }

    public ChatTitle getGroupChatTitle() {
        return groupChatTitle;
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
