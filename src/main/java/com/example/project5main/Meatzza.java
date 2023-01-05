package com.example.project5main;

import java.util.ArrayList;

/**
 * This class provides the characteristics of a Meatzza pizza
 * @author Tim Liu, Eric Hou
 */
public class Meatzza extends Pizza {
    public Meatzza(ArrayList<Topping> toppings, Crust crust) {
        super(toppings, crust);
    }

    /**
     * Sets the price of the pizza
     * Small: $15.99
     * Medium: $ 17.99
     * Large: $19.99
     * @return the price
     */
    @Override
    public double price() {
        switch(this.getSize()) {
            case SMALL:
                return 15.99;
            case MEDIUM:
                return 17.99;
            case LARGE:
                return 19.99;
            default:
                return 0;
        }
    }
}