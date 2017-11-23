package com.interview.model.market;

import java.io.Serializable;

/**
 * @author ahad
 */
public class Product implements Serializable {

    private String owner;
    private String productName;
    private double price;
    private int amount;

    public Product() {
        this.owner = "";
        this.productName = "";
        this.price = 0;
        this.amount = 0;
    }

    public Product(String owner, String productName, double price, int amount) {

        this.owner = owner;
        this.productName = productName;
        this.price = price;
        this.amount = amount;
    }

    public void setOwner(String owner) {

        this.owner = owner;
    }

    public String getOwner() {

        return owner;
    }

    public String getProductName() {

        return productName;
    }

    public double getProductPrice() {

        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }
}