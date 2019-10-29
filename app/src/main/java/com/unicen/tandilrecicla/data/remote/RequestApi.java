package com.unicen.tandilrecicla.data.remote;

import com.unicen.tandilrecicla.data.model.RegisteredUser;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestApi {

    @GET("/todos/1")
    Flowable<ResponseBody> makeQuery();

    @POST("/users")
    @FormUrlEncoded
    Observable<RegisteredUser> savePost(@Field("firstName") String firstName,
                                        @Field("lastName") String lastName,
                                        @Field("mail") String mail,
                                        @Field("username") String userName);

}

