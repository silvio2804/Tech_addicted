package Model;

import java.util.ArrayList;

public class Carrello {

    public Carrello(){
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


    private String idCarrello;
    private ArrayList<Prodotto> listaProdotti;
    private String dataAcquisto;
}
