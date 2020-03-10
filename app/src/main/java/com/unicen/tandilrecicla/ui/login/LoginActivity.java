package com.unicen.tandilrecicla.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import com.unicen.tandilrecicla.data.model.RegisteredUser;

import static com.unicen.tandilrecicla.data.Constants.FILE_NAME;
import static com.unicen.tandilrecicla.data.Constants.KEY_LOGGED;
import static com.unicen.tandilrecicla.data.Constants.KEY_USER;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    EditText emailEditText;
    EditText passwordEditText;
    EditText firstNameEditText;
    EditText lasNameEditText;
    EditText emailOrUserNameEditText;
    EditText userNameEditText;
    EditText departmentEditText;
    EditText numberEditText;
    EditText streetAddressEditText;
    EditText cityEditText;
    EditText stateEditText;
    EditText zipCodeEditText;

    private LoginViewModel loginViewModel;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory()).get(LoginViewModel.class);

        emailEditText = findViewById(R.id.activity_login_text_email);
        passwordEditText = findViewById(R.id.activity_login_text_password);
        firstNameEditText = findViewById(R.id.activity_login_text_first_name);
        lasNameEditText = findViewById(R.id.activity_login_text_last_name);
        emailOrUserNameEditText = findViewById(R.id.activity_login_text_logger);
        userNameEditText = findViewById(R.id.activity_login_text_user_name);
        departmentEditText = findViewById(R.id.activity_login_text_department);
        numberEditText = findViewById(R.id.activity_login_text_number);
        streetAddressEditText = findViewById(R.id.activity_login_text_street_address);
        cityEditText = findViewById(R.id.activity_login_text_city);
        stateEditText = findViewById(R.id.activity_login_text_state);
        zipCodeEditText = findViewById(R.id.activity_login_text_zip_code);

        final Button loginButton = findViewById(R.id.activity_login_button_login);
        final Button registerButton = findViewById(R.id.activity_login_button_register);
        final Button cancelButton = findViewById(R.id.activity_login_button_cancel);
        final CheckBox visibilityImageButton = findViewById(R.id.activity_login_text_visibility);
        final ProgressBar loadingProgressBar = findViewById(R.id.activity_login_button_loading);
        final EditText[] registerFields = new EditText[]{firstNameEditText, lasNameEditText, userNameEditText, departmentEditText,
                emailEditText, numberEditText, streetAddressEditText, cityEditText, stateEditText, zipCodeEditText};

        loginViewModel.getLoginFormState().observe(this, new Observer<FormState>() {
            @Override
            public void onChanged(@Nullable FormState formState) {
                if (formState == null) {
                    return;
                }
                loginButton.setEnabled(formState.isDataValid());
                if (formState.getUsernameError() != null) {
                    emailOrUserNameEditText.setError(getString(formState.getUsernameError()));
                }
                if (formState.getPasswordError() != null) {
                    passwordEditText.setError(getString(formState.getPasswordError()));
                }
            }
        });

        loginViewModel.getRegisterFormState().observe(this, new Observer<FormState>() {
            @Override
            public void onChanged(@Nullable FormState formState) {
                if (formState == null) {
                    return;
                }
                if (formState.isDataValid()) {
                    registerButton.setEnabled(true);
                } else {
                    registerButton.setEnabled(false);
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

                startMainActivity();
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
                loginViewModel.loginDataChanged(emailOrUserNameEditText.getText().toString(), passwordEditText.getText().toString());
                loginViewModel.registerDataChanged(emailEditText.getText().toString(),
                        userNameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };

        emailOrUserNameEditText.addTextChangedListener(afterTextChangedListener);

        passwordEditText.addTextChangedListener(afterTextChangedListener);

        emailEditText.addTextChangedListener(afterTextChangedListener);

        userNameEditText.addTextChangedListener(afterTextChangedListener);

        zipCodeEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    registerButton.performClick();
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        loginViewModel.login(emailOrUserNameEditText.getText().toString(), passwordEditText.getText().toString());
                    }
                }, 3000);
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

                    registerButton.setEnabled(false);

                    emailOrUserNameEditText.setVisibility(View.GONE);
                    loginViewModel.toRegister(emailOrUserNameEditText, emailEditText, userNameEditText);
                } else {
                    register(getRegisteredUser());
                    startMainActivity();
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

                registerButton.setEnabled(true);
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

    private void startMainActivity() {
        String username = emailOrUserNameEditText.getText().toString().isEmpty() ?
                userNameEditText.getText().toString() :
                emailOrUserNameEditText.getText().toString();
        setSharedPreferences(username);
        startActivity(new Intent(MainActivity.getIntent(this)));
        finish();
    }

    private RegisteredUser getRegisteredUser() {
        final Address address = new Address();
        address.setDepartment(departmentEditText.getText().toString());
        address.setCity(cityEditText.getText().toString());
        address.setNumber(Integer.parseInt(numberEditText.getText().toString().equals("") ? "0" : numberEditText.getText().toString()));
        address.setStreetAddress(streetAddressEditText.getText().toString());
        address.setCity(cityEditText.getText().toString());
        address.setState(stateEditText.getText().toString());
        address.setZipCode(zipCodeEditText.getText().toString());
        final RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setFirstName(firstNameEditText.getText().toString());
        registeredUser.setLastName(lasNameEditText.getText().toString());
        registeredUser.setMail(emailEditText.getText().toString());
        registeredUser.setUsername(userNameEditText.getText().toString());
        registeredUser.setAddress(address);
        return registeredUser;
    }

    private void setSharedPreferences(String user) {
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, user);
        editor.putBoolean(KEY_LOGGED, true);
        editor.apply();
    }

    private void register(RegisteredUser registeredUser) {
        loginViewModel.postUser(registeredUser).observe(this, new androidx.lifecycle.Observer<RegisteredUser>() {
            @Override
            public void onChanged(RegisteredUser responseBody) {
                Log.d(TAG, "POST User: this is a live data response!");
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        // TODO : initiate successful logged in experience
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
