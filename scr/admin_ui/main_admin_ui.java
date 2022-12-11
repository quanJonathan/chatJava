/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package admin_ui;

import database.DAO_BanBe;
import database.DAO_LSDangNhap;
import database.DAO_NhomChat;
import database.DAO_TaiKhoan;
import database.database_helper;
import entity.LichSuDangNhap;
import entity.TaiKhoan;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main_ui.DateLabelFormatter;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

/**
 *
 * @author ADMIN
 */
public class main_admin_ui extends javax.swing.JFrame {

    /**
     * Creates new form online_user
     */
    CardLayout cardLayout;
    CardLayout cardLayOutHomePage;

    public main_admin_ui() {
        initComponents();

        cardLayout = (CardLayout) pnlCard.getLayout();
        cardLayOutHomePage = (CardLayout) pnlCardEdit.getLayout();

        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        userTable.setModel(model);
        for (int count = 1; count <= 10; count++) {
            model.addRow(new Object[]{"", ""});
        }

        db = new database_helper();

        init();
    }

    public void init() {
        groupDetailPanel.setLayout(new MigLayout());
        lbHome.setForeground(Color.white);
        this.dateModel = new SqlDateModel();
        dateModel.setDate(2002, 0, 1);
        dateModel.setSelected(true);
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        jDatePanel.setLayout(new GridLayout(1, 5));
        jDatePanel.add(datePicker);

        this.dateModel2 = new SqlDateModel();
        dateModel2.setDate(2002, 0, 1);
        dateModel2.setSelected(true);
        datePanel2 = new JDatePanelImpl(dateModel2, p);
        datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        jDatePanel2.setLayout(new GridLayout(1, 5));
        jDatePanel2.add(datePicker2);

        jDatePanel2.revalidate();
        jDatePanel2.repaint();

        searchGroup.setVisible(false);
        searchGroupIcon.setVisible(false);
        cbGroupName.setVisible(false);

        showAllUser(true);
        showAllGroup();
    }

    public ArrayList<TaiKhoan> getAllUser() {
        return null;
    }

