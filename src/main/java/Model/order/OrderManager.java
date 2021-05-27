package Model.order;

import Model.cart.Cart;
import Model.cart.CarItem;
import Model.category.Category;
import Model.category.CategoryExtractor;
import Model.product.Product;
import Model.product.ProdottoExtractor;
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

public class OrderManager extends Manager implements OrderDao {


    protected OrderManager(DataSource source) throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Order> fetchOrdini(int start, int end) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine","ord");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, start);
                ps.setInt(2, end);
                ResultSet rs = ps.executeQuery();
                OrderExtractor ex = new OrderExtractor();
                ArrayList<Order> ordini = new ArrayList<>();
                while (rs.next()) {
                    Order order = ex.extract(rs);
                    ordini.add(order);
                }
                return ordini;
            }
        }
    }

    @Override
    public Optional<Order> fetchOrdine(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            String query = QueryOrder.fetchOrdini();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Order order = null;
                if (rs.next()) {
                    order = new OrderExtractor().extract(rs);
                }
                //oppure Ordine ordine = set.next() ? new OrdineExtractor().extract(resultset) : null
                return Optional.ofNullable(order); //restituisce un oggetto che incapsula null
            }
        }
    }

    public ArrayList<Order> fetchOrdiniConProdotti(int id, Paginator paginator) throws SQLException{
        try (Connection conn = source.getConnection()){
            String query = QueryOrder.fetchOrdiniConProdotti(id);
            try(PreparedStatement ps = conn.prepareStatement(query)){
                ps.setInt(1,id);
                ps.setInt(2,paginator.getOffset());
                ps.setInt(3,paginator.getLimit());
                ResultSet resultSet = ps.executeQuery();
                Map<Integer, Order> ordineMap = new LinkedHashMap<>();
                OrderExtractor orderExtractor = new OrderExtractor();
                ProdottoExtractor prodottoExtractor = new ProdottoExtractor();
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                while(resultSet.next()){
                    int idOrdine = resultSet.getInt("ord.idOrdine");
                    if(!ordineMap.containsKey(idOrdine)){
                        Order order = orderExtractor.extract(resultSet);
                        order.setCarrello(new Cart(new ArrayList<>()));
                        ordineMap.put(idOrdine, order);
                    }
                    Product product = prodottoExtractor.extract(resultSet);
                    Category category = categoryExtractor.extract(resultSet);
                    product.setCategoria(category);
                    ordineMap.get(idOrdine).getCarrello().addProdotto(product,resultSet.getInt("proInCar.quantita"));
                }
                return  new ArrayList<>(ordineMap.values());
            }
        }
    }

    @Override
    public boolean creaOrdine(Order order) throws SQLException {
        try (Connection conn = source.getConnection()) {
            conn.setAutoCommit(false);
            String query = QueryOrder.createOrdine();
            String query2 = QueryOrder.insertCar();
            try(
                    PreparedStatement ps = conn.prepareStatement(query);
                    PreparedStatement psAssoc = conn.prepareStatement(query2);
                    ){
                int rows = ps.executeUpdate();
                int total = rows;
                for(CarItem item : order.getCarrello().getItems()){
                    psAssoc.setInt(1,item.getProdotto().getIdProdotto());
                    psAssoc.setInt(2, order.getIdOrdine());
                    psAssoc.setInt(3,item.getQuantity());
                    total += psAssoc.executeUpdate();
                }
                if(total == (rows + order.entries())){
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
            String query = QueryOrder.deleteOrdine();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idOrdine);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean modificaOrdine(Order order) throws SQLException {
        try (Connection conn = source.getConnection()) {
            String query = QueryOrder.updateOrdine();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }
}
