package FlooringFactory;

public class Flooring {
	
	protected String name;
	protected double price;
	
	public Flooring() {
	}
	
	public Flooring(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String toString() {
		return "Name: " + this.name + ", Price: " + this.price;
	}
	
	public void clear() {
		this.name = "";
		this.price = 0;
	}

}
