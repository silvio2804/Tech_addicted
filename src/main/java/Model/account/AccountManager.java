package Model.account;

import Model.search.Paginator;
import Model.storage.Manager;
import Model.storage.QueryBuilder;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class AccountManager extends Manager implements AccountDao {

    public AccountManager(DataSource source) throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Account> fetchAccounts(Paginator paginator) throws SQLException { //limit
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, paginator.getOffset());
                ps.setInt(2, paginator.getLimit());
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
            String query = queryBuilder.select().where("idUte=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Account utente = null;
                if (rs.next()) {
                    utente = new AccountExtractor().extract(rs);
                }
                return Optional.ofNullable(utente);
            }
        }
    }

    @Override
    public boolean createAccount(Account utente) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            queryBuilder.insert("email", "nome", "cognome", "adm","dataNascita","citta","via","numeroCivico","sesso","passw");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, utente.getEmail());
                ps.setString(2, utente.getName());
                ps.setString(3, utente.getLastName());
                ps.setBoolean(4, utente.isAdmin());
                ps.setObject(5,utente.getDate());
                ps.setString(6,utente.getCity());
                ps.setString(7,utente.getStreet());
                ps.setInt(8,utente.getHouseNumber());
                ps.setString(9,utente.getGender());
                ps.setString(10,utente.getPassword());
                System.out.println(utente);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean deleteAccount(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            queryBuilder.delete().where("idUte=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, id);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean updateAccount(Account utente) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            queryBuilder.update("email", "passw", "nome", "cognome", "adm","dataNascita","citta","via","numeroCivico").where("idUte=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, utente.getEmail());
                ps.setString(2, utente.getPassword());
                ps.setString(3, utente.getName());
                ps.setString(4, utente.getLastName());
                ps.setBoolean(5, utente.isAdmin());
                ps.setObject(6,utente.getDate());
                ps.setString(7,utente.getCity());
                ps.setString(8,utente.getStreet());
                ps.setInt(9,utente.getHouseNumber());
                ps.setInt(10,utente.getId());
                System.out.println(utente);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public Optional<Account> findAccount(String email, String password) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            queryBuilder.select().where("email=?").andCondition("passw=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                Account account = null;
                if (rs.next())
                    account = new AccountExtractor().extract(rs);
                return Optional.ofNullable(account);
            }
        }
    }

    @Override
    public int countAll() throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            queryBuilder.count("allAccounts");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                    return rs.getInt(1);
            }
        }
        return 0;
    }
}

