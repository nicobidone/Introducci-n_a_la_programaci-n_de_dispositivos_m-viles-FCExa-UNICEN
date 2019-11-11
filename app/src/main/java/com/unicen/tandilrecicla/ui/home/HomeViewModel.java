package com.unicen.tandilrecicla.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.unicen.tandilrecicla.data.HomeRepository;

import okhttp3.ResponseBody;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private HomeRepository homeRepository;

    public HomeViewModel(HomeRepository homeRepository) {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        this.homeRepository = homeRepository;
    }

    public LiveData<String> getText() {
        return mText;
    }

    LiveData<ResponseBody> getTotalRecycling(String id) {
        return homeRepository.makeReactiveQuery(id);
    }
}