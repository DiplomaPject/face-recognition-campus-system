package team.javaSpirit.teachingAssistantPlatform.ui.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.mina.core.session.IoSession;

import team.javaSpirit.teachingAssistantPlatform.common.Constant;
import team.javaSpirit.teachingAssistantPlatform.course.service.CourseServiceImpl;
import team.javaSpirit.teachingAssistantPlatform.entity.ClassCourse;
import team.javaSpirit.teachingAssistantPlatform.entity.FeedBack;
import team.javaSpirit.teachingAssistantPlatform.entity.ShareResource;
import team.javaSpirit.teachingAssistantPlatform.entity.Students;
import team.javaSpirit.teachingAssistantPlatform.entity.Teacher;
import team.javaSpirit.teachingAssistantPlatform.feedback.service.FeedBackServiceImpl;
import team.javaSpirit.teachingAssistantPlatform.remoteMonitoring.service.Service;
import team.javaSpirit.teachingAssistantPlatform.remoteMonitoring.service.ServiceOperationServiceImpl;
import team.javaSpirit.teachingAssistantPlatform.studentScore.dao.StudentScoreDao;
import team.javaSpirit.teachingAssistantPlatform.studentScore.service.StudentScoreService;
import team.javaSpirit.teachingAssistantPlatform.studentSignIn.service.StudentSignInServiceImpl;
import team.javaSpirit.teachingAssistantPlatform.ui.event.CheckBoxMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.DeleteMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.IndexActionListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.MyItemListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.PlanMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.RandomCallMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.ResourceMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.ShareResourceMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.StuShowActionListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.StudentScoreMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.StudentSignMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.UploadMouseListener;
import team.javaSpirit.teachingAssistantPlatform.ui.event.VideotapeListener;
import team.javaSpirit.teachingAssistantPlatform.upload.dao.ShareResourceDaoImpl;
import team.javaSpirit.teachingAssistantPlatform.vediotape.service.VediotapeService;

/**
 * 
 * <p>
 * Title: Index
 * </p>
 * <p>
 * Description:教师端主页面，显示学生签到请假信息
 * </p>
 * 
 */
public class Index extends JFrame {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/* 服务对象 */
	Service service;
	/* 背景面板 */
	private JPanel bgContentPane;
	/* 显示学生签到情况的标签 */
	private JTable table_1;
	/* 显示学生成绩的标签 */
	public static DefaultTableModel  mode;
	public static JTable  table_2;
	/* 成绩录入菜单 */
	private JPanel menu1;
	/* 教学计划菜单 */
	private JPanel menu2;
	/* 签到信息菜单 */
	private JPanel menu3;
	/* logo显示 */
	private JPanel menu4;
	/* 随机点名菜单 */
	private JPanel menu5;
	/* 课堂反馈菜单 */
	private JPanel menu9;
	/* 教材管理菜单 */
	private JPanel menu10;
	/* 教师信息菜单 */
	private JPanel lmenu1;
	/* 显示当前是第几周是什么课的文本标签 */
	private JLabel lblNewLabel;
	/* 系统名称 */
	private JLabel lblNewLabel1;
	/** 中间模块容器 */
	private JPanel centerpl;
	/** 内容模块容器 */
	public static JPanel contentpl;
	/* 监听事件 */
	private IndexActionListener event;
	/*成绩对应的学号*/
	public static String sid;
	public static int cid;
	/*课程列表*/
	public static List<Object[]> courseList;
	
	public String getSid() {
		return sid;
	}
	
	public void setSid(String sid) {
		this.sid = sid;
	}

	public static int getCid() {
		return cid;
	}

	public static void setCid(int cid) {
		Index.cid = cid;
	}

	/**
	 * <p>
	 * Title: setBackground
	 * </p>
	 * <p>
	 * Description: 设置背景图的位置，宽和高。
	 * </p>
	 */
	public void setBackground() {

		bgContentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/bgindex.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		bgContentPane.setBounds(0, 0, 100, 1000);
		bgContentPane.setBorder(null);
		this.setContentPane(bgContentPane);
	}

