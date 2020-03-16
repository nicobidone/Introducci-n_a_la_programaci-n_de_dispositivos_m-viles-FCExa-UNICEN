package com.unicen.tandilrecicla.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.unicen.tandilrecicla.data.model.RegisteredUser;
import com.unicen.tandilrecicla.data.model.RegisteredUserBuilder;
import com.unicen.tandilrecicla.data.remote.ServiceGenerator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static final String TAG = "LoginRepository";

    private static volatile LoginRepository instance;

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public LiveData<RegisteredUser> postNewUserQuery(RegisteredUser registeredUser) {
        LiveData<RegisteredUser> registeredUserLiveData;
        try {
            registeredUserLiveData = LiveDataReactiveStreams
                    .fromPublisher(ServiceGenerator.getRequestApi()
                            .postNewUser(registeredUser)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .onErrorReturn(new Function<Throwable, RegisteredUser>() {
                                @Override
                                public RegisteredUser apply(Throwable error) {
                                    return RegisteredUserBuilder.getEmptyUser();
                                }
                            })
                    );
        } catch (Exception e) {
            return null;
        }
        return registeredUserLiveData;
    }
}
