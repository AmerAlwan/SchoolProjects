import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import SimpleMethods.*;

public class productGenerator {

	public static void main(String[] args) throws IOException {
		PrintWriter output = new PrintWriter(
				new FileWriter("C:/Users/amera/workspace/Unit 1 Code/src/MyStore/testdata.csv"));
		SMethods input = new SMethods();
		String productName, allColors, description, origin, userInput;
		String[] colors;
		double price, wholeSale;
		int[] quantities, jitTriggers;
		while (true) {
			productName = input.getString("Product Name: ");
			description = input.getString("Description: ");
			origin = input.getString("Origin: ");
			allColors = input.getString("Colors (',' to divide): ");
			price = input.getDouble("Price: ");
			wholeSale = input.getDouble("Wholesale: ");
			colors = allColors.split(",");
			quantities = new int[colors.length];
			jitTriggers = new int[colors.length];
			for (int i = 0; i < colors.length; i++) {
				quantities[i] = input.getInt("Quantity of " + colors[i] + ": ");
				jitTriggers[i] = input.getInt("Jit Trigger of " + colors[i] + ": ");
			}
			for (int i = 0; i < colors.length; i++) {
				output.println(productName + "|" + description + "|" + colors[i] + "|" + origin + "|"
						+ String.valueOf(price) + "|" + String.valueOf(wholeSale) + "|"
						+ String.valueOf(quantities[i] + "|" + String.valueOf(jitTriggers[i] + "|")));
			}
			userInput = input.getStringAssert("Another Product? ", "y", "n");
			if(userInput.equals("n")) {
				output.close();
				break;
			}
			System.out.println();
		}
	}

}
