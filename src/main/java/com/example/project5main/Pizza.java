package com.example.project5main;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Should have 4 subclasses extending the Pizza class, including Deluxe, BBQChicken, Meatzza and BuildYourOwn
 * You can add static final data items (constants.) You can add additional methods if necessary. All flavors of pizzas should be a subtype of Pizza class
 * @author Tim Liu, Eric Hou
 */

//Deluxe
//Deep Dish Brooklyn
//Sausage, pepperoni, green pepper, onion, mushroom
//Small: $14.99
//Medium: $ 16.99
//Large: $18.99
//
//BBQ Chicken
//Pan Thin
//BBQ chicken, green pepper, provolone, cheddar
//Small: $13.99
//Medium: $ 15.99
//Large: $17.99
//
//Meatzza
//Stuffed Hand tossed
//Sausage, pepperoni, beef, ham
//Small: $15.99
//Medium: $ 17.99
//Large: $19.99
//
//Build your own
//Pan Hand tossed
//Customizable, up to 7toppings
//Add $1.59 for each additional topping
//Small: $8.99
//Medium: $ 10.99
//Large: $12.99

public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;
    public abstract double price();

    // Default constructor
    public Pizza() {

    }

    // Constructor for build your own pizzas
    public Pizza(ArrayList<Topping> toppings, Crust crust, Size size) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    // Constructor for deluxe, bbq chicken, and meatzza pizzas
    public Pizza(ArrayList<Topping> toppings, Crust crust) {
        this.toppings = toppings;
        this.crust = crust;
    }

    public Pizza(Crust crust) {
        ArrayList<Topping> toppings = new ArrayList();
        this.toppings = toppings;
        this.crust = crust;
    }

    public Pizza(Pizza pizzaToBeCopied) {
        this.toppings = pizzaToBeCopied.toppings;
        this.crust = pizzaToBeCopied.crust;
        this.size = pizzaToBeCopied.size;
    }

    // Customizable interface

    /**
     * Adds the topping to the pizza
     * @param obj
     * @return true if the topping is successfully added to the pizza, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Topping) {
            Topping topping = (Topping) obj;
            if (toppings.size() < 7) {
                toppings.add(topping);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the topping from the pizza
     * @param obj
     * @return true if the topping is successfully removed from the pizza, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Topping) {
            Topping topping = (Topping) obj;
            if (toppings.contains(topping)) {
                toppings.remove(topping);
                return true;
            }
        }
        return false;
    }

    /**
     * Converts the pizza to a string description
     * @return the pizza as a string
     */
    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        return this.getStyle() +", " + this.getToppingName() + this.getSize()
                + "$" + f.format(this.price());
    }

    /**
     * Gets the crusts and style of the pizza and turns it into a string.
     * @return the style of pizza as a string.
     */
    public String getStyle() {
        if(this.getClass().getSimpleName().equalsIgnoreCase("Deluxe")) {
            if(this.crust.equals(Crust.DEEP_DISH)) {
                return "Deluxe (Chicago Style - Deep Dish)";
            }
            else {
                return "Deluxe (New York Style - Brooklyn)";
            }
        }
        else if(this.getClass().getSimpleName().equalsIgnoreCase("Meatzza")) {
            if(this.crust.equals(Crust.STUFFED)) {
                return "Meatzza (Chicago Style - Stuffed)";
            }
            else {
                return "Meatzza (New York Style - Hand Tossed)";
            }
        }
        else if(this.getClass().getSimpleName().equalsIgnoreCase("BBQChicken")) {
            if(this.crust.equals(Crust.PAN)) {
                return "BBQ Chicken (Chicago Style - Pan)";
            }
            else {
                return "BBQ Chicken (New York Style - Thin)";
            }
        }
        else {
            if(this.crust.equals(Crust.PAN)) {
                return "Build Your Own (Chicago Style - Pan)";
            }
            else {
                return "Build Your Own (New York Style - Hand Tossed)";
            }
        }
    }
    // Getters and setters
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    public Crust getCrust() {
        return crust;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    private String getToppingName() {
        String toplist = "";
        for(int i = 0; i < this.toppings.size(); i++) {
            toplist += toppings.get(i) + ", ";
        }
        return toplist;
    }
}