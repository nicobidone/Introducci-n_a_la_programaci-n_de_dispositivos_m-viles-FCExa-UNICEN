package com.unicen.tandilrecicla.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.unicen.tandilrecicla.data.DashboardRepository;
import com.unicen.tandilrecicla.data.model.Recycling;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private DashboardRepository dashboardRepository;

    public DashboardViewModel(DashboardRepository dashboardRepository) {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        this.dashboardRepository = dashboardRepository;
    }

    public LiveData<String> getText() {
        return mText;
    }

    LiveData<Recycling> postRecycling(String id, Recycling recycling) {
        return dashboardRepository.postReactiveQuery(id, recycling);
    }
}