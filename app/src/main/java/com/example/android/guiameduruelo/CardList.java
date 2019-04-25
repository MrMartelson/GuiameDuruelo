package com.example.android.guiameduruelo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CardList extends AppCompatActivity implements DownloadAsyncTask.DownloadAmenitiesInterface {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_card);
        super.onCreate(savedInstanceState);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        listView = findViewById(R.id.lv_cards);

        DownloadAsyncTask.delegate = this;

    }

    @Override
    public void onAmenitiesDownloaded(String amenitiesData) {
        ArrayList<Amenities> placesList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(amenitiesData);
            JSONArray amenitiesJsonArray = jsonObject.getJSONArray("array");

            for(int i = 0; i < amenitiesJsonArray.length(); i++){
                JSONObject amenitiesJsonObject = amenitiesJsonArray.getJSONObject(i);
                String title = amenitiesJsonObject.getString("title");
                String info = amenitiesJsonObject.getString("info");

                placesList.add(new Amenities(title, info));

                Log.d("DATOS", title + ": " + info);
                }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        AmenitiesAdapter amenitiesAdapter = new AmenitiesAdapter(this, R.layout.amenities_list_item, placesList);
        listView.setAdapter(amenitiesAdapter);
    }
}
