package com.example.android.guiameduruelo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CardList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_card);
        super.onCreate(savedInstanceState);

        ListView listView = (ListView) findViewById(R.id.lv_cards);

        ArrayList<String> placesList = new ArrayList<>();
        placesList.add("Iglesia");
        placesList.add("Casa Romulo");
        placesList.add("Ayuntamiento");
        placesList.add("Médico");
        placesList.add("Castroviejo");

        ArrayAdapter<String> placesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, placesList);

        listView.setAdapter(placesAdapter);
    }
}
