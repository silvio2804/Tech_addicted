package Model.account;

import java.util.ArrayList;
import java.util.Optional;

public interface AccountDao<E extends  Exception>{

    ArrayList<Account> fetchAccounts(int start, int end) throws E;

    Optional<Account> fetchAccount(int id)  throws E;

    boolean creaAccount(Account utente) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaAccount(int id) throws E;

    boolean modificaAccount(Account utente) throws E;
}
