package com.example.project5main;

// User created custom class (all classes not specified as such are required)

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Sets the characteristics of a Chicago style pizza
 * @author Tim Liu, Eric Hou
 */
public class NYPizza implements PizzaFactory {
    /**
     * Creates a New York Deluxe pizza
     * @return
     */
    @Override
    public Pizza createDeluxe() {
        Deluxe deluxeNYPizza = new Deluxe(new ArrayList<Topping>(Arrays.asList(
                Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.GREEN_PEPPER,
                Topping.ONION,
                Topping.MUSHROOM
        )), Crust.BROOKLYN);
        return deluxeNYPizza;
    }

    /**
     * Creates a New York Meatzza pizza
     * @return
     */
    @Override
    public Pizza createMeatzza() {
        Meatzza meatzzaNYPizza = new Meatzza(new ArrayList<Topping>(Arrays.asList(
                Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.BEEF,
                Topping.HAM
        )), Crust.HAND_TOSSED);
        return meatzzaNYPizza;
    }

    /**
     * Creates a BBQ Chicken New York pizza
     * @return
     */
    @Override
    public Pizza createBBQChicken() {
        BBQChicken bbqChickenNYPizza = new BBQChicken(new ArrayList<Topping>(Arrays.asList(
                Topping.BBQ_CHICKEN,
                Topping.GREEN_PEPPER,
                Topping.PROVOLONE,
                Topping.CHEDDAR
        )), Crust.THIN);
        return bbqChickenNYPizza;
    }

    /**
     * Creates a build your own New York Pizza
     * User inputs the toppings.
     * @return
     */
    @Override
    public Pizza createBuildYourOwn() {
        BuildYourOwn buildYourOwnNYPizza = new BuildYourOwn(Crust.HAND_TOSSED);
        return buildYourOwnNYPizza;
    }
}