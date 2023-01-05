package com.example.project5main;

import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.text.DecimalFormat;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;

/**
 * This class is the Activity to be started when an item on the
 * Flavor Menu was clicked
 * Customizes the page based on what was selected.
 * @author Tim Liu, Eric Hou
 */
public class SelectPizzaActivity extends AppCompatActivity {
    public static String flavor;
    private TextView selectedFlavor;
    private Button btn;
    private Button btn2;
    private TextView priceText;
    public static int style;
    private TextView crust;
    private double price;
    private ImageView image;
    private TextView pizzaName;
    Size size;

    /**
     * Creates the view
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_pizza_activity);
        Intent intent = getIntent();
        pizzaName = findViewById(R.id.name);
        if(style == 0) {
            setChicago();
        }
        else {
            setNY();
        }
        ArrayAdapter<CharSequence> sizes = ArrayAdapter.createFromResource(this,
                R.array.sizes, android.R.layout.simple_spinner_item);
        sizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById (R.id.flavorSpinner);
        spinner.setAdapter(sizes);
        selectedFlavor = findViewById(R.id.flavor);
        selectedFlavor.setText(flavor);
        priceText = findViewById(R.id.price);
        setUpAttributes(spinner);
        btn = findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn2 = findViewById(R.id.addPizza);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlert();
            }
        });
    }


    /**
     * Sets attributes for Chicago pizza
     */
    private void setChicago() {
        pizzaName.setText("Chicago Style Pizza");
        crust = findViewById(R.id.crust);
        image = findViewById(R.id.pizzaImage);
        if(flavor.equalsIgnoreCase("Deluxe")) {
            crust.setText("Crust: Deep Dish");
            image.setImageResource(R.drawable.chicagodeluxe);
        }
        else if(flavor.equalsIgnoreCase("Meatzza")) {
            crust.setText("Crust: Stuffed");
            image.setImageResource(R.drawable.chicagomeatzza);
        }
        else if(flavor.equalsIgnoreCase("BBQ Chicken")) {
            crust.setText("Crust: Pan");
            image.setImageResource(R.drawable.chicagobbq);
        }
        else {
            crust.setText("Crust: Pan");
            image.setImageResource(R.drawable.chicagobuildyourown);
        }
    }

    /**
     * Sets attributes for New York pizza
     */
    private void setNY() {
        pizzaName.setText("New York Style Pizza");
        crust = findViewById(R.id.crust);
        image = findViewById(R.id.pizzaImage);
        if(flavor.equalsIgnoreCase("Deluxe")) {
            crust.setText("Crust: Brooklyn");
            image.setImageResource(R.drawable.nydeluxe);
        }
        else if(flavor.equalsIgnoreCase("Meatzza")) {
            crust.setText("Crust: Hand Tossed");
            image.setImageResource(R.drawable.nymeatzza);
        }
        else if(flavor.equalsIgnoreCase("BBQ Chicken")) {
            crust.setText("Crust: Thin");
            image.setImageResource(R.drawable.newyorkbbq);
        }
        else {
            crust.setText("Crust: Hand Tossed");
            image.setImageResource(R.drawable.nybuildyourown);
        }
    }


    ArrayList<Topping> list1 = new ArrayList<Topping>();
    ArrayList<Topping> list2 = new ArrayList<Topping>();
    ArrayList<Topping> temp = new ArrayList<>();
    DecimalFormat f = new DecimalFormat("##.00");

    /**
     * Initializes the selectable toppings list view.
     */
    private void setDefault() {
        list1.add(Topping.SAUSAGE);
        list1.add(Topping.PEPPERONI);
        list1.add(Topping.GREEN_PEPPER);
        list1.add(Topping.ONION);
        list1.add(Topping.MUSHROOM);
        list1.add(Topping.BBQ_CHICKEN);
        list1.add(Topping.PROVOLONE);
        list1.add(Topping.CHEDDAR);
        list1.add(Topping.BEEF);
        list1.add(Topping.HAM);
        list1.add(Topping.BROCCOLI);
        list1.add((Topping.PINEAPPLE));
        list1.add(Topping.OLIVES);
    }

