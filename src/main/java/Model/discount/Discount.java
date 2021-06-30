package Model.discount;

import Model.product.Product;

import java.util.ArrayList;

public class Discount {

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public void setDiscountedProducts(ArrayList<Product> discountedProducts) {
        this.discountedProducts = discountedProducts;
    }

    public ArrayList<Product> getDiscountedProducts() {
        return discountedProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return discountId == discount.discountId;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountId=" + discountId +
                ", percentage=" + percentage +
                ", discountName='" + discountName + '\'' +
                ", discountedProducts=" + discountedProducts +
                '}';
    }

    private int discountId;
    private int percentage;
    private String discountName;
    private ArrayList<Product> discountedProducts;
}
