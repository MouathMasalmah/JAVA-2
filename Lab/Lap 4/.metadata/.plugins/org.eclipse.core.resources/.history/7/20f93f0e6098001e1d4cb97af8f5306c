package Activity3;

import java.util.ArrayList;

public abstract class Tawjihi extends Student implements Comparable<Tawjihi> {
	private int seatingNum;
	private int year = 2023;
	private ArrayList<Subject> subjects = new ArrayList<>();

	public abstract ArrayList<String> subjects();

	public int getSeatingNumber() {
		return seatingNum;
	}

	public void setSeatingNumber(int seatingNum) {
		if (seatingNum >= 0)
			this.seatingNum = seatingNum;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year >= 2022)
			this.year = year;
	}

	public ArrayList<Subject> getSubjects() {
		ArrayList<Subject> s = subjects;
		return s;
	}
	

	public Tawjihi(int seatingNum, int ID, String name, char gender, String school) {
		super(ID, name, gender, school);
		setSeatingNumber(seatingNum);
		// من اجل تطبيق الشروط نقوم بستخدام الستر للتحقيقق الشروط
		System.out.println("HI AYMNA");
	}

	public abstract double calculateAverage();

	public void addSubject(Subject s) {
		if (s != null)
			subjects.add(s);
	}

	public Subject getSubject(String title) {
		if (title != null)
			for (int i = 0; i < subjects.size(); i++)
				if (subjects.get(i).getTitle().equals(title))
					return subjects.get(i);
		return null;
	}

	public void setSubjectMark(String subjectName, int newMark) {
		for (int i = 0; i < subjects.size(); i++)
			if (subjects.get(i).getTitle().equals(subjectName)) {
				subjects.get(i).setMark(newMark);
				break;
			}
	}

	public abstract String toString();
	public abstract boolean equals(Object obj);


	public int compareTo(Tawjihi o) {
		
		if (this.calculateAverage() - o.calculateAverage() > 0)
			return 1;
		else if (this.calculateAverage()==o.calculateAverage())
			return 0;
		else
			return -1;
	}

}
