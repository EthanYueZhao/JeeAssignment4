package college.courses.exceptions;

public class CourseNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CourseNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public CourseNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CourseNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CourseNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
