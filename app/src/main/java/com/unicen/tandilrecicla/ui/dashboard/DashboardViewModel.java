package com.unicen.tandilrecicla.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.unicen.tandilrecicla.data.DashboardRepository;
import com.unicen.tandilrecicla.data.model.Recycling;

import java.util.HashMap;
import java.util.Map;

class DashboardViewModel extends ViewModel {

    private Map<Integer, Integer> quantity;

    private DashboardRepository dashboardRepository;

    DashboardViewModel(DashboardRepository dashboardRepository) {
        quantity = new HashMap<>();
        this.dashboardRepository = dashboardRepository;
    }

    String getRecyclingQuantity(Integer position) {
        Integer integer = quantity.get(position);
        if (integer == null) {
            integer = 0;
        }
        return integer.toString();
    }

    void addQuantity(Integer position, Integer integer) {
        Integer val = quantity.get(position);
        if (val != null) {
            quantity.put(position, val + integer);
        } else {
            quantity.put(position, 1);
        }
    }

    void removeQuantity(Integer position, Integer integer) {
        Integer val = quantity.get(position);
        if (val != null && val > 0) {
            quantity.put(position, val - integer);
        } else {
            quantity.put(position, 0);
        }
    }

    void clearRecyclingQuantities(){
        for ( Integer key : quantity.keySet()){
            quantity.remove(key);
        }
    }

    LiveData<Recycling> postRecycling(String id, Recycling recycling) {
        return dashboardRepository.postReactiveQuery(id, recycling);
    }
}
