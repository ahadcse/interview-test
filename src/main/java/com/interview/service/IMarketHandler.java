package com.interview.service;

import com.interview.model.market.Product;

import java.util.List;

public interface IMarketHandler {

    boolean register(String name, String passwd);
    boolean unregister(String name);
    boolean login(String username, String password);
    List<Product> getProduct();
    List<Product> getChoice();
    Product buyProduct(String buyer, String owner, String itemName, float price);
    boolean sellProduct(String seller, String itemName, float price);
    boolean adChoice(String buyer, String itemName, float price);
}