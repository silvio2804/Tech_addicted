package Model.http;

import Model.account.AccountSession;
import Model.cart.Cart;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Controller extends HttpServlet implements ErrorHandler {

    protected static DataSource source;

    //prende la path dalla richiesta per la navigazione delle pagine
    protected String getPath(HttpServletRequest request) {return request.getPathInfo() != null ? request.getPathInfo() : "/"; }


    protected String view(String viewPath) { //costruisce la path per le views WEB-INF/views/viewPath.jsp e fai il forward
        String basePath = getServletContext().getInitParameter("basePath");
        String engine = getServletContext().getInitParameter("engine");
        return basePath + viewPath + engine;
    }

    //si utilizza nelle servlet e permette la validazione dei dati: CommonValidator---> RequestValidator
    protected void validate(RequestValidator validator) throws InvalidRequestException {
        if(validator.hasErrors()){
            throw new InvalidRequestException("Validation Error",validator.getError(),
                    HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    //Concatena la Stringa per tornare indietro, in caso di errore
    protected String back(HttpServletRequest request) { return request.getServletPath() + request.getPathInfo(); }


    protected String getUploadPath() { //si utilizza per salvare i file nella cartella di tomcato(una volta caricati sul sito)
        return System.getenv("CATALINA_HOME") + File.separator + "uploads" + File.separator;
    }

    //ritorna il numero di pagina, preso dalla request
    protected int parsePage(HttpServletRequest request){return Integer.parseInt(request.getParameter("page"));}

    //ritona l'account di sessione
    protected AccountSession getSessionAccount(HttpSession session){
        return (AccountSession) session.getAttribute("accountSession");
    }

    //ritorna il carrello di sessione
    protected Cart getSessionCart(HttpSession session){
        return (Cart) session.getAttribute("accountCart");
    }

    protected boolean isAjax(HttpServletRequest request){
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    protected void sendJson(HttpServletResponse response, JSONObject object) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(object.toString());
        writer.flush();
    }
}