    /**
     * Sets the topping list view based on flavor
     * @param spinner
     */
    private void setUpAttributes(Spinner spinner) {
        ListView selectable = findViewById(R.id.selectable);
        ListView selected = findViewById(R.id.selected);
        setDefault();
        if(flavor.equalsIgnoreCase("Deluxe")) {
            addDeluxe();
            setLists(selectable, selected);
        }
        else if(flavor.equalsIgnoreCase("BBQ Chicken")) {
            addBBQ();
            setLists(selectable, selected);
        }
        else if(flavor.equalsIgnoreCase("Meatzza")) {
            addMeatzza();
            setLists(selectable, selected);

        }
        else {
            temp = list1;
            buildYourOwn(setLists(selectable, selected), selectable,selected);
        }
        setPrice(flavor, spinner);
    }

    /**
     * Set the topping list when the build your own flavor is selected
     * @param selectable
     * @param selected
     * @return
     */
    private ArrayList<ArrayAdapter> setLists(ListView selectable, ListView selected) {
        ArrayAdapter<Topping> toppings = new ArrayAdapter<Topping>(this,
                android.R.layout.simple_list_item_1,temp);
        ArrayAdapter<Topping> toppings2 = new ArrayAdapter<Topping>(this,
                android.R.layout.simple_list_item_1,list2);
        selectable.setAdapter(toppings);
        selected.setAdapter(toppings2);
        ArrayList<ArrayAdapter> bothToppings = new ArrayList<>();
        bothToppings.add(toppings);
        bothToppings.add(toppings2);
        return bothToppings;
    }

    /**
     * Adds the toppings for deluxe pizza.
     */
    private void addDeluxe() {
        temp = list1;
        temp.remove(Topping.SAUSAGE);
        list2.add(Topping.SAUSAGE);
        temp.remove(Topping.PEPPERONI);
        list2.add(Topping.PEPPERONI);
        temp.remove(Topping.GREEN_PEPPER);
        list2.add(Topping.GREEN_PEPPER);
        temp.remove(Topping.ONION);
        list2.add(Topping.ONION);
        temp.remove(Topping.MUSHROOM);
        list2.add(Topping.MUSHROOM);
    }

    /**
     * Adds toppings for meatzza pizza
     */
    private void addMeatzza() {
        temp = list1;
        temp.remove(Topping.SAUSAGE);
        list2.add(Topping.SAUSAGE);
        temp.remove(Topping.PEPPERONI);
        list2.add(Topping.PEPPERONI);
        temp.remove(Topping.BEEF);
        list2.add(Topping.BEEF);
        temp.remove(Topping.HAM);
        list2.add(Topping.HAM);
    }

    /**
     * Adds toppings for BBQ Chicken pizza
     */
    private void addBBQ() {
        temp = list1;
        temp.remove(Topping.BBQ_CHICKEN);
        list2.add(Topping.BBQ_CHICKEN);
        temp.remove(Topping.GREEN_PEPPER);
        list2.add(Topping.GREEN_PEPPER);
        temp.remove(Topping.PROVOLONE);
        list2.add(Topping.PROVOLONE);
        temp.remove(Topping.CHEDDAR);
        list2.add(Topping.CHEDDAR);
    }

