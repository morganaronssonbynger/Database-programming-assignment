package tables;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeQualification {

	private SimpleStringProperty employeeNo_ = new SimpleStringProperty();
	private SimpleStringProperty lineNo_ = new SimpleStringProperty();
	private SimpleStringProperty qualificationCode = new SimpleStringProperty();
	private SimpleStringProperty description= new SimpleStringProperty();
	private SimpleStringProperty institution_Company = new SimpleStringProperty();
	
	public String getEmployeeNo_() {
		return employeeNo_.get();
	}
	public String getLineNo_() {
		return lineNo_.get();
	}
	public String getQualificationCode() {
		return qualificationCode.get();
	}
	public String getDescription() {
		return description.get();
	}
	public String getInstitution_Company() {
		return institution_Company.get();
	}
	public SimpleStringProperty getEmployeeNo_Property() {
		return employeeNo_;
	}
	public void setEmployeeNo_(SimpleStringProperty employeeNo_) {
		this.employeeNo_ = employeeNo_;
	}
	public SimpleStringProperty getLineNo_Property() {
		return lineNo_;
	}
	public void setLineNo_(SimpleStringProperty lineNo_) {
		this.lineNo_ = lineNo_;
	}
	public SimpleStringProperty getQualificationCodeProperty() {
		return qualificationCode;
	}
	public void setQualificationCode(SimpleStringProperty qualificationCode) {
		this.qualificationCode = qualificationCode;
	}
	public SimpleStringProperty getDescriptionProperty() {
		return description;
	}
	public void setDescription(SimpleStringProperty description) {
		this.description = description;
	}
	public SimpleStringProperty getInstitution_CompanyProperty() {
		return institution_Company;
	}
	public void setInstitution_Company(SimpleStringProperty institution_Company) {
		this.institution_Company = institution_Company;
	}
}
