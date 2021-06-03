package BusinessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
    private ArrayList<BaseProduct> compositeProduct = new ArrayList<>();

    public CompositeProduct(String title, ArrayList<BaseProduct> products) {
        this.setTitle(title);
        double r=0;
        int c=0,p=0,f=0,s=0;
        for (BaseProduct b : products){
            this.compositeProduct.add(b);
            r=r+b.getRating();
            c=c+b.getCalories();
            p=p+b.getProtein();
            f=f+b.getFat();
            s=s+b.getSodium();
        }
        this.setPrice(this.computePrice());
        this.setRating(r/products.size());
        this.setCalories(c);
        this.setProtein(p);
        this.setFat(f);
        this.setSodium(s);
    }

    @Override
    public int computePrice() {
        int p = 0;
        for (BaseProduct b : this.compositeProduct)
            p = p + b.getPrice();
        return p;
    }

    public String toString() {
        String s = "Composite product: '"+ this.getTitle()+"' composed of ";
        s = s + this.compositeProduct.size() + " products: \n";
        for(int i=0;i<compositeProduct.size()-1;i++){
            s= s+ (i+1)+" -> "+compositeProduct.get(i)+"\n";
        }
        s=s+compositeProduct.size()+" -> "+compositeProduct.get(compositeProduct.size()-1);
        return s;
    }

    public void setCompositeProduct(ArrayList<BaseProduct> compositeProduct) {
        this.compositeProduct = compositeProduct;
    }

    public ArrayList<BaseProduct> getCompositeProduct() {
        return compositeProduct;
    }

}
