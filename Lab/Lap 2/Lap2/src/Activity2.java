import java.util.*;

public class Activity2 {

	public static void main(String[] args) {
		ArrayList<Integer> A1 = new ArrayList<Integer>();
		ArrayList<Integer> A2 = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		int index = 0;
		while (index < 10) {
			System.out.println("Enter an integer number:");
			int s = scan.nextInt();

			A1.add( s);
			index++;
		}
		for (int i = 0; i < A1.size(); i++) {
			if (!(A2.contains(A1.get(i)))) {
				int c=A1.get(i);
				A2.add( c);
			} else {
				continue;
			}

		}
		System.out.println("The First list: "+A1);
		System.out.println("The Secound list: "+A2);
	}
}
