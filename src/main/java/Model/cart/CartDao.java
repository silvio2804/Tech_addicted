package Model.cart;

import Model.account.Account;
import Model.account.AccountSession;
import Model.product.Product;

import java.util.ArrayList;

public interface CartDao <E extends Exception> {
    ArrayList<Product> fetchProductsByCart(Cart cart) throws E;
    Cart fetchCart(AccountSession account) throws E;

    Cart fetchCartWithProduct(int id)throws E;
}
