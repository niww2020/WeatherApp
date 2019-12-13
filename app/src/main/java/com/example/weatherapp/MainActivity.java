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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private String url = "https://www.google.ru";
    CityPreferences cityPreferences;


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




    }

    @Override
    protected void onResume() {
        super.onResume();

        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            Log.i("Sensor", sensor.getName());

        }

        final Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                Log.i("Sensor", "Sensor " + sensor.getName() + " " + sensorEvent.values[0]);

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, sensor, 100);

//        loadWedView((WebView) findViewById(R.id.webView));



    }

    private void loadWedView(final WebView webView) {
        final Handler handler = new Handler();
        Log.i("Thread", Thread.currentThread().getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("Thread", Thread.currentThread().getName());

                    URL uri = new URL(url);
                    HttpsURLConnection connection = (HttpsURLConnection) uri.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    connection.connect();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        final String result = in.lines().collect(Collectors.joining("\n"));
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                webView.loadData(result, "text/html; charset=utf-8", "utf-8");
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
