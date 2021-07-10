package Model.account;

import Model.cart.Cart;
import Model.order.Order;
import Model.product.Product;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Account {

    public Account() {
        super();
    }

    public Account(String email){
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String cognome) {
        this.lastName = cognome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Product> getWishList() {
        return wishList;
    }

    public void setWishList(ArrayList<Product> wishList) {
        this.wishList = wishList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String cap) {
        this.city = cap;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Cart> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(ArrayList<Cart> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException { // password è inserita dall'utente
        MessageDigest digest = MessageDigest.getInstance("SHA-512"); //password crittografata
        //SecureRandom ss = new SecureRandom();
        //byte[] salt = new byte[16];
        //ss.nextBytes(salt);
        //digest.update(salt);
        byte[] hashedPwd = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        digest.update(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        for (byte bit : hashedPwd)
            builder.append(String.format("%02x", bit));
        this.password = builder.toString();
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public boolean equals(Object o) {
        if (o != null)
            return false;
        if (o.getClass().getName() != this.getClass().getName())
            return false;
        Account ut = (Account) o;
        return this.id == ut.getId();
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String name;
    private String lastName;
    private int id;
    private LocalDate date;
    private String email;
    private String street;
    private int houseNumber;
    private String city;
    private boolean admin;
    private String password;
    private String gender;

    private ArrayList<Product> wishList;
    private ArrayList<Order> orders;
    private ArrayList<Cart> purchaseHistory;
}
