package Model.account;

import Components.Alert;
import Model.cart.Cart;
import Model.cart.CartManager;
import Model.http.Controller;
import Model.http.InvalidRequestException;
import Model.search.Paginator;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AccountServlet", value = "/accounts/*")
//quando chiamo create di doGet mi da il form e dopo averlo compilato, send redirect a questa servlet chiamando create di doPost per crearlo effettivamete

public class AccountServlet extends Controller {

    private AccountManager accountManager;
    private CartManager cartManager;

    public void init() throws ServletException {
        super.init();
        try {
            accountManager = new AccountManager(source);
            cartManager = new CartManager(source);
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
                    int page = parsePage(request);
                    Paginator paginator = new Paginator(page, 10);
                    int size = accountManager.countAll();
                    request.setAttribute("pages", paginator.getPages(size));
                    List<Account> accounts = accountManager.fetchAccounts(paginator);
                    request.getSession().setAttribute("accounts", accounts);
                    request.getRequestDispatcher(view("crm/manageAccount")).forward(request, response);
                    break;
                case "/signup":
                    request.getRequestDispatcher(view("site/signup")).forward(request,response);
                    break;
                case "/logout": //logout utente admin e non
                    HttpSession session = request.getSession(false);
                    authenticate(session);
                    AccountSession accountSession = getSessionAccount(session);
                    session.removeAttribute("accountSession");
                    session.invalidate();
                    response.sendRedirect("/progetto_war_exploded/site/home");
                    break;
                case "/signin":
                    request.getRequestDispatcher(view("site/signin")).forward(request, response);
                    break;
                case "/create":
                    authorize(request.getSession());
                    request.getRequestDispatcher(view("account/form")).forward(request, response);
                    break;
                case "/show":
                    int idShowed = Integer.parseInt(request.getParameter("id"));
                    Optional<Account> accountShowed = accountManager.fetchAccount(idShowed);
                    if(accountShowed.isPresent()){
                        request.setAttribute("accountShowed",accountShowed.get());
                        request.getRequestDispatcher(view("account/form")).forward(request, response);
                    }
                    else
                        internalError();
                    break;
                case "/profile":
                    AccountSession account = getSessionAccount(request.getSession(false));
                    String red = account.isAdmin() ? view("crm/showProfile") : view("site/profile");
                    int id = account.getId();
                    Optional<Account> profileAccount = accountManager.fetchAccount(id);
                    if(profileAccount.isPresent()){
                        request.setAttribute("profileAccount",profileAccount);
                        request.getRequestDispatcher(red).forward(request,response);
                    }
                    else {
                        notFound();
                    }
                default:
                    notFound();
            }
        } catch (SQLException | InvalidRequestException t) {
            t.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = (request.getPathInfo() != null) ? request.getPathInfo() : "null";
            switch (path) {
                case "/signup": //registrazione cliente
                    Account registerAccount = new RegisterAccountExtractor().extract(request,false);
                    AccountSession accountSess = new AccountSession(registerAccount);
                    registerAccount.setPassword(request.getParameter("password"));
                    Optional<Account> accountOpt= accountManager.findAccount(registerAccount.getEmail(), registerAccount.getPassword());
                    if (!accountOpt.isPresent()) {
                        accountManager.createAccount(registerAccount);
                        request.getSession(false).setAttribute("accountSession",accountSess);
                        response.sendRedirect("../site/home");
                    }
                    else{
                        throw new InvalidRequestException("Email già presente",List.of("Credenziali non valide"),
                                HttpServletResponse.SC_BAD_REQUEST);
                    }
                    break;
                case "/signin":
                    request.setAttribute("back", view("site/signin"));
                    validate(AccountValidator.validateSignin(request));
                    Account tmpCustomer = new Account();
                    tmpCustomer.setEmail(request.getParameter("email"));
                    tmpCustomer.setPassword(request.getParameter("password"));
                    if(tmpCustomer.isAdmin()) {
                        Integer accounts = accountManager.countAll();
                        request.setAttribute("accounts", accounts);
                    }
                    Optional<Account> optCustomer = accountManager.findAccount(tmpCustomer.getEmail(), tmpCustomer.getPassword());
                    if (optCustomer.isPresent()) {
                        AccountSession accountSession = new AccountSession(optCustomer.get());
                        String red = accountSession.isAdmin() ? "../pages/dashboard" :"../site/home";
                        request.getSession(true).setAttribute("accountSession", accountSession);
                        if(!optCustomer.get().isAdmin()){
                            Cart cart = cartManager.fetchCart(accountSession);
                            cart = cartManager.fetchCartWithProduct(cart.getIdCart());
                            request.getSession(false).setAttribute("cart", cart);
                        }
                        response.sendRedirect(red);
                    } else
                        throw new InvalidRequestException("Credenziali non valide!",
                                List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                    break;
                case "/create":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("account/form"));
                    validate(AccountValidator.validateForm(request, false));
                    Account account = new AccountFormExtractor().extract(request, false);
                    account.setPassword(request.getParameter("password"));
                    if (accountManager.createAccount(account)) {
                        Cart cart = new Cart(new ArrayList<>());
                        cart.setAccount(accountManager.findAccount(account.getEmail(), account.getPassword()).get());
                        if(cartManager.createCart(cart)){
                            request.setAttribute("alert", new Alert(List.of("Account creato!"), "success"));
                            request.getRequestDispatcher(view("account/form")).forward(request, response);
                        }
                        else
                            internalError();
                        }
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageAccount"));
                    //validate(AccountValidator.validateForm(request, true));
                    Account updateAccount = new AccountFormExtractor().extract(request, true);
                    updateAccount.setPassword(request.getParameter("password"));
                if(accountManager.updateAccount(updateAccount)){
                    request.setAttribute("account",updateAccount);
                    request.setAttribute("alert",new Alert(List.of("Account aggiornato !"),"success"));
                    request.getRequestDispatcher(view("account/form")).forward(request,response);
                }
                else {
                    throw new InvalidRequestException(("Credenziali non valide!"), List.of("Credenziali non valide!"), HttpServletResponse.SC_BAD_REQUEST);
                }
                    break;
                case "/delete":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageCategory"));
                    int id = Integer.parseInt(request.getParameter("id"));
                    if (accountManager.deleteAccount(id)) {
                        request.setAttribute("alert", new Alert(List.of("Account eliminato!"), "success"));
                        response.sendRedirect("/progetto_war_exploded/accounts?page=1");
                    } else
                        internalError();
                    break;
                case "profile": //aggiorna profilo cliente
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
}
