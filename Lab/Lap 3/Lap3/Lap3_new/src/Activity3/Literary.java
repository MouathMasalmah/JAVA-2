package Activity3;

import java.util.*;

public class Literary extends Tawjihi {
	public Literary(int ID, String name, char gender, String school) {
		super(ID, name, gender, school);
	}

	public Literary(int seatingNumber, int ID, String name, char gender, String school) {
		super(seatingNumber, ID, name, gender, school);
	}

	public Literary() {
		super();
	}

	@Override
	public final ArrayList<String> subjects() {
		ArrayList<String> s = new ArrayList<>(Arrays.asList("Arabic", "English", "Physics", "History", "Geography",
				"Religious Education", "Scientific Culture", "Technology"));
		return s;
	}

	@Override
	public String toString() {
		return "Seating Number : " + super.getSeatingNumber() + "Student Name : " + super.getName() + "Average : "
				+ this.calculateAverage();
	}

	public double calculateAverage() {
		double sum = 0;
		boolean EI = true, EII = true;
		ArrayList<Subject> subjects = super.getSubjects();
		for (int i = 0; i < subjects.size(); i++) {
			String s = subjects.get(i).getType();
			if (s.equals("Mandatory"))
				sum += subjects.get(i).getMark();
			else if (s.equals("ElectiveI") && EI) {
				sum += subjects.get(i).getMark();
				EI = false;
			} else if (s.equals("ElectiveII") && EII) {
				sum += subjects.get(i).getMark();
				EII = false;
			}
		}
		return sum / 7;
	}
}