package Model.cart;

import Model.product.Product;

public class CarItem {

    public CarItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double total(){
        return this.product.getPrice()* quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private final Product product;
    private  int quantity;
}
