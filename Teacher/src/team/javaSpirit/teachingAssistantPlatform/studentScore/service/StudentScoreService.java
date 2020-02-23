package team.javaSpirit.teachingAssistantPlatform.studentScore.service;

import java.util.List;

import team.javaSpirit.teachingAssistantPlatform.studentScore.dao.StudentScoreDao;

public class StudentScoreService {
	private StudentScoreDao studentScoreDao = new StudentScoreDao();
	/**
	 * <p>
	 * Title: CourseStudent
	 * </p>
	 * <p>
	 * Description: 返回当前课程所有学生。
	 * </p>
	 * 
	 * @return
	 */
	public List<Object[]> courseStudent(int id) {
		return studentScoreDao.searchStudent(id);
	}
	
	/**
	 * <p>
	 * Title: changeScore
	 * </p>
	 * <p>
	 * Description: 修改学生该科成绩。
	 * </p>
	 * 
	 * @return
	 */
	public void changeScore(String sid, int cid, double s) {
		studentScoreDao.setStuScore(sid,cid,s);
	}
	
}
