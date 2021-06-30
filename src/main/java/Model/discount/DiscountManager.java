package Model.discount;

import Model.search.Paginator;
import Model.storage.Manager;
import Model.storage.QueryBuilder;
import Model.tag.Tag;
import Model.tag.TagExtractor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class DiscountManager extends Manager implements DiscountDao {

    protected DiscountManager(DataSource source)throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Discount> fetchDiscounts(Paginator paginator) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("sconto","sco");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1,paginator.getOffset());
                ps.setInt(2,paginator.getLimit());
                ResultSet rs = ps.executeQuery();
                DiscountExtractor ex = new DiscountExtractor();
                ArrayList<Discount> sconti = new ArrayList<>();
                while (rs.next()) {
                    Discount discount = ex.extract(rs);
                    sconti.add(discount);
                }
                return sconti;
            }
        }
    }

    @Override
    public Optional<Discount> fetchDiscount(int id) throws Exception {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("sconto", "sco");
            String query = queryBuilder.select().where("id=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Discount discount= null;
                if (rs.next()) {
                    discount = new DiscountExtractor().extract(rs);
                }
                return Optional.ofNullable(discount); //restituisce un oggetto che incapsula null
            }
        }
    }

    @Override
    public boolean createDiscount(Discount discount) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("sconto","sco");
            queryBuilder.insert("idSconto","percentuale");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, discount.getDiscountId());
                ps.setInt(2, discount.getPercentage());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean deleteDiscount(int idSconto) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente","ute");
            queryBuilder.delete().where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1,idSconto);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean updateDiscount(Discount discount) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente","ute");
            queryBuilder.update("percentuale").where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, discount.getPercentage());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }
}
