package Model.tag;

import Model.prodotto.Prodotto;

import java.util.ArrayList;
import java.util.Objects;

public class Tag {

    public Tag(){
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
    private int idTag;

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return idTag == tag.idTag;
    }

    private ArrayList<Prodotto> prodotto;
}
