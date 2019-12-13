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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    WebView webView ;



    //    private String url = "https://openweathermap.org/weathermap";
//    private String url = "https://github.com";
    private String url = "https://www.google.ru";
//    private String url = "http://4pda.ru";


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

//        webView = root.findViewById(R.id.webView);
//        loadWedView(webView);
//        loadWedViewAsyncTask(webView);
//        webView.loadUrl(url);

        List<Weather> weatherOFWeekDays = new ArrayList<>();
        weatherOFWeekDays.add(new Weather("+10", "Monday", "1"));
        weatherOFWeekDays.add(new Weather("+5", "Tuesday", "2"));
        weatherOFWeekDays.add(new Weather("+3", "Wednesday", "3"));
        weatherOFWeekDays.add(new Weather("+6", "Thursday", "4"));
        weatherOFWeekDays.add(new Weather("+1", "Friday", "5"));
        weatherOFWeekDays.add(new Weather("-1", "Saturday", "6"));
        weatherOFWeekDays.add(new Weather("0", "Sunday", "7"));


        RecyclerView recyclerView = root.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        WeatherAdapter adapter = new WeatherAdapter(getContext(), weatherOFWeekDays);
        recyclerView.setAdapter(adapter);



        return root;
    }


}