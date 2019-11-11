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

    public Result<RegisteredUser> register() {
        try {
//             TODO: handle loggedInUser authentication
            Address address = new Address();
            address.setDepartment("Tandil");
            address.setCity("Tandil");
            address.setNumber(874);
            address.setStreetAddress("Alberdi");
            address.setCity("Tandil");
            address.setState("Buenos Aires");
            address.setZipCode("7000");
            RegisteredUser registeredUser = new RegisteredUser();
            registeredUser.setFirstName("Mauri");
            registeredUser.setLastName("Arroqui");
            registeredUser.setMail("mauriarroqui@gmail.com");
            registeredUser.setUsername("nicob");
            registeredUser.setAddress(address);
            return new Result.Success<>(registeredUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
