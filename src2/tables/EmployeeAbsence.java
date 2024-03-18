package tables;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeAbsence {

	private SimpleStringProperty employeeNo_ = new SimpleStringProperty();
	private SimpleStringProperty fromDate = new SimpleStringProperty();
	private SimpleStringProperty toDate = new SimpleStringProperty();
	private SimpleStringProperty causeOfAbsenceCode= new SimpleStringProperty();
	private SimpleStringProperty description = new SimpleStringProperty();
	
	public String getEmployeeNo_() {
		return employeeNo_.get();
	}
	public String getFromDate() {
		return fromDate.get();
	}
	public String getToDate() {
		return toDate.get();
	}
	public String getCauseOfAbsenceCode() {
		return causeOfAbsenceCode.get();
	}
	public String getDescription() {
		return description.get();
	}
	
	public SimpleStringProperty getEmployeeNo_Property() {
		return employeeNo_;
	}
	public void setEmployeeNo_(SimpleStringProperty employeeNo_) {
		this.employeeNo_ = employeeNo_;
	}
	public SimpleStringProperty getFromDateProperty() {
		return fromDate;
	}
	public void setFromDate(SimpleStringProperty fromDate) {
		this.fromDate = fromDate;
	}
	public SimpleStringProperty getToDateProperty() {
		return toDate;
	}
	public void setToDate(SimpleStringProperty toDate) {
		this.toDate = toDate;
	}
	public SimpleStringProperty getCauseOfAbsenceCodeProperty() {
		return causeOfAbsenceCode;
	}
	public void setCauseOfAbsenceCode(SimpleStringProperty causeOfAbsenceCode) {
		this.causeOfAbsenceCode = causeOfAbsenceCode;
	}
	public SimpleStringProperty getDescriptionProperty() {
		return description;
	}
	public void setDescription(SimpleStringProperty description) {
		this.description = description;
	}
}
