package Model.product;

import java.util.ArrayList;
import java.util.Optional;

public interface ProdottoDao <E extends Exception>{

    ArrayList<Product> fetchProdotti(int start, int end) throws E;

    Optional<Product> fetchProdotto(int id)  throws E;

    boolean creaProdotto(Product product) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaProdotto(int idProd) throws E;

    boolean modificaProdotto(Product product) throws E;

    ArrayList<Product> fetchProdottiUtente(int idUtente) throws E;
}
