package application2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tables.AllColumns;
import tables.AllKeys;
import tables.AllTables;
import tables.Employee;
import tables.EmployeeAbsence;
import tables.EmployeeMetadata;
import tables.EmployeeQualification;
import tables.EmployeeRelative;
import tables.TableConstraints;
import tables.TableWithHighestRowCount;

public class DataAccessLayer2 {
	
	// All observablelists
	ObservableList<Employee> olEmp;
	ObservableList<EmployeeAbsence> olEmpAbsence;
	ObservableList<EmployeeQualification> olEmpQualification;
	ObservableList<EmployeeRelative> olEmpRelative;
	ObservableList<AllKeys> olAllKeys;
	ObservableList<TableConstraints> olTableConstraints;
	ObservableList<AllTables> olAllTables;
	ObservableList<AllColumns> olAllColumns;
	ObservableList<EmployeeMetadata> olEmpMetadata;
	ObservableList<TableWithHighestRowCount> olTableWithHighestRowCount;
	
	DbConnection2 DBConnect = new DbConnection2();
	private Connection con;
	
	// ExecuteQuery method
	private ResultSet runExecuteQuery(String SQLstring) throws SQLException {
		con = DBConnect.getConnection();
		PreparedStatement ps = con.prepareStatement(SQLstring);
		ResultSet rs = ps.executeQuery();
		return rs;
		
	}
	
	// Method for getting values from the database to the Employee TableView
	public ObservableList<Employee> getEmployeeContent() throws SQLException{
		olEmp = FXCollections.observableArrayList();
		String query = "SELECT No_, [First Name], [Last Name], [Job Title], [Phone No_] FROM [CRONUS Sverige AB$Employee]";
		ResultSet rs = runExecuteQuery(query);
		while(rs.next()) {
			Employee e = new Employee();
			e.getNo_Property().set(rs.getString(1));
			e.getFirstNameProperty().set(rs.getString(2));
			e.getLastNameProperty().set(rs.getString(3));
			e.getJobTitleProperty().set(rs.getString(4));
			e.getPhoneNoProperty().set(rs.getString(5));
			olEmp.add(e);	
		}
		con.close();
		return olEmp;
	}
	
	// Method for getting values from the database to the Employee Absence TableView
	public ObservableList<EmployeeAbsence> getEmployeeAbsenceContent() throws SQLException{
		olEmpAbsence = FXCollections.observableArrayList();
		String query = "SELECT [Employee No_], [From Date], [To Date], [Cause of Absence Code], Description FROM [CRONUS Sverige AB$Employee Absence]";
		ResultSet rs = runExecuteQuery(query);
		while(rs.next()) {
			EmployeeAbsence ea = new EmployeeAbsence();
			ea.getEmployeeNo_Property().set(rs.getString(1));
			ea.getFromDateProperty().set(rs.getString(2));
			ea.getToDateProperty().set(rs.getString(3));
			ea.getCauseOfAbsenceCodeProperty().set(rs.getString(4));
			ea.getDescriptionProperty().set(rs.getString(5));
			olEmpAbsence.add(ea);	
		}
		con.close();
		return olEmpAbsence;
	}

	// Method for getting values from the database to the Employee Qualification TableView
	public ObservableList<EmployeeQualification> getEmployeeQualificationContent() throws SQLException{
		olEmpQualification = FXCollections.observableArrayList();
		String query = "SELECT [Employee No_], [Line No_], [Qualification Code], Description, Institution_Company FROM [CRONUS Sverige AB$Employee Qualification]";
		ResultSet rs = runExecuteQuery(query);
		while(rs.next()) {
			EmployeeQualification eq = new EmployeeQualification();
			eq.getEmployeeNo_Property().set(rs.getString(1));
			eq.getLineNo_Property().set(rs.getString(2));
			eq.getQualificationCodeProperty().set(rs.getString(3));
			eq.getDescriptionProperty().set(rs.getString(4));
			eq.getInstitution_CompanyProperty().set(rs.getString(5));

			olEmpQualification.add(eq);	
		}
		con.close();
		return olEmpQualification;
	}
	
	// Method for getting values from the database to the Employee Relative TableView
	public ObservableList<EmployeeRelative> getEmployeeRelativeContent() throws SQLException{
		olEmpRelative = FXCollections.observableArrayList();
		String query = "SELECT [Employee No_], [Relative Code], [First Name], [Last Name], [Birth Date] FROM [CRONUS Sverige AB$Employee Relative]";
		ResultSet rs = runExecuteQuery(query);
		while(rs.next()) {
			EmployeeRelative er = new EmployeeRelative();
			er.getEmployeeNo_Property().set(rs.getString(1));
			er.getRelativeCodeProperty().set(rs.getString(2));
			er.getFirstNameProperty().set(rs.getString(3));
			er.getLastNameProperty().set(rs.getString(4));
			er.getBirthDateProperty().set(rs.getString(5));

			olEmpRelative.add(er);	
		}
		con.close();
		return olEmpRelative;
	}
	
