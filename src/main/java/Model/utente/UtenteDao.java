package Model.utente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface UtenteDao <E extends  Exception>{

    ArrayList<Utente> fetchAccounts(int start, int end) throws E;

    Optional<Utente> fetchAccount(int id)  throws E;

    boolean creaAccount(Utente utente) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaAccount(int id) throws E;

    boolean modificaAccount(Utente utente) throws E;
}
