package com.unicen.tandilrecicla.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
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
import com.unicen.tandilrecicla.data.model.Recycling;
import com.unicen.tandilrecicla.data.remote.APIService;
import com.unicen.tandilrecicla.data.remote.ApiUtils;
import com.unicen.tandilrecicla.data.remote.RequestApi;
import com.unicen.tandilrecicla.data.remote.ServiceGenerator;

import java.io.IOException;

import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private LoginViewModel loginViewModel;

    private RequestApi setApi;

    private APIService mAPIService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Context context = this;

        mAPIService = ApiUtils.getAPIService();

        setApi = ServiceGenerator.getRequestApi();

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
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
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

//        Address address = new Address();
//        address.setDepartment("Tandil");
//        address.setCity("Tandil");
//        address.setNumber(874);
//        address.setStreetAddress("Alberdi");
//        address.setCity("Tandil");
//        address.setState("Buenos Aires");
//        address.setZipCode("7000");
//        RegisteredUser registeredUser = new RegisteredUser();
//        registeredUser.setFirstName("Mauri");
//        registeredUser.setLastName("Arroqui");
//        registeredUser.setMail("mauriarroqui@gmail.com");
//        registeredUser.setUsername("marroqui2");
//        registeredUser.setAddress(address);

//        sendPost(registeredUser);

        loginViewModel.makeQuery("marroqui2").observe(this, new androidx.lifecycle.Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody responseBody) {
                Log.d(TAG, "onChanged: this is a live data response!");
                try {
                    Log.d(TAG, "onChanged: " + responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Recycling recycling = new Recycling();
        recycling.setBottles(1);
        recycling.setTetrabriks(5);
        recycling.setGlass(3);
        recycling.setPaperboard(4);
        recycling.setCans(2);
        recycling.setDate("2018-11-29");

        loginViewModel.postRecycling("marroqui2",recycling).observe(this, new androidx.lifecycle.Observer<Recycling>() {
            @Override
            public void onChanged(Recycling responseBody) {
                Log.d(TAG, "onChanged: this is a live data response!");
                Log.d(TAG, "onChanged: " + responseBody.getDate());
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