    /**
     * Add toppings for build your own pizza
     * @param toppings
     * @param selectable
     * @param selected
     */
    private void buildYourOwn(ArrayList<ArrayAdapter> toppings, ListView selectable, ListView selected) {
        selectable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(list2.size() == 7) {
                    hitMaxToppings();
                }
                else {
                    list2.add(temp.get(i));
                    temp.remove(i);
                    toppings.get(0).notifyDataSetChanged();
                    toppings.get(1).notifyDataSetChanged();
                    price += 1.59;
                    priceText.setText("Price: " + f.format(price));
                }
            }
        });

        selected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                temp.add(list2.get(i));
                list2.remove(i);
                toppings.get(0).notifyDataSetChanged();
                toppings.get(1).notifyDataSetChanged();
                price = price - 1.59;
                priceText.setText("Price: " + f.format(price));
            }
        });
    }

    private void hitMaxToppings() {
        Toast toast = Toast.makeText(this,
                "Maximum of 7 toppings allowed", Toast.LENGTH_SHORT);
        toast.show();
    }
    /**
     * Sets the price of the pizza
     * @param flavor
     * @param spinner
     */
    private void setPrice(String flavor, Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String size = spinner.getItemAtPosition(position).toString();
                if(size.equalsIgnoreCase("small")) {
                    setSmall(flavor);
                }
                else if(size.equalsIgnoreCase("medium")) {
                    setMedium(flavor);
                }
                else if(size.equalsIgnoreCase("large")) {
                    setLarge(flavor);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Sets the price for a small pizza
     * @param flavor
     */
    private void setSmall(String flavor) {
        size = Size.SMALL;
        if(flavor.equalsIgnoreCase("Deluxe")) {
            price = 14.99;
        }
        else if(flavor.equalsIgnoreCase("Meatzza")) {
            price = 15.99;
        }
        else if(flavor.equalsIgnoreCase("BBQ Chicken")) {
            price = 13.99;
        }
        else {
            price = 8.99;
        }
        priceText.setText("Price: " + price);
    }

    /**
     * Sets price for medium pizza
     * @param flavor
     */
    private void setMedium(String flavor) {
        size = Size.MEDIUM;
        if(flavor.equalsIgnoreCase("Deluxe")) {
            price = 16.99;
        }
        else if(flavor.equalsIgnoreCase("Meatzza")) {
            price = 17.99;
        }
        else if(flavor.equalsIgnoreCase("BBQ Chicken")) {
            price = 15.99;
        }
        else {
            price = 10.99;
        }
        priceText.setText("Price: " + price);
    }

    /**
     * Sets price for large pizza
     * @param flavor
     */
    private void setLarge(String flavor) {
        size = Size.LARGE;
        if(flavor.equalsIgnoreCase("Deluxe")) {
            price = 18.99;
        }
        else if(flavor.equalsIgnoreCase("Meatzza")) {
            price = 19.99;
        }
        else if(flavor.equalsIgnoreCase("BBQ Chicken")) {
            price = 17.99;
        }
        else {
            price = 12.99;
        }
        priceText.setText("Price: " + price);
    }

    /**
     * Adds pizza to current order
     */
    private void addPizza() {
        Pizza pizza;
        PizzaFactory pizzaFactory;
        if(style == 0) {
            pizzaFactory = new ChicagoPizza();
        }
        else {
            pizzaFactory = new NYPizza();
        }
        if(flavor.equalsIgnoreCase("Deluxe")) {
            pizza = pizzaFactory.createDeluxe();
        }
        else if(flavor.equalsIgnoreCase("Meatzza")) {
            pizza = pizzaFactory.createMeatzza();
        }
        else if(flavor.equalsIgnoreCase("BBQ Chicken")) {
            pizza = pizzaFactory.createBBQChicken();
        }
        else {
            pizza = pizzaFactory.createBuildYourOwn();
            pizza.setToppings(list2);
        }
        pizza.setSize(size);
        MainActivity.order.add(pizza);
    }

    /**
     * Sets the confirmation alert to add pizza
     */
    private void setAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        Toast toast = Toast.makeText(this, "Pizza added.", Toast.LENGTH_SHORT);
        alert.setTitle("Add to order");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                toast.show();
                addPizza();
            }
            //handle the "NO" click
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
