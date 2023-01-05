package com.example.project5main;

import java.util.ArrayList;

/**
 * This class provides the characteristics of a Deluxe pizza
 * @author Tim Liu, Eric Hou
 */
public class Deluxe extends Pizza {
    public Deluxe(ArrayList<Topping> toppings, Crust crust) {
        super(toppings, crust);
    }

    /**
     * Sets the price of the deluxe pizza
     * Small: $14.99
     * Medium: $ 16.99
     * Large: $18.99
     * @return
     */
    @Override
    public double price() {
        switch(this.getSize()) {
            case SMALL:
                return 14.99;
            case MEDIUM:
                return 16.99;
            case LARGE:
                return 18.99;
            default:
                return 0;
        }
    }
}