package Model.cart;

import Model.product.Product;

public class CarItem {

    public CarItem(Product product, int quantita) {
        this.product = product;
        this.quantita = quantita;
    }

    public Product getProdotto() {
        return product;
    }

    public int getQuantity() {
        return quantita;
    }

    public double totale(){
        return this.product.getPrezzo()*quantita;
    }

    private final Product product;
    private final int quantita;
}