	/**
	 * <p>
	 * Title: setRemotecontrol
	 * </p>
	 * <p>
	 * Description: 设置成绩录入菜单面板
	 * </p>
	 */
	public void setRemotecontrol() {
		// 创建一个成绩录入面板对象
		menu1 = new JPanel();
		menu1.setLayout(null);
		menu1.setForeground(Color.WHITE);
		menu1.setBounds(19, 18, 88, 99);
		menu1.setBorder(null);
		menu1.setOpaque(false);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("image\\menu4.png"));
		label.setBounds(13, 10, 60, 60);
		// 面板添加图片标签label
		menu1.add(label);
		// 按钮
		JButton button = new JButton("成绩录入");
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("宋体", Font.BOLD, 16));
		button.setBorder(null);
		button.setBackground(Color.WHITE);
		button.setOpaque(false);
		button.setBounds(0, 70, 88, 16);
		menu1.add(button);
		
		StudentScoreMouseListener stuScore = new StudentScoreMouseListener(this);
		// 添加事件监听
		menu1.addMouseListener(stuScore);
		button.addMouseListener(stuScore);
	}

	/**
	 * <p>
	 * Title: setRecordScreen
	 * </p>
	 * <p>
	 * Description: 设置教学计划菜单面板，有标记图和下拉菜单。
	 * </p>
	 */
	public void setRecordScreen() {
		// 教学计划菜单
				menu2 = new JPanel();
				menu2.setLayout(null);
				menu2.setForeground(Color.WHITE);
				menu2.setBounds(117, 18, 88, 110);
				menu2.setBorder(null);
				menu2.setOpaque(false);
				JLabel label_4 = new JLabel("");
				label_4.setIcon(new ImageIcon("image\\menu5.png"));
				label_4.setBounds(14, 13, 61, 58);
				menu2.add(label_4);
				// 教学计划按钮
				JButton button_7 = new JButton("教学计划");
				button_7.addActionListener(event);
				button_7.setForeground(new Color(255,255,255));
				button_7.setFont(new Font("宋体", Font.BOLD, 16));
				button_7.setBorder(null);
				button_7.setBackground(Color.WHITE);
				button_7.setOpaque(false);
				button_7.setBounds(0, 70, 88, 16);
				menu2.add(button_7);
				PlanMouseListener listener = new PlanMouseListener(this);
				// 添加事件监听
				menu2.addMouseListener(listener);
				button_7.addMouseListener(listener);
	}


	/**
	 * <p>
	 * Title: setStuzs
	 * </p>
	 * <p>
	 * Description: 设置Logo
	 * </p>
	 */
	public void setStuzs() {
		menu4 = new JPanel();
		menu4.setLayout(null);
		menu4.setForeground(Color.WHITE);
		menu4.setBounds(321, 18, 88, 99);
		menu4.setBorder(null);
		menu4.setOpaque(false);

		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon("image\\logo2.png"));
		label_6.setBounds(13, 20, 60, 60);
		menu4.add(label_6);
	}

	/**
	 * <p>
	 * Title: setRandomcall
	 * </p>
	 * <p>
	 * Description: 设置随机点名菜单面板，有标记图和按钮
	 * </p>
	 */
	public void setRandomcall() {
		// 随机点名菜单
		menu5 = new JPanel();
		menu5.setLayout(null);
		menu5.setForeground(Color.WHITE);
		menu5.setBounds(423, 18, 88, 99);
		menu5.setBorder(null);
		menu5.setOpaque(false);
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon("image\\menu3.png"));
		label_7.setBounds(13, 10, 60, 60);
		menu5.add(label_7);
		// 随机点名按钮
		JButton button_9 = new JButton("随机点名");
		button_9.setForeground(new Color(255, 255, 255));
		button_9.setFont(new Font("宋体", Font.BOLD, 16));
		button_9.setBorder(null);
		button_9.setBackground(Color.WHITE);
		button_9.setOpaque(false);
		button_9.setBounds(0, 70, 88, 16);
		menu5.add(button_9);
		// 事件对象
		RandomCallMouseListener randomCall = new RandomCallMouseListener(this);
		// 添加事件监听
		menu5.addMouseListener(randomCall);
		button_9.addMouseListener(randomCall);
	}

	/**
	 * <p>
	 * Title: setReleasejob
	 * </p>
	 * <p>
	 * Description: 设置教学反馈菜单面板，有标记图和按钮
	 * </p>
	 */
	public void setTeachback() {
		// 教学反馈菜单
		menu9 = new JPanel();
		menu9.setLayout(null);
		menu9.setForeground(Color.WHITE);
		menu9.setBounds(525, 18, 88, 99);
		menu9.setBorder(null);
		menu9.setOpaque(false);
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("image\\menu2.png"));
		label_2.setBounds(13, 10, 60, 60);
		menu9.add(label_2);
		// 教学反馈按钮
		JButton button_3 = new JButton("教学反馈");
		button_3.setForeground(new Color(255, 255, 255));
		button_3.setFont(new Font("宋体", Font.BOLD, 16));
		button_3.setBorder(null);
		button_3.setBackground(Color.WHITE);
		button_3.setOpaque(false);
		button_3.setBounds(0, 70, 88, 16);
		menu9.add(button_3);
		DeleteMouseListener dm=new DeleteMouseListener(this);
		button_3.addMouseListener(dm);
		menu9.addMouseListener(dm);
	}

	/**
	 * <p>
	 * Title: setReleasejob
	 * </p>
	 * <p>
	 * Description: 设置教材管理菜单面板，有标记图和按钮
	 * </p>
	 */
	public void setResourcesharing() {
		// 教材管理菜单
		menu10 = new JPanel();
		menu10.setLayout(null);
		menu10.setForeground(Color.WHITE);
		menu10.setBounds(631, 18, 88, 99);
		menu10.setBorder(null);
		menu10.setOpaque(false);
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("image\\menu7.png"));
		label_3.setBounds(13, 10, 60, 60);
		menu10.add(label_3);
		menu10.addMouseListener(new ShareResourceMouseListener(this));
		// 教材管理按钮
		JButton button_4 = new JButton("教材管理");
		button_4.setForeground(new Color(255, 255, 255));
		button_4.setFont(new Font("宋体", Font.BOLD, 16));
		button_4.setBorder(null);
		button_4.setBackground(Color.WHITE);
		button_4.setOpaque(false);
		button_4.setBounds(0, 70, 88, 16);
		menu10.add(button_4);
		button_4.addMouseListener(new ShareResourceMouseListener(this));
	}

	/**
	 * 中间内容底部容器设置
	 */
	public void setCenterpl() {
		centerpl = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/bgindex.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		centerpl.setBounds(31, 137, 933, 407);
		centerpl.setBorder(BorderFactory.createMatteBorder(1,0,0,0,new Color(42, 118, 168)));
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
				ImageIcon ii = new ImageIcon("image/bgindex.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		contentpl.setBounds(113, 44, 793, 322);
		contentpl.setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(56, 139, 181)));
		centerpl.add(contentpl);
		contentpl.setLayout(null);
	}

	/**
	 * 
	 * <p>
	 * Title: centerContent
	 * </p>
	 * <p>
	 * Description:显示学生签到请假情况
	 * </p>
	 */
	public void centerContent() {
		JScrollPane scrollPanes = new JScrollPane();
		scrollPanes.setLocation(1, 1);
		scrollPanes.setSize(790, 320);
		scrollPanes.setBorder(null);
		contentpl.add(scrollPanes);
		// 签到情况的服务
		StudentSignInServiceImpl ss = new StudentSignInServiceImpl();
		// 学生签到的情况
		List<Object[]> signStu = ss.SignInStudent();
		final Object[] columnNames = { "签到", "迟到", "旷课" };
		int row = signStu.size();
		Object[][] rowData = new Object[row][3];
		int i = 0, j = 0, k = 0, l = 0;
		for (Object[] stu : signStu) {
			if ((int) stu[2] == 1) {
				rowData[i][0] = stu[0] + "-" + stu[1];
				i++;
			} else if ((int) stu[2] == 2) {
				rowData[j][1] = stu[0] + "-" + stu[1];
				j++;
			}  else if ((int) stu[2] == 0) {
				rowData[l][2] = stu[0] + "-" + stu[1];
				l++;
			}
		}

		table_1 = new JTable(rowData, columnNames);
		// 设置table内容居中显示
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table_1.setDefaultRenderer(Object.class, tcr);
		table_1.setBounds(1, 32, 790, 600);
		table_1.setFont(new Font("宋体", Font.PLAIN, 16));
		table_1.setRowHeight(50);// 设置每行的高度
		table_1.setRowMargin(5);// 设置相邻两行单元格的距离
		table_1.setShowHorizontalLines(true);// 是否显示水平的网格线
		JTableHeader head = table_1.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 50));// 设置表头大小
        head.setFont(new Font("楷体", Font.BOLD, 18));// 设置表格字体
		contentpl.setLayout(null);
		contentpl.add(table_1);

		scrollPanes.setViewportView(table_1);
		
		this.setVisible(true);
	}

	/**
	 * 
	 * <p>
	 * Title: init
	 * </p>
	 * <p>
	 * Description: 初始所有的标签、容器、内容
	 * </p>
	 */
	public void init() {
		topMenu();
		leftMenu();
		// 设置背景图
		setBackground();
		// 设置布局方式为绝对定位
		this.getContentPane().setLayout(null);
		// 时间标志
		setTime();
		// 中间容器
		this.setCenterpl();
		this.setContentpl();
		// 布局设置
		setGroupLayout();
		//监听
		setListener();
	}

	/**
	 * <p>
	 * Title: topMenu
	 * </p>
	 * <p>
	 * Description: 顶部菜单栏函数。 调用签到、教学计划、随机点名、、课堂反馈、教材管理的设置函数。
	 * </p>
	 */
	public void topMenu() {
		// 添加成绩录入面板
		this.setRemotecontrol();
		// 签到菜单
		this.setStusign();
		// 添加教学计划菜单
		this.setRecordScreen();
		// 添加logo
		this.setStuzs();
		// 添加随机点名菜单
		this.setRandomcall();
		// 添加课堂反馈菜单
		this.setTeachback();
		// 添加教材管理菜单
		this.setResourcesharing();
	}

	/**
	 * <p>
	 * Title: setStusign
	 * </p>
	 * <p>
	 * Description: 设置学生签到菜单面板，有标记图和按钮
	 * </p>
	 */
	public void setStusign() {
		// 学生签到信息
		menu3 = new JPanel();
		menu3.setBackground(new Color(176, 196, 222));
		menu3.setBounds(219, 18, 88, 110);
		menu3.setLayout(null);
		menu3.setOpaque(false);

		JLabel label_11 = new JLabel("");
		label_11.setIcon(new ImageIcon("image\\menu1.png"));
		label_11.setBounds(10, 13, 60, 67);
		menu3.add(label_11);
		// 按钮
		JButton bt8 = new JButton("签到信息");
		bt8.setForeground(new Color(255, 255, 255));
		bt8.setFont(new Font("宋体", Font.BOLD, 16));
		bt8.setBorder(null);
		bt8.setBackground(Color.WHITE);
		bt8.setOpaque(false);
		bt8.setBounds(0, 70, 88, 16);
		menu3.setLayout(null);
		menu3.add(bt8);

		// 事件对象
		StudentSignMouseListener studentSign = new StudentSignMouseListener(this);
		// 添加事件监听
		menu3.addMouseListener(studentSign);
		bt8.addMouseListener(studentSign);
	}

	/**
	 * 
	 * <p>
	 * Title: leftMenu
	 * </p>
	 * <p>
	 * Description:左侧菜单栏
	 * </p>
	 */
	public void leftMenu() {
		// 添加教师信息
		this.setTeacherInfo();
	}

	/**
	 * <p>
	 * Title: setTeacherInfo
	 * </p>
	 * <p>
	 * Description: 设置教师信息
	 * </p>
	 */
	public void setTeacherInfo() {
		// 教师信息
		lmenu1 = new JPanel();
		lmenu1.setBackground(new Color(176, 196, 222));
		lmenu1.setBounds(50, 150, 90, 200);
		lmenu1.setLayout(null);
		lmenu1.setOpaque(false);
		String tId = Constant.myTeacher.getTid();
		String tName = Constant.myTeacher.getTname();
		JLabel label_11 = new JLabel("<html>教师号：<br><br>" + tId + "<br><br>姓名：<br><br>"+ tName +"</html>");
		label_11.setBounds(10, 20, 80, 150);
		label_11.setForeground(Color.WHITE);
		lmenu1.add(label_11);
		lmenu1.setLayout(null);
	}

	/**
	 * 
	 * <p>
	 * Title: setTime
	 * </p>
	 * <p>
	 * Description:时间标志，上课标志。
	 * </p>
	 */
	public void setTime() {
		CourseServiceImpl cs = new CourseServiceImpl();
		long week = CourseServiceImpl.week;
		lblNewLabel1 = new JLabel("<html>教 务 系 统 教 师 端</html>");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel1.setForeground(Color.WHITE);
		lblNewLabel1.setBounds(733, 40, 240, 26);
		String tid = Constant.myTeacher.getTid();
		//System.out.println("tid:"+tid);
		if (cs.findCurrentCourse(tid)) {
			String cname = cs.findCname(Constant.cid);
			lblNewLabel = new JLabel("第" + week + "周:" + cname + "课");
		} else {
			lblNewLabel = new JLabel("第" + week + "周:目前没课");
		}
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(733, 80, 240, 20);
	}

	/**
	 * 
	 * <p>
	 * Title: setGroupLayout
	 * </p>
	 * <p>
	 * Description:整体布局设置
	 * </p>
	 */
	public void setGroupLayout() {
		bgContentPane.setLayout(null);
		bgContentPane.add(lmenu1);
		bgContentPane.add(menu1);
		bgContentPane.add(menu2);
		bgContentPane.add(menu3);
		bgContentPane.add(menu4);
		bgContentPane.add(menu5);
		bgContentPane.add(menu9);
		bgContentPane.add(menu10);
		bgContentPane.add(centerpl);
		bgContentPane.add(lblNewLabel);
		bgContentPane.add(lblNewLabel1);
	}

	/**
	 * 
	 * <p>
	 * Title: jumpIndex
	 * </p>
	 * <p>
	 * Description: 跳转学生签到主页面
	 * </p>
	 */
	public void jumpIndex() {
		// 顶部菜单栏
		topMenu();
		// 左侧菜单栏
		leftMenu();
		// 设置背景图
		setBackground();
		// 设置布局方式为绝对定位
		this.getContentPane().setLayout(null);
		// 时间标志
		setTime();
		// 中间容器
		this.setCenterpl();
		this.setContentpl();
		// 布局设置
		setGroupLayout();
		// 内容页，显示学生签到请假情况
		centerContent();
	}
	
	/**
	 * 
	 * <p>
	 * Title: jumpPlan
	 * </p>
	 * <p>
	 * Description:跳转到教学计划界面
	 * </p>
	 */
	public void jumpPlan() {
		// 顶部菜单栏
		topMenu();
		// 左侧菜单栏
		leftMenu();
		// 设置背景图
		setBackground();
		// 设置布局方式为绝对定位
		this.getContentPane().setLayout(null);
		// 时间标志
		setTime();
		// 中间容器
		this.setCenterpl();
		this.setContentpl();
		// 聊天窗口
		//chatView();
		// 布局设置
		setGroupLayout();
		// 内容设置，学生演示内容页，有小电脑和名字（学生按钮）
		setPlan();
	}
	
	/**
	 * 
	 * <p>
	 * Title: selectstuScore
	 * </p>
	 * <p>
	 * Description:中间内容页，显示成绩录入情况
	 * </p>
	 */
	public void selectstuScore() {
		JLabel title = new JLabel("科目选择: ");
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("宋体", Font.BOLD, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(480, 5, 100, 26);
		contentpl.add(title);
		
		CourseServiceImpl cs = new CourseServiceImpl();
		List<Object[]> courses = cs.findCoursePlan(Constant.myTeacher.getTid());
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setEnabled(true);
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(580, 5, 200, 20);
		
		for (Object[] objects : courses) {
			String id = objects[0] + "";
			String name = (String)objects[1];
			comboBox_2.addItem( id+ " "+name);
		}
		
		//System.out.println("id:"+first);
		contentpl.add(comboBox_2);
		MyItemListener listener = new MyItemListener(comboBox_2, this);
		comboBox_2.addItemListener(listener);
		
		JScrollPane scrollPanes = new JScrollPane();
		scrollPanes.setLocation(1, 40);
		scrollPanes.setSize(790, 320);
		scrollPanes.setBorder(null);
		contentpl.add(scrollPanes);
		
		if (courseList == null || courseList.size() == 0) {
			StudentScoreService ss = new StudentScoreService();
			String first = courses.get(0)[0] + "";
			this.courseList = ss.courseStudent(Integer.valueOf(first));
		}
		List<Object[]> scores = courseList;
		final Object[] columnNames = { "学号", "姓名", "科目", "成绩" };
		
		int row = scores.size();
		//System.out.println("行数:"+row);
		Object[][] rowData = new Object[row][4];
		Double s = 0.0;
		String str = s+"";
		int i = 0;
		for (Object[] stu : scores) {
			System.out.println(stu[0]);
			rowData[i][0] = stu[0];
			rowData[i][1] = stu[1];
			rowData[i][2] = stu[2];
			rowData[i][3] = stu[3];
			i++;
		}
		
		mode = new DefaultTableModel(rowData,columnNames);
		table_2 = new JTable(mode);
		table_2.setModel(mode);
		table_2.setEnabled(true);
		//table_2 = new JTable (rowData, columnNames);
		// 设置table内容居中显示
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table_2.setDefaultRenderer(Object.class, tcr);
		table_2.setBounds(1, 32, 790, 600);
		table_2.setFont(new Font("宋体", Font.PLAIN, 16));
		table_2.setRowHeight(50);// 设置每行的高度
		table_2.setRowMargin(5);// 设置相邻两行单元格的距离
		table_2.setShowHorizontalLines(true);// 是否显示水平的网格线
		JTableHeader head = table_2.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 50));// 设置表头大小
        head.setFont(new Font("楷体", Font.BOLD, 18));// 设置表格字体
		contentpl.setLayout(null);
		contentpl.add(table_2);

		scrollPanes.setViewportView(table_2);
		// 设置table内容居中显示	
		table_2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
               //得到选中的行列的索引值
              int r= table_2.getSelectedRow();
              int c= table_2.getSelectedColumn();
              if (c == 3) {
            	  String sid= (String) table_2.getValueAt(r, 0);
            	  setSid(sid);
            	  String cname = (String) table_2.getValueAt(r, 2);
            	  cid = new StudentScoreDao().searchCourseId(cname);
            	  setCid(cid);
            	  //弹出输入框
            	  Score score=new Score();
          		  score.init();
              } 
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	// TODO Auto-generated method stub
            	super.mouseExited(e);
            	scrollPanes.validate(); 
            	scrollPanes.repaint(); 
    			mode.fireTableDataChanged();
    			table_2.setModel(mode);
    			table_2.setEnabled(true);
    			
            }
        }); 

		this.setVisible(true);
	}
	
	/**
	 * 
	 * <p>
	 * Title: setPlan
	 * </p>
	 * <p>
	 * Description:中间内容页，显示教学计划情况
	 * </p>
	 */
	public void setPlan() {
		// 课程信息的服务
		CourseServiceImpl ss = new CourseServiceImpl();
		List<Object[]> courses = ss.findCoursePlan(Constant.myTeacher.getTid());
		final Object[] columnNames = { "课程号", "课程名", "学分", "学时", "主修标记", "课程性质" };
		System.out.println("课程："+ courses.toString());
		int row = courses.size();
		Object[][] rowData = new Object[row][6];
		Double s = 0.0;
		String str = s+"";
		int i = 0;
		for (Object[] c : courses) {
			rowData[i][0] = c[0];
			rowData[i][1] = c[1];
			rowData[i][2] = c[2];
			rowData[i][3] = c[3];
			rowData[i][4] = c[4];
			rowData[i][5] = c[5];
			i++;
		}

		table_1 = new JTable(rowData, columnNames);
		// 设置table内容居中显示
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table_1.setDefaultRenderer(Object.class, tcr);
		table_1.setBounds(1, 32, 790, 300);
		table_1.setFont(new Font("宋体", Font.PLAIN, 16));
		table_1.setRowHeight(50);// 设置每行的高度
		table_1.setRowMargin(5);// 设置相邻两行单元格的距离
		table_1.setShowHorizontalLines(true);// 是否显示水平的网格线
		JTableHeader head = table_1.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 50));// 设置表头大小
        head.setFont(new Font("楷体", Font.BOLD, 18));// 设置表格字体
		contentpl.setLayout(null);
		contentpl.add(table_1);

		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(1, 1, 790, 520);
		scrollPane.setEnabled(false);
		contentpl.add(scrollPane);
		this.setVisible(true);
	}
	
	/**
	 * 
	 * <p>
	 * Title: jumpScore
	 * </p>
	 * <p>
	 * Description:跳转到成绩录入界面
	 * </p>
	 */
	public void jumpScore() {
		// 顶部菜单栏
		topMenu();
		// 左侧菜单栏
		leftMenu();
		// 设置背景图
		setBackground();
		// 设置布局方式为绝对定位
		this.getContentPane().setLayout(null);
		// 时间标志
		setTime();
		// 中间容器
		this.setCenterpl();
		this.setContentpl();

		// 布局设置
		setGroupLayout();
		// 内容设置
		selectstuScore();
	}

	/**
	 * 
	 * <p>
	 * Title: setShareResource
	 * </p>
	 * <p>
	 * Description:教材管理
	 * </p>
	 */
	public void setShareResource() {
		//centerpl.setLayout(null);
		// 内容展示标题
		JLabel title = new JLabel("教 材 管 理");
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("宋体", Font.BOLD, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(341, 20, 120, 26);
		contentpl.add(title);
		// 上传文件图片
		JLabel uploads = new JLabel("");
		uploads.setIcon(new ImageIcon("image/upload.jpg"));
		uploads.setBounds(620, 14, 124, 36);
		contentpl.add(uploads);
		uploads.addMouseListener(new UploadMouseListener(this));
		// 滚动条
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(47, 64);
		scrollPane.setSize(703, 232);
		scrollPane.setBorder(null);
		contentpl.add(scrollPane);
		// 列表容器
		JPanel text = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/bgindex.png");
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
				Teacher t = sr.getTeacher();
				JLabel jl = null;
				SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" ); 
				String str = sdf.format(sr.getUploadtime());
				jl = new JLabel(
						"    题目：" + sr.getOldfile() + "   上传者：" + t.getTname() + "    时间：" + str);
				jl.setFont(new Font("宋体", Font.BOLD, 14));
				jl.setForeground(Color.BLACK);
				jl.setPreferredSize(new Dimension(700, 45));
				jl.setHorizontalAlignment(SwingConstants.LEFT);
				jl.addMouseListener(new ResourceMouseListener(this, sr));
				text.add(jl);
			}
		}
		this.setVisible(true);
	}

	/**
	 * <p>
	 * Title: jumpShareResource
	 * </p>
	 * <p>
	 * Description: 跳转到教材管理的页面
	 * </p>
	 */
	public void jumpShareResource() {
		// 顶部菜单栏
		topMenu();
		// 左侧菜单栏
		leftMenu();
		// 设置背景图
		setBackground();
		// 设置布局方式为绝对定位
		this.getContentPane().setLayout(null);
		// 时间标志
		setTime();
		// 中间容器
		this.setCenterpl();
		this.setContentpl();
		// 布局设置
		setGroupLayout();
		// 共享资源设置
		setShareResource();
	}

	/**
	 * 
	 * <p>
	 * Title: jumpStuPre
	 * </p>
	 * <p>
	 * Description:跳转到选中学生桌面的内容页
	 * </p>
	 */
	public void jumpStuPre() {
		// 顶部菜单栏
		topMenu();
		// 左侧菜单栏
		leftMenu();
		// 设置背景图
		setBackground();
		// 设置布局方式为绝对定位
		this.getContentPane().setLayout(null);
		// 时间标志
		setTime();
		// 中间容器
		this.setCenterpl();
		this.setContentpl();

		// 布局设置
		setGroupLayout();
	}

	/**
	 * 
	 * <p>
	 * Title: randomCallContent
	 * </p>
	 * <p>
	 * Description:随机点名内容页
	 * </p>
	 */
	public void randomCallContent() {
		//centerpl.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("学号  姓名");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(20, 20, 522, 231);
		lblNewLabel_1.setForeground(Color.WHITE);
		contentpl.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("开始");
		btnNewButton.setFont(new Font("幼圆", Font.PLAIN, 18));
		// 为开始按钮添加事件
		event.setLblNewLabel_1(lblNewLabel_1);
		btnNewButton.addActionListener(event);
		btnNewButton.setBounds(150, 246, 120, 41);
		btnNewButton.setBorder(UIManager.getBorder("Button.border"));
		btnNewButton.setBackground(new Color(230, 230, 250));
		contentpl.add(btnNewButton);

		JButton button = new JButton("停止");
		button.setBackground(new Color(230, 230, 250));
		button.setFont(new Font("幼圆", Font.PLAIN, 18));
		// 为暂停按钮添加事件
		button.addActionListener(event);
		button.setBounds(300, 246, 120, 41);
		contentpl.add(button);

	}

	/**
	 * 
	 * <p>
	 * Title: jumpRandomcall
	 * </p>
	 * <p>
	 * Description:跳转到随机点名界面
	 * </p>
	 */
	public void jumpRandomcall() {
		// 顶部菜单栏
		topMenu();
		// 左侧菜单栏
		leftMenu();
		// 设置背景图
		setBackground();
		// 设置布局方式为绝对定位
		this.getContentPane().setLayout(null);
		// 时间标志
		setTime();
		// 中间容器
		this.setCenterpl();
		this.setContentpl();
		// 聊天窗口
		//chatView();
		// 布局设置
		setGroupLayout();
		// 内容设置，随机点名的内容设置
		randomCallContent();
	}
	
	/**
	 * 
	 * <p>
	 * Title: teachBackContent
	 * </p>
	 * <p>
	 * Description:课堂反馈内容页
	 * </p>
	 */
	public void teachBackContent() {
		//centerpl.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(1, 0, 909, 520);
		contentpl.add(scrollPane_1);

		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image/bgindex.png");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		panel.setBackground(new Color(230, 230, 250));
		scrollPane_1.setViewportView(panel);
		panel.setPreferredSize(new Dimension(900, 2400));
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("姓名");
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(26, 6, 120, 32);
		panel.add(lblNewLabel_3);
		
		JLabel label = new JLabel("问题");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setForeground(Color.WHITE);
		label.setBounds(157, 6, 120, 32);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("image/delete.png"));
		lblNewLabel_1.setBounds(750, 4, 25, 32);
		DeleteMouseListener dm=new DeleteMouseListener(this);
		lblNewLabel_1.addMouseListener(dm);
		panel.add(lblNewLabel_1);
		
		List<FeedBack> list=new FeedBackServiceImpl().ListshowTxtByStatus();
		int i=0;
		int b=41;
		while (i<list.size()) {
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("  "+list.get(i).getStudent().getName()+"     "+list.get(i).getTxt());
		chckbxNewCheckBox_1.setFont(new Font("宋体", Font.PLAIN, 16));
//		chckbxNewCheckBox_1.setForeground(SystemColor.textHighlight);
		chckbxNewCheckBox_1.setBounds(0, b, 881, 51);
		lblNewLabel_1.setPreferredSize(new Dimension(881, 51));
		chckbxNewCheckBox_1.setBorderPainted(true);
		panel.add(chckbxNewCheckBox_1);
		 if (chckbxNewCheckBox_1.isSelected()) {
			  chckbxNewCheckBox_1.setBackground(new Color(138,177,229));
	      } else{
	    	  chckbxNewCheckBox_1.setBackground(new Color(152,188,202));
	      }
		 //设置监听
		 CheckBoxMouseListener cb=new CheckBoxMouseListener(this,list.get(i));
		 chckbxNewCheckBox_1.addMouseListener(cb);

		 
		 i++;
		 b=b+45;
		}

		this.setVisible(true);
	}
	/**
	 * 
	 * <p>
	 * Title: jumpTeachBack
	 * </p>
	 * <p>
	 * Description:跳转到课堂反馈界面
	 * </p>
	 */
	public void jumpTeachBack() {
		// 顶部菜单栏
		topMenu();
		// 左侧菜单栏
		leftMenu();
		// 设置背景图
		setBackground();
		// 设置布局方式为绝对定位
		this.getContentPane().setLayout(null);
		// 时间标志
		setTime();
		// 中间容器
		this.setCenterpl();
		this.setContentpl();
		// 布局设置
		setGroupLayout();
		// 设置课堂反馈
		teachBackContent();
	}
	

	
	
	
	public Index getIndex() {
		return this;
	}
	
	/**
	 * <p>Title: setListener</p>
	 * <p>Description: 设置监听</p>
	 */
	public void setListener() {
		// 窗口最小化时软件dispose
				this.addWindowListener(new WindowAdapter() {

					// 关闭窗口时，更改数据的teacherstatus状态
					public void windowClosing(WindowEvent e) {
						super.windowClosing(e);
						// 更改状态
						ServiceOperationServiceImpl sos = new ServiceOperationServiceImpl();
						sos.updateStatus(0);
					}
				});
	}

	/**
	 * Create the frame.
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
		// 为页面提供服务的对象
		service = new Service(this);
		// 设置关闭状态
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 事件监听的对象
		event = new IndexActionListener(this);	
		this.setVisible(true);
	}

}
