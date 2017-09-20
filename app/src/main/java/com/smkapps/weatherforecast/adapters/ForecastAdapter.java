package com.smkapps.weatherforecast.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smkapps.weatherforecast.R;
import com.smkapps.weatherforecast.models.threehourforecast.ThreeHourWeather;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    Context context;

    List<ThreeHourWeather> weatherList;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd, HH:mm:");

    public ForecastAdapter(Context context, List<ThreeHourWeather> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
    }

    public ForecastAdapter(List<ThreeHourWeather> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ThreeHourWeather weather = weatherList.get(position);
        holder.condition.setText(weather.getWeatherArray().get(0).getDescription());
        holder.minTemp.setText(weather.getMain().getTempMin()+"\u00B0" + "C");
        holder.maxTemp.setText(weather.getMain().getTempMax()+"\u00B0" + "C");
        holder.date.setText(formatter.format(new Date(weather.getDt()*1000)));
        String iconUrl = "http://openweathermap.org/img/w/" + weather.getWeatherArray().get(0).getIcon() + ".png";
        Picasso.with(context).load(iconUrl).into(holder.weatherImg);

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date, condition, minTemp, maxTemp;

        private ImageView weatherImg;

        public ViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date_tv);
            condition = (TextView) view.findViewById(R.id.condition_tv);
            minTemp = (TextView) view.findViewById(R.id.min_temp_tv);
            maxTemp = (TextView) view.findViewById(R.id.max_temp_tv);
            weatherImg = (ImageView) view.findViewById(R.id.imageView);
        }
    }

}
