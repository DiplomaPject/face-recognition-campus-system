package team.javaSpirit.teachingAssistantPlatform.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <p>
 * Title:课程成绩
 * </p>
 * <p>
 * content:成绩类对应score表
 * </p>
 * 
 */
@Entity
@Table(name = "score")
public class Score {
	private int id;
	private double score;
	private Students student;
	private Course course;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	@ManyToOne
	@JoinColumn(name = "sid")
	public Students getStudent() {
		return student;
	}
	public void setStudent(Students student) {
		this.student = student;
	}
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
