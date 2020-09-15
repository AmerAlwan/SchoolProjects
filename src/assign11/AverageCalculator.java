//
//Amer Alwan - 46266alw
//Average Calculator: Assign 1-1
//11 September 2019
//In this assignment, I will be creating an average calculator that takes in the marks of four courses and calculates their average to two decimal places. Learning concepts in this assignment include user input using scanners and field sizing


package assign11;
import java.util.Scanner;

public class AverageCalculator {
	public static void main(String[] args){
		
		//Variable Declarations
		String name;
		int userInput, marks;
		float average = 0;
		Scanner input = new Scanner(System.in);
		
		//Main Program Loop
		while (true) {
			average = 0;
			
			//Asking and getting name of user
			System.out.print("What is your name? ");
			name = input.nextLine();
			
			//For loop that gets mark for the 4 courses
			for (int i = 0; i < 4; i++) {
				
				//Loop that exits when the user enters a valid value for the mark
				do {
					System.out.print("What was your average for course " + (i + 1) + ": ");
					marks = input.nextInt();
					
					//Prints invalid error if mark is below 1 or above 100
					if (marks < 1 || marks > 100) {
						System.out.println("That mark is invalid");
					} 
				} while (marks < 1 || marks > 100);
				//Adds the mark to the average
				average += marks;
				}
			
			//Calculates the average
			average /= 4;
			
			//Tells the user their average and field sizes it to reduce the decimal points to 2
			System.out.printf("%s%s%3.2f", name, ", your average is ", average);
			
			
			System.out.print(". Your mark is ");
			
			//Tells the user a comment based on their mark
			if (average <= 50) {
				System.out.print("dissapointing.");
			} else if (average <= 60) {
				System.out.print("needing of more work.");
			} else if (average <= 70) {
				System.out.print("close enough...");
			} else if (average <= 80) {
				System.out.print("good enough.");
			} else if (average <= 90) {
				System.out.print("very good!");
			} else if (average <= 100) {
				System.out.print("excellent!");
			}
			
			System.out.println();
			
			//Asks the user if they want to restart
			System.out.print("Would you like to restart? [1-y/2-n]: ");
			userInput = input.nextInt();
			input.nextLine();
			if (userInput == 2) {
				//Exits Main Program Loop
				break;	
			}
		}
	}
}


