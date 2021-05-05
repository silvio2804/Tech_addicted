package Model.utente;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteExtractor implements ResultSetExtractor<Utente> {
    @Override
    public Utente extract(ResultSet rs) throws SQLException {
        Utente u= new Utente();
        u.setId(rs.getInt(1));
        u.setNome(rs.getString(2));
        u.setCognome(rs.getString(3));
        u.setDataNa(rs.getString(4));
        u.setEmail(rs.getString(5));
        u.setPassword(rs.getString(6));
        u.setSesso(rs.getString(7));
        u.setAdmin(rs.getBoolean(8));
        u.setCitta(rs.getString(9));
        u.setVia(rs.getString(10));
        u.setNumeroCivico(rs.getInt(11));
        return u;
    }
}
