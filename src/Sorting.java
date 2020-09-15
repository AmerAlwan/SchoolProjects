/**
 * Amer Alwan - 46266alw
 * Assignment 8: Assorted Sorting
 * 8 November 2019
 * In this assignment, we will be exploring the difference in speed between 4 different algorithms by "borrowing" their code and then using the algorithm to sort different sets of data with a different amount of elements. The times taken to sort each will be recorded and compared to assess which algorithm is the quickest and most efficient
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//The SMethods class is a class of methods I made, consisting mostly of error
// traps and user input methods, that I got bored of constantly rewriting. Tell
// me if you need the code for the class (it will be in eclipse on my account)
import SimpleMethods.*;

public class Sorting {

	// Global variables
	public static SMethods method = new SMethods();
	public static double timer = 0;
	// These variables are needed for the merge algorithm and need to be declared
	// here.
	public static int[] mergeData;
	public static int[] tempMergeData;

	/**
	 * Creates a timer thread that adds 1 to the variable timer every 1 millisecond
	 */
	public static void startTimer() {
		// Declares and initializes the thread
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// Sleeps the thread for 1 millisecond and then adds 1 to timer
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					timer += 1;
				}
			}
		});

		thread.start();
	}

	/**
	 * Displays the time in different conversions
	 * 
	 * @param sortingName the string name of the algorithm that this time belongs to
	 */
	public static void displayTime(String sortingName) {
		System.out.println();
		System.out.println(sortingName);
		System.out.println("Time (Milliseconds): " + timer);
		System.out.println("Time (Centiseconds): " + (timer / 10));
		System.out.println("Time (Deciseconds): " + (timer / 100));
		System.out.println("Time (Seconds): " + (timer / 1000));
		// Resets the value of time to restart counting (This was used with option 5)
		timer = 0;
		// System.out.println();
	}

	/**
	 * The insertion sort works by picking one element in the array and comparing it
	 * to all others
	 * 
	 * @param data the int array containing the data to be sorted
	 */
	public static void insertionSort(int[] data) {
		int current = 0, j = 0;

		for (int i = 1; i < data.length; i++) {
			current = data[i];
			j = i - 1;

			// Move elements of data[0..j--] that are greater than key to one position ahead
			// of their current position
			while (j >= 0 && current < data[j]) {
				data[j + 1] = data[j];
				j--;
			}

			data[j + 1] = current;
		}
		displayTime("Insertion Sort");
	}

	/**
	 * The selection sorting algorithm which splits the data array into two and goes
	 * through the right array, comparing its elements with the left array and then
	 * swapping them if the element in the right array is smaller Taken from
	 * 
	 * @param data
	 */
	public static void selectionSort(int[] data) {
		int min = 0, minID = 0, temp = 0;
		// One by one, move the boundary of unsorted subarray
		for (int i = 0; i < data.length; i++) {
			min = data[i];
			// Find the minimum element in unsorted array
			minID = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < min) {
					min = data[j];
					minID = j;
				}
			}
			// Swap the found minimum element with the first element
			temp = data[i];
			data[i] = min;
			data[minID] = temp;
		}
		displayTime("Selection Sort");
	}

	/**
	 * The bubble sort algorithm goes through the array and compares adjacent
	 * element pairs.
	 * 
	 * @param data the int array containing the data to be sorted
	 */
	public static void bubbleSort(int[] data) {
		boolean sorted = false;
		int temp;
		// Keeps going until no two elements were swapped by inner loop
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < data.length - 1; i++) {
				if (data[i] > data[i + 1]) {
					// swap data[i] and data[i + 1]
					temp = data[i];
					data[i] = data[i + 1];
					data[i + 1] = temp;
					sorted = false;
				}
			}
		}

		displayTime("Bubble Sort");
	}

	/**
	 * Initializes the arrays needed by the merge algorithm and calls the merge
	 * algorithm Taken from
	 * https://www.java2novice.com/java-sorting-algorithms/merge-sort/
	 * 
	 * @param data the int array containing the data
	 */
	public static void mergeSort(int[] data) {
		mergeData = data;
		tempMergeData = new int[mergeData.length];
		doMergeSort(0, mergeData.length - 1);
		displayTime("Merge Sort");
	}

	/**
	 * Splits the data array into two parts and then sorts each side, and merges
	 * both parts at the end
	 * 
	 * @param lowerIndex
	 * @param higherIndex
	 */
	public static void doMergeSort(int lowerIndex, int higherIndex) {
		if (lowerIndex < higherIndex) {
			// Gets the index of the middle of the array
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			// Sorts the left side of the array
			doMergeSort(lowerIndex, middle);
			// Sorts the right side of the array
			doMergeSort(middle + 1, higherIndex);
			// Merges both sides
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	/**
	 * Part of the merge algorithm. This method compares the indexes its been given
	 * of the data array and compares them, then merges them
	 * 
	 * @param lowerIndex
	 * @param middle
	 * @param higherIndex
	 */
	public static void mergeParts(int lowerIndex, int middle, int higherIndex) {
		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempMergeData[i] = mergeData[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;

		while (i <= middle & j <= higherIndex) {
			if (tempMergeData[i] <= tempMergeData[j]) {
				mergeData[k] = tempMergeData[i];
				i++;
			} else {
				mergeData[k] = tempMergeData[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			mergeData[k] = tempMergeData[i];
			k++;
			i++;
		}

	}

	public static void main(String[] args) throws IOException {
		// Variable Declarations and Initializations
		String fileName;
		PrintWriter output;
		BufferedReader input;
		int numOfData, userChoice;
		int[] data = new int[0];

		System.out.println("Welcome to the algorithm sorting program!");
		System.out.println();

		// Asks the user for the file name and returns the input to fileName
		fileName = method.getString("Enter file name [Empty to generate data]: ");

		// If the file name is empty, then go through process to generate data
		if (fileName.equals("")) {
			// Asks the user for how much data they want to generate and return the value to
			// numOfData
			numOfData = method.getInt("How many data do you want to generate?: ");
			// Initialize the data array with the size of numOfData
			data = new int[numOfData];

			// For loop that generates the data within the range of how much data is to be
			// generated
			for (int i = 0; i < numOfData; i++) {
				data[i] = (int) (Math.random() * numOfData);
			}

			// Initialize the PrintWriter and create a new file which has a name equivocal
			// to how much data is generated
			output = new PrintWriter(new FileWriter(String.valueOf(numOfData) + ".txt."));

			// Print on the first line of the data file how much data is actually in the
			// file
			output.println(String.valueOf(numOfData));

			// Print the randomly generated data into the file
			for (int i = 0; i < numOfData; i++) {
				output.println(String.valueOf(data[i]));
			}

			System.out.println("Data Generated! Data saved to file called " + numOfData + ".txt");
			// Close file
			output.close();

			// If the user types something into file name, then open the file and load its
			// data
		} else {
			// If the file does not contain a period (meaning the user did not put an
			// extension), then add .txt to the end of the file
			if (!fileName.contains(".")) {
				fileName += ".txt";
			}

			// Initialize the BuferredReader and open the file that the user inputted
			input = new BufferedReader(new FileReader(fileName));
			// Get how much does data is in the file, which is the first line of the file
			numOfData = Integer.valueOf(input.readLine());
			data = new int[numOfData];

			// Gets each line of the file and stores it into the data array
			for (int i = 0; i < data.length; i++) {
				data[i] = Integer.valueOf(input.readLine());
			}

			System.out.println("Data Loaded!");
			input.close();
		}

		System.out.println("");
		System.out.println("\nSelect Sorting Method\n");
		System.out.println("Method 1: Insertion sort");
		System.out.println("Method 2: Selection sort");
		System.out.println("Method 3: Bubble sort");
		System.out.println("Method 4: Merge sort");
		System.out.println("Method 5 - All (To compare times)");
		System.out.println("6 - Exit Porgram");

		// Asks the user for a number between 1 and 6 and returns that value
		userChoice = method.getIntBetween("\nChoice: ", 1, 6);
		// Starts the timer
		startTimer();

		if (userChoice == 1) {
			insertionSort(data);
		}

		if (userChoice == 2) {
			selectionSort(data);
		}

		if (userChoice == 3) {
			bubbleSort(data);

		}

		if (userChoice == 4) {
			mergeSort(data);
		}

		// Does not work. Timing for bubbleSort and mergeSort is wrong
		if (userChoice == 5) {
			insertionSort(data);
			selectionSort(data);
			bubbleSort(data);
			mergeSort(data);
		}

		if (userChoice == 6) {
			System.exit(0);
		}

	}
}
