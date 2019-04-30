package com.example.android.guiameduruelo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;


import com.example.android.guiameduruelo.database.AppDatabase;

import java.util.ArrayList;

public class CardList extends AppCompatActivity implements DownloadAsyncTask.DownloadAmenitiesInterface {
    ListView listView;
    ArrayList<Amenities> markers;
    ArrayList<Amenities> favorited;
    private static final String TAG = "CARDLIST";
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_card);
        super.onCreate(savedInstanceState);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        listView = findViewById(R.id.lv_cards);

        DownloadAsyncTask.delegate = this;

        if(savedInstanceState != null){
            Intent i = getIntent();
            if(i.getBooleanExtra("favorited", true)) {
                mDb = AppDatabase.getInstance(getApplicationContext());
                AmenitiesAdapter amenitiesAdapter = new AmenitiesAdapter(this, R.layout.favorited_list_item, favorited);
                listView.setAdapter(amenitiesAdapter);
            } else{
            markers = savedInstanceState.getParcelableArrayList("key");
            AmenitiesAdapter amenitiesAdapter = new AmenitiesAdapter(this, R.layout.amenities_list_item, markers);
            listView.setAdapter(amenitiesAdapter);
            }
        }

    }

    @Override
    public void onAmenitiesDownloaded(ArrayList<Amenities> placesList) {

        markers = placesList;

            AmenitiesAdapter amenitiesAdapter = new AmenitiesAdapter(this, R.layout.amenities_list_item, markers);
            listView.setAdapter(amenitiesAdapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", markers);
        super.onSaveInstanceState(outState);
    }

    public void addMarkersToMap(View view) {
        Intent intent = new Intent(CardList.this, MapActivity.class);
        intent.putParcelableArrayListExtra("key", markers);
        startActivity(intent);
    }

}
