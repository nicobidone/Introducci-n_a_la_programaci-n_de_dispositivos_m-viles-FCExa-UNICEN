package com.unicen.tandilrecicla.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.unicen.tandilrecicla.data.model.LoggedInUser;
import com.unicen.tandilrecicla.data.model.RegisteredUser;
import com.unicen.tandilrecicla.data.remote.ServiceGenerator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static final String TAG = "LoginRepository";

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }
//
//    public LiveData<Recycling> postReactiveQuery(String id, Recycling recycling) {
//        return LiveDataReactiveStreams
//                .fromPublisher(ServiceGenerator.getRequestApi()
//                        .savePost(id, recycling)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread()));
//    }

    public LiveData<RegisteredUser> postUserQuery(RegisteredUser registeredUser) {
        LiveData<RegisteredUser> registeredUserLiveData;
        try {
            registeredUserLiveData = LiveDataReactiveStreams
                    .fromPublisher(ServiceGenerator.getRequestApi()
                            .registerPost(registeredUser)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()));
        } catch (Exception e) {
            return null;
        }
        return registeredUserLiveData;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(String username, String password) {
        // handle login
        Result<LoggedInUser> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }
}
