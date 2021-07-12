package Model.category;

import Model.http.JsonSerializable;
import Model.product.Product;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.util.ArrayList;

public class Category implements JsonSerializable {

    public Category(){
        super();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "nomeCategoria='" + categoryName + '\'' +
                ", idCategoria=" + categoryId +
                ", prodotti=" + products +
                '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("categoryId",this.categoryId);
        object.put("categoryName",this.categoryName);
        return object;
    }

    private String categoryName;
    private int categoryId;
    private ArrayList<Product> products;
}
