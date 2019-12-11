package com.example.weatherapp.ui.home;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.Weather;
import com.example.weatherapp.WeatherAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

//    private String url = "https://openweathermap.org/weathermap";
//    private String url = "https://github.com";
    private String url = "https://www.google.ru";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        WebView webView ;
        webView = root.findViewById(R.id.webView);
//        loadWedView(webView);
//        loadWedViewAsyncTask(webView);



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        loadWedViewAsyncTask();

    }

    private void loadWedViewAsyncTask(final WebView webView) {
        new AsyncTask<String, String, String>(){

            @Override
            protected void onPostExecute(String s) {
                if (s == null) {
                    webView.loadData(s,"text/html; charset=utf-8","utf-8");

                }
                super.onPostExecute(s);

            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    Log.i("Thread", Thread.currentThread().getName());

                    URL uri = new URL(url);
                    HttpsURLConnection connection = (HttpsURLConnection) uri.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    connection.connect();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        return in.lines().collect(Collectors.joining("\n"));
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("WebView", "Fail", e);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("WebView", "Fail", e);

                }

                return null;
            }
        }.execute(url);



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
                                webView.loadData(result,"text/html; charset=utf-8","utf-8");
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