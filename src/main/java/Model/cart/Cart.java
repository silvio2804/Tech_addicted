package Model.cart;

import Model.product.Product;
import Model.account.Account;

import java.util.ArrayList;
import java.util.Optional;

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

    public  double totaleCart(){
        double totale=0;
        for(CarItem i: items){
            totale +=i.total();
        }
        return totale;
    }

    public boolean addProduct(Product product, int quantity) {
        Optional<CarItem> optItem = find(product.getProductId());
        if(optItem.isPresent()){
            optItem.get().setQuantity(quantity);
            return true;
        }
        else
            return items.add(new CarItem(product,quantity));
    }

    public Optional<CarItem> find(int id) {
        for (CarItem car : items) {
            if (car.getProduct().getProductId() == id) {
                return Optional.of(car);
            }
        }
        return null;
    }

    public boolean removeProduct(int id) {
        for (CarItem car : items) {
            if (car.getProduct().getProductId() == id) {
                items.remove(car);
                return true;
            }
        }
        return false;
    }

    public void resetCart(){
        items.clear();
    }

    public int quantity(){
        int totalQuantity = 0;
        for (CarItem item: items) {
            totalQuantity+=item.getQuantity();
        }
        return totalQuantity;
    }

    private int idCart;
    private ArrayList<Product> productList;
    private String datePurchase;
    private Account account;
    private ArrayList<CarItem> items;
}
