package post_lap4;

import java.util.Calendar;

public class Time1  implements Comparable<Time1>, Cloneable{
	private long time;

	public Time1() {
		this.time = System.currentTimeMillis();

	}

	public Time1(int hour, int minute, int second) {

		Calendar M = Calendar.getInstance();

		M.set(Calendar.SECOND, second);
		M.set(Calendar.MINUTE, minute);
		M.set(Calendar.HOUR_OF_DAY, hour);
		this.time = M.getTimeInMillis();
	}

	public Time1(long elapsed) {
		this.time = elapsed;

	}

	public int getHour() {
		Calendar M = Calendar.getInstance();
		M.setTimeInMillis(this.time);
		return M.get(Calendar.HOUR_OF_DAY);

	}

	public int getMinute() {
		Calendar M = Calendar.getInstance();
		M.setTimeInMillis(this.time);
		return M.get(Calendar.MINUTE);

	}

	public int getSecond() {
		Calendar M = Calendar.getInstance();
		M.setTimeInMillis(this.time);
		return M.get(Calendar.SECOND);

	}

	public long getSeconds() {
		return this.time / 1000;
	}

	public String toString() {
		int hours = getHour();
		int minutes = getMinute();
		int seconds = getSecond();
		return String.format("%d hour %d minute %d second", hours, minutes, seconds);
	}
	
	 

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int compareTo(Time1 obj) {
		 return (int) (this.getSecond() - obj.getSecond());
	}

	public static void main(String[] args) {
		try {
			Time1 n1 = new Time1();
			System.out.println();
			long k = 330004040l;
			Time1 n2 = new Time1(k);
			Time1 n3 = new Time1(2, 4, 56);
			Time1 n4 = (Time1) n3.clone();
			System.out.println("Current time: " + n1.toString());
			System.out.println("----------------------------------------------------");
			System.out.println(" elapsed time: " + n2.toString());
			System.out.println("----------------------------------------------------");
			System.out.println("Specific time: " + n3.toString());
			System.out.println("----------------------------------------------------");
			System.out.println("Cloned n3... =: " + n4.toString());
			System.out.println("Specific current time with Cloned time: " + n3.compareTo(n4));
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}

	}

	

	

	
	}

	

