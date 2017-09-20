package com.smkapps.weatherforecast.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smkapps.weatherforecast.DBHelper;
import com.smkapps.weatherforecast.R;
import com.smkapps.weatherforecast.WeatherDetailActivity;

import java.util.ArrayList;

public class SavedCitiesFragment extends Fragment {
    DBHelper db;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_saved, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);
        db = new DBHelper(getContext());
        arrayList = db.getAllSavedCities();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrayList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), WeatherDetailActivity.class);
                intent.putExtra(WeatherDetailActivity.CITY_KEY, arrayList.get(i));
                startActivity(intent);
            }
        });
        db.closeDB();
        return view;
    }

}
