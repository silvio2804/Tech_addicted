package Model.tag;

import Model.product.Product;

import java.util.ArrayList;

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

    public ArrayList<Product> getProdotto() {
        return products;
    }

    public void setProdotto(ArrayList<Product> products) {
        this.products = products;
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

    private ArrayList<Product> products;
}
