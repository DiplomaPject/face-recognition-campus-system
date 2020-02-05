package team.javaSpirit.teachingAssistantPlatform.record.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import team.javaSpirit.teachingAssistantPlatform.entity.Record;
import team.javaSpirit.teachingAssistantPlatform.entity.Score;
import team.javaSpirit.teachingAssistantPlatform.util.HibernateUtil;

public class RecordDao {
	/**
	 * <p>
	 * Title: allRecordBysid
	 * </p>
	 * <p>
	 * Description: 通过学号，查询本学生所有签到记录。
	 * </p>
	 * 
	 * @param sid 学号
	 * @return 所有的签到记录
	 */
	public List<Record> allRecordBysid(String sid) {
		Session session = HibernateUtil.getSession();
		Query q = session.createQuery("from Record where student.sid=?");
		q.setParameter(0, sid);
		List<Record> list = q.list();
		session.close();
		return list;
	}
	
	/**
	 * <p>
	 * Title: allScoreBysid
	 * </p>
	 * <p>
	 * Description: 通过学号，查询本学生所有考试成绩。
	 * </p>
	 * 
	 * @param sid 学号
	 * @return 所有的考试成绩
	 */
	public List<Score> allScoreBysid(String sid) {
		Session session = HibernateUtil.getSession();
		Query q = session.createQuery("from Score where student.sid=?");
		q.setParameter(0, sid);
		List<Score> list = q.list();
		session.close();
		return list;
	}
	
	/**
	 * <p>
	 * Title: allScoreByscid
	 * </p>
	 * <p>
	 * Description: 通过学号和课程号，查询本学生所有考试成绩。
	 * </p>
	 * 
	 * @param sid,couse_id 学号,课程号
	 * @return 所有的考试成绩
	 */
	public Score getScoreByscid(String sid, int course_id) {
		Session session = HibernateUtil.getSession();
		Query q = session.createQuery("from Score where student.sid=? and course.course_id=?");
		q.setParameter(0, sid);
		q.setParameter(1, course_id);
		Score score = (Score) q.uniqueResult();
		session.close();
		return score;
	}
}
