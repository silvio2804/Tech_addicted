package Model.prodotto;

import Model.sconto.Sconto;

import java.util.ArrayList;
import java.util.Optional;

public interface ProdottoDao <E extends Exception>{

    ArrayList<Prodotto> fetchProdotti(int start,int end) throws E;

    Optional<Prodotto> fetchProdotto(int id)  throws E;

    boolean creaProdotto(Prodotto prodotto) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaProdotto(int idProd) throws E;

    boolean modificaProdotto(Prodotto prodotto) throws E;

    ArrayList<Prodotto> fetchProdottiUtente(int idUtente) throws E;
}
