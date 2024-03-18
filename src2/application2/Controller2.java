package application2;


import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import tables.AllColumns;
import tables.AllKeys;
import tables.AllTables;
import tables.Employee;
import tables.EmployeeAbsence;
import tables.EmployeeMetadata;
import tables.EmployeeQualification;
import tables.EmployeeRelative;
import tables.TableConstraints;
import tables.TableWithHighestRowCount;


public class Controller2 {

	DataAccessLayer2 dal = new DataAccessLayer2();
	
	// TableView Employee
	@FXML
	TableView<Employee> tableViewEmp;
	@FXML
	TableColumn<Employee, String> colNo, colFirstName, colLastName, colJobTitle, colPhoneNo;
	
	// TableView EmployeeAbsence
	@FXML
	TableView<EmployeeAbsence> tableViewEmpAbsence;
	@FXML
	TableColumn<EmployeeAbsence, String> colEmployeeNo, colFromDate, colToDate, colCauseOfAbsenceCode, colDescription;
	
	// TableView EmployeeQualification
	@FXML
	TableView<EmployeeQualification> tableViewEmpQualification;
	@FXML
	TableColumn<EmployeeQualification, String> colEmpNoQualification, colLineNo, colQualificationCode, colDescriptionQualification, colInstitutionCompany;
	
	// TableView EmployeeRelative
	@FXML
	TableView<EmployeeRelative> tableViewEmpRelative;
	@FXML
	TableColumn<EmployeeRelative, String> colEmpNoRelative, colRelativeCode, colFirstNameRelatives, colLastNameRelatives, colBirthDate;
	
	// TableView AllKeys
	@FXML
	TableView<AllKeys> tableViewAllKeys;
	@FXML
	TableColumn<AllKeys, String> colConstraintNameAllKeys, colTableNameAllKeys, colConstraintTypeAllKeys;
	
	// TableView TableConstraints
	@FXML
	TableView<TableConstraints> tableViewTableConstraints;
	@FXML
	TableColumn<TableConstraints, String> colConstraintName, colTableNameTableConstraints, colConstraintType;
	
	// TableView AllTables
	@FXML
	TableView<AllTables> tableViewAllTables;
	@FXML
	TableColumn<AllTables, String> colTableNameAllTables;

	// TableView AllColumns
	@FXML
	TableView<AllColumns> tableViewAllColumns;
	@FXML
	TableColumn<AllColumns, String> colTableNameAllColumns, colColumnNameAllColumns, colDataType;
	
	// TableView EmployeeMetadata
	@FXML
	TableView<EmployeeMetadata> tableViewEmployeeMetadata;
	@FXML
	TableColumn<EmployeeMetadata, String> colTableCatalogEmployeeMetadata, colTableNameEmployeeMetadata, colColumnNameEmployeeMetadata, colOrdinalPositionEmployeeMetadata, colDataTypeEmployeeMetadata;
	
	// TableView TableWithHighestRowCount
	@FXML
	TableView<TableWithHighestRowCount> tableViewTableWithHighestRowCount;
	@FXML
	TableColumn<TableWithHighestRowCount, String> colTableNameTableWithHighestRowCount, colRowCountTableWithHighestRowCount;
	
