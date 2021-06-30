package Model.category;

import Model.product.Product;
import Model.product.ProductExtractor;
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
import java.util.Optional;

public class CategoryManager extends Manager implements CategoryDao {

    protected CategoryManager(DataSource source) throws SQLException {
        super(source);
    }

    @Override
    public ArrayList<Category> fetchCategories(Paginator paginator) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("categoria", "cat");
            String query = queryBuilder.select().limit(true).generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, paginator.getOffset());
                ps.setInt(2, paginator.getLimit());
                ResultSet rs = ps.executeQuery();
                CategoryExtractor ex = new CategoryExtractor();
                ArrayList<Category> categories = new ArrayList<>();
                while (rs.next()) {
                    Category cat = ex.extract(rs);
                    categories.add(cat);
                }
                return categories;
            }
        }
    }

    @Override
    public Optional<Category> fetchCategory(int id) throws Exception {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("categoria", "cat");
            String query = queryBuilder.select().where("id=?").generateQuery();
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Category cat = null;
                if (rs.next()) {
                    cat = new CategoryExtractor().extract(rs);
                }
                return Optional.ofNullable(cat); //restituisce un oggetto che incapsula null
            }
        }
    }

    @Override
    public boolean createCategory(Category cat) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("categoria", "cat");
            queryBuilder.insert("idCategoria,nomeCategoria");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, cat.getCategoryId());
                ps.setString(2, cat.getCategoryName());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean deleteCategory(int id) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("categoria", "cat");
            queryBuilder.delete().where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, id);
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public boolean updateCategory(Category cat) throws SQLException {
        try (Connection conn = source.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("categoria", "cat");
            queryBuilder.update("").where("id=?");
            try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, cat.getCategoryId());
                int updRet = ps.executeUpdate();
                return updRet == 1;
            }
        }
    }

    @Override
    public Optional<Category> fetchCategoryWithProducts(int catId) throws SQLException { //dammi tutti i prodotti che appartengono a una categoria
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
                    cat.setProducts(new ArrayList<>());
                    ProductExtractor productExtractor = new ProductExtractor();
                    cat.getProducts().add(productExtractor.extract(rs));
                    while(rs.next())
                        cat.getProducts().add(productExtractor.extract(rs));
                }
                return Optional.ofNullable(cat);
            }
        }
    }

    public Optional<Category> fetchProductWithCategory(int catId) throws SQLException { //dammi tutti i prodotti che appartengono a una categoria
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
                    cat.setProducts(new ArrayList<>());
                    ProductExtractor productExtractor = new ProductExtractor();
                    cat.getProducts().add(productExtractor.extract(rs));//aggiungo il primo prodotto, a causa del while
                    while(rs.next()){
                        cat.getProducts().add(productExtractor.extract(rs));
                    }
                }
                return Optional.ofNullable(cat);
            }
        }
    }


}
