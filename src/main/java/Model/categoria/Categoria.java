package Model.categoria;

import Model.prodotto.Prodotto;

import java.util.ArrayList;

public class Categoria {

    public Categoria(){
        super();
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    private String nomeCategoria;
    private ArrayList<Prodotto> prodotti;   //lista di prodotti appartenente alla categoria
}
