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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AccountServlet", value = "/accounts/*")


//quando chiamo create di doGet mi da il form e dopo averlo compilato, send redirect a questa servlet chiamando create di doPost per crearlo effettivamete

public class AccountServlet extends Controller {

    public void init() throws ServletException {
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
                    request.setAttribute("back", view("crm/accounts"));
                    validate(CommonValidator.validatePage(request));
                    int page = parsePage(request);
                    Paginator paginator = new Paginator(page, 50);
                    int size = accountManager.countAll();
                    request.setAttribute("pages", paginator.getPages(size));
                    List<Account> accounts = accountManager.fetchAccounts(paginator);
                    request.setAttribute("accounts", accounts);
                    request.getRequestDispatcher(view("crm/accountManager")).forward(request, response);
                    break;
                case "/signin": //registrazione pagina
                    //operazioni di accesso e reindirizzamento alla homePage --> home.jsp
                    authorize(request.getSession());
                    request.setAttribute("back", view("home qualcosa"));
                    validate(AccountValidator.validateSignin(request));
                    Account user = new Account();
                    user.setEmail(request.getParameter("email"));
                    user.setPassword("password");
                    break;
                case "/logout": //logout utente admin e non
                    HttpSession session = request.getSession(false);
                    authenticate(session);
                    AccountSession accountSession = getSessionAccount(session);
                    System.out.println(accountSession);
                    String redirect = accountSession.isAdmin() ? "/progetto_war_exploded/accounts/secret" : "/progetto_war_exploded/accounts/signin";
                    session.removeAttribute("accountSession");
                    session.invalidate();
                    response.sendRedirect(redirect);
                    break;
                case "/secret": //pannello admin
                    request.getRequestDispatcher(view("crm/secret")).forward(request, response);
                    break;
                case "/create":
                    break;
                case "/show":
                    break;
                case "/profile":
                    AccountSession account = getSessionAccount(request.getSession(false));
                    int id = account.getId();
                    Optional<Account> profileAccount = accountManager.fetchAccount(id);
                    if(profileAccount.isPresent()){
                        request.setAttribute("profileAccount",profileAccount);
                        request.getRequestDispatcher(view("site/profile")).forward(request,response);
                    }
                    else
                        notFound();
                default:
                    notFound();
            }
        } catch (SQLException | InvalidRequestException | NoSuchAlgorithmException t) {
            t.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = (request.getPathInfo() != null) ? request.getPathInfo() : "null";
            switch (path) {
                case "/secret": //login admin (ricerca nel db)
                    request.setAttribute("back", view("crm/secret"));
                    validate(AccountValidator.validateSignin(request));
                    Account tmp = new Account();
                    tmp.setEmail(request.getParameter("email"));
                    tmp.setPassword(request.getParameter("password"));
                    Optional<Account> optAccount = accountManager.findAccount(tmp.getEmail(), tmp.getPassword(),true);
                    if (optAccount.isPresent()) {
                        AccountSession accountSession = new AccountSession(optAccount.get());
                        request.getSession(true).setAttribute("accountSession", accountSession);
                        response.sendRedirect("../pages/dashboard");
                    } else
                        throw new InvalidRequestException("Credenziali non valide",
                                List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                    break;
                case "/signup": //registrazione cliente
                    validate(AccountValidator.validateForm(request, false));
                    Account customer = new AccountFormExtractor().extract(request, false);
                    if (accountManager.createAccount(customer))
                        response.sendRedirect("./accounts/signin");
                    else
                        internalError();
                    break;
                case "/signin": //login utente
                    request.setAttribute("back", view("site/signin"));
                    validate(AccountValidator.validateSignin(request));
                    Account tmpCustomer = new Account();
                    tmpCustomer.setEmail(request.getParameter("email"));
                    tmpCustomer.setPassword(request.getParameter("password"));
                    Optional<Account> optCustomer = accountManager.findAccount(tmpCustomer.getEmail(), tmpCustomer.getPassword(), false);
                    if (optCustomer.isPresent()) {
                        AccountSession accountSession = new AccountSession(optCustomer.get());
                        request.getSession(true).setAttribute("accountSession", accountSession);
                        response.sendRedirect("./site/home");
                    } else
                        throw new InvalidRequestException("Credenziali non valide!",
                                List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                    break;
                case "/create":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/account"));
                    validate(AccountValidator.validateForm(request, false));
                    Account account = new AccountFormExtractor().extract(request, false);
                    account.setPassword(request.getParameter("password"));
                    if (accountManager.createAccount(account)) {
                        request.setAttribute("alert", new Alert(List.of("Account creato!"), "success"));
                        request.getRequestDispatcher(view("crm/account")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/account"));
                    validate(AccountValidator.validateForm(request, true));
                    Account updateAccount = new AccountFormExtractor().extract(request, true);
                /*if(accountManager.updateAccount(updateAccount)){
                    request.setAttribute("account",updateAccount);
                    request.setAttribute("alert",new Alert(List.of("Account aggiornato !"),"success"));
                    request.getRequestDispatcher(view("crm/account")).forward(request,response);
                }
                else
                    internalError();*/
                    break;
                case "profile": //aggiorna profilo cliente
                    break;
                case "logout": //logout per entrambi
                    break;
                default:
                    notAllowed();
            }
        } catch (NoSuchAlgorithmException | SQLException t) {
            t.printStackTrace();
            log(t.getMessage());
        } catch (InvalidRequestException ex) {
            ex.printStackTrace();
            log(ex.getMessage());
            ex.handle(request, response);
        }
    }

    private AccountManager accountManager;
}
