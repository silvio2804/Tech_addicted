package Model.discount;

import Model.search.Paginator;

import java.util.ArrayList;
import java.util.Optional;

public interface DiscountDao<E extends Exception>{

    ArrayList<Discount> fetchDiscounts(Paginator paginator) throws E;

    Optional<Discount> fetchDiscount(int id)  throws E;

    boolean createDiscount(Discount discount) throws E ; //mettiamo boolean al posto di integer

    boolean deleteDiscount(int idSconto) throws E;

    boolean updateDiscount(Discount discount) throws E;

    int countAll() throws E;
}
