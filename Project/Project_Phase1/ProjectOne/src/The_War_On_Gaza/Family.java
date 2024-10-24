//Mouath sami masalmah,No:1220179, Dr.Fadi Khalil , Date: 3/12/2023 ,Lap 7
package The_War_On_Gaza;

import java.util.*;

public class Family extends Manager {
	private String FamilyName;
	private ArrayList<Person> members = new ArrayList<>();
	private ArrayList<Person> parents = new ArrayList<>();
	int MartyrNumber;
	int LivePersonNumber;
	int MartyrsParents;
	int Dad;
	int Mam;

	public Family() {

	}

	public Family(String familyName) {
		super();
		FamilyName = familyName;
	}

	public int getMartyrNumber() {
		return MartyrNumber;
	}

	public void setMartyrNumber(int martyrNumber) {
		MartyrNumber = martyrNumber;
	}

	public boolean addMember(Person member, String roleInFamily, boolean IsMartyr) {
		if (roleInFamily.equals("son") || roleInFamily.equals("daughter")) {
			if (!(members.contains(member)) && IsMartyr == true) {// If the person is not in the family and [ IsMartyr =
																	// True ], the person will be added to the family
																	// and 1 is added to the variable [MartyrNumber].
				members.add(member);
				MartyrNumber++;
				return true;
			} else if (!(members.contains(member)) && IsMartyr == false) {// If the person is not in the family and [
																			// IsMartyr = False ], the person will be
																			// added to the family and 1 is added to the
																			// variable [LivePersonNumber].
				members.add(member);
				LivePersonNumber++;
				return true;
			} else {
				System.out.println("The person is already registered in member list!");
				return false;
			}
		} else if (roleInFamily.equals("dad") && Dad <= 1) {// If the person has a father or mother, he will be added to
															// the parents as well
			if (!(members.contains(member)) && IsMartyr == true) {
				Dad++;
				members.add(member);
				parents.add(member);
				MartyrNumber++;
				return true;
			} else if (!(members.contains(member)) && IsMartyr == false) {
				Dad++;
				members.add(member);
				parents.add(member);
				LivePersonNumber++;
				return true;
			} else {
				System.out.println("The person is already registered in member list!");
				return false;
			}
		} else if (roleInFamily.equals("mom") && Mam <= 1) {

			if (!(members.contains(member)) && IsMartyr == true) {
				Mam++;
				members.add(member);
				parents.add(member);
				MartyrNumber++;
				return true;
			} else if (!(members.contains(member)) && IsMartyr == false) {
				Mam++;
				members.add(member);
				parents.add(member);
				LivePersonNumber++;
				return true;
			} else {
				System.out.println("The person is already registered in member list!");
				return false;
			}
		} else {
			System.out.println("The added person can't be a family member !");
			return false;
		}
	}

	public boolean removeMember(Person member, boolean IsMartyr) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).equals(member)) {
				if (IsMartyr == true) {                              // If the person is in the family and [
					if(parents.contains(member)) {                   // IsMartyr = True ], the person will be
					                                                 // Removal 1 from the family and 1 is Removal from the
				                                                     // variable [MartyrNumber].
					MartyrNumber--;
					removeParent(member, IsMartyr);
                    members.remove(i);
                    return true;
				}else {
					members.remove(i);
					MartyrNumber--;
					return true;
					}
				}
					else if (IsMartyr == false) {// If the person is in the family and [
						if(parents.contains(member)) { // IsMartyr = False ], the person will be
	                                           // Removal 1 from the family and 1 is Removal from the
                                               // variable [LivePersonNumber].
					removeParent(member, IsMartyr);
					LivePersonNumber--;
					members.remove(i);
					return true;
				}else {
					members.remove(i);
					LivePersonNumber--;
					return true;
				}
				
			}
		}}
		return false;
	}

	public ArrayList<Person> getMembers() {
		return members;

	}

	public String getFamilyName() {
		return FamilyName;

	}

	public void setFamilyName(String familyName) {
		this.FamilyName = familyName;
	}

	public void addParent(Person parent, boolean IsMartyr) {
		if (parents.size() <= 2) {
			if (!(parents.contains(parent)) && IsMartyr == true) {// If the person is not in the family and [ IsMartyr
																	// =True ],the person will be added to the parents
																	// array and 1 is added to the variable
																	// [MartyrsParents].
				MartyrsParents++;
				parents.add(parent);
			} else if (!(parents.contains(parent)) && IsMartyr == false) {// If the person is not in the family and [
																			// IsMartyr =False],
																			// the person will be added to the parents
																			// array

				parents.add(parent);
			} else {
				System.out.println("The Name is already registered in the parent list!");
			}
		} else {// If the number of people is in the number parent list, no more person will be
				// added

			System.out.println("The list is full");
		}
	}

	public boolean removeParent(Person parent, boolean IsMartyr) {
		for (int i = 0; i < parents.size(); i++) {

			if (parents.get(i).equals(parent)) {
				parents.remove(i);
				if (IsMartyr == true) {
					MartyrsParents--;
				}
				

				
					return true;
			}
		}

		return false;

	}

	public ArrayList<Person> getParents() {
		return parents;
	}

	@Override
	public String toString() {
		return "Family \n[FamilyName=" + FamilyName + ",\n members=" + members + ",\n parents=" + parents
				+ ",\n MartyrNumber=" + MartyrNumber + "]";
	}

	public boolean equal(Object obj) {
		if (obj instanceof Family) {
			Family F = (Family) obj;
			if (F.MartyrNumber == MartyrNumber) {
				System.out.println("The number of martyrs of the family you entered:" + F.toString()
						+ "\nEqual to the number of other families:" + toString());
				return true;
			} else {
				System.out.println("The number of martyrs is unequal!");
				return false;
			}
		} else {
			System.out.println("No family was entered for comparison");
			return false;
		}

	}
}