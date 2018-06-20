package com.example.gaominyu.slease;

public class ListingClass {
    private String itemName;
    private String itemDescription;
    private String category;
    private String[] images;
    private double deposit;
    private double ratePerDay;


    public void setItemName(String itemName){this.itemName = itemName;}

    public void setItemDescription(String itemDescription){this.itemDescription = itemDescription;}

    public void setCategory(String category){this.category = category;}

    public void setImages(String[] images){this.images = images;}

    public void setDeposit(double deposit){this.deposit = deposit;}

    public void setRatePerDay(double ratePerDay){this.ratePerDay = ratePerDay;}

    public String getItemName(){return itemName;}

    public String getItemDescription(){return itemDescription;}

    public String getCategory(){return category;}

    public String[] setImages(){return images;}

    public double getDeposit(){return deposit;}

    public double getRatePerDay(){return ratePerDay;}

}
