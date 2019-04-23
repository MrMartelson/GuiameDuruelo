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

    public Amenities(String place, String info){
        this.place = place;
        this.info = info;
    }

    public String getPlace(){
        return place;
    }

    public String getInfo(){
        return info;
    }
}
