package Model.cart;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartExtractor implements ResultSetExtractor<Cart> {
    @Override
    public Cart extract(ResultSet rs) throws SQLException {
        return  null;
    }
}
