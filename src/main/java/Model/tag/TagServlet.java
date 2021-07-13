package Model.tag;

import Components.Alert;
import Model.discount.Discount;
import Model.discount.DiscountFormExtractor;
import Model.discount.DiscountValidator;
import Model.http.Controller;
import Model.http.InvalidRequestException;
import Model.search.Paginator;
import Model.tag.Tag;
import Model.tag.TagManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "TagServlet", value = "/tags/*")


public class TagServlet extends Controller {

    private TagManager tagManager;

    public void init() throws ServletException {
        try {
            tagManager = new TagManager(source);
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
                    request.setAttribute("back", view("crm/dashboard"));
                    Paginator paginator = new Paginator(1, 10);
                    int size = tagManager.countAll();
                    request.setAttribute("pages", paginator.getPages(size));
                    ArrayList<Tag> tags = tagManager.fetchTags(paginator);
                    request.setAttribute("tags", tags);
                    request.getRequestDispatcher(view("crm/manageTag")).forward(request, response);
                    break;
                case "/create":
                    authorize(request.getSession());
                    request.getRequestDispatcher(view("tag/form")).forward(request, response);
                    break;
                case "/show":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Optional<Tag> tag = tagManager.fetchTag(id);
                    if (tag.isPresent()) {
                        request.setAttribute("tag", tag.get());
                        request.getRequestDispatcher(view("tag/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/search":
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
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
                    request.setAttribute("back", view("crm/manageTag"));
                    validate(TagValidator.validateForm(request,false));
                    Tag tag = new TagFormExtractor().extract(request, false);
                    if (tagManager.createTag(tag)) {
                        request.setAttribute("alert", new Alert(List.of("Tag creato!"), "success"));
                        request.getRequestDispatcher(view("tag/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageTag"));
                    validate(TagValidator.validateForm(request,true));
                    Tag updateTag = new TagFormExtractor().extract(request, true);
                    if (tagManager.updateTag(updateTag)){
                        request.setAttribute("tag", updateTag);
                        request.setAttribute("alert", new Alert(List.of("tag aggiornato !"), "success"));
                        request.getRequestDispatcher(view("tag/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/delete":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageTag"));
                    int id = Integer.parseInt(request.getParameter("id"));
                    if (tagManager.deleteTag(id)) {
                        request.setAttribute("alert", new Alert(List.of("Tag eliminato!"), "success"));
                        response.sendRedirect("/progetto_war_exploded/tags?page=1");
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
