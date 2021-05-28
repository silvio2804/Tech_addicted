package Model.category;

import Model.product.Product;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name = "categoriaServlet", value = "/categoriaServlet")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            CategoryManager cm = new CategoryManager(new DataSource());
            Optional<Category> cat = cm.fetchCategoriaConProdotto(1);
            ArrayList<Product> prodotti = cat.get().getProdotti();
            request.setAttribute("prodotti", prodotti);
            System.out.println(prodotti);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        String address = "WEB-INF/views/categoria/categorieEx.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(address);
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
