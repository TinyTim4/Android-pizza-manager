package com.example.project5main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * An instance of this class keeps the list of orders placed by the user.
 * This class keep track of the order numbers
 * All instance variables must be private
 * Must include an export() method to save the store orders to an external text file
 * File output performed for exporting store orders should be done in the StoreOrders class
 * system shall be able to export the store orders and save them in a text file, which includes a list of store orders.
 * Each store order shall include the order number, the list of pizzas ordered, and the order total
 * system shall be able to keep track of all the store orders, allow the store staff to browse the store orders and cancel an order.
 * These shall include displaying all the store orders by the order numbers, the order total for each order with 2 decimal places, and the list of pizzas in each order
 * @author Tim Liu, Eric Hou
 */

public class StoreOrders implements Customizable {
    private ArrayList<Order> orders;

    public StoreOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        this.orders = orders;
    }

    /**
     * Adds a order to the store order
     * @param obj
     * @return
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order) {
            Order order = (Order) obj;
            this.orders.add(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order) {
            this.orders.remove(obj);
            return true;
        }
        return false;
    }

    protected int getNumberOfOrders() {
        return orders.size();
    }

    void printOrders() {

    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }



    void displayStoreOrdersByOrderNumber() {

    }

    void cancelOrder() {

    }

    void displayPizzas() {

    }

}