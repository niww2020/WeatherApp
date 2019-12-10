package com.example.weatherapp.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.weatherapp.CustomTextView;
import com.example.weatherapp.R;
import com.example.weatherapp.Test.TestFragment;
import com.example.weatherapp.ui.settings.SettingsFragment;
import com.google.android.material.tabs.TabItem;

public class NotificationsFragment extends Fragment {
    CustomTextView customTextView;

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
/** Test fragment manager*/
//
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        TestFragment testFragment = new TestFragment();
//        transaction.add(R.id.llToFragment, testFragment);
//        transaction.commit();

        //todo test tabitem

//        root.findViewById(R.id.ti1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////
//                Toast.makeText(getContext(), "Monday", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        root.findViewById(R.id.ti2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });


/** test call */
        root.findViewById(R.id.bToCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:89112406009"));
                startActivity(intent);
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