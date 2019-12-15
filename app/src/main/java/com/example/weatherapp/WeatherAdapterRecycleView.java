package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapterRecycleView extends RecyclerView.Adapter<WeatherAdapterRecycleView.ViewHolder> {

    private LayoutInflater inflater;
    private List<Weather> weather;
//    private ViewHolder viewHolder;

    public WeatherAdapterRecycleView(Context context, List<Weather> weather) {
        this.inflater = LayoutInflater.from(context);
        this.weather = weather;
    }

    
    @Override
    public WeatherAdapterRecycleView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        View view = inflater.inflate(R.layout.weather_of_week_day, viewGroup, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(WeatherAdapterRecycleView.ViewHolder viewHolder, int position) {//todo why viewholder type??
        Weather currentWeather = this.weather.get(position);
        viewHolder.dayOfWeek.setText(currentWeather.getDayOfWeek());
        viewHolder.dayOfMonth.setText(currentWeather.getDayOfMonth());
        viewHolder.temperatureOfDay.setText(currentWeather.getTemperatureOfDay());
        viewHolder.imageViewOfDay.setImageResource(R.drawable.ic_filter_drama_black_24dp);
//        viewHolder.imageViewOfDay.setImageURI(Uri.parse(String.valueOf(R.drawable.ic_beach_access_black_24dp)));

    }

    @Override
    public int getItemCount() {
        return weather.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dayOfWeek;
        private TextView dayOfMonth;
        private TextView temperatureOfDay;
        private ImageView imageViewOfDay;

        public ViewHolder(  View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.dayOfWeek);
            temperatureOfDay = itemView.findViewById(R.id.temperatureOfDay);
            dayOfMonth = itemView.findViewById(R.id.dayOfMonth);
            imageViewOfDay = itemView.findViewById(R.id.imageViewOfDay);
        }

        public TextView getDayOfWeek() {
            return dayOfWeek;
        }

        public TextView getDayOfMonth() {
            return dayOfMonth;
        }

        public TextView getTemperatureOfDay() {
            return temperatureOfDay;
        }

        public ImageView getImageViewOfDay() {
            return imageViewOfDay;
        }
    }
}
