//
//Amer Alwan - 46266alw
//Assignment 7 - MarkReport
//18 October 2019
//In this assignment, we will be combining array and methods to create a program that gets the mark averages for an inputted number of students and then displays info based on the marks like the class average, the highest/lowest marks, and the honour/failed marks

package MarkReport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import SimpleMethods.*;

public class MarkReport2 {
	
	static SMethods method = new SMethods();
	

	/**
	 * Asks the user for an inputted number of marks
	 * 
	 * @param length the int amount of marks to get
	 * @return the int array containing all the marks that the user inputted
	 */
	public static int[] getMarks(int length) {
		// Variable declarations and initializations
		int[] marks = new int[length];
		// Asks user for marks between a range of 0 and 100
		for (int i = 0; i < length; i++) {
			marks[i] = method.getIntBetween("Mark " + (i + 1) + ": ", 0, 100);
		}

		return marks;
	}

	/**
	 * Calculates the class average
	 * 
	 * @param marks the int array containing all the marks
	 * @return the double average of the class
	 */
	public static double calcClassAverage(int[] marks) {
		double total = 0;
		for (int i = 0; i < marks.length; i++) {
			total += marks[i];
		}
		total /= marks.length;
		return total;
	}

	/**
	 * Displays the highest value in the marks array
	 * 
	 * @param marks the int array containing all the marks
	 */
	public static void displayHighestMark(int[] marks) {
		// Sorts the values in the marks array in ascending order (lowest value to
		// highest)
		Arrays.sort(marks);
		// If the array is sorted in ascending order, it makes sense that the highest
		// value in the array is also the last value in the array
		System.out.println("The highest mark is " + marks[marks.length - 1]);
	}

	/**
	 * Displays the lowest value in the marks array
	 * 
	 * @param marks the int array containing all the marks
	 */
	public static void displayLowestMark(int[] marks) {
		// Sorts the values in the marks array in ascending order (lowest value to
		// highest)
		Arrays.sort(marks);
		// If the array is sorted in ascending order, it makes sense that the lowest
		// value in the array is also the first value in the array
		System.out.println("The lowest mark is " + marks[0]);
	}

	/**
	 * Displays how many students have an honour roll as well as their mark
	 * 
	 * @param marks the int array containing all the marks
	 */
	public static void displayHonourRolls(int[] marks) {
		// Gets how many values in the marks array are between and equal to 80 and 100
		int numOfHonourRolls = method.returnValuesInRange(marks, 80, 100);
		System.out.println("There are " + numOfHonourRolls + " students with Honour Rolls.");
		// Displays which values in the marks array are between and equal to 80 and 100
		method.displayValuesInRange(marks, 80, 100);
	}

	/**
	 * Displays how many students have failed
	 * 
	 * @param marks the int array containing all the marks
	 */
	public static void displayFailedMarks(int[] marks) {
		// Gets how many values in the marks array are between and equal to 0 and 49
		int numOfFailedMarks = method.returnValuesInRange(marks, 0, 49);
		System.out.println("There are " + numOfFailedMarks + " students who have failed.");
		// Displays which values in the marks array are between and equal to 0 and 49
		method.displayValuesInRange(marks, 0, 49);
	}

	/**
	 * Calculates and displays the Milardovic Deviation of the marks
	 * 
	 * @param marks   the int array containing all the marks
	 * @param average the double average of all the marks
	 */
	public static void displayMDev(int[] marks, double average) {
		double total = 0;

		// Goes through every value in the marks array, subtracts from it the average,
		// and adds the total to the total double
		for (int i = 0; i < marks.length; i++) {
			total += Math.abs(marks[i] - average);
		}

		total /= marks.length;

		System.out.printf("The MDev of the class is %1.2f", total);
	}

