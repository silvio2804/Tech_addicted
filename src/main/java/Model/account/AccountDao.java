package Model.account;

import Model.search.Paginator;

import java.util.ArrayList;
import java.util.Optional;

public interface AccountDao<E extends  Exception>{

    ArrayList<Account> fetchAccounts(Paginator paginator) throws E;

    Optional<Account> fetchAccount(int id)  throws E;

    boolean creaAccount(Account utente) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaAccount(int id) throws E;

    boolean modificaAccount(Account utente) throws E;
}
