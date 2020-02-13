package team.javaSpirit.teachingAssistantPlatform.studentScore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import team.javaSpirit.teachingAssistantPlatform.common.Constant;
import team.javaSpirit.teachingAssistantPlatform.entity.Students;
import team.javaSpirit.teachingAssistantPlatform.ui.view.Score;
import team.javaSpirit.teachingAssistantPlatform.util.HibernateUtil;

public class StudentScoreDao {
	/**
	 * <p>
	 * Title: searchStudent
	 * </p>
	 * <p>
	 * Description: 通过课程id，查看选课学生。
	 * </p>
	 * 
	 * @return
	 */
	public List<Object[]> searchStudent() {
		Session session = HibernateUtil.getSession();
		String sql = "select s.sid,s.name,s2.classin.class_name,s1.score from Students s, Score s1, StudentClass s2 "
				+ "where s2.classin.class_id=? and s.sid=s2.student.sid and s1.student.sid=s.sid and s1.course.course_id=?";
		Query q = session.createQuery(sql);
		q.setParameter(0, Constant.cid);
		q.setParameter(1, Constant.cid);
		List<Object[]> list = q.list();
		session.close();
		return list;
	}
	
	/**
	 * 
	 * <p>
	 * Title: setStuScore
	 * </p>
	 * <p>
	 * Description: 修改学生该科成绩
	 * </p>
	 */
	public void setStuScore(String sid,  double s) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		String sql = "update score set score=? where sid=? and course_id=?";
		Query q = session.createSQLQuery(sql);
		q.setParameter(0, s);
		q.setParameter(1, sid);
		q.setParameter(2, Constant.cid);
		q.executeUpdate();
		tx.commit();
		session.close();
	}
}
