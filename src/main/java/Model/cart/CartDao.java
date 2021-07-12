package Model.cart;

import Model.product.Product;

import java.util.ArrayList;

public interface CartDao <E extends Exception> {
    ArrayList<Product> fetchProductsByCart(Cart cart) throws E;

    Cart fetchCartWithProduct(int id)throws E;
}
