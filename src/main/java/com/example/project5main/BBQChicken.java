package com.example.project5main;

import java.util.ArrayList;

/**
 * This class provides the characteristics of a BBQ chicken pizza
 * @author Tim Liu, Eric Hou
 */
public class BBQChicken extends Pizza {
    public BBQChicken(ArrayList<Topping> toppings, Crust crust) {
        super(toppings, crust);
    }

    /**
     * Sets the price of the pizza
     * Small: $13.99
     * Medium: $ 15.99
     * Large: $17.99
     * @return the price
     */
    @Override
    public double price() {
        switch(this.getSize()) {
            case SMALL:
                return 13.99;
            case MEDIUM:
                return 15.99;
            case LARGE:
                return 17.99;
            default:
                return 0;
        }
    }
}