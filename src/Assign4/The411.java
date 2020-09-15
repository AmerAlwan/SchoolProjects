//
//Amer Alwan - 46266alw
//The 411 - Assignment 4
//27 Spetember 2019
//In this program, I will be asking the user some question, and then compiling his answers and concatenating them into one big paragraph. I will print this paragraph out with a word wrap applied on it so the sentences do not go out of the screen

package Assign4;

import java.util.Scanner;

public class The411 {

	public void WrapText(String paragraph) {
		WrapText(paragraph, paragraph.length() / 4);
	}
	
	/** 
	 * 
	 * @param paragraph
	 * @param charLen
	 */
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

	
	
	/**
	 * Error trap function that ensures the user does not enter a blank answer
	 * @param question		the string question to ask the user
	 * @return				the string answer that the user gave
	 */
	public String AskUser(String question) {
		//Variable declarations and initializations
		Scanner input = new Scanner(System.in);
		String answer;
		//Error trap loop
		while (true) {
			//Asks user question
			System.out.print(question + ": ");
			//Gets answer from user
			answer = input.nextLine();
			//Prints out error if the answer is blank, else it returns th answer
			if (answer.equals("")) {
				System.out.println("Please input an answer!");
			} else {
				return answer;
			}
		}
	}
	
	
	
	
	

	/**
	 * Adds the answers and paragraph together and returns the whole thing as a single string
	 * @param words		the string array containing the arrays
	 * @param par		the string containing the paragraph
	 * @return			the string containing all the answers and the paragraph together
	 */
	public String Concatinate(String[] words, String par) {
		String paragraph = par;
		//For loop that replaces the first instance of "_" with the answer from the array
		for (int i = 0; i < words.length; i++) {
			paragraph = paragraph.replaceFirst("_", words[i]);
		}

		return paragraph;
	}
	
	
	
	

	public static void main(String[] args) {
		// Variable Declarations and Initializations/
		The411 the411 = new The411();

		String[] question = { "What is your name?", "How old are you?", "Where were you born?",
				"Where do you currently live?", "What school do you go to?", "What is your favorite subject?",
				"How many brothers do you have?", "How many sisters do you have?", "What is your favorite food?",
				"What is your favorite color?", "How many friends do you have?", "Where do you work?" };
		String[] answer = new String[question.length];
		String paragraph = "This is _, he is _ years old. He was born in _ and lives in _. He goes to _. At school, he likes the subject _. At home, he has _ brothers and _ sisters."
				+ " He likes to eat _. The color he likes is _. At school, he has _ friends and works at _. ";

		// For loop that prints out the question to the user and then stores the answer
		// in the answer array
	//	for (int i = 0; i < answer.length; i++) {
		//	answer[i] = the411.AskUser(question[i]);
		//}
		
		answer[0] = "Amer Alwan";
		answer[1] = "16";
		answer[2] = "Kuwait";
		answer[3] = "Canada";
		answer[4] = "St. Benedict";
		answer[5] = "Math";
		answer[6] = "3";
		answer[7] = "0";
		answer[8] = "Biryani";
		answer[9] = "Black";
		answer[10] = "3";
		answer[11] = "none";

		// Concatenates the paragraph and answers
		paragraph = the411.Concatinate(answer, paragraph);

		System.out.println("");

		// Prints out the paragraph with a wrap text
			the411.WrapText(paragraph, 40);
	}
}
