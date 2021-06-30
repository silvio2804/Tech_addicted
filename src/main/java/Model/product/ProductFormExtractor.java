package Model.product;

import Model.category.Category;
import Model.storage.FormExtractor;

import javax.servlet.http.HttpServletRequest;

public class ProductFormExtractor implements FormExtractor<Product> {
    @Override
    public Product extract(HttpServletRequest request, boolean update) {
        Product product = new Product();
        if(update){
            product.setProductId(Integer.parseInt(request.getParameter("productId")));
        }
        product.setName(request.getParameter("name"));
        product.setPrice(Integer.parseInt(request.getParameter("price")));
        product.setDescription(request.getParameter("description"));
        Category category = new Category();
        category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        product.setCategory(category);
        return product;
    }
}
