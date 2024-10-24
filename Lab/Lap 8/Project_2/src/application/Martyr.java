package application;

public class Martyr implements Comparable<Martyr> {
	private String name;
	private int age;
	private String gender;

	public Martyr() {

	}

	public Martyr(String name, int age, String gender) {

		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		if (age == -1) {
			return "Martyr [Name=" + name + ", Age= Un_Noun" + ", Gender=" + gender + "]\n";
		}
		return "Martyr [Name=" + name + ", Age=" + age + ", Gender=" + gender + "]\n";
	}

	@Override
	public int compareTo(Martyr o) {
		if (this.age - o.getAge() != 0) {
			return this.age - o.getAge();
		}
		return this.gender.toLowerCase().compareToIgnoreCase(o.getGender());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null)
			return this.name.toLowerCase().compareToIgnoreCase(((Martyr) obj).getName()) == 0;
		return false;
	}
}
