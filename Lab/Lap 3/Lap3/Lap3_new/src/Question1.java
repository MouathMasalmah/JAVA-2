import java.util.ArrayList;
import java.util.Scanner;

public class Question1 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("please enter sequence of numbers ending with 0");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        while (x != 0) {
            list.add(x);
            System.out.println("enter another number:");
            x = scanner.nextInt();
        }
        System.out.println(maximum(list));
    }

    public static Integer maximum(ArrayList<Integer> list) {
        int maximum = Integer.MIN_VALUE;
        for (Integer e : list) {
            if (maximum < e) maximum = e;
        }
        return maximum;
    }
}