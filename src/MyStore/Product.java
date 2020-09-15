/**
 * The Product Class
 * 
 * Generated using Mr. Milardovic's ObjectMaker
 * 
 * 
 * @author Amer Alwan
 * @date Wednesday, December 11, 2019
 */

package MyStore;

import java.awt.*;

public class Product {
	
	protected String name = "";
	protected double wholesale = 0.01;
	protected double price = 0.01;
	protected int quantity = 0;
	
	//Blank constructor method
	public Product() {
		;
	}
	
	//Constructor method
	public Product(String name, double wholesale, double price) {
		this.name = name;
		this.wholesale = wholesale;
		this.price = price;
		this.quantity = quantity;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setWholesale(double wholesale) {
		this.wholesale = wholesale;
	}
	
	public double getWholesale() {
		return this.wholesale;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	

}
