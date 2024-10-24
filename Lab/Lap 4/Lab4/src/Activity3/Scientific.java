package Activity3;

import java.util.*;

public class Scientific extends Tawjihi {

	public Scientific(int seatingNum, int ID, String name, char gender, String school) {
		super(seatingNum, ID, name, gender, school);
	}

	
	public final ArrayList<String> subjects() {
		ArrayList<String> s = new ArrayList<>(Arrays.asList("Arabic", "English", "Physics", "Math", "Biology",
				"Chemistry", "Technology","Religious Education"));
		return s;
	}

	@Override
	public String toString() {
	    return "Seating Number: " + super.getSeatingNumber() + 
	           "\nStudent Name: " + super.getName() + 
	           "\nAverage: " + this.calculateAverage() +
	           "\nYear: " + this.getYear() + 
	           "\nGender: " + this.getGender();
	}

	@Override
	public double calculateAverage() {
		double sum = 0;

		ArrayList<Subject> subjects = super.getSubjects();
		for (int i = 0; i < subjects.size(); i++) {
			String s = subjects.get(i).getType();
			if (s.equals("Mandatory"))

				sum += subjects.get(i).getMark();
			else if (s.equals("ElectiveI")) {

				sum += ElectiveImax(subjects);

			} else if (s.equals("ElectiveII")) {
				sum += ElectiveIImax(subjects);
			}
		}
		return sum /subjects.size();
	}

	public double ElectiveImax(ArrayList<Subject> subjects) {
		double m = 0;
		for (int i = 0; i < subjects.size(); i++) {
			String s = subjects.get(i).getType();

			if (s.equals("ElectiveI")) {
				if (m < subjects.get(i).getMark())
					m = subjects.get(i).getMark();

			}

		}
		return m;
	}

	public double ElectiveIImax(ArrayList<Subject> subjects) {
		double m = 0;
		for (int i = 0; i < subjects.size(); i++) {
			String s = subjects.get(i).getType();

			if (s.equals("ElectiveII")) {
				if (m < subjects.get(i).getMark())
					m = subjects.get(i).getMark();

			}

		}
		return m;
	}


	@Override
	public boolean equals(Object obj) {
		Scientific m=(Scientific) obj;
		if (this.compareTo(m)==0) {
			return true;
		}
		
		return false;
	}
}
