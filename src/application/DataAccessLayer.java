package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataAccessLayer {
	
	// ObservableList over all student ids, course ids
	ObservableList<String> olStudent = FXCollections.observableArrayList();
	ObservableList<String> olCourse = FXCollections.observableArrayList();
	
	DbConnection DBConnect = new DbConnection();
	private Connection con = null;
	
	
	// Method for using ExecuteQuery
	private ResultSet runExecuteQuery(String SQLstring) throws SQLException {
		con = DBConnect.getConnection();
		PreparedStatement ps = con.prepareStatement(SQLstring);
		ResultSet rs = ps.executeQuery();
		return rs;
		
	}
	
	// Method for using ExecuteUpdate
	private void runExecuteUpdate(String sqlstring)throws SQLException{
		con = DBConnect.getConnection();
		PreparedStatement ps = con.prepareStatement(sqlstring);
		ps.executeUpdate();
	}
	
	// Method for populating our observablelist with student ids and course ids
	public ObservableList<String> populateCBStudent()throws SQLException{
		
		String query = "SELECT studentID FROM Student";

		ResultSet rs = runExecuteQuery(query);
		
		while(rs.next()){
			olStudent.add(rs.getString(1));
		}
		return olStudent;
	}
	
	public ObservableList<String> populateCBCourse()throws SQLException{
		
		String query = "SELECT courseID FROM Course";
		ResultSet rs = runExecuteQuery(query);
			
		while(rs.next()){
			olCourse.add(rs.getString(1));
		}
		return olCourse;
	}
	
	// Methods for getting our observablelist over student ids and course ids
	public ObservableList<String> getObservableListStudent(){
		return olStudent;
	}
	
	public ObservableList<String> getObservableListCourse(){
		return olCourse;
	}
	
	// Methods for adding and removing student ids and course ids from our observablelists
	public void addStudentToOL(String id) {
		olStudent.add(id);
	}
	
	public void addCourseToOL(String id) {
		olCourse.add(id);
	}
	
	public void removeStudentFromOL(String studentID){
		olStudent.remove(studentID);
	}
	
	public void removeCourseFromOL(String courseID) {
		olCourse.remove(courseID);
	}
	
	// Method for getting information about a student from the database
	public ResultSet getStudent(String studentID)throws SQLException {
			
		String query = "SELECT * FROM Student WHERE studentID = '" + studentID + "'"; 
		ResultSet rs = runExecuteQuery(query);
		return rs;
			
	}
	
	// Method for adding a student to the database
	public void addStudent(String studentID, String fullName, String email, String phoneNumber)throws SQLException {
		
		String query = "INSERT INTO Student VALUES ('" + studentID + "','" + fullName + "','" + email + "','" + phoneNumber + "')";
		runExecuteUpdate(query);
		con.close();
		
	}
	
	// Method for getting information about a course from the database
	public ResultSet getCourse(String courseID)throws SQLException {
		
		String query = "SELECT * FROM Course WHERE courseID = '" + courseID + "'"; 
		ResultSet rs = runExecuteQuery(query);
		return rs;
	}
	
	// Method for adding a course to the database
	public void addCourse(String courseID, String courseName, Integer credits)throws SQLException {
		
		String query = "INSERT INTO Course VALUES ('" + courseID + "','" + courseName + "'," + credits + ")";
		runExecuteUpdate(query);
		con.close();
	}
	
	// Method for adding a student to a course
	public void addStudentToCourse(String courseID, String studentID)throws SQLException {

		String query = "INSERT INTO Studies VALUES ('" + studentID + "','" + courseID + "')";
		runExecuteUpdate(query);
		con.close();
	}
	
	// Method for adding a result to a student and deleting them from studies
	public void addResultForStudent(String studentID, String courseID, String letterGrade)throws SQLException {
		
		String query1 = "INSERT INTO HasStudied VALUES ('" + studentID + "','" + courseID + "','" + letterGrade + "')";
		String query2 = "DELETE FROM Studies WHERE courseID = '" + courseID + "' AND studentID = '" + studentID + "'"; 
		
		runExecuteUpdate(query1);
		runExecuteUpdate(query2);
		con.close();
	}
	
	// Method for removing a student from a course
	public void removeStudentFromStudies(String studentID, String courseID) throws SQLException {
		
		String query = "DELETE FROM Studies WHERE courseID = '" + courseID + "' AND studentID = '" + studentID + "'"; 

		runExecuteUpdate(query);
		con.close();
	}
	
	// Method for getting a result for a student on a course
	public ResultSet getResultForAStudent(String courseID, String studentID)throws SQLException {

		String query = "SELECT s.studentID, s.fullName, hs.courseID, hs.letterGrade FROM HasStudied hs JOIN Student s ON s.studentID = hs.studentID WHERE hs.courseID = '" + courseID + "' AND hs.studentID = '" + studentID + "'"; 
		
		ResultSet rs = runExecuteQuery(query);
		return rs;
	}
	
	// Method for getting students who studies
	public ResultSet getStudentsFromStudies (String courseID) throws SQLException {

	String query = "SELECT studentID, fullName FROM Student WHERE studentID IN (SELECT s.studentID FROM Student s JOIN Studies st ON s.studentID = st.studentID WHERE courseID = '" + courseID + "')";
	ResultSet rs = runExecuteQuery(query);

	return rs;
	}
	public ResultSet findOneStudentOnCourse (String studentID, String courseID) throws SQLException {

	String query = "SELECT * FROM Studies WHERE studentID = '" + studentID + "'AND courseID = '" + courseID + "'";
	ResultSet rs = runExecuteQuery(query);

	return rs;
	}

	// Method for getting results on a course
	public ResultSet getResultForACourse(String courseID) throws SQLException{

	String query = "SELECT s.studentID, s.fullName, hs.letterGrade FROM HasStudied hs JOIN Student s ON hs.studentID = s.studentID WHERE courseID = '" + courseID + "'";
	ResultSet rs = runExecuteQuery(query);
	
	return rs;	
	}
	
	// Method for getting students who got A on a specific course
	public ResultSet getStudentWhoGotAOnACourse(String courseID) throws SQLException{
	
	String query = "SELECT COUNT(letterGrade) AS nbr FROM HasStudied WHERE courseID = '"+ courseID + "' AND letterGrade = 'A'";
	ResultSet rs = runExecuteQuery(query);

	return rs;	
	}
	
	// Method for getting the number of students who studies a specific course
	public ResultSet getNumberOfStudentsOnACourse(String courseID) throws SQLException{
	
	String query = "SELECT courseID, COUNT(letterGrade) AS nbr FROM HasStudied GROUP BY courseID HAVING courseID = '" + courseID + "'";
	ResultSet rs = runExecuteQuery(query);

	return rs;	
	}
	
	// Method for removing a course from HasStudied
	public void removeCourseFromHasStudied(String courseID) throws SQLException {
	
	String query = "DELETE FROM HasStudied WHERE courseID = '" + courseID + "'";
	runExecuteUpdate(query);
	con.close();
	}
	
	// Method for removing a student
	public void removeStudent(String studentID) throws SQLException {
	
	String query = "DELETE FROM Student WHERE studentID = '" + studentID + "'"; 
	runExecuteUpdate(query);
	con.close();
	}

	// Method for removing a course
	public void removeCourse(String courseID) throws SQLException {
	
	String query = "DELETE FROM Course WHERE courseID = '" + courseID + "'"; 
	runExecuteUpdate(query);
	con.close();
	}
	
	// Method for removing a course from studies
	public void removeCourseFromStudies(String courseID) throws SQLException {
	
	String query = "DELETE FROM Studies WHERE courseID = '" + courseID + "'"; 
	runExecuteUpdate(query);
	con.close();
	}
	
	// Method for removing a student from studies
	public void removeStudentFromStudies(String studentID) throws SQLException {
	
	String query = "DELETE FROM Studies WHERE studentID = '" + studentID + "'"; 
	runExecuteUpdate(query);
	con.close();
	}
	
	// Method for removing a student from HasStudied
	public void removeStudentFromHasStudied(String studentID) throws SQLException {
	
	String query = "DELETE FROM HasStudied WHERE studentID = '" + studentID + "'"; 
	runExecuteUpdate(query);
	con.close();
	}
	
	// Method for getting a student who can study a course
	public ResultSet getStudentsWhoCanStudieTheCourse(String courseID) throws SQLException {

	String query = "SELECT studentID FROM Student WHERE studentID IN (SELECT s.studentID FROM Studies s JOIN Course c ON c.courseID = s.courseID GROUP BY studentID HAVING (SUM(credits) + (SELECT credits FROM Course WHERE courseID = '" + courseID + "') <= 45)) OR studentID NOT IN (SELECT studentID FROM Studies)";
	ResultSet rs = runExecuteQuery(query);
	return rs;	
	}
	
	// Method for finding if a student id and a course id has a result
	public ResultSet findOneHasStudied (String studentID, String courseID) throws SQLException {

	String query = "SELECT * FROM HasStudied WHERE studentID = '" + studentID + "'AND courseID = '" + courseID + "'";
	ResultSet rs = runExecuteQuery(query);

	return rs;
	}
	
	// Method for getting the course with highest throughput
	public ResultSet getHighestThroughput() throws SQLException {

	String query = "SELECT TOP 1(courseID) AS 'courseID', (SUM(CASE WHEN letterGrade = 'A' OR letterGrade = 'B' OR letterGrade = 'C' OR letterGrade = 'D' OR letterGrade = 'E' THEN 1 ELSE 0 END)* 100)/ COUNT(courseID) AS 'percent'  FROM HasStudied   GROUP BY courseID   ORDER BY 'percent'DESC";
	ResultSet rs = runExecuteQuery(query);
	return rs;	
	}
	



}