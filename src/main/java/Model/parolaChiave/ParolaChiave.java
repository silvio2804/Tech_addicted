package Model.parolaChiave;

import Model.prodotto.Prodotto;

import java.util.ArrayList;

public class ParolaChiave {

    public ParolaChiave(){
        super();
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public ArrayList<Prodotto> getProdotto() {
        return prodotto;
    }

    public void setProdotto(ArrayList<Prodotto> prodotto) {
        this.prodotto = prodotto;
    }

    private String parola;
    private ArrayList<Prodotto> prodotto;
}
