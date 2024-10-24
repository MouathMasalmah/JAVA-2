package TestPackage;

public class Test {
	public static void main(String[] args) {
		xMethod(5);
	}

	public static void xMethod(int n) {
	 if (n > 0) {
	 xMethod(n - 1); 
	 System.out.print(n + " ");
	 }
	 }
}