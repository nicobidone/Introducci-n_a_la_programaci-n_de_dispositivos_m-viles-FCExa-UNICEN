package com.unicen.tandilrecicla.data.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String TAG = "ServiceGenerator";

    public static final String BASE_URL = "http://192.168.0.10:8080/api/";

    public interface OnConnectionTimeoutListener {
        void onConnectionTimeout();
    }

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @NonNull
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    // TODO: deal with the issues the way you need to
                    try {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        Log.d(TAG, "The request was successful");
                        return response;
                    } catch (SocketTimeoutException exception) {
                        Log.d(TAG, "Something went wrong");
                        exception.printStackTrace();
                    }
                    return chain.proceed(chain.request());
                }

            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static RequestApi requestApi = retrofit.create(RequestApi.class);

    public static RequestApi getRequestApi() {
        return requestApi;
    }
}
