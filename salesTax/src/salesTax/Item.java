package salesTax;

import java.util.regex.Pattern;

public class Item {
    private int quantity;
    private String itemDetails;
    private Double price;
    private Boolean isItemImported = false;
    private Boolean isItemExempted = false;
    private Double afterTax;

    public Item(int qty, String details, Double price){
        this.quantity = qty;
        this.itemDetails = details;
        this.price = price;
        setSaleType(details);
    }

    public int getQuantity(){
        return this.quantity;
    }

    public String getDetails(){
        return this.itemDetails;
    }

    public Double getPrice(){
        return this.price;
    }

    public Boolean isExempt(){
        return this.isItemExempted;
    }

    public Boolean isImport(){
        return this.isItemImported;
    }

    public Double setAfterTax(Double amount){
        return this.afterTax = amount;
    }

    public Double getAfterTax(){
        return this.afterTax;
    }

    private void setSaleType(String details){
        Pattern exemptPattern = Pattern.compile("pills|chocolate|book|wine");
        Pattern importPattern = Pattern.compile("imported");
        if (exemptPattern.matcher(details).find()) {
            this.isItemExempted = true;
        }

        if (importPattern.matcher(details).find()) {
            this.isItemImported = true;
        }
    }
}