package salesTax;

import java.util.List;

public class BillCalculator {
	private Double totalTax = 0.00;
	private Double totalSale = 0.00;
	private Double totalItem = 0.00;

	public BillCalculator(List<Item> items) {
		for (Item item : items) {
			this.totalItem = this.totalItem + (item.getPrice() * item.getQuantity());
			this.totalTax = this.totalTax + calculateSalesTax(item);
		}
		this.totalSale = this.totalItem + this.totalTax;
	}

	private Double calculateSalesTax(Item item) {
		Double tax = .10;
		if (item.isExempt()) {
			tax = .00;
		}

		if (item.isImport()) {
			tax = tax + .05;
		}
		Double roundFig = roundAmount((item.getPrice() * tax) * item.getQuantity());
		item.setAfterTax(roundFig + (item.getPrice() * item.getQuantity()));
		return roundFig;
	}

	public Double getTotalTax() {
		return this.totalTax;
	}

	public Double getTotalSale() {
		return this.totalSale;
	}

	private Double roundAmount(Double amount) {
		return Math.ceil((amount * 20.0)) / 20.0;
	}

}