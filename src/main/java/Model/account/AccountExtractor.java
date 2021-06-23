package Model.account;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AccountExtractor implements ResultSetExtractor<Account> {
    @Override
    public Account extract(ResultSet rs) throws SQLException {
        Account u= new Account();
        u.setId(rs.getInt(1));
        u.setNome(rs.getString(2));
        u.setCognome(rs.getString(3));
        u.setDataNa(LocalDate.parse(rs.getString(4)));
        u.setEmail(rs.getString(5));
        u.setSesso(rs.getString(7));
        u.setAdmin(rs.getBoolean(8));
        u.setCitta(rs.getString(9));
        u.setVia(rs.getString(10));
        u.setNumeroCivico(rs.getInt(11));
        return u;
    }
}
