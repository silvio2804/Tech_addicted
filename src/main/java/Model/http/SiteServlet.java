package Model.http;

import Model.category.Category;
import Model.category.CategoryManager;
import Model.product.Product;
import Model.search.Paginator;

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

    public void init() throws ServletException {
        try {
            categoryManager = new CategoryManager(source);

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
                    ArrayList<Category> categories = categoryManager.fetchCategories(new Paginator(1, 30));
                    session.setAttribute("categories",categories);
                    request.getRequestDispatcher(view("site/home")).forward(request, response);
                default:
                    internalError();
                case "/product":
                    //response.sendRedirect("progetto_war_exploded/products/show");
                    request.getRequestDispatcher(view("site/product")).forward(request, response);
            }
        }
        catch (InvalidRequestException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    }
}