//
//Amer Alwan - 46266alw
//Flooring Factory Assignment - Updated
//24 October 2019
//In this program, we will be experimenting with methods, arrays, and file IO (with CSV files) in java to create a program that loads in floor types and their prices form a csv file and then asks the user for a criteria and splits up those loaded floors base don that criteria while maintaining a constant listing order of said floorings.

package Assign5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FlooringFactory {
	

	/**
	 * Error trap Function that asks the user a question, and then returns a double
	 * type answer if it is within the range that the user enters
	 * 
	 * @param question         the string question to ask the user
	 * @param biggerThanEqual  the int value that the input has to be bigger than
	 * @param smallerThanEqual the int value that the input has to be smaller than
	 * @return the double input from the user
	 */
	public static double askUser(String question, int biggerThanEqual, int smallerThanEqual) {
		// Variable Declarations and Initializations
		Scanner input = new Scanner(System.in);
		double userInput;

		// Error trap loop that ensures the input is within biggerThanEqual and
		// smallerThanEqual
		while (true) {
			System.out.print(question);

			// Get the double input from user
			userInput = input.nextDouble();
			input.nextLine();

			// If the userInput is under the biggerThanEqual value or the userInput is under
			// the biggerThanEqual value and over the smallerThanEqual value, then display
			// an error to the user
			if ((smallerThanEqual == 0 && userInput < biggerThanEqual)
					|| (smallerThanEqual != 0 && (userInput < biggerThanEqual || userInput > smallerThanEqual))) {
				System.out.println("That value is not allowed");
			} else {
				return userInput;
			}
		}
	}

	/**
	 * Function that calculates the area of arectangle (width x height) based on the
	 * width and height inputs
	 * 
	 * @param width  the double width variable of the rectangle area formula
	 * @param height the double height variable of the rectangle area formula
	 * @return the double rectangle area
	 */
	public static double calcArea(double width, double height) {
		return width * height;
	}

	/**
	 * Procedure that calculates and displays the costs that the user has to pay
	 * based on their floor area and floor option
	 * 
	 * @param area  the total double area of all the rooms
	 * @param price the double price of the floor option that the user choose
	 */
	public static void displayPrice(double area, double price) {
		// Variable declarations and initializations
		double labourCost = area * 6.50;
		double materialCost = price * area;
		double subTotal = materialCost + labourCost;
		double HST = subTotal * 0.13;

		// Display all the costs
		System.out.println();
		System.out.printf("Materials Cost %-23s $%1.2f \n", "", materialCost);
		System.out.printf("Labour Cost [$6.50 per m^2] %-10s $%1.2f \n", "", labourCost);
		System.out.println("---------------------");
		System.out.printf("Sub-total %-28s $%1.2f \n", "", subTotal);
		System.out.printf("HST %-34s $%1.2f \n", "", HST);
		System.out.println("---------------------");
		System.out.printf("Total Cost %-27s $%1.2f \n", "", subTotal + HST);
	}

	/**
	 * Function that displays the choices to the user based on his criteria and then asks them for what flooring they want and returns the price's array's index of that choice
	 * 
	 * @param floorings the string array containing all flooring options
	 * @param prices    the double array containing the prices of all flooring
	 *                  options
	 * @return the int index of the user's choice in the prices array
	 */	
	public static int getUserChoice(String[] floorings, double[] prices) {
		//Variable declarations and initializations
		String criteria;
		int[] criteriaNums = new int[floorings.length];
		int pointer = 0;
		int userChoice;
		Scanner in = new Scanner(System.in);
		
		System.out.println();
		
		//Gets the user's criteria
		System.out.print("What is your listing criteria (Space to list all floorings): ");
		criteria = in.nextLine();
		
		//For loop that goes through the floorings array and checks if it contains the user's criteria. If it does, it displays that choice and adds its index to the criteriaNums array
		for(int i = 0; i < floorings.length; i++) {
			if (floorings[i].toLowerCase().contains(criteria.toLowerCase())) {
				System.out.printf("%-4s %-60s $%2.2f \n", String.valueOf(pointer + 1) + "- ", floorings[i], prices[i]);
				criteriaNums[pointer] = i;
				pointer += 1;
			}
		}
		
		
		
		// Asks the user for a choice between 1 and however floors there are that met their criteria, and then
		// returns their answer - 1.
		return criteriaNums[((int) askUser("Which flooring type would you like? [1 - " + (pointer) + "]: ", 1, pointer)) - 1];
	} 

	public static void main(String[] args) throws IOException {
		// Variable declarations and initializations
		double width, height = 0, area = 0;
		String[] floorings, data;
		String text;
		double[] prices;
		int amtOfFloorings;
		//Loading the file containing the floorings
		BufferedReader input = new BufferedReader(new FileReader("flooringlist.csv"));

		//Gets amount of floor types, which in an int on the first line of the file
		amtOfFloorings = Integer.valueOf(input.readLine());
		//Declares the floorings array's size
		floorings = new String[amtOfFloorings];
		prices = new double[amtOfFloorings];
		//For loop that gets each line in file and then splits the name and price of the floor and stores it in the floorings and prices array
		for (int i = 0; i < amtOfFloorings; i++) {
			text = input.readLine();
			data = text.split("\\|");
			floorings[i] = data[0];
			prices[i] = Double.valueOf(data[1]);
		}

		System.out.println("Please input the width and height of all your rooms [0 to exit]");

		// Error trap loop that ensures the user enters the parameters of at least one
		// room before continuing to choose their floor
		while (true) {
			// Asks the user for the width
			width = askUser("Width of Room: ", 0, 0);
			if (width == 0 && height != 0) {
				break;
				// If the user enters 0 at the first time, then this error display since now,
				// both width and height are equal to 0
			} else if (width == 0 && height == 0) {
				System.out.println("Please input at least one room!");
			}
			// Asks the user for the height
			height = askUser("Height of Room: ", 0, 0);
			// Add the area of the new parameters to the existing area
			area += calcArea(width, height);
		}
		
		

		// Pass in the area and the price of the user's choice and display all the costs
		// (NOTE: the user choice is retrieved and immediately returned into the
		// parameter of the prices array
		displayPrice(area, prices[getUserChoice(floorings, prices)]);
	}
}
