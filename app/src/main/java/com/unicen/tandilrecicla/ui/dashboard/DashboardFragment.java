package com.unicen.tandilrecicla.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.unicen.tandilrecicla.R;
import com.unicen.tandilrecicla.data.model.Recycling;

public class DashboardFragment extends Fragment {

    private static final String TAG = "DashboardFragment";

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this, new DashboardViewModelFactory()).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycle();
    }

    private Recycling getRecycling() {
        Recycling recycling = new Recycling();
        recycling.setBottles(1);
        recycling.setTetrabriks(5);
        recycling.setGlass(3);
        recycling.setPaperboard(4);
        recycling.setCans(2);
        recycling.setDate("2018-11-29");
        return recycling;
    }

    private void recycle() {

        dashboardViewModel.postRecycling("marroqui2", getRecycling()).observe(this, new androidx.lifecycle.Observer<Recycling>() {
            @Override
            public void onChanged(Recycling responseBody) {
                Log.d(TAG, "onChanged: this is a live data response!");
                Log.d(TAG, "onChanged: " + responseBody.getDate());
            }
        });
    }
}