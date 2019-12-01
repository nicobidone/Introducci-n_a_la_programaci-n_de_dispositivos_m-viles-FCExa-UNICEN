package com.unicen.tandilrecicla;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.unicen.tandilrecicla.ui.login.LoginActivity;

import static com.unicen.tandilrecicla.data.Constants.EMPTY_STRING;
import static com.unicen.tandilrecicla.data.Constants.FILE_NAME;
import static com.unicen.tandilrecicla.data.Constants.KEY_LOGGED;
import static com.unicen.tandilrecicla.data.Constants.KEY_USER;

public class MainActivity extends AppCompatActivity {

    private MainActivitySharedViewModel model;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.activity_main_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        model = ViewModelProviders.of(this).get(MainActivitySharedViewModel.class);
        if (!sharedPreferences.getBoolean(KEY_LOGGED, false)) {
            startActivity(new Intent(LoginActivity.getIntent(this)));
        } else {
            if (model.getFirstOnCreate()) {
                String welcome = "Welcome " + sharedPreferences.getString(KEY_USER, EMPTY_STRING) + "!";
                Toast.makeText(this, welcome, Toast.LENGTH_LONG).show();
                model.setFirstOnCreate();
            }
            model.setSelect(sharedPreferences.getString(KEY_USER, EMPTY_STRING));
            model.setLogged(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String getSelected = model.getSelected().getValue();
        editor.putString(KEY_USER, (getSelected != null ? getSelected : EMPTY_STRING));
        Boolean getLogged = model.getLogged().getValue();
        editor.putBoolean(KEY_LOGGED, (getLogged != null ? getLogged : false));

        editor.apply();
    }
}
