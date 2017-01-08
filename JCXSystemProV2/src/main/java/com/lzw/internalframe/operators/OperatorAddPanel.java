package com.lzw.internalframe.operators;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import com.lzw.dao.UserDao;
import com.lzw.internalframe.GBC;
import com.lzw.model.User;
import com.lzw.utils.UsernamePasswordValidator;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OperatorAddPanel extends JPanel {
	private JTextField loginTextField;
	private JTextField nameTextField;
	private JPasswordField passwordField;
	private JPasswordField password1Field;
	private JLabel promptLabel;

	/**
	 * Create the panel.
	 */
	public OperatorAddPanel() {
		setLayout(new GridBagLayout());

		JLabel loginLabel = new JLabel("登录名称:");
		add(loginLabel, new GBC(0, 1, 1, 1).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 5));

		loginTextField = new JTextField();
		add(loginTextField, new GBC(1, 1, 2, 1).setFill(GridBagConstraints.HORIZONTAL).setInsets(5, 5, 5, 5));

		JLabel nameLabel = new JLabel("操作员姓名:");
		add(nameLabel, new GBC(0, 3, 1, 1).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 5));

		nameTextField = new JTextField();
		add(nameTextField, new GBC(1, 3, 2, 1).setFill(GridBagConstraints.HORIZONTAL).setInsets(5, 5, 5, 5));

		JLabel passwordLabel = new JLabel("输入密码:");
		add(passwordLabel, new GBC(0, 5, 1, 1).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 5));

		passwordField = new JPasswordField();
		add(passwordField, new GBC(1, 5, 2, 1).setFill(GridBagConstraints.HORIZONTAL).setInsets(5, 5, 5, 5));

		JLabel passowrd1Label = new JLabel("确认密码:");
		add(passowrd1Label, new GBC(0, 7, 1, 1).setAnchor(GridBagConstraints.EAST).setInsets(5, 5, 5, 5));

		password1Field = new JPasswordField();
		add(password1Field, new GBC(1, 7, 2, 1).setFill(GridBagConstraints.HORIZONTAL).setInsets(5, 5, 5, 5));

		JButton addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loginname = loginTextField.getText();
				String name = nameTextField.getText();
				String password = passwordField.getText();
				String password1 = password1Field.getText();
				UserDao dao = new UserDao();

				if (!UsernamePasswordValidator.validateUsername(loginname)) {
					promptLabel.setText("<html><body><p style=\"color:red\">登录名称校验失败：包含英文字母和数字，长度为6-16!</p></body></html>");
					return;
				}
				
				if (dao.isLoginNameExisted(loginname)) {
					promptLabel.setText("<html><body><p style=\"color:red\">登录名称已存在!</p></body></html>");
					return;
				}

//				if (login.length() == 0) {
//					promptLabel.setText("<html><body><p style=\"color:red\">登录名称不能为空!</p></body></html>");
//					return;
//				} else 
				if (name.length() == 0) {
					promptLabel.setText("<html><body><p style=\"color:red\">操作员姓名不能为空!</p></body></html>");
					return;
				}
//				} else if (password.length() == 0 || password1.length() == 0) {
//					promptLabel.setText("<html><body><p style=\"color:red\">密码不能为空!</p></body></html>");
//					return;
//				}

				if (!UsernamePasswordValidator.validatePassword(password)) {
					promptLabel.setText("<html><body><p style=\"color:red\">密码校验失败: 长度为6-16个字符，至少包括一个大写字母、小写字母、数字和特殊标识符!</p></body></html>");
					return;
				}

				if (!password.equals(password1)) {
					promptLabel.setText("<html><body><p style=\"color:red\">密码不匹配!</p></body></html>");
					return;
				}
				
				User user = new User(name, loginname, password, "O");
				
				dao.saveOrUpdate(user);
				resetTextFields();
				promptLabel.setText("<html><body><p style=\"color:blue\">添加用户成功</p></body></html>");

			}
		});
		add(addButton, new GBC(1, 9, 1, 1).setInsets(0, 0, 0, 5).setWeight(1.0, 0));

		JButton resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTextFields();
			}
		});
		add(resetButton, new GBC(2, 9, 1, 1).setInsets(0, 0, 0, 5).setWeight(1.0, 0));

		promptLabel = new JLabel("");
		add(promptLabel, new GBC(1, 10, 2, 1).setAnchor(GridBagConstraints.WEST).setInsets(5, 5, 5, 5));
	}

	public void resetTextFields() {
		loginTextField.setText("");
		nameTextField.setText("");
		passwordField.setText("");
		password1Field.setText("");
		promptLabel.setText("");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame("添加操作员");
					frame.getContentPane().add(new OperatorAddPanel());
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setBounds(0, 0, 280, 240);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
