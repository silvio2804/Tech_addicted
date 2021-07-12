package Model.order;

import Components.Alert;
import Model.category.Category;
import Model.category.CategoryFormExtractor;
import Model.category.CategoryValidator;
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
import java.util.Optional;

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
                    Paginator paginator = new Paginator(1, 5);
                    int size = orderManager.countAll();
                    request.setAttribute("pages", paginator.getPages(size));
                    ArrayList<Order> orders = orderManager.fetchOrders(paginator);
                    request.setAttribute("orders",orders);
                    request.getRequestDispatcher(view("crm/manageOrder")).forward(request, response);
                    break;
                case "/create":
                    authorize(request.getSession());
                    request.getRequestDispatcher(view("category/form")).forward(request, response);
                    break;
                case "/show":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Optional<Order> order = orderManager.fetchOrder(id);
                    if(order.isPresent()){
                        request.setAttribute("order",order.get());
                        request.getRequestDispatcher(view("order/form")).forward(request, response);
                    }
                    else
                        internalError();
                    break;
                case "/search":
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
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
                    authorize(request.getSession(false));
                    request.setAttribute("back",view("order/form"));
                    validate(OrderValidator.validateForm(request,false));
                    Order order = new OrderFormExtractor().extract(request,false);
                    if(orderManager.createOrder(order)) {
                        request.setAttribute("alert",new Alert(List.of("Ordine creato !"),"success"));
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        request.getRequestDispatcher(view("order/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageOrder"));
                    validate(OrderValidator.validateForm(request,true));
                    Order updateOrder = new OrderFormExtractor().extract(request,true);
                    if (orderManager.updateOrder(updateOrder)) {
                        request.setAttribute("order", updateOrder);
                        request.setAttribute("alert", new Alert(List.of("ordine aggiornato !"), "success"));
                        request.getRequestDispatcher(view("order/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/delete":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageOrder"));
                    int id = Integer.parseInt(request.getParameter("id"));
                    if (orderManager.deleteOrder(id)) {
                        request.setAttribute("alert", new Alert(List.of("Ordine eliminato!"), "success"));
                        request.getRequestDispatcher(view("crm/manageOrder")).forward(request, response);
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
