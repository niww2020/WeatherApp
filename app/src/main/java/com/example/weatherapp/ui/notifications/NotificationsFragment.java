package com.example.weatherapp.ui.notifications;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.CustomTextView;
import com.example.weatherapp.MainActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.TestIntentService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashSet;
import java.util.Set;

public class NotificationsFragment extends Fragment {
    CustomTextView customTextView;
    SharedPreferences sharedPreferences;
    TextInputEditText editText;
    TextView tv;
    LinearLayoutCompat llToDo;
    AppCompatButton button;
    Set<String> text;


    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        text = new HashSet<>();

        sharedPreferences = getActivity().getSharedPreferences("Preference", Context.MODE_PRIVATE);
        editText = root.findViewById(R.id.etToDo);
        llToDo = root.findViewById(R.id.llToDo);
        button = root.findViewById(R.id.bToDo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv = new TextView(getContext());
                tv.setTextSize(50);

                text.add(String.valueOf(editText.getText()));

//                sharedPreferences.edit().putStringSet("",)
                tv.setText(editText.getText());
                llToDo.addView(tv);
                editText.setText("");


            }
        });



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


    }
}