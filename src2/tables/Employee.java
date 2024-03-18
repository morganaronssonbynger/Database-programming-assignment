package tables;

import javafx.beans.property.SimpleStringProperty;

public class Employee {

	private SimpleStringProperty no_ = new SimpleStringProperty();
	private SimpleStringProperty firstName = new SimpleStringProperty();
	private SimpleStringProperty lastName = new SimpleStringProperty();
	private SimpleStringProperty jobTitle = new SimpleStringProperty();
	private SimpleStringProperty phoneNo = new SimpleStringProperty();
	
	public String getNo_() {
		return no_.get();
	}
	public String getFirstName() {
		return firstName.get();
	}
	public String getLastName() {
		return lastName.get();
	}
	public String getJobTitle() {
		return jobTitle.get();
	}
	public String getPhoneNo() {
		return phoneNo.get();
	}
	public void setNo_(SimpleStringProperty no) {
		this.no_ = no;
	}
	public SimpleStringProperty getNo_Property() {
		return no_;
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
	public SimpleStringProperty getJobTitleProperty() {
		return jobTitle;
	}
	public void setJobTitle(SimpleStringProperty jobTitle) {
		this.jobTitle = jobTitle;
	}
	public SimpleStringProperty getPhoneNoProperty() {
		return phoneNo;
	}
	public void setPhoneNo(SimpleStringProperty phoneNo) {
		this.phoneNo = phoneNo;
	}

}

	