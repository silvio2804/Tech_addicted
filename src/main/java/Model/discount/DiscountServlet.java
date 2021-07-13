package Model.discount;

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

@WebServlet(name = "DiscountServlet", value = "/discounts/*")

public class DiscountServlet extends Controller {

    private DiscountManager discountManager;

    public void init() throws ServletException {
        try {
            discountManager = new DiscountManager(source);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/":
                    authorize(request.getSession());
                    request.setAttribute("back", view("crm/product"));
                    int page = parsePage(request);
                    Paginator paginator = new Paginator(page, 10);
                    int size = discountManager.countAll();
                    request.setAttribute("pages", paginator.getPages(size));
                    ArrayList<Discount> discounts = discountManager.fetchDiscounts(paginator);
                    request.setAttribute("discounts", discounts);
                    request.getRequestDispatcher(view("crm/manageDiscount")).forward(request, response);
                    break;
                case "/create":
                    authorize(request.getSession());
                    request.getRequestDispatcher(view("discount/form")).forward(request, response);
                    break;
                case "/show":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Optional<Discount> discount = discountManager.fetchDiscount(id);
                    if (discount.isPresent()) {
                        request.setAttribute("discount", discount.get());
                        request.getRequestDispatcher(view("discount/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/search":
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;
                case "/delete":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageDiscount"));
                    int idDisc = Integer.parseInt(request.getParameter("id"));
                    if (discountManager.deleteDiscount(idDisc)) {
                        request.setAttribute("alert", new Alert(List.of("Sconto eliminato!"), "success"));
                        response.sendRedirect("/progetto_war_exploded/discounts?page=1");
                    } else
                        internalError();
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = (request.getPathInfo() != null) ? request.getPathInfo() : "null";
            switch (path) {
                case "/create":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageDiscount"));
                    validate(DiscountValidator.validateForm(request, false));
                    Discount discount = new DiscountFormExtractor().extract(request, false);
                    if (discountManager.createDiscount(discount)) {
                        request.setAttribute("alert", new Alert(List.of("Sconto creato !"), "success"));
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        request.getRequestDispatcher(view("discount/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageDiscount"));
                    validate(DiscountValidator.validateForm(request, true));
                    Discount updateDiscount = new DiscountFormExtractor().extract(request, true);
                    if (discountManager.updateDiscount(updateDiscount)) {
                        request.setAttribute("discount", updateDiscount);
                        request.setAttribute("alert", new Alert(List.of("sconto aggiornato !"), "success"));
                        request.getRequestDispatcher(view("discount/form")).forward(request, response);
                    } else
                        internalError();
                    break;

                case "/delete":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageDiscount"));
                    int id = Integer.parseInt(request.getParameter("id"));
                    if (discountManager.deleteDiscount(id)) {
                        request.setAttribute("alert", new Alert(List.of("Sconto eliminato!"), "success"));
                        request.getRequestDispatcher(view("crm/manageDiscount")).forward(request, response);
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
