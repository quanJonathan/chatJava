package main_ui;

import UIObject.ChatCard;
import UIObject.FriendCard;
import database.DAO_BanBe;
import database.StringRandomizer;
import entity.BanBe;
import entity.IDPrefix;
import static entity.IDPrefix.IDTinNhan;
import entity.TaiKhoan;
import entity.TinNhan;
import event.EventChat;
import event.PublicEvent;
import swing.ModifiedScrollBar;
import java.awt.CardLayout;
import java.net.Socket;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.json.JSONObject;
import service_client.Service;

public class main_user_ui extends javax.swing.JFrame {

    CardLayout cardLayoutMain;
    CardLayout cardHomePage;
    private static String currentUser;
    private static Service client;

    public main_user_ui(String username, Service socket) {
        initComponents();
        cardLayoutMain = (CardLayout) mainBody.getLayout();
        cardHomePage = (CardLayout) modifiedPanelHome.getLayout();

        currentUser = username;

        client = socket;

        init();
    }

    public final void init() {

        ArrayList<String> usernames = new ArrayList<>();
        usernames.add("luutuanquan");
        usernames.add("bebaoboy");
        // UI for chat page
        
        showAllPersonalChat(usernames);
        
        PublicEvent.getInstance().addEventChat(new EventChat() {
            @Override
            public void sendMessage(String text, String currentChatter) {
                Date time = new Date(System.currentTimeMillis());
                TinNhan mess = new TinNhan(IDPrefix.getIDTinNhan(), time, text, currentUser, currentChatter, "");
                JSONObject object = mess.JSONify();
                object.put("receiver", currentChatter);
                object.put("sender", currentUser);
                System.out.println(object.toString());
                client.al.sendCommand("/sendMessage", object);
                chat.getChatBody().addItemRight(text);
            }

            @Override
            public void receiveMessage(TinNhan mess, String username) {
                chat.getChatBody().addItemLeft(mess, username);
            }

            @Override
            public void setAllChat(ArrayList<TaiKhoan> users) {
            
            }

            @Override
            public void selectUser(String username) {
               chat.getChatBottom().setUser(username);
               chat.getChatTitle().setUserName(username);
               chat.getChatBody().clear();
            }

            @Override
            public void setChatData(ArrayList<TinNhan> messages) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });

        // UI for friend list page
        friendListPanel.setLayout(new MigLayout());
        var friendList = readFriendList();
        showFriendList(friendList);

    }

