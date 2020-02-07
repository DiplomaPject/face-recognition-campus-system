package team.javaSpirit.teachingAssistantPlatform.oneToOneControl.service;

import team.javaSpirit.teachingAssistantPlatform.oneToOneControl.dao.StudentsDaoImpl;

public class StudentServiceImpl {

	
	/**
	 * <p>Title: findName</p>
	 * <p>Description: 通过学生的ip，找到学生的名字</p>
	 * @param ip 学生的ip
	 * @return 学生的名字
	 */
	public String findName(String ip) {
		StudentsDaoImpl studentsDaoImpl = new StudentsDaoImpl();
		String name = studentsDaoImpl.getNameByIp(ip);
		return name;
	}
}
