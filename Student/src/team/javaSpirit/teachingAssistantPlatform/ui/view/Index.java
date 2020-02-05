package team.javaSpirit.teachingAssistantPlatform.ui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import team.javaSpirit.teachingAssistantPlatform.common.Communication;
import team.javaSpirit.teachingAssistantPlatform.common.Constant;
import team.javaSpirit.teachingAssistantPlatform.entity.FileContent;
import team.javaSpirit.teachingAssistantPlatform.entity.Record;
import team.javaSpirit.teachingAssistantPlatform.entity.Score;
import team.javaSpirit.teachingAssistantPlatform.entity.ShareResource;
import team.javaSpirit.teachingAssistantPlatform.entity.Students;
import team.javaSpirit.teachingAssistantPlatform.entity.Teacher;
import team.javaSpirit.teachingAssistantPlatform.record.service.RecordService;
import team.javaSpirit.teachingAssistantPlatform.signIn.dao.StudentCourseDao;
import team.javaSpirit.teachingAssistantPlatform.signIn.service.SignTimerTask;
import team.javaSpirit.teachingAssistantPlatform.signIn.service.StudentCourseService;
import team.javaSpirit.teachingAssistantPlatform.ui.event.HistoryRecordListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.IndexActionListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.RemoteMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.ResourceMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.ScoreListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.ShareResourceMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.SignMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.UploadMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.VideoScreenMouseListener;
import team.javaSpirit.teachingAssistantPlatform.upload.dao.ShareResourceDaoImpl;

/**
 * 
 * <p>
 * Title: Index
 * </p>
 * <p>
 * Description：登录后所有窗体的创建
 * </p>
 * 
 */
public class Index extends JFrame {
	/* 为学生找课程的对象 */
	private StudentCourseService scs = new StudentCourseService();
	/*录屏监听*/
	private IndexActionListener indexActionListener;
	/** 背景容器 */
	private JPanel bgContentPane;
	/** 中间模块容器 */
	private JPanel centerpl;
	/** 内容模块容器 */
	private JPanel contentpl;
	private IndexActionListener event;

	public StudentCourseService getScs() {
		return scs;
	}

	public JPanel getBgContentPane() {
		return bgContentPane;
	}

	public JPanel getCenterpl() {
		return centerpl;
	}

	public JPanel getContentpl() {
		return contentpl;
	}

