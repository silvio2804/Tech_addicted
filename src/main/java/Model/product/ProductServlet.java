package Model.product;

import Components.Alert;
import Model.category.Category;
import Model.http.Controller;
import Model.http.ErrorHandler;
import Model.http.InvalidRequestException;
import Model.search.Condition;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products/*")
@MultipartConfig
public class ProductServlet extends Controller implements ErrorHandler {
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
                    authorize(request.getSession(false));
                    request.setAttribute("back",view("crm/product"));
                    validate(ProductValidator.validateForm(request));
                    Product product = new ProductFormMapper().map(request,false);
                    ProdottoDao<SQLException> prodottoDao = new ProdottoManager(source);
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
                        product.writeCover(getUploadPath(), request.getPart("cover"));
                        request.setAttribute("alert",new Alert(List.of("Prodotto creato!"),"sucess"));
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        request.getRequestDispatcher(view("crm/product")).forward(request, response);
                    } else {
                        //internalError();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore Server");
                    }
                    break;
                case "/search":
                    List<Condition> conditions = new ProductSearch().buildSearch(request);
                    List<Product> searcProducts = conditions.isEmpty() ?
                            prodottoDao.fetchProdottiUtente(1);//prodottoDao.fetchProductWIthRelations(newPaginator(1,50)):
                            prodottoDao.search(conditions);
                            request.setAttribute("products",searcProducts);
                            request.getRequestDispatcher(view("site/search")).forward(request,response);
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back",view("crm/product"));
                    validate(ProductValidator.validateForm(request));
                    Product updateProduct = new ProductFormMapper().map(request,false);
                    request.setAttribute("product",updateProduct);
                    if(prodottoDao.updateProduct(updateProduct)){
                        updateProduct.writeCover(getUploadPath(), request.getPart("cover"));
                        request.setAttribute("alert",new Alert(List.of("Prodotto aggiornato!"),"sucess"));
                        request.getRequestDispatcher(view("crm/product")).forward(request, response);
                    }
                    else{
                        internalError();
                    }
                    break;
                default:
                    notFound();
            }
        } catch (SQLException | InvalidRequestException throwables) {
            throwables.printStackTrace();
        }
    }
}


// DA COMPLETARE