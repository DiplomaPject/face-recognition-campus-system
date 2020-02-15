package team.javaSpirit.teachingAssistantPlatform.ui.event;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingWorker;

import team.javaSpirit.teachingAssistantPlatform.common.Constant;
import team.javaSpirit.teachingAssistantPlatform.login.service.LoadStudentServiceImpl;
import team.javaSpirit.teachingAssistantPlatform.ui.view.Index;
import team.javaSpirit.teachingAssistantPlatform.ui.view.Login;
import team.javaSpirit.teachingAssistantPlatform.ui.view.LoginIng;
import team.javaSpirit.teachingAssistantPlatform.ui.view.Modify;
import team.javaSpirit.teachingAssistantPlatform.util.DlPropertiesUtil;

/**
 * 
 * <p>
 * Title: loginByFaceListener
 * </p>
 * <p>
 * Description:人脸识别登录
 * </p>
 * 
 */
public class LoginByFaceListener implements ActionListener, MouseListener{
	private Login login;
	private static LoginIng loginIng;
	private static boolean isCancel=false;
	private LoadStudentServiceImpl studentServiceImpl = new LoadStudentServiceImpl();
	
	public LoginByFaceListener(Login login) {
		this.login=login;
	}
	/**
	 * 当触发事件时自动调用此方法实现业务逻辑
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String str=e.getActionCommand();
		if("取消".equals(str)) {
			JButton jb=(JButton)e.getSource();
			this.setCancelButton(jb);
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int ret = studentServiceImpl.checkStudent();
		//成功返回2
		if (ret == 2) {
            this.login.dispose();
            this.loginIng=new LoginIng();
            this.loginIng.init();
          //开启一个新线程取数据库查询验证用户信息
            new SwingWorker<String, String>() {
           	 /**
           	  * 在数据中查询验证用户信息并给出相应提示
           	  */
				@Override
				protected String doInBackground() throws Exception {
					LoadStudentServiceImpl studentsServiceImpl=new LoadStudentServiceImpl();
			        int i=studentsServiceImpl.checkLoginStudentByFace(Constant.myStudent.getSid());
			        if(i==4) {
			        	//登录成功
			        	return "1";
			        }
			        if(i==5) {
			        	if(isCancel==false) 
			        		JOptionPane.showMessageDialog(null,"该用户已经登录","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
		             }
			         return "2";
				}
				/**
				 * 从数据中查询完信息后操作窗体
				 */
				protected void done() {
					try {
						String s=get();
						if(isCancel==false) {
							loginIng.dispose();
							if("1".equals(s)) {
								Index index=new Index();
								index.init();
							}else {
								Login login=new Login();
								login.init();
							}
						}
						isCancel=false;
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
			    }
             }.execute();
		}
	}
	
	/**
	 * 
	 * <p>Title: setCancelButton</p>
	 * <p>Description:登录中窗体取消按钮点击后触发次业务逻辑 </p>
	 * @param jb
	 */
	public void setCancelButton(JButton jb) {
		this.isCancel=true;
		if(this.loginIng!=null) {
			this.loginIng.dispose();
		}
		Login login=new Login();
		login.init();
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
