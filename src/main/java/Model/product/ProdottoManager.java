package Model.product;

import Model.storage.Manager;
import Model.storage.QueryBuilder;
import Model.account.Account;

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
    public ArrayList<Product> fetchProdotti(int start, int end) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, start);
                ps.setInt(2, end);
                ResultSet rs = ps.executeQuery();
                ProdottoExtractor ex = new ProdottoExtractor();
                ArrayList<Product> prodotti = new ArrayList<>();
                while (rs.next()) {
                    Product p = ex.extract(rs);
                    prodotti.add(p);
                }
                return prodotti;
            }
        }
    }

    @Override
    public Optional<Product> fetchProdotto(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Account utente = null;
                Product p = null;
                if (rs.next()) {
                    p = new ProdottoExtractor().extract(rs);
                }
                return Optional.ofNullable(p); //restituisce un oggetto che incapsula null
            }
        }
    }

    @Override
    public boolean creaProdotto(Product product) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            queryBuilder.insert("idProd", "nome", "descrizione", "prezzo", "immagine");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, product.getIdProdotto());
                ps.setString(2, product.getNome());
                ps.setString(3, product.getDescrizione());
                ps.setDouble(4, product.getPrezzo());
                ps.setString(5, product.getImmagine());
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
    public boolean modificaProdotto(Product product) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("utente", "ute");
            queryBuilder.update("").where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(3, product.getIdProdotto());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }
    public ArrayList<Product> fetchProdottiUtente(int idUtente) throws SQLException{
        try (Connection conn = source.getConnection()){
            QueryBuilder queryBuilder = new QueryBuilder("ProdottiInCarrello","proCar");
            queryBuilder.select();
        }
        ArrayList<Product> prodotti = new ArrayList<>();
        return  prodotti;
    }


    /*SELECT proCar.*
    from prodottiInCarrello as proCar, utente as ute, carrello as car
    where ute.id=3 &&
        ute.id=car.idUtente &&
        proCar.idCarrello=car.idCar;*/
}
