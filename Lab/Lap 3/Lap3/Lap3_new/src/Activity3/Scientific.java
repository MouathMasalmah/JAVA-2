package Activity3;

import java.util.*;

public class Scientific extends Tawjihi {
	public Scientific(int ID, String name, char gender, String school) {
		super(ID, name, gender, school);
	}

	public Scientific(int seatingNum, int ID, String name, char gender, String school) {
		super(seatingNum, ID, name, gender, school);
	}

	public Scientific() {
		super();
	}

	@Override
	public final ArrayList<String> subjects() {
		ArrayList<String> s = new ArrayList<>(Arrays.asList("Arabic", "English", "Physics", "Math", "Biology",
				"Chemistry", "Religious Education", "Technology"));
		return s;
	}

	@Override
	public String toString() {
		return "Seating Number : " + super.getSeatingNumber() + "Student Name : " + super.getName() + "Average : "
				+ this.calculateAverage();
	}

	@Override
	public double calculateAverage() {
		double sum = 0;
		boolean EI = true, EII = true;
		ArrayList<Subject> subjects = super.getSubjects();
		for (int i = 0; i < subjects.size(); i++) {
			String s = subjects.get(i).getType();
			if (s.equals("Mandatory")) {
				sum += subjects.get(i).getMark();
			}
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