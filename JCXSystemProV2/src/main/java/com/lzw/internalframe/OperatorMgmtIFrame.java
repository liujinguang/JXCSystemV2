package com.lzw.internalframe;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.lzw.internalframe.operators.OperatorAddPanel;
import com.lzw.internalframe.operators.OperatorDelPanel;

import java.awt.BorderLayout;

public class OperatorMgmtIFrame extends JInternalFrame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();

					JDesktopPane desktopPane = new JDesktopPane();
					OperatorMgmtIFrame iframe = new OperatorMgmtIFrame();
					desktopPane.add(iframe);

					frame.getContentPane().add(desktopPane);
					frame.setBounds(100, 100, 800, 600);
					
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
	public OperatorMgmtIFrame() {
		setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setTitle("操作员管理");
		setBounds(50, 50, 500, 300);
		setVisible(true);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);

		final OperatorAddPanel addPanel = new OperatorAddPanel();
		final OperatorDelPanel delPanel = new OperatorDelPanel();

		tabbedPane.addTab("添加操作员", null, addPanel, "添加操作员");
		tabbedPane.addTab("删除操作员", null, delPanel, "删除操作员");
		
		tabbedPane.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				delPanel.initTable();
			}
		});

		pack();
		setVisible(true);
	}

	private static final long serialVersionUID = 1L;
}
