package Model.utente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface UtenteDao <E extends  Exception>{

    ArrayList<Utente> fetchAccount(int start, int end) throws E;

    Optional<Utente> fetchAccount(String email)  throws E;

    Integer creaAccount(Utente utente) throws E ; //mettiamo boolean al posto di integer

    Integer eliminaAccount(String email) throws E;

    Integer modificaAccount(Utente utente) throws E;
}
