package Model.order;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderExtractor implements ResultSetExtractor<Order> {
    @Override
    public Order extract(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Order order = new Order();
            order.setIdOrdine(rs.getInt(1));
            order.setPagamento(rs.getString(2));
            order.setTotale(rs.getDouble(3));
            order.setDataOrdine(LocalDate.parse(rs.getString(4)));
            return order;
        }
        return null;
    }
}

