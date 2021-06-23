package Model.http;

import Model.account.AccountSession;
import Model.cart.Cart;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.File;

public abstract class Controller extends HttpServlet implements ErrorHandler {

   //@Resource(name = "jdbc/techaddicted")
    protected static DataSource source;

    protected String getPath(HttpServletRequest req) {return req.getPathInfo() != null ? req.getPathInfo() : "/"; }

    protected String view(String viewPath) {
        String basePath = getServletContext().getInitParameter("basePath");
        String engine = getServletContext().getInitParameter("engine");
        return basePath + viewPath + engine;
    }

    protected void validate(RequestValidator validator) throws InvalidRequestException {
        if(validator.hasErrors()){
            throw new InvalidRequestException("Validation Error",validator.getError(),
                    HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected String back(HttpServletRequest request) { return request.getServletPath() + request.getPathInfo(); }

    protected String getUploadPath() {
        return System.getenv("CATALINA_HOME") + File.separator + "uploads" + File.separator;
    }

    protected int parsePage(HttpServletRequest request){return Integer.parseInt(request.getParameter("page"));}

    protected AccountSession getSessionAccount(HttpSession session){
        return (AccountSession) session.getAttribute("accountSessione");
    }

    protected Cart getSessionCart(HttpSession session){
        return (Cart) session.getAttribute("accountCart");
    }
}
