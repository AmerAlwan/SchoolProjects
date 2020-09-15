package MyStore;

public class MyProduct extends Product {

	//Variable declarations and initializations
	protected String color = "black", description = "", origin = "";
	protected int jitTrigger = 0, quantityBought = 0;
	protected double costOfBought = 0;

	// Blank Constructor
	public MyProduct() {
		;
	} //MyProduct constructor

	//MyProduct Constructor for CSV format
	public MyProduct(String dataCSV) {
		String[] data = dataCSV.split("\\|");
		this.name = data[0];
		this.description = data[1];
		this.color = data[2];
		this.origin = data[3];
		this.price = Double.valueOf(data[4]);
		this.wholesale = Double.valueOf(data[5]);
		this.quantity = Integer.valueOf(data[6]);
		this.jitTrigger = Integer.valueOf(data[7]);
	} //MyProduct constructor

	// MyProduct Constructor for individual information
	public MyProduct(String name, String description, String color, String origin, double price, double wholesale,
			int quantity, int jitTrigger) {
		this.name = name;
		this.description = description;
		this.color = color;
		this.origin = origin;
		this.price = price;
		this.wholesale = wholesale;
		this.quantity = quantity;
		this.jitTrigger = jitTrigger;
	} //MyProduct constructor

	/**
	 * Sets the quantity bought and subtracts that number from the overall quantity
	 * @param quantityBought the int to set the quantityBought to
	 */
	public void setQuantityBought(int quantityBought) {
		this.quantityBought += quantityBought;
		this.quantity = (this.quantity - quantityBought);
	} //setQuantityBought method

	/**
	 * Sets the description of the product
	 * @param description the string to set the description to
	 */
	public void setDescription(String description) {
		this.description = description;
	} //setDescription method
	
	/**
	 * Adds the parameter variable to costOfBought, which represents the total sales from this product
	 * @param costOfBought the double value to add to cost of Bought
	 */
	public void addCostOfBought(double costOfBought) {
		this.costOfBought += costOfBought;
	} //addCostOfBought method

	/**
	 * Returns the product's description
	 * @return the string description of the product
	 */
	public String getDescription() {
		return this.description;
	} //getDescription method

	/**
	 * Sets the product's color
	 * @param color the string to set the color of the product as
	 */
	public void setColor(String color) {
		this.color = color;
	} //setColor method

	/**
	 * Returns the product's color
	 * @return the string color of the product
	 */
	public String getColor() {
		return this.color;
	} //getColor method

	/**
	 * Sets the product's origin
	 * @param origin the string to set the product's origin as
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	} //setOrigin method

	/**
	 * Get the product's origin
	 * @return the string origin of the product
	 */
	public String getOrigin() {
		return this.origin;
	} //setOrigin method
	
	/**
	 * Sets the product's jit trigger
	 * @param jitTrigger the int to set the product's jit trigger as
	 */
	public void setJitTrigger(int jitTrigger) {
		this.jitTrigger = jitTrigger;
	} //setJitTrigger method

	/**
	 * Returns the product's jit trigger
	 * @return the int jitTrigger of the product
	 */
	public int getJitTrigger() {
		return this.jitTrigger;
	} //getJitTrigger method

	/**
	 * Returns the product's bought quantity
	 * @return the int quantityBought of the product
	 */
	public int getQuantityBought() {
		return quantityBought;
	} //getQuantityBought method

	/**
	 * Returns the overall sales of the product
	 * @return the int costOfBought of the product
	 */
	public double getCostOfBought() {
		return costOfBought;
	} //getCostOfBought method

	/**
	 * Resets the product's bought quantity and costOfBought variables to their default values of 0
	 */
	public void reset() {
		quantityBought = 0;
		costOfBought = 0;
	} //reset method

	/**
	 * Returns all the product's information in a csv format
	 * @return the string of the csv format of the product's information
	 */
	public String toCSV() {
		return this.name + "|" + this.description + "|" + this.color + "|" + this.origin + "|" + this.price + "|"
				+ this.wholesale + "|" + this.quantity + "|" + this.jitTrigger + "|";
	} //toCSV method

	/**
	 * Returns a single line containing the product's information
	 * @return the string of the product's information
	 */
	public String toString() {
		return "Name: " + this.name + ". Price: " + this.price + ". Wholesale: " + this.wholesale + ". Quantity: "
				+ this.quantity + ". Color: " + this.color + ". Origin: " + this.origin + ". Jit Trigger: " + jitTrigger
				+ ". \nDescription: " + this.description;
	} //toString method
} //MyProduct class
