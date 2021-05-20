package Model.sconto;

import Model.prodotto.Prodotto;

import java.util.ArrayList;
import java.util.Objects;

public class Sconto {

    public int getIdSconto() {
        return idSconto;
    }

    public void setIdSconto(int idSconto) {
        this.idSconto = idSconto;
    }

    public int getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(int percentuale) {
        this.percentuale = percentuale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sconto sconto = (Sconto) o;
        return idSconto == sconto.idSconto;
    }



    public ArrayList<Prodotto> getProdottiScontati() {
        return prodottiScontati;
    }

    public void setProdottiScontati(ArrayList<Prodotto> prodottiScontati) {
        this.prodottiScontati = prodottiScontati;
    }


    private int idSconto;
    private int percentuale;
    private ArrayList<Prodotto> prodottiScontati;
}
