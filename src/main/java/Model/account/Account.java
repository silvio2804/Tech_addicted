package Model.account;

import Model.cart.Cart;
import Model.order.Order;
import Model.product.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Account {

    public Account(){
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataNa() {
        return dataNa;
    }

    public void setDataNa(LocalDate dataNa) {
        this.dataNa = dataNa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(int numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String cap) {
        this.citta = cap;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ArrayList<Order> getOrdini() {
        return ordini;
    }

    public void setOrdini(ArrayList<Order> ordini) {
        this.ordini = ordini;
    }

    public ArrayList<Cart> getStorico() {
        return storico;
    }

    public void setStorico(ArrayList<Cart> storico) {
        this.storico = storico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getSesso() {
        return sesso;
    }

    public boolean equals(Object o){
        if(o != null)
            return false;
        if(o.getClass().getName()!= this.getClass().getName())
            return false;
        Account ut = (Account) o;
        return this.id == ut.getId();
    }

    private String nome;
    private String cognome;
    private int id;
    private LocalDate dataNa;
    private String email;
    private String via;
    private int numeroCivico;
    private String citta;
    private boolean admin;
    private String password;
    private String sesso;

    private ArrayList<Product> listaDesideri;
    private ArrayList<Order> ordini;
    private ArrayList<Cart> storico;
}
