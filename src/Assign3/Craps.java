package Assign3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Craps {

	//Special Case variables that need to be declared globally
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	static boolean buttonClicked = false;

	
	
	
	
	/**
	 * Displays the Frame
	 */
	public void DisplayFrame() {
		//Shows the frame
		frame.setVisible(true);
	}

	
	
	
	
	/**
	 * Formats the panel and erases everthing in it
	 */
	public void FormatPanel() {
		//Removes every component from the panel
		panel.removeAll();
		//Repaints the panel/displays new components
		frame.validate();
		frame.repaint();
		//Hides the panel
	//	panel.setVisible(false);
	}

	
	
	
	
	/**
	 * Updates the panel to redisplay new components in it
	 */
	public void UpdatePanel() {
		//Updates the panel to display new components
		panel.repaint();
		frame.validate();
		frame.repaint();
		//Shows the panel
		//panel.setVisible(true);
	}

	
	
	
	
	/**
	 * Stops the program for inputed period
	 * @param millisecond 		the int amount of milliseconds to stop the program for
	 */
	public void Wait(int millisecond) {
		//I do not really understand the try-catch (only that it deals with errors), but it is needed for thread.sleep
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	/**
	 * Temporarily displays a JLabel to the screen
	 * @param label		the JLabel label to be temporarily displayed
	 * @param time		the int amount of time (in milliseconds) to display the JLabel for
	 */
	public void TempDisplay(JLabel label, int time) {
		//Shows the inputed JLabel
		label.setVisible(true);
		//Stops program for 'time' milliseconds
		Wait(time);
		//Hides the inputed JLabel
		label.setVisible(false);
	}

	
	
	
	
	/**
	 * Setups the frame/window of the program and adds the panel to it
	 * @param width		the int width of the frame/window
	 * @param height	the int height of the frame/window
	 */
	public void SetupFrame(int width, int height) {
		// Sets the title of the frame to "Craps"
		frame.setTitle("Craps");
		// Sets the frame at position (100, 100) and makes its size the value of width
		// and height
		frame.setBounds(100, 100, width, height);
		// Disables the ability to resize the window as that can mess with graphics in
		// the panel
		frame.setResizable(false);
		// Makes it so the frame closes when the 'X' key is pressed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Do not know what this does, but any graphics inside the panel do not show if
		// I do not have it
		panel.setLayout(null);

		// Adds the panel to the frame so the panel would be displayed inside the frame
		frame.add(panel);
	}

	
	
	
	
	/**
	 * Creates a new button that displays according to inputed parameters and then returns it
	 * @param x				int x-position of the button
	 * @param y				int y-position of the button
	 * @param width			int width of the button
	 * @param height		int height of the button
	 * @param fontSize		int font size of the text in the button
	 * @param text			string of what to display in the button
	 * @return				the button that was created with the new settings applied to it
	 */
	JButton AddButton(int x, int y, int width, int height, int fontSize, String text) {
		//Declares button with text 'text' in it
		JButton button = new JButton(text);
		//Sets the font and size of the text in the button
		button.setFont(new Font("timesnewroman", Font.PLAIN, fontSize));
		//Sets the position and size of the button
		button.setBounds(x, y, width, height);
		//Adds the button to the panel to display it on the panel
		panel.add(button);
		//Returns the newly created button
		return button;
	}

	
	
	
	
	/**
	 * Creates a text JLabel and sets its settings according to the inputed parameters
	 * @param text			the string text to display
	 * @param font			the string font of the text
	 * @param fontSize		the int size of the font of the text
	 * @param x				the int x-position of the text
	 * @param y				the int y-position of the text
	 * @param width			the int width of the text
	 * @param height		the int height of the text
	 * @param bold			the boolean for if the text will be bold
	 * @param italic		the boolean for if the text will be italic
	 * @return				returns the text JLabel with the new settings applied to it
	 */
	JLabel AddText(String text, String font, int fontSize, int x, int y, int width, int height, boolean bold,
			boolean italic) {
		//Creates a new JLabel with the text 'text'
		JLabel passage = new JLabel(text);
		//Sets the font to bold if 'bold' is true
		if (bold) {
			passage.setFont(new Font(font, Font.BOLD, fontSize));
		//Sets the font to italic if 'italic' is true
		} else if (italic) {
			passage.setFont(new Font(font, Font.ITALIC, fontSize));
		//Else, sets the font to plain
		} else {
			passage.setFont(new Font(font, Font.PLAIN, fontSize));
		}
		//Sets the position and size of the field surrounding the text (not the size of the text itself)
		passage.setBounds(x, y, width, height);
		//adds the text JLabel to the panel so it would be displayed
		panel.add(passage);
		//Returns the newly created JLabel
		return passage;
	}

	
	
	
	
	/**
	 * Creates an image JLabel and displays it on the screen. Sets its settings according to the inputed parameters
	 * @param file			String name of the image file with extension
	 * @param x				int x-position of the image
	 * @param y				int y-position of the image
	 * @param width			int width of the image
	 * @param height		int height of the image
	 * @return				returns the image JLabel with the new settings applied to it
	 */
	JLabel AddImage(String file, int x, int y, int width, int height) {
		//Variable declarations
		Image image_load;
		ImageIcon imageIcon;
		JLabel image;
		
		//Variable Initializes
		image_load = null;
		
		try {
			//Loads the image and sets its size to width and height
			image_load = ImageIO.read(new File("C:/Users/amera/workspace/Unit 1 Code/src/Assign3/img/" + file))
					.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imageIcon = new ImageIcon(image_load);
		image = new JLabel(imageIcon);
		
		//Sets the position and canvas size of the image
		image.setBounds(x, y, width, height);
		//Adds the image to the panel so it can be displayed
		panel.add(image);
		//Returns the newly created image JLabel
		return image;
	}

	
	
	
	
	/**
	 * Creates and displays a text field and sets its settings according to the inputed parameters
	 * @param text			The string text that informs the user of what to input in the textfield. Comes before the textfield.
	 * @param fontSize		The string font of the text before the textfield
	 * @param x				the int x-position of the textfield
	 * @param y				the int y-position of the textfield
	 * @param width			the int width of the textfield
	 * @param height		the int height of the textfield
	 * @return				returns the JTextField with the new settings applied to it
	 */
	JTextField AddTextField(String text, int fontSize, int x, int y, int width, int height) {
		JLabel textLabel; 
		JTextField textField;
		
		
		textLabel = AddText(text, "timesnewroman", (int) fontSize, x - (text.length() * 18), y - (height / 2),
				text.length() * 80, 100, true, false);
		textField = new JTextField();
		
		//Sets the position and size of the textfield
		textField.setBounds(x, y, width, height);
		//Sets the font of the textfield
		textField.setFont(new Font("timesnewroman", Font.PLAIN, fontSize));
		//Adds the textfield to the panel so it can be displayed
		panel.add(textField);
		//Returns the newly created textfield
		return textField;
	}

	
	
	
	
	/**
	 * Checks if a certain button was clicked
	 * @param button	the JButton to be checked
	 * @return			returns true if the button was clicked, and false otherwise
	 */
	boolean CheckButtonClicked(JButton button) {
		buttonClicked = false;
		//Adds and action listener to the button to listen to any actions done by the button
		button.addActionListener(new ActionListener() {
			//If the button is clicked, then whatever I want to happen next has to go in the actionPerformed procedure. In some cases, that means my whole program would go in there. Because of this, I just had a global variable, button clicked, be set to true when the button is clicked
			public void actionPerformed(ActionEvent e) {
				buttonClicked = true;
			}
		});
		
		//if button clicked is true, then return true
		if (buttonClicked) {
			buttonClicked = false;
			return true;
		}
		return false;
	}

	
	
	
	
	/**
	 * Rolls the two dices in an alternating dices animation and gets a random score
	 * @param dice			the JLabel array of images for the first dice
	 * @param dice2			the JLabel array of images for the second dice
	 * @return				the int sum of the randomized int of the first and second dice
	 */
	int RollDice(JLabel[] dice, JLabel[] dice2) {

		//Variable declarations and initializations
		int x1 = 0, x2 = 200, y = 30, speedx = 30, speedy = -5, randomDice1, randomDice2;

		//Plays the roll dice animation
		for (int i = 0; i < 28; i++) {
			//Get random number for each dice
			randomDice1 = (int) (Math.random() * 6);
			randomDice2 = (int) (Math.random() * 6);
			//Adds speed to the x and y position of the dice
			x1 += speedx;
			x2 += speedx;
			y += speedy;
			speedx -= 1;
			speedy += 1;
			//Sets the new position of the dices
			dice[randomDice1].setBounds(x1, y, 100, 100);
			dice2[randomDice2].setBounds(x2, y, 100, 100);
			//Sets each dice visible for 80 milliseconds and then hides them
			dice[randomDice1].setVisible(true);
			dice2[randomDice2].setVisible(true);
			Wait(80);
			dice[randomDice1].setVisible(false);
			dice2[randomDice2].setVisible(false);
		}

		//After the animation is done, the final, actual score is randomized and its corresponding dice displayed for 2 seconds
		randomDice1 = (int) (Math.random() * 6);
		randomDice2 = (int) (Math.random() * 6);
		dice[randomDice1].setBounds(x1, y, 100, 100);
		dice2[randomDice2].setBounds(x2, y, 100, 100);
		dice[randomDice1].setVisible(true);
		dice2[randomDice2].setVisible(true);
		Wait(2000);
		//Returns the score
		return (randomDice1 + 1) + (randomDice2 + 1);
	}

	
	
	
	
	/**
	 * Displays an info board that takes in important info about the user and his status and displays it as text JLabels on a board image
	 * @param namePar			String name of the user
	 * @param amtToWinPar		String amount of score needed to win
	 * @param amtToLosePar		String amount of score that would cause him to lose
	 * @param balancePar		double balance of the user
	 * @param betPar			double amount that the user bet
	 * @param scorePar			double score that the user earned on his last dice roll
	 * @param x					int x-position of the board
	 * @param y					int y-position of the board
	 * @param width				int width of the board
	 * @param height			int height of the board
	 */
	public void DisplayInfoBoard(String namePar, String amtToWinPar, String amtToLosePar, double balancePar,
			double betPar, int scorePar, int x, int y, int width, int height) {
		
		//Variable Declarations
		JLabel board, name, amtToWin, amtToLose, balance, bet, score, nameText, amtToWinText, amtToLoseText,
				balanceText, betText, scoreText;
		int boardCenterX;
		
		//Variable Initializations
		
		boardCenterX = (x + (width + x)) / 2;
		nameText = AddText("Name:", "timesnewroman", 40, x + 30, y + 30, 200, 50, true, false);
		balanceText = AddText("Balance:", "timesnewroman", 40, x + 30, y + 80, 200, 50, true, false);
		betText = AddText("Bet:", "timesnewroman", 40, x + 30, y + 150, 200, 50, true, false);
		amtToWinText = AddText("Score to Win:", "timesnewroman", 40, x + 30, y + 230, 500, 50, true, false);
		amtToLoseText = AddText("Score to Lose:", "timesnewroman", 40, x + 30, y + 300, 500, 50, true, false);
		scoreText = AddText("Score:", "timesnewroman", 40, x + 30, y + 380, 200, 50, true, false);

		name = AddText(namePar, "arial", 40, boardCenterX + 40, y + 30, 500, 50, true, false);
		balance = AddText("$" + String.valueOf(balancePar), "arial", 40, boardCenterX + 40, y + 80, 200, 50, true,
				false);
		bet = AddText("$" + String.valueOf(betPar), "arial", 40, boardCenterX + 40, y + 150, 200, 50, true, false);
		amtToWin = AddText(amtToWinPar, "arial", 40, boardCenterX + 40, y + 230, 500, 50, true, false);
		amtToLose = AddText(amtToLosePar, "arial", 40, boardCenterX + 40, y + 300, 500, 50, true, false);
		score = AddText(String.valueOf(scorePar), "arial", 40, boardCenterX + 40, y + 380, 200, 50, true, false);

		//Sets the background image of the board
		board = AddImage("board.png", x, y, width, height);
	}

	
	
	
	
	/**
	 * Plays dice animation and then returns the randomly generated sum of both dices
	 * @param name			string name of the user (needed for the info board)
	 * @param amtToWin		String amount needed to win (needed for the info board)
	 * @param amtToLose 	string amount needed to lose (needed for the info board)
	 * @param balance		double balance of the user (needed for the info board)
	 * @param bet			double bet of the user (needed for the info board)
	 * @param score			int score from last dice throw (needed for the info board)
	 * @param width			int width of board (needed for the info board)
	 * @param height  		int height of board (needed for the info board)
	 * @return				returns the user's new score
	 */	
	int GetScore(String name, String amtToWin, String amtToLose, double balance, double bet, int score, int width,
			int height) {
		//Variable Declarations
		
		JLabel background;
		ImageIcon diceIcon;
		JLabel[] dice = new JLabel[6];
		JButton rollDiceBut;

		//Variable initializations
		
		rollDiceBut = AddButton(width - 400, 100, 300, 100, 40, "Roll Dice");
		//Sets the rollDice Button as a default button so that it would be clicked when the 'Enter' key is pressed
		frame.getRootPane().setDefaultButton(rollDiceBut);

		//Displays the info board with all the necessary information
		DisplayInfoBoard(name, amtToWin, amtToLose, balance, bet, score, 900, 280, 550, 445);

		//Sets the dice image for each JLabel of the dice array and then hides them
		for (int i = 0; i < 6; i++) {
			dice[i] = AddImage("dice_" + Integer.toString(i + 1) + ".png", 0, 0, 100, 100);
			dice[i].setVisible(false);
		}

		// Background needs to be initialized last or else it will display over other
		// JLabels
		background = AddImage("table.png", 0, 0, width, height);

		//Updates the panel to display new components on it
		UpdatePanel();

		//Loop that exits when the roll dice button is clicked
		while (true) {
			//If the roll dice button is clicked, then the dice is rolled, and its result sent back and stored in score, and then the loop exits
			if (CheckButtonClicked(rollDiceBut)) {
				score = RollDice(dice, dice);
				break;
			}
		}

		//Updates panel to display new components on it
		UpdatePanel();

		//Returns the score
		return score;
	}

	
	
	
	
	/**
	 * Displays an image for the user if they win
	 */
	public void DisplayWinComponents() {
		//Variable Declarations
		JLabel winImage;
		//Formats/erases everything in the panel
		FormatPanel();
		//Initializes and displays the win image
		winImage = AddImage("win.png", 500, 70, 600, 600);
		//Updates the panel to display new components on it
		UpdatePanel();
		//Displays the win image for 3 seconds
		TempDisplay(winImage, 3000);
	}

	
	
	
	
	/**
	 * Displays a lose image to the user
	 */
	public void DisplayLoseComponents() {
		//Variable Declarations
		JLabel loseImage;
		//Formats/erases everything in the panel
		FormatPanel();
		//Initializes and displays the lose image
		loseImage = AddImage("lose.png", 450, 70, 600, 600);
		//Updates the panel to display new components on it
		UpdatePanel();
		//Displays the lose image for 3 seconds
		TempDisplay(loseImage, 3000);
	}

	
	
	
	
	/**
	 * Asks the user if they want to restart
	 * @param width			int width of the frame
	 * @param height		int height of the frame
	 * @param balance		int balance of the frame
	 * @return				returns false if the user presses the 'No' button or true if they wait until the timer reaches 0
	 */
	boolean AskRestart(int width, int height, double balance) {
		// Variable Declarations
		JLabel askRestart, balanceText, restartText;
		JButton exit_but;
		int timer = 80000;

		// Variable Initializations
		askRestart = AddText("Game Over", "timesnewroman", 30, (width / 2) - 100, (height / 2) - 100, 700, 100, false,
				false);
		balanceText = AddText("Balance: " + String.valueOf(balance), "timesnewroman", 30, (width / 2) - 105,
				(height / 2) - 50, 500, 100, false, false);
		restartText = AddText("Restarting in " + String.valueOf(timer), "timesnewroman", 30, (width / 2) - 150,
				(height / 2) + 100, 700, 100, false, false);
		exit_but = AddButton((width / 2) - 50, (height / 2) + 30, 100, 50, 30, "NO");
		// Sets the exit button to default so the button clicks when the 'enter' key is
		// pressed
		frame.getRootPane().setDefaultButton(exit_but);

		// Updates the panel to display the new components on it
		UpdatePanel();

		// Loop that exits when a boolean value is returned
		// NOTE: The reason I went with a timer is because I had a glitch whenever I
		// tried adding two buttons on the screen at the same time and checking if they
		// were clicked. Basically pressing one button would press the other and vice
		// versa. I did not have time to fix the issue or come up with a better solution,
		// so this is what I had to go with for now. This issue will be fixed in future
		// projects.
		while (true) {
			// If the exit button is pressed, then false is returned and the game ends
			if (CheckButtonClicked(exit_but)) {
				return false;
			}

			timer -= 1;

			restartText.setText("Restarting in " + String.valueOf(timer));

			// Returns true when the timer reaches 0 and the game restarts
			if (timer <= 0) {
				return true;
			}
		}
	}

	
	
	
	
	/**
	 * Asks the user for the bet in a textfield and displays a bunch of errors based on what they input
	 * @param balance		the double balance of the user
	 * @param width			the int width of the frame
	 * @param height		the int height of the frame
	 * @return				returns the double bet after it had passed all conditions
	 */
	double GetBet(double balance, int width, int height) {
		// Variable Declarations
		JLabel[] errorMessage = new JLabel[5];
		JLabel background;
		JButton doneButton;
		JTextField getBetInput;
		double bet;

		// Variable Initializations
		doneButton = AddButton((width / 2) + 70, height / 2, 200, 50, 30, "Done");
		// Sets the Done button to default so it can be clicked when the 'Enter' key is
		// pressed
		frame.getRootPane().setDefaultButton(doneButton);

		getBetInput = AddTextField("Bet [Bal: $25]:", 30, (width / 2) - 150, (height / 2), 200, 50);

		errorMessage[0] = AddText("Bet Field is empty!", "timesnewroman", 30, (width / 2) - 200, (height / 2) + 50, 500,
				100, true, false);
		errorMessage[1] = AddText("You do not have that amount!", "timesnewroman", 30, (width / 2) - 200,
				(height / 2) + 50, 500, 100, true, false);
		errorMessage[2] = AddText("You cannot bet more than $10 if your balance is less than $100!", "timesnewroman",
				30, (width / 2) - 500, (height / 2) + 50, 1000, 100, true, false);
		errorMessage[3] = AddText("That bet is not a multiple of 0.25!", "timesnewroman", 30, (width / 2) - 300,
				(height / 2) + 50, 500, 100, true, false);

		// Hides all the error messages and sets their color to red.
		for (int i = 0; i < 4; i++) {
			errorMessage[i].setVisible(false);
			errorMessage[i].setForeground(Color.red);
		}

		background = AddImage("background.png", 0, 0, width, height);

		// Updates the panel to show the newly added JLabels and textfield
		UpdatePanel();

		// Error trap to get the bet
		while (true) {
			// Checks if the done button is clicked
			if (CheckButtonClicked(doneButton)) {
				// Checks if the user has types anything into the textfield
				if (getBetInput.getText().length() != 0) {
					// Converts the string value of the textfield into a double and stores it into
					// bet
					bet = Double.parseDouble(getBetInput.getText());
					// If the bet is under 0 or over the user's balance
					if (bet < 0 || bet >= balance) {
						TempDisplay(errorMessage[1], 1000);
						// If the bet is over 10 while the balance is under 100
					} else if (balance < 100 & bet > 10) {
						TempDisplay(errorMessage[2], 1000);
						// If the bet is not a multiple of 0.25
					} else if (bet % 0.25 != 0) {
						TempDisplay(errorMessage[3], 1000);
						// If all else is good, exit the error trap
					} else {
						break;
					}
				} else {
					// Tells the user that the textfield is empty
					TempDisplay(errorMessage[0], 1000);
				}
			}

		}

		// Formats/Clears everything in the panel
		FormatPanel();

		// Returns the bet
		return bet;

	}

	
	
	
	
	/**
	 * Runs the score through a bunch of conditions that determine whether the user has won or lost
	 * @param nameParameter			the string name of the user
	 * @param balanceParameter		the double balance of the user
	 * @param width					the int width of the window
	 * @param height				the int height of the window
	 */
	public void PlayGame(String nameParameter, double balanceParameter, int width, int height) {

		// Variable Declarations
		String name = nameParameter;
		double balance = balanceParameter, bet;
		int score = -1, point;
		String amtToWin, amtToLose;

		// Main 'PlayGame' procedure loop
		while (true) {

			// Variable Initializations
			amtToWin = "7 or 11";
			amtToLose = "2, 3, or 12";

			// Gets the bet from the user
			bet = GetBet(balance, width, height);

			// Exits 'PlayGame' loop if user enters 0 for the bet, hence exiting the game
			if (bet == 0) {
				break;
			}

			// Gets score/basically runs the whole dice throw mechanism and returns the
			// result
			score = GetScore(name, amtToWin, amtToLose, balance, bet, score, width, height);

			// If the score is 7 & 11, the user will be informed of their victory, and their
			// bet added to their balance
			if (score == 7 || score == 11) {
				DisplayWinComponents();
				balance += bet;

				// If the score is 2, 3, or 12, then the user will be informed of their loss,
				// and their bet taken from their balance
			} else if (score == 2 || score == 3 || score == 12) {
				DisplayLoseComponents();
				balance -= bet;
				// If they get another score
			} else {
				// Variable Initializations
				point = score;
				amtToWin = String.valueOf(score);
				amtToLose = "7 or 11";
				// Loop that exits when the score is either the point, 7, or 11
				while (true) {
					// Formats/erases everything in the panel
					FormatPanel();
					score = GetScore(name, amtToWin, amtToLose, balance, bet, score, width, height);
					// If the score is equal to the point, then the user will be informed of their
					// victory, their bet added to their balance, and then the loop exited
					if (score == point) {
						DisplayWinComponents();
						balance += bet;
						break;
						// If the score is equal to 7 or 11, then the user will be informed of their
						// loss, their bet taken from their balance, and the the loop exits
					} else if (score == 7 || score == 11) {
						DisplayLoseComponents();
						balance -= bet;
						break;
					}
				}

			}

			// Asks the user if they want to restart by calling the AskRestart Function
			if (AskRestart(width, height, balance) == false) {
				break;
			}
			// Formats/erases everything in the panel
			FormatPanel();
		}
	}

	
	
	
	
	/**
	 * Displayed at the start of the game and asks the user for their name and then call the PlayGame procedure to display the rest of the game
	 * @param width		int width of the frame
	 * @param height	int height of the frame
	 */
	public void MainMenu(int width, int height) {
		// Variable Declarations
		JTextField getNameInput, getBetInput;
		JButton doneButton;
		JLabel background, errorMessage;
		String name;
		double balance;

		// Variable initializations
		balance = 25;

		getNameInput = AddTextField("Name: ", 30, (width / 2) - 150, (height / 2), 200, 50);
		doneButton = AddButton((width / 2) + 70, height / 2, 200, 50, 30, "Done");
		// Sets the done button as a default button, meaning it can be clicked when the
		// 'Enter' key is pressed
		frame.getRootPane().setDefaultButton(doneButton);

		errorMessage = AddText("That name is too long/short!", "timesnewroman", 30, (width / 2) - 200,
				(height / 2) - 150, 500, 100, true, false);

		// Hides the error message
		errorMessage.setVisible(false);

		// Sets the background. The background has to be initialized last or else it
		// will overlay other JLabels
		background = AddImage("background.png", 0, 0, width, height);

		// Sets the frame to visible
		DisplayFrame();

		// Error trap loop to get name from user and insures that its length is above 0
		// and under 10
		while (true) {
			// Checks if the done button is clicked. This works by the logic that the user
			// will type something into the textfield and then press the button, which is
			// when the program will actually check and save the contents of the textfield.
			if (CheckButtonClicked(doneButton)) {
				// Sets the name string to whatever is typed inside the getNameInput textfield
				name = getNameInput.getText();
				// If the name's length is 0 or more than 10, then it displays an error message
				// for 1 second, else it exits the error trap loop
				if (name.length() == 0 || name.length() > 10) {
					TempDisplay(errorMessage, 1000);
				} else {
					break;
				}
			}
		}

		// Formats/erases everything in the panel
		FormatPanel();

		// Officially starts the game after the name has been determined.
		PlayGame(name, balance, width, height);

	}

	
	
	
	
	public static void main(String[] args) {
		// Variable Declarations and Initializations
		Craps craps = new Craps();
		int width = 1466, height = 768;

		// Sets up the frame and panel
		craps.SetupFrame(width, height);

		// Displays the Main Menu, which also calls the procedure that also plays the
		// rest of the game
		craps.MainMenu(width, height);
	}
}
