package com.example.android.guiameduruelo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;

public class CardList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_card);
        super.onCreate(savedInstanceState);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        ListView listView = (ListView) findViewById(R.id.lv_cards);

        ArrayList<Amenities> placesList = new ArrayList<>();
        placesList.add(new Amenities("Iglesia", "Calle de la Iglesia"));
        placesList.add(new Amenities("Ayuntamiento", "Calle de la Iglesia"));
        placesList.add(new Amenities("Casa Romulo", "Urbanización el Berrocal"));
        placesList.add(new Amenities("Médico", "Carretera"));
        placesList.add(new Amenities("Castroviejo", "Paraje rocoso"));

        AmenitiesAdapter amenitiesAdapter = new AmenitiesAdapter(this, R.layout.amenities_list_item, placesList);

        listView.setAdapter(amenitiesAdapter);
    }
}
