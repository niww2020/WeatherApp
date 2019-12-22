package com.example.weatherapp.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.CityPreferences;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherProvider;
import com.example.weatherapp.WeatherProviderListener;
import com.example.weatherapp.weatherModel.WeatherModel;
import com.google.android.material.textfield.TextInputEditText;

public class SettingsFragment extends Fragment implements WeatherProviderListener {


    SharedPreferences sharedPreferences;
    CityPreferences cityPreferences;
    TextInputEditText editText;
    SwitchCompat switchToDarkMode;
    TextView tvIdOfCity;
    WeatherModel weatherModel;

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_settings);
        settingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        cityPreferences = new CityPreferences(getActivity());
        switchToDarkMode = root.findViewById(R.id.switchToDarkMode);

        //change theme on checked switch
        switchToDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cityPreferences.getDarkMode() != isChecked) {
                    getActivity().recreate();
                }

            }
        });

        switchToDarkMode.setChecked(cityPreferences.getDarkMode());
        tvIdOfCity = root.findViewById(R.id.idOfCity);

//        tvIdOfCity.setText(WeatherProvider.getInstance().getWeatherModel().getCity().getName());

        //fixme add listener
//        WeatherProvider.getInstance().addListener(this);
//        weatherModel = new WeatherModel();


//        tvIdOfCity.setText(WeatherProvider.getInstance().getWeatherModel("Moscow").getCity().getName());//fixme обращение к сети из основного потока??
//        tvIdOfCity.setText(weatherModel.getCity().getName());


        editText = root.findViewById(R.id.etYourLocation);
        editText.setText(cityPreferences.getCity());
        Log.i("Switch", String.valueOf(switchToDarkMode.isChecked()));

        return root;
    }

    @Override
    public void onPause() {
        cityPreferences.setCity(String.valueOf(editText.getText()));
        cityPreferences.setDarkMode(switchToDarkMode.isChecked());
        Log.i("Switch", String.valueOf(switchToDarkMode.isChecked()));


        super.onPause();
    }

    @Override
    public void updateData(WeatherModel model) {
        //fixme listener of city
        //запуск в новом потоке?
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                editText.setText(model.getCity().getName());
//
//            }
//        });
        // но почему-то можно без runOnUiThread
//        (TextView)getActivity().findViewById(R.id.yourCurrentLocation).setText()
//        editText.setText(model.getCity().getName());
//        tvIdOfCity.setText(WeatherProvider.getInstance().getWeatherModel("Moscow").getCity().getName());
        tvIdOfCity.setText(model.getCity().getName());
//        editText.setText(WeatherProvider.getInstance().getWeatherModel("Moscow").getCity().getName());
//        cityPreferences.setCity(String.valueOf(model.getCity().getId()));
//        tvIdOfCity.setText("Moscow");
//        Toast.makeText(getContext(),model.getCity().getName() , Toast.LENGTH_SHORT).show();
//        Log.i("tvIdOfCity", model.getCity().getId().toString());
//        tvIdOfCity.setText(model.getCity().getName());

    }
}