package Model.product;

import Components.Alert;
import Model.category.Category;
import Model.http.Controller;
import Model.http.ErrorHandler;
import Model.http.InvalidRequestException;
import Model.search.Condition;
import Model.search.Paginator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products/*")
@MultipartConfig
public class ProductServlet extends Controller implements ErrorHandler {

    private ProductManager productManager;

    public void init() throws ServletException {
        super.init();
        try {
            productManager = new ProductManager(source);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/":
                    authorize(request.getSession());
                    request.setAttribute("back",view("crm/product"));
                    ArrayList<Product> products = productManager.fetchProducts(new Paginator(1,30));
                    request.setAttribute("products",products);
                    request.getRequestDispatcher(view("crm/manageProduct")).forward(request, response);
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
                default:
                    notFound();
            }
        }
        catch (InvalidRequestException | SQLException t){
            t.printStackTrace();
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
                    Product product = new ProductFormExtractor().extract(request,false);
                    ProductDao<SQLException> prodottoDao = new ProductManager(source);
                    product.setPrice(Double.parseDouble(request.getParameter("price")));
                    product.setName(request.getParameter("name"));
                    product.setDescription(request.getParameter("description"));
                    Part filePart = request.getPart("cover");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    product.setCover(fileName);
                    Category category = new Category();
                    category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
                    product.setCategory(category);
                    if(prodottoDao.createProduct(product)) {
                        product.writeCover(getUploadPath(), request.getPart("cover"));
                        request.setAttribute("alert",new Alert(List.of("Prodotto creato!"),"success"));
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        request.getRequestDispatcher(view("crm/product")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/search":
                    ProductDao<SQLException> productManager = new ProductManager(source);
                    List<Condition> conditions = new ProductSearch().buildSearch(request);
                    List<Product> searchProducts = conditions.isEmpty() ? productManager.fetchProducts(new Paginator(1,50)):
                            productManager.search(conditions);
                            request.setAttribute("products",searchProducts);
                            request.getRequestDispatcher(view("site/search")).forward(request,response);
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back",view("crm/product"));
                    validate(ProductValidator.validateForm(request));
                    /*Product updateProduct = new ProductFormMapper().map(request,false);
                    request.setAttribute("product",updateProduct);
                    if(prodottoDao.updateProduct(updateProduct)){
                        updateProduct.writeCover(getUploadPath(), request.getPart("cover"));
                        request.setAttribute("alert",new Alert(List.of("Prodotto aggiornato!"),"sucess"));
                        request.getRequestDispatcher(view("crm/product")).forward(request, response);
                    }
                    else{
                        internalError();
                    }*/
                    break;
                default:
                    notFound();
            }
        } catch (SQLException | InvalidRequestException t) {
            t.printStackTrace();
        }
    }
}


// DA COMPLETARE