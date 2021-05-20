package Model.ordine;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrdineExtractor implements ResultSetExtractor<Ordine> {
    @Override
    public Ordine extract(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Ordine ordine = new Ordine();
            ordine.setIdOrdine(rs.getInt(1));
            ordine.setPagamento(rs.getString(2));
            ordine.setTotale(rs.getDouble(3));
            ordine.setDataOrdine(LocalDate.parse(rs.getString(4)));
            return ordine;
        }
        return null;
    }
}

