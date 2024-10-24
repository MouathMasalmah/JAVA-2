import java.util.*;

public class Activity1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Hexa Code: ");
		String Hexa = scan.next();

		System.out.println(hex2Dec(Hexa));
	}

	public static int hex2Dec(String Hexa) {
		int Sum = 0;
		for (int i = 0; i < Hexa.length(); i++) {

			if ((int) Hexa.charAt(i) - 48 < 10) {
				int num = (int) (((int) Hexa.charAt(i) - 48) * Math.pow(16, Hexa.length() - (i + 1)));
				Sum += num;
			} else if ((int) Hexa.charAt(i) >= 'A' && (int) Hexa.charAt(i) <= 'F') {
				int Num = (int) Hexa.charAt(i) - 'A' + 10;
				Num = (int) (Num * Math.pow(16, Hexa.length() - (i + 1)));
				Sum += Num;
			} else {
				throw new NumberFormatException("Mouath");
			}

		}
		return Sum;
	}
}