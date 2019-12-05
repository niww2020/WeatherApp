package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.textfield.TextInputEditText;

public class WeatherSettingsActivity extends BaseActivity {

    public static final int RESULT_OK = 1;
    public static final String YOUR_LOCATION = "Your location";
    public static final String CITY_NAME = "City Name";
    TextView textView;
    ListView listView;
    LinearLayout linearLayout;
    SwitchCompat aSwitchSetDarkTheme;
    String city;
    TextInputEditText etYourLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_settings);

        aSwitchSetDarkTheme = findViewById(R.id.switch1);
        aSwitchSetDarkTheme.setChecked(isDarkTheme());
        aSwitchSetDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setDarkTheme(isChecked);
                recreate();
            }
        });

        clickOnBackButton();

        etYourLocation = findViewById(R.id.etYourLocation);
//        etYourLocation.setText("New York");
//        etYourLocation.setText(WeatherSettingsModel.getInstance().getYourLocation());
//        restoreetYourLocation(savedInstanceState );

    }

    private void restoreetYourLocation(Bundle savedInstanceState) {
        if (savedInstanceState == null) return;
        etYourLocation.setText(savedInstanceState.getString(YOUR_LOCATION,"Saint Petersburg"));


    }


    private void clickOnBackButton() {
        findViewById(R.id.buttonBackOfSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo save data in singleton
//                WeatherSettingsModel.getInstance().setYourLocation(etYourLocation.getText().toString());

                returnResult();
            }
        });
    }

    public void returnResult() {
        Intent intent = new Intent();
        intent.putExtra(CITY_NAME, etYourLocation.getText().toString());
        setResult(RESULT_OK,intent);


        finish();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(YOUR_LOCATION,etYourLocation.getText().toString());

    }

}
