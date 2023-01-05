package com.example.project5main;

/**
 * Sets the characteristics of a Chicago style pizza
 * @author Tim Liu, Eric Hou
 */

import java.util.ArrayList;
import java.util.Arrays;

// Should be called ChicagoPizzaFactory
public class ChicagoPizza implements PizzaFactory {
    /**
     * Creates a Deluxe Chicago Pizza
     * @return the pizza
     */
    @Override
    public Pizza createDeluxe() {
        Deluxe deluxeChicagoPizza = new Deluxe(new ArrayList<Topping>(Arrays.asList(
                Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.GREEN_PEPPER,
                Topping.ONION,
                Topping.MUSHROOM
        )), Crust.DEEP_DISH);
        return deluxeChicagoPizza;
    }

    /**
     * Creates a Meatzza Chicago pizza
     * @return the pizza
     */
    @Override
    public Pizza createMeatzza() {
        Meatzza meatzzaChicagoPizza = new Meatzza(new ArrayList<Topping>(Arrays.asList(
                Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.BEEF,
                Topping.HAM
        )), Crust.STUFFED);
        return meatzzaChicagoPizza;
    }
    /**
     * Creates a BBQ Chicken Chicago pizza
     * @return the pizza
     */
    @Override
    public Pizza createBBQChicken() {
        BBQChicken bbqChicagoPizza = new BBQChicken(new ArrayList<Topping>(Arrays.asList(
                Topping.BBQ_CHICKEN,
                Topping.GREEN_PEPPER,
                Topping.PROVOLONE,
                Topping.CHEDDAR
        )), Crust.PAN);
        return bbqChicagoPizza;
    }
    /**
     * Creates a Build Your Own Chicago pizza.
     * User sets the toppings.
     * @return the pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        BuildYourOwn buildYourOwnNYPizza = new BuildYourOwn(Crust.PAN);
        return buildYourOwnNYPizza;
    }
}