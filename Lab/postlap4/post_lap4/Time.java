package post_lap4;

public class Time implements  Comparable<Time>,Cloneable {
    private long time;

    
    public Time() {
        this.time = System.currentTimeMillis();
    }

   
    public Time(int hour, int minute, int second) {
        this.time = hour * 3600000L + minute * 60000L + second * 1000L;
    }
    public Time(long elapsed) {
		this.time = elapsed;

	}
  
    public int getHour() {
        return (int) ((this.time / 3600000L )% 24);
    }

    
    public int getMinute() {
        return (int) ((this.time / 60000L )% 60);
    }

  
    public int getSecond() {
        return (int) ((this.time / 1000L )% 60);
    }

    
    @Override
    public String toString() {
        return String.format("%d hour %d minute %d second", getHour(), getMinute(), getSecond());
    }

    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    @Override
    public int compareTo(Time obj) {
        return (int) (this.getSecond() - obj.getSecond());
    }


    public static void main(String[] args) {
        try {
            Time currentTime = new Time();
            Time specificTime = new Time(10, 30, 25);
            Time clonedTime = (Time) specificTime.clone();

            System.out.println("Current Time: " + currentTime);
            System.out.println("Specific Time: " + specificTime);
            System.out.println("Cloned Time: " + clonedTime.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


}
