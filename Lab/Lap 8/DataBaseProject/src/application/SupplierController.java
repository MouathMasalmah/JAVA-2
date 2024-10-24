package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import application.classes.Customer;
import application.classes.Supplier;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SupplierController {
	private Connection connection;
	private PreparedStatement preparedStatement;

	public SupplierController() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/royal?useSSL=false", "root",
				"Mouath@123");
	}

	@FXML
	private Button butBack;

	@FXML
	private Button butDone;

	@FXML
	private Button butShow;

	@FXML
	private CheckBox cbAddress;

	@FXML
	private CheckBox cbEmail;

	@FXML
	private CheckBox cbName;

	@FXML
	private CheckBox cbPhone;

	@FXML
	private RadioButton rbAdd;

	@FXML
	private RadioButton rbDelete;

	@FXML
	private RadioButton rbUpdate;

	@FXML
	private TextField tfAddress;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfId;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfPhone;

	@FXML
	private TextField tfSearch;

	@FXML
	private TableView<Supplier> tableView;

	@FXML
	private TableColumn<Supplier, String> tcAddress;

	@FXML
	private TableColumn<Supplier, String> tcEmail;

	@FXML
	private TableColumn<Supplier, Integer> tcId;

	@FXML
	private TableColumn<Supplier, String> tcName;

	@FXML
	private TableColumn<Supplier, String> tcPhone;

	@FXML
	void BackButt(ActionEvent event) {
		// Implement navigation logic if needed
	}

	@FXML
	void DoneBut(ActionEvent event) {
		if (rbAdd.isSelected()) {
			insertRecord();
		} else if (rbDelete.isSelected()) {
			String idText = tfId.getText();
			if (!idText.isEmpty()) {
				try {
					int supplierId = Integer.parseInt(idText);
					deleteSupplier(supplierId);
				} catch (NumberFormatException e) {
					showAlert(1, "Error", "Invalid ID format. Please enter a valid integer.");
				}
			} else {
				showAlert(1, "Error", "Please enter a supplier ID.");
			}
		} else if (rbUpdate.isSelected()) {
			updateSupplier();
		} else {
			showAlert(1, "Error", "Please select your choice.");
		}
	}

	@FXML
	void cheakName(ActionEvent event) {
		if (cbName.isSelected()) {
			cbPhone.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			tfAddress.setDisable(true);
			tfPhone.setDisable(true);
			tfEmail.setDisable(true);
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
			tfPhone.setDisable(true);
			tfName.setDisable(true);
			tfEmail.setDisable(true);
			tfAddress.setDisable(false);
		} else {
			tfAddress.setDisable(true);
		}
	}

	@FXML
	void checkEmail(ActionEvent event) {
		if (cbEmail.isSelected()) {
			cbPhone.setSelected(false);
			cbAddress.setSelected(false);
			cbName.setSelected(false);
			tfAddress.setDisable(true);
			tfName.setDisable(true);
			tfPhone.setDisable(true);
			tfEmail.setDisable(false);
		} else {
			tfEmail.setDisable(true);
		}
	}

	@FXML
	void checkPhone(ActionEvent event) {
		if (cbPhone.isSelected()) {
			cbName.setSelected(false);
			cbAddress.setSelected(false);
			cbEmail.setSelected(false);
			tfAddress.setDisable(true);
			tfName.setDisable(true);
			tfEmail.setDisable(true);
			tfPhone.setDisable(false);
		} else {
			tfPhone.setDisable(true);
		}
	}

	@FXML
	void selectAdd(ActionEvent event) {
		if (rbAdd.isSelected()) {
			rbUpdate.setSelected(false);
			rbDelete.setSelected(false);
			cbAddress.setDisable(true);
			cbEmail.setDisable(true);
			cbName.setDisable(true);
			cbPhone.setDisable(true);
			tfAddress.setDisable(false);
			tfEmail.setDisable(false);
			tfName.setDisable(false);
			tfPhone.setDisable(false);
			tfId.setDisable(true);
		}
	}

	@FXML
	void selectDelete(ActionEvent event) {
		if (rbDelete.isSelected()) {
			rbUpdate.setSelected(false);
			rbAdd.setSelected(false);
			cbAddress.setDisable(true);
			cbEmail.setDisable(true);
			cbName.setDisable(true);
			cbPhone.setDisable(true);
			tfAddress.setDisable(true);
			tfEmail.setDisable(true);
			tfName.setDisable(true);
			tfPhone.setDisable(true);
			tfId.setDisable(false);
		}
	}

	@FXML
	void selectUpdate(ActionEvent event) {
		if (rbUpdate.isSelected()) {
			rbDelete.setSelected(false);
			rbAdd.setSelected(false);
			cbAddress.setDisable(false);
			cbEmail.setDisable(false);
			cbName.setDisable(false);
			cbPhone.setDisable(false);
			tfAddress.setDisable(true);
			tfEmail.setDisable(true);
			tfName.setDisable(true);
			tfPhone.setDisable(true);
			tfId.setDisable(false);
		}
	}

	@FXML
	void showBut(ActionEvent event) {
		loadRecords();
	}

	private void insertRecord() {
		if (validateInput()) {
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				// Insert into supplier table
				String sql = "INSERT INTO supplier (supplier_name, supplier_address, supplier_email) VALUES (?, ?, ?)";
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, tfName.getText());
				preparedStatement.setString(2, tfAddress.getText());
				preparedStatement.setString(3, tfEmail.getText());
				preparedStatement.executeUpdate();

				// Get the generated supplier_id
				resultSet = preparedStatement.getGeneratedKeys();
				int supplierId = 0;
				if (resultSet.next()) {
					supplierId = resultSet.getInt(1);
				}

				// Insert into supplier_phone table
				sql = "INSERT INTO supplier_phone (supplier_id, phone_number) VALUES (?, ?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, supplierId);
				preparedStatement.setInt(2, Integer.parseInt(tfPhone.getText()));
				preparedStatement.executeUpdate();

				// Load records and clear fields
				loadRecords();
				clearFields();
				showAlert(2, "Success", "Record inserted successfully.");

			} catch (SQLException ex) {
				showAlert(1, "Error inserting record", ex.getMessage());
			} finally {
				// Close resources
				try {
					if (resultSet != null)
						resultSet.close();
					if (preparedStatement != null)
						preparedStatement.close();
				} catch (SQLException ex) {
					showAlert(1, "Error closing resources", ex.getMessage());
				}
			}
		}
	}

	private void updateSupplier() {
		try {
			int supplierId = Integer.parseInt(tfId.getText());
			String name = tfName.getText();
			String address = tfAddress.getText();
			String email = tfEmail.getText();
			int phone ;

			// Construct the SQL UPDATE statement
			StringBuilder sqlBuilder = new StringBuilder("UPDATE supplier SET ");
			List<String> updates = new ArrayList<>();

			// Check which fields are selected for update
			if (cbName.isSelected()) {
				updates.add("supplier_name = ?");
			}
			if (cbAddress.isSelected()) {
				updates.add("supplier_address = ?");
			}
			if (cbEmail.isSelected()) {
				updates.add("supplier_email = ?");
			}

			// Append the fields to the SQL query
			if (!updates.isEmpty()) {
				String updatesStr = String.join(", ", updates);
				sqlBuilder.append(updatesStr);
				sqlBuilder.append(" WHERE supplier_id = ?");
				String sql = sqlBuilder.toString();

				preparedStatement = connection.prepareStatement(sql);

				// Set parameters based on the selected fields
				int parameterIndex = 1;
				if (cbName.isSelected()) {
					preparedStatement.setString(parameterIndex++, name);
				}
				if (cbAddress.isSelected()) {
					preparedStatement.setString(parameterIndex++, address);
				}
				if (cbEmail.isSelected()) {
					preparedStatement.setString(parameterIndex++, email);
				}
				preparedStatement.setInt(parameterIndex, supplierId);

				// Execute the update query
				preparedStatement.executeUpdate();
				showAlert(2, "Success", "Supplier updated successfully.");
				loadRecords();
				clearFields();
			}

			// Update the phone number in supplier_phone table if needed
			if (cbPhone.isSelected()) {
				phone = Integer.parseInt(tfPhone.getText());
				String phoneSql = "UPDATE supplier_phone SET phone_number = ? WHERE supplier_id = ?";
				preparedStatement = connection.prepareStatement(phoneSql);
				preparedStatement.setInt(1, phone);
				preparedStatement.setInt(2, supplierId);
				preparedStatement.executeUpdate();
			}

			clearFields(); // Clear input fields after updating
		} catch (SQLException ex) {
			showAlert(1, "Error updating record", ex.getMessage());
		}

	}

	private void deleteSupplier(int supplierId) {
		PreparedStatement preparedStatement = null;
		try {
			// Delete from supplier_phone table
			String sql = "DELETE FROM supplier_phone WHERE supplier_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, supplierId);
			int phoneRowsAffected = preparedStatement.executeUpdate();

			// Delete from supplier table
			sql = "DELETE FROM supplier WHERE supplier_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, supplierId);
			int supplierRowsAffected = preparedStatement.executeUpdate();

			if (phoneRowsAffected == 0 && supplierRowsAffected == 0) {
				// No rows affected means the supplier ID does not exist in the database
				showAlert(1, "Error", "No supplier found with ID: " + supplierId);
				loadRecords();
				clearFields();
			} else {
				loadRecords();
				clearFields(); // Clear input fields after deleting
				showAlert(2, "Success", "Supplier deleted successfully.");
			}
		} catch (SQLException ex) {
			showAlert(1, "Error deleting record", ex.getMessage());
		} finally {
			// Close resources
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException ex) {
				showAlert(1, "Error closing resources", ex.getMessage());
			}
		}
	}

	private void loadRecords() {
		ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
		String query = "SELECT s.supplier_id, s.supplier_name, s.supplier_address, s.supplier_email, sp.phone_number "
				+ "FROM supplier s LEFT JOIN supplier_phone sp ON s.supplier_id = sp.supplier_id";

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("supplier_id");
				String name = resultSet.getString("supplier_name");
				String address = resultSet.getString("supplier_address");
				String email = resultSet.getString("supplier_email");
				String phone = resultSet.getString("phone_number");

				Supplier supplier = new Supplier(id, name, phone, address, email);
				supplierList.add(supplier);
			}

			// Clear existing columns
			tableView.getColumns().clear();

			// Define TableColumn instances
			tcId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
			tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
			tcAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
			tcEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
			tcPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
			// Add TableColumn instances to the TableView
			tableView.getColumns().addAll(tcId, tcName, tcAddress, tcEmail, tcPhone);
			// Set the data to the TableView
			tableView.setItems(supplierList);

		} catch (SQLException ex) {
			showAlert(1, "Error loading records", ex.getMessage());
		}
	}

	private boolean validateInput() {
		if (tfName.getText().isEmpty() || tfEmail.getText().isEmpty() || tfPhone.getText().isEmpty()
				|| tfAddress.getText().isEmpty()) {
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

	private void clearFields() {
		tfId.clear();
		tfName.clear();
		tfAddress.clear();
		tfEmail.clear();
		tfPhone.clear();
	}

	private void showAlert(int alertType, String title, String message) {
		Alert alert = new Alert(alertType == 1 ? Alert.AlertType.ERROR : Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	void searchByName(ActionEvent event) {
	    String searchText = tfSearch.getText();
	    ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
	    String query = "SELECT s.supplier_id, s.supplier_name, s.supplier_address, s.supplier_email, sp.phone_number " +
	                   "FROM supplier s LEFT JOIN supplier_phone sp ON s.supplier_id = sp.supplier_id ";

	    if (searchText != null && !searchText.isEmpty()) {
	        query += "WHERE s.supplier_name LIKE ?";
	    }

	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        if (searchText != null && !searchText.isEmpty()) {
	            preparedStatement.setString(1, "%" + searchText + "%");
	        }

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                int id = resultSet.getInt("supplier_id");
	                String name = resultSet.getString("supplier_name");
	                String address = resultSet.getString("supplier_address");
	                String email = resultSet.getString("supplier_email");
	                String phone = resultSet.getString("phone_number"); // Ensure the phone number is retrieved as a string

	                Supplier supplier = new Supplier(id, name, phone, address, email); // Make sure the Supplier constructor matches this order
	                supplierList.add(supplier);
	            }

	            // Set the data to the TableView
	            tableView.setItems(supplierList);

	            // Define TableColumn instances if not already defined
	            if (tableView.getColumns().isEmpty()) {
	                tcId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
	                tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
	                tcAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
	                tcEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
	                tcPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));

	                // Add TableColumn instances to the TableView
	                tableView.getColumns().addAll(tcId, tcName, tcAddress, tcEmail, tcPhone);
	            }
	        }
	    } catch (SQLException ex) {
	        showAlert(1, "Error loading records", ex.getMessage());
	    }
	}


}
