package Model.product;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdottoExtractor implements ResultSetExtractor<Product> {

    public Product extract(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setIdProdotto(rs.getInt(1));
        p.setNome(rs.getString(2));
        p.setDescrizione(rs.getString(3));
        p.setImmagine(rs.getString(4));
        p.setPrezzo(rs.getDouble(5));
        return p;

    }
}
