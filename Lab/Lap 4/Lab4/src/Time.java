import java.util.Date;

public class Time implements Comparable<Time>, Cloneable {
	private long timeInSecounds;

	public Time(long hours, long minutes, long secounds) {
		long sum = hours * 3600;
		sum += minutes * 60;
		sum += secounds;
		timeInSecounds = sum;
	}


	public Time() {
		Date date = new Date();
		long sum = date.getHours() * 3600;
		sum += date.getMinutes() * 60;
		sum += date.getSeconds();
		timeInSecounds = sum;

	}

	public void setTimeInSecounds(long timeInSecounds) {
		this.timeInSecounds = timeInSecounds;
	}
	public long getHours() {
		return timeInSecounds / 3600;
	}

	public long getMinute() {
		return timeInSecounds / 60 % 60;
	}

	public long getSecound() {
		return timeInSecounds % 60;
	}

	public long getSecounds() {
		return timeInSecounds;

	}

	@Override
	public String toString() {
		return getHours() + " hour " + getMinute() + " minutes " + getSecound() + " secound";
	}

	@Override
	public int compareTo(Time o) {
		return (int) (timeInSecounds - o.getSecounds());
	}

	@Override
	protected Object clone() {
		return new Time(getHours(), getMinute(), getSecound());
	}
}
