package com.example.project5main;

import java.util.ArrayList;

/**
 * An instance of this class has a unique order number and keeps the list of instances of Pizza class
 * Whenever an order object is created, the system generates a serial number for the order, and the number is a unique integer
 * Each store order shall include the order number, the list of pizzas ordered, and the order total
 * store staff shall be able to remove a pizza or remove all pizzas in the shopping cart. While the staff is removing pizzas from the shopping cart, the system shall update the total amount, sales tax, and the order total accordingly
 * The store staff shall be able to check the detail of the current order before placing the order. The system will keep track of the current order in a shopping cart. The order details in the shopping cart shall include the list of pizzas.
 * Each pizza shall include the pizza style, crust, list of toppings, subtotal for each pizza, total amount of the pizzas in the order, sales tax amount and the order total, which is the total amount plus sales tax. The tax rate is 6.625%.
 * The system shall allow the store staff to add multiple pizzas to the same order and remove selected pizzas from the order.
 * The system shall display a running subtotal with 2 decimal places on the ordering pizza page.
 * If Build your own pizza is chosen, the system shall display a list of toppings for customization. The store maintains at least 13 different toppings every day. The store staff shall be
 * able to customize the pizza by adding or removing the toppings, with a maximum of 7 additional toppings.
 * @author Tim Liu, Eric Hou
 */

public class Order implements Customizable {
    private int orderNumber;
    private ArrayList<Pizza> pizzas;
    private double orderTotal;
    private final double SALES_TAX = 0.06625;
    private int quantity;

    public Order() {
        this.orderNumber = 1;
        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
        this.pizzas = pizzas;
        this.orderTotal = 0;
    }
    public Order(int orderNumber, ArrayList<Pizza> pizzas, double orderTotal) {
        this.orderNumber = orderNumber;
        this.pizzas = pizzas;
        this.orderTotal = orderTotal;
        this.quantity = pizzas.size();
    }

    public Order(Order orderToBeCopied) {
        this.orderNumber = orderToBeCopied.orderNumber;
        ArrayList<Pizza> temp = new ArrayList<>();
        for(int i = 0; i < orderToBeCopied.pizzas.size(); i++) {
            temp.add(orderToBeCopied.pizzas.get(i));
        }
        this.pizzas = temp;
        this.orderTotal = orderToBeCopied.orderTotal;
        this.quantity = orderToBeCopied.quantity;
    }

    /**
     * Adds pizza to order
     * @param obj a pizza object
     * @return true if added, false if not.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Pizza) {
            Pizza pizza = (Pizza) obj;
            return pizzas.add(pizza);
        }
        return false;
    }

    /**
     * Removes pizza from order
     * @param obj a pizza object
     * @return true if removed, false if not.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Pizza) {
            Pizza pizza = (Pizza) obj;
            return pizzas.remove(pizza);
        }
        return false;
    }

    /**
     * Creates a string from the order.
     * @return
     */
    @Override
    public String toString() {
        return "Order number: " + this.orderNumber + ", Pizzas: " + this.pizzasToString();
    }

    /**
     * Remvoes all pizzas from the order
     */
    void removeAllPizzas() {
        pizzas.clear();
    }

    /**
     * Calculate the order total cost
     * @return total order
     */
    public double calculateOrderTotal() {
        return calculateSalesTax() + calculateSubtotal();
    }

    /**
     * Calculates the sales tax
     * @return sales tax
     */
    public double calculateSalesTax() {
        return calculateSubtotal() * SALES_TAX;
    }

    /**
     * Calculates order without tax
     * @return subtotal
     */
    public double calculateSubtotal() {
        double subTotal = 0;
        for (Pizza pizza : this.getPizzas()) {
            subTotal += pizza.price();
        }
        return subTotal;
    }

    /**
     * Iterates through the pizzas container and adds each pizza to a string
     */
    private String pizzasToString() {
        String pizzaString = "";
        for (Pizza pizza : this.getPizzas()) {
            pizzaString += pizza.toString() + ", ";
        }
        pizzaString += "\n";
        return pizzaString;
    }

    // Getters and setters
    public int getOrderNumber() {
        return orderNumber;
    }


    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }


    public void clearOrderTotal() {
        this.orderTotal = 0.0;
    }

    public void addOrderNum() {
        this.orderNumber++;
    }
}