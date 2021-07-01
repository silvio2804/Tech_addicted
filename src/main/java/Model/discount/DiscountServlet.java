package Model.discount;

import Model.http.Controller;
import Model.http.InvalidRequestException;
import Model.search.Paginator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "DiscountServlet", value = "/discounts/*")

public class DiscountServlet extends Controller {

    private DiscountManager discountManager;

    public void init() throws ServletException{
        try {
            discountManager = new DiscountManager(source);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path){
                case "/":
                    authorize(request.getSession());
                    request.setAttribute("back",view("crm/product"));
                    ArrayList<Discount> discounts = discountManager.fetchDiscounts(new Paginator(1,30));
                    request.setAttribute("discounts",discounts);
                    request.getRequestDispatcher(view("crm/manageDiscount")).forward(request, response);
                    break;
                default:
                    notFound();
            }
        }
        catch (InvalidRequestException | SQLException t){
            t.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

    }
}
