package team.javaSpirit.teachingAssistantPlatform.oneToOneControl.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import team.javaSpirit.teachingAssistantPlatform.util.HibernateUtil;

public class StudentsDaoImpl {
	/**
	 * <p>Title: getNameByIp</p>
	 * <p>Description: 获取名字/p>
	 * @param ip ip
	 * @return name 名字
	 */
	public String getNameByIp(String ip) {
		Session session = HibernateUtil.getSession();
		Query q = session.createQuery("select name from Students where ip=?");
		q.setParameter(0, ip);
		String s = (String) q.uniqueResult();
		System.out.println("xxxxxxxxxxx:"+s);
		session.close();
		return s;
	}
}
