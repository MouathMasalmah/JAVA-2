import java.util.*;

public class Activity1 {

	public static void main(String[] args) {
		ArrayList<Integer> s = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("Enter an integer number:");
			int c = scan.nextInt();

			if (c != 0) {
				s.add(c);
			} else {
				int Max = s.get(0);
				for (int i = 1; i < s.size(); i++) {
					if (Max < s.get(i)) {
						Max = s.get(i);
					}
				}
				System.out.println("The Max Index non-Zero Number is "+ Max);
				break;
			}
		}

		
	}

}
