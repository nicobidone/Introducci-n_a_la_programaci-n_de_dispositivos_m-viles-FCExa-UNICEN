package com.unicen.tandilrecicla.data.remote;

import com.unicen.tandilrecicla.data.model.RegisteredUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("/posts")
    @FormUrlEncoded
    Call<RegisteredUser> savePost(@Field("firstName") String firstName,
                                  @Field("lastName") String lastName,
                                  @Field("mail") String mail,
                                  @Field("username") String username);
}