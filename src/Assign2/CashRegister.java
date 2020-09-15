//
//Amer Alwan - 46266alw
//13 September 2019
//Assignment 2 - Cash Register
//In this assignment, I will be using control structures and field sizing to make a cash register program that accepts the price of 16 items and returns their total cost with tax.


package Assign2;

import java.util.Scanner;

public class CashRegister {
	public static void main(String[] args) {
		
		//Variable Declarations
		double subTotalCost = 0, totalCost = 0, HST = 0, cost = -1;
		char userInput;

		//Declares Scanner for user input
		Scanner input = new Scanner(System.in);

		//'Main Program' Loop
		while (true) {

			//Displays Welcome Message
			System.out.println(
					"Welcome to Zehr's Express Cash Register. You are allowed a maximum of 16 items that can cost a maximum $19.99 each. Input '0' to stop adding items");

			//For loop that asks user to input price of 16 items (and exits when they type 0)
			for (int i = 0; i < 16; i++) {
				
				//'Error Trap Loop 1': Ensures that cost is between 0 and 20
				while (true) {
					System.out.print("Item " + (i + 1) + "$");
					//Gets double input from user for cost
					cost = input.nextDouble();
					input.nextLine();
					
					//Exits 'Error Trap Loop 1' if user enters a value between 0 and 20. Else, it returns an error to the user
					if (cost < 20 && cost >= 0) {
						break;
					} else {
						System.out.println("Please input a value between 0 and 20");
					}
				}
				//Exits the for loop if user enters 0 for the cost
				if (cost == 0) {
					break;
				}
				
				//Adds the inputed cost to the overall cost
				subTotalCost += cost;
			}
			
			//Exits the 'Main Program Loop' if user inputs 0 for cost
			if (cost == 0) {
				break;
			}
			
			//'Error Trap Loop 2' that ensures the user enters a 'y' or 'n'
			while (true) {
				//Asks the user if they want to make another transaction
				System.out.print("Would you like to perform another transaction? [y/n]: ");
				//Gets char input from user/first thing they press
				userInput = input.nextLine().toLowerCase().charAt(0);
				//Exits 'Error Trap Loop 2' if user enters a valid char (y or n)
				if (userInput == 'y' || userInput == 'n') {
					break;
				}
				else {
					System.out.println("Please enter 'y' or 'n'");
				}
			}
			
			//Exits 'Main Program Loop' if user enters 'n' to the previous question
			if (userInput == 'n') {
				break;
			}
		}
		
		
		System.out.println();
		
		//Calculates HST and Total Cost with HST
		HST = subTotalCost * 0.13;
		totalCost = subTotalCost + HST;
		
		//Displays Subtotal Cost, HST, and Total Cost to user
		System.out.printf("Sub-Total Cost %10s %1.2f \n", "$", subTotalCost);
		System.out.printf("HST %21s %1.2f \n", "$", HST);
		System.out.printf("Total Cost %14s %1.2f \n", "$", totalCost);

	}

}
