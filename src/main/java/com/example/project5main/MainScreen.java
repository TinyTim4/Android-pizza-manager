package com.example.project5main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.project5main.databinding.MainScreenBinding;

/**
 * Sets the page layout for the home screen
 * @author Tim Liu, Eric Hou
 */
public class MainScreen extends Fragment {

    private MainScreenBinding binding;

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
        binding = MainScreenBinding.inflate(inflater, container, false);
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
        getActivity().setTitle("RU Pizza");
        binding.chicago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectPizzaActivity.style = 0;
                NavHostFragment.findNavController(MainScreen.this)
                        .navigate(R.id.action_FirstFragment_to_flavorMenu);
            }
        });

        binding.newYork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectPizzaActivity.style = 1;
                NavHostFragment.findNavController(MainScreen.this)
                        .navigate(R.id.action_FirstFragment_to_flavorMenu);
            }
        });

        binding.currentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainScreen.this)
                        .navigate(R.id.action_FirstFragment_to_currentOrder2);
            }
        });
        binding.storeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainScreen.this)
                        .navigate(R.id.action_FirstFragment_to_storeOrderFragment);
            }
        });
    }

    /**
     * Destroys the view.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}