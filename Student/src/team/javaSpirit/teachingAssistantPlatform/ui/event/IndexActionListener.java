package team.javaSpirit.teachingAssistantPlatform.ui.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import team.javaSpirit.teachingAssistantPlatform.common.Constant;
import team.javaSpirit.teachingAssistantPlatform.entity.Teacher;
import team.javaSpirit.teachingAssistantPlatform.feedback.services.FeedBackServicesImpl;
import team.javaSpirit.teachingAssistantPlatform.remoteMonitoring.service.TeacherClassServiceImpl;
import team.javaSpirit.teachingAssistantPlatform.signIn.service.StudentCourseService;
import team.javaSpirit.teachingAssistantPlatform.ui.view.Index;


/**
 * <p>
 * Title: IndexActionListener
 * </p>
 * <p>
 * Description: 为学生端主页面index添加事件监听。
 * </p>
 * 
 */
public class IndexActionListener implements ActionListener {

	private Index index;
	private JTextArea text;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if  ("×".equals(s)) {
			this.setCloseButton();
		} else if("确认提交".equals(s)) {
			this.setSend();
		}
	}

	public IndexActionListener(Index index) {
		this.index = index;
	}
	public IndexActionListener(Index index,JTextArea text) {
		this.index=index;this.text=text;
	}
	
	// 签到的逻辑判断
	public void setSignButton() {
		StudentCourseService scs = new StudentCourseService();
		// 找到当前课程
		if (scs.findCurrentCourse(Constant.myStudent.getSid())
				&& scs.getStudentStatus(Constant.myStudent.getSid()) == 0) {
			try {
				// 人脸识别
				scs.firstFace();
				// 修改数据库
				scs.changeState(Constant.myStudent.getSid());
				scs.insertRecort(Constant.myStudent.getSid());
				if (StudentCourseService.status == 2) {
					JOptionPane.showMessageDialog(null, "啊哦，你迟到了");
				} else {
					JOptionPane.showMessageDialog(null, "您已签到成功");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (scs.getStudentStatus(Constant.myStudent.getSid()) != 0) {
			JOptionPane.showMessageDialog(null, "您已签到");
		} else {
			JOptionPane.showMessageDialog(null, "当前没有可以签到的课程");
		}
	}

	// ×的逻辑
	public void setCloseButton() {
		this.index.init();
	}
	
	//发送课堂反馈的逻辑
	public void setSend() {
		FeedBackServicesImpl.setTxt(this.text.getText());		
		JOptionPane.showMessageDialog(null, "您反馈的问题已收到！！！");
	}
}
