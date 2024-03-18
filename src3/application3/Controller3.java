package application3;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller3 {

	
	// Method for alert message for pathway error
	public void getAlertMessageExcel() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Path errors");
		alert.setContentText("Please check if the pathway to the excel file is correct.");
		alert.showAndWait();
	}
	
	public void getAlertMessageAccess() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Path errors");
		alert.setContentText("Please check if the pathway to the access file is correct.");
		alert.showAndWait();
	}
	
	// Customer report in excel

	public void getCustomerExcelReport(ActionEvent event) {
		File fileCE = new File("C:\\Users\\Administrator\\Documents\\Assignment2reports.xlsx");

		try {			
			Desktop.getDesktop().open(fileCE);
		}
		catch(java.lang.IllegalArgumentException f) {
			if(!fileCE.exists()) {
				getAlertMessageExcel();
			}

		} catch (IOException e) {

			
		}
	} 

	// Employee report in excel
	
	public void getEmployeeExcelReport(ActionEvent event){
		File fileEE = new File("C:\\Users\\Administrator\\Documents\\Assignment2reportsEmp.xlsx");

		try {
			Desktop.getDesktop().open(fileEE);
		}
		catch(java.lang.IllegalArgumentException f) {
			if(!fileEE.exists()) {
				getAlertMessageExcel();
			}
		}
		catch(IOException e) {
			
		}
	}
	
	// Customer report in access

	public void getCustomerAccessReport(ActionEvent event) {
		File fileCA = new File("C:\\Users\\Administrator\\Documents\\Assignment2reportsCustomer.accdb");

		try {
			Desktop.getDesktop().open(fileCA);
		}	
		catch(java.lang.IllegalArgumentException f) {
			if(!fileCA.exists()) {
				getAlertMessageAccess();
			}
		}
		catch (IOException e) {

		}		
	}
	
	// Employee report in access

	public void getEmployeeAccessReport(ActionEvent event) {
		File fileEA = new File("C:\\Users\\Administrator\\Documents\\Assignment2reportsEmployee.accdb");

		try {
			Desktop.getDesktop().open(fileEA);
		} 
		catch(java.lang.IllegalArgumentException f) {
			if(!fileEA.exists()) {
				getAlertMessageAccess();
			}
		}		
		catch (IOException e) {
			
		}
	}
	
}
