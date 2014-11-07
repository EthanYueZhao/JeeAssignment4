package college.courses.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the professor database table.
 * 
 */
@Entity
@NamedQuery(name="Professor.findAll", query="SELECT p FROM Professor p")
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int profid;
	@Column(name="FAMILYNAME")
	private String lastname;
	@Column(name="GIVENNAME")
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