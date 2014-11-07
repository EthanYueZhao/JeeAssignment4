package college.courses.data;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COURSECODE")
	private String courseCode;
	@Column(name="CAPACITY")
	private int capacity;
	@Column(name="COURSETITLE")
	private String courseTitle;
	@Column(name="ENROLLED")
	private int enrolled;

	//bi-directional many-to-one association to Professor
	@ManyToOne
	@JoinColumn(name="PROFID")
	private Professor professor;

	public Course() {
	}

	public Course(String string, String string2, Professor professor2) {
		// TODO Auto-generated constructor stub
	}

	public Course(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}


	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}



	public int getEnrolled() {
		return this.enrolled;
	}

	public void setEnrolled(int enrolled) {
		this.enrolled = enrolled;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}