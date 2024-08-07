package Model.category;

import Model.storage.FormExtractor;

import javax.servlet.http.HttpServletRequest;

public class CategoryFormExtractor implements FormExtractor<Category> {
    @Override
    public Category extract(HttpServletRequest request, boolean update) {
        Category category = new Category();
        if(update){
            category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        }
        category.setCategoryName(request.getParameter("categoryName"));
        return category;
    }
}
