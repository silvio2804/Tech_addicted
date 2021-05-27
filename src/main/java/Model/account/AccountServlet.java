package Model.account;

import Model.http.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AccountServlet", value = "/accounts/*")

//quando chiamo create di doGet mi da il form e dopo averlo compilato, send redirect a questa servlet chiamando create di doPost per crearlo effettivamete

public class AccountServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);
        System.out.println(path);
        switch(path){
            case "/":
                break;
            case "signin": //registrazione pagina
                break;
            case "signup": //login utente
                break;
            case "/secret": //login admin (pagina)
              request.getRequestDispatcher(view("crm/secret")).forward(request, response);
                break;
            case "create":
                break;
            case "show":
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"Risorsa non trovata");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "null";
        switch (path) {
            case "secret": //login admin (ricerca nel db)
            case "/":
                break;
            case "signup": //login utente (ricerca nel db)
                break;
            case "signin": //registrazione utente
                break;
            case "create":
                break;
            case "update":
                break;
            case "logout": //logout per entrambi
                break;
            default:
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"Operazione non consentita");
        }
    }
}
