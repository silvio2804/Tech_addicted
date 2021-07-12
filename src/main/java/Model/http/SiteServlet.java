package Model.http;

import Model.category.Category;
import Model.category.CategoryManager;
import Model.product.Product;
import Model.product.ProductManager;
import Model.search.Paginator;
import Model.tag.Tag;
import Model.tag.TagManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

//navigazione pagine del sito lato customer
@WebServlet(name = "SiteServlet", value = "/site/*")
public class SiteServlet extends Controller {
    private TagManager tagManager;
    private CategoryManager categoryManager;
    private ProductManager productManager;

    public void init() throws ServletException {
        try {
            categoryManager = new CategoryManager(source);
            productManager = new ProductManager(source);
            tagManager = new TagManager(source);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);
        try {
            switch (path) {
                case "/home":
                    HttpSession session = request.getSession();
                    ArrayList<Product> products = productManager.fetchProducts(new Paginator(1, 8));
                    ArrayList<Category> categories = categoryManager.fetchCategories(new Paginator(1, 30));
                    ArrayList<Tag> tags = tagManager.fetchTags(new Paginator(1, 50));
                    session.setAttribute("categories", categories);
                    request.setAttribute("products", products);
                    session.setAttribute("tags", tags);
                    request.getRequestDispatcher(view("site/home")).forward(request, response);
                default:
                    internalError();
                case "/product":
                    //response.sendRedirect("progetto_war_exploded/products/show");
                    request.getRequestDispatcher(view("site/product")).forward(request, response);
            }
        } catch (InvalidRequestException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}