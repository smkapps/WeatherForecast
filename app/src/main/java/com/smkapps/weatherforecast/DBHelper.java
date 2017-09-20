package com.smkapps.weatherforecast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "cityBase.db";
    Context mContext;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  IF NOT EXISTS cities (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL DEFAULT 'na')");
        db.execSQL("CREATE TABLE  IF NOT EXISTS saved_cities (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL DEFAULT 'na')");
        try {
            JSONArray array = new JSONArray(loadJSONFromAsset());

            for (int i = 0; i < array.length(); i++) {
                JSONObject cityJSON = array.getJSONObject(i);
                String name = cityJSON.getString("name");
                insertCity(db, name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isCityInDB(String s) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{"name"};
        String where = "name" + " = ?";
        String[] whereArgs = new String[]{s};

        Cursor cursor = db.query("saved_cities", columns, where, whereArgs, null, null, null, "1");
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    public void closeDB() {
        getWritableDatabase().close();
    }

    public ArrayList<String> getAllCities() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM cities", null);
        ArrayList<String> arrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String s = cursor.getString(1);
            arrayList.add(s);

        }
        return arrayList;
    }

    public void addCityToSaved(String name) {
        if (!isCityInDB(name)) {
            ContentValues cityValues = new ContentValues();
            cityValues.put("name", name);
            getWritableDatabase().insert("saved_cities", null, cityValues);
        }
    }

    public ArrayList<String> getAllSavedCities() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM saved_cities", null);
        ArrayList<String> arrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String s = cursor.getString(1);
            arrayList.add(s);

        }
        return arrayList;
    }

    private String loadJSONFromAsset() {
        String json = null;

        try {
            InputStream is = mContext.getAssets().open("city.list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void insertCity(SQLiteDatabase db, String name) {
        ContentValues cityValues = new ContentValues();
        cityValues.put("name", name);
        db.insert("cities", null, cityValues);
    }


}
