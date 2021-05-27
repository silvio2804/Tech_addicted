package Model.discount;

import Model.storage.Manager;
import Model.storage.QueryBuilder;

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
    public ArrayList<Discount> fetchSconti() throws Exception {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("sconto","sco");
            queryBuilder.select();
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ResultSet rs = ps.executeQuery();
                DIscountExtractor ex = new DIscountExtractor();
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
    public Optional<Discount> fetchSconto(int id) throws Exception {
        return Optional.empty();
    }

    @Override
    public boolean creaSconto(Discount discount) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("tag","tag");
            queryBuilder.insert("idSconto","percentuale");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, discount.getIdSconto());
                ps.setInt(2, discount.getPercentuale());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean eliminaSconto(int idSconto) throws SQLException {
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
    public boolean modificaSconto(Discount discount) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente","ute");
            queryBuilder.update("percentuale").where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, discount.getPercentuale());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }
}
