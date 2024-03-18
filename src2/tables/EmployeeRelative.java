package tables;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeRelative {

	private SimpleStringProperty employeeNo_ = new SimpleStringProperty();
	private SimpleStringProperty relativeCode = new SimpleStringProperty();
	private SimpleStringProperty firstName = new SimpleStringProperty();
	private SimpleStringProperty lastName = new SimpleStringProperty();
	private SimpleStringProperty birthDate = new SimpleStringProperty();
	
	public String getEmployeeNo_() {
		return employeeNo_.get();
	}
	public String getRelativeCode() {
		return relativeCode.get();
	}
	public String getFirstName() {
		return firstName.get();
	}
	public String getLastName() {
		return lastName.get();
	}
	public String getBirthDate() {
		return birthDate.get();
	}
	
	public SimpleStringProperty getEmployeeNo_Property() {
		return employeeNo_;
	}
	public void setEmployeeNo_(SimpleStringProperty employeeNo_) {
		this.employeeNo_ = employeeNo_;
	}
	public SimpleStringProperty getRelativeCodeProperty() {
		return relativeCode;
	}
	public void setRelativeCode(SimpleStringProperty relativeCode) {
		this.relativeCode = relativeCode;
	}
	public SimpleStringProperty getFirstNameProperty() {
		return firstName;
	}
	public void setFirstName(SimpleStringProperty firstName) {
		this.firstName = firstName;
	}
	public SimpleStringProperty getLastNameProperty() {
		return lastName;
	}
	public void setLastName(SimpleStringProperty lastName) {
		this.lastName = lastName;
	}
	public SimpleStringProperty getBirthDateProperty() {
		return birthDate;
	}
	public void setBirthDate(SimpleStringProperty birthDate) {
		this.birthDate = birthDate;
	}
	
}
