package college.courses.data;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@Table(name="COURSE", schema="COLLEGE")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COURSECODE", nullable = false)
	private String coursecode;

	private int capacity;

	@Column(name = "COURSETITLE", nullable = false)
	private String coursetitle;

	private int enrolled;

	//bi-directional many-to-one association to Professor
	@ManyToOne
	@JoinColumn(name="PROFID", referencedColumnName="PROFID")
	private Professor professor;

	public Course() {
	}

	public Course(String string, String string2, Professor professor2) {
		// TODO Auto-generated constructor stub
	}

	public Course(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	public String getCoursecode() {
		return this.coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getCoursetitle() {
		return this.coursetitle;
	}

	public void setCoursetitle(String coursetitle) {
		this.coursetitle = coursetitle;
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