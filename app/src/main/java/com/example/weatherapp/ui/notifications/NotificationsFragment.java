package com.example.weatherapp.ui.notifications;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.CustomTextView;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherProvider;
import com.example.weatherapp.weatherModel.WeatherModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class NotificationsFragment extends Fragment {
    CustomTextView customTextView;
    private String url = "https://www.google.ru";

    WebView webView;
    SeekBar seekBar;
    Retrofit retrofit;
    WeatherModel weatherModel;
    WeatherModel model ;
    TextView tvNotifications;


    private static String KEY = "e83d0265c9865659af525e50e89b8edd";


    private NotificationsViewModel notificationsViewModel;
    private String cityName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        webView = root.findViewById(R.id.webView);
        seekBar = root.findViewById(R.id.seekBar);
        tvNotifications = root.findViewById(R.id.tvNotifications);
        model = null;



        /** seek bar listener */
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    webView.zoomBy(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        webView.loadUrl(url);
//        loadWedViewOkHttpAndParseJson(webView,"Lisboa");


        return root;

    }

    private void createCircle(View root) {
        ((LinearLayoutCompat) root.findViewById(R.id.llNotificationsFragment))
                .addView(new CustomTextView(getContext()));//todo what a context? I can use, and why??
    }

    @Override
    public void onResume() {
        super.onResume();
//        SensorManager sensorManager = (SensorManager)
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        loadWedViewHttpURL(webView);
//        loadWedViewOkHttpAndParseJson(webView,"Moscow");
//        tvNotifications.setText(model.getCity().getName());
//        weatherModel = new WeatherModel();
//        Log.i("Gson", weatherModel.getCity().toString());

    }

    private void loadWedViewRetrofit(final WebView webView) {

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {


            }
        }).start();

    }


    private void loadWedViewHttpURL(final WebView webView) {
        final Handler handler = new Handler();
        Log.i("Thread", Thread.currentThread().getName());
        new Thread(new Runnable() {
            WeatherModel model = null;
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
                                webView.loadData(result, "text/html; charset=utf-8", "utf-8");


                                /** parsing JSON and save to WeatherModel.class*/
                                Gson gson = new Gson();
                                model = gson.fromJson(result, WeatherModel.class);

//                                Log.i("Gson", model.getCity().getName());

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

    private void loadWedViewOkHttpAndParseJson(final WebView webView, String city) {
        final Handler handler = new Handler();
        /** create Okhttp*/
        OkHttpClient client = new OkHttpClient();


        new Thread(new Runnable() {
//            WeatherModel model = null;

            @Override
            public void run() {

                Request request = new Request.Builder()
                        .url("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=" + KEY)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                                webView.loadUrl(url);
                            webView.loadData(string, "text/html; charset=utf-8", "utf-8");

                            /** parsing JSON and save to WeatherModel.class*/
                            Gson gson = new Gson();
                            model = gson.fromJson(string, WeatherModel.class);
//
                            tvNotifications.setText(model.getCity().getName());

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void loadWedWeatherMap(final WebView webView) {
//        String urlMap = "https://maps.owm.io/map/temp_new/10/55/45.png?appid=";
        String urlMap = "https://tile.openweathermap.org/map/temp_new/";
        int zoom1 = 10;
        double lat1 = 47.968056d;
        double lon1 = 7.909167d;


        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl(urlMap + getTileNumber(lat1, lon1, zoom1) + KEY);
                    }
                });


            }
        }).start();
    }

    /**
     * https://wiki.openstreetmap.org/wiki/Slippy_map_tilenames#Java
     * convert coordination
     */

    String getTileNumber(double lat, double lon, int zoom) {
        int xtile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
        int ytile = (int) Math.floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1 << zoom));
        if (xtile < 0)
            xtile = 0;
        if (xtile >= (1 << zoom))
            xtile = ((1 << zoom) - 1);
        if (ytile < 0)
            ytile = 0;
        if (ytile >= (1 << zoom))
            ytile = ((1 << zoom) - 1);
        return ("" + zoom + "/" + xtile + "/" + ytile + ".png?appid=");
    }
}