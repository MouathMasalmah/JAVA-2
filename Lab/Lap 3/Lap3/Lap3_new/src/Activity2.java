
import java.util.*;

public class Activity2 {

	public static void main(String[] args) {
		Calendar c = new GregorianCalendar(2022,7,1);
			
			int i=c.get(c.DAY_OF_WEEK);
			c.get(c.DAY_OF_MONTH);
			System.out.print("\t\t\t"+"Augast");
			System.out.println("\t"+c.get(c.YEAR));
			
			System.out.println("Sun \t Mon \t Tue \t Wed \t Thu \t Fri \t Sat");
			for(i=1;i<=31;i++) {
				if(i%7==0) {
					System.out.print("\n"+"  ");
				}else if(i==1) {
					System.out.print("\t");
				
				}
				System.out.print(i+"\t");
			}

}}
