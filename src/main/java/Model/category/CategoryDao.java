package Model.category;

import Model.account.Account;

import java.util.ArrayList;
import java.util.Optional;

public interface CategoryDao<E extends Exception>{

    ArrayList<Category> fetchCategorie(int start, int end) throws E;

    Optional<Account> fetchCategoria(int id)  throws E;

    boolean creaCategoria(Category cat) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaCategoria(int id) throws E;

    boolean modifica(Category cat) throws E;

    Optional<Category> fetchCategoriaConProdotti(int catId) throws E;
}
