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
import com.example.project5main.databinding.FragmentStoreOrderBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *  * This class controls the details of each order and display the total amount of each order, with 2 decimal places. The user can select an
 *  * order and cancel the order. The View shall display the remaining orders and the total amount correctly after the
 *  * cancellation.
 *  * @author Tim Liu, Eric Hou
 *  */

public class StoreOrderFragment extends Fragment {
    private ListView orderList;
    private FragmentStoreOrderBinding binding;
    private Spinner orderNumSpinner;
    private TextView total;


    DecimalFormat f = new DecimalFormat("##.00");
    ArrayList<Integer> orderNums = new ArrayList<>();
    ArrayList<String> pizzaStrings = new ArrayList<>();

    /**
     * Creates the view
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
        binding = FragmentStoreOrderBinding.inflate(inflater, container, false);
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
        for(Order order : MainActivity.storeOrder.getOrders()) {
            orderNums.add(order.getOrderNumber());
        }
        ArrayAdapter<Integer> orders = new ArrayAdapter<Integer>(this.getContext(),
                android.R.layout.simple_spinner_item, orderNums);
        orderNumSpinner = binding.getRoot().findViewById(R.id.orderNums);
        orderNumSpinner.setAdapter(orders);
        setLists(orders);
    }

    /**
     * Sets the list view for the pizzas
     * @param orders
     */
    private void setLists(ArrayAdapter orders) {
        total = binding.getRoot().findViewById(R.id.orderTot);
        android.content.Context context = this.getContext();
        orderNumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pizzaStrings.clear();
                int num = (Integer) orderNumSpinner.getItemAtPosition(position);
                for(Order order : MainActivity.storeOrder.getOrders()) {
                    if(order.getOrderNumber() == num) {
                        for(Pizza pizza : order.getPizzas()) {
                            pizzaStrings.add(pizza.toString());
                        }
                        total.setText("Order Total: " + f.format(order.calculateOrderTotal()));
                    }
                }
                ArrayAdapter<String> pizzas = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1,pizzaStrings);
                orderList = binding.getRoot().findViewById(R.id.orderList);
                orderList.setAdapter(pizzas);
                onRemoveClicked(orders, pizzas, num);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Sets the actions when the Cancel Order button is pressed
     * @param orders
     * @param pizzas
     * @param orderNum
     */
    private void onRemoveClicked(ArrayAdapter orders, ArrayAdapter pizzas, int orderNum) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getContext());
        Toast toast = Toast.makeText(this.getContext(), "Order cancelled", Toast.LENGTH_SHORT);
        binding.removeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.setTitle("Delete Order?");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        toast.show();
                        removeOrders(orders,pizzas,orderNum);
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
     * Removes order from store order.
     * @param orders
     * @param pizzas
     * @param orderNum
     */
    private void removeOrders(ArrayAdapter orders, ArrayAdapter pizzas, int orderNum) {
        ArrayList<Order> toRemove = new ArrayList<Order>();
        for (Order order : MainActivity.storeOrder.getOrders()) {
            if (order.getOrderNumber() == orderNum) {
                toRemove.add(order);
            }
        }
        MainActivity.storeOrder.getOrders().removeAll(toRemove);
        ArrayList<Integer> toRemove2 = new ArrayList<Integer>();
        for(int num : orderNums) {
            if(num == orderNum) {
                toRemove2.add(Integer.valueOf(num));
            }
        }
        orderNums.removeAll(toRemove2);
        pizzaStrings.clear();
        pizzas.notifyDataSetChanged();
        orders.notifyDataSetChanged();
        total.setText("Order Total: ");

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }










}