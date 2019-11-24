package com.unicen.tandilrecicla.ui.dashboard;

import android.util.SparseIntArray;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.unicen.tandilrecicla.data.DashboardRepository;
import com.unicen.tandilrecicla.data.model.Recycling;

class DashboardViewModel extends ViewModel {

    private SparseIntArray quantity;

    private DashboardRepository dashboardRepository;

    DashboardViewModel(DashboardRepository dashboardRepository) {
        quantity = new SparseIntArray();
        this.dashboardRepository = dashboardRepository;
    }

    String getRecyclingQuantity(Integer position) {
        return String.valueOf(quantity.get(position));
    }

    void addQuantity(Integer position, Integer integer) {
        Integer val = quantity.get(position);
        if (val != 0) {
            quantity.put(position, val + integer);
        } else {
            quantity.put(position, 1);
        }
    }

    void removeQuantity(Integer position, Integer integer) {
        Integer val = quantity.get(position);
        if (val > 0) {
            quantity.put(position, val - integer);
        }
    }

    void clearRecyclingQuantities() {
        quantity.clear();
    }

    LiveData<Recycling> postRecycling(String id, Recycling recycling) {
        return dashboardRepository.postReactiveQuery(id, recycling);
    }
}
