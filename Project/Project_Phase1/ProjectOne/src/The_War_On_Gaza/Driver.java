//Mouath sami masalmah,No:1220179, Dr.Fadi Khalil , Date: 3/12/2023 ,Lap 7
package The_War_On_Gaza;

import java.util.*;

public class Driver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Manager manager = new Manager();
		Family family = new Family();
		System.out.println(
				" 1- Add a New Family \n 2- Update Family Details \n 3- Delete Family \n 4- Find a family \n 5- Find a Person "
						+ "\n 6- The Total of Martyrs Number \n 7- The Total of Orphans Number \n 8- The Total of Live Persons "
						+ "\n 9- The Family Statistics \n 10- The Global Statistics \n 11- Comparison if two families have the same number of martyrs \n 12- Exit ");
		System.out.println("Enter Your Choise:"); // Print options for the user to search for what he wants
		int Choise = scan.nextInt();

		while (Choise != 12) {
			switch (Choise) {
			case 1:                             // Add a family: He can add a family if it does not exist
				System.out.println("Enter the family name:");
				String name = scan.next();
				family = new Family(name);
				if (manager.searchByName(name) != null) {
					System.out.println("The family is already registered!");
					break;
				} else {
					System.out.println(" 1- Add Member to Family \n 2- Exit");
					System.out.println("Enter Your Choise:");
					Choise = scan.nextInt();
					while (Choise != 2) {  //Any number can enter the While loops except the number 2, because it adds the family and exits from the addition case
						switch (Choise) {
						case 1:
							System.out.println("Enter the person ID:");
							String ID = scan.next();
							while (manager.searchPersonByID(ID) != null) {  //You will not be able to add the ID if it has been used before 
								System.out.println("The ID value entered for another person");
								System.out.println("Enter the person ID:");
								ID = scan.next();
							}
							System.out.println("Enter the person NAME:");
							String Name = scan.next();

							System.out.println("Enter the person AGE:");
							int Age = scan.nextInt();

							System.out.println("Enter the person Gender [ M:Male,F:Female ]:");
							String Gender = scan.next();

							while (!Gender.equals("M") && !Gender.equals("F")) { //He will not be able to enter any value except one of the options
								System.out.println("Error!,Enter the person Gender [ M:Male,F:Female ]:");
								Gender = scan.next();
							}

							System.out.println("Enter the person Address:");
							String Address = scan.next();

							System.out.println("Enter the person Contactlnfo:");
							String Contactlnfo = scan.next();

							System.out.println(
									"Enter The role of the person in the family [ dad , mam , son , daughter ]:");
							String roleInFamily = scan.next();
							while (!roleInFamily.equals("son") && !roleInFamily.equals("dad") && Gender.equals("M")) {//If he is a male, he will not be able to choose a person's role in the family except as a father or son
								System.out.println("Enter a true role!");
								System.out.println("The Gender = M ");
								roleInFamily = scan.next();
							}
							while (!roleInFamily.equals("daughter") && !roleInFamily.equals("mam")
									&& Gender.equals("F")) {//If he is a female, he will not be able to choose a person's role in the family except as a mom or daughter
								System.out.println("Enter a true role!");
								System.out.println("The Gender = F ");
								roleInFamily = scan.next();
							}

							System.out.println(" 1- Live Person \n 2- Martyr Person");//Determine the type of member: whether he is alive or a martyr
							System.out.println("Enter Your Choise:");
							Choise = scan.nextInt();
							while (Choise != 1 && Choise != 2) { 
								System.out.println("Erorr,enter a true value!");
								System.out.println(" 1- Live Person \n 2- Martyr Person");
								System.out.println("Enter Your Choise:");
								Choise = scan.nextInt();
							}
							if (Choise == 2) {//If he was a martyr, additional information related to his martyrdom is added

								System.out.println("Enter the Date Of Martyrdom:");
								String DateOfMartyrdom = scan.next();

								System.out.println("Enter the CausesOfDeath:");
								String CausesOfDeath = scan.next();

								System.out.println("Enter the PlaceOfDeath:");
								String PlaceOfDeath = scan.next();

								Martyr Martyrperson = new Martyr(ID, Name, Age, Gender, Address, Contactlnfo);
								Martyrperson.setDateOfMartyrdom(DateOfMartyrdom);
								Martyrperson.setCausesOfDeath(CausesOfDeath);
								Martyrperson.setPlaceOfDeath(PlaceOfDeath);

								family.addMember(Martyrperson, roleInFamily, true);

							} else if (Choise == 1) {
								LivePerson lifePerson = new LivePerson(ID, Name, Age, Gender, Address, Contactlnfo);
								family.addMember(lifePerson, roleInFamily, false);

							}

							break;

						default:
							System.out.println("Enter a True Number!");
						}
						System.out.println(" 1- Add Member to Family \n 2- Exit");
						System.out.println("Enter Your Choise:");
						Choise = scan.nextInt();
					}
					if (Choise == 2) {//When you enter the number, the family is added to the family list using : [manager.addFamily(family);]
						manager.addFamily(family);
						System.out.println("operation accomplished successfully \n");
					}

					break;
				}

			case 2:
				System.out.println("Enter the family name:");
				String FamilyName = scan.next();
				if (manager.searchByName(FamilyName) == null) {//The family name entered by the user is compared with the rest of the family names: if a family with the same name is found, it is modified.
					System.out.println("Sorry!,We didn't find the family in the system");
				} else {
					family = manager.searchByName(FamilyName);//Give the object the value of the family that was found

					System.out.println(" 1- Add Member to Family \n 2- Remove Member from the Family \n 3- Exit");
					System.out.println("Enter Your Choise:");
					Choise = scan.nextInt();
					while (Choise != 3) {//Any number can enter the While loops except the number 3, because it exits from the update case
						switch (Choise) {
						case 1:
							System.out.println("Enter the person ID:");
							String ID = scan.next();
							while (manager.searchPersonByID(ID) != null) {
								System.out.println("The ID value entered for another person");
								System.out.println("Enter the person ID:");
								ID = scan.next();
							}
							System.out.println("Enter the person NAME:");
							String Name = scan.next();

							System.out.println("Enter the person AGE:");
							int Age = scan.nextInt();

							System.out.println("Enter the person Gender [ M:Male,F:Female ]:");
							String Gender = scan.next();

							while (!Gender.equals("M") && !Gender.equals("F")) {
								System.out.println("Error!,Enter the person Gender [ M:Male,F:Female ]:");
								Gender = scan.next();
							}

							System.out.println("Enter the person Address:");
							String Address = scan.next();

							System.out.println("Enter the person Contactlnfo:");
							String Contactlnfo = scan.next();

							System.out.println(
									"Enter The role of the person in the family [ dad , mam , son , daughter ]:");
							String roleInFamily = scan.next();
							while (!roleInFamily.equals("son") && !roleInFamily.equals("dad") && Gender.equals("M")) {
								System.out.println("Enter a true role!");
								System.out.println("The Gender = M ");
								roleInFamily = scan.next();
							}
							while (!roleInFamily.equals("daughter") && !roleInFamily.equals("mam")
									&& Gender.equals("F")) {
								System.out.println("Enter a true role!");
								System.out.println("The Gender = F ");
								roleInFamily = scan.next();
							}
							System.out.println(" 1- Live Person \n 2- Martyr Person");
							System.out.println("Enter Your Choise:");
							Choise = scan.nextInt();
							while (Choise != 1 && Choise != 2) {
								System.out.println("Erorr,enter a true value!");
								System.out.println(" 1- Live Person \n 2- Martyr Person");
								System.out.println("Enter Your Choise:");
								Choise = scan.nextInt();
							}
							if (Choise == 2) {

								System.out.println("Enter the Date Of Martyrdom:");
								String DateOfMartyrdom = scan.next();

								System.out.println("Enter the CausesOfDeath:");
								String CausesOfDeath = scan.next();

								System.out.println("Enter the PlaceOfDeath:");
								String PlaceOfDeath = scan.next();

								Martyr Martyrperson = new Martyr(ID, Name, Age, Gender, Address, Contactlnfo);
								Martyrperson.setDateOfMartyrdom(DateOfMartyrdom);
								Martyrperson.setCausesOfDeath(CausesOfDeath);
								Martyrperson.setPlaceOfDeath(PlaceOfDeath);

								family.addMember(Martyrperson, roleInFamily, true);
								manager.updateFamilyName(FamilyName, family);

							} else if (Choise == 1) {
								LivePerson lifePerson = new LivePerson(ID, Name, Age, Gender, Address, Contactlnfo);
								family.addMember(lifePerson, roleInFamily, false);
								manager.updateFamilyName(FamilyName, family); //After adding the person, the family data is updated by sending the family name and the modified package: [manager.updateFamilyName(FamilyName, family);]
							}

							break;
						case 2:

							System.out.println("Enter the person ID:");
							ID = scan.next();
							if (manager.searchPersonByID(ID) == null) {
								System.out.println("Sorry We didn't find the person :( ,Try Again!");

							} else if (manager.searchPersonByID(ID) instanceof Martyr) {//We look to see if the person is a martyr, so we return True, or if he is a living person, so we return False
								family.removeMember(manager.searchPersonByID(ID), true);
								System.out.println("The deletion was completed successfully");
								manager.updateFamilyName(FamilyName, family);//After deleting the person, the family data is updated by sending the family name and the modified package: [manager.updateFamilyName(FamilyName, family);]
							} else {
								family.removeMember(manager.searchPersonByID(ID), false);
								System.out.println("The deletion was completed successfully");
								manager.updateFamilyName(FamilyName, family);
							}
							break;
						default:
							System.out.println("Enter a True Number!");
						}
						System.out.println(" 1- Add Member to Family \n 2- Remove Member from the Family \n 3- Exit");
						System.out.println("Enter Your Choise:");
						Choise = scan.nextInt();
					}
					if (Choise == 3) {
						System.out.println("operation accomplished successfully \n");
					}

					break;
				}
				break;
			case 3: //Case 3: Responsible for deleting the family whose name the user enters
				System.out.println("Enter the family name:");
				FamilyName = scan.next();
				family = manager.searchByName(FamilyName);
				if (manager.deleteFamily(FamilyName) == true) {
					System.out.println("operation accomplished successfully \n");
				} else {
					System.out.println("Sorry We didn't find the family :( ,Try Again!");
				}
				break;
			case 4://Case 4: Responsible for search for the family whose name the user enters and print all details
				System.out.println("Enter the family name:");
				FamilyName = scan.next();
				family = manager.searchByName(FamilyName);
				if (family == null) {
					System.out.println("Sorry We didn't find the family :( ,Try Again!");
				} else {
					System.out.println(family.toString());
				}

				break;
			case 5://Case 5: Responsible for search for the person whose ID the user enters and print all details
				System.out.println("Enter the person ID:");
				String id = scan.next();

				if (manager.searchPersonByID(id) == null) {
					System.out.println("Sorry We didn't find the person :( ,Try Again!");
				} else {
					System.out.println(manager.searchPersonByID(id).toString());
					System.out.println("operation accomplished successfully \n");
				}
				break;
			case 6://Case 6: Responsible for  print the total martyrs number 
				System.out.println("The total martyrs: " + manager.calculateTotalMartyrs() + "\n");
				break;
			case 7://Case 7: Responsible for  print the total orphans number 
				System.out.println("The total orphans: " + manager.calculateTotalOrphans() + "\n");
				break;
			case 8://Case 8: Responsible for  print the total lives number 
				System.out.println("The total live person: " + manager.calculateTotalLivePersons() + "\n");
				break;
			case 9://Case 9: Responsible for  print all statistics for the family who is the user enter her name
				System.out.println("Enter the family name:");
				FamilyName = scan.next();
				family = manager.searchByName(FamilyName);
				if (family == null) {
					System.out.println("Sorry We didn't find the family :( ,Try Again! \n");
				} else {
					System.out.println("The Family statistics: \n" + "Total Lives = "
							+ manager.calculateFamilyStatistics(FamilyName).get(0) + "\n" + "Total Martyrs = "
							+ manager.calculateFamilyStatistics(FamilyName).get(1) + "\n" + "Total Orphans = "
							+ manager.calculateFamilyStatistics(FamilyName).get(2));
				}
				break;
			case 10://Case 10: Responsible for  print the global statistics
				System.out.println(
						"The global statistics: \n" + "Total Lives = " + manager.calculateGlobalStatistics().get(0)
								+ "\n" + "Total Martyrs = " + manager.calculateGlobalStatistics().get(1) + "\n"
								+ "Total Orphans = " + manager.calculateGlobalStatistics().get(2));
				break;
			case 11://case 11:User enters the names of two families into the system, and the method: [family.equal(Family);] compares whether the number of martyrs is equal or not.
				System.out.println("Enter the family name:");
				FamilyName = scan.next();
				family = manager.searchByName(FamilyName);
				if (family == null) {
					System.out.println("Sorry We didn't find the family :( ,Try Again! \n");
				} else {
					System.out.println("Enter the family name:");
					String FamilyName2 = scan.next();
					Family Family = manager.searchByName(FamilyName2);
					if (Family == null) {
						System.out.println("Sorry We didn't find the family :( ,Try Again! \n");
					} else {
						family.equal(Family);
					}
				}
				break;
			default:
				System.out.println("Enter a true value! \n");
			}
			System.out.println(
					" 1- Add a New Family \n 2- Update Family Details \n 3- Delete Family \n 4- Find a family \n 5- Find a Person "
							+ "\n 6- The Total of Martyrs Number \n 7- The Total of Orphans Number \n 8- The Total of Live Persons "
							+ "\n 9- The Family Statistics \n 10- The Global Statistics \n 11- Comparison if two families have the same number of martyrs \n 12- Exit ");
			System.out.println("Enter Your Choise:");
			Choise = scan.nextInt();
		}
		if (Choise == 12)

		{
			System.out.println("Thank You For Vist My Program");
		}
	}
}