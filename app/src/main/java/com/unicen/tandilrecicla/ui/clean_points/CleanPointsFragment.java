package com.unicen.tandilrecicla.ui.clean_points;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unicen.tandilrecicla.R;
import com.unicen.tandilrecicla.data.model.Utils;

public class CleanPointsFragment extends Fragment {

    private static final String TAG = "CleanPointsFragment";

    private String[] mPoints;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_clean_points, container, false);
        recyclerView = root.findViewById(R.id.fragment_clean_points_recycler_view);

        initRecyclingPoints();
        initRecyclerView();

        return root;
    }

    private void initRecyclingPoints() {
        Log.d(TAG, "initRecyclingPoints: Preparing items");
        mPoints = Utils.getRecyclingPointsAddress();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: Init recyclerView");

        CleanPointsRecyclerViewAdapter adapter = new CleanPointsRecyclerViewAdapter(mPoints);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
