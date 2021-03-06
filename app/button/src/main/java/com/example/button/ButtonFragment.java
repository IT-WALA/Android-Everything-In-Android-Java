package com.example.button;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.button.databinding.FragmentButtonBinding;
import com.example.commonmodule.ToolbarManager;

import java.util.ArrayList;


public class ButtonFragment extends Fragment implements ButtonAdapter.OnRecyclerviewItemClickListener {

    FragmentButtonBinding binding;
    ArrayList<ButtonModel> list = new ArrayList<>();
    String[] data = new String[]{"Button On Click Listener", "Multiple Buttons On Click Listener", "Button Designs"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentButtonBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();
        prepareRecyclerView();
        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                true);
    }

    private void prepareRecyclerView() {
        recyclerView = binding.recyclerView;

        ButtonAdapter myAdapter = new ButtonAdapter(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<ButtonModel> getData() {
        list.clear();

        for (String item : data) {
            list.add(new ButtonModel(item));
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerviewItemClick(int type) {
        switch (ButtonType.getType(type)) {
            case ON_CLICK_LISTENER:
                navController.navigate(R.id.action_buttonFragment_to_buttonOnClickListenerFragment);
                break;
            case MULTIPLE_ON_CLICK_LISTENER:
                navController.navigate(R.id.action_buttonFragment_to_multipleButtonsOnClickListenerFragment);
                break;
            case BUTTON_DESIGNS:
                navController.navigate(R.id.action_buttonFragment_to_buttonDesignsFragment);
                break;
        }
    }

    public enum ButtonType {
        ON_CLICK_LISTENER,
        MULTIPLE_ON_CLICK_LISTENER,
        BUTTON_DESIGNS;

        private static ButtonType[] list = ButtonType.values();

        public static ButtonType getType(int i) {
            return list[i];
        }
    }
}