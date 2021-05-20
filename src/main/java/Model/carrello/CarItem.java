package Model.carrello;

import Model.prodotto.Prodotto;

public class CarItem {

    public CarItem(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public int getQuantity() {
        return quantita;
    }

    public double totale(){
        return this.prodotto.getPrezzo()*quantita;
    }

    private final Prodotto prodotto;
    private final int quantita;
}
