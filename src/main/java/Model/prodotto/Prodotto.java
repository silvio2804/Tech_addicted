package Model.prodotto;

import Model.carrello.Carrello;
import Model.categoria.Categoria;
import Model.sconto.Sconto;
import Model.tag.Tag;
import Model.utente.Utente;

import java.util.ArrayList;
import java.util.Objects;

public class Prodotto {
    public Prodotto(){
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

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setParoleChiavi(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return idProdotto == prodotto.idProdotto;
    }


    public ArrayList<Sconto> getScontiProdotto() {
        return scontiProdotto;
    }

    public void setScontiProdotto(ArrayList<Sconto> scontiProdotto) {
        this.scontiProdotto = scontiProdotto;
    }

    private int idProdotto;
    private String nome;
    private double prezzo;
    private String immagine;
    private String descrizione;
    private ArrayList<Carrello> ProdottiCarrello; //vedere in quanti carrelli si trova il prodotto?? potrebbe servire a questo
    private Categoria categoria;
    private ArrayList<Tag> tags;
    private ArrayList<Utente> UtentiDesideri;
    private ArrayList<Sconto> scontiProdotto;
}
