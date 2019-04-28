package com.example.android.guiameduruelo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class Amenities implements Parcelable {

    public String place;
    public String info;
    public String lat;
    public String lng;

    public Amenities(String place, String info, String latitude, String longitude){
        this.place = place;
        this.info = info;
        this.lat = latitude;
        this.lng = longitude;
    }


    protected Amenities(Parcel in) {
        place = in.readString();
        info = in.readString();
        lat = in.readString();
        lng = in.readString();
    }

    public static final Creator<Amenities> CREATOR = new Creator<Amenities>() {
        @Override
        public Amenities createFromParcel(Parcel in) {
            return new Amenities(in);
        }

        @Override
        public Amenities[] newArray(int size) {
            return new Amenities[size];
        }
    };

    public String getPlace(){
        return place;
    }

    public String getInfo(){
        return info;
    }

    public String getLat() { return lat; }

    public String getLng() { return lng; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(place);
        dest.writeString(info);
        dest.writeString(lat);
        dest.writeString(lng);
    }
}
