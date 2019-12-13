package com.example.weatherapp.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.CityPreferences;
import com.example.weatherapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class SettingsFragment extends Fragment {


    SharedPreferences sharedPreferences;
    CityPreferences cityPreferences;
    TextInputEditText editText;
    SwitchCompat switchToDarkMode;

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
        switchToDarkMode.setChecked(cityPreferences.getDarkMode());



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
}