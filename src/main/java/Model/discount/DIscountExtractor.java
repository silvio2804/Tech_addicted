package Model.discount;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DIscountExtractor implements ResultSetExtractor <Discount> {
    @Override
    public Discount extract(ResultSet rs) throws SQLException {
        if(rs.next()){
            Discount sc = new Discount();
            sc.setIdSconto(rs.getInt(1));
            sc.setPercentuale((rs.getInt(2)));
            return sc;
        }
        return null;
    }
}
