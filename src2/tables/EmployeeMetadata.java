package tables;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeMetadata {

	private SimpleStringProperty table_Catalog = new SimpleStringProperty();
	private SimpleStringProperty table_Name = new SimpleStringProperty();
	private SimpleStringProperty column_Name = new SimpleStringProperty();
	private SimpleStringProperty ordinal_Position= new SimpleStringProperty();
	private SimpleStringProperty data_Type = new SimpleStringProperty();
	
	public String getTable_Catalog() {
		return table_Catalog.get();
	}
	public String getTable_Name() {
		return table_Name.get();
	}
	public String getColumn_Name() {
		return column_Name.get();
	}
	public String getOrdinal_Position() {
		return ordinal_Position.get();
	}
	public String getData_Type() {
		return data_Type.get();
	}
	
	public SimpleStringProperty getTable_CatalogProperty() {
		return table_Catalog;
	}
	public void setTable_Catalog(SimpleStringProperty table_Catalog) {
		this.table_Catalog = table_Catalog;
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
	public void setColumn_Name(SimpleStringProperty column_Name) {
		this.column_Name = column_Name;
	}
	public SimpleStringProperty getOrdinal_PositionProperty() {
		return ordinal_Position;
	}
	public void setOrdinal_Position(SimpleStringProperty ordinal_Position) {
		this.ordinal_Position = ordinal_Position;
	}
	public SimpleStringProperty getData_TypeProperty() {
		return data_Type;
	}
	public void setData_Type(SimpleStringProperty data_Type) {
		this.data_Type = data_Type;
	}
}
