package com.smkapps.weatherforecast.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.smkapps.weatherforecast.DBHelper;
import com.smkapps.weatherforecast.R;
import com.smkapps.weatherforecast.WeatherDetailActivity;


public class CitiesFragment extends Fragment {
    AutoCompleteTextView city_et;
    ProgressBar progressBar;
    TextView infoTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_cities, container, false);
        city_et = (AutoCompleteTextView) view.findViewById(R.id.editTextCity);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        infoTextView = (TextView) view.findViewById(R.id.info_tv);
        FloatingActionButton searchButton = (FloatingActionButton) view.findViewById(R.id.searchButton);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!city_et.getText().toString().isEmpty()) {
                    Intent intent = new Intent(getActivity(), WeatherDetailActivity.class);
                    intent.putExtra(WeatherDetailActivity.CITY_KEY, city_et.getText().toString());
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new LoadCitiesTask().execute();
    }

    class LoadCitiesTask extends AsyncTask<Void, Void, ArrayAdapter<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            infoTextView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected ArrayAdapter<String> doInBackground(Void... voids) {
            DBHelper dbHelper = new DBHelper(getContext());
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext()
                    , android.R.layout.simple_dropdown_item_1line, dbHelper.getAllCities());
            dbHelper.closeDB();

            return arrayAdapter;
        }

        @Override
        protected void onPostExecute(ArrayAdapter<String> arrayAdapter) {
            city_et.setAdapter(arrayAdapter);
            infoTextView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
