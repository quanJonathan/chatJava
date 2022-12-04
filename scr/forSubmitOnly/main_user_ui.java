package forSubmitOnly;

import forSubmitOnly.GroupCard;
import authentication_ui.LoginUI;
import entity.TaiKhoan;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class main_user_ui extends javax.swing.JFrame {

    CardLayout cardLayoutMain;
    CardLayout cardHomePage;
    private static TaiKhoan currentUser;
    
    ArrayList<TaiKhoan> usernames = new ArrayList<>();
    ArrayList<String> groups = new ArrayList<>();
    public main_user_ui(TaiKhoan username) {
        initComponents();
        cardLayoutMain = (CardLayout) mainBody.getLayout();
        cardHomePage = (CardLayout) modifiedPanelHome.getLayout();
        cardLayoutMain.show(mainBody, "homeCard");

        // Main function
         currentUser = username;

        //Test data
        currentUser = new TaiKhoan("luutuanquan", "", "");
        init();
    }

    public final void init() {

        usernames.add(new TaiKhoan("luutuanquan", "", ""));
        usernames.add(new TaiKhoan("bebaoboy", "", ""));
        
        groups.add("Group1");
        groups.add("groups2");
        showAllPersonalChat(); 
        
        showAllGroupChat();
    }

    public void showAllPersonalChat() {
        chatList.getChatListPanel().removeAll();
        for (TaiKhoan t: usernames) {
            if (!t.getUsername().equals(currentUser.getUsername())) {
                chatList.getChatListPanel().add(new ChatCard(t), "wrap");
            }
        }
        chat.getChatBody().addItemRight("Hello"); 
        chat.getChatBody().addItemLeft("hi, how are u doing", "bao");
        chat.getChatBody().addItemLeft("I am coding", "bao");
        chat.getChatBody().addItemRight("good luck"); 
        chat.getChatBody().addItemRight("I will do a coding test now :))");
        chat.getChatBody().addItemLeft("good luck on the test :))", "bao");
        chat.getChatBody().addItemRight("Thank you"); 
        chat.getChatBody().addItemLeft("good luck on the test :))", "bao");
        chat.getChatBody().addItemRight("Thank you"); 
        chat.getChatBody().addItemLeft("good luck on the test :))", "bao");
        chat.getChatBody().addItemRight("Thank you"); 
        
        chatList.getChatListPanel().revalidate();
        chatList.getChatListPanel().repaint();
    }
    
    
    public void showAllGroupChat(){
        groupList.getChatListPanel().removeAll();
        for(String groupName: groups){
            groupList.getChatListPanel().add(new GroupCard(groupName), "wrap");
        }
        GroupCard c = (GroupCard)groupList.getChatListPanel().getComponent(0);
        c.forAdmin();
        
        groupChat.getChatBody().addItemLeft("Hello group", "bao");
        groupChat.getChatBody().addItemLeft("hii", "phu");
        groupChat.getChatBody().addItemLeft("This group is created for test only", "bao");
        groupChat.getChatBody().addItemRight("ok");
        
        groupChat.getChatTitle().setUserName("Group 1");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        sideNavBar = new javax.swing.JPanel();
        homeTab = new javax.swing.JLabel();
        friendListTab = new javax.swing.JLabel();
        chatTab = new javax.swing.JLabel();
        groupTab = new javax.swing.JLabel();
        mainBody = new javax.swing.JPanel();
        friendListPage = new forSubmitOnly.FriendPage();
        groupPage = new javax.swing.JPanel();
        groupChat = new forSubmitOnly.Chat();
        groupList = new forSubmitOnly.chatListAndSearch();
        homePage = new javax.swing.JPanel();
        initGroupCreateButton = new javax.swing.JButton();
        initChangePassButton = new javax.swing.JButton();
        initChangeUserNameButton = new javax.swing.JButton();
        modifiedPanelHome = new javax.swing.JPanel();
        changePassPage = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtOldPass = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNewPass = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtConfirmNewPass = new javax.swing.JTextField();
        changePassButton = new javax.swing.JButton();
        changeUserNamePage = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtChangePass = new javax.swing.JTextField();
        changeUsernameButton = new javax.swing.JButton();
        createGroupPage = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtGroupName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSelectToGroupCreate = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaAdmin = new javax.swing.JTextArea();
        createGroupButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        chatPage = new javax.swing.JPanel();
        chat = new forSubmitOnly.Chat();
        chatList = new forSubmitOnly.chatListAndSearch();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        sideNavBar.setBackground(new java.awt.Color(255, 255, 255));

        homeTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-home-32.png"))); // NOI18N
        homeTab.setText("Home");
        homeTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeTabMouseClicked(evt);
            }
        });

        friendListTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-friend-32.png"))); // NOI18N
        friendListTab.setText("FriendList");
        friendListTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                friendListTabMouseClicked(evt);
            }
        });

        chatTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-communication-32.png"))); // NOI18N
        chatTab.setText("Chat");
        chatTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chatTabMouseClicked(evt);
            }
        });

        groupTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-user-groups-32.png"))); // NOI18N
        groupTab.setText("Group");
        groupTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                groupTabMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sideNavBarLayout = new javax.swing.GroupLayout(sideNavBar);
        sideNavBar.setLayout(sideNavBarLayout);
        sideNavBarLayout.setHorizontalGroup(
            sideNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideNavBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sideNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(groupTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chatTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(friendListTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sideNavBarLayout.setVerticalGroup(
            sideNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideNavBarLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(homeTab)
                .addGap(51, 51, 51)
                .addComponent(chatTab)
                .addGap(53, 53, 53)
                .addComponent(friendListTab)
                .addGap(58, 58, 58)
                .addComponent(groupTab)
                .addContainerGap(362, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(sideNavBar);

        mainBody.setLayout(new java.awt.CardLayout());
        mainBody.add(friendListPage, "friendListCard");

        javax.swing.GroupLayout groupPageLayout = new javax.swing.GroupLayout(groupPage);
        groupPage.setLayout(groupPageLayout);
        groupPageLayout.setHorizontalGroup(
            groupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupPageLayout.createSequentialGroup()
                .addComponent(groupList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupChat, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        groupPageLayout.setVerticalGroup(
            groupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(groupList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(groupChat, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
        );

        mainBody.add(groupPage, "groupCard");

        homePage.setBackground(new java.awt.Color(255, 255, 255));

        initGroupCreateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-add-user-group-woman-man-32.png"))); // NOI18N
        initGroupCreateButton.setText("Tạo nhóm");
        initGroupCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initGroupCreateButtonActionPerformed(evt);
            }
        });

        initChangePassButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-password-reset-32.png"))); // NOI18N
        initChangePassButton.setText("Đổi mật khẩu");
        initChangePassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initChangePassButtonActionPerformed(evt);
            }
        });

        initChangeUserNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-name-tag-32.png"))); // NOI18N
        initChangeUserNameButton.setText("Đổi tên");
        initChangeUserNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initChangeUserNameButtonActionPerformed(evt);
            }
        });

        modifiedPanelHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        modifiedPanelHome.setEnabled(false);
        modifiedPanelHome.setFocusable(false);
        modifiedPanelHome.setOpaque(false);
        modifiedPanelHome.setLayout(new java.awt.CardLayout());

        jLabel8.setText("Nhập mật khẩu cũ");

        jLabel9.setText("Nhập mật khẩu mới");

        jLabel10.setText("Xác nhận mật khẩu mới");

        changePassButton.setText("Đổi mật khẩu");

        javax.swing.GroupLayout changePassPageLayout = new javax.swing.GroupLayout(changePassPage);
        changePassPage.setLayout(changePassPageLayout);
        changePassPageLayout.setHorizontalGroup(
            changePassPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changePassPageLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(changePassPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirmNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(changePassPageLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(changePassButton)))
                .addGap(21, 21, 21))
        );
        changePassPageLayout.setVerticalGroup(
            changePassPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePassPageLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtConfirmNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(changePassButton)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        modifiedPanelHome.add(changePassPage, "homeChangePassCard");

        jLabel6.setText("Tên hiển thị mới");

        jLabel7.setText("Nhập mật khẩu xác nhận");

        changeUsernameButton.setText("Đổi tên hiển thị");

        javax.swing.GroupLayout changeUserNamePageLayout = new javax.swing.GroupLayout(changeUserNamePage);
        changeUserNamePage.setLayout(changeUserNamePageLayout);
        changeUserNamePageLayout.setHorizontalGroup(
            changeUserNamePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeUserNamePageLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(changeUserNamePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(changeUserNamePageLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(changeUsernameButton)))
                .addGap(17, 17, 17))
        );
        changeUserNamePageLayout.setVerticalGroup(
            changeUserNamePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeUserNamePageLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(changeUsernameButton)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        modifiedPanelHome.add(changeUserNamePage, "homeChangeUsernameCard");

        jLabel1.setText("Nhập tên nhóm");

        listSelectToGroupCreate.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "quan", "bao", "phu" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listSelectToGroupCreate);

        jLabel2.setText("Chọn thành viên nhóm");

        jLabel5.setText("Chọn admin");

        textAreaAdmin.setColumns(20);
        textAreaAdmin.setRows(5);
        jScrollPane2.setViewportView(textAreaAdmin);

        createGroupButton.setText("Tạo nhóm");

        javax.swing.GroupLayout createGroupPageLayout = new javax.swing.GroupLayout(createGroupPage);
        createGroupPage.setLayout(createGroupPageLayout);
        createGroupPageLayout.setHorizontalGroup(
            createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createGroupPageLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createGroupPageLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(createGroupButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(createGroupPageLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtGroupName)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel5)))
                .addGap(22, 22, 22))
        );
        createGroupPageLayout.setVerticalGroup(
            createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createGroupPageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createGroupButton)
                .addGap(14, 14, 14))
        );

        modifiedPanelHome.add(createGroupPage, "homeCreateGroupCard");

        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-log-out-32.png"))); // NOI18N
        logoutButton.setText("Đăng xuất");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout homePageLayout = new javax.swing.GroupLayout(homePage);
        homePage.setLayout(homePageLayout);
        homePageLayout.setHorizontalGroup(
            homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageLayout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(initGroupCreateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(initChangePassButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(initChangeUserNameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addComponent(modifiedPanelHome, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 350, Short.MAX_VALUE))
        );
        homePageLayout.setVerticalGroup(
            homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(homePageLayout.createSequentialGroup()
                        .addComponent(initGroupCreateButton)
                        .addGap(56, 56, 56)
                        .addComponent(initChangePassButton)
                        .addGap(61, 61, 61)
                        .addComponent(initChangeUserNameButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutButton))
                    .addComponent(modifiedPanelHome, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(336, Short.MAX_VALUE))
        );

        mainBody.add(homePage, "homeCard");

        javax.swing.GroupLayout chatPageLayout = new javax.swing.GroupLayout(chatPage);
        chatPage.setLayout(chatPageLayout);
        chatPageLayout.setHorizontalGroup(
            chatPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPageLayout.createSequentialGroup()
                .addComponent(chatList, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE))
        );
        chatPageLayout.setVerticalGroup(
            chatPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chat, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(chatPageLayout.createSequentialGroup()
                .addComponent(chatList, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mainBody.add(chatPage, "chatCard");

        jSplitPane1.setRightComponent(mainBody);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initChangeUserNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initChangeUserNameButtonActionPerformed
        cardHomePage.show(modifiedPanelHome, "homeChangeUsernameCard");
    }//GEN-LAST:event_initChangeUserNameButtonActionPerformed

    private void homeTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeTabMouseClicked
        cardLayoutMain.show(mainBody, "homeCard");
    }//GEN-LAST:event_homeTabMouseClicked

    private void chatTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatTabMouseClicked
        cardLayoutMain.show(mainBody, "chatCard");
    }//GEN-LAST:event_chatTabMouseClicked

    private void friendListTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friendListTabMouseClicked
        cardLayoutMain.show(mainBody, "friendListCard");
    }//GEN-LAST:event_friendListTabMouseClicked

    private void groupTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_groupTabMouseClicked
        cardLayoutMain.show(mainBody, "groupCard");
    }//GEN-LAST:event_groupTabMouseClicked

    private void initGroupCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initGroupCreateButtonActionPerformed
        cardHomePage.show(modifiedPanelHome, "homeCreateGroupCard");
    }//GEN-LAST:event_initGroupCreateButtonActionPerformed

    private void initChangePassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initChangePassButtonActionPerformed
        cardHomePage.show(modifiedPanelHome, "homeChangePassCard");
    }//GEN-LAST:event_initChangePassButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        var input = JOptionPane.showConfirmDialog(rootPane, "Đăng xuất tải khoản?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            this.dispose();
            new LoginUI().setVisible(true);
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main_user_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_user_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_user_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_user_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_user_ui(currentUser).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changePassButton;
    private javax.swing.JPanel changePassPage;
    private javax.swing.JPanel changeUserNamePage;
    private javax.swing.JButton changeUsernameButton;
    private forSubmitOnly.Chat chat;
    private forSubmitOnly.chatListAndSearch chatList;
    private javax.swing.JPanel chatPage;
    private javax.swing.JLabel chatTab;
    private javax.swing.JButton createGroupButton;
    private javax.swing.JPanel createGroupPage;
    private forSubmitOnly.FriendPage friendListPage;
    private javax.swing.JLabel friendListTab;
    private forSubmitOnly.Chat groupChat;
    private forSubmitOnly.chatListAndSearch groupList;
    private javax.swing.JPanel groupPage;
    private javax.swing.JLabel groupTab;
    private javax.swing.JPanel homePage;
    private javax.swing.JLabel homeTab;
    private javax.swing.JButton initChangePassButton;
    private javax.swing.JButton initChangeUserNameButton;
    private javax.swing.JButton initGroupCreateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> listSelectToGroupCreate;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainBody;
    private javax.swing.JPanel modifiedPanelHome;
    private javax.swing.JPanel sideNavBar;
    private javax.swing.JTextArea textAreaAdmin;
    private javax.swing.JTextField txtChangePass;
    private javax.swing.JTextField txtConfirmNewPass;
    private javax.swing.JTextField txtGroupName;
    private javax.swing.JTextField txtNewPass;
    private javax.swing.JTextField txtOldPass;
    // End of variables declaration//GEN-END:variables
}
