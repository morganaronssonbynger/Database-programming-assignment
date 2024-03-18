package tables;

import javafx.beans.property.SimpleStringProperty;

public class TableWithHighestRowCount {
	
	private SimpleStringProperty tableName = new SimpleStringProperty();
	private SimpleStringProperty rowCount = new SimpleStringProperty();
	
	
	
	public String getTableName() {
		return tableName.get();
	}
	public String getRowCount() {
		return rowCount.get();
	}
	public SimpleStringProperty getTableNameProperty() {
		return tableName;
	}
	public void setTableName(SimpleStringProperty tableName) {
		this.tableName = tableName;
	}
	public SimpleStringProperty getRowCountProperty() {
		return rowCount;
	}
	public void setRowCount(SimpleStringProperty rowCount) {
		this.rowCount = rowCount;
	}
	
}
