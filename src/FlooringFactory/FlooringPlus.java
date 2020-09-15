package FlooringFactory;

public class FlooringPlus extends Flooring {
	protected int quantity;
	protected String description;
	
	
	public FlooringPlus() {
		
	}
	
	
	public FlooringPlus(String name, double price, int quantity, String description) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getDescription() {
		return description;
	}
}
