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

import static com.unicen.tandilrecicla.data.model.Utils.getRecycling;

public class DashboardFragment extends Fragment {

    private static final String TAG = "DashboardFragment";

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
