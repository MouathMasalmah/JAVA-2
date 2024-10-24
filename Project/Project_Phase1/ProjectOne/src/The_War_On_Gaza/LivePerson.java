//Mouath sami masalmah,No:1220179, Dr.Fadi Khalil , Date: 3/12/2023 ,Lap 7
package The_War_On_Gaza;

public class LivePerson extends Person {

	LivePerson() {

	}

	public LivePerson(String iD, String name, int age, String gender, String address, String contactInfo) {

		super(iD, name, age, gender, address, contactInfo);
	}

	@Override
	public String toString() {
		return "LivePerson [ID=" + getID() + ", Name=" + getName() + ", Age=" + getAge() + ", Gender=" + getGender()
				+ ", Address=" + getAddress() + ", Contactlnfo=" + getContactlnfo() + "]";
	}

}
