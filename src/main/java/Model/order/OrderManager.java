package Model.order;

import Model.cart.Cart;
import Model.cart.CarItem;
import Model.category.Category;
import Model.category.CategoryExtractor;
import Model.product.Product;
import Model.product.ProductExtractor;
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
    public ArrayList<Order> fetchOrders(Paginator paginator) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine","ord");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1,paginator.getOffset());
                ps.setInt(2,paginator.getLimit());
                ResultSet rs = ps.executeQuery();
                System.out.println(query);
                ArrayList<Order> ordini = new ArrayList<>();
                while (rs.next()) {
                    Order order = new OrderExtractor().extract(rs);
                    ordini.add(order);
                }
                return ordini;
            }
        }
    }

    @Override
    public Optional<Order> fetchOrder(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine","ord");
            String query = queryBuilder.select().where("id=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Order order = null;
                if (rs.next()) {
                    order = new OrderExtractor().extract(rs);
                }
                return Optional.ofNullable(order); //restituisce un oggetto che incapsula null
            }
        }
    }

    public ArrayList<Order> fethOrdersWithProduct(int id, Paginator paginator) throws SQLException{
        try (Connection conn = source.getConnection()){
            String query = QueryOrder.fetchOrdiniConProdotti(id);
            try(PreparedStatement ps = conn.prepareStatement(query)){
                ps.setInt(1,id);
                ps.setInt(2,paginator.getLimit());
                ps.setInt(3,paginator.getOffset());
                ResultSet resultSet = ps.executeQuery();
                Map<Integer, Order> ordineMap = new LinkedHashMap<>();
                OrderExtractor orderExtractor = new OrderExtractor();
                ProductExtractor productExtractor = new ProductExtractor();
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                while(resultSet.next()){
                    int idOrdine = resultSet.getInt("ord.idOrdine");
                    if(!ordineMap.containsKey(idOrdine)){
                        Order order = orderExtractor.extract(resultSet);
                        order.setCarrello(new Cart(new ArrayList<>()));
                        ordineMap.put(idOrdine, order);
                    }
                    Product product = productExtractor.extract(resultSet);
                    Category category = categoryExtractor.extract(resultSet);
                    product.setCategory(category);
                    ordineMap.get(idOrdine).getCarrello().addProdotto(product,resultSet.getInt("proInCar.quantita"));
                }
                return  new ArrayList<>(ordineMap.values());
            }
        }
    }

    @Override
    public boolean createOrder(Order order) throws SQLException {
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
                    psAssoc.setInt(1,item.getProduct().getProductId());
                    psAssoc.setInt(2, order.getOrderId());
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
    public boolean deleteOrder(int idOrdine) throws SQLException {
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
    public boolean updateOrder(Order order) throws SQLException {
        try (Connection conn = source.getConnection()) {
            String query = QueryOrder.updateOrdine();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

}
