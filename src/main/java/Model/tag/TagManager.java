package Model.tag;

import Model.storage.Manager;
import Model.storage.QueryBuilder;
import Model.utente.Utente;
import Model.utente.UtenteExtractor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class TagManager extends Manager implements TagDao {

    protected TagManager(DataSource source) {
        super(source);
    }

    public ArrayList<Tag> fetchTags() throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("tag","tag");
            queryBuilder.select();
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ResultSet rs = ps.executeQuery();
                TagExtractor ex = new TagExtractor();
                ArrayList<Tag> tags = new ArrayList<>();
                while (rs.next()) {
                    Tag tag = ex.extract(rs);
                    tags.add(tag);
                }
                return tags;
            }
        }
    }

    @Override
    public Optional<Tag> fetchTag(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean creaTag(int id, String tag) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("tag","tag");
            queryBuilder.insert("idTag,parola");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1,id);
                ps.setString(2,tag);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean eliminaTag(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("tag","tag");
            queryBuilder.delete().where("idTag=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1,id);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }
}
