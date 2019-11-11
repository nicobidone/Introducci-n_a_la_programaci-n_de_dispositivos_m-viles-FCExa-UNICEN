package com.unicen.tandilrecicla.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.unicen.tandilrecicla.data.remote.ServiceGenerator;

import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class HomeRepository {

    private static final String TAG = "HomeRepository";

    private static volatile HomeRepository instance;

    // private constructor : singleton access
    private HomeRepository() {
    }

    public static HomeRepository getInstance() {
        if (instance == null) {
            instance = new HomeRepository();
        }
        return instance;
    }

    public LiveData<ResponseBody> makeReactiveQuery(String id) {
        return LiveDataReactiveStreams
                .fromPublisher(ServiceGenerator.getRequestApi()
                        .makeQuery(id)
                        .subscribeOn(Schedulers.io()));
    }
}
