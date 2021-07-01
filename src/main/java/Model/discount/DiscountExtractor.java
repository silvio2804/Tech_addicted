package Model.discount;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountExtractor implements ResultSetExtractor <Discount> {
    @Override
    public Discount extract(ResultSet rs) throws SQLException {
        Discount sc = new Discount();
        sc.setDiscountId(rs.getInt(1));
        sc.setDiscountName(rs.getString(2));
        sc.setPercentage(rs.getInt(3));
        return sc;
    }
}
