package salesTax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartScanner {

	private CartItem purchase;

	public CartScanner(String cart) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(cart));
			String itemDetails;
			this.purchase = new CartItem();
			while ((itemDetails = reader.readLine()) != null) {
				this.purchase.addItem(scanQuantity(itemDetails), scanDetails(itemDetails), scanPrice(itemDetails));
			}
			reader.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public CartItem getPurchase() {
		return this.purchase;
	}

	private int scanQuantity(String details) {
		Pattern pattern = Pattern.compile("^[\\d+]+");
		Matcher match = pattern.matcher(details);
		match.find();
		return Integer.parseInt(match.group(0));
	}

	private String scanDetails(String details) {
		Pattern pattern = Pattern.compile("(?!^\\d)[A-Za-z].+(?=\\sat\\s\\d+.\\d+$)");
		Matcher match = pattern.matcher(details);
		match.find();
		return match.group(0);
	}

	private Double scanPrice(String details) {
		Pattern pattern = Pattern.compile("\\d+.\\d+$");
		Matcher match = pattern.matcher(details);
		match.find();
		return Double.parseDouble(match.group(0));
	}
}