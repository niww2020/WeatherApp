package com.example.weatherapp.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private TextInputEditText editText;
    SharedPreferences sharedPreferences;
    Set<String> set;
    List<String> list;

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

        /** test  sharedPreferences
         * */
        sharedPreferences = getActivity().getSharedPreferences("Preference", Context.MODE_PRIVATE);
        editText = root.findViewById(R.id.etYourLocation);
        String currentCity = sharedPreferences.getString("City", "");
        set = new TreeSet<>();
        set.add(currentCity);
        editText.setText(currentCity);





        Log.i("Key", sharedPreferences.getString("City", ""));

        return root;
    }

    @Override
    public void onPause() {
        sharedPreferences.edit().putString("City", editText.getText().toString()).apply();
        Log.i("Key", sharedPreferences.getString("City", ""));
        sharedPreferences.edit().putStringSet("Key",set).apply();
        super.onPause();
    }
}