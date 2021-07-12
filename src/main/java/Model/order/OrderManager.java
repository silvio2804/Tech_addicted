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

    public OrderManager(DataSource source) throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Order> fetchOrders(Paginator paginator) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "ord");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, paginator.getOffset());
                ps.setInt(2, paginator.getLimit());
                ResultSet rs = ps.executeQuery();
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
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "ord");
            String query = queryBuilder.select().where("idOrd=?").generateQuery();
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

    public ArrayList<Order> fetchOrdersWithProduct(int id, Paginator paginator) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine","ord");
            String query = queryBuilder.select().innerJoin("carrello","car").on("car.idCar = ord.idCar")
                    .innerJoin("prodottiInCarrello","proInCar").on("car.idCar=proInCar.idCarrello")
                    .innerJoin("prodotto","pro").on("proInCar.idProdotto=pro.idProd")
                    .where("car.idUtente=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.setInt(2, paginator.getLimit());
                ps.setInt(3, paginator.getOffset());
                ResultSet resultSet = ps.executeQuery();
                Map<Integer, Order> ordineMap = new LinkedHashMap<>();
                OrderExtractor orderExtractor = new OrderExtractor();
                ProductExtractor productExtractor = new ProductExtractor();
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                while (resultSet.next()) {
                    int idOrdine = resultSet.getInt("ord.idOrd");
                    if (!ordineMap.containsKey(idOrdine)) {
                        Order order = orderExtractor.extract(resultSet);
                        order.setCarrello(new Cart(new ArrayList<>()));
                        ordineMap.put(idOrdine, order);
                    }
                    Product product = productExtractor.extract(resultSet);
                    Category category = categoryExtractor.extract(resultSet);
                    product.setCategory(category);
                    ordineMap.get(idOrdine).getCarrello().addProduct(product, resultSet.getInt("proInCar.quantita"));
                }
                return new ArrayList<>(ordineMap.values());
            }
        }
    }

    @Override
    public boolean createOrder(Order order) throws SQLException {
        try (Connection conn = source.getConnection()) {
            conn.setAutoCommit(false);

            QueryBuilder qb = new QueryBuilder("ordine","ord");
            QueryBuilder qb1 = new QueryBuilder("prodottiInCarrello","proInCar");

            String query1 =  qb.insert("totale").generateQuery();
            String query2 = qb1.insert("idProdotto","idCarrello","quantita").generateQuery();

            try (
                    PreparedStatement ps = conn.prepareStatement(query1);
                    PreparedStatement psAssoc = conn.prepareStatement(query2);
            ) {
                int rows = ps.executeUpdate();
                int total = rows;
                for (CarItem item : order.getCarrello().getItems()) {
                    psAssoc.setInt(1, item.getProduct().getProductId());
                    psAssoc.setInt(2, order.getOrderId());
                    psAssoc.setInt(3, item.getQuantity());
                    total += psAssoc.executeUpdate();
                }
                if (total == (rows + order.entries())) {
                    conn.commit();
                    conn.setAutoCommit(true);
                    return true;
                } else {
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
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "ord");
            queryBuilder.delete().where("idOrder=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, idOrdine);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean updateOrder(Order order) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine","ord");
            String query = queryBuilder.update("pagamento","dataOrdine").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1,order.getPayment());
                ps.setObject(2,order.getOrderDate());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    public int countAll() throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "ord");
            queryBuilder.count("allOrders");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                    return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public double totalReveneus() throws SQLException {
        double total = 0;
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "ord");
            String query = queryBuilder.select().generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                OrderExtractor ex = new OrderExtractor();
                ArrayList<Order> orders = new ArrayList<>();
                while (rs.next()) {
                    Order order = ex.extract(rs);
                    orders.add(order);
                }
                for (Order order : orders)
                    total += order.getTotal();
                return total;
            }
        }
    }

    public boolean createOrderProva(Order order) throws SQLException {
        try (Connection conn = source.getConnection()) {
            conn.setAutoCommit(false);
            //prendo i prodotti nel carrello
            //devo aggiornare i campi
            QueryBuilder qb = new QueryBuilder("ordine","ord");
            qb.select().innerJoin("prodottiincarrello","proInCar")
                    .on("ord.idCar = proInCar.idCar").innerJoin("prodotto","pro")
                    .on("pro.idProd = proInCar.idProdotto").generateQuery();
            QueryBuilder qbTotal = new QueryBuilder("ordine","ord");
            String queryTotal = qbTotal.insert("totale","dataOrdine","pagamento").generateQuery();
            String query2 ="";
            try (
                    PreparedStatement ps = conn.prepareStatement(queryTotal);
                    PreparedStatement psAssoc = conn.prepareStatement(query2);
            ) {
                int rows = ps.executeUpdate();
                int total = rows;
                for (CarItem item : order.getCarrello().getItems()) {
                    psAssoc.setInt(1, item.getProduct().getProductId());
                    psAssoc.setInt(2, order.getOrderId());
                    psAssoc.setInt(3, item.getQuantity());
                    total += psAssoc.executeUpdate();
                }
                if (total == (rows + order.entries())) {
                    conn.commit();
                    conn.setAutoCommit(true);
                    return true;
                } else {
                    conn.rollback();
                    conn.setAutoCommit(true);
                    return false;
                }
            }
        }
    }
}
