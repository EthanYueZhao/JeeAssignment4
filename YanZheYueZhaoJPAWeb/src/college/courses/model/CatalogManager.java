package college.courses.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

import college.courses.data.Course;
import college.courses.data.Professor;
import college.courses.exceptions.CourseNotFoundException;
import college.courses.exceptions.DuplicateCourseException;

public class CatalogManager implements CourseCatalog {

	private static EntityManagerFactory emf;
	static {
		try {
			InitialContext ctx = new InitialContext();
			emf = (EntityManagerFactory) ctx.lookup("java:/CollegeEMF");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	//public default constructor
	public CatalogManager() {
	}
	
	// replace with professor manager in assignment 4
	private void addProfessor(Professor professor) { 
		if ( professor == null) {
			return;
		}
		for ( Professor p : professors ) {
			if (p.equals(professor)) {
				return;
			}
		}
		professors.add(professor);
	}
	
	// replace with professor manager in assignment 4
	public List<Professor> getProfessorList() { 
		return professors;
	}
	
	// note: not in the CourseCatalog interface client code needs a 
	//        variable of type CatalogManager not Course Catalog.
	// not required but optional for assignment 4
	public Collection<Course> getAllCourses() {
		return courses.values();
	}
	
// Rewrite to use JPA API for assignment 4
	@Override
	public Course getCourse(String courseCode) throws CourseNotFoundException {
		if (courseCode == null) {
			throw new CourseNotFoundException("No course with null courseCode");
		}
		if (courses.containsKey(courseCode)) {
			return courses.get(courseCode);
		} else {
			throw new CourseNotFoundException("No course with code "
					+ courseCode);
		}
	}

	// Rewrite to use JPA API for assignment 4
	@Override
	public Course addCourse(Course c) throws DuplicateCourseException {
		if (c == null) {
			throw new DuplicateCourseException("Cannot add a null Course");
		}
		String code = c.getCoursecode();
		if (courses.containsKey(code)) {
			throw new DuplicateCourseException("Duplicate course code " + code);
		}
		if ( c.getProfessor() != null ) {
			addProfessor(c.getProfessor() );
		}
		courses.put(code, c);
		return c;
	}

	// Rewrite to use JPA API for assignment 4
	@Override
	public Course updateCourse(Course c) throws CourseNotFoundException {
		if (c == null) {
			throw new CourseNotFoundException("Cannot update a null Course");
		}
		Course oldC = getCourse(c.getCoursecode());
		if (c.equals(oldC)) {
			// no change - nothing to do
			return c;
		}
		// insert changed course
		if ( oldC.getProfessor() != c.getProfessor() ) {
			addProfessor(c.getProfessor() );
		}
		courses.put(c.getCoursecode(), c);
		// retrieve again to get derived fields, if there are any
		return getCourse(c.getCoursecode());
	}

	// Rewrite to use JPA API for assignment 4
	@Override
	public Course deleteCourse(String courseCode)
			throws CourseNotFoundException {
		if (courseCode == null) {
			throw new CourseNotFoundException("No course with null courseCode");
		}
		if (courses.containsKey(courseCode)) {
			Course c = courses.get(courseCode);
			courses.remove(courseCode);
			return c;
		} else {
			throw new CourseNotFoundException("No course with code "
					+ courseCode);
		}
	}

	// Rewrite to use JPA API for assignment 4
	public int countCourses() {
		return courses.size();
	}
	
}
