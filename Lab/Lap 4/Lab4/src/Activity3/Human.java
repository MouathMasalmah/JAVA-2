package Activity3;

public class Human implements HumanBeing {
	private String name;
	private char gender='F';

	public Human(String name, char gender) {
	
		setName( name);
		setGender(gender);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		if (gender == M || gender == F)
			this.gender = gender;
	}

}
