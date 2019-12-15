package com.example.weatherapp.ui.info;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.R;
import com.example.weatherapp.ui.home.HomeViewModel;
import com.squareup.picasso.Picasso;

public class InfoFragment extends Fragment {
    Intent intent;
    TextView linkToGit;
    LinearLayout llInfo;
    ImageView ivInfo;



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
        root.findViewById(R.id.bToCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+8 8"));
                startActivity(intent);
            }
        });

        Picasso.get()
                .load("http://pngimg.com/uploads/github/github_PNG40.png")
                .resize(50,50)
                .into((ImageView) root.findViewById(R.id.ivInfo));



        return root;


    }
}