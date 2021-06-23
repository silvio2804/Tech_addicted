package Model.account;

import Components.Alert;
import Model.http.CommonValidator;
import Model.http.Controller;
import Model.http.InvalidRequestException;
import Model.search.Paginator;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AccountServlet", value = "/accounts/*")



//quando chiamo create di doGet mi da il form e dopo averlo compilato, send redirect a questa servlet chiamando create di doPost per crearlo effettivamete

public class AccountServlet extends Controller {

    public void init() throws  ServletException {
        super.init();
        try {
            accountManager = new AccountManager(source);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/":
                    authorize(request.getSession());
                    request.setAttribute("back",view("crm/accounts"));
                    validate(CommonValidator.validatePage(request));
                    int page = parsePage(request);
                    Paginator paginator = new Paginator(page,50);
                    int size = accountManager.countAll();
                    request.setAttribute("pages",paginator.getPages(size));
                    java.util.List<Account> accounts = accountManager.fetchAccounts(paginator);
                    request.setAttribute("accounts",accounts);
                    request.getRequestDispatcher(view("crm/accounts")).forward(request,response);
                    break;
                case "signin": //registrazione pagina
                    break;
                case "signup": //login utente
                    break;
                case "logout": //logout utente admin e non
                    HttpSession session = request.getSession(false);
                    authenticate(session);
                    AccountSession accountSession = (AccountSession) request.getAttribute("accountSession");
                    String redirect = accountSession.isAdmin() ? "/Techaddicted/accounts/secret" : "/Techaddicted/accounts/signin";
                    session.removeAttribute("accountSession");
                    session.invalidate();
                    response.sendRedirect(redirect);
                    break;
                case "/secret": //login admin (pagina)
                    request.getRequestDispatcher(view("crm/secret")).forward(request, response);
                    break;
                case "create":
                    break;
                case "show":
                    break;
                default:
                    notFound();
            }
        }
        catch (SQLException | InvalidRequestException t) {
            t.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "null";
        switch (path) {
            case "secret": //login admin (ricerca nel db)
                request.setAttribute("back",view("crm/secret"));
                validate(AccountValidator.validateSignin(request));
                Account tmp = new Account();
                tmp.setEmail(request.getParameter("email"));
                tmp.setPassword(request.getParameter("password"));
                Optional<Account> optAccount = AccountManager.findAccount(tmp.getEmail(),tmp.getPassword());
                if(optAccount.isPresent()){
                    AccountSession accountSession = new AccountSession(optAccount.get());
                    request.getSession(true).setAttribute("accountSession",accountSession);
                    response.sendRedirect("/Techaddicted/pages/dashboard");
                }
                else{
                    try {
                        throw new InvalidRequestException("Credenziali non valide",
                                List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                    } catch (InvalidRequestException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "/":
                break;
            case "signup": //login utente (ricerca nel db)
                break;
            case "signin": //registrazione utente
                break;
            case "create":
                try {
                    authorize(request.getSession(false));
                } catch (InvalidRequestException e) {
                    e.printStackTrace();
                }
                request.setAttribute("back",view("crm/account"));
                validate(AccountValidator.validateFomr(request,false));
                Account account = new AccountFormMapper().map(request,false);
                account.setPassword(request.getParameter("password"));
                if(accountManager.creaAccount(account)){
                    request.setAttribute("alert", new Alert(List.of("Account creato!"),"success"));
                    request.getRequestDispatcher(view("crm/account")).forward(request,response);
                }
                else{
                    try {
                        internalError();
                    } catch (InvalidRequestException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "update":
                break;
            case "logout": //logout per entrambi
                break;
            default:
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"Operazione non consentita");
        }
    }

    private AccountManager accountManager;
}
