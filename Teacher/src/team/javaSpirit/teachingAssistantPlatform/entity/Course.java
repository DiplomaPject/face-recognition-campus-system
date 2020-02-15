package team.javaSpirit.teachingAssistantPlatform.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * Title:课程
 * </p>
 * <p>
 * content:课程类对应course表
 * </p>
 * 
 */
/*
 * 课程表
 */
@Entity
@Table(name = "course")
public class Course {
	private int course_id;// 课程号
	private String cname;// 课程名
	private double credit;//学分
	private int period;//学时
	private String major;//是否主修
	private String nature;//课程性质
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}
	
	
}
