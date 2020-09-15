/**
 * @author Amer Alwan - 46266alw
 * Assignment 10 - MyStore
 * @date 20 December 2019
 * In this assignment, I will be creating my own store that sells electronics. 
 * This assignment utilizes all knowledge of the year, including methods, arrays, objects, inheritance, and file I/O, 
 * to create a fully functioning store that allows the user to purchase items 
 */

package MyStore;

import java.io.*;

import EasyGraphics.*;

public class MyStore {
	// Global Variables and Objects - Need to be here just because they are so
	// widely used
	static int width = 1400, height = 900;
	static Frame frame = new Frame("Phonerama", 100, 100, width, height, false);
	static Panel panel = new Panel(frame);

	/**
	 * Loads data from a file and returns it stored in an array
	 * 
	 * @param fileName the string name of the file
	 * @return the string array containing the data in the file
	 * @throws IOException
	 */
	public static String[] loadDataFromFile(String fileName) throws IOException {
		// Variable/objects declarations and initializations/instantiations
		BufferedReader input = new BufferedReader(
				new FileReader("C:/Users/amera/workspace/Unit 1 Code/src/MyStore/files/" + fileName));
		String[] data = new String[Integer.valueOf(input.readLine())];

		// For loop that reads each line in the file and stores it into the
		// corresponding array index
		for (int i = 0; i < data.length; i++) {
			data[i] = input.readLine();
		} // end for

		// closes the file
		input.close();

		return data;
	} // end loadDataFromFile method

	/**
	 * An overloaded method that writes one string to a file
	 * 
	 * @param data     the string containing the data to be written to the file
	 * @param fileName the string name of the file
	 * @param append   the boolean that dictates whether the data in the file will
	 *                 be overwritten
	 */
	public static void writeDataToFile(String data, String fileName, boolean append) {
		writeDataToFile(new String[] { data }, fileName, append);
	} // writeDataToFile method

	/**
	 * A method that writes an array of strings into a file
	 * 
	 * @param data     the string array containing all the data to be written in the
	 *                 file
	 * @param fileName the string name of the file
	 * @param append   the boolean that dictates whether the data in the file will
	 *                 be overwritten (true - add on to the file. false - overwrite
	 *                 everything in the file)
	 */
	public static void writeDataToFile(String[] data, String fileName, boolean append) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(
					new FileWriter("C:/Users/amera/workspace/Unit 1 Code/src/MyStore/files/" + fileName, append));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// The first line in the file is how much data is in it
		output.println(data.length);

