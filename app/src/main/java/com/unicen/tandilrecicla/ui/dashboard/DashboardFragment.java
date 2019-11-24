package com.unicen.tandilrecicla.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unicen.tandilrecicla.R;
import com.unicen.tandilrecicla.data.model.Recycling;
import com.unicen.tandilrecicla.data.model.Utils;

import static com.unicen.tandilrecicla.data.model.Utils.getRecycling;

public class DashboardFragment extends Fragment {

    private static final String TAG = "DashboardFragment";

    private DashboardViewModel dashboardViewModel;

    private String[] mNames;
    private Integer[] mIcons;
    private Integer[] mGreyIcons;
    private int[] mColors;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel = ViewModelProviders.of(this, new DashboardViewModelFactory()).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initImageMaps();
        initRecyclerView();

        ImageButton imageButtonDiscard = getActivity().findViewById(R.id.discard);
        ImageButton imageButtonRecycle = getActivity().findViewById(R.id.recycle);
        imageButtonRecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycle();
            }
        });
        imageButtonDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRecyclerView();
            }
        });
    }

    private void initImageMaps() {
        Log.d(TAG, "initImageMaps: Preparing items");
        mNames = Utils.getRecyclingNames();
        mIcons = Utils.getRecyclingIcons();
        mGreyIcons = Utils.getGreyRecyclingIcons();
        mColors = Utils.getVordiplomColors();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: Init recyclerView");
        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, mIcons, mGreyIcons, mColors, dashboardViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
