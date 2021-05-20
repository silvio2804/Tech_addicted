package Model.categoria;

import Model.storage.ResultSetExtractor;
import Model.utente.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaExtractor implements ResultSetExtractor<Categoria> {
    public Categoria extract(ResultSet rs) throws SQLException {
        Categoria cat = new Categoria();
        cat.setNomeCategoria(rs.getString(1));
        return cat;
    }
}
