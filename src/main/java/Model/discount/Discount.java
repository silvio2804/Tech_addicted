package Model.discount;

import Model.product.Product;

import java.util.ArrayList;

public class Discount {

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
        Discount discount = (Discount) o;
        return idSconto == discount.idSconto;
    }



    public ArrayList<Product> getProdottiScontati() {
        return prodottiScontati;
    }

    public void setProdottiScontati(ArrayList<Product> prodottiScontati) {
        this.prodottiScontati = prodottiScontati;
    }


    private int idSconto;
    private int percentuale;
    private ArrayList<Product> prodottiScontati;
}
