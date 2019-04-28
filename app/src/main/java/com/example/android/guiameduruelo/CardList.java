package com.example.android.guiameduruelo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;


import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CardList extends AppCompatActivity implements DownloadAsyncTask.DownloadAmenitiesInterface {
    ListView listView;
    ArrayList<Amenities> markers;
    private static final String TAG = "CARDLIST";

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
    public void onAmenitiesDownloaded(ArrayList<Amenities> placesList) {

            AmenitiesAdapter amenitiesAdapter = new AmenitiesAdapter(this, R.layout.amenities_list_item, placesList);
            listView.setAdapter(amenitiesAdapter);

            markers = placesList;

    }

    public void addMarkersToMap(View view) {

        Intent intent = new Intent(CardList.this, MapActivity.class);
        intent.putParcelableArrayListExtra("key", markers);
        startActivity(intent);
    }
}
