package com.example.weatherapp.ui.notifications;

import android.content.Intent;
import android.net.Uri;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class NotificationsFragment extends Fragment {
    CustomTextView customTextView;
    private String url = "https://www.google.ru";
    WebView webView;
    SeekBar seekBar;


    private NotificationsViewModel notificationsViewModel;

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


        return root;

    }

    private void createCircle(View root) {
        ((LinearLayoutCompat)root.findViewById(R.id.llNotificationsFragment))
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
        loadWedView(webView);



    }

    private void loadWedViewRetrofit(final WebView webView) {

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

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                webView.loadUrl(url);
//                                webView.loadData(result, "text/html; charset=utf-8", "utf-8");
                            }
                        });

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        final String result = in.lines().collect(Collectors.joining("\n"));
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