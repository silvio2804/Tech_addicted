package Model.category;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryExtractor implements ResultSetExtractor<Category> {
    public Category extract(ResultSet rs) throws SQLException {
        Category cat = new Category();
        cat.setCategoryId(rs.getInt(1));
        cat.setCategoryName(rs.getString(2));
        return cat;
    }
}
