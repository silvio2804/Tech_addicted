package Model.product;

import Model.search.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProdottoDao <E extends Exception>{

    ArrayList<Product> fetchProdotti(int start, int end) throws E;

    Optional<Product> fetchProdotto(int id)  throws E;

    boolean creaProdotto(Product product) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaProdotto(int idProd) throws E;

    boolean modificaProdotto(Product product) throws E;

    ArrayList<Product> fetchProdottiUtente(int idUtente) throws E;

    List<Product> search(List<Condition> conditions) throws E;
}
