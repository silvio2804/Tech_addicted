package Model.account;

import Model.search.Paginator;

import java.util.ArrayList;
import java.util.Optional;

public interface AccountDao<E extends  Exception>{

    ArrayList<Account> fetchAccounts(Paginator paginator) throws E;

    Optional<Account> fetchAccount(int id)  throws E;

    boolean createAccount(Account utente) throws E ; //mettiamo boolean al posto di integer

    boolean deleteAccount(int id) throws E;

    boolean updateAccount(Account utente) throws E;

    int countAll() throws E;

    Optional<Account> findAccount(String email, String password) throws E;
}
