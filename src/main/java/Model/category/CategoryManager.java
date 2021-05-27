package Model.category;

import Model.product.ProdottoExtractor;
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

public class CategoryManager extends Manager implements CategoryDao {

    protected CategoryManager(DataSource source) throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Category> fetchCategorie(int start, int end) throws Exception {
        return null;
    }

    @Override
    public Optional<Account> fetchCategoria(int id) throws Exception {
        return Optional.empty();
    }

    @Override
    public boolean creaCategoria(Category cat) throws Exception {
        return false;
    }

    @Override
    public boolean eliminaCategoria(int id) throws Exception {
        return false;
    }

    @Override
    public boolean modifica(Category cat) throws Exception {
        return false;
    }

    @Override
    public Optional<Category> fetchCategoriaConProdotti(int catId) throws SQLException { //dammi tutti i prodotti che appartengono a una categoria
        try(Connection conn = source.getConnection()){
            QueryBuilder queryBuilder = new QueryBuilder("categoria","cat");
            queryBuilder.select().innerJoin("prodotto","pro").on("cat.id=pro.idCategoria").where("cat.id=?");
            try(PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())){
                ps.setInt(1,catId);
                ResultSet rs = ps.executeQuery();
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                Category cat = null;
                if(rs.next()){
                    cat = categoryExtractor.extract(rs);
                    cat.setProdotti(new ArrayList<>());
                    ProdottoExtractor prodottoExtractor = new ProdottoExtractor();
                    cat.getProdotti().add(prodottoExtractor.extract(rs));
                    while(rs.next())
                        cat.getProdotti().add(prodottoExtractor.extract(rs));
                }
                return Optional.ofNullable(cat);
            }
        }
    }

    public Optional<Category> fetchCategoriaConProdotto(int catId) throws SQLException { //dammi tutti i prodotti che appartengono a una categoria
        try(Connection conn = source.getConnection()){
            QueryBuilder queryBuilder = new QueryBuilder("prodotto","pro");
            queryBuilder.select().innerJoin("categoria","cat").on("cat.idCat=pro.idCategoria").where("cat.idCat=?");
            try(PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())){
                System.out.println(queryBuilder.generateQuery());
                ps.setInt(1,catId);
                ResultSet rs = ps.executeQuery();
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                Category cat = null;
                if(rs.next()){
                    cat = categoryExtractor.extract(rs);
                    cat.setProdotti(new ArrayList<>());
                    ProdottoExtractor prodottoExtractor = new ProdottoExtractor();
                    cat.getProdotti().add(prodottoExtractor.extract(rs));//aggiungo il primo prodotto, a causa del while
                    while(rs.next()){
                        cat.getProdotti().add(prodottoExtractor.extract(rs));
                    }
                }
                return Optional.ofNullable(cat);
            }
        }
    }


}
