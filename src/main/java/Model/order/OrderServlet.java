package Model.order;

import Model.http.Controller;
import Model.http.InvalidRequestException;
import Model.product.Product;
import Model.search.Paginator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "orderServlet", value = "/orders/*")
public class OrderServlet extends Controller {

    private OrderManager orderManager;

    public void init()throws ServletException{
        try {
            orderManager = new OrderManager(source);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try{
            String path = getPath(request);
            switch (path){
                case "/":
                    authorize(request.getSession());
                    request.setAttribute("back",view("crm/home"));
                    ArrayList<Order> orders = orderManager.fetchOrders(new Paginator(-1,30));
                    request.setAttribute("orders",orders);
                    request.getRequestDispatcher(view("crm/manageOrder")).forward(request, response);
                    break;
                default:
                    notFound();
            }
        }
        catch(InvalidRequestException | SQLException t){
            t.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

    }
}
