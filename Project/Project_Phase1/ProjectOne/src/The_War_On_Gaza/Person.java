//Mouath sami masalmah,No:1220179, Dr.Fadi Khalil , Date: 3/12/2023 ,Lap 7
package The_War_On_Gaza;

public class Person  {
	private String ID;
	private String Name;
	private int Age;
	private String Gender;
	private String Address;
	private String Contactlnfo;
	

	Person() {

	}

	public Person(String iD, String name, int age, String gender, String address, String contactInfo) {
		
		this.ID = iD;
		this.Name = name;
		this.Age = age;
		this.Gender = gender;
		this.Address = address;
		this.Contactlnfo = contactInfo;
		
	}

	public void getID(String ID) {
		this.ID = ID;
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return Name;
	}

	public int getAge() {
		return Age;
	}

	public String getGender() {
		return Gender;
	}

	public String getAddress() {
		return Address;
	}

	public String getContactlnfo() {
		return Contactlnfo;
	}

	@Override
	public String toString() {
		return "Person [ID=" + ID + ", Name=" + Name + ", Age=" + Age + ", Gender=" + Gender + ", Address=" + Address
				+ ", Contactlnfo=" + Contactlnfo + "]";
	}

}