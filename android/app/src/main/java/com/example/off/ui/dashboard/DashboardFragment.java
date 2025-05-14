package com.example.off.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.off.Ghost;
import com.example.off.GhostAdapter;
import com.example.off.R;
import com.example.off.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    private ArrayList<Ghost> ghostList;
    private RecyclerView recyclerView;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        recyclerView.findViewById(R.id.recyclerView);
        ghostList = new ArrayList<>();
        setGhostInfo();
        setAdapter();


        return root;
    }

    private void setAdapter() {
        GhostAdapter adapter = new GhostAdapter(ghostList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setGhostInfo() {
        ghostList.add(new Ghost("Queimado", 0, 100, 70, 80, 70, 70, 40));
        ghostList.add(new Ghost("Queimado de calvario", 0, 250, 150, 150, 85, 80, 100));
        ghostList.add(new Ghost("Queimado crítico", 0, 100, 70, 80, 70, 70, 40));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}