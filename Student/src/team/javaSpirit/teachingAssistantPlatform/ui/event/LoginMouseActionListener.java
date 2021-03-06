package team.javaSpirit.teachingAssistantPlatform.ui.event;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import team.javaSpirit.teachingAssistantPlatform.ui.view.Login;
import team.javaSpirit.teachingAssistantPlatform.ui.view.Modify;

/**
 * 
 * <p>
 * Title: LoginMouseActionListener
 * </p>
 * <p>
 * Description:为登录界面添加鼠标监听事件
 * </p>
 * 
 */
public class LoginMouseActionListener implements MouseListener {
	private Login login;
	public LoginMouseActionListener(Login login) {
		this.login=login;
	}
	/**
	 * 
	 * <p>
	 * Title: mouseClicked
	 * </p>
	 * <p>
	 * Description:点击修改密码时触发此业务逻辑
	 * </p>
	 * 
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		this.login.dispose();
		Modify modify = new Modify();
		modify.init();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.login.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

}
