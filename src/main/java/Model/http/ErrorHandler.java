package Model.http;

import Model.account.AccountSession;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface ErrorHandler {

    default  void authenticate(HttpSession session) throws InvalidRequestException {
        if(session == null || session.getAttribute("accountSession") == null){
            throw  new InvalidRequestException("Errore autenticazione", List.of("Non sei autenticato"),HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    default  void authorize(HttpSession session) throws InvalidRequestException {
        authenticate(session);
        AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
        if(!accountSession.isAdmin()) {
            throw new InvalidRequestException("Errore autorizzazione",List.of("Azione non connsentita"),
                    HttpServletResponse.SC_FORBIDDEN);
            }
        }

    default  void internalError() throws InvalidRequestException {
        List<String> errors = List.of("Un errore imprevisto è accaduto", "Riprova più tardi");
        throw new InvalidRequestException("Errore interno", List.of("Risorsa non trovata"),
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    default void notFound() throws InvalidRequestException {
        throw new InvalidRequestException("Errore interno",List.of("Risorsa non trovata"),
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    default void notAllowed() throws InvalidRequestException {
        throw new InvalidRequestException("Operazione non consentita",List.of("Operazione non permessa"),
                HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
