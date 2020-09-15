package BusinessPOS;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Item  {

	protected String name, description, color;
	protected double price;
	protected int numSelected;
	protected boolean pressed = false;

	// Empty Constructor
	public Item() {
	}

	// Input Constructor
	public Item(String name, String description, String color, double price) {
		this.name = name;
		this.description = description;
		this.color = color;
		this.price = price;
	//	setUpButton();
	}

	// CSV Constructor
	public Item(String csvData) {
		String[] data = csvData.split("\\|");
		this.name = data[0];
		this.description = data[1];
		this.color = data[2];
		this.price = Double.valueOf(data[3]);
		//setUpButton();
	}

//	public void setUpButton() {
//		this.setBounds(0, 0, 0, 0);
//		this.setText(this.name);
//		this.setForeground(Color.WHITE);
//		addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				pressed = true;
//			}
//		});
//	}

	public void setNumSelected(int numSelected) {
		this.numSelected = numSelected;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public String getColor() {
		return this.color;
	}

	public double getPrice() {
		return this.price;
	}
	
	public int getNumSelected() {
		return this.numSelected;
	}
	
	public boolean isPressed() {
		if(pressed) {
			pressed = false;
			return true;
		}
		return false;
	}

	public String toString() {
		return "Name: " + this.name + ". Desription: " + this.description + ". Color: " + this.color + ". Price: "
				+ this.price;
	}

	public String toCSV() {
		return this.name + "|" + this.description + "|" + this.color + "|" + this.price + "|";
	}

}
