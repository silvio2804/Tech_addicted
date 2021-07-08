package Model.http;

import Model.account.AccountManager;
import Model.category.Category;
import Model.category.CategoryManager;
import Model.order.OrderManager;
import Model.product.ProductManager;
import Model.search.Paginator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

//servlet per le pagine utente
@WebServlet(name = "pageServlet",value = "/pages/*")
public class PageServlet extends Controller {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/dashboard":
                    AccountManager accountManager = new AccountManager(source);
                    OrderManager orderManager = new OrderManager(source);
                    ProductManager productManager = new ProductManager(source);
                    Integer numberAccounts = accountManager.countAll();
                    Integer numberOrders = orderManager.countAll();
                    Integer numberProducts = productManager.countAll();
                    Double totalOrders = orderManager.totalReveneus();
                    HttpSession session = request.getSession(false);
                    authorize(session);
                    session.setAttribute("numberAccounts",numberAccounts);
                    session.setAttribute("numberOrders",numberOrders);
                    session.setAttribute("numberProducts",numberProducts);
                    session.setAttribute("totalOrders",totalOrders);
                    request.getRequestDispatcher(view("crm/home")).forward(request, response);
                    break;
                case "/":
                    request.getRequestDispatcher(view("site/home")).forward(request, response);
                    break;
                default: notAllowed();
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        catch (InvalidRequestException ex) {
            ex.printStackTrace();
            log(ex.getMessage());
            ex.handle(request, response);
        }
    }
}
