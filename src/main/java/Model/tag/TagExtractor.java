package Model.tag;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagExtractor implements ResultSetExtractor <Tag> {
    @Override
    public Tag extract(ResultSet rs) throws SQLException {
        if(rs.next()){
            Tag tag = new Tag();
            tag.setTagId(rs.getInt(1));
            tag.setWord(rs.getString(2));
            return tag;
        }
        return null;
    }
}
