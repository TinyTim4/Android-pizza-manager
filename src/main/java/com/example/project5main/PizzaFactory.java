package com.example.project5main;

/**
 * Responsible for creating (making) the pizzas with the crusts and toppings
 * Should be used in the controller classes to create an instance of Pizza class based on the chosen flavor
 * @author Tim Liu, Eric Hou
 */

public interface PizzaFactory {
    Pizza createDeluxe();
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();

    // Add any other methods you need


}

//CANNOT add any instance variables, static final constants, or other methods to ChicagoPizza or NYPizza class,
//public class ChicagoPizza extends Pizza implements PizzaFactory{ }
//public class NYPizza extends Pizza implements PizzaFactory { }