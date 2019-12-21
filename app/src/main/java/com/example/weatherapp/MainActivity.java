package com.example.weatherapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.weatherapp.weatherModel.WeatherModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    CityPreferences cityPreferences;
    WeatherModel weatherModel;

    private static String KEY = "e83d0265c9865659af525e50e89b8edd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fixme theme apply on start app
        cityPreferences = new CityPreferences(this);

        if (cityPreferences.getDarkMode()) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }


        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);//FIXME fix

//        cityPreferences.setCity("Moscow");

//        loadWedViewOkHttpAndParseJson("Lisboa");
//        loadWedViewHttpURL();

        //fixme
//        WeatherProvider.getInstance();



    }

    @Override
    protected void onResume() {
        super.onResume();

//        Toast.makeText(getApplicationContext(),
//                                    weatherModel.getCity().getName()== null ? "null":weatherModel.getCity().getName()
//                                    , Toast.LENGTH_SHORT).show();

    }


    private void loadWedViewOkHttpAndParseJson( String cityName) {
        final Handler handler = new Handler();
        /** create Okhttp*/
        OkHttpClient client = new OkHttpClient();


        new Thread(new Runnable() {
            WeatherModel model = null;

            @Override
            public void run() {

                Request request = new Request.Builder()
                        .url("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid=" + KEY)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                                webView.loadUrl(url);
//                            webView.loadData(string, "text/html; charset=utf-8", "utf-8");

                            /** parsing JSON and save to WeatherModel.class*/
                            Gson gson = new Gson();
                            model = gson.fromJson(string, WeatherModel.class);
//                            Log.i("Gson", model.getCity().getName());

//                            Toast.makeText(getApplicationContext(),
//                                    model.getCity().getName()== null ? "null":model.getCity().getName()
//                                    , Toast.LENGTH_SHORT).show();

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void loadWedViewHttpURL() {
        final Handler handler = new Handler();

        Log.i("Thread", Thread.currentThread().getName());
        new Thread(new Runnable() {
        WeatherModel model = null ;
            @Override
            public void run() {
                try {
                    Log.i("Thread", Thread.currentThread().getName());

                    URL uri = new URL("http://api.openweathermap.org/data/2.5/weather?q=London&appid=" + KEY);
                    HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    connection.connect();


                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        final String result = in.lines().collect(Collectors.joining("\n"));
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
//                                webView.loadUrl(url);
//                                webView.loadData(result, "text/html; charset=utf-8", "utf-8");

                                /** parsing JSON and save to WeatherModel.class*/
                                Gson gson = new Gson();
                                model = gson.fromJson(result, WeatherModel.class);


                            }
                        });
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("WebView", "Fail 1", e);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("WebView", "Fail 2", e);

                }
            }
        }).start();
    }






}
