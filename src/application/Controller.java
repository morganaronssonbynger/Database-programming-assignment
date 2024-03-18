package application;

import java.awt.Button;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {

	DataAccessLayer dataAccessLayer = new DataAccessLayer();

	// TextAreas
	@FXML
	private TextArea txtArea_findStudent, txtArea_findCourse, txtArea_addCourse, txtArea_addStudent,
			txtArea_addStudentToCourse, txtArea_resultForStudent, txtArea_addResultForStudent, txtArea_removeCourse,
			txtArea_removeStudent;

	// TextFields
	@FXML
	private TextField txt_AddStudentPhoneNumber, txt_addStudentID, txt_addStudentFirstName, txt_addStudentLastName,
			txt_addStudentEmail, txt_addStudentPhoneNumber, txt_addCourseID, txt_addCourseName, txt_addCredits,
			txt_addStudentToCourse_courseID, txt_addStudentToCourse_studentID, txt_addResultForStudent_courseID,
			txt_addResultForStudent_studentID, txt_addResultForStudent_letterGrade;

	// Panes
	@FXML
	private Pane pnl_Student, pnl_Course, pnl_Result;

	// Buttons
	@FXML
	private Button btn_menuCourse, btn_menuStudent;

	// ComboBoxes
	@FXML
	private ComboBox<String> cbFindStudent, cbox_getResultForAStudent_courseID, cbox_getResultForAStudent_studentID,
			cbox_addResultForStudent_courseID, cbox_addResultForStudent_studentID, cbox_addResultForStudent_letterGrade,
			cbox_getResultForACourse_courseID, cbox_findCourse, cbox_registerStudentOnCourse_studentID,
			cbox_registerStudentOnCourse_courseID, cbox_removeCourse_courseID, cbox_removeStudent_studentID;

	// ObservableList over lettergrades
	ObservableList<String> letterGradeList = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F");

	// Sets values into our ComboBoxes
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			cbFindStudent.setItems(dataAccessLayer.populateCBStudent());
			cbox_getResultForAStudent_courseID.setItems(dataAccessLayer.populateCBCourse());
			cbox_addResultForStudent_letterGrade.setItems(letterGradeList);
			cbox_getResultForAStudent_studentID.setItems(dataAccessLayer.getObservableListStudent());
			cbox_addResultForStudent_courseID.setItems(dataAccessLayer.getObservableListCourse());
			cbox_addResultForStudent_studentID.setItems(dataAccessLayer.getObservableListStudent());
			cbox_getResultForACourse_courseID.setItems(dataAccessLayer.getObservableListCourse());
			cbox_findCourse.setItems(dataAccessLayer.getObservableListCourse());
			cbox_registerStudentOnCourse_studentID.setItems(dataAccessLayer.getObservableListStudent());
			cbox_registerStudentOnCourse_courseID.setItems(dataAccessLayer.getObservableListCourse());
			cbox_removeCourse_courseID.setItems(dataAccessLayer.getObservableListCourse());
			cbox_removeStudent_studentID.setItems(dataAccessLayer.getObservableListStudent());

		} catch (SQLException e) {
		}
	}

	// Methods for switching between panes
	public void buttonStudent(ActionEvent student) {
		pnl_Student.toFront();
	}

	public void buttonCourse(ActionEvent course) {
		pnl_Course.toFront();
	}

	public void buttonResult(ActionEvent result) {
		pnl_Result.toFront();
	}

	// Method for checking if a inserted value can be converted to an integer
	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	// Method returning error message for SQLException 0
	public String getErrorMessage0() {
		String msg = "Connection problems, please check" + "\n" + "your WiFi/internet connection," + "\n"
				+ "if error still persists" + "\n" + "please call our IT-support.";
		return msg;
	}

	// Method for finding information about a student
	public void btnGetStudent(ActionEvent event) {
		String studentID = cbFindStudent.getSelectionModel().getSelectedItem();

		if (studentID == null) {
			txtArea_findStudent.setText("Please fill in a student ID " + "\n" + "before searching");

		}
		try {
			ResultSet studentResultSet = dataAccessLayer.getStudent(studentID);

			while (studentResultSet.next()) {
				String studentStudentID = studentResultSet.getString("studentID");
				String studentStudentName = studentResultSet.getString("fullName");
				String studentEmailAddress = studentResultSet.getString("email");
				String studentPhoneNumber = studentResultSet.getString("phoneNumber");
				txtArea_findStudent.setText("Student ID: " + studentStudentID + "\n" + "Name: " + studentStudentName
						+ "\n" + "Emailaddress: " + studentEmailAddress + "\n" + "Phonenumber: " + studentPhoneNumber);

			}

		} catch (SQLException e) {
			if (e.getErrorCode() == 0) {
				txtArea_findStudent.setText(getErrorMessage0());
			}
		}
	}

	// Method for adding a student
	public void btnAddStudent(ActionEvent event) {
		String tmpStudentID = txt_addStudentID.getText();
		String firstName = txt_addStudentFirstName.getText();
		String lastName = txt_addStudentLastName.getText();
		String email = txt_addStudentEmail.getText();
		String phoneNumber = txt_addStudentPhoneNumber.getText();
		String fullName = firstName + " " + lastName;
		String studentID = "S" + tmpStudentID;

		boolean firstNameValidation = Pattern.matches("[a-zA-ZüéèåäöÜÉÈÅÄÖ\\-]+", firstName);
		boolean lastNameValidation = Pattern.matches("[a-zA-ZüéèåäöÜÉÈÅÄÖ]+", lastName);

		if (tmpStudentID.equals("") || firstName.equals("") || lastName.equals("") || email.equals("")
				|| phoneNumber.equals("")) {
			txtArea_addStudent.setText("Please fill in a student ID, first name," + "\n"
					+ "last name, emailadress and a" + "\n" + "phonenumber before creating.");
		} else if (firstNameValidation == false || lastNameValidation == false) {
			txtArea_addStudent.setText(
					"Illegal characters in first " + "\n" + "and/or last name," + "\n" + "legal characters are A-Ö");
		} else if (!isInt(phoneNumber)) {
			txtArea_addStudent.setText("Please write the phonenumber" + "\n" + "with numbers.");
		} else if (!isInt(tmpStudentID)) {
			txtArea_addStudent.setText("Please write the student id" + "\n" + "with numbers.");
		} else {

			try {
				dataAccessLayer.addStudent(studentID, fullName, email, phoneNumber);

				txtArea_addStudent
						.setText("Student " + studentID + " " + fullName + "\n" + "was added to the register");

				dataAccessLayer.addStudentToOL(studentID);

				txt_addStudentID.setText("");
				txt_addStudentFirstName.setText("");
				txt_addStudentLastName.setText("");
				txt_addStudentEmail.setText("");
				txt_addStudentPhoneNumber.setText("");
			}

			catch (SQLException e) {
				if (e.getErrorCode() == 2627) {
					txtArea_addStudent.setText("The student ID that you have " + "\n" + "entered already exists, "
							+ "\n" + "please try another one.");
				} else if (e.getErrorCode() == 0) {
					txtArea_addStudent.setText(getErrorMessage0());
				}
				else if (e.getErrorCode() == 8152) {
					txtArea_addStudent.setText("Please write a student id" + "\n" + "with less than 10 digits," + "\n" + "the full name with not more" + "\n" +  "than 100 characters," + "\n" + "the emailadress with not more" + "\n" + "than 50 characters and " + "\n" + "a phonenumber with not more" + "\n"+  "than 20 digits");
				}

			}

		}
	}

	// Method for finding information about a course and the students studying it
	public void btnGetCourse(ActionEvent event) {
		String courseID = cbox_findCourse.getSelectionModel().getSelectedItem();
		if (courseID == null) {
			txtArea_findCourse.setText("Please fill in a course ID before " + "\n" + "searching.");
		} else {
			try {
				ResultSet courseResultSet = dataAccessLayer.getCourse(courseID);
				ResultSet studentsStudiesResultSet = dataAccessLayer.getStudentsFromStudies(courseID);
				StringBuilder sb = new StringBuilder();

				while (studentsStudiesResultSet.next()) {
					String studentID = studentsStudiesResultSet.getString("studentID");
					String fullName = studentsStudiesResultSet.getString("fullName");
					sb.append("Student ID: " + studentID + "\n" + "Student Name: " + fullName + "\n");
				}

				while (courseResultSet.next()) {
					String courseCourseID = courseResultSet.getString("courseID");
					String courseCourseName = courseResultSet.getString("courseName");
					String courseCredits = courseResultSet.getString("credits");
					txtArea_findCourse.setText("Course ID: " + courseCourseID + "\n" + "Name: " + courseCourseName
							+ "\n" + "Credits: " + courseCredits + "\n" + "\n" + "Students on this course: " + "\n"
							+ "\n" + sb.toString());

				}

			} catch (SQLException e) {
				if (e.getErrorCode() == 0) {
					txtArea_findCourse.setText(getErrorMessage0());
				}
			}
		}
	}

	// Method for adding a course
	public void btnAddCourse(ActionEvent event) {
		String tmpCourseID = txt_addCourseID.getText();
		String courseName = txt_addCourseName.getText();
		String creditsText = txt_addCredits.getText();
		String courseID = "C" + tmpCourseID;

		if (tmpCourseID.equals("") || courseName.equals("") || creditsText.equals("")) {
			txtArea_addCourse.setText(
					"Please choose a course ID, " + "\n" + "course name and the number " + "\n" + "of credits");
		} else if (!isInt(creditsText)) {
			txtArea_addCourse.setText("Please write the credits" + "\n" + "with numbers");
		} else if (!tmpCourseID.matches("[0-9]+")) {
			txtArea_addCourse.setText("Please enter the course id" + "\n" + "with numbers.");
		} else if (Integer.parseInt(creditsText) <= 0 || Integer.parseInt(creditsText) >= 30) {
			txtArea_addCourse.setText("Maximum amount of credits " + "\n" + "for a course is 30");
		} else {
			try {
				int credits = Integer.parseInt(creditsText);
				dataAccessLayer.addCourse(courseID, courseName, credits);
				txtArea_addCourse.setText("The course " + "\n" + courseID + " " + courseName + "\n" + "was added to the register.");
				dataAccessLayer.addCourseToOL(courseID);

				txt_addCourseID.setText("");
				txt_addCourseName.setText("");
				txt_addCredits.setText("");

			} catch (SQLException e) {
				if (e.getErrorCode() == 2627) {
					txtArea_addCourse.setText("The Course ID that you have " + "\n" + "entered already exists, " + "\n"
							+ "please try another one.");
				} else if (e.getErrorCode() == 0) {
					txtArea_addCourse.setText(getErrorMessage0());
				}
				else if (e.getErrorCode() == 8152) {
					txtArea_addCourse.setText("Please write a course id " + "\n" + "with less than 10 digits," + "\n" + "a course name with not more" + "\n" + "than 50 characters");
				}
			}

		}
	}

	// Method for adding a student on a course
	public void btnAddStudentToCourse(ActionEvent event) {
		String courseID = cbox_registerStudentOnCourse_courseID.getSelectionModel().getSelectedItem();
		String studentID = cbox_registerStudentOnCourse_studentID.getSelectionModel().getSelectedItem();
		boolean canStudentStudie = false;
		
		if (courseID == null || studentID == null || courseID.equals("") || studentID.equals("")) {
			txtArea_addStudentToCourse
					.setText("Please fill in both a course ID" + "\n" + "and a student ID before adding.");
		} else {
			try {
				ResultSet studentInHasStudied = dataAccessLayer.findOneHasStudied(studentID, courseID);
				while (studentInHasStudied.next()) {
					String studentIDHS = studentInHasStudied.getString("studentID");
					String courseIDHS = studentInHasStudied.getString("courseID");
					if (studentIDHS.equals(studentID) && courseIDHS.equals(courseID)) {
						dataAccessLayer.removeStudentFromHasStudied(studentIDHS);
					}

				}
				ResultSet students = dataAccessLayer.getStudentsWhoCanStudieTheCourse(courseID);
				while (students.next()) {

					String studentIDFromRS = students.getString("studentID");
					try {
						if (studentID.equals(studentIDFromRS)) {
							canStudentStudie = true;
							dataAccessLayer.addStudentToCourse(courseID, studentID);
							txtArea_addStudentToCourse.setText(
									"Student ID: " + studentID + " was added" + "\n" + "to course ID: " + courseID);
						}

					} catch (SQLException e) {
				
						if (e.getErrorCode() == 2627) {
							txtArea_addStudentToCourse.setText("The student id that you have " + "\n"
									+ "entered already studies that course," + "\n" + "please try another Course ID.");
						}

						else if (e.getErrorCode() == 0) {
							txtArea_addStudentToCourse.setText(getErrorMessage0());
						}

					}
				}

				if (canStudentStudie == false) {
					txtArea_addStudentToCourse.setText("The credits exceed the maximum" + "\n" + "limit of 45 for a student");
				}
			}

			catch (SQLException e) {

				if (e.getErrorCode() == 0) {
					txtArea_addStudentToCourse.setText(getErrorMessage0());
				}
			}
		}
	}

	// Method for adding a result for a student on a course
	public void btnAddResultForStudent(ActionEvent event) {
		String courseID = cbox_addResultForStudent_courseID.getSelectionModel().getSelectedItem();
		String studentID = cbox_addResultForStudent_studentID.getSelectionModel().getSelectedItem();
		String letterGrade = cbox_addResultForStudent_letterGrade.getSelectionModel().getSelectedItem();

		if (courseID == null || studentID == null || letterGrade == null) {
			txtArea_addResultForStudent.setText("Please choose a course ID," + "\n" + "student ID and a grade" + "\n" + "before adding");
		} else {
			try {
				if (dataAccessLayer.findOneStudentOnCourse(studentID, courseID).next()) {
					dataAccessLayer.addResultForStudent(studentID, courseID, letterGrade);
					dataAccessLayer.removeStudentFromStudies(studentID, courseID);
					txtArea_addResultForStudent.setText("The following result " + letterGrade + "\n" + "was added for "
							+ studentID + " on " + courseID);
				} else {
					txtArea_addResultForStudent.setText("The following student " + studentID + "\n" + "does not study course " + courseID);
				}
			} catch (SQLException e) {
				if (e.getErrorCode() == 2627) {
					txtArea_addResultForStudent.setText("The student has already gotten a grade" + "\n"
							+ "for that course, " + "\n" + "please try another Course ID/Student ID.");
				} else if (e.getErrorCode() == 0) {
					txtArea_addResultForStudent.setText(getErrorMessage0());
				}
			}

		}
	}

	// Method for finding a result for a student on a course
	public void btnGetResultForAStudent(ActionEvent event) {
		String courseID = cbox_getResultForAStudent_courseID.getSelectionModel().getSelectedItem();
		String studentID = cbox_getResultForAStudent_studentID.getSelectionModel().getSelectedItem();
		if (courseID == null || studentID == null || studentID.equals("") || courseID.equals("")) {
			txtArea_resultForStudent.setText("Please choose both a course ID " + "\n"
					+ "and a student ID before searching " + "\n" + "for a result.");
		} else {
			try {
				if (dataAccessLayer.findOneHasStudied(studentID, courseID).next()) {
					ResultSet resultForAStudent = dataAccessLayer.getResultForAStudent(courseID, studentID);
					while (resultForAStudent.next()) {
						String resultStudentID = resultForAStudent.getString("studentID");
						String resultFullName = resultForAStudent.getString("fullName");
						String resultCourseID = resultForAStudent.getString("courseID");
						String resultLetterGrade = resultForAStudent.getString("letterGrade");
						txtArea_resultForStudent.setText("Student ID: " + resultStudentID + "\n" + "Student name: " + resultFullName
										+ "\n" + "Course ID: " + resultCourseID + "\n" + "Grade: " + resultLetterGrade);
					}
				} else {
					txtArea_resultForStudent.setText("The student ID that you have " + "\n" + "entered does not have a result " + "\n"
									+ "on that course." + "\n" + "Please try another one");
				}
			} catch (SQLException e) {
			
				if (e.getErrorCode() == 0) {
					txtArea_resultForStudent.setText(getErrorMessage0());
				}
			}
		}
	}

	// Method for finding all results on a specific course, and the percentage of students who got A on that course
	public void btnGetResultsForACourse(ActionEvent event) {
		String courseID = cbox_getResultForACourse_courseID.getSelectionModel().getSelectedItem();
		StringBuilder sb = new StringBuilder();
		String stringPercentage;
		double nbrADouble = 0;
		double nbrStudents = 0;

		if (courseID == null || courseID.equals("")) {
			txtArea_resultForStudent.setText("Please choose a course ID " + "\n" + "before searching.");
		} else {

			try {

				ResultSet NbrOfStudentsWhoGotA = dataAccessLayer.getStudentWhoGotAOnACourse(courseID);
				ResultSet NbrOfStudentsOnACourse = dataAccessLayer.getNumberOfStudentsOnACourse(courseID);

				while (NbrOfStudentsWhoGotA.next()) {
					String nbrA = NbrOfStudentsWhoGotA.getString("nbr");
					nbrADouble = Double.parseDouble(nbrA);

					while (NbrOfStudentsOnACourse.next()) {
						String courseIDInHasStudied = NbrOfStudentsOnACourse.getString("courseID");
						String nbrOnCourse = NbrOfStudentsOnACourse.getString("nbr");
						nbrStudents = Double.parseDouble(nbrOnCourse);

						if (courseIDInHasStudied.equals(courseID)) {
							double percentage = (nbrADouble / nbrStudents) * 100;
							stringPercentage = Double.toString(percentage);
							sb.append("Percentage of students who got" + "\n" + "grade A on this course : " + "\n"
									+ "\n" + stringPercentage + "%" + "\n" + "\n");

						}

					}
				}

			}

			catch (SQLException e) {

				if (e.getErrorCode() == 0) {
					txtArea_resultForStudent.setText(getErrorMessage0());
				}

			}

			try {
				ResultSet resultForACourse = dataAccessLayer.getResultForACourse(courseID);
				//Here we have duplicate of resultset to handle bug. Bug was that the first row of the resultset was missed and thereby not all results for a student was presented.
				ResultSet resultForACourse2 = dataAccessLayer.getResultForACourse(courseID);

				if (!resultForACourse2.next()) {
					txtArea_resultForStudent.setText("The following course " + courseID + "\n" + "has no results");
				} else {
					while (resultForACourse.next()) {
						String resultStudentID = resultForACourse.getString("studentID");
						String resultFullName = resultForACourse.getString("fullName");
						String resultLetterGrade = resultForACourse.getString("letterGrade");

						sb.append("Student ID: " + resultStudentID + "\n" + "Student name: " + resultFullName + "\n"
								+ "Grade: " + resultLetterGrade + "\n" + "\n");
					}
					txtArea_resultForStudent.setText(sb.toString());
				}
			} catch (SQLException e) {

				if (e.getErrorCode() == 0) {
					txtArea_resultForStudent.setText(getErrorMessage0());
				}
			}

		}

	}

	// Method for removing a student from a course
	public void btnRemoveStudentFromACourse(ActionEvent event) {
		String courseID = cbox_registerStudentOnCourse_courseID.getSelectionModel().getSelectedItem();
		String studentID = cbox_registerStudentOnCourse_studentID.getSelectionModel().getSelectedItem();

		if (courseID == null || studentID == null || studentID.equals("") || courseID.equals("")) {
			txtArea_addStudentToCourse
					.setText("Please choose both a course ID and" + "\n" + "a student ID before removing.");
		} else {

			try {
				if (dataAccessLayer.findOneStudentOnCourse(studentID, courseID).next()) {
					dataAccessLayer.removeStudentFromStudies(studentID, courseID);
					txtArea_addStudentToCourse.setText("You have removed " + studentID + "\n" + "from " + courseID);
					cbox_registerStudentOnCourse_courseID.setValue("");
					cbox_registerStudentOnCourse_studentID.setValue("");
				} else {
					txtArea_addStudentToCourse.setText("Chosen student does not study" + "\n" + "on that course");
				}

			} catch (SQLException e) {

				if (e.getErrorCode() == 0) {
					txtArea_addStudentToCourse.setText(getErrorMessage0());
				}

			}
		}
	}

	// Method for removing a course
	public void btnRemoveCourse(ActionEvent event) {
		String courseID = cbox_removeCourse_courseID.getSelectionModel().getSelectedItem();
		if (courseID == null || courseID.equals("")) {
			txtArea_removeCourse.setText("Please choose a course ID" + "\n" + "before removing.");
		} else {
			try {
				dataAccessLayer.removeCourseFromStudies(courseID);
				dataAccessLayer.removeCourseFromHasStudied(courseID);
				dataAccessLayer.removeCourse(courseID);
				dataAccessLayer.removeCourseFromOL(courseID);
				txtArea_removeCourse.setText("The following course was removed" + "\n" + courseID);
				cbox_removeCourse_courseID.setValue("");
			} catch (SQLException e) {

				if (e.getErrorCode() == 0) {
					txtArea_removeCourse.setText(getErrorMessage0());
				}

			}

		}
	}

	// Method for removing a student
	public void btnRemoveStudent(ActionEvent event) {

		String studentID = cbox_removeStudent_studentID.getSelectionModel().getSelectedItem();

		if (studentID == null || studentID.equals("")) {
			txtArea_removeStudent.setText("Please choose a Student ID" + "\n" + "before removing");
		} else {
			try {
				dataAccessLayer.removeStudentFromStudies(studentID);
				dataAccessLayer.removeStudentFromHasStudied(studentID);
				dataAccessLayer.removeStudent(studentID);
				dataAccessLayer.removeStudentFromOL(studentID);
				txtArea_removeStudent.setText("The following student was removed" + "\n" + studentID);
				cbox_removeStudent_studentID.setValue("");
			} catch (SQLException e) {
			
				if (e.getErrorCode() == 0) {
					txtArea_removeStudent.setText(getErrorMessage0());
				}

			}

		}
	}

	// Method for getting the course with the highest throughput
	public void btnGetCourseWithHighestThroughput() {
		try {
			
		ResultSet rs = dataAccessLayer.getHighestThroughput();

		if(rs.next()) {
			String courseID = rs.getString("courseID");
			String percent = rs.getString("percent");
			txtArea_findCourse.setText("The course " + courseID  + " has the" + "\n" + "highest throughput which is " + percent + "%");
		}

		} catch (SQLException e) {

		}

	}

}