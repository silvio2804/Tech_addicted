package Model.order;

import Model.account.Account;
import Model.account.AccountManager;
import Model.cart.CarItem;
import Model.cart.Cart;
import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class OrderExtractor implements ResultSetExtractor<Order> {
    @Override
    public Order extract(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt(1));
        order.setPayment(rs.getString(2));
        order.setTotal(rs.getDouble(3));
        order.setOrderDate(rs.getDate(4));
        int idAccount = rs.getInt(5);
        Account account = new Account();
        account.setId(idAccount);
        order.setAccount(account);
        Cart cart = new Cart(new ArrayList<CarItem>());
        cart.setIdCart(rs.getInt(6));
        order.setCarrello(cart);
        return order;
    }
}

