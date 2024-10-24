//Mouath sami masalmah,No:1220179, Dr.Fadi Khalil , Date: 3/12/2023 ,Lap 7
package The_War_On_Gaza;

public abstract class Person  {
	private String ID;
	private String Name;
	private int Age;
	private String Gender;
	private String Address;
	private String Contactlnfo;
	private String roleInFamily;

	Person() {

	}

	public Person(String iD, String name, int age, String gender, String address, String contactInfo,String roleInFamily) {
		
		this.ID = iD;
		this.Name = name;
		this.Age = age;
		this.Gender = gender;
		this.Address = address;
		this.Contactlnfo = contactInfo;
		this.roleInFamily=roleInFamily;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setAge(int age) {
		Age = age;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public void setContactlnfo(String contactlnfo) {
		Contactlnfo = contactlnfo;
	}

	public void setRoleInFamily(String roleInFamily) {
		this.roleInFamily = roleInFamily;
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

	public String getRoleInFamily() {
		return roleInFamily;
	}

	@Override
	public String toString() {
		return "Person [ID=" + ID + ", Name=" + Name + ", Age=" + Age + ", Gender=" + Gender + ", Address=" + Address
				+ ", Contactlnfo=" + Contactlnfo + ", roleInFamily=" + roleInFamily + "]\n";
	}

}