    public void showAllUser(boolean dateFirst) {
        var orderQuery = new ArrayList<String>();
        if (DateSort.getSelectedIndex() != 0) {
            if (DateSort.getSelectedIndex() == 1) {
                orderQuery.add("ngaydangxuat asc");
            } else {
                orderQuery.add("ngaydangxuat desc");
            }
        }
        if (NameSort.getSelectedIndex() != 0) {

            if (NameSort.getSelectedIndex() == 1) {
                if (NameSortCondition.getSelectedIndex() == 0) {
                    orderQuery.add("taikhoan.username asc");
                } else {
                    orderQuery.add("fullname asc");
                }
            } else {
                if (NameSortCondition.getSelectedIndex() == 0) {
                    orderQuery.add("taikhoan.username desc");
                } else {
                    orderQuery.add("fullname desc");
                }
            }
        }
        if (!dateFirst) {
            Collections.reverse(orderQuery);
        }
        var orderQueries = "";
        if (!orderQuery.isEmpty()) {
            orderQueries = "order by " + String.join(",", orderQuery);
        }
//        if (DateSort.getSelectedIndex() != 0) {
//            if (DateSort.getSelectedIndex() == 1) {
//                orderQuery += "order by ngaydangxuat asc";
//            } else {
//                orderQuery += "order by ngaydangxuat desc";
//            }
//        }
//        if (NameSort.getSelectedIndex() != 0) {
//            if (DateSort.getSelectedIndex() == 0) {
//                orderQuery += "order by ";
//            } else {
//                orderQuery += ", ";
//            }
//
//            if (NameSort.getSelectedIndex() == 1) {
//                if (NameSortCondition.getSelectedIndex() == 0) {
//                    orderQuery += "taikhoan.username asc";
//                } else {
//                    orderQuery += "fullname asc";
//                }
//            } else {
//                if (NameSortCondition.getSelectedIndex() == 0) {
//                    orderQuery += "taikhoan.username desc";
//                } else {
//                    orderQuery += "fullname desc";
//                }
//            }
//        }
        var r = database_helper.select("""
                                       select taikhoan.username, fullname, ngaydangxuat, trangthai from taikhoan left join lichsudangnhap ls1 on taikhoan.username = ls1.username where ls1.NgayDangXuat >= all (
                                       \tselect ls2.ngaydangxuat from LichSuDangNhap ls2
                                       \twhere ls2.Username = ls1.Username
                                       )
                                       """ + orderQueries);
        var logw = new ArrayList<LichSuDangNhap>();
        var acc = new ArrayList<TaiKhoan>();
        try {
            while (r.next()) {
                acc.add(new TaiKhoan(r.getNString(1), "", "").setTrangThai(r.getInt(4)).setFullName(r.getNString(2)));
                logw.add(new LichSuDangNhap(r.getNString(1), r.getTimestamp(3), r.getTimestamp(3)));
            }
        } catch (SQLException ex) {
        }
        addRow(userTable, acc.size());
        for (int i = 0; i < acc.size(); i++) {
            System.out.println(acc.get(i));
            var status = acc.get(i).getTrangThai();
            userTable.setValueAt(status == 1 ? "Active" : status == 0 ? "Offline" : "Locked", i, 3);
            userTable.setValueAt(DateLabelFormatter.dateToTime(logw.get(i).getNgayDangXuat()), i, 2);
            userTable.setValueAt(acc.get(i).getFullName(), i, 1);
            userTable.setValueAt(acc.get(i).getUsername(), i, 0);
        }

//        DAO_LSDangNhap log = new DAO_LSDangNhap();
//        var result = log.select("ls1\n"
//                + "where ls1.NgayDangXuat >= all (\n"
//                + "	select ls2.ngaydangxuat from LichSuDangNhap ls2\n"
//                + "	where ls2.Username = ls1.Username\n"
//                + ")\n"
//                + "order by NgayDangXuat desc");
//
//        var result2 = new DAO_TaiKhoan().select("where username not in (select distinct username from lichsudangnhap)");
//        addRow(userTable, result2.size() + result.size());
//
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println(result.get(i));
//            var acc = new DAO_TaiKhoan().select("where username=N'" + result.get(i).getUsername() + "'");
//            var status = acc.get(0).getTrangThai();
//            userTable.setValueAt(status == 1 ? "Active" : status == 0 ? "Offline" : "Locked", i, 3);
//            userTable.setValueAt(acc.get(0).getFullName(), i, 1);
//        }
//
//        for (int i = 0; i < result.size(); i++) {
//            userTable.setValueAt(result.get(i).getUsername(), i, 0);
//            userTable.setValueAt(DateLabelFormatter.dateToTime(result.get(i).getNgayDangXuat()), i, 2);
//        }
//
//        for (int i = 0; i < result2.size(); i++) {
//            //System.out.println(result2.get(i));
//            userTable.setValueAt(result2.get(i).getUsername(), i + result.size(), 0);
//            var status = result2.get(i).getTrangThai();
//            userTable.setValueAt(status == 1 ? "Active" : status == 0 ? "Offline" : "Locked", i + result.size(), 3);
//            userTable.setValueAt(result2.get(i).getFullName(), i + result.size(), 1);
//        }
    }

