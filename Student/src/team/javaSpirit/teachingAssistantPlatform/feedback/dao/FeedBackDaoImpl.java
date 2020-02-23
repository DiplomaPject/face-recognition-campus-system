package team.javaSpirit.teachingAssistantPlatform.feedback.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import team.javaSpirit.teachingAssistantPlatform.entity.FeedBack;
import team.javaSpirit.teachingAssistantPlatform.entity.Students;
import team.javaSpirit.teachingAssistantPlatform.entity.Teacher;
import team.javaSpirit.teachingAssistantPlatform.util.HibernateUtil;

public class FeedBackDaoImpl {

	public void setTxtById(Students sid, Teacher t, String txt) {
		Session session = HibernateUtil.getSession();
		FeedBack fb = new FeedBack();
		Transaction tx = session.beginTransaction();
		fb.setStudent(sid);
		fb.setTeacher(t);
		fb.setTxt(txt);
		fb.setStatus(0);
		fb.setFeedtime(new Date());
		session.save(fb);
		tx.commit();
		session.close();
	}
	
	public List<List> showTxtByStatus(int status) {
		Session session = HibernateUtil.getSession();
		Query q = session.createQuery("select * from feedback where status=?");
		q.setParameter(0, status);
		List<List> list = q.list();
		FeedBack feedback = new FeedBack();
		feedback.setId(Integer.parseInt(list.get(0).get(0).toString()));
		session.close();
		return list;
	}

	public void changeStatus(FeedBack fb) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		fb.setStatus(1);
		session.save(fb);
		tx.commit();
		session.close();
	}
}
