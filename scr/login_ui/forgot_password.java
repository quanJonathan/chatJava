package login_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class forgot_password {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forgot_password window = new forgot_password();
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
	public forgot_password() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 630, 369);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setBounds(352, 130, 65, 34);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setToolTipText("Enter email");
		textField.setColumns(10);
		textField.setBounds(352, 162, 231, 27);
		frame.getContentPane().add(textField);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Username");
		lblNewLabel_1_1_1.setBounds(352, 188, 65, 34);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(352, 219, 231, 27);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton_1 = new JButton("Send email reset password");
		btnNewButton_1.setBounds(375, 282, 194, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblForgotPasswordPage = new JLabel("Forgot password page");
		lblForgotPasswordPage.setIcon(new ImageIcon(forgot_password.class.getResource("/resources/icons8-forgot-password-96.png")));
		lblForgotPasswordPage.setBounds(41, 15, 251, 320);
		frame.getContentPane().add(lblForgotPasswordPage);
		lblForgotPasswordPage.setIconTextGap(5);
		lblForgotPasswordPage.setAlignmentX(0.5f);
	}

}
