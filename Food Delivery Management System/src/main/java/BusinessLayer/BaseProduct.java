package BusinessLayer;

public class BaseProduct extends MenuItem{

    public BaseProduct(String title, double rating, int calories,int protein, int fat,int sodium,int price) {
        this.setTitle(title);
        this.setRating(rating);
        this.setCalories(calories);
        this.setProtein(protein);
        this.setFat(fat);
        this.setSodium(sodium);
        this.setPrice(price);
    }
    @Override
    public int computePrice() {
        return this.getPrice();
    }

}
