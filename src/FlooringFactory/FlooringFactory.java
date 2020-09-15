package FlooringFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import EasyGraphics.*;

public class FlooringFactory {

	private final static int width = 1400;
	private final static int height = 800;
	private static Frame frame = new Frame("Flooring Factory", 100, 100, width, height, false);
	private static Panel panel = new Panel(frame);

	/**
	 * Loads information from flooring file, instant
	 * @return
	 * @throws IOException
	 */
	public static Flooring[] loadFloorings() throws IOException {
		String text;
		String[] data;
		int amtOfFloorings;
		Flooring[] floorings;

		// Loading the file containing the floorings
		BufferedReader input = new BufferedReader(new FileReader("flooringlist.csv"));

		// Gets amount of floor types, which in an int on the first line of the file
		amtOfFloorings = Integer.valueOf(input.readLine());

		floorings = new Flooring[amtOfFloorings];

		// For loop that gets each line in file and then splits the name and price of
		// the floor and instantiates each instance of Flooring object array with its
		// corresponding values for name and price
		for (int i = 0; i < amtOfFloorings; i++) {
			text = input.readLine();
			data = text.split("\\|");
			floorings[i] = new Flooring(data[0], Double.valueOf(data[1]));
		}

		//Closes folder
		input.close();

		//Returns all the floors
		return floorings;

	}

	public static void displayPrice(double area, Flooring flooring) {
		resetBackground();
		double materialCost = flooring.getPrice() * area, labourCost = 6.50 * area,
				subTotal = materialCost + labourCost, total = subTotal * 1.13;
		Text receipt = new Text(
				"Materials Cost: " + String.valueOf(materialCost) + "\nLabour Cost: " + String.valueOf(labourCost)
						+ "\nSubtotal: " + String.valueOf(subTotal) + "\nTotal: " + String.valueOf(total),
				0, 0, 50);
		receipt.centerTextHor(width);
		receipt.centerTextVer(height);
		panel.add(receipt);
		frame.update();

	}

	public static Flooring getFlooringChoice(Flooring[] floorings) {

		PagesList list;
		String[] data = new String[floorings.length];
		String prevText;
		int z = 0, prevPageNum;
		boolean cat = false;

		// searchBar.centerFieldHor(width);

		for (int i = 0; i < floorings.length; i++) {
			data[i] = floorings[i].getName() + " |<800> $" + String.valueOf(floorings[i].getPrice());
		}

		list = new PagesList(data, (width / 2) - 500, 200, 8, 550, 30, panel);

		prevText = list.getSearchBar().getText();

		while (true) {

			if (!prevText.equals(list.getSearchBar().getText())) {
				prevText = list.getSearchBar().getText();
				list.update();
				frame.update();
			}

			list.updatePage();

			list.updateSelection();

			if (list.getChoice() != -1) {
				System.out
						.println(floorings[list.getChoice()].getName() + " " + floorings[list.getChoice()].getPrice());
				return floorings[list.getChoice()];
			}

		}

	}

	public static void resetBackground() {
		Picture background = new Picture(
				"C:/Users/amera/workspace/Unit 1 Code/src/FlooringFactory/imgs/background.jpg");
		panel.clear();
		panel.setBackground(background, width, height);
	}

	/**
	 * Displays a simple user input textbox to get the area from the user
	 * 
	 * @throws IOException
	 */
	public static void getArea() throws IOException {
		// Variable declarations and initializations
		int area = 0, wid, hei;
		String input = "";
		// Object declarations and instantiations
		TextFieldPack userInput = new TextFieldPack("AREA #x#", (width / 2) - 200, (height / 2) - 100, 400, 200, 3, 4, 40);
		Text[] errorMessage = new Text[3];
		Text areaText = new Text("", 50, 50, 50, "white");
		Button addRoom_but = new Button(" + ", (userInput.getButton().x() + userInput.getButton().width()) + 20,
				userInput.getButton().y(), 100, userInput.getButton().height(), "white");
		Animation animation = new Animation(frame);

		// Changing Settings on some of the JComponents objects
		for (int i = 0; i < errorMessage.length; i++) {
			errorMessage[i] = new Text("-", 0, height - 250, 50);
			panel.add(errorMessage[i]);
			errorMessage[i].setVisible(false);
		}
		errorMessage[0].changeText("Please input a value higher than 0!");
		errorMessage[1].changeText("Please input atleast one room!");
		errorMessage[2].changeText("Area format must be WidthxHeight");
		errorMessage[0].centerTextHor(width);
		errorMessage[1].centerTextHor(width);
		errorMessage[2].centerTextHor(width);

		userInput.getTextField().setFont(100);

		// Resetting panel and adding all components on screen

		resetBackground();

		panel.add(userInput.getText());
		panel.add(userInput.getButton());
		panel.add(userInput.getTextField());
		panel.add(addRoom_but);
		panel.add(areaText);

		frame.update();

		// Get area Loop
		while (true) {
			// Saves the text typed into the text field into the variable input
			try {
				input = userInput.getTextField().text().toLowerCase();
			} catch (NullPointerException e) {

			}
			if (!input.equals("")) {
				// input = userInput.getTextField().text().toLowerCase();

				// Checks if the addRoom button (the one with a '+') is pressed
				if (addRoom_but.isPressed()) {
					if (!input.contains("x")) {
						animation.tempShow(errorMessage[2], 2000);
					} else {
						wid = Integer.valueOf(input.substring(0, input.indexOf("x")));
						hei = Integer.valueOf(input.substring(input.indexOf("x") + 1));
						if (wid == 0 || hei == 0) {
							animation.tempShow(errorMessage[0], 2000);
						} else {
							area += (wid * hei);
							userInput.getTextField().setText("");
							areaText.changeText(String.valueOf(area) + "m^2");
						}

					}

				}
			}

			if (userInput.getButton().isPressed()) {
				if (area == 0) {
					animation.tempShow(errorMessage[1], 2000);
				} else {
					panel.clear();
					displayPrice(area, getFlooringChoice(loadFloorings()));
				}
			}

			frame.update();
		}

	}

	/**
	 * Displays the main menu with a title, a start button which starts the program,
	 * and an exit button that exit the program
	 * 
	 * @throws IOException
	 */
	public static void mainMenu() throws IOException {
		// Object declarations and instantiations
		Text title = new Text("FLOORING\nFACTORY", 0, 60, 100);
		Button start_b = new Button("START", 0, 0, 200, 100, "white");
		Button exit_b = new Button("EXIT", 0, height - 300, 200, 100, "179, 000, 000");

		// Altering settings of objects
		title.centerTextHor(width);
		start_b.centerButHor(width);
		start_b.centerButVer(height);
		exit_b.centerButHor(width);

		// Adding panel to frame and resetting panel
		frame.add(panel);
		resetBackground();

		// Adding objects to panel
		panel.add(title);
		panel.add(start_b);
		panel.add(exit_b);

		// Main menu loop
		while (true) {

			// Checks to see if the Start button is pressed and gets the area if it is
			if (start_b.isPressed()) {
				panel.clear();
				getArea();
			}

			// Exits the program if the exit button is pressed
			if (exit_b.isPressed()) {
				System.exit(0);
			}

			frame.update();
		}

	}

	public static void main(String[] args) throws IOException {

		// Displays the start menu
		mainMenu();

	}

}
