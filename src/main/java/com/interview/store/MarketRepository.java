package com.interview.store;

import com.interview.exceptions.DatabaseErrorException;
import com.interview.model.market.Product;
import com.interview.service.ICustomerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MarketRepository implements DataProvider {

    private static Logger LOGGER = LoggerFactory.getLogger(MarketRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

 /*   @Autowired
    public MarketRepository() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        //setDataSource(dataSource);
    }*/

    /*private String marketName = "Sweden";
    private Map<String, ICustomerHandler> customer = new HashMap<>();
    private Map<String, Product> ProductList = new HashMap<>();
    private Map<String, Product> ChoiceList = new HashMap<>();
    private static final long serialVersionUID = 7526472295622776147L;*/
    //Bank bankObj;
    ICustomerHandler custObj;
    //private Connection connect;
    //private String name;

    /*public Market(String name)  {
        super();
        this.marketName = name;
        connectDatabase();
    }*/

    @Override
    public boolean register(String name, String passwd) {
        String REGISTER_SQL = "INSERT INTO CUSTOMER (CUSTNAME, CUSTPASS, TOTITEMSSOLD, TOTITEMSBOUGHT) VALUES (?, ?, ?, ?)";
        try {
            int register = jdbcTemplate.update(REGISTER_SQL, name, passwd, 0, 0);
            if(register == 1) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception exception) {
            LOGGER.error("Database error: " + exception.getMessage());
            throw new DatabaseErrorException("Database error", exception);
        }
    }

    @Override
    public boolean unregister(String name) {
        String UNREGISTER_SQL = "DELETE FROM CUSTOMER WHERE CUSTNAME = ?";
        try {
            int unregister = jdbcTemplate.update(UNREGISTER_SQL, name);
            if(unregister == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            LOGGER.error("Database error: " + exception.getMessage());
            throw new DatabaseErrorException("Database error", exception);
        }
    }

    private boolean updateDatabase(String sql) {
        try {
            int update = jdbcTemplate.update(sql);
            if (update == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            LOGGER.error("Database error: " + exception.getMessage());
            throw new DatabaseErrorException("Database error", exception);
        }
    }

    @Override
    public boolean login(String uname, String password) {
        boolean validUser = false;
        String LOGIN_SQL = "SELECT * from CUSTOMER WHERE CUSTNAME = ?";
        try {
            List<String> passwords = jdbcTemplate.query(LOGIN_SQL,
                    new Object[]{uname},
                    (resultSet, i) -> {
                        String passwd = resultSet.getString("CUSTPASS");
                        return passwd;
                    });
            for (String pass : passwords) {
                if (password.equals(pass)) {
                    validUser = true;
                } else {
                    validUser = false;
                }
            }
        } catch (Exception exception) {
            LOGGER.error("Database error: " + exception.getMessage());
            throw new DatabaseErrorException("Database error", exception);
        }
        return validUser;
    }

    @Override
    public List<Product> getProduct() {
        int index = 0;
        String GET_PRODUCT_SQL = "SELECT * FROM ITEMLIST";
        return provideProductList(GET_PRODUCT_SQL);
    }

    @Override
    public List<Product> getChoice() {
        int index = 0;
        String GET_WISH_SQL = "SELECT * FROM WISHLIST";
        return provideProductList(GET_WISH_SQL);
    }

    private List<Product> provideProductList(String GET_PRODUCT_SQL) {
        List<Product> productList = null;
        try {
            productList = jdbcTemplate.query(GET_PRODUCT_SQL, new Object[]{},
                    ((resultSet, i) -> {
                        Product product = new Product();
                        product.setOwner(resultSet.getString("ITEMOWNER"));
                        product.setProductName(resultSet.getString("ITEMNAME"));
                        product.setPrice(resultSet.getDouble("ITEMPRICE"));
                        product.setAmount(resultSet.getInt("ITEMAMOUNT"));
                        return product;
                    }));

        } catch (Exception exception) {
            LOGGER.error("Database error: " + exception.getMessage());
            throw new DatabaseErrorException("Database error", exception);
        }
        return productList;
    }


    @Override
    public boolean adChoice(String buyer, String itemName, float price) {
        String AD_CHOICE_SQL = "INSERT INTO WISHLIST (ITEMNAME, ITEMPRICE, ITEMOWNER, ITEMAMOUNT) VALUES (:itemname, :price, :buyer, :amount)";
        return updateDatabase(AD_CHOICE_SQL);
    }
}
