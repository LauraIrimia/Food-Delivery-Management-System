package DataLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class FileWriter {
    public void createBill(HashMap<Order, Set<MenuItem>> orderSetHashMap, Order order){
        try {
            File myFile = new File("src/main/resources/bill" + order.getOrderID() + ".txt");
            java.io.FileWriter myWriter = new java.io.FileWriter(myFile);
            myWriter.write(order.toString()+"\n");
            for(MenuItem m:orderSetHashMap.get(order)){
                myWriter.write(m.toString()+"\n");
            }
            int price=0;
            for(MenuItem m:orderSetHashMap.get(order)){
                price=price+m.getPrice();
            }
            myWriter.write("Order price: "+price);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
