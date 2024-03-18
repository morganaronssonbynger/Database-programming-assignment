package tables;

import javafx.beans.property.SimpleStringProperty;

public class TableConstraints {
	
	private SimpleStringProperty constraint_Name = new SimpleStringProperty();
	private SimpleStringProperty table_Name = new SimpleStringProperty();
	private SimpleStringProperty constraint_Type = new SimpleStringProperty();
	
	public String getConstraint_Name() {
		return constraint_Name.get();
	}
	public String getTable_Name() {
		return table_Name.get();
	}
	public String getConstraint_Type() {
		return constraint_Type.get();
	}
	public SimpleStringProperty getConstraint_NameProperty() {
		return constraint_Name;
	}
	public void setConstraint_Name(SimpleStringProperty constraint_Name) {
		this.constraint_Name = constraint_Name;
	}
	public SimpleStringProperty getTable_Name_Property() {
		return table_Name;
	}
	public void setTable_Name(SimpleStringProperty table_Name) {
		this.table_Name = table_Name;
	}
	public SimpleStringProperty getConstraint_TypeProperty() {
		return constraint_Type;
	}
	public void setConstraint_Type(SimpleStringProperty constraint_Type) {
		this.constraint_Type = constraint_Type;
	}
	

}
