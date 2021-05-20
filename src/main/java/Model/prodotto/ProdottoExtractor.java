package Model.prodotto;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdottoExtractor implements ResultSetExtractor<Prodotto> {

    public Prodotto extract(ResultSet rs) throws SQLException {
        Prodotto p = new Prodotto();
        p.setIdProdotto(rs.getInt(1));
        p.setNome(rs.getString(2));
        p.setDescrizione(rs.getString(3));
        p.setImmagine(rs.getString(4));
        p.setPrezzo(rs.getDouble(5));
        return p;

    }
}
