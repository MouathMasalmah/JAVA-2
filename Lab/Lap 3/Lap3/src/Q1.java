import java.util.*;

public class Q1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> ListOfNumber = new ArrayList<>();
		System.out.println("enter a number");
		int Num = scan.nextInt();
		while (Num > 0) {
			ListOfNumber.add(Num);
			System.out.println("enter a number");
			 Num = scan.nextInt();
		}

		System.out.println("The New List Equel"+evenAndOdd(ListOfNumber));
	}

	public static ArrayList<Integer> evenAndOdd(ArrayList<Integer> ListOfNumber) {
		ArrayList<Integer> List = new ArrayList<>();
		ArrayList<Integer> ListEven = new ArrayList<>();
		ArrayList<Integer> ListOdd = new ArrayList<>();
		for (int i = 0; i < ListOfNumber.size(); i++) {
			if (ListOfNumber.get(i) % 2 == 0) {
				ListEven.add(ListOfNumber.get(i));
			} else if (ListOfNumber.get(i) % 2 != 0) {
				ListOdd.add(ListOfNumber.get(i));
			}
		}
		List.addAll(ListOdd);
		List.addAll(ListEven);

		return List;

	}
}