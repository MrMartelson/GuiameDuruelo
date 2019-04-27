package com.example.android.guiameduruelo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Amenities {

    private String place;
    private String info;



    private String lat;
    private String lng;

    public Amenities(String place, String info, String latitude, String longitude){
        this.place = place;
        this.info = info;
        this.lat = latitude;
        this.lng = longitude;
    }

    public String getPlace(){
        return place;
    }

    public String getInfo(){
        return info;
    }

    public String getLat() { return lat; }

    public String getLng() { return lng; }
}