    private void addRow(JTable table, int size) {
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        int adds = abs(size - model.getRowCount());
        for (int count = 1; count <= adds; count++) {
            model.addRow(new Object[]{"", "", "", "", ""});
            // System.out.println(table.getRowCount());
        }
        table.setModel(model);

        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                table.setValueAt("", i, j);
            }
        }

    }

    public ArrayList<TaiKhoan> getUserWithFilter(String... fields) {
        return null;
    }

    public void showAllGroup() {
        var orderQuery = cbCreateDateGroup.getSelectedIndex() == 0 ? "order by ngaytao asc" : "order by ngaytao desc";
        var result = new DAO_NhomChat().select(orderQuery);
        addRow(groupTable, result.size());
        for (int i = 0; i < result.size(); i++) {
            groupTable.setValueAt(result.get(i).getIDNhom(), i, 0);
            groupTable.setValueAt(result.get(i).getTenNhom(), i, 1);
            groupTable.setValueAt(DateLabelFormatter.dateToTime(result.get(i).getNgayTao()), i, 2);

        }
    }

    public void filterAllGroupChatMember() {

    }

    public void showAllGroupChatMember(String groupID) {
        var conds = cbGroupMember.getSelectedIndex() == 0 ? "" : cbGroupMember.getSelectedIndex() == 1 ? "and chucnang='false'" : "and chucnang='true'";
        var result = new DAO_NhomChat().selectAllMembers(groupID, conds);
        addRow(groupDetailTable, result.size() + 1);
        groupDetailTable.setValueAt(groupTable.getValueAt(groupTable.getSelectedRow(), 0).toString(), 0, NORMAL);
        groupDetailTable.setValueAt(groupTable.getValueAt(groupTable.getSelectedRow(), 2).toString(), 0, 3);

        for (int i = 0; i < result.size(); i++) {
            groupDetailTable.setValueAt(result.get(i).getChucNang() ? "Admin" : "Member", i + 1, 2);
            groupDetailTable.setValueAt(result.get(i).getUsername(), i + 1, 1);
            groupDetailTable.setValueAt(DateLabelFormatter.dateToTime(result.get(i).getNgayThem()), i + 1, 3);
        }
    }

    public String getCurrentSelect() {
        return userTable.getValueAt(userTable.getSelectedRow(), 0).toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        lbHome = new javax.swing.JLabel();
        lbGroup = new javax.swing.JLabel();
        pnlCard = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        NameSort = new javax.swing.JComboBox<>();
        DateSort = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        AdminUserSearch_home = new javax.swing.JTextField();
        btnFriend = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnLockAccount = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        pnlCardEdit = new javax.swing.JPanel();
        loginHistoryPage = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        friendListPage = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendTable = new javax.swing.JTable();
        editPage = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        textAreaAddr = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        maleBtn = new javax.swing.JRadioButton();
        femaleBtn = new javax.swing.JRadioButton();
        jDatePanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        addPage = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtName1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPass1 = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        txtEmail1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        textAreaAddr1 = new javax.swing.JTextArea();
        maleBtn2 = new javax.swing.JRadioButton();
        femaleBtn2 = new javax.swing.JRadioButton();
        jDatePanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtFullName1 = new javax.swing.JTextField();
        addUserButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        NameSortCondition = new javax.swing.JComboBox<>();
        Group = new javax.swing.JPanel();
        cbCreateDateGroup = new javax.swing.JComboBox<>();
        searchGroup = new javax.swing.JTextField();
        cbGroupName = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        groupTable = new javax.swing.JTable();
        groupDetailPanel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        groupDetailTable = new javax.swing.JTable();
        searchGroupIcon = new javax.swing.JLabel();
        cbGroupMember = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 900, 500));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(258, 520));
        jPanel2.setMinimumSize(new java.awt.Dimension(228, 520));
        jPanel2.setPreferredSize(new java.awt.Dimension(228, 520));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        lbHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-database-administrator-32.png"))); // NOI18N
        lbHome.setText("Quản lý tài khoản");
        lbHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHomeMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(148, 56, 0, 63);
        jPanel2.add(lbHome, gridBagConstraints);

        lbGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-friend-32.png"))); // NOI18N
        lbGroup.setText("Quản lý nhóm chat");
        lbGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbGroupMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(65, 56, 243, 63);
        jPanel2.add(lbGroup, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel2);

        pnlCard.setLayout(new java.awt.CardLayout());

        NameSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không sắp", "Sắp xếp A-Z", "Sắp xếp Z-A" }));
        NameSort.setName("NameSort"); // NOI18N
        NameSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameSortActionPerformed(evt);
            }
        });

        DateSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thời gian không đổi", "Thời gian tăng dần", "Thời gian giảm dần" }));
        DateSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateSortActionPerformed(evt);
            }
        });

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        AdminUserSearch_home.setToolTipText("Tìm tài khoản");
        AdminUserSearch_home.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AdminUserSearch_homeKeyReleased(evt);
            }
        });

        btnFriend.setText("Danh sách bạn bè");
        btnFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFriendActionPerformed(evt);
            }
        });

        btnHistory.setText("Lịch sử đăng nhập");
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        btnEdit.setText("Cập nhập tài khoản");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnLockAccount.setBackground(new java.awt.Color(255, 255, 0));
        btnLockAccount.setText("Khóa tài khoản");
        btnLockAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLockAccountActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setText("Xóa tài khoản");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Username", "Họ Tên", "Đăng nhập gần nhất", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(userTable);

        pnlCardEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlCardEdit.setLayout(new java.awt.CardLayout());

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thời gian đăng nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(historyTable);

        javax.swing.GroupLayout loginHistoryPageLayout = new javax.swing.GroupLayout(loginHistoryPage);
        loginHistoryPage.setLayout(loginHistoryPageLayout);
        loginHistoryPageLayout.setHorizontalGroup(
            loginHistoryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginHistoryPageLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        loginHistoryPageLayout.setVerticalGroup(
            loginHistoryPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
        );

        pnlCardEdit.add(loginHistoryPage, "historyCard");

        friendTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Bạn bè", "Ngày kết bạn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(friendTable);

        javax.swing.GroupLayout friendListPageLayout = new javax.swing.GroupLayout(friendListPage);
        friendListPage.setLayout(friendListPageLayout);
        friendListPageLayout.setHorizontalGroup(
            friendListPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        );
        friendListPageLayout.setVerticalGroup(
            friendListPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
        );

        pnlCardEdit.add(friendListPage, "friendListCard");

        editPage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Username");

        txtName.setText("luutuanquan");
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel7.setText("Password");

        txtPass.setText("password");

        txtEmail.setText("aido@gmail.com");

        jLabel8.setText("Email");

        jLabel9.setText("Địa chỉ");

        textAreaAddr.setColumns(20);
        textAreaAddr.setRows(5);
        textAreaAddr.setText("1 Lê Lợi Q5");
        jScrollPane5.setViewportView(textAreaAddr);

        jLabel10.setText("Ngày sinh");

        jLabel11.setText("Giới tính");

        updateButton.setText("Cập nhập");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(maleBtn);
        maleBtn.setText("Nam");

        buttonGroup1.add(femaleBtn);
        femaleBtn.setText("Nữ");

        javax.swing.GroupLayout jDatePanelLayout = new javax.swing.GroupLayout(jDatePanel);
        jDatePanel.setLayout(jDatePanelLayout);
        jDatePanelLayout.setHorizontalGroup(
            jDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDatePanelLayout.setVerticalGroup(
            jDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        jLabel18.setText("Họ Tên");

        txtFullName.setText("Lưu Tuấn Quân");

        javax.swing.GroupLayout editPageLayout = new javax.swing.GroupLayout(editPage);
        editPage.setLayout(editPageLayout);
        editPageLayout.setHorizontalGroup(
            editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editPageLayout.createSequentialGroup()
                        .addGroup(editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editPageLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(editPageLayout.createSequentialGroup()
                                .addGroup(editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editPageLayout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel11)
                                    .addGroup(editPageLayout.createSequentialGroup()
                                        .addComponent(maleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(femaleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addGroup(editPageLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editPageLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editPageLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPageLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(updateButton)
                        .addGap(85, 85, 85))
                    .addGroup(editPageLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        editPageLayout.setVerticalGroup(
            editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maleBtn)
                    .addComponent(femaleBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pnlCardEdit.add(editPage, "editCard");

        jLabel12.setText("Username");

        txtName1.setText("example");

        jLabel13.setText("Password");

        txtPass1.setText("1234");

        jLabel14.setText("Email");

        txtEmail1.setText("example@gmail.com");

        jLabel15.setText("Địa chỉ");

        jLabel16.setText("Ngày sinh");

        jLabel17.setText("Giới tính");

        addButton.setText("Thêm");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        textAreaAddr1.setColumns(20);
        textAreaAddr1.setRows(5);
        textAreaAddr1.setText("Lê Đại Hành Q10");
        jScrollPane6.setViewportView(textAreaAddr1);

        buttonGroup1.add(maleBtn2);
        maleBtn2.setSelected(true);
        maleBtn2.setText("Nam");

        buttonGroup1.add(femaleBtn2);
        femaleBtn2.setText("Nữ");

        javax.swing.GroupLayout jDatePanel2Layout = new javax.swing.GroupLayout(jDatePanel2);
        jDatePanel2.setLayout(jDatePanel2Layout);
        jDatePanel2Layout.setHorizontalGroup(
            jDatePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDatePanel2Layout.setVerticalGroup(
            jDatePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jLabel19.setText("Họ Tên");

        txtFullName1.setText("Nguyễn Thị Ví Dụ");

        javax.swing.GroupLayout addPageLayout = new javax.swing.GroupLayout(addPage);
        addPage.setLayout(addPageLayout);
        addPageLayout.setHorizontalGroup(
            addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPageLayout.createSequentialGroup()
                        .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPageLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(jDatePanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(addPageLayout.createSequentialGroup()
                                .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(addPageLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtName1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(addPageLayout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addPageLayout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addPageLayout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 2, Short.MAX_VALUE)))
                        .addGap(7, 7, 7))
                    .addGroup(addPageLayout.createSequentialGroup()
                        .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(addPageLayout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addGap(59, 59, 59))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPageLayout.createSequentialGroup()
                                    .addComponent(maleBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(femaleBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(addPageLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(txtFullName1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton)
                .addGap(97, 97, 97))
        );
        addPageLayout.setVerticalGroup(
            addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtFullName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jDatePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maleBtn2)
                    .addComponent(femaleBtn2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pnlCardEdit.add(addPage, "addCard");

        addUserButton.setText("Thêm tài khoản");
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-search-32.png"))); // NOI18N

        NameSortCondition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Username", "Họ và tên" }));
        NameSortCondition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameSortConditionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnFriend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnLockAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHistory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(182, 182, 182))
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(HomeLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(NameSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NameSortCondition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DateSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(HomeLayout.createSequentialGroup()
                                .addContainerGap(63, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)))
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCardEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AdminUserSearch_home, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
            .addGroup(HomeLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NameSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DateSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AdminUserSearch_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NameSortCondition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnLockAccount))
                        .addGap(18, 18, 18)
                        .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addUserButton)
                            .addComponent(btnEdit))
                        .addGap(18, 18, 18)
                        .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFriend)
                            .addComponent(btnHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(63, Short.MAX_VALUE))
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addComponent(pnlCardEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pnlCard.add(Home, "homeAdmin");
        Home.getAccessibleContext().setAccessibleName("");

        cbCreateDateGroup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày tăng dần", "Ngày giảm dần" }));
        cbCreateDateGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCreateDateGroupActionPerformed(evt);
            }
        });

        searchGroup.setToolTipText("Tìm kiếm");

        cbGroupName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sắp xếp A-Z", "Sắp xếp Z-A", " " }));
        cbGroupName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGroupNameActionPerformed(evt);
            }
        });

        groupTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "nhomchat1", "25-11-2022 12:00:00"},
                {null, "nhomchat2", "20-11-2022 00:00:00"},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tên Nhóm", "Ngày tạo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        groupTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        groupTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                groupTableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(groupTable);

        groupDetailPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        groupDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên nhóm", "Thành viên", "Chức năng", "Ngày thêm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(groupDetailTable);
        if (groupDetailTable.getColumnModel().getColumnCount() > 0) {
            groupDetailTable.getColumnModel().getColumn(2).setMinWidth(60);
            groupDetailTable.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        javax.swing.GroupLayout groupDetailPanelLayout = new javax.swing.GroupLayout(groupDetailPanel);
        groupDetailPanel.setLayout(groupDetailPanelLayout);
        groupDetailPanelLayout.setHorizontalGroup(
            groupDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupDetailPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        groupDetailPanelLayout.setVerticalGroup(
            groupDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        searchGroupIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-search-32.png"))); // NOI18N
        searchGroupIcon.setText("jLabel3");

        cbGroupMember.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Member", "Admin" }));
        cbGroupMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGroupMemberActionPerformed(evt);
            }
        });

        jLabel1.setText("Lọc thành viên:");

        javax.swing.GroupLayout GroupLayout = new javax.swing.GroupLayout(Group);
        Group.setLayout(GroupLayout);
        GroupLayout.setHorizontalGroup(
            GroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(GroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(GroupLayout.createSequentialGroup()
                        .addComponent(cbCreateDateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(GroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(GroupLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(groupDetailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbGroupMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchGroupIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(206, Short.MAX_VALUE))
        );
        GroupLayout.setVerticalGroup(
            GroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCreateDateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchGroupIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbGroupMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(GroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addComponent(groupDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(140, 140, 140))
        );

        pnlCard.add(Group, "groupAdmin");

        jSplitPane1.setRightComponent(pnlCard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHomeMouseClicked
        cardLayout.show(pnlCard, "homeAdmin");
        lbHome.setForeground(Color.white);
        lbGroup.setForeground(Color.black);
        showAllUser(true);
    }//GEN-LAST:event_lbHomeMouseClicked

    private void lbGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbGroupMouseClicked
        cardLayout.show(pnlCard, "groupAdmin");
        lbGroup.setForeground(Color.white);
        lbHome.setForeground(Color.black);
        showAllGroup();
    }//GEN-LAST:event_lbGroupMouseClicked

    private void btnFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFriendActionPerformed
        if (userTable.getSelectedRow() == -1) {
            return;
        }
        var result = new DAO_BanBe().select("where username=N'" + getCurrentSelect() + "' order by ngayketban desc");
        addRow(friendTable, result.size());
        for (int i = 0; i < result.size(); i++) {
            friendTable.setValueAt(result.get(i).getUsernameBanBe(), i, 0);
            friendTable.setValueAt(DateLabelFormatter.dateToTime(result.get(i).getNgayKetBan()), i, 1);
        }
        cardLayOutHomePage.show(pnlCardEdit, "friendListCard");
    }//GEN-LAST:event_btnFriendActionPerformed

    private void btnLockAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLockAccountActionPerformed
        int index = userTable.getSelectedRow();
        if (index == -1) {
            return;
        }
        var input = JOptionPane.showConfirmDialog(rootPane, "Khóa " + userTable.getSelectedRowCount() + " tài khoản?", "Xác nhận khóa tài khoản", JOptionPane.YES_NO_CANCEL_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            int[] rows = userTable.getSelectedRows();
            for (int i = 0; i < rows.length; i++) {
                try {
                    database_helper.insert("update taikhoan set trangthai='-1' where username=N'" + userTable.getValueAt(rows[i], 0) + "'");
                } catch (Exception ex) {
                }
            }
        }

    }//GEN-LAST:event_btnLockAccountActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        int index = userTable.getSelectedRow();
        if (index == -1) {
            return;
        }
        var r = new DAO_LSDangNhap().select("where username=N'" + userTable.getValueAt(index, 0) + "' order by ngaydangxuat desc");
        addRow(historyTable, r.size());
        for (int i = 0; i < r.size(); i++) {
            historyTable.setValueAt(DateLabelFormatter.dateToTime(r.get(i).getNgayDangXuat()), i, 0);
        }
        cardLayOutHomePage.show(pnlCardEdit, "historyCard");
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int index = userTable.getSelectedRow();
        if (index == -1) {
            return;
        }
        var r = new DAO_TaiKhoan().select("where username=N'" + userTable.getValueAt(index, 0) + "'");
        txtName.setText(r.get(0).getUsername());
        txtPass.setText(r.get(0).getPassword());
        txtEmail.setText(r.get(0).getEmail());
        txtFullName.setText(r.get(0).getFullName());
        var d = r.get(0).getNgaySinh();
        datePicker.getModel().setDay(DateLabelFormatter.getDay(d));
        datePicker.getModel().setMonth(DateLabelFormatter.getMonth(d));
        datePicker.getModel().setYear(DateLabelFormatter.getYear(d));

        textAreaAddr.setText(r.get(0).getDiaChi());
        if (!r.get(0).getGioiTinh()) {
            maleBtn.setSelected(true);
        } else {
            femaleBtn.setSelected(true);
        }

        cardLayOutHomePage.show(pnlCardEdit, "editCard");
    }//GEN-LAST:event_btnEditActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        var input = JOptionPane.showConfirmDialog(rootPane, "Hoàn tất cập nhật thông tin?", "Xác nhận cập nhật", JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION && userTable.getSelectedRow() > -1) {
            new DAO_TaiKhoan().update(new TaiKhoan(txtName.getText(), String.valueOf(txtPass.getPassword()), txtEmail.getText()).setFullName(txtFullName.getText())
                    .setDiaChi(new String(textAreaAddr.getText().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8)).setGioiTinh(!maleBtn.isSelected()).setNgaySinh((Date) datePicker.getModel().getValue()), getCurrentSelect());
            showAllUser(true);
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thông tin thành công!", "Thông báo", JOptionPane.OK_OPTION);

            cardLayOutHomePage.show(pnlCardEdit, "historyCard");
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        var input = JOptionPane.showConfirmDialog(rootPane, "Hoàn tất thêm tài khoản?", "Xác nhận thông tin", JOptionPane.YES_NO_OPTION);
        Date selectedDate = (java.sql.Date) datePicker2.getModel().getValue();
        if (input == JOptionPane.YES_OPTION) {
            new DAO_TaiKhoan().insert(new TaiKhoan(txtName1.getText(), String.valueOf(txtPass1.getPassword()), txtEmail1.getText()).setFullName(new String(txtFullName1.getText().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                    .setDiaChi(new String(textAreaAddr1.getText().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8)).setGioiTinh(!maleBtn2.isSelected()).setNgaySinh(selectedDate));
            showAllUser(true);
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công!", "Thông báo", JOptionPane.OK_OPTION);

        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void cbCreateDateGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCreateDateGroupActionPerformed
        showAllGroup();
    }//GEN-LAST:event_cbCreateDateGroupActionPerformed

    private void cbGroupNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGroupNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGroupNameActionPerformed

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        int index = userTable.getSelectedRow();
        var r = new DAO_LSDangNhap().select("where username=N'" + userTable.getValueAt(index, 0) + "' order by ngaydangxuat desc");
        addRow(historyTable, r.size());
        for (int i = 0; i < r.size(); i++) {
            historyTable.setValueAt(DateLabelFormatter.dateToTime(r.get(i).getNgayDangXuat()), i, 0);
        }
        cardLayOutHomePage.show(pnlCardEdit, "historyCard");
    }//GEN-LAST:event_userTableMouseClicked

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
//        txtName1.setText("");
//        txtPass1.setText("");
//        txtEmail1.setText("");
//        textAreaAddr1.setText("");
        cardLayOutHomePage.show(pnlCardEdit, "addCard");
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void groupTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_groupTableMouseClicked
        showAllGroupChatMember(groupTable.getValueAt(groupTable.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_groupTableMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int index = userTable.getSelectedRow();
        if (index == -1) {
            return;
        }
        var input = JOptionPane.showConfirmDialog(rootPane, "Xóa " + userTable.getSelectedRowCount() + " tài khoản?", "Xác nhận xóa tài khoản", JOptionPane.YES_NO_CANCEL_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            var dtk = new DAO_TaiKhoan();
            DefaultTableModel model = (DefaultTableModel) this.userTable.getModel();
            int[] rows = userTable.getSelectedRows();
            for (int i = 0; i < rows.length; i++) {
                dtk.delete("where username=N'" + userTable.getValueAt(rows[i], 0) + "'");
                model.removeRow(rows[i] - i);
            }
            showAllUser(true);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void AdminUserSearch_homeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AdminUserSearch_homeKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (AdminUserSearch_home.getText().strip().isEmpty()) {
                showAllUser(true);
            }
            return;
        }
        if (AdminUserSearch_home.getText().strip().isEmpty()) {
            showAllUser(true);
            return;
        }
        var query = AdminUserSearch_home.getText().strip().split("\\s+");
        var s = new ArrayList<String>();
        for (var word : query) {
            s.add("username like N'%" + word + "%'");
        }
        DAO_LSDangNhap log = new DAO_LSDangNhap();
        var result = log.select("ls1\n"
                + "where " + String.join(" or ", s) + " and ls1.NgayDangXuat >= all (\n"
                + "	select ls2.ngaydangxuat from LichSuDangNhap ls2\n"
                + "	where ls2.Username = ls1.Username\n"
                + ")\n"
                + "order by NgayDangXuat desc");

        var result2 = new DAO_TaiKhoan().select("where " + String.join(" or ", s) + " and username not in (select distinct username from lichsudangnhap)");
        addRow(userTable, result2.size() + result.size());

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
            var acc = new DAO_TaiKhoan().select("where username=N'" + result.get(i).getUsername() + "'");
            var status = acc.get(0).getTrangThai();
            userTable.setValueAt(status == 1 ? "Active" : status == 0 ? "Offline" : "Locked", i, 2);
        }

        for (int i = 0; i < result.size(); i++) {
            userTable.setValueAt(result.get(i).getUsername(), i, 0);
            userTable.setValueAt(DateLabelFormatter.dateToTime(result.get(i).getNgayDangXuat()), i, 1);
        }

        for (int i = 0; i < result2.size(); i++) {
            //System.out.println(result2.get(i));
            userTable.setValueAt(result2.get(i).getUsername(), i + result.size(), 0);
            var status = result2.get(i).getTrangThai();
            userTable.setValueAt(status == 1 ? "Active" : status == 0 ? "Offline" : "Locked", i + result.size(), 2);
        }
    }//GEN-LAST:event_AdminUserSearch_homeKeyReleased

    private void DateSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateSortActionPerformed
        showAllUser(true);
    }//GEN-LAST:event_DateSortActionPerformed

    private void NameSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameSortActionPerformed
        showAllUser(false);
    }//GEN-LAST:event_NameSortActionPerformed

    private void NameSortConditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameSortConditionActionPerformed
        showAllUser(false);
    }//GEN-LAST:event_NameSortConditionActionPerformed

    private void cbGroupMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGroupMemberActionPerformed
        if (groupTable.getSelectedRow() == -1) {
            return;
        }

        showAllGroupChatMember(groupTable.getValueAt(groupTable.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_cbGroupMemberActionPerformed

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
            java.util.logging.Logger.getLogger(main_admin_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_admin_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_admin_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_admin_ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_admin_ui().setVisible(true);
            }
        });
    }

    SqlDateModel dateModel, dateModel2;
    //model.setDate(20,04,2014);
    // Need this...
    Properties p = new Properties();

    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    JDatePanelImpl datePanel2;
    JDatePickerImpl datePicker2;
    database_helper db;
    DefaultTableModel model;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AdminUserSearch_home;
    private javax.swing.JComboBox<String> DateSort;
    private javax.swing.JPanel Group;
    private javax.swing.JPanel Home;
    private javax.swing.JComboBox<String> NameSort;
    private javax.swing.JComboBox<String> NameSortCondition;
    private javax.swing.JButton addButton;
    private javax.swing.JPanel addPage;
    private javax.swing.JButton addUserButton;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFriend;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnLockAccount;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbCreateDateGroup;
    private javax.swing.JComboBox<String> cbGroupMember;
    private javax.swing.JComboBox<String> cbGroupName;
    private javax.swing.JPanel editPage;
    private javax.swing.JRadioButton femaleBtn;
    private javax.swing.JRadioButton femaleBtn2;
    private javax.swing.JPanel friendListPage;
    private javax.swing.JTable friendTable;
    private javax.swing.JPanel groupDetailPanel;
    private javax.swing.JTable groupDetailTable;
    private javax.swing.JTable groupTable;
    private javax.swing.JTable historyTable;
    private javax.swing.JPanel jDatePanel;
    private javax.swing.JPanel jDatePanel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lbGroup;
    private javax.swing.JLabel lbHome;
    private javax.swing.JPanel loginHistoryPage;
    private javax.swing.JRadioButton maleBtn;
    private javax.swing.JRadioButton maleBtn2;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JPanel pnlCardEdit;
    private javax.swing.JTextField searchGroup;
    private javax.swing.JLabel searchGroupIcon;
    private javax.swing.JTextArea textAreaAddr;
    private javax.swing.JTextArea textAreaAddr1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtFullName1;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtName1;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JButton updateButton;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
