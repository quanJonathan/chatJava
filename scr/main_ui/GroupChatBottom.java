package main_ui;

import entity.IDPrefix;
import entity.NhomChat;
import entity.TaiKhoan;
import entity.TinNhan;
import event.PublicEvent;
import swing.AutoResizeTextPanel;
import swing.ModifiedScrollBar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class GroupChatBottom extends javax.swing.JPanel {

    private NhomChat group;
    private TaiKhoan user;

    public GroupChatBottom() {
        initComponents();
        init();
    }

    public void setSender(TaiKhoan user) {
        this.user = user;
    }

    public void setGroup(NhomChat group){
        this.group = group;
    }
    
    private void init() {
        setLayout(new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2"));
        JScrollPane sp = new JScrollPane();
        sp.setBorder(null);
        sp.setViewportView(txt);
        
        ModifiedScrollBar sb = new ModifiedScrollBar();
        sb.setPreferredSize(new Dimension(2, 10));
        sp.setVerticalScrollBar(sb);
        
        add(sb);
        add(sp, "w 100%");
       
        panel.setLayout(new MigLayout("filly", "0[]0", "0[bottom]0"));
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        add(panel);
    }

    private void refresh() {
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        txt = new swing.AutoResizeTextPanel();
        cmd = new javax.swing.JButton();

        setBackground(new java.awt.Color(229, 229, 229));

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setPreferredSize(new java.awt.Dimension(30, 28));

        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txt.setToolTipText("Write Message Here ...");
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKeyTyped(evt);
            }
        });

        cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/send.png"))); // NOI18N
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cmd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionPerformed
        String text = txt.getText().trim();
        if (!text.equals("")) {
            TinNhan mess = new TinNhan(IDPrefix.getIDTinNhan(),
                    new Timestamp(System.currentTimeMillis()), text, user.getUsername(),
                    "/", group.getIDNhom(), user.getUsername());
            PublicEvent.getInstance().getEventGroupChat().sendMessage(group,mess);
            txt.setText("");
            txt.grabFocus();
            refresh();
        } else {
            txt.grabFocus();
        }
    }//GEN-LAST:event_cmdActionPerformed

    private void txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyTyped
        refresh();
    }//GEN-LAST:event_txtKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmd;
    private javax.swing.JPanel panel;
    private swing.AutoResizeTextPanel txt;
    // End of variables declaration//GEN-END:variables
}
