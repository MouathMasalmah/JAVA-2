//Mouath sami masalmah,No:1220179, Dr.Fadi Khalil , Date: 3/12/2023 ,Lap 7
package The_War_On_Gaza;

import java.util.*;

public class Manager {
	private ArrayList<Family> families = new ArrayList<>();
	Family TheFamily;

	Manager() {

	}

	public ArrayList<Family> getFamilies() {
		return families;
	}

	public boolean addFamily(
			Family family) {/*
							 * After receiving the object of type Family, this method compares the object
							 * name with the rest of the family names. If the name of the object is similar
							 * to one of the names, it makes the value of the identifying counter equal to
							 * 1, which means that the family already exists.
							 */
		int count = 0;
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).getFamilyName().equals(family.getFamilyName()) == true) {
				count = 1;
			}
		}
		if (count != 1) {
			if (families.contains(null)) {//It checks whether there is a field in the arrayList that does not have a Null value and adds the family to this field
				for (int i = 0; i < families.size(); i++) {
					if (families.get(i) == null) {
						families.add(i,family);
						break;
					}
				}
			}else {
				families.add(family);
			}
			return true;
		} else {
			System.out.println("The family is already added");
			return false;
		}

	}

	public boolean updateFamilyName(String FamilyName, Family updatedFamily) {// This method accepts the modified family
																				// and replaces the old family with the
																				// same name
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).getFamilyName().equals(FamilyName)) {
				families.set(i, updatedFamily);
				return true;
			}
		}
		return false;
	}

	public boolean deleteFamily(String FamilyName) {// This method receives the family from the driver and checks
													// whether the name matches one of the family names and deletes the
													// family
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).getFamilyName().contains(FamilyName)) {
				families.remove(i);
				return true;
			}
		}
		return false;
	}

	public Family searchByName(String FamilyName) {//If the entered name matches one of the names in the family list, the details are printed
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).getFamilyName().equals(FamilyName)) {
				return families.get(i);
			}
		}
		return null;
	}

	public Person searchPersonByID(String personID) {//If the entered ID matches one of the any ID in the member list in the family list, the details are printed
		for (int i = 0; i < families.size(); i++) {
			for (int j = 0; j < families.get(i).getMembers().size(); j++) {
				if (families.get(i).getMembers().get(j).getID().equals(personID)) {
					return families.get(i).getMembers().get(j);
				}
			}
		}
		return null;
	}

	public int calculateTotalMartyrs() {//Each number of martyrs is collected through a variable definition, and the number of martyrs is added for each family
		int SumOfMartyrs = 0;
		for (int i = 0; i < families.size(); i++) {
			SumOfMartyrs += families.get(i).MartyrNumber;
		}
		return SumOfMartyrs;
	}

	public int calculateTotalOrphans() {//Each number of orphans is collected through the definition of a variable, and the number of orphans is added for each family
		int SumOfOrphans = 0;
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).MartyrsParents == 2) {
				SumOfOrphans = SumOfOrphans + (families.get(i).LivePersonNumber);
			}
		}
		return SumOfOrphans;
	}

	public int calculateTotalLivePersons() {//All the number of neighborhoods are collected through the definition of a variable and the number of neighborhoods is added for each family
		int SumOfLivePerson = 0;
		for (int i = 0; i < families.size(); i++) {

			SumOfLivePerson += families.get(i).LivePersonNumber;

		}
		return SumOfLivePerson;
	}

	public ArrayList<Integer> calculateFamilyStatistics(String familyName) {//The family name entered from the driver is searched for and the number of the living, the dead, and the orphans is printed

		ArrayList<Integer> FamilyStatistics = new ArrayList<>();
		int Orphans = 0;
		for (int i = 0; i < families.size(); i++) {

			if (families.get(i).getFamilyName().equals(familyName)) {
				TheFamily = families.get(i);
				break;
			}
		}
		if (TheFamily.MartyrsParents == 2) {
			Orphans = TheFamily.LivePersonNumber;
			FamilyStatistics.add(TheFamily.LivePersonNumber);
			FamilyStatistics.add(TheFamily.MartyrNumber);
			FamilyStatistics.add(Orphans);
		} else {
			Orphans = 0;
			FamilyStatistics.add(TheFamily.LivePersonNumber);
			FamilyStatistics.add(TheFamily.MartyrNumber);
			FamilyStatistics.add(Orphans);

		}

		return FamilyStatistics;
	}

	public ArrayList<Integer> calculateGlobalStatistics() {//The total of the living, the dead, and the orphans for all families is printed

		ArrayList<Integer> GlobalStatistics = new ArrayList<>();

		GlobalStatistics.add(0, calculateTotalLivePersons());
		GlobalStatistics.add(1, calculateTotalMartyrs());
		GlobalStatistics.add(2, calculateTotalOrphans());

		return GlobalStatistics;

	}

	@Override
	public String toString() {
		return "Manager [families=" + families + "]";
	}
}