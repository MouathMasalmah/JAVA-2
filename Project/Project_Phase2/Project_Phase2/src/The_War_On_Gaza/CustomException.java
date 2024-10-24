package The_War_On_Gaza;

public class CustomException extends Exception {/*This exception was created to address logical problems that were expected to appear and that the system cannot detect,
 													such as that the age of the son must be less than the age of the father.
 													The message is entered via the throw message and then printed using the catch.*/
	private String message = "";
	Manager manager = new Manager();

	public CustomException() {
		// default message
		message = "Error!";
	}

	public CustomException(Family FAMILY) {
		// default message
		
			this.message = FAMILY.getFamilyName() + " : The family is already registered!";

		
	}

	public CustomException(int Age) {
		// default message

		message = Age + " : Enter a TRUE Age!";

	}

	public CustomException(String message) {
		// default message
		this.message = message;

	}

	@Override
	public String getMessage() {
		return message;
	}
}
