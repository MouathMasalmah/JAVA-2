package Q2;

public class Admin extends User {

	boolean isAdmin;

	Admin() {

	}

	Admin(String userId, String username, String email, boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
