//Amer Alwan - 46266alw\
//Marks Report 2 Assignment
//2 December 2019
//In this assignment, I will be recoding my marks report assignment, but instead creating each student as an object that has all their corresponding information stored in that object

package MarksReport2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MarksReport2 {

	/**
	 * Asks the user for the file containing the student info and then instantiates an array of student objects based on that data
	 * @return a student object array containing all the fully instantiated student objects
	 * @throws IOException
	 */
	public static Student[] getStudents() throws IOException {
		//Variable Declarations and Initializations
		String fileName = "";
		BufferedReader input;
		File tempFile;
		Student[] students;
		Scanner s = new Scanner(System.in);

		//Error trap loop ensures that the file exists
		while (true) {
			System.out.print("Enter FileName: ");
			fileName = s.nextLine();

			//Adds .csv to the end of the file if it does not already
			if (!fileName.contains(".csv")) {
				fileName += ".csv";
			}

			//Instantiates the tempFile object with the fileName
			tempFile = new File(fileName);

			//Exits loop if the file does not exist
			if (tempFile.exists()) {
				break;
			} else {
				System.out.println("File Does Not Exist!");
			}

		}
		
		//Instantiates the input BufferedReader object
		input = new BufferedReader(new FileReader(fileName));

		//Initializes the student object array and its size
		students = new Student[Integer.valueOf(input.readLine())];

		//For loop that instantiates every student object
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(input.readLine());
		}

		//close file
		input.close();
		
		return students;
	}

	/**
	 * Displays the average of all students in all grades
	 * 
	 * @param students the student object array containing all the students
	 */
	public static void displayAverage(Student[] students) {
		// Variable Declarations and Initializations
		double average = 0;

		// For loop that adds all the student's averages to the overall average
		for (int i = 0; i < students.length; i++) {
			average += students[i].getAverage();
		}

		// Divides the overall average by the amount of students
		average /= students.length;

		// Displays the overall average
		System.out.printf("The school average is %1.2f", average);
	}

	/**
	 * Displays the data of all students in a field sizing format
	 * 
	 * @param students the student object array containing all the students
	 */
	public static void displayAllData(Student[] students) {
		// Variable Declarations and Initializations
		String userInput = null;
		Scanner s = new Scanner(System.in);

		// Field Size that displays a header for all the student data
		System.out.printf("%-30s %-20s %-10s %-10s %-10s %-10s %-10s \n", "Name", "Grade", "Course 1", "Course 2",
				"Course 3", "Course 4", "Average");
		System.out.println();

		// For loop that displays the field size format of the 10 students at a time
		for (int i = 0; i < students.length; i++) {
			System.out.println(students[i].toStringFormat());
			// prompt the user to press enter to exit the scanner and load 10 more data
			if (i != 0 && i % 10 == 0) {
				System.out.println("Press Enter to load more data");
				userInput = s.nextLine();
			}
		}
	}

	/**
	 * Displays the overall student average in a user-inputted grade
	 * 
	 * @param students the student object array containing all the students
	 */
	public static void displayAverageByGrade(Student[] students) {
		// variable declarations and initializations
		double average = 0;
		int grade = getGrade(), numOfStudents = 0;

		// For loop that goes through every student in the grade and adds their average
		// to the overall average. It also adds 1 to numOfStudents, which represents the
		// amount of students in that grade
		for (int i = 0; i < students.length; i++) {
			if (students[i].getGrade() == grade) {
				average += students[i].getAverage();
				numOfStudents += 1;
			}
		}

		// Divides the overall average by the overall students in the user-inputted
		// grade.
		average /= numOfStudents;

		// Displays the overall average
		System.out.printf("The Average for this grade is %1.2f", average);
	}

	/**
	 * Displays which student(s) have the highest mark in a grade
	 * 
	 * @param students the student object array containing all the students
	 */
	public static void displayHighestMark(Student[] students) {
		// Variable declarations and initializations
		int highestMark = 0, grade = getGrade();

		// For loop that goes through all students in a grade and compares their highest
		// mark with the current highest mark. If a student has a higher mark, then the
		// highestMark is updated to be equal to that student's highest mark
		for (int i = 0; i < students.length; i++) {
			if (students[i].getGrade() == grade && students[i].getHighestMark() > highestMark) {
				highestMark = students[i].getHighestMark();
			}
		}

		// The for loop goes through all students and prints the name and mark of the
		// students
		// who have their highest mark equal to the value of highestMark
		for (int i = 0; i < students.length; i++) {
			if (students[i].getGrade() == grade && students[i].getHighestMark() == highestMark) {
				System.out.println(students[i].getName() + " has the highest mark of " + students[i].getHighestMark());
			}
		}
	}

	/**
	 * Displays which student(s) have the highest average in a grade
	 * 
	 * @param students the student object array containing all the student objects
	 */
	public static void displayHighestAverage(Student[] students) {
		// variable declarations and initializations
		double highestAverage = 0, grade = getGrade();

		// For loop that goes through all students in a grade and compares their average
		// with the current highest Average. If a student has a higher average, then the
		// highestAverage is updated to be equal to that student's average
		for (int i = 0; i < students.length; i++) {
			if (students[i].getGrade() == grade && students[i].getAverage() > highestAverage) {
				highestAverage = students[i].getAverage();
			}
		}

		// The for loop goes through all the students and
		// prints the name and average of the students who have an average equal to the
		// highestAverage
		for (int i = 0; i < students.length; i++) {
			if (students[i].getGrade() == grade && students[i].getAverage() == highestAverage) {
				System.out.println(students[i].getName() + "'s average is " + students[i].getAverage());
			}
		}
	}

	/**
	 * Asks user for grade and gets a value that is between and equal to 9 and 11
	 * 
	 * @return the int grade that the user inputs
	 */
	public static int getGrade() {
		// Variable declarations and initializations
		int userChoice;
		Scanner s = new Scanner(System.in);

		System.out.println("");

		// Error trap loop ensures that the grade is between and equal to 9 and 11
		while (true) {
			// Get grade
			System.out.print("Grade: ");
			userChoice = s.nextInt();

			// Exit loop if grade is between and equal to 9 and 11
			if (userChoice >= 9 && userChoice <= 11) {
				return userChoice;

			} else {
				System.out.println("Grade must be between and equal to 9 and 11");
			}
		}

	}

	public static void main(String[] args) throws IOException {
		// Variable/Object Declarations and Initializations/Instantiations
		Student[] students = getStudents();
		Scanner s = new Scanner(System.in);
		int userChoice;

		// Options Menu
		System.out.println("=====Options=====");
		System.out.println("1-Display All Student Data");
		System.out.println("2-Display Overall School Average");
		System.out.println("3-Display Average for a Specific Grade");
		System.out.println("4-Display Highest Individual Mark by Grade");
		System.out.println("5-Display Highest Average Mark by Grade");
		System.out.println("6-Exit Program");
		System.out.println();

		// Error trap loop ensures the choice is between and equal to 1 and 6
		while (true) {
			System.out.print("Choice: ");
			userChoice = s.nextInt();
			s.nextLine();
			if (userChoice >= 1 && userChoice <= 6) {
				break;
			} else {
				System.out.println("User Choice has to be between and equal to 1 and 6");
			}
		}

		// Display all the student Data
		if (userChoice == 1) {
			displayAllData(students);
		}
		// Display the average of the whole school
		if (userChoice == 2) {
			displayAverage(students);
		}
		// Display the average of a whole specific grade
		if (userChoice == 3) {
			displayAverageByGrade(students);
		}
		// Display the highest mark in a specific grade
		if (userChoice == 4) {
			displayHighestMark(students);
		}
		// Display the highest average of a specific grade
		if (userChoice == 5) {
			displayHighestAverage(students);
		}
		// Exit program
		if (userChoice == 6) {
			System.exit(0);
		}

	}

}
