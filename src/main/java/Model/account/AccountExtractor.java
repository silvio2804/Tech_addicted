package Model.account;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class AccountExtractor implements ResultSetExtractor<Account> {
    @Override
    public Account extract(ResultSet rs) throws SQLException {
        Account u= new Account();
        u.setId(rs.getInt(1));
        u.setName(rs.getString(2));
        u.setLastName(rs.getString(3));
        u.setDate(rs.getDate(4));
        u.setEmail(rs.getString(5));
        u.setGender(rs.getString(7));
        u.setAdmin(rs.getBoolean(8));
        u.setCity(rs.getString(9));
        u.setStreet(rs.getString(10));
        u.setHouseNumber(rs.getInt(11));
        return u;
    }
}
