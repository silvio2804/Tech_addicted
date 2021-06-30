package Model.order;

import Model.search.Paginator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface OrderDao<E extends SQLException> {

    ArrayList<Order> fetchOrders(Paginator paginator) throws E;

    Optional<Order> fetchOrder(int id)  throws E;

    boolean createOrder(Order order) throws E ; //mettiamo boolean al posto di integer

    boolean deleteOrder(int idOrdine) throws E;

    boolean updateOrder(Order order) throws E;

    ArrayList<Order> fethOrdersWithProduct(int id, Paginator paginator) throws E;
}
