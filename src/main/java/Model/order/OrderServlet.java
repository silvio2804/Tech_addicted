package Model.order;

import Components.Alert;
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
import java.util.List;

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
                    ArrayList<Order> orders = orderManager.fetchOrders(new Paginator(1,30));
                    request.setAttribute("orders",orders);
                    request.getRequestDispatcher(view("crm/manageOrder")).forward(request, response);
                    break;
                default:
                    notFound();
            }
        }
        catch ( SQLException t) {
        t.printStackTrace();
        log(t.getMessage());
        }
        catch (InvalidRequestException ex) {
        ex.printStackTrace();
        log(ex.getMessage());
        ex.handle(request, response);
    }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = (request.getPathInfo() != null) ? request.getPathInfo() : "null";
            switch (path) {
                case "/create":
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/account"));
                    validate(OrderValidator.validateForm(request,false));
                    Order updateOrder = new OrderFormExtractor().extract(request,true);
                    if (orderManager.updateOrder(updateOrder)) {
                        request.setAttribute("order", updateOrder);
                        request.setAttribute("alert", new Alert(List.of("ordine aggiornato !"), "success"));
                        request.getRequestDispatcher(view("crm/account")).forward(request, response);
                    } else
                        internalError();
                    break;
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
