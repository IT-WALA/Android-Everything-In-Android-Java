package com.example.recyclerview.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commonmodule.ToolbarManager;
import com.example.recyclerview.R;
import com.example.recyclerview.adapters.RecyclerviewAdapterHorizontalLayout;
import com.example.recyclerview.databinding.FragmentRecyclerviewHorizontalLayoutBinding;
import com.example.recyclerview.models.RecyclerviewModelHorizontalLayout;

import java.util.ArrayList;

public class RecyclerviewHorizontalLayoutFragment extends Fragment implements RecyclerviewAdapterHorizontalLayout.OnRecyclerviewItemClickListener {

    FragmentRecyclerviewHorizontalLayoutBinding binding;
    ArrayList<RecyclerviewModelHorizontalLayout> list = new ArrayList<>();
    String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerviewHorizontalLayoutBinding.inflate(inflater, container, false);
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

        RecyclerviewAdapterHorizontalLayout myAdapter = new RecyclerviewAdapterHorizontalLayout(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<RecyclerviewModelHorizontalLayout> getData() {
        list.clear();

        for (String item : data) {
            list.add(new RecyclerviewModelHorizontalLayout(item));
        }
        return list;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerviewItemClick(int index) {
        Toast.makeText(getContext(), "Data: " + data[index], Toast.LENGTH_SHORT).show();
    }

}