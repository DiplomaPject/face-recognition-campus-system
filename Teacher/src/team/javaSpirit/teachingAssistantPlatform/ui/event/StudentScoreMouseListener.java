package team.javaSpirit.teachingAssistantPlatform.ui.event;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import team.javaSpirit.teachingAssistantPlatform.ui.view.Index;

public class StudentScoreMouseListener implements MouseListener {
	/* 监听页面的对象 */
	private Index index;

	public StudentScoreMouseListener(Index index) {
		this.index = index;
	}

	/**
	 * 点击实现页面的跳转
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// 跳转到成绩录入界面
		this.index.jumpScore();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.index.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.index.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
