package com.lzw.internalframe.operators;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import com.lzw.dao.UserDao;
import com.lzw.internalframe.GBC;
import com.lzw.internalframe.OperatorMgmtIFrame;
import com.lzw.model.User;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OperatorDelPanel extends JPanel {
	/**
	 * Create the panel.
	 */
	public OperatorDelPanel() {
		super();
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, new GBC(0, 0, 9, 6).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setInsets(0, 0, 10, 0));

		table = new JTable();
		dftm = (DefaultTableModel)table.getModel();
		dftm.setColumnIdentifiers(columnNames);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				nameTextField.setText(table.getValueAt(row, 0).toString().trim());
				loginTextField.setText(table.getValueAt(row, 1).toString().trim());
				passwordTextField.setText(table.getValueAt(row, 2).toString().trim());
			}
		});
		initTable();
		
		scrollPane.setViewportView(table);

		nameLable = new JLabel("用户姓名:");
		add(nameLable, new GBC(0, 6, 1, 1).setFill(GridBagConstraints.CENTER).setInsets(0, 2, 0, 2));

		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		add(nameTextField, new GBC(1, 6, 2, 1).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0));

		loginLable = new JLabel("登录名:");
		add(loginLable, new GBC(3, 6, 1, 1).setFill(GridBagConstraints.CENTER).setInsets(0, 2, 0, 2));
		loginTextField = new JTextField();
		loginTextField.setEditable(false);
		add(loginTextField, new GBC(4, 6, 2, 1).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0));

		passwordLabel = new JLabel("密码:");
		add(passwordLabel, new GBC(6, 6, 1, 1).setFill(GridBagConstraints.CENTER).setInsets(0, 2, 0, 2));
		passwordTextField = new JTextField();
		passwordTextField.setEditable(false);
		add(passwordTextField, new GBC(7, 6, 2, 1).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0).setInsets(0, 0, 0, 2));

		delButton = new JButton("删除");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDao dao = new UserDao();
				User user = dao.getUser(nameTextField.getText(), passwordTextField.getText());
				dao.delete(user);
				initTable();
			}
		});
		add(delButton, new GBC(3, 7, 1, 1).setFill(GridBagConstraints.CENTER).setInsets(5, 0, 20, 0));

		closeButton = new JButton("关闭");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperatorMgmtIFrame ifrmae = (OperatorMgmtIFrame)OperatorDelPanel.this.getRootPane().getParent();
				ifrmae.doDefaultCloseAction();
			}
		});
		add(closeButton, new GBC(6, 7, 1, 1).setFill(GridBagConstraints.CENTER).setInsets(5, 0, 20, 0));

	}
	
	public void initTable() {
		UserDao dao = new UserDao();
		List<User> users = dao.findAll(User.class);
		
		String[] data = new String[4];
		dftm.setDataVector(null, columnNames);
		
		for (User user : users) {
			data[0] = user.getName();
			data[1] = user.getLoginName();
			data[2] = user.getPassword();
			if (user.getAuthority().equals("A")) {
				data[3] = new String("系统管理员");
			} else {
				data[3] = new String("操作员");
			}
			
			dftm.addRow(data);
		}
		
		table.setRowSelectionInterval(0, 0);
		
		setVisible(true);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame("添加操作员");
					frame.getContentPane().add(new OperatorDelPanel());
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setBounds(0, 0, 500, 300);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel loginLable;
	private JTextField loginTextField;
	private JLabel nameLable;
	private JTextField nameTextField;
	private JLabel passwordLabel;
	private JTextField passwordTextField;
	private JTable table;
	private JButton delButton;
	private JButton closeButton;
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dftm;
	private String[] columnNames = new String[] { "用户姓名", "登录名", "密码", "权限" };
}
