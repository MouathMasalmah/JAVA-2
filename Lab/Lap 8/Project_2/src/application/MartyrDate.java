package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MartyrDate implements Comparable<MartyrDate> {
	private String date;
	private linkedList<Martyr> martyrList = new linkedList<>();

	public MartyrDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public linkedList<Martyr> getMartyrList() {
		return martyrList;
	}

	public void setMartyrList(linkedList<Martyr> martyrList) {
		this.martyrList = martyrList;
	}

	public LinkedListQueue<Martyr> allMartyerInTheDate() {
		LinkedListQueue<Martyr> martyrs = martyrList.traverse();
		return martyrs;
	}

	public Martyr oldest() {
		if (martyrList.lastNodeData() != null)
			return martyrList.lastNodeData();
		return null;
	}

	public Martyr youngest() {
		LinkedListQueue<Martyr> martyrs = martyrList.traverse();
		Martyr youngestMartyr = new Martyr();
		if (!martyrs.isEmpty()) {
			youngestMartyr = martyrs.dequeue();
			Martyr curr = new Martyr();
			while (!martyrs.isEmpty()) {
				curr = martyrs.dequeue();
				if (curr.getAge() > -1 && youngestMartyr.getAge() == -1) {
					youngestMartyr = curr;
					break;
				} else {
					return youngestMartyr;
				}
			}
		}
		return youngestMartyr;
	}

	public int avgAge() {
		int avgAge = 0;
		int numberOfMartyrs = 0;
		LinkedListQueue<Martyr> martyrs = martyrList.traverse();
		Martyr martyr = new Martyr();
		while (!martyrs.isEmpty()) {
			martyr = martyrs.dequeue();
			if (martyr.getAge() > -1) {
				avgAge += martyr.getAge();
				numberOfMartyrs++;
			}
		}
		if (numberOfMartyrs != 0)
			return avgAge / numberOfMartyrs;
		return 0;
	}

	public String searchByPartOfName(String partName) {
		LinkedListQueue<Martyr> martyrs = martyrList.traverse();
		String result = null;
		String martyrName = null;
		Martyr curr = new Martyr();
		while (!martyrs.isEmpty()) {
			curr = martyrs.dequeue();
			martyrName = curr.getName();
			if (martyrName.toLowerCase().contains(partName.toLowerCase()) == true) {
				if (result != null) {
					result += curr.toString();
				} else {
					result = curr.toString();
				}
			}
		}
		return result;

	}

	public boolean deleteMartyer(Martyr martyerName) {
		LinkedListQueue<Martyr> martyrs = martyrList.traverse();
		Martyr searchMartyr = new Martyr();
		Martyr temp = new Martyr();
		while (!martyrs.isEmpty()) {
			temp = martyrs.dequeue();
			if (temp.equals(martyerName) == true) {
				searchMartyr = temp;
				break;
			}
		}
		return martyrList.delete(searchMartyr);

	}

	@Override
	public String toString() {
		return "" + date;
	}

	@Override
	public int compareTo(MartyrDate o) {
		String[] Split1 = this.getDate().split("/");
		String[] Split2 = o.getDate().split("/");
		int day1 = Integer.parseInt(Split1[1]);
		int month1 = Integer.parseInt(Split1[0]) - 1;
		int year1 = Integer.parseInt(Split1[2]);

		int day2 = Integer.parseInt(Split2[1]);
		int month2 = Integer.parseInt(Split2[0]) - 1;
		int year2 = Integer.parseInt(Split2[2]);

		Calendar dateObject1 = new GregorianCalendar(year1, month1, day1);
		Calendar dateObject2 = new GregorianCalendar(year2, month2, day2);
		return dateObject1.compareTo(dateObject2);
	}

}
