//Mouath sami masalmah,No:1220179, Dr.Fadi Khalil , Date: 3/12/2023 ,Lap 7
package The_War_On_Gaza;

public class Martyr extends Person {

	private String DateOfMartyrdom;
	private String CausesOfDeath;
	private String PlaceOfDeath;

	public Martyr() {

	}

	public Martyr(String iD, String name, int age, String gender, String address, String contactInfo) {
		super(iD, name, age, gender, address, contactInfo);
	}

	public String getDateOfMartyrdom() {
		return DateOfMartyrdom;
	}

	public void setDateOfMartyrdom(String dateOfMartyrdom) {
		DateOfMartyrdom = dateOfMartyrdom;
	}

	public String getCausesOfDeath() {
		return CausesOfDeath;
	}

	public void setCausesOfDeath(String causesOfDeath) {
		CausesOfDeath = causesOfDeath;
	}

	public String getPlaceOfDeath() {
		return PlaceOfDeath;
	}

	public void setPlaceOfDeath(String placeOfDeath) {
		PlaceOfDeath = placeOfDeath;
	}

	@Override
	public String toString() {
		return "Martyr [DateOfMartyrdom=" + DateOfMartyrdom + ", CausesOfDeath=" + CausesOfDeath + ", PlaceOfDeath="
				+ PlaceOfDeath + "\n"+"ID=" + getID() + ", Name=" + getName() + ", Age=" + getAge() + ", Gender="
				+ getGender() + ", Address=" + getAddress() + ", Contactlnfo=" + getContactlnfo() + "]";
	}

}