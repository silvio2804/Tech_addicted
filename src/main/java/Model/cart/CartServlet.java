package Model.cart;

import Components.Alert;
import Model.account.Account;
import Model.account.AccountSession;
import Model.discount.Discount;
import Model.discount.DiscountFormExtractor;
import Model.discount.DiscountValidator;
import Model.http.Controller;
import Model.http.InvalidRequestException;
import Model.product.Product;
import Model.product.ProductManager;
import Model.search.Paginator;
import Model.tag.Tag;
import Model.tag.TagFormExtractor;
import Model.tag.TagManager;
import Model.tag.TagValidator;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "CartServlet", value = "/carts/*")


public class CartServlet extends Controller {

    private CartManager cartManager;
    private ProductManager productManager;

    public void init() throws ServletException {
        try {
            productManager = new ProductManager(source);
            cartManager = new CartManager((DataSource) source);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/show":
                    //prendere il carrello di un utente
                    HttpSession session = request.getSession(false);
                    int id = getSessionAccount(session).getId();
                    Cart sessionCart = cartManager.fetchCartWithProduct(id);
                    session.setAttribute("sessionCart",sessionCart);
                    request.getRequestDispatcher(view("site/showCart")).forward(request,response);
                    break;
                default:
                    notFound();
            }
        }catch (InvalidRequestException ex) {
            ex.printStackTrace();
            log(ex.getMessage());
            ex.handle(request, response);
        }
        catch (SQLException t){
            t.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = (request.getPathInfo() != null) ? request.getPathInfo() : "null";
            switch (path) {
                case "/add":
                    request.setAttribute("back",view("site/details"));
                    validate(CartValidator.validateProduct(request));
                    int id = Integer.parseInt(request.getParameter("id"));
                    Optional<Product> optionalProduct = productManager.fetchProduct(id);
                    if(optionalProduct.isPresent()){
                        int quantity = Integer.parseInt(request.getParameter("quantity"));
                        if(request.getSession(false).getAttribute("accountCart") == null){
                            request.getSession(false).setAttribute("accountCart",new Cart(new ArrayList<>()));
                        }
                        getSessionCart(request.getSession(false)).addProduct(optionalProduct.get(),quantity);
                        response.sendRedirect("/progetto_war_exploded/products/details?id="+optionalProduct.get().getProductId());
                    }else
                        notFound();
                   break;
                case "/remove":
                    //commonValidator
                    int removeId = Integer.parseInt(request.getParameter("productId"));
                    if(getSessionCart(request.getSession(false)).removeProduct(removeId)){
                        response.sendRedirect("/progetto_war_exploded/carts/show");
                    }
                    else
                        notFound();
                    break;
                case "addProduct":
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    Optional<Product> product = productManager.fetchProduct(productId);
                    if(product.isPresent()){
                        int id1 = 0;
                        Cart cart = cartManager.fetchCartWithProduct(id1);
                        cart.addProduct(product.get(),quantity);
                        if (true){
                            request.setAttribute("alert", new Alert(List.of("Prodotto aggiunto al carrello!"), "success"));
                            response.sendRedirect("/progetto_war_exploded/site/home");
                        } else
                            internalError();
                        break;
                    }
                    response.sendRedirect("/progetto_war_exploded/site/home");
                    break;
                default:
                    notAllowed();
            }
        }
        catch (SQLException t) {
            t.printStackTrace();
            log(t.getMessage());
        }
        catch (InvalidRequestException ex) {
            ex.printStackTrace();
            log(ex.getMessage());
            ex.handle(request, response);
        }
    }
}

