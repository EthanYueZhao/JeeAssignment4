package college.courses.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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


	public CatalogManager() {
		super();
	}

	// replace with professor manager in assignment 4
	// private void addProfessor(Professor professor) {
	// if (professor == null) {
	// return;
	// }
	// for (Professor p : professors) {
	// if (p.equals(professor)) {
	// return;
	// }
	// }
	// professors.add(professor);
	// }


	public List<Professor> getProfessorList() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Professor> tq = em.createNamedQuery("Professor.findAll",
				Professor.class);
		ArrayList<Professor> professors = (ArrayList<Professor>) tq
				.getResultList();
		return professors;
	}

	// note: not in the CourseCatalog interface client code needs a
	// variable of type CatalogManager not Course Catalog.
	// not required but optional for assignment 4
//	public Collection<Course> getAllCourses() {
//		return courses.values();
//	}

	@Override
	public Course getCourse(String courseCode) throws CourseNotFoundException {
		if (courseCode == null) {
			throw new CourseNotFoundException("No course with null courseCode");
		}

		EntityManager em = emf.createEntityManager();
		Course c = em.find(Course.class, courseCode);
		em.close();

		return c;
	}

	@Override
	public Course addCourse(Course c) throws DuplicateCourseException {
		if (c == null) {
			throw new DuplicateCourseException("Cannot add a null Course");
		}

		EntityManager em = emf.createEntityManager();

		Course course = em.find(Course.class, c.getCourseCode());
		if (course != null) {
			throw new DuplicateCourseException("Duplicate course code "
					+ c.getCourseCode());
		} else {
			course = new Course();
			course.setCourseCode(c.getCourseCode());
			course.setCourseTitle(c.getCourseTitle());
			if(c.getProfessor()!=null){
				course.setProfessor(em.find(Professor.class, c.getProfessor()
						.getProfid()));
			}

			em.getTransaction().begin();
			em.persist(course);
			em.getTransaction().commit();
			em.close();

		}

		return c;
	}

	@Override
	public Course updateCourse(Course c) throws CourseNotFoundException {
		if (c == null) {
			throw new CourseNotFoundException("Cannot update a null Course");
		}

		EntityManager em = emf.createEntityManager();
		Course course = em.find(Course.class, c.getCourseCode());
		if (course == null) {
			throw new CourseNotFoundException("No Course with id "
					+ c.getCourseCode());
		}
		if (course.equals(c)) {
			return c;
		}

		try {
			EntityTransaction et = em.getTransaction();
			et.begin();

			course.setCourseTitle(c.getCourseTitle());
			if(c.getProfessor()==null){
				course.setProfessor(c.getProfessor());				
			}else{
				course.setProfessor(em.find(Professor.class, c.getProfessor()
						.getProfid()));				
			}
			em.merge(course);

			et.commit();
			em.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("cm.updateCourse()");
		}

		return c;
	}

	@Override
	public Course deleteCourse(String courseCode)
			throws CourseNotFoundException {
		if (courseCode == null) {
			throw new CourseNotFoundException("No course with null courseCode");
		}

		EntityManager em = emf.createEntityManager();

		Course c = em.find(Course.class, courseCode);
		
		if (!em.contains(c)) {
			throw new CourseNotFoundException("No course with courseCode "
					+ courseCode);
		}
		try {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(c);
			et.commit();
			em.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}


	public int countCourses() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Course> tq = em.createNamedQuery("Course.findAll",
				Course.class);
		return tq.getResultList().size();
	}

}
