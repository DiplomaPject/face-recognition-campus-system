package team.javaSpirit.teachingAssistantPlatform.login.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import team.javaSpirit.teachingAssistantPlatform.common.Constant;
import team.javaSpirit.teachingAssistantPlatform.entity.LoadStudent;
import team.javaSpirit.teachingAssistantPlatform.entity.Students;
import team.javaSpirit.teachingAssistantPlatform.entity.Studentstatus;
import team.javaSpirit.teachingAssistantPlatform.facecheck.JcvTest;
import team.javaSpirit.teachingAssistantPlatform.login.dao.LoadStudentDaoImpl;
import team.javaSpirit.teachingAssistantPlatform.signIn.service.StudentCourseService;
import team.javaSpirit.teachingAssistantPlatform.util.IpUtil;

/**
 * <p>
 * Title: LoadStudentService
 * </p>
 * <p>
 * Description: Students数据库逻辑代码实现
 * </p>
 * 
 */
public class LoadStudentServiceImpl {

	/* 跟数据库交互的对象 */
	private LoadStudentDaoImpl loadStudentDaoImpl;

	public LoadStudentServiceImpl() {
		loadStudentDaoImpl = new LoadStudentDaoImpl();
	}

	/**
	 * 
	 * <p>
	 * Title: checkLoginStudent
	 * </p>
	 * <p>
	 * Description: 登陆验证学生学号，密码，状态是否正确
	 * </p>
	 * 
	 * @param sid
	 * @param password
	 * @return 用户不存在返回1，状态不是1返回2，密码不正确返回3，信息正确返回4
	 */
	public int checkLoginStudent(String sid, String password) {
		Students s = this.loadStudentDaoImpl.getStudentById(sid);
		if (s == null) {
			return 1;
		} else {
			if (s.getState() != 1) {
				return 2;
			} else if (s.getPassword().equals(password)) {
				Studentstatus ss=this.loadStudentDaoImpl.checkStatus(s.getSid());
				if(ss.getRecord_status()==0) {
					//String ip = IpUtil.getRealIP();
					String ip = "192.168.43.59";
					Students st = this.loadStudentDaoImpl.updateStudentIp(s, ip);
					Constant.myStudent = st;
					LoadStudent ls = new LoadStudent();
					ls.setLogin_time(new Date());
					ls.setStudent(st);
					loadStudentDaoImpl.saveLoadStudent(ls);
					this.loadStudentDaoImpl.updateStatus(s.getSid());
					return 4;
				}
				else
					return 5;
			} else {
				return 3;
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Title: checkModifyStudent
	 * </p>
	 * <p>
	 * Description: 检验学生修改密码是否正确
	 * </p>
	 * 
	 * @param sid
	 * @param pwd
	 * @param conpwd
	 * @return 用户不存在返回1，用户旧密码不匹配返回2，密码不匹配返回3，信息无误返回4
	 */
	public int checkModifyStudent(String sid, String oldpwd, String pwd, String conpwd) {
		Students s = this.loadStudentDaoImpl.getStudentById(sid);
		if (s == null)
			return 1;
		else if (!oldpwd.equals(s.getPassword()))
			return 2;
		else if (!pwd.equals(conpwd))
			return 3;
		else {
			this.loadStudentDaoImpl.updateStudentPassword(s, pwd);
			return 4;
		}
	}
	
	/**
	 * 
	 * <p>
	 * Title: checkStudent
	 * </p>
	 * <p>
	 * Description: 检验学生修改密码是否正确
	 * </p>
	 * 
	 * @return 识别失败返回1，识别成功返回2
	 */
	public int checkStudent() {
		List<String> sids = this.loadStudentDaoImpl.allStudent();
		StudentCourseService scs = new StudentCourseService();
		try {
			//人脸识别
			Constant.imgsrc = JcvTest.captureFaceBySid(sids);
			String strs = Constant.imgsrc;
			String src = "";
			if (strs != null || strs.length() != 0) {
				String[] str1 = strs.split("-");
				String[] idstr = str1[3].split("\\.");
				if (idstr != null || idstr.length != 0) {
					src = idstr[0];
				}else {
					src = "失败";
				}
				System.out.println("sid:"+src);
				Students s = this.loadStudentDaoImpl.getStudentById(src);
				Constant.myStudent = s;
			}
			
			if (Constant.imgsrc != null) {
				JOptionPane.showMessageDialog(null,"您与"+src+"成功匹配");
				return 2;
			}else if (Constant.imgsrc == null) {
				JOptionPane.showMessageDialog(null,"未能成功匹配，请再次尝试或使用账号密码登录");
			}else {
				JOptionPane.showMessageDialog(null,"error");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * 
	 * <p>
	 * Title: checkLoginStudentByFace
	 * </p>
	 * <p>
	 * Description: 登陆验证学生学号，密码，状态是否正确
	 * </p>
	 * 
	 * @param sid
	 * @return 不在学校返回2，信息正确返回4, 登录失败返回5
	 */
	public int checkLoginStudentByFace(String sid) {
		Students s = this.loadStudentDaoImpl.getStudentById(sid);
		Studentstatus ss=this.loadStudentDaoImpl.checkStatus(s.getSid());
		if (s.getState() != 1) {
			return 2;
		} else if(ss.getRecord_status()==0) {
			//String ip = IpUtil.getRealIP();
			String ip = "192.168.43.59";
			Students st = this.loadStudentDaoImpl.updateStudentIp(s, ip);
			Constant.myStudent = st;
			LoadStudent ls = new LoadStudent();
			ls.setLogin_time(new Date());
			ls.setStudent(st);
			loadStudentDaoImpl.saveLoadStudent(ls);
			this.loadStudentDaoImpl.updateStatus(s.getSid());
			return 4;
		}else
			return 5;
	}

}
