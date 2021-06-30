package Model.http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SiteServlet",value ="/site/*")
public class SiteServlet extends Controller {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String path = getPath(request);
        try {
            switch (path) {
                case "/home":
                    request.getRequestDispatcher(view("site/home")).forward(request, response);
                default:
                    internalError();
            }
        }
        catch (InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    }
}