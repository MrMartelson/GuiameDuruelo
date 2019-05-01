package com.example.android.guiameduruelo.database;



import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AmenitiesDao {

    @Query("SELECT * FROM amenitiesTable ORDER BY id")
    LiveData<List<AmenitiesEntry>> loadAllAmenities();

    @Insert
    void addAmenities(AmenitiesEntry amenities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAmenities(AmenitiesEntry amenities);

    @Delete
    void deleteAmenities(AmenitiesEntry amenities);
}
