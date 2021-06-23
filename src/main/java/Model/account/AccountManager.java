package Model.account;

import Model.search.Paginator;
import Model.storage.Manager;
import Model.storage.QueryBuilder;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class AccountManager extends Manager implements AccountDao { // E' il mio DAO

    private AccountManager accountManager;


    public AccountManager(DataSource source)throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Account> fetchAccounts(Paginator paginator) throws SQLException { //limit
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente","ute");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, paginator.getLimit());
                ps.setInt(2, paginator.getOffset());
                ResultSet rs = ps.executeQuery();
                AccountExtractor ex = new AccountExtractor();
                ArrayList<Account> utenti = new ArrayList<>();
                while (rs.next()) {
                    Account u = ex.extract(rs);
                    utenti.add(u);
                }
                return utenti;
            }
        }
    }


    @Override
    public Optional<Account> fetchAccount(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Account utente = null;
                if (rs.next()) {
                    utente = new AccountExtractor().extract(rs);
                }
                return Optional.ofNullable(utente); //restituisce un oggetto che incapsula null
            }
        }
    }

    @Override
    public boolean creaAccount(Account utente) throws SQLException {
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
    public boolean modificaAccount(Account utente) throws SQLException {
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

    public Optional<Account> findAccount(String email, String password, boolean admin) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            queryBuilder.select().where("email=?").andCondition("password=?").andCondition("admin=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setBoolean(3,admin);
                ResultSet rs = ps.executeQuery();
                Account account = null;
                if (rs.next())
                    account = new AccountExtractor().extract(rs);
                return Optional.ofNullable(account);
            }
        }
    }
}

