package Model.carrello;

import Model.prodotto.Prodotto;
import Model.utente.Utente;

import java.util.ArrayList;

public class Carrello {

    public Carrello(ArrayList<carItem> items){
        this.items = items;
    }

    public ArrayList<carItem> getItems() {
        return items;
    }

    public String getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(String idCarrello) {
        this.idCarrello = idCarrello;
    }

    public ArrayList<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(ArrayList<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public String getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(String dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public  double totaleCarrello(){
        double totale=0;
        for(carItem i: items){
            totale +=i.totale();
        }
        return totale;
    }

    private String idCarrello;
    private ArrayList<Prodotto> listaProdotti;
    private String dataAcquisto;
    private Utente utente;
    private ArrayList<carItem> items;
}
