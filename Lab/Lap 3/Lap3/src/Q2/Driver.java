package Q2;

public class Driver {

	public static void main(String[] args) {
		User FirstUser = new User("1425", "Mouath Masalmah", "mouath@gmail.com");
		User SecondtUser = new User("505", "Mohammad Salem", "Mohammad@gmail.com");
		FirstUser.addFrindList(SecondtUser);
		SecondtUser.addFrindList(FirstUser);
		Post p = new Post("Mohammad", SecondtUser, "111");
		System.out.println("The Post Detials = "+p.toString());
		p.addLike(FirstUser);
		System.out.println("The Likes Number OnPost= "+ p.getLike());
		System.out.println("The Number Of Users = "+ FirstUser.NumberOfUser);
	}

}
