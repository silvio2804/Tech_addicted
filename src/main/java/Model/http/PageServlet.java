package Model.http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//servlet per le pagine utente
@WebServlet(name = "pageServlet",value = "/pages/*")
public class PageServlet extends Controller {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/dashboard":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher(view("crm/home")).forward(request, response);
                    break;
                case "/":
                    request.getRequestDispatcher(view("site/home")).forward(request, response);
                    break;
                default: notAllowed();
            }
        } catch (ServletException  | InvalidRequestException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
