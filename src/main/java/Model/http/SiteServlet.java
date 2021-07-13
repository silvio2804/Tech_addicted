package Model.http;

import Model.cart.Cart;
import Model.cart.CartManager;
import Model.category.Category;
import Model.category.CategoryManager;
import Model.product.Product;
import Model.product.ProductManager;
import Model.search.Paginator;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name = "SiteServlet",value ="/site/*")
public class SiteServlet extends Controller {

    private CategoryManager categoryManager;
    private ProductManager productManager;
    private CartManager cartManager;
    public void init() throws ServletException {
        try {
            categoryManager = new CategoryManager(source);
            productManager = new ProductManager(source);
            cartManager = new CartManager((DataSource) source);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String path = getPath(request);
        try {
            switch (path) {
                case "/home":
                    HttpSession session = request.getSession();
                    ArrayList<Product> products = productManager.fetchProducts(new Paginator(1,30));
                    ArrayList<Category> categories = categoryManager.fetchCategories(new Paginator(1, 30));
                    /*int id = 0;
                    Cart cart = cartManager.fetchCartWithProduct(id);*/
                    session.setAttribute("categories",categories);
                    session.setAttribute("products", products);
                    //session.setAttribute("cart",cart);
                    request.getRequestDispatcher(view("site/home")).forward(request, response);
                    break;
                case "/product":
                    //response.sendRedirect("progetto_war_exploded/products/show");
                    request.getRequestDispatcher(view("site/product")).forward(request, response);
                    break;
                default:
                    internalError();
            }
        }
        catch (InvalidRequestException | SQLException e) {
            e.printStackTrace();
        }
    }
}