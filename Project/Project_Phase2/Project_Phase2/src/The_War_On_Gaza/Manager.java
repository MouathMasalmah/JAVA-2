//Mouath sami masalmah,No:1220179, Dr.Fadi Khalil , Date: 3/12/2023 ,Lap 7
package The_War_On_Gaza;

import java.util.*;

public class Manager implements Sortable {
	private ArrayList<Family> families = new ArrayList<>();
	private ArrayList<Family> CopyFamilies = new ArrayList<>();
	private ArrayList<Martyr> CopyMartyr = new ArrayList<>();
	private ArrayList<LivePerson> CopyLivePerson = new ArrayList<>();
	Family TheFamily;

	Manager() {

	}

	public ArrayList<Family> getFamilies() {
		return families;
	}

	public boolean CopyFamily(Family CopyFamily) {

		return this.CopyFamilies.add(CopyFamily);

	}

	public boolean CopyMartyr(Martyr CopyMartyr) {
		return this.CopyMartyr.add(CopyMartyr);
	}

	public boolean CopyLivePerson(LivePerson CopyLivePerson) {
		return this.CopyLivePerson.add(CopyLivePerson);
	}

	public ArrayList<Family> getCopyFamilies() {
		return CopyFamilies;
	}

	public ArrayList<Martyr> getCopyMartyr() {
		return CopyMartyr;
	}

	public ArrayList<LivePerson> getCopyLivePerson() {
		return CopyLivePerson;
	}

	public boolean addFamily(
			Family family) throws CustomException {/*
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
			if (families.contains(null)) {// It checks whether there is a field in the arrayList that does not have a
											// Null value and adds the family to this field
				for (int i = 0; i < families.size(); i++) {
					if (families.get(i) == null) {
						families.add(i, family);
						break;
					}
				}
			} else {
				families.add(family);
			}
			return true;
		} else {
			throw new CustomException("The family is already added");
			
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

	public Family searchByName(String FamilyName) {// If the entered name matches one of the names in the family list,
													// the details are printed
		for (int i = 0; i < families.size(); i++) {
			if (families.get(i).getFamilyName().equals(FamilyName)) {
				return families.get(i);
			}
		}
		return null;
	}

	public Person searchPersonByID(String personID) {// If the entered ID matches one of the any ID in the member list
														// in the family list, the details are printed
		for (int i = 0; i < families.size(); i++) {
			for (int j = 0; j < families.get(i).getMembers().size(); j++) {
				
				if (families.get(i).getMembers().get(j).getID().equals(personID)) {
					return families.get(i).getMembers().get(j);
				}
			}
		}
		return null;
	}

	public int calculateTotalMartyrs() {// Each number of martyrs is collected through a variable definition, and the
										// number of martyrs is added for each family
		int SumOfMartyrs = 0;
		for (int i = 0; i < families.size(); i++) {
			SumOfMartyrs += families.get(i).MartyrNumber;
		}
		return SumOfMartyrs;
	}

	public int calculateTotalOrphans() {// Each number of orphans is collected through the definition of a variable, and
										// the number of orphans is added for each family
		int SumOfOrphans = 0;
		for (int i = 0; i < families.size(); i++) {
			families.get(i).getOrphans();
			SumOfOrphans = SumOfOrphans + (families.get(i).getOrphans());
		}

		return SumOfOrphans;
	}

	public int calculateTotalLivePersons() {// All the number of neighborhoods are collected through the definition of a
											// variable and the number of neighborhoods is added for each family
		int SumOfLivePerson = 0;
		for (int i = 0; i < families.size(); i++) {

			SumOfLivePerson += families.get(i).LivePersonNumber;

		}
		return SumOfLivePerson;
	}

	public ArrayList<Integer> calculateFamilyStatistics(String familyName) {// The family name entered from the driver
																			// is searched for and the number of the
																			// living, the dead, and the orphans is
																			// printed

		ArrayList<Integer> FamilyStatistics = new ArrayList<>();

		for (int i = 0; i < families.size(); i++) {

			if (families.get(i).getFamilyName().equals(familyName)) {
				TheFamily = families.get(i);
				break;
			}
		}
		

		FamilyStatistics.add(TheFamily.LivePersonNumber);
		FamilyStatistics.add(TheFamily.MartyrNumber);
		FamilyStatistics.add(TheFamily.getOrphans());

		return FamilyStatistics;
	}

	public ArrayList<Integer> calculateGlobalStatistics() {// The total of the living, the dead, and the orphans for all
															// families is printed

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

	@Override
	public ArrayList<Family> sortByMartyrs(ArrayList<Family> families) throws CloneNotSupportedException {//An override was made for the Method from the Interface Sortable class and the Method arrange the families in Are Not New according to the number of martyrs in descending order.

		ArrayList<Family> familiesByMartyr = new ArrayList<>();

		familiesByMartyr = (ArrayList<Family>) families.clone();//The family ArrayList was copied using the clone method
		int Max = 0;

		for (int i = 0; i < familiesByMartyr.size() - 1; i++) {
			Max = i;

			for (int j = i + 1; j < familiesByMartyr.size(); j++) {

				if (familiesByMartyr.get(i).MartyrNumber < familiesByMartyr.get(j).MartyrNumber) {
					Max = j;

				}
			}
			Family Sort = familiesByMartyr.get(Max);
			familiesByMartyr.set(Max, familiesByMartyr.get(i));
			familiesByMartyr.set(i, Sort);

		}

		return familiesByMartyr;
	}

	@Override
	public ArrayList<Family> sortByOrphans(ArrayList<Family> families) throws CloneNotSupportedException {//An override was made for the Method from the Interface Sortable class and the Method arrange the families in Are Not New according to the number of orphans in descending order.

		ArrayList<Family> familiesByOrphans = new ArrayList<>();

		familiesByOrphans = (ArrayList<Family>) families.clone();//The family ArrayList was copied using the clone method
		int Max = 0;

		for (int i = 0; i < familiesByOrphans.size() - 1; i++) {
			
			int Orphans = familiesByOrphans.get(i).getOrphans();
			Max = i;
			for (int j = i + 1; j < familiesByOrphans.size(); j++) {
				
				int Orphans2 = familiesByOrphans.get(j).getOrphans();
				if (Orphans < Orphans2) {
					Max = j;

				}
			}

			Family Sort = familiesByOrphans.get(Max);
			familiesByOrphans.set(Max, familiesByOrphans.get(i));
			familiesByOrphans.set(i, Sort);
		}
		return familiesByOrphans;
	}
}