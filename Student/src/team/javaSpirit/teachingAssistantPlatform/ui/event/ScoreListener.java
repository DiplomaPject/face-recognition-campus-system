package team.javaSpirit.teachingAssistantPlatform.ui.event;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import team.javaSpirit.teachingAssistantPlatform.ui.view.Index;

public class ScoreListener implements MouseListener {
	private Index index;
	public ScoreListener(Index index) {
		this.index=index;
	}
	//点击成绩查询时调用此业务逻辑
	@Override
	public void mouseClicked(MouseEvent e) {
		this.index.jumpScoreRecord();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	//鼠标进入菜单时改变鼠标形状
	@Override
	public void mouseEntered(MouseEvent e) {
		this.index.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	//鼠标离开菜单时恢复鼠标形状
	@Override
	public void mouseExited(MouseEvent e) {
		this.index.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
