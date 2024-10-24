//Mouath sami masalmah,No:1220179, Dr.Fadi Khalil , Date: 3/12/2023 ,Lap 7
package The_War_On_Gaza;

import java.util.*;
import java.io.*;
import java.security.PublicKey;

public class Driver {
	static boolean test;
	static Manager manager = new Manager();

	public static void main(String[] args) throws CustomException {
		Scanner scan = new Scanner(System.in);

		Family family = new Family();
		boolean TrueFileValue = true;

		while (TrueFileValue) {
			try {
				System.out.println("Do you want to import previously stored data from the family registry?\r\n"
						+ "1- Yes\r\n" + "2- No");

				int FileChiose = scan.nextInt();
				while (FileChiose != 1 && FileChiose != 2) {

					System.out.println("Enter a TRUE Value!");
					FileChiose = scan.nextInt();
				}
				if (FileChiose == 1) {
					test = InputDataFileOnArrayList();
					if (test == true) {
						System.out.println("operation accomplished successfully \n");
					}
				}
				TrueFileValue = false;
				boolean TrueInput = true;
				while (TrueInput) {
					System.out.println(
							" 1- Add a New Family \n 2- Update Family Details \n 3- Delete Family \n 4- Find a family \n 5- Find a Person "
									+ "\n 6- The Total of Martyrs Number \n 7- The Total of Orphans Number \n 8- The Total of Live Persons "
									+ "\n 9- The Family Statistics \n 10- The Global Statistics \n 11- Comparison if two families have the same number of martyrs "
									+ "\n 12- Ranking of families according to the number of martyrs \n 13- Ranking of families according to the number of orphans "
									+ "\n 14- Copy an object of type Martyr \n 15- Copy an object of type LivePerson \n 16- Copy an object of type Family \n 17- Exit ");
					System.out.println("Enter Your Choise:"); // Print options for the user to search for what he wants
					try {
						int Choise = scan.nextInt();
						while (Choise != 17) {
							switch (Choise) {
							case 1: // Add a family: He can add a family if it does not exist
								boolean TrueInput1 = true;
								while (TrueInput1) {
									try {
										System.out.println("Enter the family name:");
										String name = scan.next();
										family = new Family(name);
										if (manager.searchByName(name) != null) {
											throw new CustomException(family);

										} else if (family.getFamilyName().length() < 3) {
											throw new CustomException("Enter a TRUE name!");
										} else {
											int count = 0;
											for (int i = 0; i < family.getFamilyName().length(); i++) {
												if (Character.isDigit(family.getFamilyName().charAt(i))) {
													count++;
													break;
												}
											}
											if (count > 0) {
												throw new CustomException("Enter a TRUE name!");

											}
										}

										boolean TrueInput3 = true;
										while (TrueInput3) {
											System.out.println(" 1- Add Member to Family \n 2- Exit");
											System.out.println("Enter Your Choise:");
											try {
												Choise = scan.nextInt();

												while (Choise != 2) { /*
																		 * Any number can enter the While loops except
																		 * the number 2, because it adds the family and
																		 * exits from the addition case
																		 */
													switch (Choise) {
													case 1:
														try {
															family = (Family) AddPersonToFamily(name, family).clone();
														} catch (CloneNotSupportedException e) {
															e.printStackTrace();
														}
														break;
													default:
														System.out.println("Enter a True Number!");
													}
													System.out.println(" 1- Add Member to Family \n 2- Exit");
													System.out.println("Enter Your Choise:");
													Choise = scan.nextInt();
												}
												if (Choise == 2) {/*
																	 * When you enter the number, the family is added to
																	 * the family list using :
																	 * [manager.addFamily(family);]
																	 */
													manager.addFamily(family);
													System.out.println("operation accomplished successfully \n");
													TrueInput3 = false;
												}
											} catch (InputMismatchException e) {
												System.out.println("Enter a NUMBER!");
												scan.nextLine();
											}
										}

										TrueInput1 = false;

									} catch (InputMismatchException e) {
										System.out.println("Enter a NUMBER!");
										scan.nextLine();
									} catch (CustomException e) {
										e.printStackTrace();
										scan.nextLine();
									}
								}
								break;
							case 2:

								System.out.println("Enter the family name:");
								String FamilyName = scan.next();
								if (manager.searchByName(
										FamilyName) == null) {/*
																 * The family name entered by the user is compared with
																 * the rest of the family names: if a family with the
																 * same name is found, it is modified.
																 */
									System.out.println("Sorry!,We didn't find the family in the system");
								} else {
									family = manager.searchByName(FamilyName);// Give the object the value of the family
																				// that
									TrueInput = true; // was found
									while (TrueInput == true) {
										System.out.println(" 1- Add Member to Family \n 2- Remove Member from the Family \n 3- Exit");
										System.out.println("Enter Your Choise:");
										try {
											 Choise = scan.nextInt();
											Family family2=new Family();
											while (Choise != 3) {/*
																	 * Any number can enter the While loops except the
																	 * number 3, because it exits from the update case
																	 */
												switch (Choise) {
												case 1:
													try {
														 family2 = (Family) AddPersonToFamily(FamilyName, family).clone();
														 TrueInput=false;
													} catch (CloneNotSupportedException e) {
														e.printStackTrace();
													}
													break;
												case 2:
													boolean TrueInput7 = true;
													do {
														try {
															
															System.out.println("Enter the person ID:");
															String ID = scan.next();
															int count=0;
															for(int i=0;i<family.getMembers().size();i++) {
																
																if(family.getMembers().get(i).getID().equals(ID)) {
																	count++;	
																	break;
																}
															}
															if(count==0) {
																throw new CustomException("The person is not a family member!");
															}
															Person person = manager.searchPersonByID(ID);
															if (person.getRoleInFamily().equals("dad")
																	|| person.getRoleInFamily().equals("mom")) {
																try {
																	
																	
																	boolean trueValue = true;
																	while (trueValue) {
																		System.out.println(
																				"The person you want is one of the parents and it is forbidden to have a family without both parents. "
																						+ "\n"
																						+ "Choose one of the following options:");
																		System.out.println(
																				"1- Undo the deletion process\r\n"
																						+ "2- Delete the family\r\n"
																						+ "3- Add a father or mother instead of the one you want to delete");
																		try {
																			int choise = scan.nextInt();
																			while (choise != 1) {
																				if (choise == 2) {

																					manager.deleteFamily(
																							family.getFamilyName());
																					System.out.println(
																							"The Family deletion was completed successfully");
																					
																					TrueInput7=false;
																					TrueInput=false;
																					Choise=3;
																					break;
																				} else if (choise == 3) {

																					if (manager.searchPersonByID(
																							ID) instanceof Martyr) {/*
																													 * We look to see if the person is a martyr, so we
																													 * return True, or if he is a living person, so we return False
																													 */
																						family.removeMember(manager
																								.searchPersonByID(ID),
																								true);
																						System.out.println(
																								"The deletion was completed successfully");
																						family2 = (Family) AddPersonToFamily(
																								FamilyName, family)
																								.clone();
																						manager.updateFamilyName(
																								FamilyName,
																								family2);   /*
																											 * After deleting the person, the family data is updated by sending the family name and
																											 * the modified package:[manager.updateFamilyName(FamilyName, family);]
																											 */
																						break;
																					} else {
																						family.removeMember(manager
																								.searchPersonByID(ID),
																								false);
																						System.out.println(
																								"The deletion was completed successfully");
																						family2 = (Family) AddPersonToFamily(
																								FamilyName, family)
																								.clone();
																						manager.updateFamilyName(
																								FamilyName, family2);
																						break;
																					}
																				} else {
																					System.out.println(
																							"Enter a number of options!");
																				}
																			}
																			if (choise == 1) {
																				System.out.println(
																						"The operation was canceled successfully!");
																			}
																			trueValue = false;
																			
																		} catch (InputMismatchException e) {
																			e.printStackTrace();
																			scan.nextLine();
																		} catch (CloneNotSupportedException e) {
																			e.printStackTrace();
																		}
																	}
																	TrueInput7 = false;
																} catch (InputMismatchException e) {
																	e.printStackTrace();
																	scan.nextLine();
																} 
															} else {
																if (manager.searchPersonByID(
																		ID) instanceof Martyr) {/*
																								 * We look to see if the person is a martyr,
																								 * so we return True, or if he is a living
																								 * person, so we return False
																								 */
																	family.removeMember(manager.searchPersonByID(ID),
																			true);
																	System.out.println(
																			"The deletion was completed successfully");

																	manager.updateFamilyName(FamilyName,
																			family);/*
																					 * After deleting the person, the family data is updated by sending
																					 * the family name and the modified package:[manager.updateFamilyName( FamilyName, family);]
																					 */
																	TrueInput7 = false;
																} else {
																	family.removeMember(manager.searchPersonByID(ID),
																			false);
																	System.out.println(
																			"The deletion was completed successfully");

																	manager.updateFamilyName(FamilyName, family);
																	TrueInput7 = false;
																}
															}
															TrueInput7 = false;
														} catch (CustomException e) {
															e.printStackTrace();
														}
														
													    
													}while (TrueInput7);
													
													
													break;
												default:
													throw new CustomException("Enter a True Number!");
												}
												
												if(Choise==3) {
													break;
												}
												System.out.println(
														" 1- Add Member to Family \n 2- Remove Member from the Family \n 3- Exit");
												System.out.println("Enter Your Choise:");
												Choise = scan.nextInt();
												
											}
											if (Choise == 3) {
												System.out.println("operation accomplished successfully \n");
											}
											TrueInput=false;
										} catch (InputMismatchException e) {
											e.printStackTrace();
											scan.nextLine();
										} catch (CustomException e) {
											e.printStackTrace();
										}
										if(!TrueInput) {
											break;
										}
									}

									break;
								}
								break;
							case 3: // Case 3: Responsible for deleting the family whose name the user enters
								try {

									System.out.println("Enter the family name:");
									FamilyName = scan.next();
									family = manager.searchByName(FamilyName);
									if (manager.deleteFamily(FamilyName) == true) {
										System.out.println("operation accomplished successfully \n");
									} else {
										throw new CustomException("Sorry We didn't find the family :( ,Try Again!");

									}
								} catch (CustomException e) {
									System.out.println(e.toString());
								}
								break;
							case 4:// Case 4: Responsible for search for the family whose name the user enters and
									// print all details
								try {
									System.out.println("Enter the family name:");
									FamilyName = scan.next();
									family = manager.searchByName(FamilyName);
									if (family == null) {
										throw new CustomException("Sorry We didn't find the family :( ,Try Again!");
									} else {
										System.out.println(family.toString());
									}
								} catch (CustomException e) {
									System.out.println(e.toString());
								}

								break;
							case 5:// Case 5: Responsible for search for the person whose ID the user enters and
									// print all details
								try {
									System.out.println("Enter the person ID:");
									String id = scan.next();

									if (manager.searchPersonByID(id) == null) {
										throw new CustomException("Sorry We didn't find the person :( ,Try Again!");
									} else {
										System.out.println(manager.searchPersonByID(id).toString());
										System.out.println("operation accomplished successfully \n");
									}
								} catch (CustomException e) {
									System.out.println(e.toString());
								}
								break;
							case 6:// Case 6: Responsible for print the total martyrs number
								System.out.println("The total martyrs: " + manager.calculateTotalMartyrs() + "\n");
								break;
							case 7:// Case 7: Responsible for print the total orphans number
								System.out.println("The total orphans: " + manager.calculateTotalOrphans() + "\n");
								break;
							case 8:// Case 8: Responsible for print the total lives number
								System.out.println(
										"The total live person: " + manager.calculateTotalLivePersons() + "\n");
								break;
							case 9:// Case 9: Responsible for print all statistics for the family who is the user
									// enter her name
								System.out.println("Enter the family name:");
								try {
									FamilyName = scan.next();
									family = manager.searchByName(FamilyName);
									if (family == null) {
										throw new CustomException("Sorry We didn't find the family :( ,Try Again! \n");
									} else {
										System.out.println("The Family statistics: \n" + "Total Lives = "
												+ manager.calculateFamilyStatistics(FamilyName).get(0) + "\n"
												+ "Total Martyrs = "
												+ manager.calculateFamilyStatistics(FamilyName).get(1) + "\n"
												+ "Total Orphans = "
												+ manager.calculateFamilyStatistics(FamilyName).get(2));
									}
								} catch (CustomException e) {
									System.out.println(e.toString());
								}
								break;
							case 10:// Case 10: Responsible for print the global statistics
								System.out.println("The global statistics: \n" + "Total Lives = "
										+ manager.calculateGlobalStatistics().get(0) + "\n" + "Total Martyrs = "
										+ manager.calculateGlobalStatistics().get(1) + "\n" + "Total Orphans = "
										+ manager.calculateGlobalStatistics().get(2));
								break;
							case 11:// case 11:User enters the names of two families into the system, and the
									// method: [family.equal(Family);] compares whether the number of martyrs is
									// equal or not.
								System.out.println("Enter the family name:");
								try {
									FamilyName = scan.next();
									family = manager.searchByName(FamilyName);
									if (family == null) {
										throw new CustomException("Sorry We didn't find the family :( ,Try Again! \n");
									} else {
										System.out.println("Enter the family name:");
										String FamilyName2 = scan.next();
										Family Family = manager.searchByName(FamilyName2);
										if (Family == null) {
											throw new CustomException(
													"Sorry We didn't find the family :( ,Try Again! \n");
										} else {
											family.equals(Family);
										}
									}
								} catch (CustomException e) {
									System.out.println(e.toString());
								}

								break;
							case 12:
								try {
									ArrayList<Family> Martyrs=new ArrayList<>();
									Martyrs=(ArrayList<Family>) manager.sortByMartyrs(manager.getFamilies()).clone();
									for(int i =0;i<Martyrs.size();i++) {
									System.out.println(Martyrs.get(i).getFamilyName() + " , Martyr Number: "+Martyrs.get(i).MartyrNumber);
									}
									
								} catch (CloneNotSupportedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
							case 13:
								try {
									ArrayList<Family> Orphans=new ArrayList<>();
									Orphans=(ArrayList<Family>) manager.sortByOrphans(manager.getFamilies()).clone();
									for(int i =0;i<Orphans.size();i++) {
										
									System.out.println(Orphans.get(i).getFamilyName() + " The Number of Orphans: "+Orphans.get(i).getOrphans());
									}} catch (CloneNotSupportedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
							case 14:
								System.out.println("Enter the Person ID:");
								try {
									String ID = scan.next();
									Martyr martyr = new Martyr();
									if(manager.searchPersonByID(ID)!=null) {
									if (manager.searchPersonByID(ID) instanceof Martyr
											) {
										martyr = (Martyr) manager.searchPersonByID(ID);
										Martyr CopyMartyr = new Martyr();
										CopyMartyr = (Martyr) martyr.clone();
										manager.CopyMartyr(CopyMartyr);
										System.out.println(manager.getCopyMartyr().toString());
										System.out.println("operation accomplished successfully \n");
									} else if (manager.searchPersonByID(ID) instanceof LivePerson
											) {
										throw new CustomException(
												"Sorry, the person you are looking for is a living person! \n");
									}} else {
										throw new CustomException("Sorry We didn't find the person :( ,Try Again! \n");
									}

								} catch (CloneNotSupportedException e) {
									e.printStackTrace();
								} catch (CustomException e) {
									e.printStackTrace();
								}
								break;
							case 15:
								try {
									System.out.println("Enter the Person ID:");
									String ID = scan.next();
									LivePerson livePerson = new LivePerson();
									if (manager.searchPersonByID(ID) instanceof LivePerson
											&& manager.searchPersonByID(ID) != null) {
										livePerson = (LivePerson) manager.searchPersonByID(ID);
										LivePerson CopyLivePerson = new LivePerson();
										CopyLivePerson = (LivePerson) livePerson.clone();
										manager.CopyLivePerson(CopyLivePerson);
										System.out.println(manager.getCopyLivePerson().toString());
										System.out.println("operation accomplished successfully \n");
									} else if (manager.searchPersonByID(ID) instanceof Martyr
											&& manager.searchPersonByID(ID) != null) {
										throw new CustomException(
												"Sorry, the person you are looking for is a Martyr person! \n");
									} else {
										throw new CustomException("Sorry We didn't find the person :( ,Try Again! \n");
									}

								} catch (CloneNotSupportedException e) {
									e.printStackTrace();

								} catch (CustomException e) {

									e.printStackTrace();

								}
								break;
							case 16:
								System.out.println("Enter the family name:");
								try {
									FamilyName = scan.next();
									family = manager.searchByName(FamilyName);
									if (family == null) {
										throw new CustomException("Sorry We didn't find the family :( ,Try Again! \n");
									} else {
										Family CopyFamily = new Family();
										CopyFamily = (Family) family.clone();
										manager.CopyFamily(CopyFamily);
										System.out.println(manager.getCopyFamilies().toString());
										System.out.println("operation accomplished successfully \n");
									}
								} catch (CloneNotSupportedException e) {
									e.printStackTrace();

								} catch (CustomException e) {
									e.printStackTrace();

								}
								break;
							default:
								throw new CustomException("Enter a true value! \n");
							}
							System.out.println(
									" 1- Add a New Family \n 2- Update Family Details \n 3- Delete Family \n 4- Find a family \n 5- Find a Person "
											+ "\n 6- The Total of Martyrs Number \n 7- The Total of Orphans Number \n 8- The Total of Live Persons "
											+ "\n 9- The Family Statistics \n 10- The Global Statistics \n 11- Comparison if two families have the same number of martyrs "
											+ "\n 12- Ranking of families according to the number of martyrs \n 13- Ranking of families according to the number of orphans "
											+ "\n 14- Copy an object of type Martyr \n 15- Copy an object of type LivePerson \n 16- Copy an object of type Family \n 17- Exit ");
							System.out.println("Enter Your Choise:");
							Choise = scan.nextInt();
						}
						if (Choise == 17) {
							if (FileChiose == 1 && test == true) {
								try {
									OutputDataFileFromArrayList();
								} catch (CloneNotSupportedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println("operation accomplished successfully \n");
							}
							System.out.println("Thank You For Vist My Program");
						}
						TrueInput = false;
					} catch (InputMismatchException e) {

						System.out.println("Enter atrue value");
						scan.nextLine();

					}
				}

			} catch (InputMismatchException e) {
				e.printStackTrace();
				scan.next();
			}
		}
	}

	public static Family AddPersonToFamily(String FamilyName, Family family) {

		Scanner scan = new Scanner(System.in);
		boolean TrueInput4 = true;
		while (TrueInput4) {
			try {

				System.out.println("Enter the person ID:");
				String ID = scan.next();
				if (manager.searchPersonByID(ID) != null) {
					throw new CustomException("The ID value entered for another person");
				} /*
					 * You will not be able to add the ID if it has been used before
					 */

				System.out.println("Enter the person NAME:");

				String Name = scan.next();
				if (Name.length() < 2) {
					throw new CustomException("The name is vary short!");
				}
				boolean TrueInput5 = true;
				while (TrueInput5) {
					System.out.println("Enter the person AGE:");
					try {
						int Age = scan.nextInt();
						if (Age < 1 || Age > 150) {
							throw new CustomException(Age);
						}
						System.out.println("Enter the person Gender [ M:Male,F:Female ]:");
						String Gender = scan.next();

						while (!Gender.equals("M") && !Gender
								.equals("F")) { /*
												 * He will not be able to enter any value except one of the options
												 */
							System.out.println("Error!,Enter the person Gender [ M:Male,F:Female ]:");
							Gender = scan.next();
						}

						System.out.println("Enter the person Address:");
						String Address = scan.next();

						System.out.println("Enter the person Contactlnfo:");
						String Contactlnfo = scan.next();

						System.out
								.println("Enter The role of the person in the family [ dad , mam , son , daughter ]:");
						String roleInFamily = scan.next();

						while (!roleInFamily.equals("son") && !roleInFamily.equals("dad")
								&& Gender.equals("M")) {/*
														 * If he is a male,he will not be able to choose a person's role
														 * in the family except as a father or son
														 */
							System.out.println("Enter a true role!");
							System.out.println("The Gender = M ");
							roleInFamily = scan.next();

						}
						while (!roleInFamily.equals("daughter") && !roleInFamily.equals("mam")
								&& Gender.equals("F")) {/*
														 * If he is a female, he will not be able to choose a person's
														 * role in the family except as a mom or daughter
														 */
							System.out.println("Enter a true role!");
							System.out.println("The Gender = F ");
							roleInFamily = scan.next();

						}
						if (roleInFamily.equals("daughter") && family.getParents().size() != 2) {

							throw new CustomException(
									"Sorry, you cannot add a child to the family if you do not add both parents!");
						} else if (roleInFamily.equals("daughter")

								&& (family.getParents().get(0).getAge() - Age < 18
										|| family.getParents().get(1).getAge() - Age < 18)) {
							throw new CustomException(
									"The daughter's age cannot be this close to the age of the father or mother");
						} else if (roleInFamily.equals("son") && family.getParents().size() != 2) {
							throw new CustomException(
									"Sorry, you cannot add a child to the family if you do not add both parents!");

						} else if (roleInFamily.equals("son")

								&& (family.getParents().get(0).getAge() - Age < 18
										|| family.getParents().get(1).getAge() - Age < 18)) {
							System.out.println(family.getParents().get(0).getName()+" : "+family.getParents().get(0).getAge());
							System.out.println(family.getParents().get(1).getName()+" : "+family.getParents().get(1).getAge());
							throw new CustomException(
									"The son's age cannot be this close to the age of the father or mother");
						} else if (roleInFamily.equals("dad") && Age < 18) {
							throw new CustomException("Sorry, the person you entered is under the legal age");
						} else if (roleInFamily.equals("mom") && Age < 18) {
							throw new CustomException("Sorry, the person you entered is under the legal age");
						}else if(roleInFamily.equals("dad")&&family.Dad==1) {
							throw new CustomException("Sorry, The dad has already been added");
						}else if(roleInFamily.equals("mam")&&family.Mam==1) {
							throw new CustomException("Sorry, The mam has already been added");
							
						}
						boolean TrueInput6 = true;
						while (TrueInput6) {
							System.out
									.println(" 1- Live Person \n 2- Martyr Person");/*
																					 * Determine the type of member:
																					 * whether he is alive or a martyr
																					 */

							System.out.println("Enter Your Choise:");
							try {
								int Choise = scan.nextInt();
								while (Choise != 1 && Choise != 2) {
									System.out.println("Erorr,enter a true value!");
									System.out.println(" 1- Live Person \n 2- Martyr Person");
									System.out.println("Enter Your Choise:");
									Choise = scan.nextInt();
								}
								if (Choise == 2) {/*
													 * If he was a martyr, additional information related to his
													 * martyrdom is added
													 */

									System.out.println("Enter the Date Of Martyrdom:");
									String DateOfMartyrdom = scan.next();

									System.out.println("Enter the CausesOfDeath:");
									String CausesOfDeath = scan.next();

									System.out.println("Enter the PlaceOfDeath:");
									String PlaceOfDeath = scan.next();

									Martyr Martyrperson = new Martyr(ID, Name, Age, Gender, Address, Contactlnfo,
											roleInFamily);
									Martyrperson.setDateOfMartyrdom(DateOfMartyrdom);
									Martyrperson.setCausesOfDeath(CausesOfDeath);
									Martyrperson.setPlaceOfDeath(PlaceOfDeath);

									family.addMember(Martyrperson, roleInFamily, true);

								} else if (Choise == 1) {
									LivePerson lifePerson = new LivePerson(ID, Name, Age, Gender, Address, Contactlnfo,
											roleInFamily);
									family.addMember(lifePerson, roleInFamily, false);

								}
								TrueInput6 = false;
							} catch (InputMismatchException e) {
								System.out.println("Enter a NUMBER!");
								scan.nextLine();
							}
						}

						TrueInput5 = false;
					} catch (InputMismatchException e) {
						System.out.println("Enter a NUMBER!");
						scan.nextLine();
					} catch (CustomException e) {
						System.out.println(e.toString());
						scan.nextLine();
					}
				}
				TrueInput4 = false;
			} catch (InputMismatchException e) {
				System.out.println("Enter a NUMBER!");
				scan.nextLine();
			} catch (CustomException e) {
				System.out.println(e.toString());
				scan.nextLine();
			}
		}
		return family;
	}

	public static boolean InputDataFileOnArrayList() throws CustomException {//This method is responsible for reading families from the file and saving them in an ArrayList that is not of the Family type
		try {
			File file = new File("The_War_On_Gaza.txt");
			Scanner upload = new Scanner(file);//Reading is done using method: Upload type scanner
			 ArrayList<Family>familyList = new ArrayList<>();
			 Family family = new Family();
			 
			 int i=-1;
			
			 while (upload.hasNextLine()) {//It checks whether there are lines that can be read
				String line = upload.nextLine();
				
				if (line.startsWith("Family")) {//When he reads "Family", he creates a new family and the name that comes after the split (:) is read AND saved in array of String.
					family=new Family();
					String[] data = line.split(":");
					family.setFamilyName(data[1]);
					familyList.add(family);
					
					i++;
					
					
				} else if (family != null) {//After he reads the family name and saves it in the list array, he reads the family data. Every type of data that comes after the split ( , ) is read and saved in a string type array so that each type is given a value.
					String[] data2 = line.split(" , ");
					String NAME = data2[0];
					String ID = data2[1];
					int AGE = Integer.parseInt(data2[2]);
					String GENDER = data2[3];
					String ADDRESS = data2[4];
					String CANTACTINFO = data2[5];
					String RoleInFAMILY = data2[6];
					String TYPE = data2[7];
					if (TYPE.equals("Martyr") == true) {
						String DateOfMartyrdom = data2[8];
						String CausesOfDeath = data2[9];
						String PlaceOfDeath = data2[10];
						Martyr saveMartyr = new Martyr(ID, NAME, AGE, GENDER, ADDRESS, CANTACTINFO, RoleInFAMILY);
						saveMartyr.setDateOfMartyrdom(DateOfMartyrdom);
						saveMartyr.setCausesOfDeath(CausesOfDeath);
						saveMartyr.setPlaceOfDeath(PlaceOfDeath);
						familyList.get(i).addMember(saveMartyr, RoleInFAMILY, true);

					} else {
						LivePerson saveLivePerson = new LivePerson(ID, NAME, AGE, GENDER, ADDRESS, CANTACTINFO,
								RoleInFAMILY);
						familyList.get(i).addMember(saveLivePerson, RoleInFAMILY, false);
						
						
					}
					
				}
			}
			 for(i=0;i<familyList.size();i++) {//Families saved in an ArrayList are backed up on the ArrayList in the system
				manager.addFamily(familyList.get(i));
			 }
			 upload.close();
			 return true;
		} catch (IOException e) {
				e.printStackTrace();
				return false;
		}

	}

	public static void OutputDataFileFromArrayList() throws CloneNotSupportedException {//This method is responsible for storing families in a text file

		boolean validInput = false;

		Family saveFamily = new Family();
		Martyr saveMartyr = new Martyr();
		ArrayList <Family> FamilyList=new ArrayList<>();
		FamilyList=(ArrayList<Family>) manager.sortByMartyrs(manager.getFamilies()).clone();//The families are arranged in descending order based on the number of martyrs for each family
		LivePerson saveLivePerson = new LivePerson();
		
		do {
			try {
				File file = new File("The_War_On_Gaza.txt");
				try (PrintWriter Save = new PrintWriter(file);) {//The file is written using object: Save of a type PrintWriter
					
					for (int i = 0; i < FamilyList.size(); i++) {
						saveFamily = (Family) FamilyList.get(i).clone();//We copy the family into the object type Family to make it easier for us to work with the method -> [clone();]
						Save.println("Family:" + saveFamily.getFamilyName());
						for (int j = 0; j < saveFamily.getMembers().size(); j++) {
							if (saveFamily.getMembers().get(j) instanceof Martyr) {//Data is divided by ( , )
								saveMartyr = (Martyr) ((Martyr) saveFamily.getMembers().get(j)).clone();

								Save.println(saveMartyr.getName() + " , " + saveMartyr.getID() + " , "
										+ saveMartyr.getAge() + " , " + saveMartyr.getGender() + " , "
										+ saveMartyr.getAddress() + " , " + saveMartyr.getContactlnfo() + " , "
										+ saveMartyr.getRoleInFamily() + " , " + "Martyr" + " , "
										+ saveMartyr.getDateOfMartyrdom() + " , " + saveMartyr.getCausesOfDeath()
										+ " , " + saveMartyr.getPlaceOfDeath());

							} else if (saveFamily.getMembers().get(j) instanceof LivePerson) {//Data is divided by ( , )
								saveLivePerson = (LivePerson) ((LivePerson) saveFamily.getMembers().get(j)).clone();
								Save.println(saveLivePerson.getName() + " , " + saveLivePerson.getID() + " , "
										+ saveLivePerson.getAge() + " , " + saveLivePerson.getGender() + " , "
										+ saveLivePerson.getAddress() + " , " + saveLivePerson.getContactlnfo() + " , "
										+ saveLivePerson.getRoleInFamily() + " , " + "LivePerson");
							}
						}

					}

					validInput = true;

				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} while (!validInput);

	}

}