	// Method for getting values from the database to the All Keys TableView
	public ObservableList<AllKeys> getAllKeysContent() throws SQLException{
		olAllKeys = FXCollections.observableArrayList();
		String query = "SELECT CONSTRAINT_NAME, TABLE_NAME, CONSTRAINT_TYPE FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE CONSTRAINT_TYPE LIKE '%KEY'";
		ResultSet rs = runExecuteQuery(query);
		while(rs.next()) {
			AllKeys ak = new AllKeys();
			ak.getConstraint_NameProperty().set(rs.getString(1));
			ak.getTable_NameProperty().set(rs.getString(2));
			ak.getConstraint_TypeProperty().set(rs.getString(3));

			olAllKeys.add(ak);	
		}
		con.close();
		return olAllKeys;
	}
	
	// Method for getting values from the database to the Table Constraints TableView
	public ObservableList<TableConstraints> getTableConstraintsContent() throws SQLException{
		olTableConstraints = FXCollections.observableArrayList();
		String query = "SELECT CONSTRAINT_NAME, TABLE_NAME, CONSTRAINT_TYPE FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS";
		ResultSet rs = runExecuteQuery(query);
		while(rs.next()) {
			TableConstraints tc = new TableConstraints();
			tc.getConstraint_NameProperty().set(rs.getString(1));
			tc.getTable_Name_Property().set(rs.getString(2));
			tc.getConstraint_TypeProperty().set(rs.getString(3));


			olTableConstraints.add(tc);	
		}
		con.close();
		return olTableConstraints;
	}
	
	// Method for getting values from the database to the All Tables TableView
	public ObservableList<AllTables> getAllTablesContent() throws SQLException{
		olAllTables = FXCollections.observableArrayList();
		String query ="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES";
		ResultSet rs = runExecuteQuery(query);
		
		while(rs.next()) {
			AllTables at = new AllTables();
			at.getTable_NameProperty().set(rs.getString(1));

		olAllTables.add(at);	
		}
		con.close();
		return olAllTables;
	}
	
	// Method for getting values from the database to the All Columns TableView
	public ObservableList<AllColumns> getAllColumnsContent() throws SQLException{
		olAllColumns = FXCollections.observableArrayList();
		String query ="SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'CRONUS Sverige AB$Employee'";
		ResultSet rs = runExecuteQuery(query);
		
		while(rs.next()) {
			AllColumns ac = new AllColumns();
			ac.getTable_NameProperty().set(rs.getString(1));
			ac.getColumn_NameProperty().set(rs.getString(2));
			ac.getData_TypeProperty().set(rs.getString(3));

		olAllColumns.add(ac);	
	}
	con.close();
	return olAllColumns;
	
	}
	
	// Method for getting values from the database to the Employee Metadata TableView
	public ObservableList<EmployeeMetadata> getEmployeeMetadataContent() throws SQLException{
		olEmpMetadata = FXCollections.observableArrayList();
		String query = "SELECT TABLE_CATALOG,TABLE_NAME, COLUMN_NAME, ORDINAL_POSITION, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE 'CRONUS Sverige AB$Employee%'";
		ResultSet rs = runExecuteQuery(query);
		while(rs.next()) {
			EmployeeMetadata em = new EmployeeMetadata();
			em.getTable_CatalogProperty().set(rs.getString(1));
			em.getTable_NameProperty().set(rs.getString(2));
			em.getColumn_NameProperty().set(rs.getString(3));
			em.getOrdinal_PositionProperty().set(rs.getString(4));
			em.getData_TypeProperty().set(rs.getString(5));

			olEmpMetadata.add(em);	
		}
		con.close();
		return olEmpMetadata;
	}
	
	// Method for getting the tables with the highest rowcount from the database to the Highest RowCount TableView
	public ObservableList<TableWithHighestRowCount> getTableWithHighestRowCountContent() throws SQLException{
		olTableWithHighestRowCount = FXCollections.observableArrayList();
		String query = "SELECT TOP 1 [TableName] = so.name, [RowCount] = MAX(si.rows) FROM sysobjects so, sysindexes si WHERE so.xtype = 'U' AND si.id = OBJECT_ID(so.name) GROUP BY so.name ORDER BY 2 DESC";
		ResultSet rs = runExecuteQuery(query);
		while(rs.next()) {
			TableWithHighestRowCount tableWithHighestRowCount = new TableWithHighestRowCount();
			tableWithHighestRowCount.getTableNameProperty().set(rs.getString(1));
			tableWithHighestRowCount.getRowCountProperty().set(rs.getString(2));

			olTableWithHighestRowCount.add(tableWithHighestRowCount);	
		}
		con.close();
		return olTableWithHighestRowCount;
	}
}
