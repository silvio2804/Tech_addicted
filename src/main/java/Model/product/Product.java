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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public ArrayList<Cart> getProdottiCarrello() {
        return cartProducts;
    }

    public void setProdottiCarrello(ArrayList<Cart> prodottiCart) {
        this.cartProducts = prodottiCart;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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
            File file = new File(uploadPath + cover);
            if(!file.exists()){
                Files.copy(fileStream, file.toPath());
            }
            else{
                throw new IOException("Immagine gia esistente!");
            }
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    public ArrayList<Discount> getDiscountProducts() {
        return discountProducts;
    }

    public void setDiscountProducts(ArrayList<Discount> discountProducts) {
        this.discountProducts = discountProducts;
    }

    public String toString() {
        return "Product{" +
                "idProdotto=" + productId +
                ", nome='" + name + '\'' +
                ", prezzo=" + price +
                ", immagine='" + cover + '\'' +
                ", descrizione='" + description + '\'' +
                ", prodottiCart=" + cartProducts +
                ", category=" + category +
                ", tags=" + tags +
                ", UtentiDesideri=" + wishList +
                ", scontiProdotto=" + discountProducts +
                '}';
    }

    private int productId;
    private String name;
    private double price;
    private String cover;
    private String description;
    private ArrayList<Cart> cartProducts; //vedere in quanti carrelli si trova il prodotto?? potrebbe servire a questo
    private Category category;
    private ArrayList<Tag> tags;
    private ArrayList<Account> wishList;
    private ArrayList<Discount> discountProducts;
}
