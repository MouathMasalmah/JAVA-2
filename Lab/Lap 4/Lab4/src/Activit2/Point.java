package Activit2;

public class Point implements Comparable<Point>, Cloneable {
	private int A;
	private int B;

	public Point() {
		A = 0;
		B = 0;
	}

	public Point(int a, int b) {

		A = a;
		B = b;
	}

	public int getX() {
		return A;
	}

	public void setX(int A) {
		this.A = A;
	}

	public int getY() {
		return B;
	}

	public void setY(int B) {
		this.B = B;
	}

	@Override
	public int compareTo(Point other) {
		if (this.A > other.A || (this.A == other.A && this.B > other.B)) {
			return 1;
		} else if (this.A == other.A && this.B == other.B) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();

	}

	@Override
	public String toString() {
		return "Point (A=" + A + ", B=" + B + ")";
	}

	@Override
	public boolean equals(Object obj) {

		Point point = (Point) obj;
		return this.A == point.A && this.B == point.B;
	}

	public static void main(String[] args) throws CloneNotSupportedException {

		Point Num1 = new Point();
		Point Num2 = new Point(10, 15);
		Point Num3 = (Point) Num2.clone();
		System.out.println("P 1: " + Num1);
		System.out.println("*********************************************************");
		System.out.println("P 2: " + Num2);
		System.out.println("*********************************************************");
		System.out.println("Point 3 clone of Point 1: " + Num3);
		System.out.println("*********************************************************");

		System.out.println("Point 1 equals Point 2: " + Num1.equals(Num2));
		System.out.println("*********************************************************");
		System.out.println("Point 1 compared to Point 2: " + Num1.compareTo(Num2));
		System.out.println("*********************************************************");

	}

}
