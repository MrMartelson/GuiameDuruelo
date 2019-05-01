package com.example.android.guiameduruelo.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ViewModel extends AndroidViewModel {


    private LiveData<List<AmenitiesEntry>> amenities;

    public ViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        amenities = database.amenitiesDao().loadAllAmenities();
    }

    public LiveData<List<AmenitiesEntry>> getAmenities() {
        return amenities;
    }

}
