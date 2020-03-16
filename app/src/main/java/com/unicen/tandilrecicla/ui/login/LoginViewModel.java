package com.unicen.tandilrecicla.ui.login;

import android.util.Patterns;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.unicen.tandilrecicla.R;
import com.unicen.tandilrecicla.data.LoginRepository;
import com.unicen.tandilrecicla.data.model.RegisteredUser;

class LoginViewModel extends ViewModel {

    private MutableLiveData<FormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<FormState> registerFormState = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<FormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<FormState> getRegisterFormState() {
        return registerFormState;
    }

    void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new FormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new FormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new FormState(true));
        }
    }

    void registerDataChanged(String email, String username, String password) {
        if (!isUserNameValid(email) || !isUserNameValid(username)) {
            registerFormState.setValue(new FormState(false));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new FormState(false));
        } else {
            registerFormState.setValue(new FormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    LiveData<RegisteredUser> postNewUserData(RegisteredUser registeredUser) {
        return loginRepository.postNewUserQuery(registeredUser);
    }

    void toRegister(@NonNull EditText emailOrUserName, @NonNull EditText email, @NonNull EditText userName) {
        String emailOrUserNameText = emailOrUserName.getText().toString();
        if (emailOrUserNameText.contains("@")) {
            email.setText(emailOrUserNameText);
        } else {
            userName.setText(emailOrUserNameText);
        }
    }

    void toLogin(@NonNull EditText emailOrUserName, @NonNull EditText email) {
        String emailToString = email.getText().toString();
        if (!emailToString.isEmpty()) {
            emailOrUserName.setText(emailToString);
        }
    }
}
