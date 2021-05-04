package Model.prodotto;

import Model.carrello.Carrello;
import Model.categoria.Categoria;
import Model.parolaChiave.ParolaChiave;

import java.util.ArrayList;

public class Prodotto {
    public Prodotto(){
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public ArrayList<Carrello> getProdottiCarrello() {
        return ProdottiCarrello;
    }

    public void setProdottiCarrello(ArrayList<Carrello> prodottiCarrello) {
        ProdottiCarrello = prodottiCarrello;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<ParolaChiave> getParoleChiavi() {
        return paroleChiavi;
    }

    public void setParoleChiavi(ArrayList<ParolaChiave> paroleChiavi) {
        this.paroleChiavi = paroleChiavi;
    }

    private int idProdotto;
    private String nome;
    private double prezzo;
    private String immagine;
    private String colore;
    private String descrizione;

    private ArrayList<Carrello> ProdottiCarrello; //vedere in quanti carrelli si trova il prodotto?? potrebbe servire a questo
    private Categoria categoria;
    private ArrayList<ParolaChiave> paroleChiavi;
}
