package Model.order;

import Model.cart.Cart;
import Model.cart.CarItem;
import Model.account.Account;

import java.time.LocalDate;
import java.util.Date;

public class Order {

    public Order(){
        super();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Cart getCarrello() {
        return cart;
    }

    public void setCarrello(Cart cart) {
        this.cart = cart;
    }
    public int entries() {
        int entries = 0;
        for(CarItem item: cart.getItems()){
            entries++;
        }
        return entries;
    }

    @Override
    public String toString() {
        return "Order{" +
                "total=" + total +
                ", payment='" + payment + '\'' +
                ", orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", account=" + account +
                ", cart=" + cart +
                '}';
    }

    private double total;
    private String payment;
    private int orderId;
    private Date orderDate;
    private Account account;
    private Cart cart;

}
