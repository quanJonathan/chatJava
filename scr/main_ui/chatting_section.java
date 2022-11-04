package main_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JList;
import java.awt.List;

public class chatting_section {

	private JFrame frame;
	private final JPanel panel_4 = new JPanel();
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chatting_section window = new chatting_section();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public chatting_section() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1015, 788);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel chatDetail = new JPanel();
		chatDetail.setBorder(null);
		chatDetail.setMaximumSize(new Dimension(32, 32));
		chatDetail.setForeground(new Color(255, 255, 255));
		chatDetail.setBounds(353, 0, 638, 741);
		frame.getContentPane().add(chatDetail);
		chatDetail.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 355, 741);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(5, 100, 153));
		panel_3.setBounds(83, 0, 272, 741);
		panel.add(panel_3);
		panel_3.setLayout(null);
		panel_4.setBackground(new Color(0, 128, 255));
		panel_4.setBounds(10, 40, 234, 44);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel searchButton = new JLabel("");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		searchButton.setIcon(new ImageIcon("D:\\scr\\Java_Eclipse\\img\\icons8-search-32.png"));
		searchButton.setBounds(189, 0, 45, 44);
		panel_4.add(searchButton);
		
		txtSearch = new JTextField();
		txtSearch.setBackground(new Color(0, 128, 255));
		txtSearch.setText("Search");
		txtSearch.setBounds(0, 0, 179, 44);
		panel_4.add(txtSearch);
		txtSearch.setColumns(10);
		
		JPanel AvailableChatUserList = new JPanel();
		AvailableChatUserList.setBounds(10, 177, 252, 554);
		panel_3.add(AvailableChatUserList);
		AvailableChatUserList.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel onlineUserList = new JPanel();
		onlineUserList.setBounds(10, 94, 244, 62);
		panel_3.add(onlineUserList);
		
		JScrollPane scrollPane = new JScrollPane();
		onlineUserList.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setVerifyInputWhenFocusTarget(false);
		panel_2.setBackground(new Color(0, 128, 255));
		panel_2.setBounds(0, 0, 84, 741);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel homeNav = new JLabel("");
		homeNav.setIcon(new ImageIcon("D:\\scr\\Java_Eclipse\\img\\icons8-home-32.png"));
		homeNav.setHorizontalAlignment(SwingConstants.CENTER);
		homeNav.setBounds(0, 158, 84, 82);
		panel_2.add(homeNav);
		
		JLabel chatNav = new JLabel("");
		chatNav.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		chatNav.setOpaque(true);
		chatNav.setBackground(new Color(5, 100, 153));
		chatNav.setBorder(new MatteBorder(0, 4, 0, 0, (Color) new Color(255, 255, 255)));
		chatNav.setIcon(new ImageIcon("D:\\scr\\Java_Eclipse\\img\\icons8-chat-room-32.png"));
		chatNav.setHorizontalAlignment(SwingConstants.CENTER);
		chatNav.setBounds(0, 337, 84, 82);
		panel_2.add(chatNav);
		
		JLabel friendListNav = new JLabel("");
		friendListNav.setBounds(0, 255, 84, 82);
		panel_2.add(friendListNav);
		friendListNav.setHorizontalTextPosition(SwingConstants.RIGHT);
		friendListNav.setIconTextGap(3);
		friendListNav.setIcon(new ImageIcon("D:\\scr\\Java_Eclipse\\img\\icons8-friend-32.png"));
		friendListNav.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
