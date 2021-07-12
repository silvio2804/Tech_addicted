package Model.cart;

import Model.account.Account;
import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartExtractor implements ResultSetExtractor<Cart> {
    @Override
    public Cart extract(ResultSet rs) throws SQLException {
        Cart cart = new Cart(new ArrayList<>());
        cart.setIdCart(rs.getInt(1));
        int id = rs.getInt(2);
        Account account = new Account();
        account.setId(id);
        cart.setAccount(account);
        //cart.setDatePurchase(rs.getString(3));
        return cart;
    }
}
