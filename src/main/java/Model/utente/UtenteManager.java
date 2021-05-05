package Model.utente;

import Model.storage.Manager;
import Model.storage.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UtenteManager extends Manager implements UtenteDao {

    private final UtenteQuery QUERY = new UtenteQuery("Utente");

    public UtenteManager(DataSource source) {
        super(source);
    }

    @Override
    public ArrayList<Utente> fetchAccount(int start, int end) throws SQLException { //limit
        try (Connection conn = source.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(QUERY.selectUtenti())) {
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
    public Optional<Utente> fetchAccount(String email) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Integer creaAccount(Utente utente) throws SQLException {
        try (Connection conn = source.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(QUERY.insertUtente())) {
                ps.setString(1, utente.getEmail());
                ps.setString(2, utente.getNome());
                ps.setString(3, utente.getCognome());
                ps.setString(4, utente.getPassword());
                ps.setBoolean(5, utente.isAdmin());
                return ps.executeUpdate();
            }
        }
    }

    @Override
    public Integer eliminaAccount(String email) throws SQLException {
        if (email == null)
            return null;
        try (Connection conn = source.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(QUERY.deleteUtente())) {
                ps.setString(1, email);
                return ps.executeUpdate();
            }
        }
    }

    @Override
    public Integer modificaAccount(Utente utente) throws SQLException {
        if (utente == null)
            return null;
        try (Connection conn = source.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(QUERY.updateUtente())) {
                ps.setString(1, utente.getNome());
                ps.setString(2, utente.getCognome());
                ps.setString(3, utente.getEmail());
                return ps.executeUpdate();
            }
        }
    }
}

