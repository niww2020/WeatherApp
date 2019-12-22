package com.example.weatherapp;

import android.os.Handler;

import com.example.weatherapp.weatherModel.WeatherModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Singleton to update our fragment
 */
public class WeatherProvider {

    /**
     * Create
     */
    Set<WeatherProviderListener> listeners;
    Timer timer = null;
    Handler handler = new Handler();
    private static WeatherProvider instance;
    private static String KEY = "e83d0265c9865659af525e50e89b8edd";
    private Retrofit retrofit;
    OpenWeather weatherApi;
    WeatherModel weatherModel;


    public static WeatherProvider getInstance() {
        instance = instance == null ? new WeatherProvider() : instance;
        return instance;
    }

    public WeatherProvider() {
        listeners = new HashSet<>();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi = retrofit.create(OpenWeather.class);
        startData();


    }

    interface OpenWeather {
        @GET("data/2.5/weather")
        Call<WeatherModel> getWeatherRetrofit(@Query("q") String q, @Query("appid") String key);
    }

    private WeatherModel getWeatherRetrofit(String city) {

//        String urlCity = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + KEY;
        Call<WeatherModel> call = weatherApi.getWeatherRetrofit(city + ",ru", "e83d0265c9865659af525e50e89b8edd");
        retrofit2.Response<WeatherModel> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.body();


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
                //work thread
//                 WeatherModel model = getWeatherRetrofit("Moscow");
//                WeatherModel model = getWeatherUrl("Moscow");
                WeatherModel model = getWeatherByOkHttp("Lisbon");
//                if (model == null) {
//                    return;
//                }

//                        WeatherModel model = getWeatherByOkHttp("Moscow");

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        for (WeatherProviderListener listener : listeners) {
                            listener.updateData(model);
                        }

                    }
                });


            }
        }, 2000, 10000);
    }

    void stop() {
        if (timer != null) {
            timer.cancel();
        }

    }


    private WeatherModel getWeatherUrl(String city) {

        String urlCity = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + KEY;
        WeatherModel model = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(urlCity);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.getRequestMethod();
            urlConnection.setConnectTimeout(10000);
            urlConnection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                //fixme my version in 6.0
                String result = in.lines().collect(Collectors.joining("\n"));
                Gson gson = new Gson();
                model = gson.fromJson(result, WeatherModel.class);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    private WeatherModel getWeatherByOkHttp(String city) {

        WeatherModel model = null;
//        create Okhttp
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + KEY)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();

            /** parsing JSON and save to WeatherModel.class*/
            Gson gson = new Gson();
            model = gson.fromJson(string, WeatherModel.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }

    public WeatherModel getWeatherModel(String s) {
//        return getWeatherByOkHttp(s);
        return getWeatherRetrofit("Moscow");
    }

}
