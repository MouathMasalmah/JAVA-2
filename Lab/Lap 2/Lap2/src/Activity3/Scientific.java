package Activity3;

import java.util.*;

public class Scientific extends Tawjihi {
	public Scientific(int seatingNum, int ID, String name, char gender, String school) {
		super(seatingNum, ID, name, gender, school);
	}

	@Override
	public double calculateAverage() {
		ArrayList<Subject> subjects = super.getSubjects();
		if (subjects.size() == 0) {
			return 0;
		}
		double sum = 0;
		int i = 0;
		while (i < subjects.size()) {
			sum += subjects.get(i).getMark();
			i++;
		}
		return sum / subjects.size();
	}
}
