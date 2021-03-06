package team.javaSpirit.teachingAssistantPlatform.feedback.services;

import java.util.ArrayList;
import java.util.List;

import team.javaSpirit.teachingAssistantPlatform.common.Constant;
import team.javaSpirit.teachingAssistantPlatform.entity.FeedBack;
import team.javaSpirit.teachingAssistantPlatform.entity.Students;
import team.javaSpirit.teachingAssistantPlatform.entity.Teacher;
import team.javaSpirit.teachingAssistantPlatform.feedback.dao.FeedBackDaoImpl;
import team.javaSpirit.teachingAssistantPlatform.signIn.dao.StudentCourseDao;

public class FeedBackServicesImpl {
	private static FeedBackDaoImpl fbDaoImpl = new FeedBackDaoImpl();

	public static void setTxt(String txt, Teacher teacher) {
		StudentCourseDao s = new StudentCourseDao();
		Students stu = s.getStudentById(Constant.myStudent.getSid());
		fbDaoImpl.setTxtById(stu, teacher, txt);
	}
}
