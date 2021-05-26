package Model.prodotto;

import Model.storage.Manager;
import Model.storage.QueryBuilder;
import Model.utente.Utente;
import Model.utente.UtenteExtractor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ProdottoManager extends Manager implements ProdottoDao {

    protected ProdottoManager(DataSource source) throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Prodotto> fetchProdotti(int start, int end) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, start);
                ps.setInt(2, end);
                ResultSet rs = ps.executeQuery();
                ProdottoExtractor ex = new ProdottoExtractor();
                ArrayList<Prodotto> prodotti = new ArrayList<>();
                while (rs.next()) {
                    Prodotto p = ex.extract(rs);
                    prodotti.add(p);
                }
                return prodotti;
            }
        }
    }

    @Override
    public Optional<Prodotto> fetchProdotto(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Utente utente = null;
                Prodotto p = null;
                if (rs.next()) {
                    p = new ProdottoExtractor().extract(rs);
                }
                return Optional.ofNullable(p); //restituisce un oggetto che incapsula null
            }
        }
    }

    @Override
    public boolean creaProdotto(Prodotto prodotto) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            queryBuilder.insert("idProd", "nome", "descrizione", "prezzo", "immagine");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, prodotto.getIdProdotto());
                ps.setString(2, prodotto.getNome());
                ps.setString(3, prodotto.getDescrizione());
                ps.setDouble(4, prodotto.getPrezzo());
                ps.setString(5, prodotto.getImmagine());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean eliminaProdotto(int idProd) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            queryBuilder.delete().where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, idProd);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean modificaProdotto(Prodotto prodotto) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            queryBuilder.update("").where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(3, prodotto.getIdProdotto());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }
    public ArrayList<Prodotto> fetchProdottiUtente(int idUtente) throws SQLException{
        try (Connection conn = source.getConnection()){
            QueryBuilder queryBuilder = new QueryBuilder("ProdottiInCarrello","proCar");
            queryBuilder.select();
        }
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        return  prodotti;
    }


    /*SELECT proCar.*
    from prodottiInCarrello as proCar, utente as ute, carrello as car
    where ute.id=3 &&
        ute.id=car.idUtente &&
        proCar.idCarrello=car.idCar;*/
}
