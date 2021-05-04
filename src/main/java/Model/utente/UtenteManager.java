package Model.utente;

import Model.storage.Manager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UtenteManager extends Manager implements UtenteDao{

    private final UtenteQuery QUERY = new UtenteQuery("Utente");

    public UtenteManager(DataSource source){
        super(source);
    }

    @Override
    public ArrayList<Utente> fetchAccount(int start, int end) throws SQLException { //limit
        try(Connection conn = source.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement(QUERY.selectUtenti())){
                ps.setInt(1,start);
                ps.setInt(2,end);
                ResultSet rs = ps.executeQuery();
                ArrayList<Utente> utenti = new ArrayList<>();
                while(rs.next()){
                    Utente u = new Utente();
                    u.setId(rs.getInt(1));
                    u.setNome(rs.getString(2));
                    u.setCognome(rs.getString(3));
                    u.setCitta(rs.getString(4));
                    u.setVia(rs.getString(5));
                    u.setNumeroCivico(rs.getInt(6));
                    u.setEmail(rs.getString(7));
                    u.setDataNa(rs.getString(8));
                    u.setAdmin(rs.getBoolean(9));
                    u.setPassword(rs.getString(10));
                    utenti.add(u);
                }
                rs.close();
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
        try(Connection conn = source.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement(QUERY.insertUtente())){
                ps.setString(2,utente.getNome());
                ps.setString(3,utente.getNome());
                ps.setString(7,utente.getEmail());
                ps.setBoolean(8,utente.isAdmin());
                ps.setString(10,utente.getPassword());
                return ps.executeUpdate();
            }
        }
    }

    @Override
    public Integer eliminaAccount(String email) throws SQLException {
        return null;
    }

    @Override
    public Integer modificaAccount(Utente utente) throws SQLException {
        return null;
    }
}
