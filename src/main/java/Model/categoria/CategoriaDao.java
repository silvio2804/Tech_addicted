package Model.categoria;

import Model.utente.Utente;

import java.util.ArrayList;
import java.util.Optional;

public interface CategoriaDao <E extends Exception>{

    ArrayList<Categoria> fetchCategorie(int start, int end) throws E;

    Optional<Utente> fetchCategoria(int id)  throws E;

    boolean creaCategoria(Categoria cat) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaCategoria(int id) throws E;

    boolean modifica(Categoria cat) throws E;

    Optional<Categoria> fetchCategoriaConProdotti(int catId) throws E;
}
