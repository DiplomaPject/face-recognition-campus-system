package team.javaSpirit.teachingAssistantPlatform.ui.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import team.javaSpirit.teachingAssistantPlatform.studentScore.service.StudentScoreService;
import team.javaSpirit.teachingAssistantPlatform.ui.view.Index;
import team.javaSpirit.teachingAssistantPlatform.ui.view.Login;
import team.javaSpirit.teachingAssistantPlatform.ui.view.LoginIng;
import team.javaSpirit.teachingAssistantPlatform.ui.view.Score;

public class StudentScoreListener implements ActionListener {
	private Score score;
	private static boolean isCancel = false;
	
	/**
	 * 当监听事件被触发调用该业务逻辑
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if ("确 认".equals(s)) {
			this.setConfirmButton();
		} 
	}

	public StudentScoreListener(Score score) {
		this.score = score;
	}

	public StudentScoreListener() {
	}
	
	/**
	 * 
	 * <p>
	 * Title: setConfirmButton
	 * </p>
	 * <p>
	 * Description: 点击确认按钮掉用该业务逻辑
	 * </p>
	 */
	public void setConfirmButton() {
		String getScore = this.score.getScore().getText();
		if (getScore == null || getScore.equals("")) {
			JOptionPane.showMessageDialog(null, "成绩不能为空", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
		}else {
			StudentScoreService ss = new StudentScoreService();
			String sid = Index.sid;
			int cid = Index.cid;
			Double s = new Double(getScore);
			ss.changeScore(sid,cid, s);
			score.dispose();
			javax.swing.JOptionPane.showMessageDialog(null, getScore+"已修改");
			Index.courseList = ss.courseStudent(cid);
			Index.mode.fireTableDataChanged();
		}
		
	}
	
	
}
