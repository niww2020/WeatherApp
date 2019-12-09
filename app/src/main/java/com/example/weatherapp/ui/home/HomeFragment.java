package com.example.weatherapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.CustomViewGraphics;
import com.example.weatherapp.R;
import com.example.weatherapp.Weather;
import com.example.weatherapp.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

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


        //todo create graphic in custom view
        CustomViewGraphics customViewGraphics = new CustomViewGraphics(getContext());
        LinearLayoutCompat linearLayoutCompat = root.findViewById(R.id.llFragmentHome);


        linearLayoutCompat.addView(customViewGraphics);
//        linearLayoutCompatView.setOrientation(LinearLayoutCompat.HORIZONTAL);
//        horizontalScrollView.addView(linearLayoutCompatView);
//        linearLayoutCompatView.setLayoutParams(layoutParams);
//        horizontalScrollView.addView(customViewGraphics);
//        linearLayoutCompat.addView(customViewGraphics);





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