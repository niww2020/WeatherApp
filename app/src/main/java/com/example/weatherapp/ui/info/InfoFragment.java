package com.example.weatherapp.ui.info;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.R;
import com.example.weatherapp.ui.home.HomeViewModel;

public class InfoFragment extends Fragment {
    Intent intent;
    TextView linkToGit;


    private InfoViewModel infoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        infoViewModel =
                ViewModelProviders.of(this).get(InfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_info, container, false);

        linkToGit = root.findViewById(R.id.linkToGitHub);

        linkToGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent = new Intent(getContext(),startActivity(Uri.parse(),););
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/niww1919/WeatherApp")));

            }
        });

        return root;


    }
}