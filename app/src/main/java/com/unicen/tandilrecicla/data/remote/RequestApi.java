package com.unicen.tandilrecicla.data.remote;

import com.unicen.tandilrecicla.data.model.Recycling;
import com.unicen.tandilrecicla.data.model.RegisteredUser;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestApi {

    @GET("users/{id}/total/")
    Flowable<Recycling> getTotalRecycling(@Path("id") String id);

    @POST("users/{id}/recycling/")
    Flowable<Recycling> postNewRecycling(@Path("id") String id, @Body Recycling recycling);

    @POST("users")
    Flowable<RegisteredUser> postNewUser(@Body RegisteredUser registeredUser);

    @GET("users/{id}/recycling/")
    Flowable<List<Recycling>> getRecyclingList(@Path("id") String id);
}

