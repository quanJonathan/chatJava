package main_ui;

import entity.TaiKhoan;
import entity.TinNhan;
import java.awt.Adjustable;
import swing.ModifiedScrollBar;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.Date;
import net.miginfocom.swing.MigLayout;
import java.util.ArrayList;
import javax.swing.JScrollBar;

public class ChatBody extends javax.swing.JPanel {
    
    public TaiKhoan user;
    private ArrayList<TinNhan> currentChatData;
   
    public ChatBody() {
        initComponents();
        init();
    }
    
    public void setUser(TaiKhoan user){
        this.user = user;
    }
    

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[bottom]5"));
        sp.setVerticalScrollBar(new ModifiedScrollBar());
        
        addItemRight(new TinNhan("3", new Date(System.currentTimeMillis()), "Nothing much", "user2", "user1", "")); 
        addItemLeft(new TinNhan("1", new Date(System.currentTimeMillis()), "hello", "user1", "user2", ""));
        addItemLeft(new TinNhan("2", new Date(System.currentTimeMillis()), "what are you doing", "user1", "user2", ""));
        addItemRight(new TinNhan("3", new Date(System.currentTimeMillis()), "Nothing much", "user2", "user1", ""));
    }
    
    private void setCurrentData(TaiKhoan user){
       
    }
    
    private void showCurrentData(){
       for(TinNhan t: currentChatData){
            if(t.getNguoiGui().equals(this.user.getUsername())){
                addItemRight(t);
            }else{
                addItemLeft(t);
            }
       }
    }
    
    public void addItemLeft(TinNhan text) {
        ChatLeftWithProfile item = new ChatLeftWithProfile();
        item.setText(text.getNoiDung(), text.getThoiGian().toString());
        item.setUserProfile(text.getNguoiNhan());
        body.add(item, "wrap, w 100::80%");
        body.repaint();
        body.revalidate();
    }

    public void addItemRight(TinNhan text) {
        ChatRight item = new ChatRight();
        item.setText(text.getNoiDung());
        item.setTime(text.getThoiGian().toString());
        body.add(item, "wrap, al right, w 100::80%");
        body.repaint();
        body.revalidate();
        scrollToBottom();
    }
    
    public void setChatData(ArrayList<TinNhan> messages){
        clear();
        for (TinNhan t: messages){
            if(t.getNguoiGui().equals(this.user.getUsername())){
                addItemRight(t);
            }else{
                addItemLeft(t);
            }
        }
    }
    
    public void clear(){
        body.removeAll();
        body.repaint();
        body.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1000, 555));

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables

    private void scrollToBottom() {
        JScrollBar scrollBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                scrollBar.removeAdjustmentListener(this);
            }
        };
        scrollBar.addAdjustmentListener(downScroller);
    }
}
