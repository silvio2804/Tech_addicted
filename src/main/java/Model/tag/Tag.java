package Model.tag;

import Model.product.Product;

import java.util.ArrayList;

public class Tag {

    public Tag(){
        super();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<Product> getProdotto() {
        return products;
    }

    public void setProdotto(ArrayList<Product> products) {
        this.products = products;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return tagId == tag.tagId;
    }

    private String word;
    private int tagId;
    private ArrayList<Product> products;
}
