//Mouath sami masalmah,No:1220179, Dr.Fadi Khalil , Date: 3/12/2023 ,Lap 7
package The_War_On_Gaza;

public class LivePerson extends Person implements Cloneable{

	LivePerson() {

	}

	public LivePerson(String iD, String name, int age, String gender, String address, String contactInfo,String roleInFamily) {

		super(iD, name, age, gender, address, contactInfo,roleInFamily);
	}
@Override
protected Object clone() throws CloneNotSupportedException {//We cast the object that was copied because the method returns an object of type Object and we create throws of type Possible Exception to be processed at the place of the call.

	return super.clone();
}
	@Override
	public String toString() {
		return "LivePerson [ID=" + getID() + ", Name=" + getName() + ", Age=" + getAge() + ", Gender=" + getGender()
				+ ", Address=" + getAddress() + ", Contactlnfo=" + getContactlnfo() + "]\n";
	}

}