	/**
	 * 
	 * <p>
	 * Title: setBackground
	 * </p>
	 * <p>
	 * Description:设置背景图bgContentPane
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
		bgContentPane.setBounds(0, 0, 100, 1000);
		bgContentPane.setBorder(null);
		this.setContentPane(bgContentPane);
	}

	/**
	 * 
	 * <p>
	 * Title: setCenterpl
	 * </p>
	 * <p>
	 * Description:中间容器设置centerpl
	 * </p>
	 */
	public void setCenterpl() {
		centerpl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/img1.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		centerpl.setBounds(31, 137, 933, 407);
		centerpl.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, null, null, null));
		centerpl.setBackground(null);
		centerpl.setLayout(null);
		bgContentPane.add(centerpl);
	}

	/**
	 * 
	 * <p>
	 * Title: setContentpl
	 * </p>
	 * <p>
	 * Description:内容容器设置contentpl
	 * </p>
	 */
	public void setContentpl() {
		contentpl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/img1.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		contentpl.setBounds(113, 44, 793, 322);
		contentpl.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textInactiveText, null, null, null));
		centerpl.add(contentpl);
		contentpl.setLayout(null);
	}

	/**
	 * 
	 * <p>
	 * Title: setMenu
	 * </p>
	 * <p>
	 * Description: 主菜单栏设置（签到 远程监控 录屏 广播 作业 课堂小测 资源共享模块）向bgcontentpl中添加组件
	 * </p>
	 */
	public void setMenu() {
		this.setSignMenu();
		this.setRemoteMenu();
		this.setVideoScreen();
		this.setBroadcastingMenu();
		this.setScoreMenu();
		this.setWorkMenu();
		this.setShareMenu();
	}

	/**
	 * 
	 * <p>
	 * Title: setSignMenu
	 * </p>
	 * <p>
	 * Description:设置签到菜单
	 * </p>
	 */
	public void setSignMenu() {
		// 签到菜单容器
		JPanel menu1 = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/menubg.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		menu1.setBounds(45, 28, 88, 99);
		menu1.setBorder(null);
		menu1.setForeground(new Color(255, 255, 255));
		menu1.setLayout(null);

		// 签到图标
		JLabel lb1 = new JLabel("");
		lb1.setBounds(13, 10, 60, 60);
		menu1.add(lb1);
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setVerticalAlignment(SwingConstants.TOP);
		lb1.setIcon(new ImageIcon("image/menu1.jpg"));
		// 签到按钮
		JButton bt1 = new JButton("签到");
		bt1.setBounds(28, 70, 31, 17);
		menu1.add(bt1);
		bt1.setFont(new Font("宋体", Font.BOLD, 14));
		bt1.setForeground(new Color(100, 149, 237));

		bt1.setBorder(null);
		bt1.setBackground(Color.WHITE);
		bgContentPane.add(menu1);

		// 事件对象
		SignMouseListener signEvent = new SignMouseListener(this);
		// 事件监听
		menu1.addMouseListener(signEvent);
		bt1.addMouseListener(signEvent);
	}

	/**
	 * 
	 * <p>
	 * Title: setShareMenu
	 * </p>
	 * <p>
	 * Description:设置资源共享菜单
	 * </p>
	 */
	public void setShareMenu() {
		// 资源共享菜单容器
		JPanel menu7 = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/menubg.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		// 事件对象
		HistoryRecordListener shareEvent = new HistoryRecordListener(this);
		// 添加事件
		menu7.addMouseListener(shareEvent);
		menu7.setBounds(785, 28, 88, 99);
		menu7.setBorder(null);
		menu7.setForeground(Color.WHITE);
		menu7.setLayout(null);

		// 资源共享菜单图标
		JLabel lb7 = new JLabel("");
		lb7.setBounds(13, 10, 60, 60);
		menu7.add(lb7);
		lb7.setIcon(new ImageIcon("image/menu7.jpg"));
		// 资源共享菜单按钮
		JButton bt7 = new JButton("资源共享");
		// 添加事件
		bt7.addMouseListener(shareEvent);
		bt7.setBounds(13, 70, 61, 17);
		bt7.setForeground(new Color(100, 149, 237));
		bt7.setFont(new Font("宋体", Font.BOLD, 14));
		bt7.setBorder(null);
		bt7.setBackground(Color.WHITE);

		menu7.add(bt7);
		bgContentPane.add(menu7);
	}

	/**
	 * 
	 * <p>
	 * Title: setVideoScreen
	 * </p>
	 * <p>
	 * Description: 设置录屏菜单
	 * </p>
	 */
	public void setVideoScreen() {
		// 录屏菜单容器
		JPanel menu3 = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/menubg.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		menu3.setBounds(289, 28, 88, 99);
		menu3.setBorder(null);
		menu3.setForeground(Color.WHITE);
		menu3.setLayout(null);
		// 录屏菜单图标
		JLabel lb3 = new JLabel("");
		lb3.setBounds(13, 10, 60, 60);
		menu3.add(lb3);
		lb3.setIcon(new ImageIcon("image/menu3.jpg"));
		// 录屏菜单按钮
		JButton bt3 = new JButton("录屏");
		bt3.setBounds(28, 70, 31, 17);
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menu3.add(bt3);
		bt3.setForeground(new Color(100, 149, 237));
		bt3.setFont(new Font("宋体", Font.BOLD, 14));
		bt3.setBackground(Color.WHITE);
		bt3.setBorder(null);
		bgContentPane.add(menu3);
		VideoScreenMouseListener asm=new VideoScreenMouseListener(this);
		menu3.addMouseListener(asm);bt3.addMouseListener(asm);
	}

	/**
	 * 
	 * <p>
	 * Title: setWorkMenu
	 * </p>
	 * <p>
	 * Description:设置签到历史记录菜单
	 * </p>
	 */
	public void setWorkMenu() {
		// 记录菜单容器
		JPanel menu5 = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/menubg.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		
		menu5.setBounds(531, 28, 88, 99);
		menu5.setBorder(null);
		menu5.setForeground(Color.WHITE);
		menu5.setLayout(null);
		// 记录菜单图标
		JLabel lb5 = new JLabel("");
		lb5.setBounds(13, 10, 60, 60);
		menu5.add(lb5);
		lb5.setIcon(new ImageIcon("image/menu5.jpg"));
		// 记录菜单按钮
		JButton bt5 = new JButton("历史记录");
		bt5.setBounds(13, 69, 63, 23);
		bt5.setForeground(new Color(100, 149, 237));
		bt5.setFont(new Font("宋体", Font.BOLD, 14));
		bt5.setBorder(null);
		bt5.setBackground(Color.WHITE);
		menu5.add(bt5);
		bgContentPane.add(menu5);
		HistoryRecordListener listener = new HistoryRecordListener(this);
		menu5.addMouseListener(listener);
		lb5.addMouseListener(listener);
		bt5.addMouseListener(listener);
	}

	/**
	 * 
	 * <p>
	 * Title: setTestMenu
	 * </p>
	 * <p>
	 * Description:设置成绩查询菜单
	 * </p>
	 */
	public void setScoreMenu() {
		// 成绩查询菜单容器
		JPanel menu6 = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/menubg.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		menu6.setBounds(661, 28, 88, 99);
		menu6.setBorder(null);
		menu6.setForeground(Color.WHITE);
		menu6.setLayout(null);
		// 成绩查询菜单图标
		JLabel lb6 = new JLabel("");
		lb6.setBounds(13, 10, 60, 60);
		menu6.add(lb6);
		lb6.setIcon(new ImageIcon("image/menu6.jpg"));
		// 成绩查询菜单按钮
		JButton bt6 = new JButton("成绩查询");
		bt6.setBounds(13, 70, 61, 17);
		bt6.setForeground(new Color(100, 149, 237));
		bt6.setFont(new Font("宋体", Font.BOLD, 14));
		bt6.setBorder(null);
		bt6.setBackground(Color.WHITE);
		menu6.add(bt6);
		bgContentPane.add(menu6);
		ScoreListener listener = new ScoreListener(this);
		menu6.addMouseListener(listener);
		lb6.addMouseListener(listener);
		bt6.addMouseListener(listener);
	}

	/**
	 * 
	 * <p>
	 * Title: setBroadcastingMenu
	 * </p>
	 * <p>
	 * Description:系统名称
	 * </p>
	 */
	public void setBroadcastingMenu() {
		JLabel menu4 = new JLabel("<html>教 务<br><br>系 统</html>");
		menu4.setBounds(411, 28, 88, 99);
		menu4.setFont(new Font("宋体", Font.BOLD, 14));
		menu4.setHorizontalAlignment(SwingConstants.CENTER);
		menu4.setLayout(null);
		bgContentPane.add(menu4);
	}

	/**
	 * 
	 * <p>
	 * Title: setRemoteMenu
	 * </p>
	 * <p>
	 * Description:设置远程监控菜单
	 * </p>
	 */
	public void setRemoteMenu() {
		// 远程监控菜单容器
		JPanel menu2 = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/menubg.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		menu2.setBounds(166, 28, 88, 99);
		menu2.setBorder(null);
		menu2.setForeground(new Color(255, 255, 255));
		menu2.setLayout(null);

		// 远程监控图标
		JLabel lb2 = new JLabel("");
		lb2.setBounds(13, 10, 60, 60);
		menu2.add(lb2);
		lb2.setIcon(new ImageIcon("image/menu2.jpg"));
		// 远程监控按钮
		JButton bt2 = new JButton("远程监控");
		bt2.setBounds(13, 70, 61, 17);

		menu2.add(bt2);
		bt2.setForeground(new Color(100, 149, 237));
		bt2.setFont(new Font("宋体", Font.BOLD, 14));
		bt2.setBackground(Color.WHITE);
		bt2.setBorder(null);
		bgContentPane.add(menu2);
		// 事件对象
		RemoteMouseListener remote = new RemoteMouseListener(this);
		// 添加事件
		menu2.addMouseListener(remote);
		bt2.addMouseListener(remote);

	}

	/**
	 * 
	 * <p>
	 * Title: setAuxiliaryMenu
	 * </p>
	 * <p>
	 * Description: 学生信息显示 （向centerpl中添加组件）
	 * </p>
	 */
	public void setAuxiliaryMenu() {
		String sid = Constant.myStudent.getSid();
		JLabel info = null;
		String sname = Constant.myStudent.getName();
		String cname = scs.getClassAdministration(sid);
		info = new JLabel("<html>学号：<br><br>" + sid + "<br><br>" 
				+ "姓名：<br><br>" + sname + "<br><br>"
				+ "班级：<br><br>" + cname + "<br><br>"
				 + "</html>");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setBounds(20, 60, 90, 200);
		info.setFont(new Font("宋体", Font.BOLD, 12));
		info.setForeground(new Color(128, 128, 128));
		centerpl.add(info);
		info.setLayout(null);
		bgContentPane.setLayout(null);
	}

	/**
	 * 
	 * <p>
	 * Title: setTime
	 * </p>
	 * <p>
	 * Description:时间标志，在数据库中查找第几周***课
	 * </p>
	 */
	public void setTime() {
		long w = StudentCourseService.week;
		JLabel time = null;
		if (scs.findCurrentCourse(Constant.myStudent.getSid())) {
			String cname = scs.findCname(Constant.cid);
			time = new JLabel("<html>第" + w + "周<br><br>" + cname + "课</html>");
			// 启动定时任务
			new SignTimerTask();
		} else {
			time = new JLabel("<html>第" + w + "周<br><br>目前没课</html>");
		}
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setBounds(870, 49, 120, 47);
		time.setFont(new Font("宋体", Font.BOLD, 12));
		time.setForeground(new Color(128, 128, 128));
		bgContentPane.add(time);

	}

	/**
	 * 
	 * <p>
	 * Title: init
	 * </p>
	 * <p>
	 * Description: 初始化基本窗体
	 * </p>
	 */
	public void init() {
		this.setBackground();
		this.setCenterpl();
		this.setContentpl();
		this.setMenu();
		this.setAuxiliaryMenu();
		this.setTime();
	}

	/**
	 * 
	 * <p>
	 * Title: setSign
	 * </p>
	 * <p>
	 * Description: 设置签到界面，向centerpl中添加控件
	 * </p>
	 */
	public void setSign() {
		// 向centerpl中添加控件
		JPanel closepl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/btbackground.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		closepl.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textInactiveText, null, null, null));
		closepl.setBounds(113, 14, 104, 30);
		closepl.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("正在签到");
		lblNewLabel_1.setForeground(SystemColor.textInactiveText);
		lblNewLabel_1.setBounds(10, 5, 63, 23);
		closepl.add(lblNewLabel_1);
		JButton closebt = new JButton("×");
		closebt.setBounds(80, 5, 23, 23);
		closepl.add(closebt);
		closebt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init();
			}
		});
		closebt.setBackground(null);
		closebt.setBorder(null);
		centerpl.add(closepl);
	}

	/**
	 * 
	 * <p>
	 * Title: setRemote
	 * </p>
	 * <p>
	 * Description:设置远程监控页面，向centerpl中添加控件，向contentpl中添加控件
	 * </p>
	 */
	public void setRemote() {
		// 向centerpl中添加控件
		JPanel closepl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/btbackground.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		closepl.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textInactiveText, null, null, null));
		closepl.setBounds(113, 14, 104, 30);
		closepl.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("远程共享");
		lblNewLabel_1.setForeground(SystemColor.textInactiveText);
		lblNewLabel_1.setBounds(10, 5, 63, 23);
		closepl.add(lblNewLabel_1);

		JButton closebt = new JButton("×");
		closebt.setBounds(80, 5, 23, 23);
		closepl.add(closebt);
		closebt.addActionListener(event);
		closebt.setBackground(null);
		closebt.setBorder(null);
		centerpl.add(closepl);
		// 向contentpl中添加控件
		JButton remortbt = new JButton("开启远程连接");
		remortbt.setForeground(SystemColor.textInactiveText);
		remortbt.setFont(new Font("宋体", Font.PLAIN, 14));
		remortbt.setBounds(66, 130, 129, 31);
		// 添加事件监听
		remortbt.addActionListener(event);
		contentpl.add(remortbt);

		JPanel chatpl = new JPanel();
		chatpl.setBackground(SystemColor.inactiveCaption);
		chatpl.setBounds(227, 39, 499, 258);
		contentpl.add(chatpl);
		chatpl.setLayout(null);

		JTextArea textField = new JTextArea("  请发表您的问题");
		textField.setBounds(14, 13, 471, 200);
		textField.setFont(new Font("宋体", Font.PLAIN, 22));
		textField.setForeground(SystemColor.textHighlight);
		chatpl.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("发送");
		btnNewButton.setBounds(397, 217, 74, 35);
		chatpl.add(btnNewButton);
		// 事件对象
		IndexActionListener il=new IndexActionListener(this,textField);
		// 添加事件
		btnNewButton.addActionListener(il);
	}


	/**
	 * 
	 * <p>
	 * Title: setShareResource
	 * </p>
	 * <p>
	 * Description:设置共享资源页面，向centerpl中添加控件，向contentpl中添加控件
	 * </p>
	 */
	public void setShareResource() {
		// 向centerpl中添加控件
		JPanel closepl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/btbackground.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		closepl.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textInactiveText, null, null, null));
		closepl.setBounds(113, 14, 104, 30);
		closepl.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("资源共享");
		lblNewLabel_1.setForeground(SystemColor.textInactiveText);
		lblNewLabel_1.setBounds(10, 5, 63, 23);
		closepl.add(lblNewLabel_1);

		JButton closebt = new JButton("×");
		closebt.setBounds(80, 5, 23, 23);
		closepl.add(closebt);
		closebt.addActionListener(new IndexActionListener(this));
		closebt.setBackground(null);
		closebt.setBorder(null);
		centerpl.add(closepl);

		// 内容展示标题
		JLabel title = new JLabel("历 史 资 源");
		title.setForeground(new Color(119, 136, 153));
		title.setFont(new Font("宋体", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(341, 20, 105, 26);
		// 上传文件图片
		contentpl.add(title);
		JLabel uploads = new JLabel("");
		uploads.setIcon(new ImageIcon("image/upload.jpg"));
		uploads.setBounds(659, 10, 124, 36);
		contentpl.add(uploads);
		uploads.addMouseListener(new UploadMouseListener(this));
		// 滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(47, 64);
		scrollPane.setSize(703, 232);
		scrollPane.setBorder(null);
		contentpl.add(scrollPane);
		// 资源列表容器
		JPanel text = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/img1.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		text.setBackground(null);
		text.setForeground(new Color(173, 216, 230));
		text.setPreferredSize(new Dimension(700, 1000));
		scrollPane.setViewportView(text);
		// 获取所有资源
		List<ShareResource> list = ShareResourceDaoImpl.getAllResources();
		// 遍历所有已上传的资源
		for (ShareResource sr : list) {
			if (sr.getOldfile() != null && sr.getUploadtime() != null) {
				Students s = sr.getStu();
				Teacher t = sr.getTeacher();
				JLabel jl = null;
				if (t == null) {
					jl = new JLabel("题目：" + sr.getOldfile() + "   上传者：" + s.getName() + "    时间：" + sr.getUploadtime());
				} else if (s == null) {
					jl = new JLabel(
							"题目：" + sr.getOldfile() + "   上传者：" + t.getTname() + "    时间：" + sr.getUploadtime());
				}
				jl.setFont(new Font("宋体", Font.BOLD, 14));
				jl.setPreferredSize(new Dimension(700, 45));
				jl.setHorizontalAlignment(SwingConstants.CENTER);
				jl.addMouseListener(new ResourceMouseListener(this, sr));
				text.add(jl);
			}
		}
	}
	
	/**
	 * 
	 * <p>
	 * Title: setHistoryRecord
	 * </p>
	 * <p>
	 * Description:设置历史记录页面，向centerpl中添加控件，向contentpl中添加控件
	 * </p>
	 */
	public void setHistoryRecord() {
		// 向centerpl中添加控件
		JPanel closepl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/btbackground.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		closepl.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textInactiveText, null, null, null));
		closepl.setBounds(113, 14, 104, 30);
		closepl.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("历史记录");
		lblNewLabel_1.setForeground(SystemColor.textInactiveText);
		lblNewLabel_1.setBounds(10, 5, 63, 23);
		closepl.add(lblNewLabel_1);

		JButton closebt = new JButton("×");
		closebt.setBounds(80, 5, 23, 23);
		closepl.add(closebt);
		closebt.addActionListener(new IndexActionListener(this));
		closebt.setBackground(null);
		closebt.setBorder(null);
		centerpl.add(closepl);

		// 内容展示标题
		JLabel title = new JLabel("签 到 历 史 记 录");
		title.setForeground(new Color(119, 136, 153));
		title.setFont(new Font("宋体", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(341, 20, 200, 26);
		contentpl.add(title);
		// 滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(47, 64);
		scrollPane.setSize(703, 232);
		scrollPane.setBorder(null);
		contentpl.add(scrollPane);
		// 历史记录容器
		JPanel text = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/img1.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		text.setBackground(null);
		text.setForeground(new Color(173, 216, 230));
		text.setPreferredSize(new Dimension(700, 1000));
		scrollPane.setViewportView(text);
		// 获取所有资源
		RecordService recordService = new RecordService();
		List<Record> list = recordService.getRecord(Constant.myStudent.getSid());
		// 遍历所有已上传的资源
		if (list.isEmpty()) {
			JLabel jl = null;
			jl = new JLabel("近7天未签到");
			jl.setFont(new Font("宋体", Font.BOLD, 14));
			jl.setForeground(new Color(119, 136, 153));
			jl.setHorizontalAlignment(SwingConstants.CENTER);
			jl.setBounds(341, 20, 105, 26);
			text.add(jl);
		}else {
			for (Record sr : list) {
				int status = sr.getState();
				String statu = "";
				if (status == 1) {
					statu = "已签到";
				}else {
					statu = "未签到";
				}
				Date date = sr.getDate();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		        String datestr = format.format(date); 
				String cname = scs.findCname(sr.getClassin().getClass_id());
				JLabel jl = null;
				jl = new JLabel("班级："+ cname + "    状态：" + statu + "    时间：" + datestr);
				jl.setFont(new Font("宋体", Font.BOLD, 14));
				jl.setPreferredSize(new Dimension(700, 45));
				jl.setHorizontalAlignment(SwingConstants.CENTER);
				text.add(jl);
			}
		}
	}
	
	/**
	 * 
	 * <p>
	 * Title: setScoreRecord
	 * </p>
	 * <p>
	 * Description:设置成绩查询页面，向centerpl中添加控件，向contentpl中添加控件
	 * </p>
	 */
	public void setScoreRecord() {
		// 向centerpl中添加控件
		JPanel closepl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/btbackground.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		closepl.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textInactiveText, null, null, null));
		closepl.setBounds(113, 14, 104, 30);
		closepl.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("成绩查询");
		lblNewLabel_1.setForeground(SystemColor.textInactiveText);
		lblNewLabel_1.setBounds(10, 5, 63, 23);
		closepl.add(lblNewLabel_1);

		JButton closebt = new JButton("×");
		closebt.setBounds(80, 5, 23, 23);
		closepl.add(closebt);
		closebt.addActionListener(new IndexActionListener(this));
		closebt.setBackground(null);
		closebt.setBorder(null);
		centerpl.add(closepl);

		// 内容展示标题
		JLabel title = new JLabel("学 业 成 绩 信 息");
		title.setForeground(new Color(119, 136, 153));
		title.setFont(new Font("宋体", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(341, 20, 200, 26);
		contentpl.add(title);
		// 滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(47, 64);
		scrollPane.setSize(700, 232);
		scrollPane.setBorder(null);
		contentpl.add(scrollPane);
		// 成绩查询容器
		JPanel text = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/img1.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		text.setBackground(null);
		text.setForeground(new Color(173, 216, 230));
		text.setPreferredSize(new Dimension(700, 700));
		scrollPane.setViewportView(text);
		// 获取所有资源
		RecordService recordService = new RecordService();
		List<Score> scorelist = recordService.getScore(Constant.myStudent.getSid());
		// 遍历所有成绩
		if (scorelist.isEmpty()) {
			JLabel jl = null;
			jl = new JLabel("成绩未公布");
			jl.setFont(new Font("宋体", Font.BOLD, 14));
			jl.setForeground(new Color(119, 136, 153));
			jl.setHorizontalAlignment(SwingConstants.CENTER);
			jl.setBounds(341, 20, 105, 26);
			text.add(jl);
		}else {
			String[][] datas = new String[14][4];
			int i = 0;
			for (Score sr : scorelist) {
				double score = sr.getScore();
				String statu = "";
				if (score >= 60 ) {
					statu = "通过";
				}else {
					statu = "未通过";
				}
				String cname = sr.getCourse().getCname();
				int cid = sr.getCourse().getCourse_id();
				String[] data = {cid+"", cname, score+"", statu};
				datas[i] = data;
				i++;
			}
			String[] columnNames = {"课程编号","课程名","成绩","状态"};
			JTable table = new JTable(datas, columnNames);
			table.setBackground(Color.WHITE);
			table.setRowHeight(26);
			JTableHeader head = table.getTableHeader(); // 创建表格标题对象
	        head.setPreferredSize(new Dimension(head.getWidth(), 30));// 设置表头大小
	        head.setFont(new Font("楷体", Font.BOLD, 18));// 设置表格字体
			JScrollPane scrollPanes = new JScrollPane(table);
			scrollPanes.setBackground(null);
			scrollPanes.setFont(new Font("宋体", Font.PLAIN, 14));
			scrollPanes.setBounds(20, 20, 700, 500);
			text.add(scrollPanes);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: setVideoScreenContent
	 * </p>
	 * <p>
	 * Description:设置录屏页面，向centerpl中添加控件，向contentpl中添加控件
	 * </p>
	 */
	public void setVideoScreenContent() {
		JPanel closepl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/btbackground.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		closepl.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textInactiveText, null, null, null));
		closepl.setBounds(113, 14, 104, 30);
		closepl.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("录 屏");
		lblNewLabel_1.setForeground(SystemColor.textInactiveText);
		lblNewLabel_1.setBounds(10, 5, 63, 23);
		closepl.add(lblNewLabel_1);

		JButton closebt = new JButton("×");
		closebt.setBounds(80, 5, 23, 23);
		closepl.add(closebt);
		closebt.addActionListener(event);
		closebt.setBackground(null);
		closebt.setBorder(null);
		centerpl.add(closepl);//793, 322

		
		JPanel panel = new JPanel();
		panel.setBounds(377, 30, 386, 250);
		contentpl.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon("image/1.png"));
		lblNewLabel_2.setBounds(0, 0, 386, 250);
		
		
		JPanel panel_1 = new JPanel(){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/img1.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		panel_1.setBounds(14, 30, 340, 250);
		contentpl.add(panel_1);
		panel_1.setLayout(null);
		
		
		int i=0;
		List<String> list=new ArrayList<String>();
		list.add("开 启 录 屏");
		list.add("暂 停 录 屏");
		list.add("关 闭 录 屏");
		for(String s:list)  {
			JButton bt3 = new JButton(s);
			bt3.setForeground(SystemColor.textHighlight);
			bt3.setFont(new Font("宋体", Font.BOLD, 18));
			bt3.setBounds(60, 20+i*70, 200, 45);
			bt3.setPreferredSize(new Dimension(166,44));
			bt3.addActionListener(this.indexActionListener);
			panel_1.add(bt3);
			i++;
		}
	}
	
	
	/**
	 * 
	 * <p>
	 * Title: jumpScoreRecord
	 * </p>
	 * <p>
	 * Description: 转到录屏界面
	 * </p>
	 */
	public void jumpVideoScreen() {
		this.setBackground();
		this.setCenterpl();
		this.setContentpl();
		this.setMenu();
		this.setAuxiliaryMenu();
		this.setTime();
		this.setVideoScreenContent();
	}
	
	/**
	 * 
	 * <p>
	 * Title: jumpScoreRecord
	 * </p>
	 * <p>
	 * Description: 转到成绩查询界面
	 * </p>
	 */
	public void jumpScoreRecord() {
		this.setBackground();
		this.setCenterpl();
		this.setContentpl();
		this.setMenu();
		this.setAuxiliaryMenu();
		this.setTime();
		this.setScoreRecord();
	}
	
	/**
	 * 
	 * <p>
	 * Title: jumpHistoryRecord
	 * </p>
	 * <p>
	 * Description: 转到历史记录界面
	 * </p>
	 */
	public void jumpHistoryRecord() {
		this.setBackground();
		this.setCenterpl();
		this.setContentpl();
		this.setMenu();
		this.setAuxiliaryMenu();
		this.setTime();
		this.setHistoryRecord();
	}

	/**
	 * 
	 * <p>
	 * Title: jumpShareResource
	 * </p>
	 * <p>
	 * Description: 转到共享资源界面
	 * </p>
	 */
	public void jumpShareResource() {
		this.setBackground();
		this.setCenterpl();
		this.setContentpl();
		this.setMenu();
		this.setAuxiliaryMenu();
		this.setTime();
		this.setShareResource();
	}

	/**
	 * 
	 * <p>
	 * Title: jumpSign
	 * </p>
	 * <p>
	 * Description:转到签到界面
	 * </p>
	 */
	public void jumpSign() {
		this.setBackground();
		this.setBackground();
		this.setCenterpl();
		this.setContentpl();
		this.setMenu();
		this.setAuxiliaryMenu();
		this.setTime();
		this.setSign();
	}

	/**
	 * 
	 * <p>
	 * Title: jumpRemote
	 * </p>
	 * <p>
	 * Description:转到远程监控界面
	 * </p>
	 */
	public void jumpRemote() {
		this.setBackground();
		this.setBackground();
		this.setCenterpl();
		this.setContentpl();
		this.setMenu();
		this.setAuxiliaryMenu();
		this.setTime();
		this.setRemote();
	}

	/**
	 * 主窗体创建
	 */
	public Index() {
		// 设置窗体大小
		this.setBounds(0, 0, 1000, 600);
		// 窗体大小不能改变
		this.setResizable(false);
		// 居中显示
		this.setLocationRelativeTo(null);
		// 设置图标
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image\\logo1.png"));
		// 设置关闭状态
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 窗体可见
		this.setVisible(true);
		//录屏之间监听
		this.indexActionListener=new IndexActionListener();
		// 事件监听
		event = new IndexActionListener(this);
		// 关闭窗体的事件监听
		this.addWindowListener(new WindowAdapter() {
			// 关闭窗口时，更改数据的teacherstatus状态
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				// 更改状态
				StudentCourseDao scs = new StudentCourseDao();
				scs.changeStudentStatus(Constant.myStudent.getSid(), 0);
				scs.changeStudentIp(Constant.myStudent.getSid(), "");
				// 给老师发送命令，告诉他关闭了连接
				if (Constant.session != null) {
					FileContent f = new FileContent();
					f.setCommand(Communication.closeCommand);
					Constant.session.write(f);
				}
			}
		});
	}
}
