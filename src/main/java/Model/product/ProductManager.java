package Model.product;

import Model.category.CategoryExtractor;
import Model.search.Condition;
import Model.search.Operator;
import Model.search.Paginator;
import Model.storage.Manager;
import Model.storage.QueryBuilder;
import Model.account.Account;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager extends Manager implements ProductDao<SQLException> {

    protected ProductManager(DataSource source)throws SQLException{
        super(source);
    }

    @Override
    public ArrayList<Product> fetchProducts(Paginator paginator) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1,paginator.getOffset());
                ps.setInt(2,paginator.getLimit());
                ResultSet rs = ps.executeQuery();
                ProductExtractor ex = new ProductExtractor();
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
    public Optional<Product> fetchProduct(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Product p = null;
                if (rs.next()) {
                    p = new ProductExtractor().extract(rs);
                }
                return Optional.ofNullable(p); //restituisce un oggetto che incapsula null
            }
        }
    }

    @Override
    public boolean createProduct(Product product) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            queryBuilder.insert("idProd", "nome", "descrizione", "prezzo", "immagine", "idCategoria");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, product.getProductId());
                ps.setString(2, product.getName());
                ps.setString(3, product.getDescription());
                ps.setDouble(4, product.getPrice());
                ps.setString(5, product.getCover());
                ps.setInt(6, product.getCategory().getCategoryId());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean deleteProduct(int idProd) throws SQLException {
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
    public boolean updateProduct(Product product) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            queryBuilder.update("").where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, product.getProductId());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    public ArrayList<Product> fetchProductAccount(int idUtente) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ProdottiInCarrello", "proCar");
            queryBuilder.select();
        }
        ArrayList<Product> prodotti = new ArrayList<>();
        return prodotti;
    }


    @Override
    public List<Product> search(List <Condition> conditions) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            queryBuilder.select().innerJoin("categoria", "cat").on("pro.idCateria = cat.idCat");
            //altre join se necessarie
            if (!conditions.isEmpty()) {
                queryBuilder.where("").search(conditions);
            }
            String query = queryBuilder.generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                for (int i = 0; i < conditions.size(); i++) {
                    if (conditions.get(i).getOperator() == Operator.MATCH) {
                        ps.setObject(i + 1, "%" + conditions.get(i).getValue() + "%");//qui eseguo la concatenazione
                    } else {
                        ps.setObject(i + 1, conditions.get(i).getValue());
                    }
                }
                ResultSet rs = ps.executeQuery();
                List<Product> products = new ArrayList<>();
                while (rs.next()) {
                    Product product = new ProductExtractor().extract(rs);
                    product.setCategory(new CategoryExtractor().extract(rs));
                    products.add(product);
                }
                return products;
            }
        }
    }
}
