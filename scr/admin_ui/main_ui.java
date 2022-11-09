package admin_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;

public class main_ui {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_ui window = new main_ui();
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
	public main_ui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 851, 623);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 583, 835, 1);
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(5, 100, 153));
		frame.getContentPane().add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(0, 128, 255));
		panel_4.setBounds(10, 40, 234, 44);
		panel_3.add(panel_4);
		
		JLabel searchButton = new JLabel("");
		searchButton.setBounds(189, 0, 45, 44);
		panel_4.add(searchButton);
		
		textField = new JTextField();
		textField.setText("Search");
		textField.setColumns(10);
		textField.setBackground(new Color(0, 128, 255));
		textField.setBounds(0, 0, 179, 44);
		panel_4.add(textField);
		
		JPanel AvailableChatUserList = new JPanel();
		AvailableChatUserList.setBounds(10, 177, 252, 554);
		panel_3.add(AvailableChatUserList);
		AvailableChatUserList.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel onlineUserList = new JPanel();
		onlineUserList.setBounds(10, 94, 244, 62);
		panel_3.add(onlineUserList);
		
		JScrollPane scrollPane = new JScrollPane();
		onlineUserList.add(scrollPane);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setVerifyInputWhenFocusTarget(false);
		panel_2_1.setBackground(new Color(0, 0, 0, 0));
		panel_2_1.setBounds(0, 100, 86, 384);
		frame.getContentPane().add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel homeNav = new JLabel("");
		homeNav.setIcon(new ImageIcon(main_ui.class.getResource("/resources/icons8-home-32.png")));
		homeNav.setOpaque(true);
		homeNav.setHorizontalAlignment(SwingConstants.CENTER);
		homeNav.setBorder(new MatteBorder(0, 4, 0, 0, (Color) new Color(255, 255, 255)));
		homeNav.setBackground(new Color(5, 100, 153));
		panel_2_1.add(homeNav);
		
		JLabel onlineUser = new JLabel("");
		onlineUser.setIcon(new ImageIcon(main_ui.class.getResource("/resources/icons8-chat-room-32.png")));
		// onlineUser.setOpaque(true);
		onlineUser.setHorizontalAlignment(SwingConstants.CENTER);
		// onlineUser.setBorder(new MatteBorder(0, 4, 0, 0, (Color) new Color(255, 255, 255)));
		// onlineUser.setBackground(new Color(5, 100, 153));
		panel_2_1.add(onlineUser);
		
		JLabel group = new JLabel("");
		group.setIcon(new ImageIcon(main_ui.class.getResource("/resources/icons8-friend-32.png")));
		group.setIconTextGap(3);
		group.setHorizontalTextPosition(SwingConstants.RIGHT);
		group.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2_1.add(group);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 86, 583);
		panel_2.setVerifyInputWhenFocusTarget(false);
		panel_2.setBackground(new Color(0, 128, 255));
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(6, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(86, 0, 749, 584);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(0, 0, 749, 584);
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(new Color(5, 100, 153));
		panel.add(panel_3_1);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBackground(new Color(0, 128, 255));
		panel_4_1.setBounds(75, 39, 573, 44);
		panel_3_1.add(panel_4_1);
		
		JLabel searchButton_1 = new JLabel("");
		searchButton_1.setIcon(new ImageIcon(main_ui.class.getResource("/resources/icons8-search-32.png")));
		searchButton_1.setBounds(8, 0, 45, 44);
		panel_4_1.add(searchButton_1);
		
		textField_1 = new JTextField();
		textField_1.setBorder(null);
		textField_1.setText("Search");
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(0, 128, 255));
		textField_1.setBounds(65, 0, 179, 44);
		panel_4_1.add(textField_1);
		
		JPanel onlineUserList_1 = new JPanel();
		onlineUserList_1.setBounds(75, 112, 573, 410);
		panel_3_1.add(onlineUserList_1);
		onlineUserList_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(8, 5, 557, 395);
		onlineUserList_1.add(scrollPane_1);
	}
}
