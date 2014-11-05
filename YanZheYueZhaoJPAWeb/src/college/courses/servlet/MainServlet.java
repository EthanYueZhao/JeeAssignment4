package college.courses.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import college.courses.data.Course;
import college.courses.exceptions.InvalidCommandException;
import college.courses.model.CatalogManager;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// make sure user is logged in
		HttpSession session = request.getSession();
		if ( session.getAttribute("userName") == null ) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// clear last course user worked on
		session.setAttribute("course", null);
		// GET a course or ADD a course - sets new course to work on
		String command = request.getParameter("submit");
		try {
		switch ( command) {
			case "Get": {
				CatalogManager cm = CatalogManager.getInstance();
				String code = request.getParameter("courseCode");
				if ( code == null || code.isEmpty() ) {
					throw new InvalidCommandException("code code cannot be empty");
				}
				Course course = cm.getCourse(code.trim().toUpperCase() );
				session.setAttribute("course", course);
				request.getRequestDispatcher("/displayCourse.jsp").forward(request,  response);
				return;
				}	
			case "Add": {
				CatalogManager cm = CatalogManager.getInstance();
				request.setAttribute("professors", cm.getProfessorList());
				request.getRequestDispatcher("/addCourse.jsp").forward(request, response);
				return;
				}
			default: {
				throw new InvalidCommandException("Unrecognized command:" + command);
			}
		}
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
