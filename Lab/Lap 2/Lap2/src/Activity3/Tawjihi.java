package Activity3;

import java.util.*;

public class Tawjihi extends Student {
	private int seatingNum;
	private int year = 2022;
	private ArrayList<Subject> subjects = new ArrayList<>();

	public Tawjihi(int seatingNum, int ID, String name, char gender, String school) {
		super(ID, name, gender, school);
		this.seatingNum = seatingNum;
	}

	public int getSeatingNumber() {
		return seatingNum;
	}

	public void setSeatingNumber(int seatingNum) {
		this.seatingNum = seatingNum;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ArrayList<Subject> getSubjects() {
		ArrayList<Subject> s = subjects;
		return s;
	}

	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}

	public double calculateAverage() {
		double res = 0;
		int c = 0;
		for (int i = 0; i < subjects.size(); i++, c++)
			res += this.subjects.get(i).getMark();
		return res / c;
	}

	public void addSubject(Subject Sub) {
		if (Sub != null)
			subjects.add(Sub);
	}

	public Subject getSubject(String title) {
		if (title != null) {
			int i = 0;
			while (i < subjects.size())
				if (subjects.get(i).getTitle().equals(title))
					return subjects.get(i);
			i++;
		}
		return null;
	}
}
