package college.courses.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the professor database table.
 * 
 */
@Entity
@Table(name="PROFESSOR", schema="COLLEGE")
@NamedQuery(name="Professor.findAll", query="SELECT p FROM Professor p")
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int profid;
	@Column(name = "FAMILYNAME", nullable = false)
	private String lastname;
	@Column(name = "GIVENNAME", nullable = false)
	private String firstname;

	//bi-directional many-to-one association to Course
	@OneToMany(mappedBy="professor")
	private List<Course> courses;

	public Professor() {
	}

	public Professor(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	public int getProfid() {
		return this.profid;
	}

	public void setProfid(int profid) {
		this.profid = profid;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course addCours(Course cours) {
		getCourses().add(cours);
		cours.setProfessor(this);

		return cours;
	}

	public Course removeCours(Course cours) {
		getCourses().remove(cours);
		cours.setProfessor(null);

		return cours;
	}

}