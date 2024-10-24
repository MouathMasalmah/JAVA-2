package Q2;

import java.util.ArrayList;

public class User {
	protected String UserId;
	public String UserName;
	public String email;
	public static int NumberOfUser;
	ArrayList<User> FrindList = new ArrayList<>();

	public User() {
		
	}

	public User(String userId, String userName, String email) {
		super();
		UserId = userId;
		UserName = userName;
		this.email = email;
		NumberOfUser++;
	}

	public ArrayList<User> getFrindList() {
		return FrindList;
	}

	public void addFrindList(User x) {
		FrindList.add(x);
		
	}
	

}
