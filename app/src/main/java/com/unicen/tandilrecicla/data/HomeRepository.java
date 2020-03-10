package com.unicen.tandilrecicla.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.unicen.tandilrecicla.data.model.Recycling;
import com.unicen.tandilrecicla.data.model.RecyclingBuilder;
import com.unicen.tandilrecicla.data.remote.ServiceGenerator;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

    public LiveData<Recycling> makeReactiveQuery(String id) {
        return LiveDataReactiveStreams.fromPublisher(ServiceGenerator.getRequestApi()
                .makeQuery(id)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, Recycling>() {
                    @Override
                    public Recycling apply(Throwable error) {
                        return RecyclingBuilder.getRecyclingEmpty();
                    }
                })
        );
    }
}