    public void getPersonalChat() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<BanBe> usersChat = new ArrayList<>();
            }
        });
    }

    public void showAllPersonalChat(ArrayList<String> nameList) {
        chatList.getChatListPanel().removeAll();
        for (int i = 0; i < nameList.size(); i++) {
            chatList.getChatListPanel().add(new ChatCard(nameList.get(i)), "wrap");
        }
        chatList.getChatListPanel().revalidate();
        chatList.getChatListPanel().repaint();
    }

    public void showCurrentChat() {

    }

    public void showFriendList(ArrayList<BanBe> friendList) {
        spFriendList.setVerticalScrollBar(new ModifiedScrollBar());
        spFriendList.setHorizontalScrollBar(new ModifiedScrollBar());
        friendListPanel.removeAll();
        for (int i = 0; i < friendList.size(); i++) {
            friendListPanel.add(new FriendCard(friendList.get(i).getUsernameBanBe()), "wrap");
        }
        friendListPanel.revalidate();
        friendListPanel.repaint();
    }

    public ArrayList<BanBe> readFriendList() {
        ArrayList<BanBe> friendList;
        var daoFriend = new DAO_BanBe();

        friendList = daoFriend.select(" where username = '" + currentUser + "'");

        friendList.forEach((account) -> {
            System.out.println(account);
        });

        return friendList;
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
        groupPage = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel6 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jTextArea2 = new javax.swing.JTextArea();
        jTextField6 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        friendListPage = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        spFriendList = new javax.swing.JScrollPane();
        friendListPanel = new javax.swing.JPanel();
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
        chatPage = new javax.swing.JSplitPane();
        chat = new main_ui.Chat();
        chatList = new main_ui.chatListAndSearch();

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
        setPreferredSize(new java.awt.Dimension(900, 700));

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
                    .addComponent(friendListTab, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
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
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(sideNavBar);

        mainBody.setLayout(new java.awt.CardLayout());

        jTextField5.setText("jTextField5");

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane2.setLeftComponent(jPanel6);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-user-groups-32.png"))); // NOI18N
        jLabel3.setText("Group name");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-ellipsis-50.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("user1: hii\n user2: hiii\n user3: hello ");

        jTextField6.setText("Type sth");

        jButton5.setText("jButton5");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addComponent(jTextArea2, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 411, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addComponent(jTextArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 62, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(jPanel7);

        javax.swing.GroupLayout groupPageLayout = new javax.swing.GroupLayout(groupPage);
        groupPage.setLayout(groupPageLayout);
        groupPageLayout.setHorizontalGroup(
            groupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupPageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        groupPageLayout.setVerticalGroup(
            groupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupPageLayout.createSequentialGroup()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mainBody.add(groupPage, "groupCard");

        friendListPage.setBackground(new java.awt.Color(204, 204, 204));

        jTextField3.setText("bebaoboy");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        spFriendList.setViewportView(friendListPanel);

        javax.swing.GroupLayout friendListPageLayout = new javax.swing.GroupLayout(friendListPage);
        friendListPage.setLayout(friendListPageLayout);
        friendListPageLayout.setHorizontalGroup(
            friendListPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendListPageLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(friendListPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spFriendList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        friendListPageLayout.setVerticalGroup(
            friendListPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendListPageLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(friendListPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(38, 38, 38)
                .addComponent(spFriendList, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainBody.add(friendListPage, "friendListCard");

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
            .addGroup(changePassPageLayout.createSequentialGroup()
                .addGroup(changePassPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changePassPageLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(changePassPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConfirmNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(changePassPageLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(changePassButton)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        changePassPageLayout.setVerticalGroup(
            changePassPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePassPageLayout.createSequentialGroup()
                .addGap(20, 20, 20)
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
                .addContainerGap(59, Short.MAX_VALUE))
        );

        modifiedPanelHome.add(changePassPage, "homeChangePassCard");

        jLabel6.setText("Tên hiển thị mới");

        jLabel7.setText("Nhập mật khẩu xác nhận");

        changeUsernameButton.setText("Đổi tên hiển thị");

        javax.swing.GroupLayout changeUserNamePageLayout = new javax.swing.GroupLayout(changeUserNamePage);
        changeUserNamePage.setLayout(changeUserNamePageLayout);
        changeUserNamePageLayout.setHorizontalGroup(
            changeUserNamePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeUserNamePageLayout.createSequentialGroup()
                .addGroup(changeUserNamePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changeUserNamePageLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(changeUserNamePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(changeUserNamePageLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(changeUsernameButton)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        changeUserNamePageLayout.setVerticalGroup(
            changeUserNamePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeUserNamePageLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(changeUsernameButton)
                .addContainerGap(113, Short.MAX_VALUE))
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
            .addGroup(createGroupPageLayout.createSequentialGroup()
                .addGroup(createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createGroupPageLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createGroupPageLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(txtGroupName, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                .addComponent(jScrollPane1))
                            .addComponent(jLabel5)))
                    .addGroup(createGroupPageLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(createGroupButton)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        createGroupPageLayout.setVerticalGroup(
            createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createGroupPageLayout.createSequentialGroup()
                .addContainerGap()
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        modifiedPanelHome.add(createGroupPage, "homeCreateGroupCard");

        javax.swing.GroupLayout homePageLayout = new javax.swing.GroupLayout(homePage);
        homePage.setLayout(homePageLayout);
        homePageLayout.setHorizontalGroup(
            homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageLayout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(initChangeUserNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(initChangePassButton)
                    .addComponent(initGroupCreateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(modifiedPanelHome, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 211, Short.MAX_VALUE))
        );
        homePageLayout.setVerticalGroup(
            homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageLayout.createSequentialGroup()
                .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homePageLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(initGroupCreateButton)
                        .addGap(52, 52, 52)
                        .addComponent(initChangePassButton)
                        .addGap(52, 52, 52)
                        .addComponent(initChangeUserNameButton))
                    .addGroup(homePageLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(modifiedPanelHome, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainBody.add(homePage, "homeCard");

        chatPage.setPreferredSize(new java.awt.Dimension(800, 800));
        chatPage.setRightComponent(chat);
        chatPage.setLeftComponent(chatList);

        mainBody.add(chatPage, "chatCard");

        jSplitPane1.setRightComponent(mainBody);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void initGroupCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initGroupCreateButtonActionPerformed
        cardHomePage.show(modifiedPanelHome, "homeCreateGroupCard");
    }//GEN-LAST:event_initGroupCreateButtonActionPerformed

    private void initChangePassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initChangePassButtonActionPerformed
        cardHomePage.show(modifiedPanelHome, "homeChangePassCard");
    }//GEN-LAST:event_initChangePassButtonActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_user_ui(currentUser, client).setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changePassButton;
    private javax.swing.JPanel changePassPage;
    private javax.swing.JPanel changeUserNamePage;
    private javax.swing.JButton changeUsernameButton;
    private main_ui.Chat chat;
    private main_ui.chatListAndSearch chatList;
    private javax.swing.JSplitPane chatPage;
    private javax.swing.JLabel chatTab;
    private javax.swing.JButton createGroupButton;
    private javax.swing.JPanel createGroupPage;
    private javax.swing.JPanel friendListPage;
    private javax.swing.JPanel friendListPanel;
    private javax.swing.JLabel friendListTab;
    private javax.swing.JPanel groupPage;
    private javax.swing.JLabel groupTab;
    private javax.swing.JPanel homePage;
    private javax.swing.JLabel homeTab;
    private javax.swing.JButton initChangePassButton;
    private javax.swing.JButton initChangeUserNameButton;
    private javax.swing.JButton initGroupCreateButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JList<String> listSelectToGroupCreate;
    private javax.swing.JPanel mainBody;
    private javax.swing.JPanel modifiedPanelHome;
    private javax.swing.JPanel sideNavBar;
    private javax.swing.JScrollPane spFriendList;
    private javax.swing.JTextArea textAreaAdmin;
    private javax.swing.JTextField txtChangePass;
    private javax.swing.JTextField txtConfirmNewPass;
    private javax.swing.JTextField txtGroupName;
    private javax.swing.JTextField txtNewPass;
    private javax.swing.JTextField txtOldPass;
    // End of variables declaration//GEN-END:variables
}
