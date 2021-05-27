package Model.order;

import Model.cart.Cart;
import Model.cart.CarItem;
import Model.account.Account;

import java.time.LocalDate;

public class Order {
    public Order(){
        super();
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Account getUtente() {
        return utente;
    }

    public void setUtente(Account utente) {
        this.utente = utente;
    }

    public Cart getCarrello() {
        return cart;
    }

    public void setCarrello(Cart cart) {
        this.cart = cart;
    }

    private double totale;
    private String pagamento;
    private int idOrdine;
    private LocalDate dataOrdine;
    private Account utente;
    private Cart cart;

    public int entries() {
        int entries = 0;
        for(CarItem item: cart.getItems()){
            entries++;
        }
        return entries;
    }
}
