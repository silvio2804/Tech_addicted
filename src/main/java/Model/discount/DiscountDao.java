package Model.discount;

import java.util.ArrayList;
import java.util.Optional;

public interface DiscountDao<E extends Exception>{

    ArrayList<Discount> fetchSconti() throws E;

    Optional<Discount> fetchSconto(int id)  throws E;

    boolean creaSconto(Discount discount) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaSconto(int idSconto) throws E;

    boolean modificaSconto(Discount discount) throws E;
}
