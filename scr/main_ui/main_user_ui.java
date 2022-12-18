package main_ui;

import UIObject.GroupCard;
import authentication_ui.LoginUI;
import entity.BanBe;
import entity.IDPrefix;
import entity.NhomChat;
import entity.TaiKhoan;
import entity.ThanhVienNhomChat;
import entity.TinNhan;
import event.EventChat;
import event.EventFriend;
import event.EventGroupChat;
import event.EventMain;
import event.PublicEvent;
import java.awt.CardLayout;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
import service_client.Service;

public class main_user_ui extends javax.swing.JFrame {

    CardLayout cardLayoutMain;
    CardLayout cardHomePage;
    private static TaiKhoan currentUser;
    private ArrayList<BanBe> friends;
    ArrayList<NhomChat> groups = new ArrayList<>();

    public main_user_ui(TaiKhoan username) {
        initComponents();
        setLocationRelativeTo(null);

        cardLayoutMain = (CardLayout) mainBody.getLayout();
        cardHomePage = (CardLayout) modifiedPanelHome.getLayout();

        chat.setVisible(false);
        groupChat.setVisible(false);
        currentUser = username;

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Service.getInstance().al.sendCommand("/shutDown", currentUser.JSONify());
            }
        });

        System.out.println(currentUser);

        initUI();
        initEvent();
        PublicEvent.getInstance().getEventMain().navigateToHomePage();
    }

    public final void initUI() {
        chat.getChatBottom().setSender(currentUser);
        friends = new ArrayList<>();

        lblUsername.setText(currentUser.getUsername());

        readFriendList();
        readFriendRequestList();
//        readChatList();
//        readGroupChatList();

        memberGroupCreate.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (index0 == index1) {
                    if (isSelectedIndex(index0)) {
                        removeSelectionInterval(index0, index0);
                        return;
                    }
                }
                super.setSelectionInterval(index0, index1);
            }

            @Override
            public void addSelectionInterval(int index0, int index1) {
                if (index0 == index1) {
                    if (isSelectedIndex(index0)) {
                        removeSelectionInterval(index0, index0);
                        return;
                    }
                    super.addSelectionInterval(index0, index1);
                }
            }

        });

        adminGroupCreate.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (index0 == index1) {
                    if (isSelectedIndex(index0)) {
                        removeSelectionInterval(index0, index0);
                        return;
                    }
                }
                super.setSelectionInterval(index0, index1);
            }

            @Override
            public void addSelectionInterval(int index0, int index1) {
                if (index0 == index1) {
                    if (isSelectedIndex(index0)) {
                        removeSelectionInterval(index0, index0);
                        return;
                    }
                    super.addSelectionInterval(index0, index1);
                }
            }

        });
    }

    public final void initEvent() {
        PublicEvent.getInstance().addEventMain(new EventMain() {
            @Override
            public void navigateToChatPage() {
                cardLayoutMain.show(mainBody, "chatCard");
            }

            @Override
            public void navigateToGroupChatPage() {
                cardLayoutMain.show(mainBody, "groupCard");
            }

            @Override
            public void navigateToHomePage() {
                cardLayoutMain.show(mainBody, "homeCard");
            }

            @Override
            public void navigateToFriendPage() {
                cardLayoutMain.show(mainBody, "friendListCard");
            }

            @Override
            public void showDialog(String message, String title) {
                JOptionPane.showConfirmDialog(rootPane, message, title, JOptionPane.OK_CANCEL_OPTION);
            }
        });

        PublicEvent.getInstance().addEventChat(new EventChat() {
            @Override
            public void sendMessage(TinNhan mess) {
                JSONObject object = mess.JSONify();
                System.out.println(object.toString());
                Service.getInstance().al.sendCommand("/sendMessage", object);
                chat.getChatBody().addItemRight(mess);
                readChatList();
            }

            @Override
            public void receiveMessage(TinNhan mess) {
                chat.addMessage(mess);
            }

            @Override
            public void setUser(TaiKhoan user) {
                chat.setUser(user);
                chat.setVisible(true);
            }

            @Override
            public void requestData(TaiKhoan chatter) {
                var user1 = currentUser.getUsername();
                var user2 = chatter.getUsername();
                var object = new JSONObject();
                object.put("current", user1);
                object.put("chatter", user2);
                Service.getInstance().al.sendCommand("/getChatData", object);
            }

            @Override
            public void setChatData(ArrayList<TinNhan> messages) {
                //System.out.println(messages);
                chat.setChatData(messages);
            }

            @Override
            public void updateUser(TaiKhoan user) {
                chat.updateUser(user);
            }

            @Override
            public void deleteChat(TaiKhoan user) {
                chat.setVisible(false);
                JSONObject object = new JSONObject();
                object.put("chatter", user.getUsername());
                object.put("user", currentUser.getUsername());
                Service.getInstance().al.sendCommand("/deleteChatHistory", object);
                chatList.refreshChatListPanel();
                chatList.removeUser(user);
                readChatList();

            }

            @Override
            public void searchFromAUser(TaiKhoan banbe, String text) {
                System.out.println("search query= " + text);
                Service.getInstance().al.sendCommand("/getMessageSearch",
                        new JSONObject().put("username", currentUser.getUsername())
                                .put("usernameBanBe", banbe.getUsername())
                                .put("text", text));
            }

            @Override
            public void searchFromAllUser(String text) {
                System.out.println("search query= " + text);
                Service.getInstance().al.sendCommand("/getAllMessageSearch",
                        new JSONObject().put("username", currentUser.getUsername())
                                .put("text", text));
            }

            @Override
            public void setSearchData(ArrayList<TinNhan> messages, ArrayList<String> names) {
                System.out.println("");
                System.out.println("search result: ");
                messages.forEach(item -> {
                    System.out.println(item);
                });
                
                var messageSearch = new MessageSearch();
                messageSearch.showSearchResult(messages, names);
                messageSearch.setVisible(true);
            }
        });

        PublicEvent.getInstance().addEventFriend(new EventFriend() {
            @Override
            public void setData(ArrayList<BanBe> friendList, ArrayList<Integer> status) {
                friends = friendList;
                friendListPage.setFriendList(friendList, status);
                friendListPage.showAllFriend();

            }

            @Override
            public void unfriend(BanBe user) {
                user = new BanBe(currentUser.getUsername(), user.getUsernameBanBe(), user.getNgayKetBan());
                Service.getInstance().al.sendCommand("/unfriend", user.JSONify());
                readFriendList();
            }

            @Override
            public void setFriendSearchData(ArrayList<BanBe> friendList) {
                friendListPage.showAllSearch(friendList);
            }

            @Override
            public void getFriendSearchData(String text) {
                Service.getInstance().al.sendCommand("/getSearchFriendList", new JSONObject().put("text", text).put("username", currentUser.getUsername()));
            }

            @Override
            public void setFriendRequestData(ArrayList<BanBe> friendList) {
                friendList.forEach(item -> {
                    System.out.println(item);
                });
                friendListPage.showAllFriendRequest(friendList);
            }

            @Override
            public void getFriendRequestData(String user) {
                readFriendRequestList();
            }

            @Override
            public void addFriend(String usernameBanBe) {
                Service.getInstance().al.sendCommand("/addFriend", new JSONObject().put("username", currentUser.getUsername()).put("usernameBanBe", usernameBanBe));

            }
        });

        PublicEvent.getInstance().addEventGroupChat(new EventGroupChat() {
            @Override
            public void getGroupMember(NhomChat group) {
                Service.getInstance().al.sendCommand("/getGroupMember", group.JSONify());
            }

            @Override
            public void setGroupChatData(ArrayList<TinNhan> messages) {

                groupChat.setVisible(true);
                groupChat.setGroupData(messages);

            }

            @Override
            public void deleteCurrentGroupData(NhomChat user) {

            }

            @Override
            public void setAdmin(NhomChat group, ArrayList<ThanhVienNhomChat> users) {
                JSONArray array = new JSONArray();
                for (var tk : users) {
                    array.put(tk.JSONify());
                }
                var object = new JSONObject();
                object.put("group", group.JSONify());
                object.put("members", array);
                Service.getInstance().al.sendCommand("/addNewAdmin", object);
            }

            @Override
            public void setNewGroupName(NhomChat group, String newName) {
                var object = new JSONObject();
                object.put("newName", newName);
                object.put("group", group.JSONify());
                Service.getInstance().al.sendCommand("/changeGroupName", object);
            }

            @Override
            public void requestGroupData(NhomChat group) {
                JSONObject object = new JSONObject();
                object.put("name", currentUser.getUsername());
                object.put("group", group.JSONify());
                Service.getInstance().al.sendCommand("/getGroupData", object);
            }

            @Override
            public void setGroup(NhomChat group) {
                groupChat.setGroup(group, currentUser);
                groupChat.setVisible(true);
                groupChat.setUser(currentUser);
//                System.out.println(group);
            }

            @Override
            public void sendMessage(NhomChat group, TinNhan message) {
                groupChat.addMessage(message);
                JSONObject object = new JSONObject();
                object.put("group", group.JSONify());
                object.put("mess", message.JSONify());
                Service.getInstance().al.sendCommand("/groupChatSendMessage", object);
            }

            @Override
            public void receiveMessage(NhomChat group, TinNhan messages) {
                groupChat.getGroupChatBody().addItemLeft(messages);
            }

            @Override
            public void requestMemberData(NhomChat group) {
                Service.getInstance().al.sendCommand("/getMember", group.JSONify());
            }

            @Override
            public void insertMember(String id, ArrayList<ThanhVienNhomChat> users) {
                JSONArray array = new JSONArray();
                for (var tk : users) {
                    array.put(tk.JSONify());
                }
                Service.getInstance().al.sendCommand("/addMember", new JSONObject().put("group", id).put("user", array));
                readFriendList();
                readGroupChatList();
                groupChatList.setFriend(friends);
            }

            @Override
            public void removeMember(String id, ArrayList<ThanhVienNhomChat> users) {
                JSONArray array = new JSONArray();
                for (var tk : users) {
                    array.put(tk.JSONify());
                }
                Service.getInstance().al.sendCommand("/removeMember", new JSONObject().put("group", id).put("user", array));
                readFriendList();
                readGroupChatList();
                groupChatList.setFriend(friends);

            }
        });
    }

