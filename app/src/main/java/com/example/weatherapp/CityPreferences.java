package com.example.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CityPreferences {
    private static final String CITY_TAG = "City";
    public static final String DARK_MODE = "DarkMode";
    String city = "London";
    SharedPreferences userPreferences;
    Boolean isDarkMode = false;


    public CityPreferences(Activity activity) {
        userPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public String getCity() {
      return   userPreferences.getString(CITY_TAG, city);
    }

    public void setCity(String city) {
        userPreferences.edit().putString(CITY_TAG, city).apply();
    }

    public Boolean getDarkMode() {
        return userPreferences.getBoolean(DARK_MODE,isDarkMode);
    }

    public void setDarkMode(Boolean darkMode) {
        userPreferences.edit().putBoolean(DARK_MODE, darkMode).apply();
    }
}
