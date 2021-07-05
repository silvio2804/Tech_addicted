package Model.category;

import Model.http.Controller;
import Model.http.InvalidRequestException;
import Model.http.RequestValidator;
import Model.product.Product;
import Model.search.Paginator;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name = "categoryServlet", value = "/categories/*")

public class CategoryServlet extends Controller{

    private CategoryManager categoryManager;
    public void init()throws ServletException{
        try {
            categoryManager = new CategoryManager(source);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String path = getPath(request);
            switch (path){
                case "/": //mostra tutte le categorie
                    authorize(request.getSession());
                    request.setAttribute("back",view("crm/home"));
                    ArrayList<Category> categories = categoryManager.fetchCategories(new Paginator(1, 30));
                    request.setAttribute("categories",categories);
                    request.getRequestDispatcher(view("crm/manageCategory")).forward(request, response);
                    break;
                case "/create":
                    request.getRequestDispatcher(view("crm/category")).forward(request,response);
                    break;
                case "/show":
                    request.getRequestDispatcher(view("crm/product")).forward(request, response);
                    break;
                case "/search":
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;
                default:
                    notFound();
            }
        }
     catch (SQLException t) {
        t.printStackTrace();
        log(t.getMessage());
    } catch (InvalidRequestException ex) {
        ex.printStackTrace();
        log(ex.getMessage());
        ex.handle(request, response);
    }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
