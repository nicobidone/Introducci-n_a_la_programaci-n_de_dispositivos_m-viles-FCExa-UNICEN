package com.unicen.tandilrecicla.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.unicen.tandilrecicla.MainActivity;
import com.unicen.tandilrecicla.R;
import com.unicen.tandilrecicla.data.model.Address;
import com.unicen.tandilrecicla.data.model.Recycling;
import com.unicen.tandilrecicla.data.model.RegisteredUser;

import java.io.IOException;

import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText emailEditText = findViewById(R.id.email);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText firstNameEditText = findViewById(R.id.firstName);
        final EditText lasNameEditText = findViewById(R.id.lastName);
        final EditText emailOrUserNameEditText = findViewById(R.id.logger);
        final EditText userNameEditText = findViewById(R.id.userName);
        final EditText departmentEditText = findViewById(R.id.department);
        final EditText numberEditText = findViewById(R.id.number);
        final EditText streetAddressEditText = findViewById(R.id.streetAddress);
        final EditText cityEditText = findViewById(R.id.city);
        final EditText stateEditText = findViewById(R.id.state);
        final EditText zipCodeEditText = findViewById(R.id.zipCode);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register);
        final Button cancelButton = findViewById(R.id.cancel);
        final CheckBox visibilityImageButton = findViewById(R.id.visibility);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final EditText[] registerFields = new EditText[]{firstNameEditText, lasNameEditText, userNameEditText, departmentEditText,
                emailEditText, numberEditText, streetAddressEditText, cityEditText, stateEditText, zipCodeEditText};

        final Context context = this;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    emailOrUserNameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                startActivity(new Intent(MainActivity.getIntent(context)));
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(emailOrUserNameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };

        emailOrUserNameEditText.addTextChangedListener(afterTextChangedListener);

        passwordEditText.addTextChangedListener(afterTextChangedListener);

        zipCodeEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    register();
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(emailOrUserNameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstNameEditText.getVisibility() == View.GONE) {

                    for (EditText element : registerFields) {
                        element.setVisibility(View.VISIBLE);
                    }
                    cancelButton.setVisibility(View.VISIBLE);
                    loginButton.setVisibility(View.GONE);
                    firstNameEditText.requestFocus();

                    emailOrUserNameEditText.setVisibility(View.GONE);
                    loginViewModel.toRegister(emailOrUserNameEditText, emailEditText, userNameEditText);
                } else {
                    register();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (EditText element : registerFields) {
                    element.setVisibility(View.GONE);
                }
                cancelButton.setVisibility(View.GONE);
                loginButton.setVisibility(View.VISIBLE);

                loginViewModel.toLogin(emailOrUserNameEditText, emailEditText);
                emailOrUserNameEditText.setVisibility(View.VISIBLE);
            }
        });

        visibilityImageButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (visibilityImageButton.isChecked()) {
                            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        } else {
                            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }
                        passwordEditText.setSelection(passwordEditText.getText().toString().length());
                    }
                }
        );
    }

    private RegisteredUser getRegisteredUser() {
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
        return registeredUser;
    }

    private void register() {

        loginViewModel.postUser(getRegisteredUser()).observe(this, new androidx.lifecycle.Observer<RegisteredUser>() {
            @Override
            public void onChanged(RegisteredUser responseBody) {
                Log.d(TAG, "POST User: this is a live data response!");
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}

