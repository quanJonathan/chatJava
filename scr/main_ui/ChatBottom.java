package main_ui;

import entity.IDPrefix;
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

public class ChatBottom extends javax.swing.JPanel {

    private TaiKhoan currentChatter;
    private TaiKhoan user;
    public ChatBottom() {
        initComponents();
        init();
    }
    public void setSender(TaiKhoan user){
        this.user = user;
    }
    
    public void setUser(TaiKhoan user){
        currentChatter = user;
    }

    private void init() {
        setLayout(new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2"));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        AutoResizeTextPanel txt = new AutoResizeTextPanel();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
            }
        });
        txt.setBorder(new EmptyBorder(5, 5, 5, 5));
        txt.setToolTipText("Write Message Here ...");
        scroll.setViewportView(txt);
        ModifiedScrollBar sb = new ModifiedScrollBar();
        sb.setPreferredSize(new Dimension(2, 10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll, "w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.WHITE);
        JButton cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/resources/send.png")));
        cmd.addActionListener((ActionEvent ae) -> {
            String text = txt.getText().trim();
            if (!text.equals("")) {
                TinNhan mess = new TinNhan(IDPrefix.getIDTinNhan(), 
                        new Timestamp(System.currentTimeMillis()), text, user.getUsername(),
                        currentChatter.getUsername(), "");
                PublicEvent.getInstance().getEventChat().sendMessage(mess);
                txt.setText("");
                txt.grabFocus();
                refresh();
            } else {
                txt.grabFocus();
            }
        });
        panel.add(cmd);
        add(panel);
    }

    private void refresh() {
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(229, 229, 229));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
