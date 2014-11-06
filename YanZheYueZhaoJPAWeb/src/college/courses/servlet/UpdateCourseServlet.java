package college.courses.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import college.courses.data.Course;
import college.courses.data.Professor;
import college.courses.exceptions.InvalidCommandException;
import college.courses.exceptions.InvalidDataException;
import college.courses.model.CatalogManager;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCourseServlet() {
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Course course = (Course) session.getAttribute("course");
		try {
		if ( course == null ) {
			throw new InvalidCommandException("No course selected to update or delete");
		}
		// process an update or delete or Done command and getting a course
				String command = request.getParameter("submit");
				switch ( command) {
					case "Update": {
						CatalogManager cm = CatalogManager.getInstance();
						request.setAttribute("professors", cm.getProfessorList() );
						request.getRequestDispatcher("/updateCourse.jsp").forward(request,  response);
						return;
						}	
					case "Delete": {
						CatalogManager cm = CatalogManager.getInstance();
						cm.deleteCourse( course.getCoursecode() );
                        updateBanner();
						request.getRequestDispatcher("/main.jsp").forward(request,  response);
						return;
						}
					case "Done": {
						request.getRequestDispatcher("/main.jsp").forward(request,  response);
						return;
					}
					default: {
						throw new InvalidCommandException("Unrecognized command:" + command);
					}
				}
				} catch (Exception e) {
					request.setAttribute("message",  e.getMessage());
					request.getRequestDispatcher("/error.jsp").forward(request,  response);
				}
	}
	
	private Course getCourseData(HttpServletRequest request)
			throws InvalidDataException {
		HttpSession session = request.getSession();
		Course course = (Course) session.getAttribute("course");
		String courseTitle = request.getParameter("courseTitle");
		if (courseTitle != null) {
			courseTitle = courseTitle.trim();
		}
		course.setCoursetitle(courseTitle);
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
	
	// process update course form
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("submit");
		if ( command.equals("Cancel") ) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			return;
		}
		try {
			CatalogManager cm = CatalogManager.getInstance();
			Course course = getCourseData(request);
			cm.updateCourse(course);
			request.getSession().setAttribute("course", course);
			updateBanner();
			request.getRequestDispatcher("/main.jsp")
					.forward(request, response);
			return;
		} catch (Exception e) {
			if ( e.getMessage() == null ) { 
				e.printStackTrace(System.out);
			}
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
			return;
		}
	}
}
