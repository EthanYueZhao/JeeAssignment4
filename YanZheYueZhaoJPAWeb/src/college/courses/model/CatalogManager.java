package college.courses.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import college.courses.data.Course;
import college.courses.data.Professor;
import college.courses.exceptions.CourseNotFoundException;
import college.courses.exceptions.DuplicateCourseException;

public class CatalogManager implements CourseCatalog {

	// delete for assignment 4. Class does not need to be a singleton
	private static CatalogManager cm = null;
	
	// delete for assignment 4: replaced by COURSE table
	private Map<String, Course> courses = null;
	
	// delete for assignment 4: replaced by PROFESSOR table
	private List<Professor> professors = null;
	
	// delete for assignment 4: class does not need to be a singleton
	public synchronized static CatalogManager getInstance() {
		if (cm == null) {
			cm = new CatalogManager();
			cm.initialize();
		}
		return cm;
	}

	// replace with public default constructor
	private CatalogManager() {
		if (courses == null) {
			courses = new ConcurrentHashMap<String, Course>();
			professors = new ArrayList<Professor>();
		}
	}
	
	// delete for assignment 4: data base is ready to use
	private void initialize() {
		System.out.println("Building course catalog");
		try {
			// replace up to catch to add courses you are taking this term
			Professor professor = new Professor("Paula", "McMillan");
			cm.addCourse(new Course("COMP303", "Java EE Programming", professor));
			cm.addCourse(new Course("COMP311", "Software Testing and QA"));
			professor = new Professor("Peter", "Voldner");
			cm.addCourse(new Course("SWS310",
					"Software Standards, Testing and Maintenance", professor));
			professor = new Professor("Michael", "Marovich");
			cm.addCourse(new Course("SWS311", "Programming Network Systems" , professor)) ;
			cm.addCourse(new Course("SWS312", "Database Programming"));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println();
		System.out.println("Testing get all courses");
		try {
			System.out.println("Course catalog:");
			Collection<Course> courses = ((CatalogManager) cm).getAllCourses();
			for (Course course : courses) {
				System.out.println(course);
			}
			System.out.println("Lsting all professors" );
			for (Professor p : cm.getProfessorList() ) {
				System.out.println(p);			
			}
			System.out.println("done");
			
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
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
		String code = c.getCourseCode();
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
		Course oldC = getCourse(c.getCourseCode());
		if (c.equals(oldC)) {
			// no change - nothing to do
			return c;
		}
		// insert changed course
		if ( oldC.getProfessor() != c.getProfessor() ) {
			addProfessor(c.getProfessor() );
		}
		courses.put(c.getCourseCode(), c);
		// retrieve again to get derived fields, if there are any
		return getCourse(c.getCourseCode());
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
