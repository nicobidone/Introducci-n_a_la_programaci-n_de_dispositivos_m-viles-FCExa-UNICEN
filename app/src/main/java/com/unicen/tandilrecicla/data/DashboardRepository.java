package com.unicen.tandilrecicla.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.unicen.tandilrecicla.data.model.Recycling;
import com.unicen.tandilrecicla.data.model.RecyclingBuilder;
import com.unicen.tandilrecicla.data.remote.ServiceGenerator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DashboardRepository {

    private static final String TAG = "DashboardRepository";

    private static volatile DashboardRepository instance;

    private DashboardRepository() {
    }

    public static DashboardRepository getInstance() {
        if (instance == null) {
            instance = new DashboardRepository();
        }
        return instance;
    }

    public LiveData<Recycling> postNewRecyclingQuery(String id, Recycling recycling) {
        return LiveDataReactiveStreams
                .fromPublisher(ServiceGenerator.getRequestApi()
                        .postNewRecycling(id, recycling)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorReturn(new Function<Throwable, Recycling>() {
                            @Override
                            public Recycling apply(Throwable error) {
                                return RecyclingBuilder.getRecyclingEmpty();
                            }
                        })
                );
    }
}

