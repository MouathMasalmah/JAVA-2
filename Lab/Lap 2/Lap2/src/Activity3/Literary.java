package Activity3;

import java.util.*;

public class Literary extends Tawjihi {
	public Literary(int seatingNum, int ID, String name, char gender, String school) {
		super(seatingNum, ID, name, gender, school);
	}

	@Override
	public double calculateAverage() {
		ArrayList<Subject> subjects = super.getSubjects();
		if (subjects.size() == 0) {
			return 0;
		}
		double sum = 0;
		for (int i = 0; i < subjects.size(); i++) {
			sum += subjects.get(i).getMark();
		}
		return sum / subjects.size();
	}
}