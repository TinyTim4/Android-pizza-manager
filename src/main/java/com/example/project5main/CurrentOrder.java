package com.example.project5main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.DuplicateFormatFlagsException;

import com.example.project5main.databinding.FragmentCurrentOrderBinding;

/**
 * Controls the current order page which displays the order number, the list of pizzas with the pizza
 * style, crust, the list of toppings, subtotal, sales tax, and order total of the current order. All
 * dollar amounts must be displayed with 2 decimal places. The user can review the order, remove the selected
 * pizza, clear the order, or place the order.
 * @author Tim Liu, Eric Hou
 */

public class CurrentOrder extends Fragment {
    private FragmentCurrentOrderBinding binding;
    private TextView currentOrderNum;
    private ListView pizzaList;
    private TextView subTotal;
    private TextView taxes;
    private TextView total;
    DecimalFormat f = new DecimalFormat("##.00");
    ArrayList<String> pizzaStrings = new ArrayList<>();

    /**
     * Creates the view.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCurrentOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    /**
     * Initializes the page
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentOrderNum = binding.getRoot().findViewById(R.id.orderNum);
        currentOrderNum.setText("Order Number: " + MainActivity.order.getOrderNumber());
        pizzaList = binding.getRoot().findViewById(R.id.pizzaList);
        for(Pizza pizza : MainActivity.order.getPizzas()) {
            pizzaStrings.add(pizza.toString());
        }
        ArrayAdapter<String> pizzas = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1,pizzaStrings);
        pizzaList.setAdapter(pizzas);
        selectListItem(pizzas);
        calculate();
        addOrderOnClick(pizzas);
        onClearClicked(pizzas);

    }

    /**
     * Destroys the view
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Calculates the prices for the pizzas
     */
    private void calculate() {
        subTotal = binding.getRoot().findViewById(R.id.orderSub);
        subTotal.setText("Subtotal: " + f.format(MainActivity.order.calculateSubtotal()));
        taxes = binding.getRoot().findViewById(R.id.orderTax);
        taxes.setText("Sales Tax: " + f.format(MainActivity.order.calculateSalesTax()));
        total = binding.getRoot().findViewById(R.id.orderTotal);
        total.setText("Order Total: " + f.format(MainActivity.order.calculateOrderTotal()));
    }

    /**
     * Sets the list view for the pizzas
     * @param pizzas
     */
    private void selectListItem(ArrayAdapter pizzas) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getContext());
        pizzaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                alert.setTitle("Remove pizza from order?");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deletePizza(i, pizzas);
                    }
                    //handle the "NO" click
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * Helper method to remove pizzas from order
     * @param position
     * @param pizzas
     */
    private void deletePizza(int position, ArrayAdapter pizzas) {
        MainActivity.order.getPizzas().remove(position);
        pizzaStrings.remove(position);
        pizzas.notifyDataSetChanged();
        calculate();
    }

    /**
     * Sets actions for when user presses place order button
     * @param pizzas
     */
    private void addOrderOnClick(ArrayAdapter pizzas) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getContext());
        Toast toast = Toast.makeText(this.getContext(), "Order added.", Toast.LENGTH_SHORT);
        Toast error = Toast.makeText(this.getContext(), "No pizzas in order", Toast.LENGTH_SHORT);
        Order duplicate = new Order(MainActivity.order);
        binding.placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pizzaStrings.isEmpty()) {
                    error.show();
                }
                else {
                    alert.setTitle("Add to Store Order?");
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            toast.show();
                            addOrder(duplicate, pizzas);
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
        });
    }

    /**
     * Adds order to store order
     * @param duplicate
     * @param pizzas
     */
    private void addOrder(Order duplicate, ArrayAdapter pizzas) {
        MainActivity.storeOrder.add(duplicate);
        MainActivity.order.addOrderNum();
        clearOrder(pizzas);
    }

    /**
     * Clears the order
     * @param pizzas
     */
    private void clearOrder(ArrayAdapter pizzas) {
        MainActivity.order.clearOrderTotal();
        MainActivity.order.removeAllPizzas();
        subTotal.setText("Subtotal: ");
        taxes.setText("Sales Tax: ");
        total.setText("Order Total: ");
        currentOrderNum.setText("Order NUm: ");
        pizzaStrings.clear();
        pizzas.notifyDataSetChanged();
    }

    /**
     * Sets the actions when the user presses the clear pizzas button.
     * @param pizzas
     */
    private void onClearClicked(ArrayAdapter pizzas) {
        binding.clearOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearOrder(pizzas);
            }
        });
    }
}