package Model.storage;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extract result set in to a Bean
 * @param <B> object to converte
 */

public interface ResultSetExtractor<B> {

    /**Extract info from a ResultSet
     *
     * @param rs the resultSet
     * @return the bean
     * @throws SQLException
     */

    B extract(ResultSet rs) throws SQLException;

}
