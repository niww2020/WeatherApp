package com.example.weatherapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.CityPreferences;
import com.example.weatherapp.R;
import com.example.weatherapp.Weather;
import com.example.weatherapp.WeatherAdapterRecycleView;
import com.example.weatherapp.weatherModel.WeatherModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    WebView webView ;
    TextView yourCurrentLocation;
    CityPreferences cityPreferences;
    WeatherModel weatherModel;



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
        yourCurrentLocation = root.findViewById(R.id.yourCurrentLocation);
        cityPreferences = new CityPreferences(getActivity());

        /**Get city from  cityPreferences*/
        yourCurrentLocation.setText(cityPreferences.getCity());



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
        WeatherAdapterRecycleView adapter = new WeatherAdapterRecycleView(getContext(), weatherOFWeekDays);
        recyclerView.setAdapter(adapter);


//        Picasso.LoadedFrom
//        Picasso.get()
//                .load()
//                .info();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        yourCurrentLocation.setText(weatherModel.getCity().getName() + weatherModel.getCity().getId());
    }
}