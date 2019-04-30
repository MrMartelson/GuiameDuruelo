package com.example.android.guiameduruelo.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "amenitiesTable")
public class AmenitiesEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String place;
    private String info;
    private String lat;
    private String lng;
    private String image;

    @Ignore
    public AmenitiesEntry(String place, String info, String lat, String lng, String image){
        this.place = place;
        this.info = info;
        this.lat = lat;
        this.lng = lng;
        this.image = image;

    }

    public AmenitiesEntry(int id, String place, String info, String lat, String lng, String image){
        this.id = id;
        this.place = place;
        this.info = info;
        this.lat = lat;
        this.lng = lng;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }



}
