package post_lap4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Tawjihi> students = new ArrayList<>();

		System.out.println("Enter the marks for each student:");

		for (int i = 1; i <= 6; i++) {
			System.out.println("Student " + i + ":");

			System.out.print("Enter seating number: ");
			int seatingNum = scanner.nextInt();

			System.out.print("Enter student ID: ");
			int studentID = scanner.nextInt();

			scanner.nextLine();
			System.out.print("Enter student name: ");
			String studentName = scanner.nextLine();

			System.out.print("Enter gender (M/F): ");
			char gender = scanner.next().charAt(0);

			scanner.nextLine();

			System.out.print("Enter school: ");
			String school = scanner.nextLine();

			Tawjihi student;
			if (i < 4) {
				System.out.println("Enter for Scientific student");
				student = new Scientific(seatingNum, studentID, studentName, gender, school);
			} else {
				System.out.println("Enter for Literary student");
				student = new Literary(seatingNum, studentID, studentName, gender, school);
			}

			for (String subject : student.subjects()) {
				System.out.print("Enter mark for " + subject + ": ");
				int mark = scanner.nextInt();
				String type = "Mandatory";

				if (i < 4) {
					if (subject.equals("Biology") || subject.equals("Chemistry")) {
						type = "ElectiveI";
					} else if (subject.equals("Religious Education") || subject.equals("Technology")) {
						type = "ElectiveII";
					}
				} else {
					if (subject.equals("Geography") || subject.equals("Religious Education")) {
						type = "ElectiveI";
					} else if (subject.equals("Technology") || subject.equals("Scientific Culture")) {
						type = "ElectiveII";
					}
				}

				student.addSubject(new Subject(subject, type, mark));
			}

			students.add(student);
		}

		Collections.sort(students);

		for (Student student : students) {
			System.out.println(student.toString());
			System.out.println("--------------------------------------------------");
		}
	}
}
