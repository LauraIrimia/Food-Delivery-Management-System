package BusinessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public abstract int computePrice();
    public String toString() {
        return "Title: "+this.getTitle()+", rating: "+this.getRating()+", calories: "+this.getCalories()+", protein: "
                +this.getProtein()+", fat: "+this.getFat()+", sodium: "+this.getSodium()+", price: "+this.getPrice();
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getProtein() {
        return protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getFat() {
        return fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getSodium() {
        return sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
