package com.example.weatherapp;

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreference {
    private static final String LONDON = "London";
    private static final String KEY = "city";
    private SharedPreferences userPreferences;

    public CityPreference(Activity activity) {
        userPreferences = activity.getPreferences(Activity.MODE_PRIVATE);

    }

    String getCity() {
        return userPreferences.getString(KEY, LONDON);

    }

    void setCity(String city) {
        userPreferences.edit().putString(KEY,city).apply();
    }


}
