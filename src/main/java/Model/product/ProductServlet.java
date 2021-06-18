package Model.product;

import Model.category.Category;
import Model.http.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

@WebServlet(name = "ProductServlet", value = "/products/*")
@MultipartConfig //per inviare file binari
public class ProductServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);
        switch (path) {
            case "/":
                request.getRequestDispatcher(view("crm/products")).forward(request, response);
                break;
            case "/show":
                request.getRequestDispatcher(view("crm/product")).forward(request, response);
                break;
            case "/create":
                request.getRequestDispatcher(view("crm/product")).forward(request, response);
                break;
            case "/search":
                request.getRequestDispatcher(view("site/search")).forward(request, response);
                break;
            case "/advancedSearch":
                request.getRequestDispatcher(view("site/search")).forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/create":
                    ProdottoDao<SQLException> prodottoDao = new SqlProdottoDao(source); //?
                    Product product = new Product();
                    product.setPrezzo(Double.parseDouble(request.getParameter("price")));
                    product.setNome(request.getParameter("name"));
                    product.setDescrizione(request.getParameter("description"));
                    Part filePart = request.getPart("cover");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    product.setImmagine(fileName);
                    Category category = new Category();
                    category.setIdCategoria(Integer.parseInt(request.getParameter("categoryId")));
                    product.setCategoria(category);
                    if(prodottoDao.creaProdotto(product)) {
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                        String uploadRoot = getUploadPath(); //?
                        try(InputStream fileStream = filePart.getInputStream()){
                            File file = new File(uploadRoot + fileName);
                            Files.copy(fileStream, file.toPath());

                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore Server");
                    }
                    break;
            }
        }
    }
}
// DA COMPLETARE