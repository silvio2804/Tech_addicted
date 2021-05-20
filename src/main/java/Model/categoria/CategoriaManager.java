package Model.categoria;

import Model.prodotto.ProdottoExtractor;
import Model.storage.Manager;
import Model.storage.QueryBuilder;
import Model.utente.Utente;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CategoriaManager extends Manager implements CategoriaDao {

    protected CategoriaManager(DataSource source) {
        super(source);
    }

    @Override
    public ArrayList<Categoria> fetchCategorie(int start, int end) throws Exception {
        return null;
    }

    @Override
    public Optional<Utente> fetchCategoria(int id) throws Exception {
        return Optional.empty();
    }

    @Override
    public boolean creaCategoria(Categoria cat) throws Exception {
        return false;
    }

    @Override
    public boolean eliminaCategoria(int id) throws Exception {
        return false;
    }

    @Override
    public boolean modifica(Categoria cat) throws Exception {
        return false;
    }

    @Override
    public Optional<Categoria> fetchCategoriaConProdotti(int catId) throws SQLException { //dammi tutti i prodotti che appartengono a una categoria
        try(Connection conn = source.getConnection()){
            QueryBuilder queryBuilder = new QueryBuilder("categoria","cat");
            queryBuilder.select().innerJoin("prodotto","pro").on("cat.id=pro.idCategoria").where("cat.id=?");
        try(PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())){
            ps.setInt(1,catId);
            ResultSet rs = ps.executeQuery();
            CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
            Categoria cat = null;
            if(rs.next()){
                cat = categoriaExtractor.extract(rs);
                cat.setProdotti(new ArrayList<>());
                ProdottoExtractor prodottoExtractor = new ProdottoExtractor();
                cat.getProdotti().add(prodottoExtractor.extract(rs));
                while(rs.next()){
                    cat.getProdotti().add(prodottoExtractor.extract(rs));
                }
            }
            return Optional.ofNullable(cat);
        }
        }
    }


}
