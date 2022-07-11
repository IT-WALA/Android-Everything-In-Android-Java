package com.example.dashboard;

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

import com.example.commonmodule.ToolbarManager;
import com.example.dashboard.databinding.FragmentDashboardBinding;

import java.util.ArrayList;


public class DashboardFragment extends Fragment implements DashboardAdapter.OnRecyclerviewItemClickListener {

    FragmentDashboardBinding binding;
    ArrayList<DashboardModel> list = new ArrayList<>();
    String[] data = new String[]{"Logs", "Toast", "Button", "Text Watcher", "Snackbar"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();
        prepareRecyclerView();
        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                false);
    }

    private void prepareRecyclerView() {
        recyclerView = binding.recyclerView;

        DashboardAdapter myAdapter = new DashboardAdapter(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<DashboardModel> getData() {
        list.clear();

        for (String item : data) {
            list.add(new DashboardModel(item));
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
        switch (DashboardType.getType(type)) {
            case LOGS:
                navController.navigate(R.id.action_dashboardFragment_to_logs_nav_graph);
                break;
            case TOAST:
                navController.navigate(R.id.action_dashboardFragment_to_toast_nav_graph);
                break;
            case BUTTON:
                navController.navigate(R.id.action_dashboardFragment_to_button_nav_graph);
                break;
            case TEXT_WATCHER:
                navController.navigate(R.id.action_dashboardFragment_to_textwatcher_nav_graph);
                break;
            case SNAKCBAR:
                navController.navigate(R.id.action_dashboardFragment_to_snackbar_nav_graph);
                break;
        }
    }

    public enum DashboardType {
        LOGS,
        TOAST,
        BUTTON,
        TEXT_WATCHER,
        SNAKCBAR;

        private static DashboardFragment.DashboardType[] list = DashboardFragment.DashboardType.values();

        public static DashboardFragment.DashboardType getType(int i) {
            return list[i];
        }
    }
}