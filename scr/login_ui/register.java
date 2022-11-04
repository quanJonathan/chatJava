package login_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class register {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register window = new register();
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
	public register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 565, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, -29, 221, 400);
		frame.getContentPane().add(panel);
		
		JLabel lblUserRegisterPage = new JLabel("User register page");
		lblUserRegisterPage.setIcon(new ImageIcon(register.class.getResource("/resources/icons8-sign-up-96.png")));
		lblUserRegisterPage.setSize(new Dimension(100, 100));
		lblUserRegisterPage.setIconTextGap(5);
		lblUserRegisterPage.setAlignmentX(0.5f);
		lblUserRegisterPage.setBounds(0, 27, 221, 376);
		panel.add(lblUserRegisterPage);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(270, 32, 65, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setToolTipText("Enter email");
		textField.setColumns(10);
		textField.setBounds(270, 64, 231, 27);
		frame.getContentPane().add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setBounds(270, 89, 65, 34);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(270, 119, 231, 27);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Back to sign in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(270, 297, 131, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign up");
		btnNewButton_1.setBounds(416, 297, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(270, 175, 231, 27);
		frame.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Reenter Password");
		lblNewLabel_1_1_1.setBounds(270, 145, 106, 34);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("User name");
		lblNewLabel_1_2.setBounds(270, 201, 65, 34);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Enter email");
		textField_1.setColumns(10);
		textField_1.setBounds(270, 233, 231, 27);
		frame.getContentPane().add(textField_1);
		
		JLabel lblNewLabel = new JLabel("Valid or not");
		lblNewLabel.setVisible(false);
		lblNewLabel.setEnabled(false);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(345, 212, 91, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Valid or not");
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setBounds(365, 156, 91, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Valid or not");
		lblNewLabel_3.setVisible(false);
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setEnabled(false);
		lblNewLabel_3.setBounds(302, 43, 91, 13);
		frame.getContentPane().add(lblNewLabel_3);
	}

}
