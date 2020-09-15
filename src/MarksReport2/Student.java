package MarksReport2;

import java.util.Arrays;

public class Student {

	//Variable declaration and initializations
	protected String lName, fName;
	protected int grade;
	private int[] marks = new int[4];

	/**
	 * Student constructor that takes one line from the student csv file and splits up the information and stores it into the object's variables
	 * @param csvData the string line containing a line from the csv file containing all student data
	 */
	public Student(String csvData) {
		//Splits csvData line at the '|' and stores each information piece seperately in the data array
		String[] data = csvData.split("\\|");

		//Initializes the object's variables using values acquired from the data line
		this.lName = data[0];
		this.fName = data[1];
		this.grade = Integer.valueOf(data[2]);
		this.marks[0] = Integer.valueOf(data[3]);
		this.marks[1] = Integer.valueOf(data[4]);
		this.marks[2] = Integer.valueOf(data[5]);
		this.marks[3] = Integer.valueOf(data[6]);
	}

	/**
	 * Sets the object's first name variable
	 * @param fName the string to change the first name to
	 */
	public void setFName(String fName) {
		this.fName = fName;
	}

	/**
	 * Sets the object's last name variable
	 * @param lName the string to change the last name to
	 */
	public void setLName(String lName) {
		this.lName = lName;
	}

	/**
	 * Sets the object's grade variable
	 * @param grade the int to change the grade to
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * Sets the object's first course mark in the marks array
	 * @param marks the int to change the first course mark to
	 */
	public void setMarkC1(int marks) {
		this.marks[0] = marks;
	}

	/**
	 * Sets the object's second course mark in the marks array
	 * @param marks the int to change the second course mark to
	 */
	public void setMarkC2(int marks) {
		this.marks[1] = marks;
	}
	
	/**
	 * Sets the object's third course mark in the marks array
	 * @param marks the int to change the third course mark to
	 */
	public void setMarkC3(int marks) {
		this.marks[2] = marks;
	}
	
	/**
	 * Sets the object's fourth course mark in the marks array
	 * @param marks the int to change the fourth course mark to
	 */
	public void setMarkC4(int marks) {
		this.marks[3] = marks;
	}
	
	/**
	 * Sets all the object's course marks in the marks array
	 * @param marks the int array to set the marks array to
	 */
	public void setMarks(int[] marks) {
		this.marks = marks;
	}

	/**
	 * Get the object's last name variable
	 * @return the string last name
	 */
	public String getLastName() {
		return this.lName;
	}

	/**
	 * Get the object's first name
	 * @return the string first name
	 */
	public String getFirstName() {
		return this.fName;
	}

	/**
	 * Gets the object's full name
	 * @return a string concatenation of the first name and last name
	 */
	public String getName() {
		return this.fName + " " + this.lName;
	}

	/**
	 * Get the object's grade variable
	 * @return the int grade
	 */
	public int getGrade() {
		return this.grade;
	}

	/**
	 * Gets the object's marks array
	 * @return the int array containing all the marks
	 */
	public int[] getMarks() {
		return this.marks;
	}

	/**
	 * Get the object's first course mark
	 * @return the int of the first mark
	 */
	public int getMarkC1() {
		return this.marks[0];
	}

	/**
	 * Get the object's second course mark
	 * @return the int of the second mark
	 */
	public int getMarkC2() {
		return this.marks[1];
	}

	/**
	 * Get the object's third course mark
	 * @return the int of the third mark
	 */
	public int getMarkC3() {
		return this.marks[2];
	}

	/**
	 * Get the object's fourth course mark
	 * @return the int of the fourth mark
	 */
	public int getMarkC4() {
		return this.marks[3];
	}

	/**
	 * Get which course mark is the highest
	 * @return the int course mark that is the highest value in the marks array
	 */
	public int getHighestMark() {
		int[] tempMarks = marks;
		Arrays.sort(tempMarks);
		return tempMarks[tempMarks.length - 1];
	}

	/**
	 * Get all the object's data formatted into a csv
	 * @return the string with all the data in a csv format
	 */
	public String toCSV() {
		return lName + "|" + fName + "|" + grade + "|" + marks[0] + "|" + marks[1] + "|" + marks[2] + "|" + marks[3];
	}

	/**
	 * Get all the object's data formatted with what the value represents
	 * @return the string containing all the information and their headers
	 */
	public String toString() {
		return "First Name: " + fName + ". Last Name: " + lName + ". Grade: " + grade + ". C1 Mark: " + marks[0]
				+ ". C2 Mark: " + marks[1] + ". C3 Mark: " + marks[2] + "C4 Mark: " + marks[3];
	}

	/**
	 * Get all the object's data formatted with field Sizing
	 * @return the sting containing all the information formatted with field Sizing
	 */
	public String toStringFormat() {
		return fName + " " + lName + String.format("%" + String.valueOf(32 - (fName + lName).length()) + "s", "") + String.format("%-22d", grade)
				+ String.format("%-12d", marks[0]) + String.format("%-10d", marks[1]) + String.format("%-10d", marks[2])
				+ String.format("%-10d", marks[3]) + String.format("%-10.2f", getAverage());

	}

	/**
	 * Gets the object's average mark
	 * @return the double of the student's average calculated from their 4 course marks
	 */
	public double getAverage() {
		double average = 0;
		for (int i = 0; i < this.marks.length; i++) {
			average += this.marks[i];
		}
		return (average / this.marks.length);
	}
}
