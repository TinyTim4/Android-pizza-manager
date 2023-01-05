package com.example.project5main;

/**
 * This class provides the characteristics of a Build Your Own pizza
 * @author Tim Liu, Eric Hou
 */
public class BuildYourOwn extends Pizza {
    public BuildYourOwn(Crust crust) {
        super(crust);
    }

    /**
     * Sets the price of a Build Your Own pizza
     * Add $1.59 for each additional topping
     * Small: $8.99
     * Medium: $ 10.99
     * Large: $12.99
     * @return the price
     */
    @Override
    public double price() {
        switch(this.getSize()) {
            case SMALL:
                return 8.99 + (1.59 * (this.getToppings().size()));
            case MEDIUM:
                return 10.99 + (1.59 * (this.getToppings().size()));
            case LARGE:
                return 12.99 + (1.59 * (this.getToppings().size()));
            default:
                return 0;
        }
    }
}