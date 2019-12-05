package com.example.weatherapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String NameSharedPreference = "LOGIN";
    private static final String IsDarkTheme = "IsDarkTheme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (isDarkTheme()) {
            setTheme(R.style.AppDarkTheme);
        } else {
            setTheme(R.style.AppTheme);

        }


    }


    protected boolean isDarkTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPreferences.getBoolean(IsDarkTheme, true);
    }

    protected void setDarkTheme(boolean isDarkTheme) {
        SharedPreferences sharedPrefer = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefer.edit();
        editor.putBoolean(IsDarkTheme, isDarkTheme);
        editor.apply();
    }

}