//  chat demo data
//    private void showAllPersonalChat() {
//        chatList.getChatListPanel().removeAll();
//        for (TaiKhoan t : usernames) {
//            if (!t.getUsername().equals(currentUser.getUsername())) {
//                chatList.getChatListPanel().add(new ChatCard(t), "wrap");
//            }
//        }
//        chatList.getChatListPanel().revalidate();
//        chatList.getChatListPanel().repaint();
//    }
    //Group chat demo data
//    private void showAllGroupChat() {
//        groupList.getChatListPanel().removeAll();
//        for (NhomChat group : groups) {
//            groupList.getChatListPanel().add(new GroupCard(group.getTenNhom()), "wrap");
//        }
//        GroupCard c = (GroupCard) groupList.getChatListPanel().getComponent(0);
//        c.forAdmin();
//
//        groupChat.getGroupChatBody().addItemLeft(new TinNhan("1", new Date(System.currentTimeMillis()), "Hii group", "bebaoboy", "", "groups1", "bebaoboy"));
//        groupChat.getGroupChatBody().addItemLeft(new TinNhan("1", new Date(System.currentTimeMillis()), "Hii group", "reika", "", "groups1", "reika"));
//        groupChat.getGroupChatBody().addItemLeft(new TinNhan("1", new Date(System.currentTimeMillis()), "Hii group", "meow", "", "groups1", "meow"));
//
//        groupChat.getGroupChatBody().addItemRight(new TinNhan("1", new Date(System.currentTimeMillis()), "Hii group", "luutuanquan", "", "groups1", "luutuanquan"));
//
//        //groupChat.getGroupChatTitle().setUserName("groups1");
//    }
    private void readGroupChatList() {
        Service.getInstance().al.sendCommand("/getGroupChatList", currentUser.JSONify());
    }

    private void readChatList() {
        chatList.refreshChatListPanel();
        Service.getInstance().al.sendCommand("/getChatList", currentUser.JSONify());
    }

    private void readFriendList() {
        Service.getInstance().al.sendCommand("/getFriendList", currentUser.JSONify());
    }

    private void readFriendRequestList() {
        Service.getInstance().al.sendCommand("/getFriendRequestList", new JSONObject().put("username", currentUser.getUsername()));
    }

    private void setGroupFriendList() {
        ArrayList<NhomChat> groups = groupChatList.getGroup();
        System.out.println("group at main = " + groups);
        for (int i = 0; i < groups.size(); i++) {
            var groupCard = (GroupCard) groupChatList.getGroupChatListPanel().getComponent(i);
            groupCard.setFriendList(friends);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        sideNavBar = new javax.swing.JPanel();
        homeTab = new javax.swing.JLabel();
        friendListTab = new javax.swing.JLabel();
        chatTab = new javax.swing.JLabel();
        groupTab = new javax.swing.JLabel();
        mainBody = new javax.swing.JPanel();
        friendListPage = new main_ui.FriendPage();
        groupPage = new javax.swing.JPanel();
        groupChatList = new main_ui.GroupChatListAndSearch();
        groupChat = new main_ui.GroupChat();
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
        lblError = new javax.swing.JLabel();
        changeUserNamePage = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNewName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtConfirmPass = new javax.swing.JTextField();
        changeUsernameButton = new javax.swing.JButton();
        createGroupPage = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtGroupName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        memberGroupCreate = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        createGroupButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        adminGroupCreate = new javax.swing.JList<>();
        logoutButton = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        chatPage = new javax.swing.JPanel();
        chatList = new main_ui.chatListAndSearch();
        chat = new main_ui.Chat();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ChatJava");
        setResizable(false);

        sideNavBar.setBackground(new java.awt.Color(255, 255, 255));
        sideNavBar.setMaximumSize(new java.awt.Dimension(99, 753));

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
                .addGap(109, 109, 109)
                .addComponent(homeTab)
                .addGap(90, 90, 90)
                .addComponent(chatTab)
                .addGap(91, 91, 91)
                .addComponent(friendListTab)
                .addGap(98, 98, 98)
                .addComponent(groupTab)
                .addContainerGap(241, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(sideNavBar);

        mainBody.setLayout(new java.awt.CardLayout());
        mainBody.add(friendListPage, "friendListCard");

        javax.swing.GroupLayout groupPageLayout = new javax.swing.GroupLayout(groupPage);
        groupPage.setLayout(groupPageLayout);
        groupPageLayout.setHorizontalGroup(
            groupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupPageLayout.createSequentialGroup()
                .addComponent(groupChatList, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupChat, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE))
        );
        groupPageLayout.setVerticalGroup(
            groupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(groupChatList, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
            .addGroup(groupPageLayout.createSequentialGroup()
                .addComponent(groupChat, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
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
        changePassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changePassPageLayout = new javax.swing.GroupLayout(changePassPage);
        changePassPage.setLayout(changePassPageLayout);
        changePassPageLayout.setHorizontalGroup(
            changePassPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changePassPageLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(changePassPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirmNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(changePassPageLayout.createSequentialGroup()
                .addGroup(changePassPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changePassPageLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(changePassButton))
                    .addGroup(changePassPageLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changePassButton)
                .addGap(22, 22, 22))
        );

        modifiedPanelHome.add(changePassPage, "homeChangePassCard");

        jLabel6.setText("Tên hiển thị mới");

        jLabel7.setText("Nhập mật khẩu xác nhận");

        changeUsernameButton.setText("Đổi tên hiển thị");
        changeUsernameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeUsernameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changeUserNamePageLayout = new javax.swing.GroupLayout(changeUserNamePage);
        changeUserNamePage.setLayout(changeUserNamePageLayout);
        changeUserNamePageLayout.setHorizontalGroup(
            changeUserNamePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeUserNamePageLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(changeUserNamePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewName, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(txtNewName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(changeUsernameButton)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        modifiedPanelHome.add(changeUserNamePage, "homeChangeUsernameCard");

        jLabel1.setText("Nhập tên nhóm");

        memberGroupCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                memberGroupCreateMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(memberGroupCreate);

        jLabel2.setText("Chọn thành viên nhóm");

        jLabel5.setText("Chọn admin");

        createGroupButton.setText("Tạo nhóm");
        createGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGroupButtonActionPerformed(evt);
            }
        });

        adminGroupCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminGroupCreateMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(adminGroupCreate);

        javax.swing.GroupLayout createGroupPageLayout = new javax.swing.GroupLayout(createGroupPage);
        createGroupPage.setLayout(createGroupPageLayout);
        createGroupPageLayout.setHorizontalGroup(
            createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createGroupPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createGroupPageLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(createGroupButton)
                        .addGap(100, 100, 100))
                    .addGroup(createGroupPageLayout.createSequentialGroup()
                        .addGroup(createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createGroupPageLayout.createSequentialGroup()
                        .addGroup(createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, createGroupPageLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtGroupName)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(16, 16, 16))))
        );
        createGroupPageLayout.setVerticalGroup(
            createGroupPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createGroupPageLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createGroupButton)
                .addGap(21, 21, 21))
        );

        modifiedPanelHome.add(createGroupPage, "homeCreateGroupCard");

        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-log-out-32.png"))); // NOI18N
        logoutButton.setText("Đăng xuất");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUsername.setText("username");

        javax.swing.GroupLayout homePageLayout = new javax.swing.GroupLayout(homePage);
        homePage.setLayout(homePageLayout);
        homePageLayout.setHorizontalGroup(
            homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageLayout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(initChangeUserNameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(initGroupCreateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(initChangePassButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90)
                .addComponent(modifiedPanelHome, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePageLayout.createSequentialGroup()
                .addContainerGap(381, Short.MAX_VALUE)
                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(386, 386, 386))
        );
        homePageLayout.setVerticalGroup(
            homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePageLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblUsername)
                .addGap(52, 52, 52)
                .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modifiedPanelHome, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(homePageLayout.createSequentialGroup()
                        .addComponent(initGroupCreateButton)
                        .addGap(80, 80, 80)
                        .addComponent(initChangePassButton)
                        .addGap(76, 76, 76)
                        .addComponent(initChangeUserNameButton)
                        .addGap(76, 76, 76)
                        .addComponent(logoutButton)))
                .addContainerGap(247, Short.MAX_VALUE))
        );

        mainBody.add(homePage, "homeCard");

        javax.swing.GroupLayout chatPageLayout = new javax.swing.GroupLayout(chatPage);
        chatPage.setLayout(chatPageLayout);
        chatPageLayout.setHorizontalGroup(
            chatPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPageLayout.createSequentialGroup()
                .addComponent(chatList, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE))
        );
        chatPageLayout.setVerticalGroup(
            chatPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chatList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(chat, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
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
        readFriendList();
        PublicEvent.getInstance().getEventMain().navigateToHomePage();
    }//GEN-LAST:event_homeTabMouseClicked

    private void chatTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatTabMouseClicked

        readChatList();
        PublicEvent.getInstance().getEventMain().navigateToChatPage();
    }//GEN-LAST:event_chatTabMouseClicked

    private void friendListTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friendListTabMouseClicked
        readFriendList();
        readFriendRequestList();
        friendListPage.showAllSearch(new ArrayList<>());
        PublicEvent.getInstance().getEventMain().navigateToFriendPage();
    }//GEN-LAST:event_friendListTabMouseClicked

    private void groupTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_groupTabMouseClicked
        readFriendList();
        readGroupChatList();
        PublicEvent.getInstance().getEventMain().navigateToGroupChatPage();

        groupChatList.setFriend(friends);
    }//GEN-LAST:event_groupTabMouseClicked

    private void initGroupCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initGroupCreateButtonActionPerformed
        DefaultListModel listModel = new DefaultListModel(), listModel2 = new DefaultListModel();
        readFriendList();
        for (BanBe b : friends) {
            listModel.addElement(b.getUsernameBanBe());
            listModel2.addElement(b.getUsernameBanBe());
        }
        this.memberGroupCreate.setModel(listModel);
        this.adminGroupCreate.setModel(listModel2);
        cardHomePage.show(modifiedPanelHome, "homeCreateGroupCard");
    }//GEN-LAST:event_initGroupCreateButtonActionPerformed

    private void initChangePassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initChangePassButtonActionPerformed
        cardHomePage.show(modifiedPanelHome, "homeChangePassCard");
    }//GEN-LAST:event_initChangePassButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        var input = JOptionPane.showConfirmDialog(rootPane, "Đăng xuất tải khoản?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            Service.getInstance().al.sendCommand("/logout", currentUser.JSONify());
            this.dispose();
            new LoginUI().setVisible(true);
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void changePassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassButtonActionPerformed
        String oldPass = txtOldPass.getText();
        String newPass = txtNewPass.getText();
        String newPassConfirm = txtConfirmNewPass.getText();
        if (newPass.equals(oldPass)) {
            PublicEvent.getInstance().getEventMain().showDialog("New password cannot be the same as your old passwords!", "Invalid password");
            return;
        }

        if (!newPass.equals(newPassConfirm)) {
            lblError.setText("Mật khẩu mới không khớp");
        } else {
            JSONObject object = new JSONObject();
            object.put("oldPass", oldPass);
            object.put("newPass", newPass);
            object.put("user", currentUser.getUsername());
            Service.getInstance().al.sendCommand("/changePass", object);
        }
    }//GEN-LAST:event_changePassButtonActionPerformed

    private void changeUsernameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeUsernameButtonActionPerformed
        String newName = txtNewName.getText();
        String passWord = txtConfirmPass.getText();
        if (newName.equals(currentUser.getUsername())) {
            PublicEvent.getInstance().getEventMain().showDialog("New username is the same!", "Invalid name");
            return;
        }

        JSONObject object = new JSONObject();
        object.put("password", passWord);
        object.put("newName", newName);
        object.put("user", currentUser.getUsername());

        Service.getInstance().al.sendCommand("/changeUsername", object);
    }//GEN-LAST:event_changeUsernameButtonActionPerformed

    private void createGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGroupButtonActionPerformed
        var newGroup = new NhomChat(IDPrefix.getIDNhomChat(), txtGroupName.getText(), new Timestamp(System.currentTimeMillis()));

        ArrayList<ThanhVienNhomChat> members = new ArrayList<>();
        memberGroupCreate.getSelectedValuesList().forEach(mem -> {
            members.add(new ThanhVienNhomChat(newGroup.getIDNhom(), mem, false, new Timestamp(System.currentTimeMillis())));
        });
        ArrayList<ThanhVienNhomChat> admins = new ArrayList<>();
        adminGroupCreate.getSelectedValuesList().forEach(mem -> {
            admins.add(new ThanhVienNhomChat(newGroup.getIDNhom(), mem, true, new Timestamp(System.currentTimeMillis())));
        });
        admins.add(new ThanhVienNhomChat(newGroup.getIDNhom(), currentUser.getUsername(), true, new Timestamp(System.currentTimeMillis())));
        members.addAll(admins);
        members.forEach(mem -> {
            System.out.println(mem);
        });

        var memberArray = new JSONArray();
        for (ThanhVienNhomChat t : members) {
            memberArray.put(t.JSONify());
        }

        var object = new JSONObject();
        object.put("group", newGroup.JSONify());
        object.put("members", memberArray);

        Service.getInstance().al.sendCommand("/createGroup", object);
    }//GEN-LAST:event_createGroupButtonActionPerformed

    private void memberGroupCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_memberGroupCreateMouseClicked
        var memSelected = memberGroupCreate.getSelectedValuesList();
        System.out.println("mem=");
        memSelected.forEach(i -> {
            System.out.print(i);
        });
        System.out.println("");
        DefaultListModel listModel = new DefaultListModel();
        var adminSelected = adminGroupCreate.getSelectedValuesList();
        System.out.println("admin=");
        adminSelected.forEach(i -> {
            System.out.print(i);
        });
        System.out.println("");

        if (memSelected.isEmpty()) {
            for (var k : friends) {
                listModel.addElement(k.getUsernameBanBe());
            }
        } else {
            for (var k : friends) {
                boolean exists = false;
                for (var i : memSelected) {
                    if (k.getUsernameBanBe().equals(i)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    listModel.addElement(k.getUsernameBanBe());
                }
            }
        }
        adminGroupCreate.setModel(listModel);
        var idx = new ArrayList<Integer>();
        adminSelected.forEach(a -> {
            for (int i = 0; i < listModel.getSize(); i++) {
                if (adminGroupCreate.getModel().getElementAt(i).equals(a)) {
                    idx.add(i);
                }
            }
        });
        adminGroupCreate.setSelectedIndices(convertIntegers(idx));


    }//GEN-LAST:event_memberGroupCreateMouseClicked
    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
    private void adminGroupCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminGroupCreateMouseClicked
        var adminSelected = adminGroupCreate.getSelectedValuesList();
        System.out.println("admin=");
        adminSelected.forEach(i -> {
            System.out.print(i);
        });
        System.out.println("");
        DefaultListModel listModel = new DefaultListModel();
        var memSelected = memberGroupCreate.getSelectedValuesList();
        System.out.println("mem=");
        memSelected.forEach(i -> {
            System.out.print(i);
        });
        System.out.println("");

        if (adminSelected.isEmpty()) {
            for (var k : friends) {
                listModel.addElement(k.getUsernameBanBe());
            }
        } else {
            for (var k : friends) {
                boolean exists = false;
                for (var i : adminSelected) {
                    if (k.getUsernameBanBe().equals(i)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    listModel.addElement(k.getUsernameBanBe());
                }
            }
        }
        memberGroupCreate.setModel(listModel);
        var idx = new ArrayList<Integer>();
        memSelected.forEach(a -> {
            for (int i = 0; i < listModel.getSize(); i++) {
                if (memberGroupCreate.getModel().getElementAt(i).equals(a)) {
                    idx.add(i);
                }
            }
        });
        memberGroupCreate.setSelectedIndices(convertIntegers(idx));
    }//GEN-LAST:event_adminGroupCreateMouseClicked

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
                new main_user_ui(currentUser).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> adminGroupCreate;
    private javax.swing.JButton changePassButton;
    private javax.swing.JPanel changePassPage;
    private javax.swing.JPanel changeUserNamePage;
    private javax.swing.JButton changeUsernameButton;
    private main_ui.Chat chat;
    private main_ui.chatListAndSearch chatList;
    private javax.swing.JPanel chatPage;
    private javax.swing.JLabel chatTab;
    private javax.swing.JButton createGroupButton;
    private javax.swing.JPanel createGroupPage;
    private main_ui.FriendPage friendListPage;
    private javax.swing.JLabel friendListTab;
    private main_ui.GroupChat groupChat;
    private main_ui.GroupChatListAndSearch groupChatList;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainBody;
    private javax.swing.JList<String> memberGroupCreate;
    private javax.swing.JPanel modifiedPanelHome;
    private javax.swing.JPanel sideNavBar;
    private javax.swing.JTextField txtConfirmNewPass;
    private javax.swing.JTextField txtConfirmPass;
    private javax.swing.JTextField txtGroupName;
    private javax.swing.JTextField txtNewName;
    private javax.swing.JTextField txtNewPass;
    private javax.swing.JTextField txtOldPass;
    // End of variables declaration//GEN-END:variables
}
