package com.example.weatherapp.ui.notifications;

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
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.CustomTextView;
import com.example.weatherapp.R;
import com.example.weatherapp.todo.ToDoAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class NotificationsFragment extends Fragment {
    public static final String SET_KEY = "Set";
    CustomTextView customTextView;
    TextInputEditText editText;
    TextView tv;
    LinearLayoutCompat llToDo;
    AppCompatButton button;
    Set<String> stringSet;
    SharedPreferences userPreferences;
    RecyclerView recyclerView;


    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);


        userPreferences = getActivity().getSharedPreferences("Preference", Context.MODE_PRIVATE);
        //fixme always create new HashSet
        stringSet = userPreferences.getStringSet(SET_KEY, new HashSet<String>());
        Log.i("stringSet", String.valueOf(stringSet.size()));

        editText = root.findViewById(R.id.etToDo);
        llToDo = root.findViewById(R.id.llToDo);
        button = root.findViewById(R.id.bToDo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv = new TextView(getContext());
//                tv.setTextSize(50);

                stringSet.add(String.valueOf(editText.getText()));

                tv.setText(String.valueOf(userPreferences.getStringSet("Set", stringSet)));

                Log.i("stringSet", String.valueOf(stringSet.size()));


                llToDo.addView(tv);
                editText.setText("");


            }
        });

        /**
         loadRecycleView();
         */
        recyclerView = root.findViewById(R.id.rvViewToDo);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ToDoAdapter adapter = new ToDoAdapter();
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration();


        return root;

    }

    private void loadRecycleView() {


    }

    @Override
    public void onResume() {
        super.onResume();
//        SensorManager sensorManager = (SensorManager)
    }

    @Override
    public void onPause() {
        userPreferences.edit().putStringSet("Set", stringSet).apply();
        super.onPause();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}