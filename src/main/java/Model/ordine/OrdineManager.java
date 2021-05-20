package Model.ordine;

import Model.carrello.Carrello;
import Model.carrello.CarItem;
import Model.categoria.Categoria;
import Model.categoria.CategoriaExtractor;
import Model.prodotto.Prodotto;
import Model.prodotto.ProdottoExtractor;
import Model.search.Paginator;
import Model.storage.Manager;
import Model.storage.QueryBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class OrdineManager extends Manager implements OrdineDao{

    protected OrdineManager(DataSource source) {
        super(source);
    }

    @Override
    public ArrayList<Ordine> fetchOrdini(int start, int end) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine","ord");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, start);
                ps.setInt(2, end);
                ResultSet rs = ps.executeQuery();
                OrdineExtractor ex = new OrdineExtractor();
                ArrayList<Ordine> ordini = new ArrayList<>();
                while (rs.next()) {
                    Ordine ordine= ex.extract(rs);
                    ordini.add(ordine);
                }
                return ordini;
            }
        }
    }

    @Override
    public Optional<Ordine> fetchOrdine(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            String query = QueryOrdine.fetchOrdini();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Ordine ordine = null;
                if (rs.next()) {
                    ordine = new OrdineExtractor().extract(rs);
                }
                //oppure Ordine ordine = set.next() ? new OrdineExtractor().extract(resultset) : null
                return Optional.ofNullable(ordine); //restituisce un oggetto che incapsula null
            }
        }
    }

    public ArrayList<Ordine> fetchOrdiniConProdotti(int id, Paginator paginator) throws SQLException{
        try (Connection conn = source.getConnection()){
            String query = QueryOrdine.fetchOrdiniConProdotti(id);
            try(PreparedStatement ps = conn.prepareStatement(query)){
                ps.setInt(1,id);
                ps.setInt(2,paginator.getOffset());
                ps.setInt(3,paginator.getLimit());
                ResultSet resultSet = ps.executeQuery();
                Map<Integer,Ordine> ordineMap = new LinkedHashMap<>();
                OrdineExtractor ordineExtractor = new OrdineExtractor();
                ProdottoExtractor prodottoExtractor = new ProdottoExtractor();
                CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
                while(resultSet.next()){
                    int idOrdine = resultSet.getInt("ord.idOrdine");
                    if(!ordineMap.containsKey(idOrdine)){
                        Ordine ordine = ordineExtractor.extract(resultSet);
                        ordine.setCarrello(new Carrello(new ArrayList<>()));
                        ordineMap.put(idOrdine,ordine);
                    }
                    Prodotto prodotto = prodottoExtractor.extract(resultSet);
                    Categoria categoria = categoriaExtractor.extract(resultSet);
                    prodotto.setCategoria(categoria);
                    ordineMap.get(idOrdine).getCarrello().addProdotto(prodotto,resultSet.getInt("proInCar.quantita"));
                }
                return  new ArrayList<>(ordineMap.values());
            }
        }
    }

    @Override
    public boolean creaOrdine(Ordine ordine) throws SQLException {
        try (Connection conn = source.getConnection()) {
            conn.setAutoCommit(false);
            String query = QueryOrdine.createOrdine();
            String query2 = QueryOrdine.insertCar();
            try(
                    PreparedStatement ps = conn.prepareStatement(query);
                    PreparedStatement psAssoc = conn.prepareStatement(query2);
                    ){
                int rows = ps.executeUpdate();
                int total = rows;
                for(CarItem item : ordine.getCarrello().getItems()){
                    psAssoc.setInt(1,item.getProdotto().getIdProdotto());
                    psAssoc.setInt(2,ordine.getIdOrdine());
                    psAssoc.setInt(3,item.getQuantity());
                    total += psAssoc.executeUpdate();
                }
                if(total == (rows + ordine.entries())){
                    conn.commit();
                    conn.setAutoCommit(true);
                    return true;
                }
                else{
                    conn.rollback();
                    conn.setAutoCommit(true);
                    return false;
                }
            }
        }
    }

    @Override
    public boolean eliminaOrdine(int idOrdine) throws SQLException {
        try (Connection conn = source.getConnection()) {
            String query = QueryOrdine.deleteOrdine();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idOrdine);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean modificaOrdine(Ordine ordine) throws SQLException {
        try (Connection conn = source.getConnection()) {
            String query = QueryOrdine.updateOrdine();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }
}
