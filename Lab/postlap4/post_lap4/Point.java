package post_lap4;

public class Point implements Comparable<Point>, Cloneable {
	private int x;
	private int y;

	public Point() {
		this.x = 0;
		this.y = 0;
	}

	public Point(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Point other) {
		if (this.x > other.x || (this.x == other.x && this.y > other.y)) {
			return 1;
		} else if (this.x == other.x && this.y == other.y) {
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
		return "Point (x=" + x + ", y=" + y + ")";
	}

	@Override
	public boolean equals(Object obj) {

		Point point = (Point) obj;
		return this.x == point.x && this.y == point.y;
	}

	public static void main(String[] args) {
		try {
			Point n1 = new Point();
			Point n2 = new Point(4, 5);
			Point n3 = (Point) n2.clone();
			System.out.println("Point 1: " + n1);
			System.out.println("----------------------------------------------------");
			System.out.println("Point 2: " + n2);
			System.out.println("----------------------------------------------------");
			System.out.println("Point 3 (clone of Point 1): " + n3);
			System.out.println("----------------------------------------------------");

			System.out.println("Point 1 equals Point 2: " + n1.equals(n2));
			System.out.println("----------------------------------------------------");
			System.out.println("Point 1 compared to Point 2: " + n1.compareTo(n2));
			System.out.println("----------------------------------------------------");
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}

	}

}
