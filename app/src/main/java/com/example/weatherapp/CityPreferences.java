package com.example.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CityPreferences {
    private static final String CITY_TAG = "City";
    String city = "London";
    SharedPreferences userPreferences;

    public CityPreferences(Activity activity) {
        userPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public String getCity() {
      return   userPreferences.getString(CITY_TAG, city);
    }

    public void setCity(String city) {
        userPreferences.edit().putString(CITY_TAG, city).apply();
    }
}
