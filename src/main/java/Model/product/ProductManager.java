package Model.product;

import Model.category.Category;
import Model.category.CategoryExtractor;
import Model.category.CategoryManager;
import Model.search.Condition;
import Model.search.Operator;
import Model.search.Paginator;
import Model.storage.Manager;
import Model.storage.QueryBuilder;
import Model.account.Account;
import Model.tag.Tag;

import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager extends Manager implements ProductDao<SQLException> {

    public ProductManager(DataSource source) throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Product> fetchProducts(Paginator paginator) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, paginator.getOffset());
                ps.setInt(2, paginator.getLimit());
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
            String query = queryBuilder.select("*").innerJoin("categoria", "cat")
                    .on("pro.idCategoria = cat.idCat").where("idProd=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Product product = null;
                while (rs.next()) {
                    product = new ProductExtractor().extract(rs);
                    CategoryManager categoryManager = new CategoryManager(source);
                    Optional<Category> category = categoryManager.fetchCategory(rs.getInt(6));
                    product.setCategory(category.get());
                }
                return Optional.ofNullable(product);
            }
        }
    }

    @Override
    public boolean createProduct(Product product) throws SQLException {

        try (Connection conn = source.getConnection()) {
            conn.setAutoCommit(false);
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
            queryBuilder.delete().where("idProd=?");
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
            String query = queryBuilder.update("nome", "descrizione", "immagine",
                    "prezzo", "idCategoria").where("idProd=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setString(3, product.getCover());
                ps.setDouble(4, product.getPrice());
                ps.setString(5, product.getCategory().getCategoryName());
                ps.setInt(6, product.getProductId());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public ArrayList<Product> fetchProductAccount(int idUtente) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ProdottiInCarrello", "proCar");
            queryBuilder.select();
        }
        ArrayList<Product> prodotti = new ArrayList<>();
        return prodotti;
    }


    @Override
    public List<Product> search(List<Condition> conditions) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select("*").innerJoin("categoria", "cat")
                    .on("pro.idCategoria = cat.idCat").generateQuery();
            System.out.println(query);
            if (!conditions.isEmpty()) {
                queryBuilder.where("").search(conditions);
            }
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
                Product product = null;
                while (rs.next()) {
                    product = new ProductExtractor().extract(rs);
                    CategoryManager categoryManager = new CategoryManager(source);
                    Optional<Category> category = categoryManager.fetchCategory(rs.getInt(6));
                    product.setCategory(category.get());
                    products.add(product);
                }
                return products;
            }
        }
    }

    @Override
    public ArrayList<Category> fetchCategoriesByProducts() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("categoria", "cat");
            String query = queryBuilder.selectDistinct("nomeCategoria", "idCat").outerJoin(true, "prodotto", "pro").
                    on("(cat.idCat = pro.idCategoria)").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                CategoryExtractor extractor = new CategoryExtractor();
                while (rs.next()) {
                    Category category = extractor.extract(rs);
                    categories.add(category);
                }
                return categories;
            }
        }
    }

    @Override
    public int countAll() throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            queryBuilder.count("allProducts");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                    return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public ArrayList<Product> fetchProductsByCategory(Category category) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select().where("idCategoria=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, category.getCategoryId());
                ResultSet rs = ps.executeQuery();
                ProductExtractor productExtractor = new ProductExtractor();
                while (rs.next()) {
                    Product product = productExtractor.extract(rs);
                    products.add(product);
                }
                return products;
            }
        }
    }

    @Override
    public ArrayList<Product> fetchProductsByTag(Tag tag) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("prodotto", "pro");
            String query = queryBuilder.select("*").innerJoin("tagprodotti", "tag")
                    .on("pro.idProd = tag.idProdotto").where("idTag=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, tag.getTagId());
                ResultSet rs = ps.executeQuery();
                ProductExtractor productExtractor = new ProductExtractor();
                while (rs.next()) {
                    Product product = productExtractor.extract(rs);
                    products.add(product);
                }
                return products;
            }
        }
    }
}


