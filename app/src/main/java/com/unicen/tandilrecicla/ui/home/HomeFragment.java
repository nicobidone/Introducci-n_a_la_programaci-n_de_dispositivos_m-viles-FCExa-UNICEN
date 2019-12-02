package com.unicen.tandilrecicla.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.unicen.tandilrecicla.MainActivitySharedViewModel;
import com.unicen.tandilrecicla.R;
import com.unicen.tandilrecicla.ui.login.LoginActivity;

import java.io.IOException;

import okhttp3.ResponseBody;

public class HomeFragment extends Fragment implements OnChartValueSelectedListener {

    private static final String TAG = "HomeFragment";

    private HomeViewModel homeViewModel;

    private MainActivitySharedViewModel maSharedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this, new HomeViewModelFactory()).get(HomeViewModel.class);

        if (getActivity() != null) {
            homeViewModel.setTypefaces(Typeface.createFromAsset(getActivity().getAssets(), "open_sans_regular.ttf"),
                    Typeface.createFromAsset(getActivity().getAssets(), "open_sans_light.ttf"));
            maSharedViewModel = ViewModelProviders.of(getActivity()).get(MainActivitySharedViewModel.class);
        }

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        if (getActivity().getWindow() != null) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        PieChart chart = root.findViewById(R.id.fragment_home_pie_chart);
        ImageButton centerIcon = root.findViewById(R.id.fragment_home_center_button);
        ImageButton logoutIcon = root.findViewById(R.id.fragment_home_logout_button);

        chart.setOnChartValueSelectedListener(this);
        homeViewModel.setPieChart(chart);
        homeViewModel.setChartConfiguration(chart);
        centerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.changeDisplayUserValues();
            }
        });
        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Confirm Logout")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    maSharedViewModel.setLogged(false);
                                    startActivity(new Intent(LoginActivity.getIntent(getActivity())));
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                }
            }
        });

//        total();
        return root;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    private void total() {
        homeViewModel.getTotalRecycling(maSharedViewModel.getSelected().getValue()).observe(this,
                new androidx.lifecycle.Observer<ResponseBody>() {
                    @Override
                    public void onChanged(ResponseBody responseBody) {
                        Log.d(TAG, "onChanged: this is a live data response!");
                        try {
                            Log.d(TAG, "onChanged: " + responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                });
    }
}
