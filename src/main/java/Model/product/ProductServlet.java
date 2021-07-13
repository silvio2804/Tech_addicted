package Model.product;

import Components.Alert;
import Model.category.Category;
import Model.category.CategoryManager;
import Model.http.Controller;
import Model.http.ErrorHandler;
import Model.http.InvalidRequestException;
import Model.http.RequestValidator;
import Model.search.Condition;
import Model.search.Paginator;
import Model.tag.Tag;
import Model.tag.TagManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ProductServlet", value = "/products/*")
@MultipartConfig
public class ProductServlet extends Controller implements ErrorHandler {

    private ProductManager productManager;
    private ArrayList<Category> categories;
    private CategoryManager categoryManager;
    private TagManager tagManager;

    public void init() throws ServletException {
        super.init();
        try {
            productManager = new ProductManager(source);
            categories = productManager.fetchCategoriesByProducts();
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
                    HttpSession session = request.getSession();
                    authorize(session);
                    request.setAttribute("back", view("crm/product"));
                    int page = parsePage(request);
                    Paginator paginator = new Paginator(page, 10);
                    int size = productManager.countAll();
                    request.setAttribute("pages", paginator.getPages(size));
                    ArrayList<Product> products = productManager.fetchProducts(paginator);
                    request.setAttribute("products", products);
                    session.setAttribute("categories", categories);
                    request.getRequestDispatcher(view("crm/manageProduct")).forward(request, response);
                    break;
                case "/show":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Optional<Product> product = productManager.fetchProduct(id);
                    if (product.isPresent()) {
                        request.setAttribute("product", product.get());
                        request.setAttribute("categories", categories);
                        request.getRequestDispatcher(view("product/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/showCli":
                    int id2 = Integer.parseInt(request.getParameter("id"));
                    Optional<Product> product2 = productManager.fetchProduct(id2);
                    if (product2.isPresent()) {
                        request.setAttribute("product", product2.get());
                        request.setAttribute("categories", categories);
                        request.getRequestDispatcher(view("site/product")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/create":
                    authorize(request.getSession());
                    request.getRequestDispatcher(view("product/form")).forward(request, response);
                    break;
                case "/search":
                    ProductSearch productSearch = new ProductSearch();
                    List<Condition> conditions = productSearch.buildSearch(request);
                    List<Product> searchProd = productManager.search(conditions);
                    request.setAttribute("products", searchProd);
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;
                case "/advancedSearch":
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;
                case "/searchByCat":
                    categoryManager = new CategoryManager(source);
                    int idCat = Integer.parseInt(request.getParameter("categoryId"));
                    Optional<Category> category = categoryManager.fetchCategory(idCat);
                    if (category.isPresent()) {
                        ArrayList<Product> prod = productManager.fetchProductsByCategory(category.get());
                        request.setAttribute("products", prod);
                        request.getRequestDispatcher(view("site/search")).forward(request, response);
                    } else notFound();
                    break;

                case "/searchByTag":
                    tagManager = new TagManager(source);
                    int idTag = Integer.parseInt(request.getParameter("tag"));
                    Optional<Tag> tag = tagManager.fetchTag(idTag);
                    if (tag.isPresent()) {
                        ArrayList<Product> prod = productManager.fetchProductsByTag(tag.get());
                        request.setAttribute("products", prod);
                        request.getRequestDispatcher(view("site/search")).forward(request, response);
                    } else notFound();
                    break;
                default:
                    notFound();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (InvalidRequestException ex) {
            ex.printStackTrace();
            log(ex.getMessage());
            ex.handle(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/create":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("product/form"));
                    validate(ProductValidator.validateForm(request));
                    RequestValidator requestValidator = ProductValidator.validateForm(request);
                    Product product = new ProductFormExtractor().extract(request, false);
                    Part filePart = request.getPart("cover");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    product.setCover(fileName);
                    product.writeCover(getUploadPath(), request.getPart("cover"));
                    if (productManager.createProduct(product)) {
                        request.setAttribute("alert", new Alert(List.of("Prodotto creato!"), "success"));
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        request.getRequestDispatcher(view("product/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/search":
                    List<Condition> conditions = new ProductSearch().buildSearch(request);
                    List<Product> searchProducts = conditions.isEmpty() ? productManager.fetchProducts(new Paginator(1, 50)) :
                            productManager.search(conditions);
                    request.setAttribute("products", searchProducts);
                    request.getRequestDispatcher(view("site/search")).forward(request, response);
                    break;
                case "/update":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/product"));
                    validate(ProductValidator.validateForm(request));
                    Product updateProduct = new ProductFormExtractor().extract(request, true);
                    Part filePart1 = request.getPart("cover");
                    String fileName1 = Paths.get(filePart1.getSubmittedFileName()).getFileName().toString();
                    updateProduct.setCover(fileName1);
                    if (productManager.updateProduct(updateProduct)) {
                        updateProduct.writeCover(getUploadPath(), request.getPart("cover"));
                        request.setAttribute("product", updateProduct);
                        request.setAttribute("alert", new Alert(List.of("Prodotto aggiornato!"), "success"));
                        request.getRequestDispatcher(view("product/form")).forward(request, response);
                    } else
                        internalError();
                    break;
                case "/delete":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("crm/manageProduct"));
                    int id = Integer.parseInt(request.getParameter("id"));
                    if (productManager.deleteProduct(id)) {
                        request.setAttribute("alert", new Alert(List.of("Prodotto eliminato!"), "success"));
                        response.sendRedirect("/progetto_war_exploded/products?page=1");
                    } else
                        internalError();
                    break;
                default:
                    notFound();
            }
        } catch (SQLException t) {
            t.printStackTrace();
            log(t.getMessage());
        } catch (InvalidRequestException  ex) {
            ex.printStackTrace();
            log(ex.getMessage());
            ex.handle(request, response);
        }
        catch (IOException ioException){
            ioException.printStackTrace();
            log(ioException.toString());
            response.sendRedirect("/progetto_war_exploded/products/create");
        }
    }
}
