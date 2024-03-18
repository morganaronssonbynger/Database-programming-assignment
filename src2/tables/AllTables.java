package tables;

import javafx.beans.property.SimpleStringProperty;

public class AllTables {

	private SimpleStringProperty table_Name = new SimpleStringProperty();
	
	
	public String getTable_Name() {
		return table_Name.get();
	}

	public SimpleStringProperty getTable_NameProperty() {
		return table_Name;
	}

	public void setTable_Name(SimpleStringProperty table_Name) {
		this.table_Name = table_Name;
	}
	
	
}
