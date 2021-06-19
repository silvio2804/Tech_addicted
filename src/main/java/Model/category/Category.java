package Model.category;

import Model.product.Product;

import java.util.ArrayList;

public class Category {

    public Category(){
        super();
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public ArrayList<Product> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Product> prodotti) {
        this.prodotti = prodotti;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return idCategoria == category.idCategoria;
    }

    @Override
    public String toString() {
        return "Category{" +
                "nomeCategoria='" + nomeCategoria + '\'' +
                ", idCategoria=" + idCategoria +
                ", prodotti=" + prodotti +
                '}';
    }

    private String nomeCategoria;
    private int idCategoria;
    private ArrayList<Product> prodotti;   //lista di prodotti appartenente alla categoria
}
