package Model.sconto;

import Model.storage.Manager;
import Model.storage.QueryBuilder;
import Model.tag.Tag;
import Model.tag.TagExtractor;
import Model.utente.Utente;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ScontoManager extends Manager implements ScontoDao {

    protected ScontoManager(DataSource source)throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Sconto> fetchSconti() throws Exception {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("sconto","sco");
            queryBuilder.select();
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ResultSet rs = ps.executeQuery();
                ScontoExtractor ex = new ScontoExtractor();
                ArrayList<Sconto> sconti = new ArrayList<>();
                while (rs.next()) {
                    Sconto sconto = ex.extract(rs);
                    sconti.add(sconto);
                }
                return sconti;
            }
        }
    }

    @Override
    public Optional<Sconto> fetchSconto(int id) throws Exception {
        return Optional.empty();
    }

    @Override
    public boolean creaSconto(Sconto sconto) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("tag","tag");
            queryBuilder.insert("idSconto","percentuale");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1,sconto.getIdSconto());
                ps.setInt(2,sconto.getPercentuale());
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
    public boolean modificaSconto(Sconto sconto) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente","ute");
            queryBuilder.update("percentuale").where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1,sconto.getPercentuale());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }
}
