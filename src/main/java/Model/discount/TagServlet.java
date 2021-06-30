package Model.discount;

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

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path){
                case "/":
                    authorize(request.getSession());
                    request.setAttribute("back",view("crm/product"));
                    ArrayList<Tag> tags = tagManager.fetchTags(new Paginator(-1,30));
                    request.setAttribute("tags",tags);
                    request.getRequestDispatcher(view("crm/manageTag")).forward(request, response);
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
