package BusinessPOS;

import java.awt.*;
import java.awt.event.*;
import java.io.*;


import javax.swing.*;

public class BusinessPOS {

	//Global variables
	public static boolean removeMode = false;
	public static boolean descMode = false;

	/**
	 * Wraps an inputted one line paragraph into a multiple line paragraph with
	 * lines of length charLen
	 * 
	 * @param paragraph the string paragraph to wrap
	 * @param charLen   the int length of each line in the paragraph
	 * @return the string containing '/n' where the paragraph is to go to the next
	 *         line
	 */
	public static String wrapText(String paragraph, int charLen) {
		// String declarations and initializations
		String tempPar = "";

		int beginIndex = 0;
		int endIndex = charLen;
		int parLen = (int) paragraph.length() / charLen;

		// For loop that goes through the paragraph and wraps it
		for (int i = 0; i <= parLen; i++) {
			// Check where there is is a space from begin Index + charLen to the end of the
			// paragraph
			endIndex = paragraph.lastIndexOf(" ", beginIndex + charLen) + 1;

			if (endIndex == beginIndex) {
				endIndex = paragraph.indexOf(" ", beginIndex + charLen) + 1;
			} // end if

			// Add the new line to the tempPar, with a "\n" to indicate that it is a new
			// line
			tempPar += paragraph.substring(beginIndex, endIndex) + "\n";
			beginIndex = endIndex;

			// if the index has reached the end of the paragraph
			if (endIndex == paragraph.length()) {
				break;
			} // end if
		} // end for

		// add the last line in the paragraph to tempPar
		tempPar += paragraph.substring(endIndex, paragraph.length()) + "\n";

		return tempPar;

	} // wrapText method

	/**
	 * Loads csv lines from a file and returns them in a string array
	 * @param fileName the name of the file to laod items from
	 * @return the string array containing the csv items from the file
	 * @throws IOException
	 */
	public static String[] getItemsFromFile(String fileName) throws IOException {
		//variable and object declarations and initializations/instantiations 
		int numOfItems;
		String[] data;
		BufferedReader input = new BufferedReader(
				new FileReader("C:/Users/amera/workspace/Unit 1 Code/src/BusinessPOS/files/" + fileName));

		//Gets how many items are in the file
		numOfItems = Integer.valueOf(input.readLine());

		data = new String[numOfItems];

		//for loop that stores each csv line in the data array
		for (int i = 0; i < numOfItems; i++) {
			data[i] = input.readLine();
		} //end for

		return data;
	} //getItemsFromFile