	// Panes
	@FXML
	Pane pnl_Query, pnl_Employee;
	
	
	// Alert message method
	public void getAlertMessage() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Connection problems");
		alert.setContentText("Please check your internet/WiFi connection. If problems still remains please contact our IT-support.");
		alert.showAndWait();
	}
	
	// Methods for switching panes
	public void btnQuery(ActionEvent event) {
		pnl_Query.toFront();
	}
	
	public void btnEmployee() {
		pnl_Employee.toFront();
	}
	
	// Sets values into TableView Employee
	public void btnEmployeeContent(ActionEvent event) {
		
		colNo.setCellValueFactory(new PropertyValueFactory<Employee,String>("no_"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<Employee,String>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<Employee,String>("lastName"));
		colJobTitle.setCellValueFactory(new PropertyValueFactory<Employee,String>("jobTitle"));
		colPhoneNo.setCellValueFactory(new PropertyValueFactory<Employee,String>("phoneNo"));
		
		try {
			tableViewEmp.setItems(dal.getEmployeeContent());
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 0) {
				getAlertMessage();
			}
			
		}
	}
	
	// Sets values into TableView EmployeeAbsence
	public void btnEmployeeAbsence(ActionEvent event) {
		colEmployeeNo.setCellValueFactory(new PropertyValueFactory<EmployeeAbsence, String>("employeeNo_"));
		colFromDate.setCellValueFactory(new PropertyValueFactory<EmployeeAbsence, String>("fromDate"));
		colToDate.setCellValueFactory(new PropertyValueFactory<EmployeeAbsence, String>("toDate"));
		colCauseOfAbsenceCode.setCellValueFactory(new PropertyValueFactory<EmployeeAbsence, String>("causeOfAbsenceCode"));
		colDescription.setCellValueFactory(new PropertyValueFactory<EmployeeAbsence, String>("description"));
	
		try {
			tableViewEmpAbsence.setItems(dal.getEmployeeAbsenceContent());
		} catch (SQLException e) {
			if(e.getErrorCode() == 0) {
				getAlertMessage();
			}

		}
	}
	
	// Sets values into TableView EmployeeQualification
	public void btnEmployeeQualification(ActionEvent event) {
		colEmpNoQualification.setCellValueFactory(new PropertyValueFactory<EmployeeQualification, String>("employeeNo_"));
		colLineNo.setCellValueFactory(new PropertyValueFactory<EmployeeQualification, String>("lineNo_"));
		colQualificationCode.setCellValueFactory(new PropertyValueFactory<EmployeeQualification, String>("qualificationCode"));
		colDescriptionQualification.setCellValueFactory(new PropertyValueFactory<EmployeeQualification, String>("description"));
		colInstitutionCompany.setCellValueFactory(new PropertyValueFactory<EmployeeQualification, String>("institution_Company"));
		
		try {
			tableViewEmpQualification.setItems(dal.getEmployeeQualificationContent());
		} catch (SQLException e) {
			if(e.getErrorCode() == 0) {
				getAlertMessage();
			}
		
		}
	}
	
	// Sets values into TableView EmployeeRelative
	public void btnEmployeeRelative(ActionEvent event) {
		colEmpNoRelative.setCellValueFactory(new PropertyValueFactory<EmployeeRelative, String>("employeeNo_"));
		colRelativeCode.setCellValueFactory(new PropertyValueFactory<EmployeeRelative, String>("relativeCode"));
		colFirstNameRelatives.setCellValueFactory(new PropertyValueFactory<EmployeeRelative, String>("firstName"));
		colLastNameRelatives.setCellValueFactory(new PropertyValueFactory<EmployeeRelative, String>("lastName"));
		colBirthDate.setCellValueFactory(new PropertyValueFactory<EmployeeRelative, String>("birthDate"));
	
		try {
			tableViewEmpRelative.setItems(dal.getEmployeeRelativeContent());
		} catch (SQLException e) {
			if(e.getErrorCode() == 0) {
				getAlertMessage();
			}
		
		}
	}
	
	// Sets values into TableView AllKeys
	public void btnAllKeys(ActionEvent event) {
		colConstraintNameAllKeys.setCellValueFactory(new PropertyValueFactory<AllKeys, String>("constraint_Name"));
		colTableNameAllKeys.setCellValueFactory(new PropertyValueFactory<AllKeys, String>("table_Name"));
		colConstraintTypeAllKeys.setCellValueFactory(new PropertyValueFactory<AllKeys, String>("constraint_Type"));
		
		try {
			tableViewAllKeys.setItems(dal.getAllKeysContent());
		} catch (SQLException e) {
			if(e.getErrorCode() == 0) {
				getAlertMessage();
			}
		
		}
	}
	
	// Sets values into TableView TableConstraints
	public void btnTableConstraints(ActionEvent event) {
		colConstraintName.setCellValueFactory(new PropertyValueFactory<TableConstraints, String>("constraint_Name"));
		colTableNameTableConstraints.setCellValueFactory(new PropertyValueFactory<TableConstraints, String>("table_Name"));
		colConstraintType.setCellValueFactory(new PropertyValueFactory<TableConstraints, String>("constraint_Type"));
		
		try {
			tableViewTableConstraints.setItems(dal.getTableConstraintsContent());
		} catch (SQLException e) {
			if(e.getErrorCode() == 0) {
				getAlertMessage();
			}
	
		}
	}
	
	// Sets values into TableView AllTables
	public void btnAllTables(ActionEvent event) {
		colTableNameAllTables.setCellValueFactory(new PropertyValueFactory<AllTables, String>("table_Name"));

		try {
			tableViewAllTables.setItems(dal.getAllTablesContent());
		} catch (SQLException e) {
			if(e.getErrorCode() == 0) {
				getAlertMessage();
			}
	
		}
	}
	
	// Sets values into TableView AllColumns
	public void btnAllColumns(ActionEvent event) {
		colTableNameAllColumns.setCellValueFactory(new PropertyValueFactory<AllColumns, String>("table_Name"));
		colColumnNameAllColumns.setCellValueFactory(new PropertyValueFactory<AllColumns, String>("column_Name"));
		colDataType.setCellValueFactory(new PropertyValueFactory<AllColumns, String>("data_Type"));
		

		try {
			tableViewAllColumns.setItems(dal.getAllColumnsContent());
		} catch (SQLException e) {
			if(e.getErrorCode() == 0) {
				getAlertMessage();
			}
	
		}
	}
	
	// Sets values into TableView EmployeeMetadata
	public void btnEmployeeMetadata(ActionEvent event) {	
		colTableCatalogEmployeeMetadata.setCellValueFactory(new PropertyValueFactory<EmployeeMetadata, String>("table_Catalog"));
		colTableNameEmployeeMetadata.setCellValueFactory(new PropertyValueFactory<EmployeeMetadata, String>("table_Name"));
		colColumnNameEmployeeMetadata.setCellValueFactory(new PropertyValueFactory<EmployeeMetadata, String>("column_Name"));
		colOrdinalPositionEmployeeMetadata.setCellValueFactory(new PropertyValueFactory<EmployeeMetadata, String>("ordinal_Position"));
		colDataTypeEmployeeMetadata.setCellValueFactory(new PropertyValueFactory<EmployeeMetadata, String>("data_Type"));
		

		try {
			tableViewEmployeeMetadata.setItems(dal.getEmployeeMetadataContent());
		} catch (SQLException e) {
			if(e.getErrorCode() == 0) {
				getAlertMessage();
			}
		
		}
	}
	
	// Sets values into TableView HighestRowCount
	public void btnTableWithHighestRowCount(ActionEvent event) {
		colTableNameTableWithHighestRowCount.setCellValueFactory(new PropertyValueFactory<TableWithHighestRowCount, String>("tableName"));
		colRowCountTableWithHighestRowCount.setCellValueFactory(new PropertyValueFactory<TableWithHighestRowCount, String>("rowCount"));

		try {
			tableViewTableWithHighestRowCount.setItems(dal.getTableWithHighestRowCountContent());
		} catch (SQLException e) {
			if(e.getErrorCode() == 0) {
				getAlertMessage();
			}
	
		}
	}
}
