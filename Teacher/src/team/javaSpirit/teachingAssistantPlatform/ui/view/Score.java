package team.javaSpirit.teachingAssistantPlatform.ui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import team.javaSpirit.teachingAssistantPlatform.ui.event.StudentScoreListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.TeacherLoginActionListener;
import team.javaSpirit.teachingAssistantPlatform.util.DlProPertiesUtil;

public class Score extends JFrame {
	
	/** 背景容器 */
	private JPanel bgContentPane;
	/** 成绩 */
	private JTextField score;
	
	public JTextField getScore() {
		return score;
	}
	
	/**
	 * Create the frame.
	 */
	public Score() {
		// 设置窗体大小
		this.setBounds(0, 0, 200, 120);
		// 窗体大小不能改变
		this.setResizable(false);
		// 设置图标
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image\\logo1.png"));
		// 居中显示
		this.setLocationRelativeTo(null);
		// 窗体可见
		this.setVisible(true);
	}
	
	public void init() {
		this.setBackground();
		this.setScore();
		this.setConfirmButton();
	}
	
	/**
	 * 
	 * <p>
	 * Title: setScore
	 * </p>
	 * <p>
	 * Description: 设置成绩
	 * </p>
	 */
	public void setScore() {
		JLabel scorelb = new JLabel("成绩:");
		scorelb.setForeground(Color.LIGHT_GRAY);
		scorelb.setFont(new Font("宋体", Font.BOLD, 14));
		scorelb.setBounds(20, 10, 40, 30);
		bgContentPane.add(scorelb);
		score = new JTextField("");
		score.setBackground(new Color(240, 248, 255));
		score.setHorizontalAlignment(SwingConstants.LEFT);
		score.setForeground(SystemColor.activeCaptionBorder);
		score.setFont(new Font("宋体", Font.BOLD, 14));
		score.setBounds(62, 10, 100, 30);
		score.setColumns(5);
		bgContentPane.add(score);
	}
	
	/**
	 * 
	 * <p>
	 * Title: setConfirmButton
	 * </p>
	 * <p>
	 * Description:设置确认按钮
	 * </p>
	 */
	public void setConfirmButton() {
		JButton loginButton = new JButton("确 认");
		loginButton.setForeground(new Color(169, 169, 169));
		loginButton.setFont(new Font("宋体", Font.BOLD, 16));
		loginButton.setBackground(null);
		loginButton.setBounds(60, 50, 80, 32);
		// 回车确认
		getRootPane().setDefaultButton(loginButton);
		bgContentPane.add(loginButton);
		// 给登录按钮添加事件
		loginButton.addActionListener(new StudentScoreListener(this));
	}
	
	/**
	 * 
	 * <p>
	 * Title: setBackground
	 * </p>
	 * <p>
	 * Description:设置背景bgContentPane
	 * </p>
	 */
	public void setBackground() {
		bgContentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/img1.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		bgContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(bgContentPane);
		// 设置布局方式为绝对定位
		this.getContentPane().setLayout(null);
	}
	
	
}
