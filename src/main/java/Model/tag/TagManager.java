package Model.tag;

import Model.product.Product;
import Model.product.ProductExtractor;
import Model.search.Paginator;
import Model.storage.Manager;
import Model.storage.QueryBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class TagManager extends Manager implements TagDao {

    public TagManager(DataSource source) throws SQLException {
        super(source);
    }

    public ArrayList<Tag> fetchTags(Paginator paginator) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("tag","tag");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1,paginator.getOffset());
                ps.setInt(2,paginator.getLimit());
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
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("tag", "tag");
            String query = queryBuilder.select().where("id=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Tag tag= null;
                if (rs.next()) {
                    tag = new TagExtractor().extract(rs);
                }
                return Optional.ofNullable(tag); //restituisce un oggetto che incapsula null
            }
        }
    }

    @Override
    public boolean createTag(int id, String tag) throws SQLException {
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
    public boolean deleteTag(int id) throws SQLException {
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
