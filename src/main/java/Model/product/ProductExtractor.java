package Model.product;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExtractor implements ResultSetExtractor<Product> {

    public Product extract(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductId(rs.getInt(1));
        p.setName(rs.getString(2));
        p.setDescription(rs.getString(3));
        p.setCover(rs.getString(4));
        p.setPrice(rs.getDouble(5));

        return p;
    }
}
