package com.unicen.tandilrecicla.data;

import com.unicen.tandilrecicla.data.model.Address;
import com.unicen.tandilrecicla.data.model.LoggedInUser;
import com.unicen.tandilrecicla.data.model.RegisteredUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

//    public Result<RegisteredUser> register() {
//        try {
            // TODO: handle loggedInUser authentication
//            RegisteredUser fakeUser =
//                    new RegisteredUser(
//                            "Mauri",
//                            "Arroqui",
//                            "mauriarroqui@gmail.com",
//                            "marroqui2",
//                            new Address(
//                                    "Tandil",
//                                    874,
//                                    "Alberdi",
//                                    "Tandil",
//                                    "Buenos Aires",
//                                    "7000"));
//            return new Result.Success<>(fakeUser);
//        } catch (Exception e) {
//            return new Result.Error(new IOException("Error logging in", e));
//        }
//    }

    public void logout() {
        // TODO: revoke authentication
    }
}
