package com.interview.service;

import com.interview.model.market.Product;
import com.interview.store.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketHandlerImpl implements IMarketHandler {

    private final DataProvider dataProvider;

    @Autowired
    public MarketHandlerImpl(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public boolean register(String name, String passwd) {
        return dataProvider.register(name, passwd);
    }

    @Override
    public boolean unregister(String name) {
        return dataProvider.unregister(name);

    }

    @Override
    public boolean login(String username, String password) {
        return dataProvider.login(username, password);

    }

    @Override
    public List<Product> getProduct() {
        return dataProvider.getProduct();
    }

    @Override
    public List<Product> getChoice() {
        return dataProvider.getChoice();
    }

    @Override
    public boolean adChoice(String buyer, String itemName, float price) {
        boolean adChoice = false;
        try {
            adChoice = dataProvider.adChoice(buyer, itemName, price);
        } catch (Exception e) {
            System.out.println(e);
        }
        return adChoice;
    }

    @Override
    public Product buyProduct(String buyer, String owner, String itemName, float price) {
        return null;
    }

    @Override
    public boolean sellProduct(String seller, String itemName, float price) {
        return false;
    }
}
