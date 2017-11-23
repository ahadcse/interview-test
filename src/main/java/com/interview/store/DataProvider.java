package com.interview.store;

import com.interview.model.market.Product;

import java.util.List;

public interface DataProvider {
    boolean register(String name, String passwd);
    boolean unregister(String name);
    boolean login(String uname, String password);
    List<Product> getProduct();
    List<Product> getChoice();
    boolean adChoice(String buyer, String itemName, float price);
}
