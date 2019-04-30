package com.example.android.guiameduruelo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ListView;

import com.example.android.guiameduruelo.database.AmenitiesEntry;
import com.example.android.guiameduruelo.database.AppDatabase;
import com.example.android.guiameduruelo.database.DatabaseExecutor;

import java.util.ArrayList;
import java.util.List;

public class FavoritedList extends AppCompatActivity implements FavoritedAdapter.ItemClickListener {
    private RecyclerView mRecyclerView;
    private FavoritedAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.favorited_list);
        super.onCreate(savedInstanceState);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mRecyclerView = findViewById(R.id.recyclerViewAmenities);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FavoritedAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        mDb = AppDatabase.getInstance(getApplicationContext());


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
                DatabaseExecutor.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<AmenitiesEntry> amenities = mAdapter.getAmenities();
                        mDb.amenitiesDao().deleteAmenities(amenities.get(position));
                        retrieveAmenities();
                    }
                });
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveAmenities();
    }

    private void retrieveAmenities() {
        DatabaseExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<AmenitiesEntry> amenitiesEntries = mDb.amenitiesDao().loadAllAmenities();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setFavorited(amenitiesEntries);
                    }
                });
            }
        });
    }

    @Override
    public void onItemClickListener(int itemId) {

    }

}
