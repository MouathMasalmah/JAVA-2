package Activity3;

public class Student {
	private int ID;
	private String name;
	private char gender;
	private String school;

	public Student(int ID, String name, char gender, String school) {
		this.ID = ID;
		this.name = name;
		this.gender = gender;
		this.school = school;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

}
