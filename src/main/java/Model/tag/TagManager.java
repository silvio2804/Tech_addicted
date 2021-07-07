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
            String query = queryBuilder.select().where("idTag=?").generateQuery();
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
    public boolean createTag(Tag tag) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("tag","tag");
            queryBuilder.insert("idTag","parola");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1,tag.getTagId());
                ps.setString(2,tag.getWord());
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

    @Override
    public boolean updateTag(Tag tag) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("tag","tag");
            queryBuilder.update("word").where("idTag=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,tag.getWord());
                ps.setInt(2, tag.getTagId());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    /*public ArrayList<TagProdotto> SearchTagAssociatedAtProduct(String NomeProdotto, Paginator paginator) throws SQLException {
        //SELECT * from tag, associationtag where tag.idTag=associationTag.idtag where associationtag.idProdotto=?
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT * from tag,associationtag,prodotto where " +
                    "tag.idTag=associationtag.IdTag AND " +
                    "associationtag.IdProduct=prodotto.idProduct AND " +
                    "prodotto.nome=? limit ? offset ?")) {
                ps.setString(1, NomeProdotto);
                ps.setInt(2, paginator.getLimit());
                ps.setInt(3, paginator.getOffset());
                ResultSet set = ps.executeQuery();
                Map<Integer, TagProdotto> AssociazioneTagProdotti = new LinkedHashMap<>();
                TagExtractor tagExtractor = new TagExtractor();
                ProdottoExtractor prodottoExtractor = new ProdottoExtractor();
                CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
                while (set.next()) {
                    int IdTag = set.getInt("Tag.idTag");
                    if (!AssociazioneTagProdotti.containsKey(IdTag)) {
                        TagProdotto tagProdotto = tagExtractor.extract(set);
                        tagProdotto.setListOfProduct(new ArrayList<>());
                        AssociazioneTagProdotti.put(IdTag, tagProdotto);
                    }
                    Prodotto prodotto = prodottoExtractor.extract(set);
                    Categoria categoria = categoriaExtractor.extract(set);
                    prodotto.setNomeCategoria(categoria);
                    AssociazioneTagProdotti.get(IdTag).getListOfProduct().add(prodotto);
                }
                return new ArrayList<>(AssociazioneTagProdotti.values());
            }
        }
    }*/
}
