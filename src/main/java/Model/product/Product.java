package Model.product;

import Model.account.Account;
import Model.cart.Cart;
import Model.category.Category;
import Model.discount.Discount;
import Model.tag.Tag;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;

public class Product {
    public Product(){
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

    public ArrayList<Cart> getProdottiCarrello() {
        return prodottiCart;
    }

    public void setProdottiCarrello(ArrayList<Cart> prodottiCart) {
        this.prodottiCart = prodottiCart;
    }

    public Category getCategoria() {
        return category;
    }

    public void setCategoria(Category category) {
        this.category = category;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setParoleChiavi(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public void writeCover(String uploadPath, Part stream) throws IOException {
        try(InputStream fileStream = stream.getInputStream()){
            File file = new File(uploadPath + immagine);
            Files.copy(fileStream, file.toPath());
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return idProdotto == product.idProdotto;
    }

    public ArrayList<Discount> getScontiProdotto() {
        return scontiProdotto;
    }

    public void setScontiProdotto(ArrayList<Discount> scontiProdotto) {
        this.scontiProdotto = scontiProdotto;
    }

    public String toString() {
        return "Product{" +
                "idProdotto=" + idProdotto +
                ", nome='" + nome + '\'' +
                ", prezzo=" + prezzo +
                ", immagine='" + immagine + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prodottiCart=" + prodottiCart +
                ", category=" + category +
                ", tags=" + tags +
                ", UtentiDesideri=" + UtentiDesideri +
                ", scontiProdotto=" + scontiProdotto +
                '}';
    }

    private int idProdotto;
    private String nome;
    private double prezzo;
    private String immagine;
    private String descrizione;
    private ArrayList<Cart> prodottiCart; //vedere in quanti carrelli si trova il prodotto?? potrebbe servire a questo
    private Category category;
    private ArrayList<Tag> tags;
    private ArrayList<Account> UtentiDesideri;
    private ArrayList<Discount> scontiProdotto;
}
