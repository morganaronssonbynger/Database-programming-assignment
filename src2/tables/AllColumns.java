package tables;

import javafx.beans.property.SimpleStringProperty;

public class AllColumns {
	
	private SimpleStringProperty table_Name = new SimpleStringProperty();
	private SimpleStringProperty column_Name = new SimpleStringProperty();
	private SimpleStringProperty data_Type = new SimpleStringProperty();
	
	
	
	public String getTable_Name() {
		return table_Name.get();
	}
	public String getColumn_Name() {
		return column_Name.get();
	}
	public String getData_Type() {
		return data_Type.get();
	}
	public SimpleStringProperty getTable_NameProperty() {
		return table_Name;
	}
	public void setTable_Name(SimpleStringProperty table_Name) {
		this.table_Name = table_Name;
	}
	public SimpleStringProperty getColumn_NameProperty() {
		return column_Name;
	}
	public void setColumn_Name(SimpleStringProperty coloumn_Name) {
		this.column_Name = coloumn_Name;
	}
	public SimpleStringProperty getData_TypeProperty() {
		return data_Type;
	}
	public void setData_Type(SimpleStringProperty data_Type) {
		this.data_Type = data_Type;
	}
	
	
}
