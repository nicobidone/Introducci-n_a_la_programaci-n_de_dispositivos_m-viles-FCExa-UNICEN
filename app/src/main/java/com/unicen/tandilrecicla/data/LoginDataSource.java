package com.unicen.tandilrecicla.data;

import com.unicen.tandilrecicla.data.model.Address;
import com.unicen.tandilrecicla.data.model.LoggedInUser;
import com.unicen.tandilrecicla.data.model.Recycling;
import com.unicen.tandilrecicla.data.model.User;

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

    public Result<User> register() {
        try {
            // TODO: handle loggedInUser authentication
            Address address = new Address();
            address.setDepartment("Tandil");
            address.setCity("Tandil");
            address.setNumber(874);
            address.setStreetAddress("Alberdi");
            address.setCity("Tandil");
            address.setState("Buenos Aires");
            address.setZipCode("7000");
            User user = new User();
            user.setFirstName("Mauri");
            user.setLastName("Arroqui");
            user.setMail("mauriarroqui@gmail.com");
            user.setUsername("marroqui2");
            user.setAddress(address);
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public Result<Recycling> recycle() {
        try {
            Recycling recycling = new Recycling();
            recycling.setBottles(1);
            recycling.setTetrabriks(5);
            recycling.setGlass(3);
            recycling.setPaperboard(4);
            recycling.setCans(2);
            recycling.setDate("2018-11-29");
            return new Result.Success<>(recycling);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
