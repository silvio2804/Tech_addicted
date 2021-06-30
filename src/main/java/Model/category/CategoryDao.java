package Model.category;

import Model.account.Account;
import Model.search.Paginator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface CategoryDao<E extends Exception>{

    ArrayList<Category> fetchCategories(Paginator paginator) throws E;

    Optional<Account> fetchCategory(int id)  throws E;

    boolean createCategory(Category cat) throws E ; //mettiamo boolean al posto di integer

    boolean deleteCategory(int id) throws E;

    boolean updateCategory(Category cat) throws E;

    Optional<Category> fetchCategoryWithProducts(int catId) throws E;

    Optional<Category> fetchProductWithCategory(int catId) throws E;

    }
