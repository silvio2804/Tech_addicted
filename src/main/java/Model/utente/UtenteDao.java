package Model.utente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface UtenteDao {

    ArrayList<Utente> fetchAccount(int start, int end) throws SQLException;

    Optional<Utente> fetchAccount(String email) throws SQLException;

    Integer creaAccount(Utente utente) throws SQLException;

    Integer eliminaAccount(String email) throws SQLException;

    Integer modificaAccount(Utente utente) throws SQLException;
}
