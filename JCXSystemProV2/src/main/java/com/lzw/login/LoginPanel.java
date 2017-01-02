package com.lzw.login;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.lzw.utils.Utils;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		super();
		
		imageIcon = new ImageIcon(Utils.getResourceUrl("login.jpg"));
		setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
		setLayout(null);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imageIcon.getImage(), 0, 0, getParent());
	}

	private ImageIcon imageIcon;
}
