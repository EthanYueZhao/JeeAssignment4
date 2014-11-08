package college.courses.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import college.courses.model.CatalogManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		CatalogManager cm =new CatalogManager();
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
		session.invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");
		if ( name != null ) {
			request.setAttribute("message", "You are already logged in as " + name);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		name = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		if ( name.isEmpty() || password.isEmpty() ) {
			request.setAttribute("message", "You must enter both a name and a password");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			return;
		} else {
			
			session.setAttribute("userName", name);
			RequestDispatcher rd = request
					.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		}
	}
}
