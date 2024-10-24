
public class Activity1 {

	public static void main(String[] args) {
		// create new object with no-arguments constructor
		Time time = new Time();
		// use toString method
		System.out.println(time.toString());

		// create new object with arguments
		Time time1 = new Time(3, 53, 10);

		// use compare method
		System.out.println("object one greater than object two: " + (time.compareTo(time1) > 0));
		
		// use clone method
		Time time2 = (Time) time1.clone();
		// try to change new object
		time2.setTimeInSecounds(15);
		// new object dosen't changed so clone is true
		System.out.println(time1); 
	}

}
