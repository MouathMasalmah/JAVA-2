package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Main extends Application {
	private BSTree<District> distractTree = new BSTree<>();
	private FileChooser filechooser = new FileChooser();
	private File file;
	private String allDistract;
	private String allLocation;
	private String allMartyrDate;
	private String allMartyr;
	private TNode<District> selectedDis;
	private TNode<Location> selectedLoca;
	private TNode<MartyrDate> selectedMartyrDate;
	private Label labDistrictName = new Label();
	private String changeString;
	private Martyr changeMartyr;

	@Override
	public void start(Stage stage) {
		Label Tmasseg = new Label();
		Tmasseg.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		Tmasseg.setPadding(new Insets(15));
		Button load = new Button("Load From File");
		load.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 15px;");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		load.setOnAction(e -> {
			filechooser.setTitle("Choose your file");
			filechooser.setInitialDirectory(new File("c:\\"));
			filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File.csv", "*.csv"));
			file = filechooser.showOpenDialog(stage);
			if (file != null) {
				if (file.exists()) {
					try {
						Scanner scan = new Scanner(file);
						String line = scan.nextLine().trim();
						while (scan.hasNext()) {
							try {
								 line = scan.nextLine().trim();

								String[] splitLine = line.split(",");

								String name = splitLine[0];
								String dateDeath = splitLine[1];
								int age = 0;
								if (splitLine[2].equals("") || splitLine[2] == null) {
									age = -1;
								} else {
									age = Integer.parseInt(splitLine[2].trim());
								}
								String location = splitLine[3];
								String distirct = splitLine[4];
								String gender = splitLine[5];
								Martyr InsertMartyr = new Martyr(name, age, gender);
								District insertDistrict = new District(distirct.trim());
								TNode<District> newDistrict = distractTree.find(insertDistrict);
								if (newDistrict == null) {
									MartyrDate insertDate = new MartyrDate(dateDeath.trim());
									Location insertLocation = new Location(location.trim());
									insertDate.getMartyrList().insert(InsertMartyr);
									insertLocation.getDateTree().insert(insertDate);
									insertDistrict.getLocationTree().insert(insertLocation);
									distractTree.insert(insertDistrict);
								} else {
									Location insertLocation = new Location(location);
									TNode<Location> newLocation = newDistrict.getData().getLocationTree().find(insertLocation);
									if (newLocation == null) {
										MartyrDate insertDate = new MartyrDate(dateDeath);
										insertDate.getMartyrList().insert(InsertMartyr);
										insertLocation.getDateTree().insert(insertDate);
										newDistrict.getData().getLocationTree().insert(insertLocation);
									} else {
										MartyrDate insertDate = new MartyrDate(dateDeath);
										TNode<MartyrDate> newDate = newLocation.getData().getDateTree()
												.find(insertDate);
										if (newDate == null) {
											insertDate.getMartyrList().insert(InsertMartyr);
											newLocation.getData().getDateTree().insert(insertDate);
										} else {
											newDate.getData().getMartyrList().insert(InsertMartyr);
										}
									}
								}

								Tmasseg.setText("Well Done");
								Tmasseg.setTextFill(Color.BLACK);

							} catch (NumberFormatException ex) {
								Tmasseg.setText("We Can't find the file!");
								Tmasseg.setTextFill(Color.RED);
							} catch (Exception ex) {
								Tmasseg.setText("Done,with some problem");
								Tmasseg.setTextFill(Color.RED);
							}
						}
						scan.close();
					} catch (NumberFormatException ex) {
						Tmasseg.setText("We Can't find the file!");
						Tmasseg.setTextFill(Color.RED);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					stage.close();
					distrctScreen(stage);
				}
			} else {
				Tmasseg.setText("the system did not find your file");
			}

		});

		root.setCenter(load);
		root.setBottom(Tmasseg);
		root.setAlignment(Tmasseg, Pos.CENTER);
		stage.setScene(scene);
		stage.show();
	}

	public void distrctScreen(Stage stage) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		allDistract = distractTree.traverseInOrder();
		selectedDis = new TNode<District>(distractTree.getNext());
		Label labTitle = new Label("District Screen");
		labTitle.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		labTitle.setPadding(new Insets(15));
		labTitle.setAlignment(Pos.CENTER);
		labDistrictName.setText("Distract Name: " + selectedDis);
		labDistrictName.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		labDistrictName.setAlignment(Pos.CENTER);
		Label labResult = new Label("");
		Label lblResultStatistics = new Label("");
		lblResultStatistics.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		Label lblForSize = new Label("قال رسول الله صلى الله عليه وسلم:\r\n" + " للشهيد عند الله سبع خصال: \r\n"
				+ "1- يغفر له في أول دفعة من دمه. \r\n" + "2- ويرى مقعده من الجنة. \r\n" + "3- ويحلى حلة الإيمان. \r\n"
				+ "4- ويزوج اثنتين وسبعين زوجة من الحور العين. \r\n" + "5- ويجار من عذاب القبر. \r\n"
				+ "6- ويأمن من الفزع الأكبر. \r\n" + "7- ويوضع على رأسه تاج الوقار، الياقوتة منه خير \r\n"
				+ "من الدنيا وما فيها. \r\n" + "8- ويشفع في سبعين إنسانًا من أهل بيته. \r\n");
		lblForSize.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		lblForSize.setPadding(new Insets(20));
		lblForSize.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		labResult.setAlignment(Pos.CENTER);
		Button butInsertDistric = new Button("insert new district");
		Button butUpdateDistric = new Button("update a district");
		Button butDeleteDistric = new Button("delete a district");
		Button butGoPreviousDistric = new Button("Prev District");
		Button butGoNextDistric = new Button("Next District");
		Button butLocationScreen = new Button("load the location screen");
		butInsertDistric.setOnAction(e -> {
			stage.close();
			insertDistrict(stage);
		});
		butUpdateDistric.setOnAction(e -> {
			stage.close();
			UpdateDistrict(stage);
		});
		butDeleteDistric.setOnAction(e -> {
			stage.close();
			deleteDistrict(stage);
		});
		butLocationScreen.setOnAction(e -> {
			if (selectedDis != null && selectedDis.getData() != null) {
				stage.close();
				locationScreen(stage);
			} else {
				labResult.setText("the List is Empty");
			}
		});
		butGoNextDistric.setOnAction(e -> {
			int totalMartyr = 0;
			if (distractTree.isEmpty()) {
				labResult.setText("the List is Empty");
			} else if (!distractTree.getNextStack().isEmpty()) {
				selectedDis = new TNode<District>(distractTree.getNext());
				labDistrictName.setText("Distract Name: " + selectedDis);

				if (selectedDis != null) {
					totalMartyr = selectedDis.getData().theNumberOfMartyer();
					lblResultStatistics.setText("\nThe total number of martyr: " + totalMartyr);
					labResult.setText("");
				}
			} else {
				if (distractTree.getNextStack().isEmpty()) {
					labResult.setText("This is the last Distract");
					if (selectedDis != null) {
						totalMartyr = selectedDis.getData().theNumberOfMartyer();
						lblResultStatistics.setText("\nThe total number of martyr: " + totalMartyr);
					}
				}
			}
		});
		butGoPreviousDistric.setOnAction(e -> {
			int totalMartyr = 0;
			if (distractTree.isEmpty()) {
				labResult.setText("the List is Empty");
			} else if (distractTree.getPrefStack().isEmpty()) {
				labResult.setText("This is the First Distract");
				if (selectedDis != null) {
					totalMartyr = selectedDis.getData().theNumberOfMartyer();
					lblResultStatistics.setText("\nThe total number of martyr: " + totalMartyr);

				}
			} else {
				selectedDis.setData(distractTree.getPref());
				labDistrictName.setText("Distract Name: " + selectedDis);
				if (selectedDis != null) {
					totalMartyr = selectedDis.getData().theNumberOfMartyer();
					lblResultStatistics.setText("\nThe total number of martyr: " + totalMartyr);
				}
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		butInsertDistric.setPrefWidth(200);
		butUpdateDistric.setPrefWidth(200);
		butDeleteDistric.setPrefWidth(200);
		butLocationScreen.setPrefWidth(200);
		butGoNextDistric.setPrefWidth(100);
		butGoPreviousDistric.setPrefWidth(100);
		lblResultStatistics.setPrefWidth(350);
		lblResultStatistics.setPadding(new Insets(15));
		lblForSize.setPrefWidth(350);
		VBox butBox = new VBox(10, labDistrictName, butInsertDistric, butUpdateDistric, butDeleteDistric,
				butLocationScreen, labResult);
		HBox nextAndPrevBox = new HBox(10, butGoPreviousDistric, butGoNextDistric);
		butBox.setAlignment(Pos.CENTER);
		nextAndPrevBox.setAlignment(Pos.CENTER);
		labDistrictName.setAlignment(Pos.CENTER);
		labTitle.setAlignment(Pos.CENTER);
		nextAndPrevBox.setPadding(new Insets(10));
		BorderPane root = new BorderPane();
		root.setCenter(butBox);
		root.setTop(labTitle);
		root.setRight(lblForSize);
		root.setLeft(lblResultStatistics);
		root.setBottom(nextAndPrevBox);
		root.setAlignment(labTitle, Pos.CENTER);
		root.setAlignment(nextAndPrevBox, Pos.CENTER);
		root.setAlignment(lblForSize, Pos.CENTER);
		Scene scene = new Scene(root, 1100, 450);
		stage.setTitle("District Screen");
		stage.setScene(scene);
		stage.show();
	}

	public void insertDistrict(Stage stage) {
		Label LblResult = new Label("");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		TextField tfEnterDis = new TextField();
		tfEnterDis.setPromptText("Enter The District");
		Button butInsert = new Button("Insert");
		butInsert.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to add the District?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				if (tfEnterDis.getText().equals("")) {
					LblResult.setText("Enter a true value");
				} else {
					String Name = tfEnterDis.getText();
					District newDistract = new District(Name);
					int counterOfNumber = 0;
					for (int i = 0; i < Name.length(); i++) {
						if (Character.isDigit(Name.charAt(i))) {
							counterOfNumber++;
						}
					}
					if (counterOfNumber == 0) {

						if (distractTree.find(newDistract) == null) {

							distractTree.insert(newDistract);
							LblResult.setText("Done");

						} else {
							LblResult.setText("The District is already added");
						}
					} else {
						LblResult.setText("Enter a true value");
					}
				}
			}

		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				distrctScreen(stage);
			}

		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterDis, 1, 2);
		gpPane.add(butInsert, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("Insert District");
		stage.setScene(scene);
		stage.show();

	}

	public void UpdateDistrict(Stage stage) {
		Label LblResult = new Label("");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		ComboBox<String> comboList = new ComboBox<>();
		String[] allDistrictNames = allDistract.split(",");
		for (int i = allDistrictNames.length - 1; i >= 0; i--) {
			comboList.getItems().add(allDistrictNames[i]);
		}
		TextField tfEnterNewDis = new TextField();
		tfEnterNewDis.setPromptText("Enter The New District Name");
		Button butUpdate = new Button("Update");

		comboList.setOnAction(e -> {
			if (comboList.getValue() != null)
				setChangeString(comboList.getValue().trim());
		});
		butUpdate.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to update the District Name?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				String oldName = getChangeString();
				String newName = tfEnterNewDis.getText();
				if (oldName == null) {
					System.out.println(allDistract);
					LblResult.setText("select district");
				} else if (newName.equals("")) {
					LblResult.setText("Enter true value");
				} else {
					District oldDistractName = new District(oldName);
					TNode<District> oldDistrict = distractTree.find(oldDistractName);
					if (oldDistrict != null) {
						int counterOfNumber = 0;
						for (int i = 0; i < newName.length(); i++) {
							if (Character.isDigit(newName.charAt(i))) {
								counterOfNumber++;
							}
						}
						District newDistractName = new District(newName);
						TNode<District> newDistract = distractTree.find(newDistractName);
						if (counterOfNumber == 0 && newDistract == null) {
							District newDistrict = oldDistrict.getData();
							distractTree.delete(oldDistrict.getData());
							newDistrict.setDistrictName(newName);
							distractTree.insert(newDistrict);
							LblResult.setText("Done");
							comboList.getItems().clear();
							allDistract = distractTree.traverseInOrder();
							String[] allDistrictName = allDistract.split(",");
							for (int i = allDistrictName.length - 1; i >= 0; i--) {
								comboList.getItems().add(allDistrictName[i]);
							}
						} else if (newDistract != null) {
							LblResult.setText("The new Name is in the system");
						} else {

							LblResult.setText("The new Name is not true");
						}
					} else {
						LblResult.setText("The Name is not in the system");
					}
				}
			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				distrctScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(comboList, 1, 2);
		gpPane.add(tfEnterNewDis, 2, 2);
		gpPane.add(butUpdate, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("Update District");
		stage.setScene(scene);
		stage.show();

	}

	public void deleteDistrict(Stage stage) {
		setChangeString("");
		Label LblResult = new Label("");
		Button butDelete = new Button("Delete");
		ComboBox<String> comboList = new ComboBox<>();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		String[] allDistrictNames = allDistract.split(",");
		for (int i = allDistrictNames.length - 1; i >= 0; i--) {
			comboList.getItems().add(allDistrictNames[i]);
		}

		comboList.setOnAction(e -> {
			if (comboList.getValue() != null)
				setChangeString(comboList.getValue().trim());
		});
		butDelete.setOnAction(e -> {
			District newDistract = new District(getChangeString().trim());
			TNode<District> selectedDistrict = distractTree.find(newDistract);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to delete the District?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				if (selectedDistrict != null && distractTree.delete(selectedDistrict.getData()) != null) {
					LblResult.setText("Done");
					comboList.getItems().clear();
					allDistract = distractTree.traverseInOrder();
					String[] allDistrictName = allDistract.split(",");
					for (int i = allDistrictName.length - 1; i >= 0; i--) {
						comboList.getItems().add(allDistrictName[i]);
					}

				} else {
					LblResult.setText("The Distract is not in the system");
				}
			}

		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				distrctScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(comboList, 1, 2);
		gpPane.add(butDelete, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("delete District");
		stage.setScene(scene);
		stage.show();

	}

	public void locationScreen(Stage stage) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		BSTree<Location> locationInDistract = selectedDis.getData().getLocationTree();
		allLocation = locationInDistract.traverseLevelByLevel();
		selectedLoca = new TNode<Location>(locationInDistract.getNext());
		Label labTitle = new Label("Location Screen");
		labTitle.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		labTitle.setPadding(new Insets(15));
		labTitle.setAlignment(Pos.CENTER);
		Label labLocationName = new Label("Location Name: " + selectedLoca);
		labLocationName.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		labLocationName.setAlignment(Pos.CENTER);
		Label labResult = new Label("");
		labResult.setAlignment(Pos.CENTER);
		Label lblForSize = new Label("قال رسول الله صلى الله عليه وسلم:\r\n" + " للشهيد عند الله سبع خصال: \r\n"
				+ "1- يغفر له في أول دفعة من دمه. \r\n" + "2- ويرى مقعده من الجنة. \r\n" + "3- ويحلى حلة الإيمان. \r\n"
				+ "4- ويزوج اثنتين وسبعين زوجة من الحور العين. \r\n" + "5- ويجار من عذاب القبر. \r\n"
				+ "6- ويأمن من الفزع الأكبر. \r\n" + "7- ويوضع على رأسه تاج الوقار، الياقوتة منه خير \r\n"
				+ "من الدنيا وما فيها. \r\n" + "8- ويشفع في سبعين إنسانًا من أهل بيته. \r\n");
		lblForSize.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		lblForSize.setPadding(new Insets(20));
		lblForSize.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		Label lblResultStatistics = new Label("");
		Button butInsertLocation = new Button("insert new location");
		Button butUpdateLocation = new Button("update a location");
		Button butDeleteLocation = new Button("delete a location");
		Button butGoNextLocation = new Button("Next location");
		Button butGoPrefLocation = new Button("pref location");
		Button butGoPreviousPage = new Button("Back to District");
		Button butGoToMartyrPage = new Button("Load Martyr Screen");
		butInsertLocation.setOnAction(e -> {
			stage.close();
			insertLocation(stage);
		});
		butUpdateLocation.setOnAction(e -> {
			stage.close();
			UpdateLocation(stage);
		});
		butDeleteLocation.setOnAction(e -> {
			stage.close();
			deleteLocation(stage);
		});
		butGoToMartyrPage.setOnAction(e -> {
			if (selectedLoca != null && selectedLoca.getData() != null) {
				stage.close();
				martyrScreen(stage);
			} else {
				labResult.setText("the List is Empty");
			}
		});
		butGoNextLocation.setOnAction(e -> {
			if (locationInDistract.isEmpty()) {
				labResult.setText("the List is Empty");
				System.out.println(allLocation);
			} else if (!locationInDistract.getNextStack().isEmpty()) {
				selectedLoca = new TNode<Location>(locationInDistract.getNext());
				labLocationName.setText("Location Name: " + selectedLoca);

				if (selectedLoca != null) {
					selectedLoca.getData().getDateTree().traverseInOrder();
					lblResultStatistics
							.setText("\nThe earliest date: " + selectedLoca.getData().getDateTree().smallest()
									+ "\nThe latest date: " + selectedLoca.getData().getDateTree().largest()
									+ "\nThe maximum: " + selectedLoca.getData().maximumDate());
					labResult.setText("");
				}
			} else {
				if (locationInDistract.getNextStack().isEmpty()) {
					labResult.setText("This is the last Location");
					if (selectedLoca != null) {
						selectedLoca.getData().getDateTree().traverseInOrder();
						lblResultStatistics
								.setText("\nThe earliest date: " + selectedLoca.getData().getDateTree().smallest()
										+ "\nThe latest date: " + selectedLoca.getData().getDateTree().largest()
										+ "\nThe maximum: " + selectedLoca.getData().maximumDate());
					}
				}
			}
		});
		butGoPrefLocation.setOnAction(e -> {
			if (locationInDistract.isEmpty()) {
				labResult.setText("the List is Empty");
			} else if (locationInDistract.getPrefStack().isEmpty()) {
				labResult.setText("This is the First Location");
				if (selectedLoca != null) {
					selectedLoca.getData().getDateTree().traverseInOrder();
					lblResultStatistics
							.setText("\nThe earliest date: " + selectedLoca.getData().getDateTree().smallest()
									+ "\nThe latest date: " + selectedLoca.getData().getDateTree().largest()
									+ "\nThe maximum: " + selectedLoca.getData().maximumDate());
				}
			} else {
				selectedLoca.setData(locationInDistract.getPref());
				labLocationName.setText("Location Name: " + selectedLoca);
				if (selectedLoca != null) {
					selectedLoca.getData().getDateTree().traverseInOrder();
					lblResultStatistics
							.setText("\nThe earliest date: " + selectedLoca.getData().getDateTree().smallest()
									+ "\nThe latest date: " + selectedLoca.getData().getDateTree().largest()
									+ "\nThe maximum: " + selectedLoca.getData().maximumDate());
				}
				labResult.setText("");
			}
		});
		butGoPreviousPage.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				distrctScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		butInsertLocation.setPrefWidth(200);
		butUpdateLocation.setPrefWidth(200);
		butDeleteLocation.setPrefWidth(200);
		butGoToMartyrPage.setPrefWidth(200);
		butGoNextLocation.setPrefWidth(100);
		butGoPrefLocation.setPrefWidth(100);
		butGoPreviousPage.setPrefWidth(100);
		VBox butBox = new VBox(10, labLocationName, butInsertLocation, butUpdateLocation, butDeleteLocation,
				butGoToMartyrPage, labResult);
		butBox.setAlignment(Pos.CENTER);
		HBox nextAndPrevBox = new HBox(10, butGoPrefLocation, butGoNextLocation);

		VBox bottomVbox = new VBox(10, nextAndPrevBox, butGoPreviousPage);
		bottomVbox.setAlignment(Pos.CENTER);
		nextAndPrevBox.setAlignment(Pos.CENTER);
		butGoPreviousPage.setAlignment(Pos.CENTER);
		nextAndPrevBox.setPadding(new Insets(10));
		bottomVbox.setPadding(new Insets(10));
		lblResultStatistics.setPrefWidth(350);
		lblResultStatistics.setPadding(new Insets(15));
		lblForSize.setPrefWidth(350);
		BorderPane root = new BorderPane();
		root.setCenter(butBox);
		root.setTop(labTitle);
		root.setBottom(bottomVbox);
		root.setRight(lblForSize);
		root.setLeft(lblResultStatistics);
		root.setAlignment(labTitle, Pos.CENTER);
		root.setAlignment(labResult, Pos.CENTER);
		Scene scene = new Scene(root, 1000, 500);
		stage.setTitle("Location Screen");
		stage.setScene(scene);
		stage.show();
	}

	public void insertLocation(Stage stage) {
		Label LblResult = new Label("");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		TextField tfEnterLoca = new TextField();
		tfEnterLoca.setPromptText("Enter The Location");
		ComboBox<String> comboList = new ComboBox<>();
		String[] allDistrictNames = allDistract.split(",");
		for (int i = allDistrictNames.length - 1; i >= 0; i--) {
			comboList.getItems().add(allDistrictNames[i]);
		}

		comboList.setOnAction(e -> {
			if (comboList.getValue() != null)
				setChangeString(comboList.getValue().trim());
		});
		Button butInsert = new Button("Insert");
		butInsert.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to add the location?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				Location newLocation = new Location(tfEnterLoca.getText());
				if (tfEnterLoca.getText().equals("")) {
					LblResult.setText("Enter a true value");
				} else {
					boolean testName = true;
					for (int i = 0; i < tfEnterLoca.getText().length(); i++) {
						if (Character.isDigit(tfEnterLoca.getText().charAt(i))) {
							testName = false;
							break;
						}
					}
					District searchAboutdistrit = new District(getChangeString());
					TNode<District> findDistrict = distractTree.find(searchAboutdistrit);
					if (findDistrict != null) {
						if (findDistrict.getData().getLocationTree().find(newLocation) == null && testName == true) {
							findDistrict.getData().getLocationTree().insert(newLocation);
							LblResult.setText("Done");
						} else if (testName == false) {
							LblResult.setText("Enter a true value");
						} else {

							LblResult.setText("The Location is already added");
						}
					}
				}
			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				locationScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(comboList, 1, 3);
		gpPane.add(tfEnterLoca, 1, 2);
		gpPane.add(butInsert, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("Insert Location");
		stage.setScene(scene);
		stage.show();

	}

	public void UpdateLocation(Stage stage) {
		BSTree<Location> locationInDistract = selectedDis.getData().getLocationTree();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		Label LblResult = new Label("");
		ComboBox<String> comboList = new ComboBox<>();
		comboList.setPromptText("select Location");
		String[] allLocationNames = allLocation.split(",");
		for (int i = allLocationNames.length - 1; i >= 0; i--) {
			comboList.getItems().add(allLocationNames[i]);
		}

		comboList.setOnAction(e -> {
			if (comboList.getValue() != null) {
				setChangeString(comboList.getValue().trim());
			}
		});
		TextField tfEnterNewLocation = new TextField();
		tfEnterNewLocation.setPromptText("Enter The New Location Name");
		Button butUpdate = new Button("Update");

		butUpdate.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to update the location name?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				boolean testName = true;
				if (tfEnterNewLocation.getText().equals("") || comboList.getValue() == null) {
					LblResult.setText("Enter a true value");
				} else {
					for (int i = 0; i < tfEnterNewLocation.getText().length(); i++) {
						if (Character.isDigit(tfEnterNewLocation.getText().charAt(i))) {
							testName = false;
							break;
						}
					}
					if (testName == true) {
						Location oldLocation = new Location(getChangeString());
						TNode<Location> findOldLocation = selectedDis.getData().getLocationTree().find(oldLocation);
						Location newLocation = new Location(tfEnterNewLocation.getText().trim());
						TNode<Location> findNewLocation = selectedDis.getData().getLocationTree().find(newLocation);
						if (findOldLocation != null) {
							Location updateLocation = findOldLocation.getData();

							if (findNewLocation == null) {
								selectedDis.getData().getLocationTree().delete(findOldLocation.getData());
								updateLocation.setLocationName(tfEnterNewLocation.getText());
								selectedDis.getData().getLocationTree().insert(updateLocation);
								LblResult.setText("Done");
								allLocation = locationInDistract.traverseLevelByLevel();
								comboList.getItems().clear();
								String[] allLocationName = allLocation.split(",");
								for (int i = allLocationName.length - 1; i >= 0; i--) {
									comboList.getItems().add(allLocationName[i]);
								}
							} else
								LblResult.setText("The new Name is in the system");
						} else {
							LblResult.setText("Enter a true value");
						}
					}
				}

			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				locationScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(comboList, 1, 3);
		gpPane.add(tfEnterNewLocation, 1, 2);
		gpPane.add(butUpdate, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("updete Location");
		stage.setScene(scene);
		stage.show();

	}

	public void deleteLocation(Stage stage) {
		setChangeString("");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		Label LblResult = new Label("");
		ComboBox<String> comboList = new ComboBox<>();
		comboList.setPromptText("select Location");
		String[] allLocationNames = allLocation.split(",");
		for (int i = allLocationNames.length - 1; i >= 0; i--) {
			comboList.getItems().add(allLocationNames[i]);
		}

		comboList.setOnAction(e -> {
			if (comboList.getValue() != null) {
				setChangeString(comboList.getValue().trim());
			}
		});
		Button butDelete = new Button("Delete");

		butDelete.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to delete the location?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				Location deleteLocation = new Location(getChangeString());
				TNode<Location> selectLocation = selectedDis.getData().getLocationTree().find(deleteLocation);
				if (selectLocation != null) {
					selectedDis.getData().getLocationTree().delete(selectLocation.getData());
					LblResult.setText("Done");
					comboList.getItems().clear();
					String[] allLocationName = allLocation.split(",");
					for (int i = allLocationName.length - 1; i >= 0; i--) {
						comboList.getItems().add(allLocationName[i]);
					}
				} else {
					LblResult.setText("The Location is not in the system");
				}
			}

		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				locationScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(comboList, 1, 2);
		gpPane.add(butDelete, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 400, 200);
		stage.setTitle("delete Location");
		stage.setScene(scene);
		stage.show();

	}

	public void martyrScreen(Stage stage) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		BSTree<MartyrDate> dateInLocation = selectedLoca.getData().getDateTree();
		allMartyrDate = dateInLocation.traverseInOrder();
		selectedMartyrDate = new TNode<MartyrDate>(dateInLocation.getNext());
		TextArea martyrArea = new TextArea();
		martyrArea.setEditable(false);
		Label labTitle = new Label("Matryr Screen");
		labTitle.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		labTitle.setPadding(new Insets(15));
		labTitle.setAlignment(Pos.CENTER);
		Label labLocationName = new Label("Date: " + selectedMartyrDate);
		labLocationName.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		labLocationName.setAlignment(Pos.CENTER);
		Label labResult = new Label("");
		labResult.setAlignment(Pos.CENTER);
		Label lblForSize = new Label("قال رسول الله صلى الله عليه وسلم:\r\n" + " للشهيد عند الله سبع خصال: \r\n"
				+ "1- يغفر له في أول دفعة من دمه. \r\n" + "2- ويرى مقعده من الجنة. \r\n" + "3- ويحلى حلة الإيمان. \r\n"
				+ "4- ويزوج اثنتين وسبعين زوجة من الحور العين. \r\n" + "5- ويجار من عذاب القبر. \r\n"
				+ "6- ويأمن من الفزع الأكبر. \r\n" + "7- ويوضع على رأسه تاج الوقار، الياقوتة منه خير \r\n"
				+ "من الدنيا وما فيها. \r\n" + "8- ويشفع في سبعين إنسانًا من أهل بيته. \r\n");
		lblForSize.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		lblForSize.setPadding(new Insets(20));
		lblForSize.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 13px; -fx-font-weight: bold;");
		Label lblResultStatistics = new Label("");
		Button butGoNextDate = new Button("Next");
		Button butGoPrefDate = new Button("Pref");
		Button butInsertANewMartyr = new Button("insert a new martyr");
		Button butUpdateAMartyr = new Button("update a martyr");
		Button butDeleteAMartyr = new Button("delete a martyr");
		Button butSearchForMartyr = new Button("search for martyr");
		Button butGoPreviousPage = new Button("Back to District");

		butInsertANewMartyr.setOnAction(e -> {
			stage.close();
			insetrMartyrScrean(stage);
		});
		butDeleteAMartyr.setOnAction(e -> {
			stage.close();
			deleteMartyr(stage);
		});
		butUpdateAMartyr.setOnAction(e -> {
			stage.close();
			UpdateMartyrScrean(stage);
		});
		butSearchForMartyr.setOnAction(e -> {
			stage.close();
			searchByPartOfName(stage);
		});

		butGoPreviousPage.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				locationScreen(stage);
			}
		});

		butGoNextDate.setOnAction(e -> {
			if (dateInLocation.isEmpty()) {
				labResult.setText("the List is Empty");
			} else if (!dateInLocation.getNextStack().isEmpty()) {
				selectedMartyrDate = new TNode<MartyrDate>(dateInLocation.getNext());
				labLocationName.setText("Date: " + selectedMartyrDate);

				if (selectedMartyrDate != null) {
					martyrArea.clear();
					LinkedListQueue<Martyr> martyersInDate = selectedMartyrDate.getData().allMartyerInTheDate();
					martyersInDate = insertInSortedOrder(martyersInDate);
					while (!martyersInDate.isEmpty()) {
						Martyr martyr = martyersInDate.dequeue();
						martyrArea.appendText(martyr.toString());
					}
					if (selectedMartyrDate.getData().getMartyrList().length() != 0) {
						lblResultStatistics.setText("\nThe average: " + selectedMartyrDate.getData().avgAge()
								+ "\nThe youngest: " + selectedMartyrDate.getData().youngest().getName()
								+ "\nThe oldest: " + selectedMartyrDate.getData().oldest().getName());
						labResult.setText("");
					} else {
						lblResultStatistics.setText("\nThe average: 0" + "\nThe youngest: None" + "\nThe oldest: None");
						labResult.setText("");
					}
				}
			} else {
				if (dateInLocation.getNextStack().isEmpty()) {
					labResult.setText("This is the last Date");
					if (selectedMartyrDate != null) {
						martyrArea.clear();
						LinkedListQueue<Martyr> martyersInDate = selectedMartyrDate.getData().allMartyerInTheDate();
						martyersInDate = insertInSortedOrder(martyersInDate);
						while (!martyersInDate.isEmpty()) {
							Martyr martyr = martyersInDate.dequeue();
							martyrArea.appendText(martyr.toString());
						}
						if (selectedMartyrDate.getData().getMartyrList().length() != 0) {
							lblResultStatistics.setText("\nThe average: " + selectedMartyrDate.getData().avgAge()
									+ "\nThe youngest: " + selectedMartyrDate.getData().youngest().getName()
									+ "\nThe oldest: " + selectedMartyrDate.getData().oldest().getName());

						} else {
							lblResultStatistics
									.setText("\nThe average: 0" + "\nThe youngest: None" + "\nThe oldest: None");

						}

					}
				}
			}
		});
		butGoPrefDate.setOnAction(e -> {
			if (dateInLocation.isEmpty()) {
				labResult.setText("the List is Empty");
			} else if (dateInLocation.getPrefStack().isEmpty()) {
				labResult.setText("This is the First Date");
				if (selectedMartyrDate != null) {
					martyrArea.clear();
					LinkedListQueue<Martyr> martyersInDate = selectedMartyrDate.getData().allMartyerInTheDate();
					martyersInDate = insertInSortedOrder(martyersInDate);
					while (!martyersInDate.isEmpty()) {
						Martyr martyr = martyersInDate.dequeue();
						martyrArea.appendText(martyr.toString());
					}
					if (selectedMartyrDate.getData().getMartyrList().length() != 0) {
						lblResultStatistics.setText("\nThe average: " + selectedMartyrDate.getData().avgAge()
								+ "\nThe youngest: " + selectedMartyrDate.getData().youngest().getName()
								+ "\nThe oldest: " + selectedMartyrDate.getData().oldest().getName());

					} else {
						lblResultStatistics.setText("\nThe average: 0" + "\nThe youngest: None" + "\nThe oldest: None");

					}
				}
			} else {
				selectedMartyrDate.setData(dateInLocation.getPref());
				labLocationName.setText("Date: " + selectedMartyrDate);
				if (selectedMartyrDate != null) {
					martyrArea.clear();
					LinkedListQueue<Martyr> martyersInDate = selectedMartyrDate.getData().allMartyerInTheDate();
					martyersInDate = insertInSortedOrder(martyersInDate);
					while (!martyersInDate.isEmpty()) {
						Martyr martyr = martyersInDate.dequeue();
						martyrArea.appendText(martyr.toString());
					}
					if (selectedMartyrDate.getData().getMartyrList().length() != 0) {
						lblResultStatistics.setText("\nThe average: " + selectedMartyrDate.getData().avgAge()
								+ "\nThe youngest: " + selectedMartyrDate.getData().youngest().getName()
								+ "\nThe oldest: " + selectedMartyrDate.getData().oldest().getName());
						labResult.setText("");
					} else {
						lblResultStatistics.setText("\nThe average: 0" + "\nThe youngest: None" + "\nThe oldest: None");
						labResult.setText("");
					}
				}
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		butInsertANewMartyr.setPrefWidth(200);
		butDeleteAMartyr.setPrefWidth(200);
		butUpdateAMartyr.setPrefWidth(200);
		butSearchForMartyr.setPrefWidth(200);
		butGoNextDate.setPrefWidth(100);
		butGoPrefDate.setPrefWidth(100);
		butGoPreviousPage.setPrefWidth(100);
		VBox butBox = new VBox(10, labLocationName, butInsertANewMartyr, butDeleteAMartyr, butUpdateAMartyr,
				butSearchForMartyr, labResult);
		butBox.setAlignment(Pos.CENTER);
		HBox nextAndPrevBox = new HBox(10, butGoPrefDate, butGoNextDate);
		nextAndPrevBox.setAlignment(Pos.CENTER);
		nextAndPrevBox.setPadding(new Insets(10));

		VBox VboxBottom = new VBox(10, nextAndPrevBox, butGoPreviousPage);
		VBox s = new VBox(10, lblResultStatistics, martyrArea);
		VboxBottom.setAlignment(Pos.CENTER);
		VboxBottom.setPadding(new Insets(10));
		BorderPane root = new BorderPane();
		lblResultStatistics.setPrefWidth(350);
		lblResultStatistics.setPadding(new Insets(15));
		lblForSize.setPrefWidth(450);
		root.setCenter(butBox);
		root.setTop(labTitle);
		root.setBottom(VboxBottom);
		root.setLeft(s);
		root.setRight(lblForSize);
		root.setAlignment(labTitle, Pos.CENTER);
		root.setAlignment(labResult, Pos.CENTER);
		Scene scene = new Scene(root, 1400, 500);
		stage.setTitle("Location Screen");
		stage.setScene(scene);
		stage.show();
	}

	public void searchByPartOfName(Stage stage) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		Label LblResult = new Label("");
		TextArea martyrTextArea = new TextArea();
		martyrTextArea.setEditable(false);
		TextField tfEnterDis = new TextField();
		tfEnterDis.setPromptText("Enter The name or part of the name");
		Button butSearch = new Button("Search");

		butSearch.setOnAction(e -> {
			martyrTextArea.clear();
			if (selectedLoca != null) {
				String result = selectedMartyrDate.getData().searchByPartOfName(tfEnterDis.getText());
				if (result != null) {
					martyrTextArea.appendText(result);
				} else {
					martyrTextArea.appendText("");
				}
			} else {
				LblResult.setText("their is no martyer in this date");
			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		GridPane gpPane = new GridPane();
		gpPane.add(tfEnterDis, 1, 2);
		gpPane.add(butSearch, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		VBox buttVBox = new VBox(10, gpPane, back);
		buttVBox.setAlignment(Pos.CENTER);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane SearchPane = new BorderPane();
		SearchPane.setCenter(martyrTextArea);
		SearchPane.setBottom(buttVBox);
		Scene scene = new Scene(SearchPane, 900, 450);
		stage.setTitle("search by martyr name");
		stage.setScene(scene);
		stage.show();
	}

	public void insetrMartyrScrean(Stage stage) {
		setChangeString("");
		Label LblResult = new Label("");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		TextField tfName = new TextField();
		tfName.setPromptText("Enter The Name");

		DatePicker datePicker = new DatePicker();
		datePicker.setEditable(false);
		datePicker.setOnAction(event -> {
			try {
				LocalDate date = datePicker.getValue();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				String formattedDate = date.format(formatter);
				setChangeString(formattedDate);
			} catch (DateTimeParseException ex) {
				LblResult.setText("Enter True Date");
			}
		});
		TextField tfAge = new TextField();
		tfAge.setPromptText("Enter The Age");

		TextField tfGender = new TextField();
		tfGender.setPromptText("Enter The Gender");

		Button butInsert = new Button("Insert");
		butInsert.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to add the martyr?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				try {
					boolean testName = true;
					String nameMartyr = tfName.getText();
					String[] splitName = nameMartyr.split(" ");
					if (splitName.length >= 3) {
						for (int i = 0; i < nameMartyr.length(); i++) {
							if (Character.isDigit(nameMartyr.charAt(i)) == true) {
								testName = false;
								break;
							}
						}
					} else {
						testName = false;
					}
					String date = getChangeString();
					String[] Split1 = date.split("/");

					boolean testDate = true;

					for (int i = 0; i < date.length(); i++) {
						if (Split1.length != 3) {
							testDate = false;
							break;
						} else if ((date.charAt(i) >= 65 && date.charAt(i) <= 90)
								|| (date.charAt(i) >= 97 && date.charAt(i) <= 122)) {
							testDate = false;
							break;
						} else if (Integer.parseInt(Split1[1]) > 31 || Integer.parseInt(Split1[1]) <= 0
								|| (Integer.parseInt(Split1[2]) == 2024 && Integer.parseInt(Split1[1]) > 30)) {
							testDate = false;
							break;
						} else if (Integer.parseInt(Split1[0]) > 12 || Integer.parseInt(Split1[0]) <= 0
								|| (Integer.parseInt(Split1[2]) == 2024 && Integer.parseInt(Split1[0]) > 5)) {
							testDate = false;
							break;
						} else if (Integer.parseInt(Split1[2]) > 2024 || Integer.parseInt(Split1[2]) <= 0) {
							testDate = false;
							break;
						}

					}

					int Age;
					if (tfAge.getText().equals("")) {
						Age = -1;
					} else {
						Age = Integer.parseInt(tfAge.getText());
					}

					boolean testAge = false;
					if (Age >= 0 && Age <= 120) {
						testAge = true;
					} else if (tfAge.getText().equals("")) {
						Age = -1;
						testAge = true;
					}
					boolean testgender = false;
					if (tfGender.getText().equalsIgnoreCase("M") || tfGender.getText().equalsIgnoreCase("F")
							|| tfGender.getText().equalsIgnoreCase("N/A")) {
						testgender = true;
					}

					if (testDate == true && testAge == true && testgender == true && testName == true) {
						Martyr newMartyr = new Martyr(tfName.getText(), Age, tfGender.getText().toUpperCase());
						MartyrDate searchAboutDate = new MartyrDate(getChangeString());
						TNode<MartyrDate> searchAboutDateNode = selectedLoca.getData().getDateTree()
								.find(searchAboutDate);
						if (searchAboutDateNode != null) {
							Node<Martyr> findName = searchAboutDateNode.getData().getMartyrList().find(newMartyr);
							if (findName != null) {
								LblResult.setText("The name is already added");
							} else {
								searchAboutDateNode.getData().getMartyrList().insert(newMartyr);
								LblResult.setText("Add Successfuly");
							}
						} else {
							searchAboutDate.getMartyrList().insert(newMartyr);
							selectedLoca.getData().getDateTree().insert(searchAboutDate);
							LblResult.setText("Add Successfuly");
						}
					} else {
						LblResult.setText("Add not Successfuly");
					}
				} catch (Exception ex) {
					LblResult.setText("Erorr");
				}

			}
		});
		Button butBack = new Button("Back");
		butBack.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox lblAndButBackBox = new VBox(10, LblResult, butBack);
		lblAndButBackBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(tfName, 1, 0);
		gpPane.add(tfAge, 1, 2);
		gpPane.add(datePicker, 1, 1);
		gpPane.add(tfGender, 1, 3);
		gpPane.add(butInsert, 1, 4);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		gpPane.setAlignment(Pos.CENTER);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(lblAndButBackBox);
		InsertPane.setAlignment(lblAndButBackBox, Pos.CENTER);
		Scene scene = new Scene(InsertPane, 400, 250);
		stage.setTitle("insert Martyr");
		stage.setScene(scene);
		stage.show();
	}

	public void deleteMartyr(Stage stage) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		Label LblResult = new Label("");
		ComboBox<Martyr> comboList = new ComboBox<>();
		Button butDelete = new Button("Delete");
		LinkedListQueue<Martyr> temp = selectedMartyrDate.getData().getMartyrList().traverse();

		while (!temp.isEmpty()) {
			comboList.getItems().add(temp.dequeue());
		}

		comboList.setOnAction(e -> {
			if (comboList.getValue() != null)
				setChangeMartyr(comboList.getValue());
		});

		butDelete.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to delete the martyr?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				Node<Martyr> martyr = selectedMartyrDate.getData().getMartyrList().find(getChangeMartyr());
				if (martyr != null && selectedMartyrDate.getData().deleteMartyer(martyr.getData()) == true) {
					LblResult.setText("Done");
					comboList.getItems().clear();
					LinkedListQueue<Martyr> newTemp = selectedMartyrDate.getData().getMartyrList().traverse();
					while (!newTemp.isEmpty()) {
						comboList.getItems().add(newTemp.dequeue());
					}
				} else if (martyr == null) {
					LblResult.setText("Choose currect value");
				} else {
					LblResult.setText("The Martyr is not in the system");
				}

			}
		});
		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		GridPane gpPane = new GridPane();
		gpPane.add(comboList, 1, 2);
		gpPane.add(butDelete, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 600, 200);
		stage.setTitle("delete Martyer");
		stage.setScene(scene);
		stage.show();

	}

	public void UpdateMartyrScrean(Stage stage) {
		GridPane gpPane = new GridPane();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		Label LblResult = new Label("");
		TextField tfEnterSelection = new TextField();
		tfEnterSelection.setPromptText("Enter The update");
		Button butUpdate = new Button("Update");
		DatePicker datePicker = new DatePicker();
		datePicker.setEditable(false);
		datePicker.setOnAction(event -> {
			try {
				LocalDate date = datePicker.getValue();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				String formattedDate = date.format(formatter);
				setChangeString(formattedDate);
			} catch (DateTimeParseException ex) {
				LblResult.setText("Enter True Date");
			}
		});
		ComboBox<Martyr> comboList = new ComboBox<>();
		LinkedListQueue<Martyr> temp = selectedMartyrDate.getData().getMartyrList().traverse();
		comboList.setOnAction(e -> {
			if (comboList.getValue() != null)
				setChangeMartyr(comboList.getValue());

		});
		while (!temp.isEmpty()) {
			comboList.getItems().add(temp.dequeue());
		}

		ComboBox<String> selectComBox = new ComboBox<>();
		selectComBox.getItems().setAll("Name", "Date", "Age", "Gender");
		selectComBox.setOnAction(e -> {

			try {

				if (selectComBox.getValue() != null && selectComBox.getValue().equals("Date")) {
					gpPane.getChildren().remove(tfEnterSelection);
					gpPane.add(datePicker, 1, 1);
				} else {
					gpPane.getChildren().remove(datePicker);
					gpPane.add(tfEnterSelection, 1, 1);
				}
			} catch (Exception e2) {
			}
		});
		butUpdate.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to delete the martyr?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				if (getChangeMartyr() != null) {
					Node<Martyr> martyr = selectedMartyrDate.getData().getMartyrList().find(getChangeMartyr());
					if (martyr != null) {
						try {
							switch (selectComBox.getValue()) {
							case "Name":
								boolean testName = true;
								String nameMartyr = tfEnterSelection.getText();
								String[] splitName = nameMartyr.split(" ");
								if (splitName.length >= 3) {
									for (int i = 0; i < nameMartyr.length(); i++) {
										if (nameMartyr.charAt(i) > 48 && nameMartyr.charAt(i) < 57) {
											testName = false;
											break;
										}
									}
								} else {
									testName = false;
								}
								if (testName == true) {
									martyr.getData().setName(nameMartyr);
									LblResult.setText("Done");
								} else {
									LblResult.setText("We the new Name is not true");
								}
								break;
							case "Date":
								String date = getChangeString();
								String[] Split1 = date.split("/");

								boolean testDate = true;
								for (int i = 0; i < date.length(); i++) {
									if (Split1.length != 3) {
										testDate = false;
										break;
									} else if ((date.charAt(i) >= 65 && date.charAt(i) <= 90)
											|| (date.charAt(i) >= 97 && date.charAt(i) <= 122)) {
										testDate = false;
										break;
									} else if (Integer.parseInt(Split1[1]) > 31 || Integer.parseInt(Split1[1]) <= 0
											|| (Integer.parseInt(Split1[2]) == 2024
													&& Integer.parseInt(Split1[1]) > 30)) {
										testDate = false;
										break;
									} else if (Integer.parseInt(Split1[0]) > 12 || Integer.parseInt(Split1[0]) <= 0
											|| (Integer.parseInt(Split1[2]) == 2024
													&& Integer.parseInt(Split1[0]) > 5)) {
										testDate = false;
										break;
									} else if (Integer.parseInt(Split1[2]) > 2024 || Integer.parseInt(Split1[2]) <= 0) {
										testDate = false;
										break;
									}
								}

								if (testDate == true) {
									MartyrDate newDate = new MartyrDate(date);
									selectedMartyrDate.getData().getMartyrList().delete(changeMartyr);
									TNode<MartyrDate> newMartyrDate = selectedLoca.getData().getDateTree()
											.find(newDate);
									if (newMartyrDate != null) {
										newMartyrDate.getData().getMartyrList().insert(changeMartyr);
									} else {
										newDate.getMartyrList().insert(changeMartyr);
										selectedLoca.getData().getDateTree().insert(newDate);
									}
									LblResult.setText("Done");
								} else {
									LblResult.setText("We the new Date is not true");
								}
								break;
							case "Age":
								int Age = Integer.parseInt(tfEnterSelection.getText());

								boolean testAge = false;
								if (Age >= 0 && Age <= 120) {
									testAge = true;
								} else if (tfEnterSelection.getText().equals("")) {
									Age = -1;
									testAge = true;
								}
								if (testAge == true) {
									martyr.getData().setAge(Age);
									LblResult.setText("Done");
								} else {
									LblResult.setText("We the new Age is not true");
								}
								break;
							case "Gender":
								boolean testgender = false;
								if (tfEnterSelection.getText().equalsIgnoreCase("M")
										|| tfEnterSelection.getText().equalsIgnoreCase("F")
										|| tfEnterSelection.getText().equalsIgnoreCase("N/A")) {
									testgender = true;
								}
								if (testgender == true) {
									Martyr updateMartyr = martyr.getData();
									selectedMartyrDate.getData().getMartyrList().delete(martyr.getData());
									updateMartyr.setGender(tfEnterSelection.getText().toUpperCase());
									selectedMartyrDate.getData().getMartyrList().insert(updateMartyr);
									LblResult.setText("Done");
								} else {
									LblResult.setText("We the new Gender is not true");
								}
								break;
							default:
								LblResult.setText("Error");
							}
							comboList.getItems().clear();
							LinkedListQueue<Martyr> tempList = selectedMartyrDate.getData().getMartyrList().traverse();
							while (!tempList.isEmpty()) {
								comboList.getItems().add(tempList.dequeue());
							}
						} catch (Exception ex) {
							LblResult.setText("Error");
						}
					} else {
						LblResult.setText("The Martyr is not in the system");
					}

				} else {
					LblResult.setText("select Martyr!");
				}
			}
		});

		Button back = new Button("Back");
		back.setOnAction(e -> {
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure you want to close the window?");
			alert.setContentText("You will lose all the data you entered");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
				martyrScreen(stage);
			}
		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("Choose your option.");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {

				Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
				saveAlert.setTitle("Confirm Save");
				saveAlert.setHeaderText("Do you want to save the data in a file?");
				saveAlert.setContentText("Choose your option.");
				saveAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				result = saveAlert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.YES) {
					insertToFile();
				}
				stage.close();
			}
		});
		VBox buttVBox = new VBox(10, LblResult, back);
		buttVBox.setAlignment(Pos.CENTER);
		gpPane.add(selectComBox, 0, 1);
		gpPane.add(comboList, 1, 2);
		gpPane.add(butUpdate, 0, 2);
		gpPane.setAlignment(Pos.CENTER);
		gpPane.setHgap(10);
		gpPane.setVgap(10);
		LblResult.setAlignment(Pos.CENTER);
		LblResult.setPadding(new Insets(15));
		BorderPane InsertPane = new BorderPane();
		InsertPane.setCenter(gpPane);
		InsertPane.setBottom(buttVBox);
		Scene scene = new Scene(InsertPane, 600, 200);
		stage.setTitle("update Martyer");
		stage.setScene(scene);
		stage.show();

	}

	public LinkedListQueue<Martyr> insertInSortedOrder(LinkedListQueue<Martyr> queue) {
		if (queue.isEmpty()) {
			return queue; // If the queue is empty, return it directly
		}

		LinkedListQueue<Martyr> sortedQueue = new LinkedListQueue<>();
		while (!queue.isEmpty()) {
			Martyr current = queue.dequeue();
			boolean inserted = false;
			int size = sortedQueue.size(); // Keep track of initial size of sortedQueue

			// Temporary queue to hold elements while looking for insertion point
			LinkedListQueue<Martyr> tempQueue = new LinkedListQueue<>();

			for (int i = 0; i < size; i++) {
				Martyr fromSorted = sortedQueue.dequeue();
				if (!inserted && current.getName().trim().compareToIgnoreCase(fromSorted.getName().trim()) < 0) {
					tempQueue.enqueue(current);
					inserted = true;
				}
				tempQueue.enqueue(fromSorted);
			}

			if (!inserted) {
				tempQueue.enqueue(current);
			}

			while (!tempQueue.isEmpty()) {
				sortedQueue.enqueue(tempQueue.dequeue());
			}
		}
		return sortedQueue;
	}

	public void insertToFile() {
		Stage stage = new Stage();
		Label Tmasseg = new Label();
		Tmasseg.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 20px; -fx-font-weight: bold;");
		Tmasseg.setPadding(new Insets(15));
		Button load = new Button("Load From File");
		load.setStyle("-fx-font-family: 'Cairo'; -fx-font-size: 15px;");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		load.setOnAction(e -> {
			String result = "";
			filechooser.setTitle("Choose your file");
			filechooser.setInitialDirectory(new File("c:\\"));
			filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File.csv", "*.csv"));
			file = filechooser.showOpenDialog(stage);
			if (file != null) {
				if (file.exists()) {
					try {
						PrintWriter fileWriter = new PrintWriter(file);
						fileWriter.println("Name,event,Age,location,District,Gender");
						try {
							distractTree.traverseInOrder();
							QStack<District> districtsStack = distractTree.getNextStack();
							while (!districtsStack.isEmpty()) {
								District theSelectedDistrict = districtsStack.pop();
								theSelectedDistrict.getLocationTree().traverseLevelByLevel();
								QStack<Location> LocationStack = theSelectedDistrict.getLocationTree().getNextStack();
								while (!LocationStack.isEmpty()) {
									Location theSelectedLocation = LocationStack.pop();
									theSelectedLocation.getDateTree().traverseLevelByLevel();
									QStack<MartyrDate> theMartyrDateStack = theSelectedLocation.getDateTree()
											.getNextStack();
									while (!theMartyrDateStack.isEmpty()) {
										MartyrDate theSelectedMartyrDate = theMartyrDateStack.pop();
										LinkedListQueue<Martyr> MartyrQueue = theSelectedMartyrDate.getMartyrList()
												.traverse();
										while (!MartyrQueue.isEmpty()) {
											Martyr theSelectedMartyr = MartyrQueue.dequeue();
											String name = "";
											String date = "";
											String age = "";
											String location = "";
											String district = "";
											String gender = "";
											name = theSelectedMartyr.getName();
											date = theSelectedMartyrDate.getDate();
											if (theSelectedMartyr.getAge() > -1)
												age =Integer.toString(theSelectedMartyr.getAge());
											else
												age = "";
											location = theSelectedLocation.getLocationName();
											district = theSelectedDistrict.getDistrictName();
											gender = theSelectedMartyr.getGender();
											result = name.trim() + "," + date.trim() + "," + age.trim() + "," + location.trim() + "," + district.trim()
													+ "," + gender.trim();
											fileWriter.println(result);
										}
									}
								}
							}
							Tmasseg.setText("Well Done");
							Tmasseg.setTextFill(Color.BLACK);

						} catch (NumberFormatException ex) {
							Tmasseg.setText("We Can't find the file!");
							Tmasseg.setTextFill(Color.RED);
						} catch (Exception ex) {
							Tmasseg.setText("Done,with some problem");
							Tmasseg.setTextFill(Color.RED);
						}

						fileWriter.close();
					} catch (IOException ex) {
						Tmasseg.setText("We Can't find the file!");
						Tmasseg.setTextFill(Color.RED);
					}

					stage.close();
					distrctScreen(stage);
				}
			} else {
				Tmasseg.setText("the system did not find your file");
			}

		});
		stage.setOnCloseRequest(event -> {
			event.consume();

			Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
			exitAlert.setTitle("Confirm Exit");
			exitAlert.setHeaderText("Are you sure you want to exit?");
			exitAlert.setContentText("All modifications will be lost!");
			Optional<ButtonType> result = exitAlert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {
				stage.close();
			}
		});
		root.setCenter(load);
		root.setBottom(Tmasseg);
		root.setAlignment(Tmasseg, Pos.CENTER);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public String getChangeString() {
		return changeString;
	}

	public void setChangeString(String changeString) {
		this.changeString = changeString;
	}

	public Martyr getChangeMartyr() {
		return changeMartyr;
	}

	public void setChangeMartyr(Martyr changeMartyr) {
		this.changeMartyr = changeMartyr;
	}

}