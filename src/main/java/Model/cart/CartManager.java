package Model.cart;

import Model.order.Order;
import Model.order.OrderExtractor;
import Model.storage.Manager;
import Model.storage.QueryBuilder;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartManager extends Manager implements CartDao {

    public CartManager(DataSource source) throws SQLException{
        super(source);
    }
}
