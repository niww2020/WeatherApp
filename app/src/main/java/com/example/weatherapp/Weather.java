package com.example.weatherapp;

import java.util.Date;

public class Weather {
    String temperatureOfDay;
    String dayOfWeek;
    String dayOfMonth;
    String cityName;
    Date timeInCity;

    //    ImageView imageViewOfDay;



    public Weather(String temperatureOfDay, String dayOfWeek, String dayOfMonth) {
        this.temperatureOfDay = temperatureOfDay;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
    }

    public String getTemperatureOfDay() {
        return temperatureOfDay;
    }

    public void setTemperatureOfDay(String temperatureOfDay) {
        this.temperatureOfDay = temperatureOfDay;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTimeInCity() {
        return timeInCity.toString().substring(0,16);
    }

    public void setTimeInCity(Date timeInCity) {
        this.timeInCity = timeInCity;
    }
}
