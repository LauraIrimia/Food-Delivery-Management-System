package DataLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Serializator {

    public void serializeProducts(Object o) {
        FileOutputStream file;
        try {
            file = new FileOutputStream("src/main/resources/products.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(o);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Set<MenuItem> deserializeProducts() {
        Set<MenuItem> itemsSet = new HashSet<>();
        FileInputStream file;
        try {
            file = new FileInputStream("src/main/resources/products.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            itemsSet = (Set<MenuItem>) in.readObject();
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemsSet;
    }

    public void serializeOrders(Object o) {
        FileOutputStream file;
        try {
            file = new FileOutputStream("src/main/resources/orders.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(o);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HashMap<Order, Set<MenuItem>> deserializeOrders() {
        HashMap<Order, Set<MenuItem>> itemsSet = new HashMap<>();
        FileInputStream file;
        try {
            file = new FileInputStream("src/main/resources/orders.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            itemsSet = (HashMap) in.readObject();
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemsSet;
    }
}
