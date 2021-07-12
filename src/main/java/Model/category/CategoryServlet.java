package Model.category;

import Components.Alert;
import Model.account.Account;
import Model.account.AccountFormExtractor;
import Model.account.AccountValidator;
import Model.http.Controller;
import Model.http.InvalidRequestException;
import Model.http.RequestValidator;
import Model.product.Product;
import Model.product.ProductFormExtractor;
import Model.product.ProductValidator;
import Model.search.Paginator;
import com.mysql.cj.xdevapi.JsonArray;
import netscape.javascript.JSObject;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "categoryServlet", value = "/categories/*")

public class CategoryServlet extends Controller {

    private CategoryManager categoryManager;

    public void init() throws ServletException {
        try {
            categoryManager = new CategoryManager(source);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/": //mostra tutte le categorie
                    authorize(request.getSession());
                    request.setAttribute("back", view("crm/home"));
                    int page = parsePage(request);
                    Paginator paginator = new Paginator(page, 10);
                    int size = categoryManager.countAll();
                    request.setAttribute("pages", paginator.getPages(size));
                    List<Category> cat = categoryManager.fetchCategories(paginator);
                    request.setAttribute("categories", cat);
                    request.getRequestDispatcher(view("crm/manageCategory")).forward(request, response);
                    break;
                case "/create":
                    authorize(request.getSession());
                    request.getRequestDispatcher(view("category/form")).forward(request, response);
                    break;
                case "/show":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Optional<Category> category = categoryManager.fetchCategory(id);
                    if (category.isPresent()) {
                        request.setAttribute("category", category.get());
                        request.getRequestDispatcher(view("category/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/search":
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;
                case "/api":
                    if(isAjax(request)){
                        List<Category> categoryList = categoryManager.fetchCategories(new Paginator(1,30));
                        JSONObject root = new JSONObject();
                        JSONArray arr = new JSONArray();
                        root.put("categories",categoryList);
                        categoryList.forEach(c -> arr.put(c.toJson()));
                        sendJson(response,root);
                        break;
                    }else{
                        notFound();
                    }
                    break;
                default:
                    notFound();
            }
        } catch (SQLException t) {
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
        try {
            String path = (request.getPathInfo() != null) ? request.getPathInfo() : "null";
            switch (path) {
                case "/create":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("category/form"));
                    validate(CategoryValidator.validateForm(request, false));
                    Category category = new CategoryFormExtractor().extract(request, false);
                    if (categoryManager.createCategory(category)) {
                        request.setAttribute("alert", new Alert(List.of("Categoria creata !"), "success"));
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        request.getRequestDispatcher(view("category/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageCategory"));
                    validate(CategoryValidator.validateForm(request, false));
                    Category updateCategory = new CategoryFormExtractor().extract(request, true);
                    if (categoryManager.updateCategory(updateCategory)) {
                        request.setAttribute("category", updateCategory);
                        request.setAttribute("alert", new Alert(List.of("categoria aggiornata !"), "success"));
                        request.getRequestDispatcher(view("category/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/delete":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageCategory"));
                    int id = Integer.parseInt(request.getParameter("id"));
                    if (categoryManager.deleteCategory(id)) {
                        request.setAttribute("alert", new Alert(List.of("Categoria eliminata!"), "success"));
                        response.sendRedirect("/progetto_war_exploded/categories?page=1");
                    } else
                        internalError();
                    break;
            }
        } catch (SQLException t) {
            t.printStackTrace();
            log(t.getMessage());
        } catch (InvalidRequestException ex) {
            ex.printStackTrace();
            log(ex.getMessage());
            ex.handle(request, response);
        }
    }
}
