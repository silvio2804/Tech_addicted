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

    public String getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(String idCarrello) {
        this.idCarrello = idCarrello;
    }

    public ArrayList<Product> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(ArrayList<Product> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public String getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(String dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public Account getUtente() {
        return utente;
    }

    public void setUtente(Account utente) {
        this.utente = utente;
    }

    public  double totaleCarrello(){
        double totale=0;
        for(CarItem i: items){
            totale +=i.totale();
        }
        return totale;
    }
    public void addProdotto(Product product, int quantita) {
        CarItem item = new CarItem(product,quantita);
        items.add(item);
    }

    private String idCarrello;
    private ArrayList<Product> listaProdotti;
    private String dataAcquisto;
    private Account utente;
    private ArrayList<CarItem> items;
}
