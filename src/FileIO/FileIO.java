//
//Amer Alwan - 46266alw
//FileIO - Assignment 6
//11 October 2019
//In this assignment, we will experimenting with writing and reading to a file using java IO. We will also be combining previously made methods, like the word wrap, into the program


package FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class FileIO {
	
	public void WrapText(String paragraph, int charLen) {
		int beginIndex = 0;
		int endIndex = charLen;
		int parLen = (int) paragraph.length() / charLen;

		for (int i = 0; i <= parLen; i++) {
			endIndex = paragraph.lastIndexOf(" ", beginIndex + charLen) + 1;
			if (endIndex == beginIndex) {
				endIndex = paragraph.indexOf(" ", beginIndex + charLen) + 1; 
			}
			System.out.println(paragraph.substring(beginIndex, endIndex));
			beginIndex = endIndex;
			if (endIndex == paragraph.length()) {
				break;
			}
		}
		System.out.println(paragraph.substring(endIndex, paragraph.length()));
	}
	
	public static void writeToFile(String fileName) throws IOException {
		Scanner input = new Scanner(System.in);
		PrintWriter file = new PrintWriter(new FileWriter(fileName));
		System.out.println("Input what you want to write below:");
		file.println(input.nextLine());
		file.close();
	}
	
	public static void readFromFile(String fileName, int wrapLength) throws IOException {
	//	BufferedReader file = new BufferedReader(new FileWriter(fileName));
		String text;
		while(true) {
		//	text += file.readLine();
			
		}
		
		
		//file.close();
	}
	
	public static void addLines(int amtOfLines) {
		for (int i = 0; i < amtOfLines; i++) {
			System.out.println("");
		}
	}
	
	public static void displayInstructions() {
		
	}
	 
	
	public static void main(String[] args) throws IOException {
		int userChoice;
		String fileName;
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("Choose Option: ");
		System.out.println("1 - Write to File");
		System.out.println("2 - Read from File");
		System.out.println("3 - Exit Program");
		
		while (true) {
			System.out.print("Choice: ");
			userChoice = input.nextInt();
			input.nextLine();
			if (userChoice < 1 || userChoice > 4) {
				System.out.println("Value must be between 1 and 4");
			} else {
				break;
			}
		}
	
		if (userChoice == 1) {
			System.out.println("Write to File");
			System.out.print("Input File Name: ");
			fileName = input.nextLine();
			writeToFile(fileName);
		} else if (userChoice == 2 ) {
			while (true) {
				System.out.println("Read from File");
				System.out.print("Input File Name: ");
				fileName = input.nextLine();
				File tmpFile = new File(fileName);
				if (tmpFile.exists()) {
					while(true) {
						System.out.print("Enter Word Wrap Length [20-79]: ");
						userChoice = input.nextInt();
						if (userChoice >= 20 && userChoice <= 79) {
							break;
						} else {
							System.out.println("The word wrap length has to be between 20 and 79");
						}
					}
					break;
				} else {
					System.out.println("This file was not found");
				}
			}
			readFromFile(fileName, userChoice);
		} else if (userChoice == 4) {
			displayInstructions();
		} else {
			
		}
		
		
		
		
		
	}

}
