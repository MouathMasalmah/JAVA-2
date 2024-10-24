package Activity6;

import java.io.*;
import java.util.ArrayList;

public class DriverInput {

	public static void main(String[] args) throws IOException {
		ArrayList<Tawjihi> students = new ArrayList<>();
		int i = 0;
		FileInputStream file = new FileInputStream("Tawjihi.dat");
		DataInputStream Enter = new DataInputStream(file);
		while (Enter.available() > 0) {
			int seatingNum = Enter.readInt();
			int studentID = Enter.readInt();
			String studentName = Enter.readUTF();
			char gender = Enter.readChar();
			String school = Enter.readUTF();
			String branch = Enter.readUTF();

			int year = Enter.readInt();
			int numOfSubjects = Enter.readInt();

			Tawjihi student;
			if (branch.equals("Scientific")) {
				Scientific Scient = new Scientific(seatingNum, studentID, studentName, gender, school);
				students.add(Scient);
				i++;
			} else {
				Literary lib = new Literary(seatingNum, studentID, studentName, gender, school);
				students.add(lib);
				i++;
			}

			for (int m = 0; i < numOfSubjects; m++) {
				String subjectTitle = Enter.readUTF();
				int subjectMark = Enter.readInt();
				String subjectType = Enter.readUTF();

				int subjectMaxMark = Enter.readInt();
				Subject Sub = new Subject(subjectTitle, subjectType, subjectMark, subjectMaxMark);
				students.get(i).addSubject(Sub);
			}

		}
	}
}
