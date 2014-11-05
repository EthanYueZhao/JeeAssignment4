package college.courses.data;

import java.io.Serializable;

import college.courses.exceptions.InvalidDataException;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	private String courseCode;
	private String courseTitle;
	private Professor professor;

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) throws InvalidDataException {
		if ( courseCode == null || courseCode.isEmpty() ) {
			throw new InvalidDataException("Course must have a course code");
		}
		this.courseCode = courseCode;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		// assume a course can have an empty or null title
		this.courseTitle = courseTitle;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		// professor can be null when professor not assigned
		this.professor = professor;
	}

	public Course( String courseCode) throws InvalidDataException {
		super();
		setCourseCode(courseCode);
	}
	
	public Course(String courseCode, String courseTitle) throws InvalidDataException  {
		this( courseCode );
		setCourseTitle(courseTitle);
	}
	
	public Course(String code, String title, Professor professor) throws InvalidDataException   {
		this(code,title);
		this.professor = professor;
}
	
	public String toString() {
		String output = getCourseCode() + ": [" + getCourseTitle() + "]";
		if (getProfessor() != null ) {
			output += " Professor " + getProfessor();
		}
		return output;
	}
	
	public boolean equals(Course c) {
		if ( ! this.getCourseCode().equals(c.getCourseCode()) ) {
			return false;
		}
		if ( ! this.getCourseTitle().equals(c.getCourseTitle()) ) {
			return false;
		}
		if ( this.getProfessor() == null ) {
			return c.getProfessor() == null ;
		} 
		if ( ! this.getProfessor().equals(c.getProfessor()) ) {
			return false;
		}
		return true;
	}


}
