package salesTax;

import java.io.File;
import java.io.IOException;

public class SalesTaxTest {
    public static void main(String[] args) throws IOException {
        String[] defaultCarts = {
                "inputFiles/textFiles/input1.txt",
                "inputFiles/textFiles/input2.txt",
                "inputFiles/textFiles/input3.txt"
        };

        String[] cartFilesInQueue = (args.length > 0) ?
                args : defaultCarts;

        for (String cart : cartFilesInQueue) {
            if (new File(cart).exists()){
                CartScanner cartScanner = new CartScanner(cart);
                CartItem purchase = cartScanner.getPurchase();
                BillCalculator calculate = new BillCalculator(purchase.getInventory());
                BillDisplay display = new BillDisplay();
                for(Item item: purchase.getInventory()){
                    display.purchaseList(item);
                }
                display.breakLine();
                display.salesTax(calculate.getTotalTax());
                display.salesTotal(calculate.getTotalSale());
            }
        }
    }
}