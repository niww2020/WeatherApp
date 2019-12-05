package com.example.weatherapp;

/**
 * Realize  Singleton
 */
public class WeatherSettingsModel {
    String yourLocation = "London";
    boolean showPicture = true;

    private static WeatherSettingsModel instance;

    public static WeatherSettingsModel getInstance() {

        instance = instance == null ? new WeatherSettingsModel() : instance;
        return instance;

    }

    public String getYourLocation() {
        return yourLocation;
    }

    public void setYourLocation(String yourLocation) {
        this.yourLocation = yourLocation;
    }

    public boolean isShowPicture() {
        return showPicture;
    }

    public void setShowPicture(boolean showPicture) {
        this.showPicture = showPicture;
    }
}
