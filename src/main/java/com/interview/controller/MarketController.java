package com.interview.controller;

import com.interview.model.market.Product;
import com.interview.service.IMarketHandler;
import com.interview.service.MarketHandlerImpl;
import com.interview.store.DataProvider;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {
    private static Logger LOGGER = LoggerFactory.getLogger(MarketController.class);

    @Autowired
    private final DataProvider dataProvider;

    public MarketController(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @ApiOperation(value = "Register to Market")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(@RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "passwd", required = true) String passwd) {
        LOGGER.info("register controller started...");
        IMarketHandler marketHandler = new MarketHandlerImpl(dataProvider);
        boolean register = marketHandler.register(name, passwd);
        return register;
    }

    @ApiOperation(value = "Unregister to Market")
    @RequestMapping(value = "/unregister", method = RequestMethod.POST)
    public boolean register(@RequestParam(value = "name", required = true) String name) {
        LOGGER.info("unregister controller started...");
        IMarketHandler marketHandler = new MarketHandlerImpl(dataProvider);
        boolean unregister = marketHandler.unregister(name);
        return unregister;
    }

    @ApiOperation(value = "Login to Market")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public boolean login(@RequestParam(value = "username", required = true) String username,
                            @RequestParam(value = "password", required = true) String password) {
        LOGGER.info("login controller started...");
        IMarketHandler marketHandler = new MarketHandlerImpl(dataProvider);
        boolean login = marketHandler.login(username, password);
        return login;
    }

    @ApiOperation(value = "Get all the product in market")
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<Product> getProduct() {
        LOGGER.info("product controller started...");
        IMarketHandler marketHandler = new MarketHandlerImpl(dataProvider);
        List<Product> products = marketHandler.getProduct();
        return products;
    }
}