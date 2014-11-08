package college.courses.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Calendar;
import java.util.Date;

import college.courses.data.Course;
import college.courses.data.Professor;
import college.courses.exceptions.InvalidDataException;
import college.courses.model.CatalogManager;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/add")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCourseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void updateBanner() {
		CatalogManager cm = CatalogManager.getInstance();
		int courseCount = cm.countCourses();
		getServletContext().setAttribute("courseCount", courseCount);
		Date lastUpdated = Calendar.getInstance().getTime();
		getServletContext().setAttribute("lastUpdated", lastUpdated);
	}

	private Course getCourseData(HttpServletRequest request)
			throws InvalidDataException {
		Course course = null;
		String courseCode = request.getParameter("courseCode");
		
		if (courseCode != null) {
			courseCode = courseCode.trim().toUpperCase();
		}
		
		String courseTitle = request.getParameter("courseTitle");
		if (courseTitle != null) {
			courseTitle = courseTitle.trim();
		}
		course = new Course(courseCode, courseTitle);
		
		String profName = request.getParameter("profName");
		if (profName == null || profName.equals("TBA")) {
			course.setProfessor(null);
		} else {
			String names[] = profName.split(" ");
			Professor professor = new Professor(names[0], names[1]);
			course.setProfessor(professor);
		}
		
		return course;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Course course = getCourseData(request);
			CatalogManager cm = CatalogManager.getInstance();
			
			cm.addCourse(course);
			session.setAttribute("course", course);
			updateBanner();
			request.getRequestDispatcher("/main.jsp")
					.forward(request, response);
			return;
		} catch (Exception e) {
			if (e.getMessage() == null) {
				e.printStackTrace(System.out);
			}
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
			return;
		}
	}
}
