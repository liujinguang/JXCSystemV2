package com.lzw.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.lzw.MainFrame;
import com.lzw.dao.UserDao;
import com.lzw.model.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("登录企业进销存管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new LoginPanel();
		contentPane.add(panel);
		setBounds(300, 200, 456, 298);
		setResizable(false);

		JLabel userLabel = new JLabel("用户名:");
		userLabel.setBounds(80, 140, 50, 20);
		panel.add(userLabel);

		userField = new JTextField();
		userField.setBounds(155, 140, 200, 20);
		panel.add(userField);
		userField.setColumns(10);

		JLabel label = new JLabel("密    码:");
		label.setBounds(80, 170, 50, 20);
		panel.add(label);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginButton.doClick();
				}
			}
		});
		passwordField.setBounds(155, 170, 200, 20);
		panel.add(passwordField);
		passwordField.setColumns(10);

		loginButton = new JButton("登录");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = userField.getText();
				String password = passwordField.getText();
				if (name.length() == 0 || password.length() == 0) {
					promptLabel.setText("<html><body><p style=\"color:red;\">用户名或密码不能为空！</p></body></html>");
				} else {
					user = new UserDao().getUser(name, password);
					if (user == null) {
						promptLabel.setText("<html><body><p style=\"color:red;\">用户名或密码错误！</p></body></html>");
					} else {
						promptLabel.setText("");
						dispose();
						
						new MainFrame();
					}
				}
			}
		});
		loginButton.setBounds(160, 205, 70, 25);
		panel.add(loginButton);

		quitButton = new JButton("退出");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setBounds(270, 205, 70, 25);
		panel.add(quitButton);

		promptLabel = new JLabel("");
		promptLabel.setBounds(155, 241, 200, 20);
		panel.add(promptLabel);
		// contentPane.setSize(panel.getWidth(), panel.getHeight());
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Login.user = user;
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userField;
	private JTextField passwordField;
	private JButton loginButton;
	private JButton quitButton;
	private JLabel promptLabel;
	private static User user;
}
