package com.example.project5main;

import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.project5main.databinding.FragmentFlavorMenuBinding;

import java.util.ArrayList;

/**
 * Sets the Flavor Menu recycle view.
 * @author Tim Liu, Eric Hou
 */
public class FlavorMenu extends Fragment {
    //Declare an instance of ArrayList to hold the items to be display with the RecyclerView
    private ArrayList<Item> items = new ArrayList<>();
    /* All the images associated with the menu items are stored in the res/drawable folder
     *  Each image are accessed with the resourse ID, which is an integer.
     *  We need an array of integers to hold the resource IDs. Make sure the index of a given
     *  ID is consistent with the index of the associated menu item in the ArrayList.
     *  An image resource could also be an URI.
     */
    private int [] itemImages = {R.drawable.chicagobuildyourown,R.drawable.chicagodeluxe
            ,R.drawable.chicagomeatzza, R.drawable.chicagobbq};
    private FragmentFlavorMenuBinding binding;

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu, container, false);
        RecyclerView rcview = (RecyclerView) v.findViewById(R.id.rcView_menu);
        setupMenuItems(); //add the list of items to the ArrayList
        ItemsAdapter adapter = new ItemsAdapter(this.getContext(), items); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        if(SelectPizzaActivity.style == 0) {
            getActivity().setTitle("Chicago Pizza flavors");
        }
        else {
            getActivity().setTitle("New York Style flavors");
        }
        return v;
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        /*
         * Item names are defined in a String array under res/string.xml.
         * Your item names might come from other places, such as an external file, or the database
         * from the backend.
         */
        String [] itemNames = getResources().getStringArray(R.array.flavors);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], itemImages[i], "$1.39"));
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}