	/**
	 * Manually asks the user to enter each mark based on how many students they say
	 * are in the class
	 * 
	 * @return the int array containing all the marks the user enters
	 * @throws IOException
	 */
	public static int[] getMarksFromUser() throws IOException {
		// Variable declarations and initializations
		int numOfStudents;
		int[] marks;
		String userInput;

		Scanner input = new Scanner(System.in);

		PrintWriter output;

		// Asks for the number of students in the class
		System.out.print("Enter number of students: ");
		numOfStudents = input.nextInt();
		input.nextLine();

		// Asks user for marks and returns the array into marks
		marks = getMarks(numOfStudents);

		// Error trap loop that ensures a 'y' or 'n' input when asking the user if they
		// want to store their inputted marks in a file
		userInput = method.getStringAssert("Do you want to store the marks in a file? [y/n] ", "y", "n");

		// If the user inputted 'y' for their answer, then store the results in a file
		// of the name of their choosing
		if (userInput.equals("y")) {
			// Gets file name from user
			System.out.print("Input file name: ");
			userInput = input.nextLine();
			// Adds a .txt extension if the user did not input one
			if (userInput.contains(".") == false) {
				userInput += ".txt";
			}

			output = new PrintWriter(new FileWriter(userInput));
			// Adds the number of students to the first line of the file
			output.println(String.valueOf(marks.length));
			// Adds each mark to a new line int he file
			for (int i = 0; i < marks.length; i++) {
				output.println(String.valueOf(marks[i]));
			}
			// Closes file
			output.close();
		}

		return marks;
	}

	/**
	 * Gets the marks from a file that has its name and extension inputted by the
	 * user
	 * 
	 * @return the int array containing the marks that were stored in the file
	 * @throws IOException
	 */
	public static int[] getMarksFromFile() throws IOException {
		// Variable declarations and initializations
		BufferedReader input;
		File file;
		String userInput;
		Scanner s = new Scanner(System.in);
		int numOfStudents = 0;
		int[] marks;

		// Error trap loop that asks the user for the file name and exits if it exists
		while (true) {
			// Asks user for file name
			System.out.print("Enter file name: ");
			userInput = s.nextLine();
			// If the file contains a '.', then it means the user put some kind of file
			// extension. If not, then just assume it is a .txt
			if (userInput.contains(".") == false) {
				userInput += ".txt";
			}
			file = new File(userInput);
			// Exits loop if the file exists
			if (file.exists()) {
				break;
			} else {
				System.out.println("The file does not exist");
				System.out.println();
			}
		}

		input = new BufferedReader(new FileReader(userInput));

		// Gets the first line in the file, which contains the number of students
		numOfStudents = Integer.valueOf(input.readLine());

		// Initialize the marks array's size
		marks = new int[numOfStudents];

		System.out.println("The marks in the file are: ");

		// Gets each line in the file, which contains a mark, stores its value in the
		// corresponding space in the marks array as well as displays it on the screen
		for (int i = 0; i < marks.length; i++) {
			marks[i] = Integer.valueOf(input.readLine());
			System.out.println("Mark " + (i + 1) + ": " + marks[i]);
		}

		// Closes file
		input.close();

		return marks;
	}

	public static void displayResults(int[] marks) {
		// Calculates the class average
		double classAverage = calcClassAverage(marks);
		System.out.println();
		System.out.printf("The Class Average is %1.2f \n", classAverage);
		System.out.println();
		// Displays the highest marks
		displayHighestMark(marks);
		System.out.println();
		// Displays the lowest mark
		displayLowestMark(marks);
		System.out.println();
		// Displays the honour roll marks
		displayHonourRolls(marks);
		System.out.println();
		// Displays the failed marks
		displayFailedMarks(marks);
		System.out.println();
		// Displays the Milardovic Deviation
		displayMDev(marks, classAverage);
		System.out.println();
		System.out.println();

	}

	public static void main(String[] args) throws IOException {
		// Variable Declarations and Initializations
		int[] marks;
		String userInput;
		int userChoice;

		Scanner input = new Scanner(System.in);

		while (true) {

			// Displays Menu
			System.out.println("------------------------------");
			System.out.println("1 - Enter marks manually");
			System.out.println("2 - Read mark from file");
			System.out.println("3 - Exit");
			System.out.println("------------------------------");
			System.out.println();
			// Gets user choice
			userChoice = method.getIntBetween("Choice: ", 1, 3);

			// Gets marks differently or exits program based on the user's input
			if (userChoice == 1) {
				marks = getMarksFromUser();
				displayResults(marks);
			} else if (userChoice == 2) {
				marks = getMarksFromFile();
				displayResults(marks);
			} else {
				System.exit(0);
			}

			// Asks the user if they want to restart and exits when they input 'n'
			System.out.print("Would you like to add a new class? [y/n]: ");
			userInput = input.nextLine();
			if (userInput.toLowerCase().equals("n")) {
				break;
			}
		}
	}
}
