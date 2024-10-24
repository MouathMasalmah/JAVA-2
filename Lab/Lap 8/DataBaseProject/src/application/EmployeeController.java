package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import application.classes.Employee;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeeController {
	private Connection connection;
	private PreparedStatement preparedStatement;

	public EmployeeController() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/royal?useSSL=false", "root",
				"Mouath@123");
	}

	@FXML
	private Button butBack, butDone, butShow;
	@FXML
	private CheckBox cbAddress, cbEmail, cbGender, cbName, cbPassword, cbPhone, cbSalary, cbtype;
	@FXML
	private RadioButton rbAdd, rbCasher, rbDelete, rbFemale, rbMale, rbManager, rbUpdate, rdMore, rdEqual, rdLess;
	@FXML
	private TableView<Employee> tableView;
	@FXML
	private TableColumn<Employee, String> tcAddress, tcEmail, tcGender, tcName, tcPassword, tcPhone, tcType;
	@FXML
	private TableColumn<Employee, Integer> tcId, tcSalary;
	@FXML
	private TextField tfAddress, tfEmail, tfId, tfName, tfPassword, tfPhone, tfSalary, tfSearch, tfCompare, tfTotal,
			tfAvg;

	@FXML
	void BackButt(ActionEvent event) {

	}

	@FXML
	void CheckType(ActionEvent event) {
		if (cbtype.isSelected()) {
			cbPhone.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			cbGender.setSelected(false);
			cbName.setSelected(false);
			cbSalary.setSelected(false);
			cbPassword.setSelected(false);
			tfAddress.setDisable(true);
			tfPhone.setDisable(true);
			tfEmail.setDisable(true);
			tfPassword.setDisable(true);
			tfSalary.setDisable(true);
			rbFemale.setDisable(true);
			rbMale.setDisable(true);
			rbManager.setDisable(false);
			rbCasher.setDisable(false);
			tfName.setDisable(true);
		} else {
			rbManager.setDisable(true);
			rbCasher.setDisable(true);
		}
	}

	@FXML
	void cheakName(ActionEvent event) {
		if (cbName.isSelected()) {
			cbPhone.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			cbGender.setSelected(false);
			cbtype.setSelected(false);
			cbSalary.setSelected(false);
			cbPassword.setSelected(false);
			tfAddress.setDisable(true);
			tfPhone.setDisable(true);
			tfEmail.setDisable(true);
			tfPassword.setDisable(true);
			tfSalary.setDisable(true);
			rbFemale.setDisable(true);
			rbMale.setDisable(true);
			rbManager.setDisable(true);
			rbCasher.setDisable(true);
			tfName.setDisable(false);
		} else {
			tfName.setDisable(true);
		}
	}

	@FXML
	void checkAddress(ActionEvent event) {
		if (cbAddress.isSelected()) {
			cbPhone.setSelected(false);
			cbName.setSelected(false);
			cbEmail.setSelected(false);
			cbGender.setSelected(false);
			cbtype.setSelected(false);
			cbSalary.setSelected(false);
			cbPassword.setSelected(false);
			tfAddress.setDisable(false);
			tfPhone.setDisable(true);
			tfEmail.setDisable(true);
			tfPassword.setDisable(true);
			tfSalary.setDisable(true);
			rbFemale.setDisable(true);
			rbMale.setDisable(true);
			rbManager.setDisable(true);
			rbCasher.setDisable(true);
			tfName.setDisable(true);
		} else {
			tfAddress.setDisable(true);
		}
	}

	@FXML
	void checkEmail(ActionEvent event) {
		if (cbEmail.isSelected()) {
			cbPhone.setSelected(false);
			cbName.setSelected(false);
			cbAddress.setSelected(false);
			cbGender.setSelected(false);
			cbtype.setSelected(false);
			cbSalary.setSelected(false);
			cbPassword.setSelected(false);
			tfAddress.setDisable(true);
			tfPhone.setDisable(true);
			tfEmail.setDisable(false);
			tfPassword.setDisable(true);
			tfSalary.setDisable(true);
			rbFemale.setDisable(true);
			rbMale.setDisable(true);
			rbManager.setDisable(true);
			rbCasher.setDisable(true);
			tfName.setDisable(true);
		} else {
			tfEmail.setDisable(true);
		}
	}

	@FXML
	void checkGender(ActionEvent event) {
		if (cbGender.isSelected()) {
			cbPhone.setSelected(false);
			cbName.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			cbtype.setSelected(false);
			cbSalary.setSelected(false);
			cbPassword.setSelected(false);
			tfAddress.setDisable(true);
			tfPhone.setDisable(true);
			tfEmail.setDisable(true);
			tfPassword.setDisable(true);
			tfSalary.setDisable(true);
			rbFemale.setDisable(false);
			rbMale.setDisable(false);
			rbManager.setDisable(true);
			rbCasher.setDisable(true);
			tfName.setDisable(false);
		} else {
			rbFemale.setDisable(true);
			rbMale.setDisable(true);
		}
	}

	@FXML
	void checkPassword(ActionEvent event) {
		if (cbPassword.isSelected()) {
			cbPhone.setSelected(false);
			cbName.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			cbtype.setSelected(false);
			cbSalary.setSelected(false);
			cbGender.setSelected(false);
			tfAddress.setDisable(true);
			tfPhone.setDisable(true);
			tfEmail.setDisable(true);
			tfPassword.setDisable(false);
			tfSalary.setDisable(true);
			rbFemale.setDisable(true);
			rbMale.setDisable(true);
			rbManager.setDisable(true);
			rbCasher.setDisable(true);
			tfName.setDisable(true);
		} else {
			tfPassword.setDisable(true);
		}
	}

	@FXML
	void checkPhone(ActionEvent event) {
		if (cbPhone.isSelected()) {
			cbPassword.setSelected(false);
			cbName.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			cbtype.setSelected(false);
			cbSalary.setSelected(false);
			cbGender.setSelected(false);
			tfAddress.setDisable(true);
			tfPhone.setDisable(false);
			tfEmail.setDisable(true);
			tfPassword.setDisable(true);
			tfSalary.setDisable(true);
			rbFemale.setDisable(true);
			rbMale.setDisable(true);
			rbManager.setDisable(true);
			rbCasher.setDisable(true);
			tfName.setDisable(true);
		} else {
			tfPhone.setDisable(true);
		}
	}

	@FXML
	void checkSalary(ActionEvent event) {
		if (cbSalary.isSelected()) {
			cbPassword.setSelected(false);
			cbName.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			cbtype.setSelected(false);
			cbPhone.setSelected(false);
			cbGender.setSelected(false);
			tfAddress.setDisable(true);
			tfPhone.setDisable(true);
			tfEmail.setDisable(true);
			tfPassword.setDisable(true);
			tfSalary.setDisable(false);
			rbFemale.setDisable(true);
			rbMale.setDisable(true);
			rbManager.setDisable(true);
			rbCasher.setDisable(true);
			tfName.setDisable(true);
		} else {
			tfSalary.setDisable(true);
		}
	}

	@FXML
	void selectAdd(ActionEvent event) {
		if (rbAdd.isSelected()) {
			rbUpdate.setSelected(false);
			rbDelete.setSelected(false);

			cbPassword.setSelected(false);
			cbName.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			cbtype.setSelected(false);
			cbSalary.setSelected(false);
			cbGender.setSelected(false);
			cbPhone.setSelected(false);

			cbPassword.setDisable(true);
			cbName.setDisable(true);
			cbAddress.setDisable(true);
			cbEmail.setDisable(true);
			cbtype.setDisable(true);
			cbSalary.setDisable(true);
			cbGender.setDisable(true);
			cbPhone.setDisable(true);

			tfAddress.setDisable(false);
			tfPhone.setDisable(false);
			tfEmail.setDisable(false);
			tfPassword.setDisable(false);
			tfSalary.setDisable(false);
			rbFemale.setDisable(false);
			rbMale.setDisable(false);
			rbManager.setDisable(false);
			rbCasher.setDisable(false);
			tfName.setDisable(false);
			tfId.setDisable(true);
		}
	}

	@FXML
	void selectCashire(ActionEvent event) {
		if (rbCasher.isSelected()) {
			rbManager.setSelected(false);
		}
	}

	@FXML
	void selectDelete(ActionEvent event) {
		if (rbDelete.isSelected()) {
			rbUpdate.setSelected(false);
			rbAdd.setSelected(false);

			cbPassword.setSelected(false);
			cbName.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			cbtype.setSelected(false);
			cbSalary.setSelected(false);
			cbGender.setSelected(false);
			cbPhone.setSelected(false);

			cbPassword.setDisable(true);
			cbName.setDisable(true);
			cbAddress.setDisable(true);
			cbEmail.setDisable(true);
			cbtype.setDisable(true);
			cbSalary.setDisable(true);
			cbGender.setDisable(true);
			cbPhone.setDisable(true);

			tfAddress.setDisable(true);
			tfPhone.setDisable(true);
			tfEmail.setDisable(true);
			tfPassword.setDisable(true);
			tfSalary.setDisable(true);
			rbFemale.setDisable(true);
			rbMale.setDisable(true);
			rbManager.setDisable(true);
			rbCasher.setDisable(true);
			tfName.setDisable(true);
			tfId.setDisable(false);
		}
	}

	@FXML
	void selectFemale(ActionEvent event) {
		if (rbFemale.isSelected()) {
			rbMale.setSelected(false);
		}
	}

	@FXML
	void selectMale(ActionEvent event) {
		if (rbMale.isSelected()) {
			rbFemale.setSelected(false);
		}
	}

	@FXML
	void selectManager(ActionEvent event) {
		if (rbManager.isSelected()) {
			rbCasher.setSelected(false);
		}
	}

	@FXML
	void selectUpdate(ActionEvent event) {
		if (rbUpdate.isSelected()) {
			rbDelete.setSelected(false);
			rbAdd.setSelected(false);

			cbPassword.setSelected(false);
			cbName.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			cbtype.setSelected(false);
			cbSalary.setSelected(false);
			cbGender.setSelected(false);
			cbPhone.setSelected(false);

			cbPassword.setDisable(false);
			cbName.setDisable(false);
			cbAddress.setDisable(false);
			cbEmail.setDisable(false);
			cbtype.setDisable(false);
			cbSalary.setDisable(false);
			cbGender.setDisable(false);
			cbPhone.setDisable(false);

			tfAddress.setDisable(true);
			tfPhone.setDisable(true);
			tfEmail.setDisable(true);
			tfPassword.setDisable(true);
			tfSalary.setDisable(true);
			rbFemale.setDisable(true);
			rbMale.setDisable(true);
			rbManager.setDisable(true);
			rbCasher.setDisable(true);
			tfName.setDisable(true);
			tfId.setDisable(false);
		}
	}

	private void insertRecord() {
		if (validateInput()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO employee (employee_name, employee_email, employee_address, employee_position, salary, employee_password, employee_gender) VALUES (?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS)) {

				String newPassword = tfPassword.getText();
				String newEmail = tfEmail.getText();

				if (isPasswordInUse(newPassword)) {
					showAlert(1, "Error", "Password is already in use. Please choose a different password.");
					return;
				}

				if (newEmail.toLowerCase().contains("e-mail")) {
					showAlert(1, "Error",
							"Email cannot contain the word 'e-mail'. Please choose a different email address.");
					return;
				}

				preparedStatement.setString(1, tfName.getText());
				preparedStatement.setString(2, newEmail);
				preparedStatement.setString(3, tfAddress.getText());
				preparedStatement.setString(4, rbManager.isSelected() ? "Manager" : "Cashier");
				preparedStatement.setInt(5, Integer.parseInt(tfSalary.getText()));
				preparedStatement.setString(6, newPassword);
				preparedStatement.setString(7, rbMale.isSelected() ? "Male" : "Female");
				preparedStatement.executeUpdate();

				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					int employeeId = resultSet.getInt(1);

					try (PreparedStatement phoneStatement = connection
							.prepareStatement("INSERT INTO employee_phone (employee_id, phone_number) VALUES (?, ?)")) {
						phoneStatement.setInt(1, employeeId);
						phoneStatement.setString(2, tfPhone.getText());
						phoneStatement.executeUpdate();
					}
				}

				loadRecords();
				clearFields();
				showAlert(2, "Success", "Record inserted successfully.");
			} catch (SQLException ex) {
				showAlert(1, "Error inserting record", ex.getMessage());
			}
		}
	}

	private void updateEmployee() {
		String employeeIdText = tfId.getText();
		if (employeeIdText.isEmpty()) {
			showAlert(1, "Error", "Please enter an employee ID.");
			return;
		}

		try {
			int employeeId = Integer.parseInt(employeeIdText);
			String name = tfName.getText();
			if (!cbAddress.isSelected() && !cbEmail.isSelected() && !cbGender.isSelected() && !cbName.isSelected()
					&& !cbPassword.isSelected() && !cbPhone.isSelected() && !cbSalary.isSelected()
					&& !cbtype.isSelected()) {
				String address = tfAddress.getText();
				String email = tfEmail.getText();
				String phone = tfPhone.getText();
				String gender = rbMale.isSelected() ? "Male" : "Female";
				String password = tfPassword.getText();
				String position = rbManager.isSelected() ? "Manager" : "Cashier";
				int salary = 0;

				if (isPasswordInUse(password)) {
					showAlert(1, "Error", "New password cannot be the same as any existing password.");
					return;
				}

				if (email.toLowerCase().contains("e-mail")) {
					showAlert(1, "Error",
							"Email cannot contain the word 'e-mail'. Please choose a different email address.");
					return;
				}

				StringBuilder sqlBuilder = new StringBuilder("UPDATE employee SET ");
				List<String> updates = new ArrayList<>();

				if (cbName.isSelected())
					updates.add("employee_name = ?");
				if (cbAddress.isSelected())
					updates.add("employee_address = ?");
				if (cbEmail.isSelected())
					updates.add("employee_email = ?");
				if (cbGender.isSelected())
					updates.add("employee_gender = ?");
				if (cbPassword.isSelected())
					updates.add("employee_password = ?");
				if (cbtype.isSelected())
					updates.add("employee_position = ?");
				if (cbSalary.isSelected()) {
					salary = Integer.parseInt(tfSalary.getText());
					updates.add("salary = ?");
				}

				if (!updates.isEmpty()) {
					String updatesStr = String.join(", ", updates);
					sqlBuilder.append(updatesStr);
					sqlBuilder.append(" WHERE employee_id = ?");
					String sql = sqlBuilder.toString();

					try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
						int parameterIndex = 1;
						if (cbName.isSelected())
							preparedStatement.setString(parameterIndex++, name);

						if (cbAddress.isSelected())
							preparedStatement.setString(parameterIndex++, address);
						if (cbEmail.isSelected())
							preparedStatement.setString(parameterIndex++, email);
						if (cbGender.isSelected())
							preparedStatement.setString(parameterIndex++, gender);
						if (cbPassword.isSelected())
							preparedStatement.setString(parameterIndex++, password);
						if (cbtype.isSelected())
							preparedStatement.setString(parameterIndex++, position);
						if (cbSalary.isSelected())
							preparedStatement.setInt(parameterIndex++, salary);
						preparedStatement.setInt(parameterIndex, employeeId);
						preparedStatement.executeUpdate();
					}

					if (cbPhone.isSelected()) {
						try (PreparedStatement phoneStatement = connection
								.prepareStatement("UPDATE employee_phone SET phone_number = ? WHERE employee_id = ?")) {
							phoneStatement.setString(1, phone);
							phoneStatement.setInt(2, employeeId);
							phoneStatement.executeUpdate();
						}
					}

					loadRecords();
					clearFields();
					showAlert(2, "Success", "Employee updated successfully.");
				}
			} else {
				showAlert(1, "Erorr", "Select Choose to update");
			}
		} catch (SQLException | NumberFormatException ex) {
			ex.printStackTrace();
			showAlert(1, "Error updating record", ex.getMessage());
		}
	}

	private void deleteEmployee(int employeeId) {
		try {
			try (PreparedStatement phoneStatement = connection
					.prepareStatement("DELETE FROM employee_phone WHERE employee_id = ?")) {
				phoneStatement.setInt(1, employeeId);
				phoneStatement.executeUpdate();
			}

			try (PreparedStatement employeeStatement = connection
					.prepareStatement("DELETE FROM employee WHERE employee_id = ?")) {
				employeeStatement.setInt(1, employeeId);
				int rowsAffected = employeeStatement.executeUpdate();
				if (rowsAffected == 0) {
					showAlert(1, "Error", "No employee found with ID: " + employeeId);
				} else {
					loadRecords();
					clearFields();
					showAlert(2, "Success", "Employee deleted successfully.");
				}
			}
		} catch (SQLException ex) {
			showAlert(1, "Error deleting record", ex.getMessage());
		}
	}

	@FXML
	void DoneBut(ActionEvent event) {
		if (rbAdd.isSelected()) {
			insertRecord();
		} else if (rbDelete.isSelected()) {
			String idText = tfId.getText();
			if (!idText.isEmpty()) {
				try {
					int employeeId = Integer.parseInt(idText);
					deleteEmployee(employeeId);
				} catch (NumberFormatException e) {
					showAlert(1, "Error", "Invalid ID format. Please enter a valid integer.");
				}
			} else {
				showAlert(1, "Error", "Please enter an employee ID.");
			}
		} else if (rbUpdate.isSelected()) {
			updateEmployee();
		} else {
			showAlert(1, "Error", "Please select your choice.");
		}
		loadRecords(); // Reload records and apply row styling
	}

	@FXML
	void searchByName(ActionEvent event) {
		String searchText = tfSearch.getText().trim();
		loadRecords(searchText);
	}

	private void loadRecords() {
		calculateTotalAndAverageSalaries();
		calculateTotalSalaries();
		ObservableList<Employee> employeeList = FXCollections.observableArrayList();
		String query = "SELECT e.employee_id, e.employee_name, e.employee_address, e.employee_email, e.employee_position, "
				+ "e.salary, e.employee_password, e.employee_gender, ep.phone_number "
				+ "FROM employee e LEFT JOIN employee_phone ep ON e.employee_id = ep.employee_id";

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				int id = resultSet.getInt("employee_id");
				String name = resultSet.getString("employee_name");
				String address = resultSet.getString("employee_address");
				String email = resultSet.getString("employee_email");
				String position = resultSet.getString("employee_position");
				int salary = resultSet.getInt("salary");
				String password = resultSet.getString("employee_password");
				String gender = resultSet.getString("employee_gender");
				String phone = resultSet.getString("phone_number");

				Employee employee = new Employee(id, name, phone, email, position, address, salary, password, gender);
				employeeList.add(employee);
			}

			tableView.setItems(employeeList);
			applyRowStyling();

			tcId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
			tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
			tcAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
			tcEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
			tcType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition()));
			tcSalary.setCellValueFactory(
					cellData -> new SimpleIntegerProperty(cellData.getValue().getSalary()).asObject());
			tcGender.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
			tcPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
			tcPassword.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));

		} catch (SQLException ex) {
			showAlert(1, "Error loading records", ex.getMessage());
		}
	}

	private void loadRecords(String searchText) {
		calculateTotalAndAverageSalaries();
		calculateTotalSalaries();
		ObservableList<Employee> employeeList = FXCollections.observableArrayList();
		String query = "SELECT e.employee_id, e.employee_name, e.employee_address, e.employee_email, e.employee_position, "
				+ "e.salary, e.employee_password, e.employee_gender, ep.phone_number "
				+ "FROM employee e LEFT JOIN employee_phone ep ON e.employee_id = ep.employee_id ";

		if (!searchText.isEmpty()) {
			query += "WHERE e.employee_name LIKE '%" + searchText + "%'";
		}

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				int id = resultSet.getInt("employee_id");
				String name = resultSet.getString("employee_name");
				String address = resultSet.getString("employee_address");
				String email = resultSet.getString("employee_email");
				String position = resultSet.getString("employee_position");
				int salary = resultSet.getInt("salary");
				String password = resultSet.getString("employee_password");
				String gender = resultSet.getString("employee_gender");
				String phone = resultSet.getString("phone_number");

				Employee employee = new Employee(id, name, phone, email, position, address, salary, password, gender);
				employeeList.add(employee);
			}

			tableView.getItems().clear();
			tableView.setItems(employeeList);
			applyRowStyling();

			tcId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
			tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
			tcAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
			tcEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
			tcType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition()));
			tcSalary.setCellValueFactory(
					cellData -> new SimpleIntegerProperty(cellData.getValue().getSalary()).asObject());
			tcGender.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
			tcPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
			tcPassword.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));

		} catch (SQLException ex) {
			showAlert(1, "Error loading records", ex.getMessage());
		}
	}

	private int getMaxSalary() {
		String query = "SELECT MAX(salary) AS max_salary FROM employee";
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			if (resultSet.next()) {
				return resultSet.getInt("max_salary");
			}
		} catch (SQLException ex) {
			showAlert(1, "Error fetching max salary", ex.getMessage());
		}
		return 0;
	}

	private int getMinSalary() {
		String query = "SELECT MIN(salary) AS min_salary FROM employee";
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			if (resultSet.next()) {
				return resultSet.getInt("min_salary");
			}
		} catch (SQLException ex) {
			showAlert(1, "Error fetching min salary", ex.getMessage());
		}
		return 0;
	}

	@FXML
	void moreSalary(ActionEvent event) {
		if (rdMore.isSelected()) {
			rdEqual.setSelected(false);
			rdLess.setSelected(false);
			String salaryText = tfCompare.getText().trim();
			if (!salaryText.isEmpty()) {
				try {
					int salary = Integer.parseInt(salaryText);
					loadRecordsBySalary(">", salary);
				} catch (NumberFormatException ex) {
					showAlert(1, "Error", "Invalid salary format. Please enter a valid number.");
				}
			} else {
				showAlert(1, "Error", "Please enter a salary.");
			}
		}
	}

	@FXML
	void equalSalary(ActionEvent event) {
		if (rdEqual.isSelected()) {
			rdMore.setSelected(false);
			rdLess.setSelected(false);
			String salaryText = tfCompare.getText().trim();
			if (!salaryText.isEmpty()) {
				try {
					int salary = Integer.parseInt(salaryText);
					loadRecordsBySalary("=", salary);
				} catch (NumberFormatException ex) {
					showAlert(1, "Error", "Invalid salary format. Please enter a valid number.");
				}
			} else {
				showAlert(1, "Error", "Please enter a salary.");
			}
		}
	}

	@FXML
	void lessSalary(ActionEvent event) {
		if (rdLess.isSelected()) {
			rdEqual.setSelected(false);
			rdMore.setSelected(false);
			String salaryText = tfCompare.getText().trim();
			if (!salaryText.isEmpty()) {
				try {
					int salary = Integer.parseInt(salaryText);
					loadRecordsBySalary("<", salary);
				} catch (NumberFormatException ex) {
					showAlert(1, "Error", "Invalid salary format. Please enter a valid number.");
				}
			} else {
				showAlert(1, "Error", "Please enter a salary.");
			}
		}
	}

	private void loadRecordsBySalary(String operator, int salary) {
		ObservableList<Employee> employeeList = FXCollections.observableArrayList();
		String query = "SELECT e.employee_id, e.employee_name, e.employee_address, e.employee_email, e.employee_position, "
				+ "e.salary, e.employee_password, e.employee_gender, ep.phone_number "
				+ "FROM employee e LEFT JOIN employee_phone ep ON e.employee_id = ep.employee_id " + "WHERE e.salary "
				+ operator + " ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, salary);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("employee_id");
					String name = resultSet.getString("employee_name");
					String address = resultSet.getString("employee_address");
					String email = resultSet.getString("employee_email");
					String position = resultSet.getString("employee_position");
					int empSalary = resultSet.getInt("salary");
					String password = resultSet.getString("employee_password");
					String gender = resultSet.getString("employee_gender");
					String phone = resultSet.getString("phone_number");

					Employee employee = new Employee(id, name, phone, email, position, address, empSalary, password,
							gender);
					employeeList.add(employee);
				}

				tableView.getItems().clear();
				tableView.setItems(employeeList);

				tcId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
				tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
				tcAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
				tcEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
				tcType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition()));
				tcSalary.setCellValueFactory(
						cellData -> new SimpleIntegerProperty(cellData.getValue().getSalary()).asObject());
				tcGender.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
				tcPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
				tcPassword.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));

				applyRowStyling();

			} catch (SQLException ex) {
				showAlert(1, "Error loading records", ex.getMessage());
			}
		} catch (SQLException ex) {
			showAlert(1, "Error preparing statement", ex.getMessage());
		}
	}

	private void applyRowStyling() {
		int maxSalary = getMaxSalary();
		int minSalary = getMinSalary();

		tableView.setRowFactory(tv -> new TableRow<Employee>() {
			@Override
			protected void updateItem(Employee employee, boolean empty) {
				super.updateItem(employee, empty);
				if (employee == null || empty) {
					setStyle("");
				} else {
					if (employee.getSalary() == maxSalary) {
						setStyle("-fx-background-color: greenyellow;");
					} else if (employee.getSalary() == minSalary) {
						setStyle("-fx-background-color: red;");
					} else {
						setStyle("");
					}
				}
			}

		});
		tableView.refresh();
	}

	private boolean validateInput() {
		if (tfName.getText().isEmpty() || tfEmail.getText().isEmpty() || tfPhone.getText().isEmpty()
				|| tfAddress.getText().isEmpty() || tfSalary.getText().isEmpty() || !isGenderValid() || !isTypeValid()
				|| tfPassword.getText().isEmpty()) {
			showAlert(1, "Validation Error", "All fields must be filled out.");
			return false;
		}

		if (!isValidName(tfName.getText())) {
			showAlert(1, "Validation Error", "Invalid name. Please enter a valid name.");
			return false;
		}

		if (!isValidEmail(tfEmail.getText())) {
			showAlert(1, "Validation Error", "Invalid email. Please enter a valid email address.");
			return false;
		}

		if (!isValidPhone(tfPhone.getText())) {
			showAlert(1, "Validation Error", "Invalid phone number. Please enter a valid phone number.");
			return false;
		}

		if (!isSalaryValid(tfSalary.getText())) {
			showAlert(1, "Validation Error", "Invalid salary. Please enter a positive number.");
			return false;
		}

		if (!isPasswordValid(tfPassword.getText())) {
			showAlert(1, "Validation Error", "Invalid password. Ensure it meets the criteria.");
			return false;
		}

		return true;
	}

	private boolean isValidName(String name) {
		// Example: Check if the name contains only letters and spaces
		return name.matches("[a-zA-Z\\s]+");
	}

	private boolean isValidEmail(String email) {
		// Simple email pattern
		String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(emailRegex);
		return pattern.matcher(email).matches();
	}

	private boolean isValidPhone(String phone) {
		// Check if the phone number contains exactly 10 digits
		return phone.matches("\\d{10}");
	}

	private boolean isPasswordValid(String password) {
		// Example criteria: at least 8 characters long
		if (password == null || password.length() < 8) {
			return false;
		}
		// You can add more criteria such as requiring a mix of letters, numbers, and
		// special characters
		return true;
	}

	private boolean isSalaryValid(String salaryText) {
		try {
			int salary = Integer.parseInt(salaryText);
			return salary > 0; // Ensure the salary is a positive integer
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	private boolean isGenderValid() {
		return rbMale.isSelected() || rbFemale.isSelected();
	}

	private boolean isTypeValid() {
		return rbManager.isSelected() || rbCasher.isSelected();
	}

	private void clearFields() {
		tfId.clear();
		tfName.clear();
		tfAddress.clear();
		tfEmail.clear();
		tfPhone.clear();
		tfPassword.clear();
		tfSalary.clear();
		rbMale.setSelected(false);
		rbFemale.setSelected(false);
		rbManager.setSelected(false);
		rbCasher.setSelected(false);
	}

	private void showAlert(int alertType, String title, String message) {
		Alert alert;
		if (alertType == 1) {
			alert = new Alert(Alert.AlertType.ERROR);
		} else {
			alert = new Alert(Alert.AlertType.INFORMATION);
		}
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	void showButt(ActionEvent event) {
		loadRecords();
	}

	private boolean isPasswordInUse(String password) {
		String query = "SELECT employee_id FROM employee WHERE employee_password = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, password);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next(); // Returns true if a matching password is found
			}
		} catch (SQLException ex) {
			showAlert(1, "Error", "Error checking password: " + ex.getMessage());
			return false;
		}
	}

	private void calculateTotalSalaries() {
		String query = "SELECT SUM(salary) AS total_salary FROM employee";

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			if (resultSet.next()) {
				double totalSalaries = resultSet.getDouble("total_salary");
				tfTotal.setText(String.valueOf(totalSalaries));
			} else {
				tfTotal.setText("0");
			}
		} catch (SQLException ex) {
			showAlert(1, "Error calculating total salaries", ex.getMessage());
		}
	}

	private void calculateTotalAndAverageSalaries() {
		String query = "SELECT SUM(salary) AS total_salary, AVG(salary) AS avg_salary FROM employee";

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			if (resultSet.next()) {
				double totalSalaries = resultSet.getDouble("total_salary");
				double avgSalaries = resultSet.getDouble("avg_salary");
				tfTotal.setText(String.valueOf(totalSalaries));
				tfAvg.setText(String.valueOf(avgSalaries));
			} else {
				tfTotal.setText("0");
				tfAvg.setText("0");
			}
		} catch (SQLException ex) {
			showAlert(1, "Error calculating total and average salaries", ex.getMessage());
		}
	}

}
