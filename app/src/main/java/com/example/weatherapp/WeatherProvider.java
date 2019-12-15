package com.example.weatherapp;

import android.os.Handler;
import android.webkit.WebView;

import com.example.weatherapp.weatherModel.WeatherModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**Singleton to update our fragment
 * */
public class WeatherProvider {

    /**
     * Create
     */
    Set<WeatherProviderListener> listeners;
    Timer timer = null;
    private static WeatherProvider instance;
    private static String KEY = "e83d0265c9865659af525e50e89b8edd";
    private WeatherModel weatherModel;

    public static WeatherProvider getInstance() {
        instance = instance == null ? new WeatherProvider() : instance;
        return instance;
    }

    public WeatherProvider() {
//        loadWedViewOkHttpAndParseJson("Moscow");
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
                    listener.updateCity("Saratov");

                }
                

            }
        }, 100, 5000);
    }

     void stop() {
         if (timer != null) {
             timer.cancel();
         }

    }

    private void loadWedViewOkHttpAndParseJson( String сity) {
        final Handler handler = new Handler();
        /** create Okhttp*/
        OkHttpClient client = new OkHttpClient();


        new Thread(new Runnable() {
            WeatherModel model = null;

            @Override
            public void run() {

                Request request = new Request.Builder()
                        .url("http://api.openweathermap.org/data/2.5/weather?q="+сity+"&appid=" + KEY)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            /** parsing JSON and save to WeatherModel.class*/
                            Gson gson = new Gson();
                            model = gson.fromJson(string, WeatherModel.class);
//                            Log.i("Gson", new WeatherModel().getCity().getName());

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
