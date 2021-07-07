package Model.cart;

import Model.product.Product;
import Model.account.Account;

import java.util.ArrayList;

public class Cart {

    public Cart(ArrayList<CarItem> items){
        this.items = items;
    }

    public ArrayList<CarItem> getItems() {
        return items;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public String getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(String datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public  double totaleCarrello(){
        double totale=0;
        for(CarItem i: items){
            totale +=i.total();
        }
        return totale;
    }

    public void addProdotto(Product product, int quantita) {
        CarItem item = new CarItem(product,quantita);
        items.add(item);
    }

    private int idCart;
    private ArrayList<Product> productList;
    private String datePurchase;
    private Account account;
    private ArrayList<CarItem> items;
}
