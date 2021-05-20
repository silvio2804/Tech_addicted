package Model.sconto;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScontoExtractor implements ResultSetExtractor <Sconto> {
    @Override
    public Sconto extract(ResultSet rs) throws SQLException {
        if(rs.next()){
            Sconto sc = new Sconto();
            sc.setIdSconto(rs.getInt(1));
            sc.setPercentuale((rs.getInt(2)));
            return sc;
        }
        return null;
    }
}
