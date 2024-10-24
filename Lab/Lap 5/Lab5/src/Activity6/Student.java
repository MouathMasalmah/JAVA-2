package Activity6;

public abstract class Student extends Human {
	private int ID;
	private String name;
	
	private String school;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		if (ID > 0) {
			this.ID = ID;
		}
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		if (!name.isEmpty())
			this.name = name;
	}

	

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		if (!school.isEmpty())
			this.school = school;
	}

	public Student(int ID, String name, char gender, String school) {
		super(name, gender);
		setID(ID);
		setSchool(school);
	
	}
}
