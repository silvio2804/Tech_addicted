package Model.ordine;

import Model.search.Paginator;
import Model.utente.Utente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface OrdineDao <E extends SQLException> {

    ArrayList<Ordine> fetchOrdini(int start, int end) throws E;

    Optional<Ordine> fetchOrdine(int id)  throws E;

    boolean creaOrdine(Ordine ordine) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaOrdine(int idOrdine) throws E;

    boolean modificaOrdine(Ordine ordine) throws E;

    ArrayList<Ordine> fetchOrdiniConProdotti(int id, Paginator paginator) throws E;
}
