package Model.order;

import Model.search.Paginator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface OrderDao<E extends SQLException> {

    ArrayList<Order> fetchOrdini(int start, int end) throws E;

    Optional<Order> fetchOrdine(int id)  throws E;

    boolean creaOrdine(Order order) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaOrdine(int idOrdine) throws E;

    boolean modificaOrdine(Order order) throws E;

    ArrayList<Order> fetchOrdiniConProdotti(int id, Paginator paginator) throws E;
}
