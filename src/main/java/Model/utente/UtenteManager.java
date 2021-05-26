package Model.utente;

import Model.storage.Manager;
import Model.storage.QueryBuilder;
import Model.storage.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UtenteManager extends Manager implements UtenteDao { // E' il mio DAO

    private final UtenteQuery QUERY = new UtenteQuery("Utente");

    public UtenteManager(DataSource source)throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Utente> fetchAccounts(int start, int end) throws SQLException { //limit
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente","ute");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, start);
                ps.setInt(2, end);
                ResultSet rs = ps.executeQuery();
                UtenteExtractor ex = new UtenteExtractor();
                ArrayList<Utente> utenti = new ArrayList<>();
                while (rs.next()) {
                    Utente u = ex.extract(rs);
                    utenti.add(u);
                }
                return utenti;
            }
        }
    }


    @Override
    public Optional<Utente> fetchAccount(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Utente utente = null;
                if (rs.next()) {
                    utente = new UtenteExtractor().extract(rs);
                }
                return Optional.ofNullable(utente); //restituisce un oggetto che incapsula null
            }
        }
    }

    @Override
    public boolean creaAccount(Utente utente) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente","ute");
            String admin =""+utente.isAdmin();
            queryBuilder.insert("email","password","nome","cognome","adm");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,utente.getEmail());
                ps.setString(2,utente.getPassword());
                ps.setString(3,utente.getNome());
                ps.setString(4,utente.getCognome());
                ps.setBoolean(5,utente.isAdmin());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean eliminaAccount(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente","ute");
            queryBuilder.delete().where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1,id);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean modificaAccount(Utente utente) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente","ute");
            queryBuilder.update("nome","cognome").where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, utente.getNome());
                ps.setString(2, utente.getCognome());
                ps.setInt(3,utente.getId());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }


}

