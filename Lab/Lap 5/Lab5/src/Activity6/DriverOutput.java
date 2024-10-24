package Activity6;

import java.io.*;
import java.util.*;

public class DriverOutput {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Literary literary = null;
		Scientific scientific = null;
		String filename = "Tawjihi.dat";

		ArrayList<String> Literar = literary.getSubjects();
		ArrayList<String> Scientifi = scientific.getSubjects();

		try (DataOutputStream Data = new DataOutputStream(new FileOutputStream(filename))) {
			System.out.println("Enter the marks for each student:");

			for (int i = 0; i < 2; i++) {
				System.out.println("Student " + i + ":");

				System.out.print("Enter seating number: ");
				int seatingNum = scanner.nextInt();
				Data.writeInt(seatingNum);
				System.out.print("Enter student ID: ");
				int studentID = scanner.nextInt();
				Data.writeInt(studentID);
				scanner.nextLine();
				System.out.print("Enter student name: ");
				String studentName = scanner.nextLine();
				Data.writeUTF(studentName);
				System.out.print("Enter gender (M/F): ");
				char gender = scanner.next().charAt(0);
				Data.writeChar(gender);
				scanner.nextLine();
				System.out.print("Enter school: ");
				String school = scanner.nextLine();
				Data.writeUTF(school);
				System.out.print("Enter branch: ");
				String branch = scanner.nextLine();
				Data.writeUTF(branch);
				System.out.print("Enter student year: ");
				int year = scanner.nextInt();
				Data.writeInt(year);
				System.out.print("Enter student year: ");
				int numOfSubjects = scanner.nextInt();
				Data.writeInt(numOfSubjects);
				if (branch.equals("Literary")) {
					for (String subject : Literar) {
						System.out.print("Enter mark for " + subject + ": ");
						Data.writeUTF(subject);
						int mark = scanner.nextInt();
						Data.writeInt(mark);
						String type = "Mandatory";

						if (subject.equals("Biology") || subject.equals("Chemistry") || subject.equals("Geography")
								|| subject.equals("Religious Education")) {
							type = "ElectiveI";
							Data.writeUTF(type);
						} else if (subject.equals("Religious Education") || subject.equals("Technology")
								|| subject.equals("Technology") || subject.equals("Scientific Culture")) {
							type = "ElectiveII";
							Data.writeUTF(type);
						} else {
							Data.writeUTF(type);
						}
						System.out.print("Enter Maxmark for " + subject + ": ");

						int Max = scanner.nextInt();
						Data.writeInt(Max);
					}

				} else {

					for (String subject : Scientifi) {
						System.out.print("Enter mark for " + subject + ": ");
						Data.writeUTF(subject);
						int mark = scanner.nextInt();
						Data.writeInt(mark);
						String type = "Mandatory";

						if (subject.equals("Biology") || subject.equals("Chemistry") || subject.equals("Geography")
								|| subject.equals("Religious Education")) {
							type = "ElectiveI";
							Data.writeUTF(type);
						} else if (subject.equals("Religious Education") || subject.equals("Technology")
								|| subject.equals("Technology") || subject.equals("Scientific Culture")) {
							type = "ElectiveII";
							Data.writeUTF(type);
						} else {
							Data.writeUTF(type);
						}
						System.out.print("Enter Maxmark for " + subject + ": ");

						int Max = scanner.nextInt();
						Data.writeInt(Max);
					}

				}

			}

		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

}
