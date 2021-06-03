package BusinessLayer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {
    private static int id=1;
    private final int orderID;
    private int clientID;
    private final Date orderDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public Order(int clientID) {
        this.orderID =id;
        id++;
        this.setClientID(clientID);
        this.orderDate = new Date();
    }

    @Override
    public int hashCode() {
        return this.orderID;
    }

    public String toString(){
        return "Order ID: "+this.getOrderID()+", "+dateFormat.format(orderDate)+", client ID: "+this.getClientID();
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getOrderID() {
        return this.orderID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getClientID() {
        return clientID;
    }

}
