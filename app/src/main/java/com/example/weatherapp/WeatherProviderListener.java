package com.example.weatherapp;

import com.example.weatherapp.weatherModel.WeatherModel;

public interface WeatherProviderListener {
    void updateData(WeatherModel model);
}