		// For loop that goes through each element in the data array and stores it on a
		// new line in the file
		for (int i = 0; i < data.length; i++) {
			output.println(data[i]);
		} // end for loop
			// closes the file
		output.close();
	} // writeDataToFile method

	/**
	 * Saves the products in a passed in MyProduct array to the data csv file
	 * 
	 * @param products the MyProduct object array containing all the product objects
	 */
	public static void saveProducts(MyProduct[] products) {
		String[] data = new String[products.length];
		// For loop that stores each product's csv format into each element in the data
		// string array
		for (int i = 0; i < data.length; i++) {
			data[i] = products[i].toCSV();
		} // end for

		// Writes the elements in the data string array into the data.csv file
		writeDataToFile(data, "data.csv", false);
	} // saveProducts method

	/**
	 * Saves the passed in sale value into the sales-daily file
	 * 
	 * @param totalCost the double value of the sale/total cost
	 * @throws IOException
	 */
	public static void saveSales(double totalCost) throws IOException {
		// Loads all the data from the sales-daily file
		String[] data = loadDataFromFile("sales-daily.txt");
		// Creates a tempData array, which will hold all the values from the data array
		// + the totalCost
		String[] tempData = new String[data.length + 1];

		// For loop that goes through each element in the data array (starting from
		// element 2) and stores it in the corresponding spot in the tempData array
		for (int i = 0; i < data.length; i++) {
			tempData[i] = data[i];
		} // end for

		// The last spot in the tempData array is the totalCost
		tempData[tempData.length - 1] = String.valueOf(totalCost);

		// Writes back the data to the sales-daily file
		writeDataToFile(tempData, "sales-daily.txt", false);
	} // saveSales method

	/**
	 * Wraps an inputted one line paragraph into a multiple line paragraph with
	 * liens of length charLen
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
	 * Gets the number of purchases that the user has done. Basically, the user
	 * starts off with 20 null purchases, whenever the user purchases a product,
	 * that object is stored in the purchasedProducts array. Therefore, all the
	 * elements which are not null represent how much products out of 20 that the
	 * user has purchased
	 * 
	 * @param purchasedProducts the MyProduct object arrays containing the purchased
	 *                          products and the left over null values
	 * @return
	 */
	public static int getNumOfPurchases(MyProduct[] purchasedProducts) {
		// For loop which checks which index in the purchasedProducts array is null,
		// which represents the amount of purchases that the user has made
		for (int i = 0; i < purchasedProducts.length; i++) {
			if (purchasedProducts[i] == null) {
				return i;
			} // end if
		} // end for
			// If none of the values are null, then the array is full, and the user has
			// reached the maximum 20 items buying limit
		return purchasedProducts.length;
	} // getNumOfPurchases method

	/**
	 * The checkout page displays all the products the user purchased and the final
	 * cost of what they will be paying
	 * 
	 * @param products          The MyProduct object array containing all the
	 *                          products
	 * @param purchasedProducts the MyProduct object array containing all the
	 *                          products that the user purchased
	 * @throws IOException
	 */
	public static void displayCheckoutPage(MyProduct[] products, MyProduct[] purchasedProducts) throws IOException {
		// Variable and Object initializations and Instantiations
		Text titleText = new Text("  <b> Product Name {49} Cost {5} Quant. Total {5} </b>", 10, 5, 30), totalText;
		Text[] purchasedText;
		Button backBut = new Button("MENU", 20, height - 120, 200, 75, "white");
		Button exitBut = new Button("EXIT", width - 220, height - 120, 200, 75, "red");
		String name, color;
		int quantityBought, numOfPurchases = getNumOfPurchases(purchasedProducts);
		double indivCost, totalCost, subTotal = 0, finalTotal, HST = 0.13;

		panel.clear();

		purchasedText = new Text[numOfPurchases];

		// For loop that does all the calculates for each purchased product and then
		// instantiates that product's corresponding purchasedText object
		for (int i = 0; i < numOfPurchases; i++) {
			name = purchasedProducts[i].getName();
			color = purchasedProducts[i].getColor();
			quantityBought = purchasedProducts[i].getQuantityBought();
			// If the user had purchased more than 100 of that product, then the individual
			// cost is the wholesale price
			if (purchasedProducts[i].getQuantityBought() >= 100) {
				indivCost = purchasedProducts[i].getWholesale();
				// If the user had purchased less than 100 of that product, then the individual
				// cost is the normal price
			} else {
				indivCost = purchasedProducts[i].getPrice();
			} // end if
			totalCost = purchasedProducts[i].getCostOfBought();
			// Calls the saveSales method, which saves the totalCost, the sum of how much
			// the company made from that product, into the sales-daily file
			saveSales(totalCost);
			// Resets the quantitiesBought and costOfBought for that product
			purchasedProducts[i].reset();
			subTotal += totalCost;
			// Instantiations the purchased text object for that product
			purchasedText[i] = new Text((i + 1) + "  " + name + " | " + color + "{50}$" + indivCost + "{10}"
					+ quantityBought + "{5}$" + totalCost + "{10}", 10, (i + 1) * 30, 30);
			panel.add(purchasedText[i]);
		} // for loop
		finalTotal = subTotal + (subTotal * HST);
		// Instantiates the totalText text object, which contains all the total cost
		// information
		totalText = new Text(
				"<b>================================================================== {200} \n Sub-total {62} $"
						+ subTotal + "{100} \n HST {62} $" + (HST * subTotal) + "{100} \n Final Total {62} $" + finalTotal
						+ "{100} </b>",
				10, (numOfPurchases + 1) * 30, 30);

		panel.add(titleText);
		panel.add(totalText);
		panel.add(backBut);
		panel.add(exitBut);

		// update loop
		while (true) {
			// Exit program if exit button is pressed
			if (exitBut.isPressed()) {
				System.exit(0);
			} // end if

			// Break out of loop if backBut is pressed (which returns to the purchases page
			// and then the menu)
			if (backBut.isPressed()) {
				break;
			} // end if
			frame.update();
		} // update loop
	} // displayCheckoutPage method

	/**
	 * Displays the session sales page, which shows all the transactions for the
	 * day, the total made for the day, and the grad total made since day 1
	 * 
	 * @throws IOException
	 */
	public static void displaySessionSalesPage() throws IOException {
		// Variable/object declarations and initializations/instantiations
		Button rollOverBut = new Button("Roll Over", 0, height - 130, width, 100, "orange");
		Button menuBut = new Button("MENU", width - 220, 10, 200, 75, "white");
		Text[] salesText;
		Text totalText;
		// Loads the information in the sales daily file into the data string array
		String[] data = loadDataFromFile("sales-daily.txt");
		double[] sales;
		double total = 0, grandTotal;
		int index = 0, day;

		// Since the first two lines in the data file are the day number and grand
		// total, it is only logical that the rest of the lines, which contain each
		// different transaction, would represent the amount of sales that day
		sales = new double[data.length - 2];
		// Gets the day number
		day = Integer.valueOf(data[0]);
		// Gets the grand total, which is how much in sales was made since day 1
		grandTotal = Double.valueOf(data[1]);
		salesText = new Text[sales.length];

		panel.clear();

		// For loop that goes through the data array and stores the double value of that
		// sale into the sales double array. Then it adds that value of the total as
		// well as instantiates the text object that corresponds to that sales amount
		for (int i = 2; i < data.length; i++) {
			sales[index] = Double.valueOf(data[i]);
			total += sales[index];
			salesText[index] = new Text((index + 1) + " - $" + sales[index], 10, index * 40, 40);
			panel.add(salesText[index]);
			index += 1;
		} // end for

		// Adds the total sales of the day to the grand total
		grandTotal += total;

		// Instantiates the total text, which contains the total and grandTotal variable
		totalText = new Text("Total: $" + total + "\nGrand Total: $" + grandTotal + "\n<b>Day: " + day + "</b>", 10,
				sales.length * 40, 40);

		panel.add(rollOverBut);
		panel.add(menuBut);
		panel.add(totalText);

		// update loop
		while (true) {

			// If the roll over button is pressed
			if (rollOverBut.isPressed()) {
				// Adds one to the day
				day += 1;
				// Rewrites the sales-daily file with the day value and the grandTotal value
				writeDataToFile(new String[] { String.valueOf(day), String.valueOf(grandTotal) }, "sales-daily.txt",
						false);
				// Exits the sessionSales Loop
				break;
			} // end if

			// If the menu button is pressed, exit the sessionSales loop
			if (menuBut.isPressed()) {
				break;
			} // end if

			frame.update();
		} // end update loop

	} // end displaySessionsSalesPage method

	/**
	 * Method that allows the user to edit the variables of the inputted MyProduct
	 * object
	 * 
	 * @param product the MyProduct object that will have its variables edited
	 */
	public static void editProduct(MyProduct product) {
		// Variable/Object declarations and Instantiations
		TextField[] userInput = new TextField[8];
		Text[] userInputTitle = new Text[8];
		Button confirmBut = new Button("CONFIRM CHANGES", 0, height - 130, width, 75, "green");
		int index = 0;

		panel.clear();

		// For loops that organizes the title texts and their corresponding textfields
		// in the textfield array into 2 columns and 4 rows
		// Rows for loop
		for (int y = 0; y < 4; y++) {
			// Columns for loop
			for (int x = 0; x < 2; x++) {
				userInput[index] = new TextField("", (x * 700) + 50, (y * 200) + 50, 600, 100);
				userInputTitle[index] = new Text("", (x * 700) + 50, (y * 200) + 10, 30);
				panel.add(userInputTitle[index]);
				panel.add(userInput[index]);
				index += 1;
			} // end columns for loop
		} // end rows for loop

		// Sets each element in the title text array to the name of the variable which
		// its corresponding textfield will edit
		userInputTitle[0].setText("NAME");
		userInputTitle[1].setText("DESCRIPTION");
		userInputTitle[2].setText("COLOR");
		userInputTitle[3].setText("ORIGIN");
		userInputTitle[4].setText("PRICE");
		userInputTitle[5].setText("WHOLESALE");
		userInputTitle[6].setText("QUANTITY");
		userInputTitle[7].setText("JIT TRIGGER");

		panel.add(confirmBut);

		// update loop
		while (true) {
			// If the confirm button is pressed
			if (confirmBut.isPressed()) {
				// If the variable in one of the textfields was changed, then set the
				// corresponding variable (in the product object) of that textfield to the input
				// inside that textfield

				// If the Name textfield was changed, change the name variable in the product
				// object
				if (userInput[0].wasChanged()) {
					product.setName(userInput[0].getText());
				} // end if

				// If the description textfield was changed, change the description variable in
				// the product object
				if (userInput[1].wasChanged()) {
					product.setDescription(userInput[1].getText());
				} // end if

				// If the color textfield was changed, change the color variable in the product
				// object
				if (userInput[2].wasChanged()) {
					product.setColor(userInput[2].getText());
				} // end if

				// If the origin textfield was changed, change the origin variable in the
				// product object
				if (userInput[3].wasChanged()) {
					product.setOrigin(userInput[3].getText());
				} // end if

				// If the price textfield was changed, change the price variable in the product
				// object
				if (userInput[4].wasChanged()) {
					product.setPrice(Double.valueOf(userInput[4].getText()));
				} // end if

				// If the wholesale textfield was changed, change the wholesale variable in the
				// product object
				if (userInput[5].wasChanged()) {
					product.setWholesale(Double.valueOf(userInput[5].getText()));
				} // end if

				// If the quantity textfield was changed, change the quantity variable in the
				// product object
				if (userInput[6].wasChanged()) {
					product.setQuantity(Integer.valueOf(userInput[6].getText()));
				} // end if

				// If the jit trigger textfield was changed, change the jit trigger variable in
				// the product object
				if (userInput[7].wasChanged()) {
					product.setJitTrigger(Integer.valueOf(userInput[7].getText()));
				} // end if
					// Exits the editProduct loop
				break;
			} // end if
			frame.update();
		} // end update loop

	} // end editProduct method

	/**
	 * Displays the Jit Monitor page which show which products are under their jit
	 * limit
	 * 
	 * @param products the MyProduct object array containing all the products
	 */
	public static void displayJitMonitorPage(MyProduct[] products) {
		// Variable/object declarations and initializations/instantiations
		String[] data = new String[products.length];
		String title = "NAME[34]COLOR[15]QUANTITY[8]JIT[100]";
		ListSearch list;
		Button menuBut = new Button("MENU", 10, 10, 200, 75, "white");

		panel.clear();

		// For loop that instantiations the data string of each product, and colors that
		// data line depending on if the quantity is close to or under the JIT limit.
		// The reason I use html is because JLabel uses html as the only way to format
		// text, and while JLabel does have a color changing option, with the way I made
		// the list object, there is no way for me to access each JLabel object and
		// change
		// its color, so I have to decide that color here in the string.
		for (int i = 0; i < data.length; i++) {
			// If the quantity of product is under the jit limit, add to the data string an
			// html font format that makes the text red
			if (products[i].getQuantity() <= products[i].getJitTrigger()) {
				data[i] = "<font color = red>";
				// If the quantity of product is under the (jit limit) * 2, add to the data
				// string an html font format that makes the text orange
			} else if (products[i].getQuantity() <= products[i].getJitTrigger() * 2) {
				data[i] = "<font color = orange>";
				// else, make the text black
			} else {
				data[i] = "<font color = black>";
			} // end if

			// Add the rest of the product information to the data string
			data[i] += products[i].getName() + "[35]" + products[i].getColor() + "[15]" + products[i].getQuantity()
					+ "[8]" + products[i].getJitTrigger() + "</b></font>[100]";
		} // end for loop

		// Instantiates the list object
		list = new ListSearch(title, data, 175, 300, 15, 500, 30, panel);

		panel.add(menuBut);

		// update loop
		while (true) {
			list.update();

			// Exits the Jit Monitor loop if the menu button is pressed
			if (menuBut.isPressed()) {
				break;
			} // end if

			frame.update();
		} // update loop
	} // displayJitMonitorPage method

	/**
	 * Displays the Admin Options page which allows the user to edit any of the
	 * products
	 * 
	 * @param products the MyProduct object array containing all the products
	 */
	public static void displayAdminOptionsPage(MyProduct[] products) {
		// Variable and object declarations
		ListSearch list;
		String[] data;
		String title;
		Button menuBut;
		boolean returnToMenu = false;
		int choice;

		// adminOptionsPage loop
		while (true) {
			// Variable initializations and object instantiations
			menuBut = new Button("MENU", 10, 10, 200, 75, "white");
			data = new String[products.length];
			title = "NAME[40]COLOR[10]";
			choice = -1;

			// Creates the data string for the list
			for (int i = 0; i < data.length; i++) {
				data[i] = products[i].getName() + "[40]" + products[i].getColor() + "[100]";
			} // end for

			panel.clear();

			// Instantiates the list
			list = new ListSearch(title, data, 175, 200, 20, 650, 30, panel);

			panel.add(menuBut);

			// update loop
			while (true) {
				// Updates the list and gets the user's choice
				list.update();
				choice = list.getChoice();

				// If the choice is not its default value, then edit the product which the user
				// has chosen and then exit to redisplay the adminOptionsPage
				if (choice != -1) {
					editProduct(products[choice]);
					saveProducts(products);
					break;
				} // end if

				// If the menu button is pressed, set returnToMenu to true and then exit the
				// update loop
				if (menuBut.isPressed()) {
					returnToMenu = true;
					break;
				} // end if
				frame.update();
			} // end update loop
				// if returnToMenu is true, exit the adminOptionsPage which return to the menu
			if (returnToMenu) {
				break;
			} // end if
		} // end adminOptionsLoop
	}

	/**
	 * Displays the help page, which explains how each section of the program works
	 */
	public static void displayHelpPage() {
		// Variable and object declarations and initializations
		String purchasingHelpInfo = "<b>PURCHASING</b> \nThe list interface is pretty simple to use. To move the between items,"
				+ " use the up or down arrow keys. To move between pages, either click the buttons to the top-left "
				+ "or click the right/left arrow keys. To show the description of the product, just press the left "
				+ "click button (anywhere on the screen) to show the description box and click it again to hide the "
				+ "description box. To search for a product, just click inside the text field (big white square) at "
				+ "the top and type in your search criteria. You can include product colors and prices in your search "
				+ "criteria! To choose a product, simply click the enter key to be redirected to the purchase page "
				+ "of that product. Here, you can input how much of the product you want to buy. If you buy an amount more "
				+ "than 100, the product will be automatically be charged at whole sale price (rather than the normal, so do not worry!. Input the "
				+ "amount you want to buy in the text field and click done! Input 0 if you do not want to buy the product "
				+ "anymore. After clicking done, you will be redirected back to the list. Continue ordering up to 20 products. When you "
				+ "are done, click on the checkout button, this will display all your purchases and the total cost of all "
				+ "product purchases. From there, you can head back to the main menu or exit the program.";
		String jitMonitorHelpInfo = "<b>JIT MONITOR </b> \nAll products are displayed in the search list format. Here you can "
				+ "simply just look at what products are reaching their JIT quantity limit. The orange highlighted ones are nearing "
				+ "the limit, or to be precise, they are halfway there. The red products have reached or exceeded their JIT limit. ";
		String sessionSalesHelpInfo = "<b>SESSION SALES </b> \nThis page displays all the transactions done during the day, as well as "
				+ "what those sales amount to, and what all the sales of every day amount to (the grand total). You can press the roll "
				+ "over button to reset the sales for the day and move on to the next day. Pressing the button will take you back to the menu, "
				+ "and if you go into session sales again, you will find that all the sales are gone and that the day has increased "
				+ "by one.";
		String adminOptionsHelpInfo = "<b>ADMIN INFO </b> \nThis page allows you to edit the information of a product. All the products "
				+ "are displayed in the classic list format. Simply click enter to edit the product. You wil be taken to a page with "
				+ "8 textfield, each with its own paramter. To change for example the name of the product, simply type the new name "
				+ "in the textfield labelled name. If you do not want to edit a certain info, simply do not type anything into the "
				+ "textfield associated with that information. Once you have changed all the information you need, click the "
				+ "confirm changes button and that product should be updated with the new information.";

		Text[] helpInfoText = new Text[4];
		Button purchasingBut, adminOptionsBut, jitMonitorBut, sessionSalesBut, backBut, menuBut;
		boolean returnToMenu;

		// help page loop
		while (true) {
			// Object and variable initializations/instantiations
			returnToMenu = false;
			purchasingBut = new Button("PURCHASING HELP", panel.getCenterHor(600), 200, 600, 75, "white");
			adminOptionsBut = new Button("ADMIN OPTIONS HELP", panel.getCenterHor(600), 350, 600, 75, "orange");
			jitMonitorBut = new Button("JIT MONITOR HELP", panel.getCenterHor(600), 500, 600, 75, "yellow");
			sessionSalesBut = new Button("SESSION SALES HELP", panel.getCenterHor(600), 650, 600, 75, "blue");
			backBut = new Button("BACK", width - 220, height - 150, 200, 75, "white");
			menuBut = new Button("MENU", 20, 20, 200, 75, "white");

			helpInfoText[0] = new Text(wrapText(purchasingHelpInfo, 50), 20, 20, 20); // HP = Help Info
			helpInfoText[1] = new Text(wrapText(jitMonitorHelpInfo, 50), 20, 20, 20);
			helpInfoText[2] = new Text(wrapText(sessionSalesHelpInfo, 50), 20, 20, 20);
			helpInfoText[3] = new Text(wrapText(adminOptionsHelpInfo, 50), 20, 20, 20);

			panel.clear();

			panel.add(purchasingBut);
			panel.add(jitMonitorBut);
			panel.add(adminOptionsBut);
			panel.add(sessionSalesBut);
			panel.add(menuBut);

			// update loop
			while (true) {

				// Clears panel, adds the back button and corresponding helpInfoText, and then
				// sets the helpInfoText to opaque so it looks like a text box
				if (purchasingBut.isPressed()) {
					panel.clear();
					panel.add(backBut);
					helpInfoText[0].setOpaque(true);
					panel.add(helpInfoText[0]);
				} // end if

				if (jitMonitorBut.isPressed()) {
					panel.clear();
					panel.add(backBut);
					helpInfoText[1].setOpaque(true);
					panel.add(helpInfoText[1]);
				} // end if

				if (sessionSalesBut.isPressed()) {
					panel.clear();
					panel.add(backBut);
					helpInfoText[2].setOpaque(true);
					panel.add(helpInfoText[2]);
				} // end if

				if (adminOptionsBut.isPressed()) {
					panel.clear();
					panel.add(backBut);
					helpInfoText[3].setOpaque(true);
					panel.add(helpInfoText[3]);
				} // end if

				if (menuBut.isPressed()) {
					returnToMenu = true;
					break;
				} // end if

				if (backBut.isPressed()) {
					break;
				} // end if

				frame.update();

			} // update loop
			if (returnToMenu) {
				break;
			} // end if

		} // help page loop
	} // displayHelpPage method

	/**
	 * Displays the purchase page for a specific product
	 * 
	 * @param product the MyProduct object that the user wants to purchase
	 * @return boolean that returns true if the user has bought a quantity of the
	 *         product that is above 0
	 */
	public static boolean purchase(MyProduct product) {
		// Variable/Object declarations and initializations/instantiations
		Text title = new Text("Name: " + product.getName() + "\nColor: " + product.getColor() + "\nOrigin: " + product.getOrigin() +"\nQuantity: "
				+ String.valueOf(product.getQuantity()) + "{200}\n\nPrice {14} WholeSale (100+)\n"
				+ "$" + String.valueOf(product.getPrice()) + "{30} " + "$" + String.valueOf(product.getWholesale()) + "{300}", 100,
				100, 40);
		Text quantityErrorMessage = new Text("We do not have that amount of this product! Come back soon!", 75,
				height - 100, 35, "red");

		// The TextFieldPack is an object that gets input from the user. It consists of
		// a text object which displays a question or what the user should input, a
		// textfield object for the user to type their answer/input, and a button object
		// that the user presses to confirm their answer
		// TextFieldPack Parameter(String question, int xPosition, int yPosition, int
		// width, int height, int posOfText (is relative to the textField. 1 = left, 2 =
		// right, 3 = up, 4 = down), int posOfButton (relative to textfield), int
		// fontSize)
		TextFieldPack userInputTF = new TextFieldPack("Amount to Buy", panel.getCenterHor(500),
				panel.getCenterVer(150) + 200, 500, 150, 3, 4, 50);
		int userInput;

		// Hides the quantity error message
		quantityErrorMessage.setVisible(false);

		panel.clear();

		panel.add(title);
		panel.add(quantityErrorMessage);

		// Adds an array of JComponents that contains the userInputTF's text object,
		// button object, and textfield object, to the panel to be displayed
		panel.add(userInputTF.getPackComponents());

		// update Loop
		while (true) {
			// If the button in the userInputTF pack is pressed and the textfield is not
			// empty
			if (userInputTF.buttonPressed() && userInputTF.getInput() != "") {
				userInput = Integer.valueOf(userInputTF.getInput());
				// if the quantity that the user enters is under 0 or above the quantity of that
				// object, then display the quantityErrorMessage text object for 2 seconds
				if (userInput > product.getQuantity() || userInput < 0) {
					panel.tempShow(quantityErrorMessage, 2000);
					// If the user enters 0, return false, as the user did not purchase any quantity
					// of that product
				} else if (userInput == 0) {
					return false;
					// If the user does not input 0 or an amount that exceeds the quantity of the
					// product
				} else {
					product.setQuantityBought(userInput);
					// If the user buys more than 100, then the price they are charged is the
					// wholesale price
					if (userInput >= 100) {
						product.addCostOfBought(userInput * product.getWholesale());
						// If the user buys less than 100, then the price they are charged is the normal
						// price of the product
					} else {
						product.addCostOfBought(userInput * product.getPrice());
					} // end if 3

					// return true as the user DID purchase some of the product
					return true;
				} // end if 2

			} // end if 1

			frame.update();
		} // update Loop

	}

	/**
	 * Displays a list of all the products and detects which product they choose
	 * 
	 * @param products
	 * @throws IOException
	 */
	public static void displayPurchasePage(MyProduct[] products) throws IOException {
		// Variable/Object Declarations
		MyProduct[] purchasedProducts = new MyProduct[20];
		ListSearch list;
		Mouse mouse;
		Text maxPurchasesError;
		Text[] descriptions;
		String columnName;
		String[] data;
		Button backBut, checkoutBut;
		int choice, numOfPurchases;
		boolean showingDesc, returnToMenu;

		// Purchases Page Loop - This is only really supposed to run once to set up
		// everything, then when the user finishes their operations in the update loop
		// and the the purchases page needs to be displayed again, then this loop
		// re-runs
		// and re-displays the purchases (aka the list of products and checkout button
		// etc)
		while (true) {
			// Variable initializations and object instantiations
			mouse = new Mouse(panel);
			maxPurchasesError = new Text("You cannot buy more than 20 items", 250, 30, 40, "red");
			descriptions = new Text[products.length];
			columnName = "Name[35]Color[15]Price[6]Quantity[40]";
			data = new String[products.length];
			backBut = new Button("BACK", 10, 10, 200, 75, "white");
			numOfPurchases = getNumOfPurchases(purchasedProducts);
			showingDesc = false;
			returnToMenu = false;

			checkoutBut = new Button("CART - " + String.valueOf(numOfPurchases), width - 350, 10, 300, 75, "green");

			// For loop that organizes the information for each product into the data array.
			// The [num] represents how many spaces should be between the string behind it
			// and after it. Think of it like fieldSizing
			for (int i = 0; i < data.length; i++) {
				data[i] = products[i].getName() + "[35]" + products[i].getColor() + "[15]$" + products[i].getPrice()
						+ "[10]" + products[i].getQuantity() + "[40]";
			} // end for

			// Clears panel
			panel.clear();

			// Adds the JComponent objects to the panel
			panel.add(backBut);
			panel.add(checkoutBut);
			panel.add(maxPurchasesError);

			// Hides the max Purchases error text object
			maxPurchasesError.setVisible(false);

			// For loop that sets up the description box for each product
			for (int i = 0; i < descriptions.length; i++) {
				// Declares the description with the text wrapped to make a neat paragraph
				descriptions[i] = new Text(wrapText(products[i].getDescription(), 40), 0, 0, 30);
				// Centers the description text horizontally and vertically
				descriptions[i].centerTextHor(width);
				descriptions[i].centerTextVer(height);
				// Makes it so the text object has a background color, which is what makes it
				// look like a text box
				descriptions[i].setOpaque(true);
				// Adds the description text object into the panel and hides it
				panel.add(descriptions[i]);
				descriptions[i].setVisible(false);
			} // end for

			// Format of List(String columnName, String[] allDataInList, int xPosition, int
			// yPosition, int numOfItemsPerPage, int heightOfList, int fontSize, Panel
			// panel)
			list = new ListSearch(columnName, data, 175, 300, 6, 500, 30, panel);

			// update loop
			while (true) {

				// If back button is pressed, exit the loop and set returnToMenu to true so the
				// purchases page loop can exit
				if (backBut.isPressed()) {
					returnToMenu = true;
					break;
				} // end if

				// If the check out button is pressed, go to the checkout page, and once the
				// user is done there, exit the update loop and set returnToMenu to true so the
				// purchases page loop exits
				if (checkoutBut.isPressed()) {
					displayCheckoutPage(products, purchasedProducts);
					returnToMenu = true;
					break;
				} // end if

				// Update the list
				list.update();
				// Get what item the user has chosen from the list
				choice = list.getChoice();

				// If the choice is not the default value (-1)
				if (choice != -1) {
					// If the user has purchased more than 20 items, display the maxPurchases Error
					// and reset the choice back to its default value of -1
					if (numOfPurchases > 20) {
						panel.tempShow(maxPurchasesError, 2000);
						list.setChoice(-1);
						// Take the user to the purchase page for their product choice. If the user does
						// purchase some of the product (does not buy 0 quantities of the product), then
						// the method will return true, and the product they bought will be added to the
						// purchased products array. Then the update loop will exit to redisplay the
						// purchases page
					} else if (purchase(products[choice])) {
						purchasedProducts[numOfPurchases] = products[choice];
						break;
						// Else if the user types 0 for the quantities to buy for their product, and the
						// purchase method returns false, then just break out of the update loop and
						// redisplay the purchases page
					} else {
						break;
					}
				} // end if

				// If the mouse is pressed and the description is not showing, then show the
				// description for the product that the user has highlighted, else hide the
				// description of the product they have highlighted.
				if (mouse.isPressed()) {
					if (!showingDesc) {
						descriptions[list.getSelectionChoice()].setVisible(true);
						showingDesc = true;
					} else {
						descriptions[list.getSelectionChoice()].setVisible(false);
						showingDesc = false;
					}
				} // end if
				frame.update();
			} // update loop
				// If returnToMenu is true, then exit the purchases page loop, which exits the
				// method and returns to the the menu
			if (returnToMenu) {
				break;
			} // end if
		} // Purchases Page Loop
	}

	/**
	 * Displays the main menu containing the buttons which take the user into the
	 * different sections in the program
	 * 
	 * @param products The MyProduct object array containing all the product objects
	 * @throws IOException
	 */
	public static void displayMainMenu(MyProduct[] products) throws IOException {
		// Variable/Object declarations
		int buttonWidth, buttonHeight;
		Text title;
		Button purchase_button, admin_button, jitMonitor_button, sessionSales_button, help_button, exit_button;
		Picture background;

		// Constantly running loop for the menu//Essentially the program's loop
		// mainMenu loop
		while (true) {
			// Variable initializations and object instantiations
			buttonWidth = 500;
			buttonHeight = 75;

			// Format of Text (String text, int xPosition, int yPosition, int fontSize)
			title = new Text("Phonerama", 480, 100, 80);
			

			// Format of Button(String text, int xPosition, int yPosition, int buttonWidth,
			// int buttonHeight, String buttonColor)
			purchase_button = new Button("PURCHASE", panel.getCenterHor(buttonWidth), 250, buttonWidth, buttonHeight,
					"white");
			admin_button = new Button("ADMIN OPTIONS", panel.getCenterHor(buttonWidth), 350, buttonWidth, buttonHeight,
					"orange");
			jitMonitor_button = new Button("JIT MONITOR", panel.getCenterHor(buttonWidth), 450, buttonWidth,
					buttonHeight, "yellow");
			sessionSales_button = new Button("SESSION SALES", panel.getCenterHor(buttonWidth), 550, buttonWidth,
					buttonHeight, "blue");
			help_button = new Button("HELP", panel.getCenterHor(buttonWidth), 650, buttonWidth, buttonHeight, "green");
			exit_button = new Button("EXIT", panel.getCenterHor(buttonWidth), 750, buttonWidth, buttonHeight, "red");
			background = new Picture("C:\\Users\\amera\\workspace\\Unit 1 Code\\src\\MyStore\\imgs\\background.jpg");

			
			// Erases everything on the panel
			panel.clear();

			// Adds all the JComponent objects to the panel to be displayed
			panel.add(title);
			panel.add(purchase_button);
			panel.add(admin_button);
			panel.add(jitMonitor_button);
			panel.add(sessionSales_button);
			panel.add(help_button);
			panel.add(exit_button);
			panel.setBackground(background, width, height);

			// Loop that checks what button the user presses and displays the corresponding
			// section
			// update loop
			while (true) {

				// Go to purchase section
				if (purchase_button.isPressed()) {
					displayPurchasePage(products);
					break;
				} // end if

				// Go to session sales amount section
				if (sessionSales_button.isPressed()) {
					displaySessionSalesPage();
					break;
				} // end if

				// Go to admin options section
				if (admin_button.isPressed()) {
					displayAdminOptionsPage(products);
					break;
				} // end if

				// Go to Jit Monitor section
				if (jitMonitor_button.isPressed()) {
					displayJitMonitorPage(products);
					break;
				} // end if

				// Go to Help section
				if (help_button.isPressed()) {
					displayHelpPage();
					break;
				} // end if

				// Exit program
				if (exit_button.isPressed()) {
					System.exit(0);
				} // end if

				// Updates frame to apply new changes (needed to detect if button is pressed)
				frame.update();

			} // end update loop
		} // end mainMenu loop
	}

	public static void main(String[] args) throws IOException {
		// Variable/object Declarations and initializations/instantiations
		String[] data = loadDataFromFile("data.csv");
		MyProduct[] products = new MyProduct[data.length];

		// Passing in the csv lines from the data file into the corresponding products
		// object
		for (int i = 0; i < data.length; i++) {
			products[i] = new MyProduct(data[i]);
		} // end for

		// Adds panel to frame so it would be displayed
		frame.add(panel);

		// Displays the main menu
		displayMainMenu(products);

	} // main method

} // MyStore class
