package Model.sconto;

import Model.utente.Utente;

import java.util.ArrayList;
import java.util.Optional;

public interface ScontoDao <E extends Exception>{

    ArrayList<Sconto> fetchSconti() throws E;

    Optional<Sconto> fetchSconto(int id)  throws E;

    boolean creaSconto(Sconto sconto) throws E ; //mettiamo boolean al posto di integer

    boolean eliminaSconto(int idSconto) throws E;

    boolean modificaSconto(Sconto sconto) throws E;
}
