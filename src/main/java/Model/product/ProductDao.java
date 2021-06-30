package Model.product;

import Model.search.Condition;
import Model.search.Paginator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductDao<E extends Exception>{

    ArrayList<Product> fetchProducts(Paginator paginator) throws E;

    Optional<Product> fetchProduct(int id)  throws E;

    boolean createProduct(Product product) throws E ; //mettiamo boolean al posto di integer

    boolean deleteProduct(int idProd) throws E;

    boolean updateProduct(Product product) throws E;

    ArrayList<Product> fetchProductAccount(int idUtente) throws E;

    List<Product> search(List <Condition> conditions) throws E;
}
