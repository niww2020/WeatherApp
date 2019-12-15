package com.example.weatherapp;

import android.os.Handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**Singleton to update our fragment
 * */
public class WeatherProvider {

    /**
     * Create
     */
    Set<WeatherProviderListener> listeners;
    Timer timer = null;
    private static WeatherProvider instance;

    public static WeatherProvider getInstance() {
        instance = instance == null ? new WeatherProvider() : instance;
        return instance;
    }

    public WeatherProvider() {
        listeners = new HashSet<>();
        startData();
    }

    public void addListener(WeatherProviderListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }
    public void removeListener(WeatherProviderListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }


    private void startData() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //fixme create listener
                for (WeatherProviderListener listener: listeners) {
                    listener.updateCity("New York");

                }
                

            }
        }, 100, 5000);
    }

     void stop() {
         if (timer != null) {
             timer.cancel();
         }

    }
}
