package Model.cart;

import Model.account.Account;
import Model.account.AccountExtractor;
import Model.account.AccountSession;
import Model.category.Category;
import Model.category.CategoryExtractor;
import Model.order.Order;
import Model.order.OrderExtractor;
import Model.product.Product;
import Model.product.ProductExtractor;
import Model.storage.Manager;
import Model.storage.QueryBuilder;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class CartManager extends Manager implements CartDao {

    public CartManager (DataSource source) throws SQLException{
        super(source);
    }



    public boolean createCart(Cart cart) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("carrello", "car");
            queryBuilder.insert("idCar","idUtente");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, cart.getIdCart());
                ps.setInt(2, cart.getAccount().getId());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

  /*  public boolean insertInCart(Cart cart,Product product)throws  SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodottiincarrello", "proInCar");
            String query = queryBuilder.insert("proInCar","proInCar.idProdotto").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idCar);
                ResultSet rs = ps.executeQuery();
                Cart cart = new Cart(new ArrayList<>());
                if (rs.next()) {
                    cart = new CartExtractor().extract(rs);
                }
            }
        }
    }
        return false;
    }*/

    @Override
    public Cart fetchCart(AccountSession account) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder =new QueryBuilder("carrello", "car");
            String query = queryBuilder.select().where("idUtente=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, account.getId());
                ResultSet rs = ps.executeQuery();
                CartExtractor cartExtractor = new CartExtractor();
                Cart cart = null;
                while (rs.next()) {
                    cart = cartExtractor.extract(rs);
                }
                return cart;
            }
        }
    }

    @Override
    public ArrayList<Product> fetchProductsByCart(Cart cart) throws SQLException {
        try (Connection conn = source.getConnection()) {

            QueryBuilder queryBuilder = new QueryBuilder("prodottiincarrello","proInCar");
            String query = queryBuilder.select().innerJoin("prodotto","pro").on("pro.idProd=proInCar.idProdotto").
                    where("proInCar.idCarrello=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
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
                //return new ArrayList<>(ordineMap.values());
                return null;
            }
        }
    }

    /*select proInCar.idCarrello, pro.idProd, pro.nome, pro.descrizione, pro.immagine, pro.prezzo, cat.nomeCategoria
    from prodottiInCarrello proInCar inner join prodotto pro on(proInCar.idProdotto = pro.idProd)
    inner join categoria cat on (pro.idCategoria = cat.idCat)
    where proInCar.idCarrello = 30;*/
    //prelevo il carrello con i prodotti
    @Override
    public Cart fetchCartWithProduct(int idCar) throws SQLException {
        try (Connection conn = source.getConnection()) {
            //prelevo tutti i prodotti del carrello
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select( "idProd", "nome", "immagine", "prezzo").innerJoin("prodottiincarrello","proInCar")
                    .on("proInCar.idProdotto=pro.idProd")
                    .where("proInCar.idCarrello=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idCar);
                ResultSet rs = ps.executeQuery();
                Cart cart = new Cart(new ArrayList<>());
                if (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt(1));
                    product.setName(rs.getString(2));
                    product.setCover(rs.getString(3));
                    product.setPrice(rs.getDouble(4));
                    cart = new CartExtractor().extract(rs);
                    cart.addProduct(product,2);
                }
                return cart;
            }
        }
    }
/*
    public Cart fetchCartWithProducts(int idCar) throws SQLException {
        try (Connection conn = source.getConnection()) {
            //prelevo tutti i prodotti del carrello
            QueryBuilder queryBuilder = new QueryBuilder("carrello", "car");
            String query = queryBuilder.select().where("idCar=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idCar);
                ResultSet rs = ps.executeQuery();
                Cart cart = new Cart(new ArrayList<>());
                if (rs.next()) {
                    cart = new CartExtractor().extract(rs);
                }
                return cart;
            }
        }
    }*/

}