	/**
	 * Checks whether an inputted item object array contains an inputted item object
	 * @param array the Item object array to be checked of containing the item object
	 * @param item the Item object to be checked of being in the item object array
	 * @return boolean of whether the item array contains the item
	 */
	public static boolean itemContains(Item[] array, Item item) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == item) {
				return true;
			} //end if
		} //end for
		return false;
	} //itemContains

	/**
	 * Checks the index of the inputted item object in the inputted item object array
	 * @param array the item object array to be checked for the index of the item object
	 * @param item the Item object to be checked for its index in the item object array
	 * @return the index of the Item object in the item object array
	 */
	public static int getItemContains(Item[] array, Item item) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == item) {
				return i;
			} //end if
		} //end for
		//If the item object is not in the array, then return -1
		return -1;
	} //getItemContains

	/**
	 * Returns the index of the first null value in an inputted item object array
	 * @param array the item object array to be checked for the first value of null
	 * @return the index of the first value of null in the item object array
	 */
	public static int getNullIndex(Item[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				return i;
			} //end if
		} //end for
		return -1;
	} //getNullIndex

	public static void main(String[] args) throws IOException {
		// Variable and object declarations and initializations/instantiations
		String[] data = getItemsFromFile("data.csv");
		JFrame frame = new JFrame("Amer's Phone Store");
		JPanel[] panels = new JPanel[6];
		Item[] item = new Item[data.length], selectedItems = new Item[data.length + 1];
		JButton[] buttons = new JButton[data.length + 5];
		JLabel[] receiptLabel = new JLabel[4];
		JTextArea[] receiptTotals = new JTextArea[4];
		JTextArea receipt = new JTextArea(15, 62);
		JScrollPane scrollPane = new JScrollPane(receipt);
		String[] controlButText = { "Show Desc.", "Hide Desc.", "Remove", "Clear", "TOTAL" },
				receiptText = { "Cost", "Tax", "Total", "# Items" },
				receiptTotalsText = { "0.00", "0.00", "0.00", "0.00" };
		int index = 0;

		removeMode = false;

		// Receipt adjustments
		receipt.setEditable(false);
		receipt.setFont(new Font("Monospaced", Font.PLAIN, 20));

		// panel instantiations
		panels[0] = new JPanel(new BorderLayout());
		panels[1] = new JPanel(new GridLayout(11, 6, 35, 10));
		panels[2] = new JPanel(new BorderLayout());
		panels[3] = new JPanel(new GridLayout(3, 2, 10, 10));
		panels[4] = new JPanel(new GridLayout(4, 1, 10, 10));
		panels[5] = new JPanel(new GridLayout(4, 1, 10, 10));

		panels[2].add(scrollPane, BorderLayout.NORTH);

		// for loop that instantiates the Text objects and textArea objects for the
		// receipt totals and then adds them their corresponding panels
		for (int i = 0; i < receiptLabel.length; i++) {
			receiptLabel[i] = new JLabel(receiptText[i]);
			receiptLabel[i].setFont(new Font("timesnewroman", Font.BOLD, 20));
			panels[4].add(receiptLabel[i]);
			receiptTotals[i] = new JTextArea(10, 25);
			receiptTotals[i].setFont(new Font("MonoSpaced", Font.PLAIN, 30));
			receiptTotals[i].setEditable(false);
			receiptTotals[i].setText(receiptTotalsText[i]);
			panels[5].add(receiptTotals[i]);
		} // end for

		panels[2].add(panels[4], BorderLayout.CENTER);
		panels[2].add(panels[5], BorderLayout.EAST);

		// For loop that instantiates the Item and buttons objects with their
		// corresponding data
		for (int i = 0; i < data.length; i++) {
			item[i] = new Item(data[i]);
			// The string for color but centered inside the button
			String colorCentered = String.format(
					"%" + String.valueOf(((item[i].getName().length() / 2) - (item[i].getColor().length() / 2))) + "s",
					" ") + item[i].getColor();
			// The string for price but centered inside the button
			String priceCentered = String
					.format("%" + String.valueOf(((item[i].getName().length() / 2)
							- ((String.valueOf(item[i].getPrice()).length() + 1) / 2))) + "s", " ")
					+ "$" + item[i].getPrice();
			// Instantiating the buttons with html so I can add multiple spaces and lines
			// (which will allow the color and price to be centered)
			buttons[i] = new JButton(
					"<html><pre>" + item[i].getName() + "\n" + colorCentered + "\n" + priceCentered + "</pre></html>");
			buttons[i].setPreferredSize(new Dimension(250, 100));
			buttons[i].setBackground(Color.WHITE);
			panels[1].add(buttons[i]);
		} // end for

		// for loop that instantiates the last 5 buttons (Show/Hide Desc., remove,
		// clear, and total)
		index = 0;
		for (int z = data.length; z < buttons.length; z++) {
			buttons[z] = new JButton(controlButText[index]);
			buttons[z].setBackground(Color.WHITE);
			panels[3].add(buttons[z]);
			index += 1;
		} // end for

		// for loop that goes through every button and adds an action listener to it
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(new ActionListener() {

				/**
				 * Detects if a button was clicked
				 */
				@Override
				public void actionPerformed(ActionEvent ae) {
					JButton pressedBut = (JButton) ae.getSource();

					// for loop that checks if one of the control buttons were clicked (Show/hide
					// desc., remove, clear, total)
					for (int i = data.length; i < buttons.length; i++) {
						// Checks if the button clicked was the "remove" button
						if (pressedBut == buttons[data.length + 2]) {
							if (!removeMode) {
								removeMode = true;
								// Set the remove button's color to light gray
								buttons[data.length + 2].setBackground(Color.LIGHT_GRAY);
								// for loop that goes through the selected items and makes them light gray
								for (int z = 0; z < getNullIndex(selectedItems); z++) {
									buttons[getItemContains(item, selectedItems[z])].setBackground(Color.LIGHT_GRAY);
								} // end for
							} else if (removeMode) {
								removeMode = false;
								// Change the remove button's color to white
								buttons[data.length + 2].setBackground(Color.WHITE);
								// For loop that changes every button's color to white
								for (int z = 0; z < data.length; z++) {
									buttons[z].setBackground(Color.WHITE);
								}
							} // end if

						} // end if

						// Checks if the clear button was clicked
						if (pressedBut == buttons[data.length + 3]) {
							// For loop that sets every value of selectedItems to null, hence meaning
							// nothing was selected
							for (int z = 0; z < selectedItems.length; z++) {
								selectedItems[z] = null;
							} // end for
						} // end if

						// Checks if the Total button was clicked
						if (pressedBut == buttons[data.length + 4]) {
							double totalCost = 0, totalTax = 0;
							int totalNum = 0;
							// for loop that goes through all the selected items and makes the total
							// calculations based on their prices and how much of them were bought
							for (int z = 0; z < getNullIndex(selectedItems); z++) {
								double priceItem = selectedItems[z].getPrice() * selectedItems[z].getNumSelected();
								totalCost += priceItem;
								totalTax += priceItem * 0.13;
								totalNum += selectedItems[z].getNumSelected();
							} // end for
								// Sets the receipt text according to the new totals
							receiptTotals[0].setText(String.format("$%1.2f", totalCost));
							receiptTotals[1].setText(String.format("$%1.2f", totalTax));
							receiptTotals[2].setText(String.format("$%1.2f", totalCost + totalTax));
							receiptTotals[3].setText(String.valueOf(totalNum));
						} // end if

						// Checks if the show desc. button was clicked
						if (pressedBut == buttons[data.length]) {
							descMode = true;
						} // end if

						// Checks if the hide desc. button was clicked
						if (pressedBut == buttons[data.length + 1]) {
							descMode = false;
						} // end if

					} // end for

					// for loop that checks which non-control button was clicked (all except
					// show/hide desc., remove, clear, and total)
					for (int i = 0; i < data.length; i++) {
						// Check which button was clicked
						if (pressedBut == buttons[i]) {
							// If removeMode is true and descMode is false, then the user is removing an
							// item
							if (removeMode && descMode == false) {
								// If the item number is above 0, then keep subtracting the number selected of
								// this item
								if (item[i].getNumSelected() > 0) {
									item[i].setNumSelected(item[i].getNumSelected() - 1);
								} // end if
									// If the item number is under 0, remove the item from the receipt
								if (item[i].getNumSelected() <= 0) {
									int index = 0;
									// Copy of all the selected items
									Item[] selectedItemsCopy = selectedItems;
									// The item to be removed
									Item compareItem = item[i];
									// For loop that goes through the copy item array and sets its object values to
									// the original selected item array except if that object is the copareItem,
									// which is to be removed from the original selected items array
									for (int z = 0; z < selectedItemsCopy.length; z++) {
										if (selectedItemsCopy[z] != compareItem) {
											selectedItems[index] = selectedItemsCopy[z];
											index += 1;
										} // end for
									} // end if
								} // end if
									// If remove mode is false and descMod is false, then the user is adding an item
							} else if (!descMode) {
								// increases the number of that item selected by one
								item[i].setNumSelected(item[i].getNumSelected() + 1);
								// If the selectedItems does not already contain the selected item, then the
								// user is adding a completely new item instead of buying an extra item for an
								// item already on the receipt
								if (!itemContains(selectedItems, item[i])) {
									// Check where the selectedItems has a null slot, which will store the newly
									// purchased item
									selectedItems[getNullIndex(selectedItems)] = item[i];
								} // end if
								// If removeMode is false and description mode is true
							} else if (descMode) {
								// Set the textArea's text to the selected item's description
								receipt.setText(wrapText(item[i].getDescription(), 60));
							} // end if
						} // end if
					} // end for

					// If description mode is false
					if (!descMode) {
						// Add the column text
						receipt.setText(String.format(" Q.  %-30s %-16s Price", "Name", "Color"));
						// for loop that goes through every non-null value of the selectedItems array
						// and displays the selected items on the receipt
						for (int z = 0; z < getNullIndex(selectedItems); z++) {
							receipt.setText(receipt.getText() + "\n"
									+ String.format("(x" + selectedItems[z].getNumSelected() + ") %-30s %-15s  $%1.2f",
											selectedItems[z].getName(), selectedItems[z].getColor(),
											selectedItems[z].getPrice() * selectedItems[z].getNumSelected()));
						} //end for
					} // end if
				} //actionPerformed

			}); //addActionListener
		} //end for

		//setup of panels and frame
		
		panels[2].add(panels[3], BorderLayout.WEST);

		panels[0].add(panels[1], BorderLayout.WEST);
		panels[0].add(panels[2], BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panels[0]);
		frame.setBounds(100, 100, 1600, 700);
		frame.setVisible(true);

		//Main class loop
		while (true) {
			//if there are no items in the receipt, then display the message
			if (getNullIndex(selectedItems) == 0) {
				receipt.setText("Welcome to Amer's Phone Store!");
			} //end if
		} //end while

	} //Main Method

} //BussinessPOS class
